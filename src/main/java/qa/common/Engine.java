package qa.common;

import org.testng.Reporter;
import qa.Entity.CheckList;
import qa.exception.HTTPException;
import qa.exception.RunException;
import qa.httpClient.HttpClientUtil;
import qa.httpClient.HttpRequestType;
import qa.httpClient.ResponseInfo;
import qa.utils.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangli on 21/4/2017.
 * 主要引擎，发送请求、结果验证（包含数据库）、LOG输出
 */
@SuppressWarnings("unchecked")
public class Engine {

    private static final String TAB= "\t";
    private static final String QUOTE= "\"";
    private static final String LR= "\r\n";

    /**
     * 根据接口类型，发送对应请求
     * @param httpClientUtil
     * @param map JSON统一模板
     * @param rspBody 请求响应内容
     * @return ResponseInfo
     * @throws HTTPException
     * @throws RunException
     */
    public ResponseInfo getResponseInfo(HttpClientUtil httpClientUtil, Map<String, ?> map, String rspBody) throws HTTPException, RunException {
        ResponseInfo responseInfo = new ResponseInfo();
        Map<String, String> headers = (Map<String, String>) map.get(Parameters.JSON_TEMPLATE_HEADERS);
        headers.put(Parameters.REQUEST_HEADERS_CLIENTTIME,String.valueOf(DateFormat.getCurrentTimeMillis()));
        Map<String, String> wsInfo = (Map<String, String>) map.get(Parameters.JSON_TEMPLATE_WSINFO);
        String requestType = wsInfo.get(Parameters.JSON_TEMPLATE_HEADERS_REQUEST_TYPE);
        log("url:"+wsInfo.get(Parameters.JSON_TEMPLATE_WSINFO_URL));

        String type = wsInfo.get(Parameters.JSON_TEMPLATE_HEADERS_REQUEST_TYPE);
        if (type.equals(HttpRequestType.HTTP_REQUEST_GET)){
            rspBody = "";
        }
        String newS = getClientSign(httpClientUtil,headers,rspBody);
        headers.put(Parameters.JSON_TEMPLATE_HEADERS_S, newS);

        if (requestType.equals(HttpRequestType.HTTP_REQUEST_POST)){
            // POST
            log("requestBody:"+rspBody);
            responseInfo = httpClientUtil.executePostKeepConnWithHeaders(
                    wsInfo.get(Parameters.JSON_TEMPLATE_WSINFO_URL),
                    headers,
                    rspBody);
//            Map<String,String> temp = JSONFormat.getMapFromJson(responseInfo.getContent());
//            if (temp.get(Parameters.RESPONES_BODY_CODE).equals(Parameters.RESPONES_BODY_CODE_INVALID_SIGN)){
//                // 如果code=200407 ，调用/demo/createClientSign 获取sign值
//                String newS = getClientSign(httpClientUtil,headers,rspBody);
//                headers.put(Parameters.JSON_TEMPLATE_HEADERS_S, newS);
//                responseInfo = httpClientUtil.executePostKeepConnWithHeaders(
//                        wsInfo.get(Parameters.JSON_TEMPLATE_WSINFO_URL),
//                        headers,
//                        rspBody);
//            }
        }else if(requestType.equals(HttpRequestType.HTTP_REQUEST_GET)){
            // GET
            responseInfo = httpClientUtil.executeGetKeepConnWithHeaders(
                    wsInfo.get(Parameters.JSON_TEMPLATE_WSINFO_URL),
                    headers);
        }else if(requestType.equals(HttpRequestType.HTTP_REQUEST_PUT)){
            // PUT
            responseInfo = httpClientUtil.executePutKeepConnWithHeaders(
                    wsInfo.get(Parameters.JSON_TEMPLATE_WSINFO_URL),
                    headers,
                    rspBody);
//            Map<String,String> temp = JSONFormat.getMapFromJson(responseInfo.getContent());
//            if (temp.get(Parameters.RESPONES_BODY_CODE).equals(Parameters.RESPONES_BODY_CODE_INVALID_SIGN)){
//                // 如果code=200407 ，调用/demo/createClientSign 获取sign值
//                String newS = getClientSign(httpClientUtil,headers,rspBody);
//                headers.put(Parameters.JSON_TEMPLATE_HEADERS_S, newS);
//                responseInfo = httpClientUtil.executePutKeepConnWithHeaders(
//                        wsInfo.get(Parameters.JSON_TEMPLATE_WSINFO_URL),
//                        headers,
//                        rspBody);
//            }
        }else if(requestType.equals(HttpRequestType.HTTP_REQUEST_PATCH)){
            // PATCH
            responseInfo = httpClientUtil.executePatchKeepConnWithHeaders(
                    wsInfo.get(Parameters.JSON_TEMPLATE_WSINFO_URL),
                    headers,
                    rspBody);
//            Map<String,String> temp = JSONFormat.getMapFromJson(responseInfo.getContent());
//            if (temp.get(Parameters.RESPONES_BODY_CODE).equals(Parameters.RESPONES_BODY_CODE_INVALID_SIGN)){
//                // 如果code=200407 ，调用/demo/createClientSign 获取sign值
//                String newS = getClientSign(httpClientUtil,headers,rspBody);
//                headers.put(Parameters.JSON_TEMPLATE_HEADERS_S, newS);
//                responseInfo = httpClientUtil.executePatchKeepConnWithHeaders(
//                        wsInfo.get(Parameters.JSON_TEMPLATE_WSINFO_URL),
//                        headers,
//                        rspBody);
//            }
        }

        return responseInfo;
    }

