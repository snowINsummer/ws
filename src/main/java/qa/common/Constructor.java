package qa.common;

import qa.Entity.CheckList;
import qa.exception.HTTPException;
import qa.exception.RunException;
import qa.httpClient.HttpClientUtil;
import qa.httpClient.ResponseInfo;
import qa.utils.DataBaseUtil;

import java.util.*;

/**
 * Created by zhangli on 3/5/2017.
 * 所有case统一入口
 * 解析excel、数据库连接、http请求、assert
 */
@SuppressWarnings("unchecked")
public class Constructor {
    private Engine engine;
    ExcelData excelData;
    private HttpClientUtil httpClientUtil;
    private DataBaseUtil dataBaseUtil;
    private LinkedList<Map<String,Object>> caseInfo;
    private LinkedList<String> rspBody;
    private Map<String,Object> responseBody;
    private String excelPath = Parameters.excelPath;
    private LinkedList<CheckList> checkList;
    private LinkedList<String> description;

//    private Iterator<Object[]> dp;

    public Constructor(String classFullPath) throws Exception {
        if (null == excelPath || excelPath.isEmpty()) throw new Exception("excelPath异常：excelPath=" + excelPath);
        engine = new Engine();
        httpClientUtil = new HttpClientUtil();
        dataBaseUtil = new DataBaseUtil(Parameters.oracleUrl,Parameters.oracleUserName,Parameters.oraclePassword);
        excelData = new ExcelData(excelPath, classFullPath);
        caseInfo = excelData.getCaseInfo();
        rspBody = excelData.getRequestBody();
        checkList = excelData.getCheckList();
        description = excelData.getDescription();
    }

    public void termination(){
        httpClientUtil.termination();
        if (null != dataBaseUtil){
            dataBaseUtil.disConn();
        }
    }

    public void executeCase() throws HTTPException, RunException {
        Map<String, Object> allData = caseInfo.pop();
        ResponseInfo responseInfo = engine.getResponseInfo(httpClientUtil, allData, rspBody.pop());
        Map<String,Object> caseInfo = (Map<String, Object>) allData.get(Parameters.JSON_TEMPLATE_CASEINFO);
        engine.assertResult(caseInfo,responseInfo, responseBody,dataBaseUtil, checkList.pop());
//        engine.log(JSONFormat.getObjectToJson(json.pop()));
//        engine.log(responseInfo.getContent());
    }
    public void executeCase(Object o1, Object o2, Object o3, Object o4) throws Exception {
        engine.log("用例描述：" + o4.toString());
        Map<String, Object> allData = (Map<String, Object>) o1;
        String rspBody =  String.valueOf(o2);
        CheckList checkList = (CheckList) o3;
        ResponseInfo responseInfo = engine.getResponseInfo(httpClientUtil, allData, rspBody);
        engine.log("responseBody:"+responseInfo.getContent());
        Map<String,Object> caseInfo = (Map<String, Object>) allData.get(Parameters.JSON_TEMPLATE_CASEINFO);
        responseBody = excelData.getResponseBody();
        engine.assertResult(caseInfo,responseInfo, responseBody,dataBaseUtil, checkList);
//        engine.log(JSONFormat.getObjectToJson(json.pop()));
//        engine.log(responseInfo.getContent());
    }
    public Iterator<Object[]> getDp(){
        List<Object[]> toIter = new ArrayList<>();
        Object[] arrObj = new Object[]{caseInfo.pop(),rspBody.pop(), checkList.pop(), description.pop()};
        toIter.add(arrObj);
        return toIter.iterator();
    }

}