    /**
     * assert统一入口
     * @param caseInfo 单个case所有信息
     * @param responseInfo 请求响应信息
     * @param responseBody 请求响应body模板
     * @param dataBaseUtil 数据库工具类
     * @param checkList 检查点
     */
    public void assertResult(Map<String, ?> caseInfo, ResponseInfo responseInfo, Map<String, Object> responseBody, DataBaseUtil dataBaseUtil, CheckList checkList) {
//        AssertResult assertResult = new AssertResult(this);
        insertResponseBody(responseBody, responseInfo.getContent());
        AssertResult assertResult = new AssertResult(this, dataBaseUtil, checkList, responseBody);
        assertResult.assertAll();

    }

    /**
     * 把结果存入reponseBody, 暂时只支持map递归
     * @param responseBody
     * @param content
     */
    private void insertResponseBody(Map<String, Object> responseBody, String content) {
        try {
            Map map = JSONFormat.getMapFromJson(content);
            for (Map.Entry<String, ?> entry : responseBody.entrySet()) {
                String value = String.valueOf(entry.getValue());
                String key = entry.getKey();
                if (RegExp.findCharacters(value, "^\\$\\{.*\\}$")) {
                    if (null != map) {
                        responseBody.put(key, map.get(key));
                    } else {
                        responseBody.put(key, null);
                    }
                } else {
                    Map<String, Object> mapT = (Map<String, Object>) responseBody.get(key);
                    insertResponseBody(mapT, JSONFormat.getObjectToJson(map.get(key)));
                }
            }
        }catch (Exception e) {
            logRed(e.getMessage());
        }
    }

    /**
     * 黑色字体
     * @param log
     */
    public void log(String log){
        Reporter.log(getLogTime()+": "+"<pre style="+QUOTE+"color:black;font-size: 12px;"+QUOTE+ ">"+log+"</pre>");
    }

    /**
     * 黑色加粗字体
     * @param log
     */
    public void logBold(String log){
        Reporter.log(getLogTime()+": "+"<pre style="+QUOTE+"color:black;font-size: 12px;font-weight: bold;"+QUOTE+ ">"+log+"</pre>");
    }

    /**
     * 蓝色字体
     * @param log
     */
    public void logBlue(String log){
        Reporter.log(getLogTime()+": "+"<pre style="+QUOTE+"color:blue;font-size: 12px;"+QUOTE+ ">"+log+"</pre>");
    }

    /**
     * 红色字体
     * @param log
     */
    public void logRed(String log){
        Reporter.log(getLogTime()+": "+"<pre style="+QUOTE+"color:red;font-size: 12px;"+QUOTE+ ">"+log+"</pre>");
    }

    public void logEmpty(){
        Reporter.log("<pre style="+QUOTE+"color:red;font-size: 12px;"+QUOTE+ "> </pre>");
    }

    /**
     * 自定义日志
     * @param color 字体颜色
     * @param log log内容
     */
    public void log(String color,String log){
        Reporter.log(getLogTime()+": "+"<pre style="+QUOTE+"color:"+color+";font-size: 12px;"+QUOTE+">"+log+"</pre>");
    }

    /**
     * 获取当前时间
     * @return
     */
    public String getLogTime() {
        return DateFormat.getDate("yyyy:MM:dd-HH:mm:ss");
    }

    /**
     * 获取sign值
     * @param httpClientUtil http工具类
     * @param headers 所有头信息
     * @param rspBody 响应body模板
     * @return
     * @throws HTTPException
     * @throws RunException
     */
    public String getClientSign(HttpClientUtil httpClientUtil, Map<String, String> headers, String rspBody) throws HTTPException, RunException {
        log("获取sign...");
        String environment = Parameters.environment;

        Map<String, String> map = new HashMap<>();
        String clientId = headers.get(Parameters.REQUEST_HEADERS_CLIENTID);
        String clientTime = headers.get(Parameters.REQUEST_HEADERS_CLIENTTIME);
        map.put(Parameters.REQUEST_HEADERS_CLIENTID, clientId);
        map.put(Parameters.REQUEST_HEADERS_CLIENTTIME, clientTime);
        String url = "http://"+environment+".xxd.com/userCenter/demo/createClientSign?client_id="+clientId+"&client_time="+clientTime+"&client_key=123456&bodyString="+ StringUtil.urlEncoderUTF8(rspBody);
        log(url);
        ResponseInfo responseInfo = httpClientUtil.executeGetKeepConnWithHeaders(url,map);
        log("sign="+responseInfo.getContent());
        return responseInfo.getContent();
    }


}

