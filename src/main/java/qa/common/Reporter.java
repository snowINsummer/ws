package qa.common;

import org.apache.log4j.Logger;
import org.testng.*;
import org.testng.xml.XmlSuite;
import org.uncommons.reportng.ReportNGUtils;
import qa.Entity.Result;
import qa.Entity.ResultData;
import qa.Entity.ResultDataItemList;
import qa.exception.HTTPException;
import qa.httpClient.HttpClientUtil;
import qa.httpClient.ResponseInfo;
import qa.utils.DateFormat;
import qa.utils.JSONFormat;
import qa.utils.RegExp;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangli on 15/5/2017.
 */
public class Reporter extends ReportNGUtils implements IReporter {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String ATDBackService = Parameters.Tyrant_Service_IP;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        List<ITestResult> list = new ArrayList<>();
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                ITestContext testContext = suiteResult.getTestContext();
                IResultMap passedTests = testContext.getPassedTests();
                IResultMap failedTests = testContext.getFailedTests();
                IResultMap skippedTests = testContext.getSkippedTests();
                IResultMap failedConfig = testContext.getFailedConfigurations();
                list.addAll(listTestResult(passedTests));
                list.addAll(listTestResult(failedTests));
                list.addAll(listTestResult(skippedTests));
                list.addAll(listTestResult(failedConfig));
            }
        }
        sort(list);
        saveResult(list);
    }

    public String getArguments(ITestResult result) {
        Object[] arguments = result.getParameters();
        Object[] args = (Object[]) arguments[0];
        int argsLen = args.length;
        Object[] newO = new Object[argsLen-1];
        for(int i = 0;i<args.length-1;i++){
            newO[i] = args[i];
        }
        String str = JSONFormat.getObjectToJson(newO);
        return str;
        /*
        ArrayList argumentStrings = new ArrayList(arguments.length);
        Object[] var7 = arguments;
        int var6 = arguments.length;
        for(int var5 = 0; var5 < var6; ++var5) {
            Object[] argument = (Object[]) var7[var5];
            argumentStrings.add(this.renderArgument(argument));
        }
        return this.commaSeparate(argumentStrings);
        */
    }

    private String getDescription(ITestResult result){
        Object[] arguments = result.getParameters();
        Object[] args = (Object[]) arguments[0];
        return args[args.length-1].toString();
    }

    private String commaSeparate(Collection<String> strings) {
        StringBuilder buffer = new StringBuilder();
        Iterator iterator = strings.iterator();

        while(iterator.hasNext()) {
            String string = (String)iterator.next();
            buffer.append(string);
            if(iterator.hasNext()) {
                buffer.append(", ");
            }
        }

        return buffer.toString();
    }

    private String renderArgument(Object argument) {
        return argument == null?"null":(argument instanceof String?"\"" + argument + "\"":(argument instanceof Character?"\'" + argument + "\'":argument.toString()));
    }

    private ArrayList<ITestResult> listTestResult(IResultMap resultMap){
        Set<ITestResult> results = resultMap.getAllResults();
        return new ArrayList<>(results);
    }

    private void sort(List<ITestResult> list){
        Collections.sort(list, new Comparator<ITestResult>() {
            @Override
            public int compare(ITestResult r1, ITestResult r2) {
                if(r1.getStartMillis()>r2.getStartMillis()){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
    }

    /**
     * 调用ATDBack saveResult接口，把测试结果信息插入数据库
     * @param list 测试结果信息
     */
    private void saveResult(List<ITestResult> list) {

        if (Parameters.TESTCASE_EXCEL_NAME.isEmpty()){
            // 如果为空说明是本地调试，不保存结果。
            logger.info("testcaseName is empty. Do not insert into db.");
            return;
        }

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        String batchNo = date; // batchNo
        Result result = new Result();
        ResultData resultData = new ResultData();
        resultData.setCaseType("1");
        resultData.setProductName(Parameters.PRODUCT_NAME);
        resultData.setProjectName(Parameters.PROJECT_NAME);
        resultData.setModuleName(Parameters.MODULE_NAME);
        resultData.setTestcaseName(Parameters.TESTCASE_EXCEL_NAME);
        resultData.setBatchNo(batchNo);
        List<ResultDataItemList> resultDataItemLists = new ArrayList();

        /*
{
	"data":{
		"caseType":"1",
		"productName":"XXD",
		"batchNo":"2017-05-12",
		"itemList":
			[
				{
					"projectName":"TIANDUN",
					"moduleName":"IntegrationPlatform",
					"testcaseName":"IntegrationPlatform_WS",
					"testcaseModuleName":"/user",
					"excelNo":"1",
					"caseInfo":"caseInfo",
					"status":"success",
					"startTime":"2017-05-09 00:00:00",
					"finishTime":"2017-05-09 01:00:00",
					"duration":"12s",
					"description":"测试"
				}
			]
		}
}
        */
        for (ITestResult iTestResult : list) {
            ResultDataItemList resultDataItemList = new ResultDataItemList();

            // String xmlTestClass = iTestResult.getMethod().getTestClass().getName();
            String testcaseModuleName = iTestResult.getMethod().getXmlTest().getName(); // testcaseModuleName
            resultDataItemList.setTestcaseModuleName(testcaseModuleName);
            String methodName = iTestResult.getMethod().getMethodName();
            String str = RegExp.filterString(methodName, "a-zA-Z");
            Integer num = Integer.parseInt(str);
            resultDataItemList.setExcelNo(num.toString());// 用例编号
            String caseInfo = getArguments(iTestResult); // 用例所有信息
            resultDataItemList.setCaseInfo(caseInfo);
            String status = getStatus(iTestResult.getStatus()); // 结果
            resultDataItemList.setStatus(status);
            Long startTime = iTestResult.getStartMillis(); // beginTime
            String startTimeStr = DateFormat.getDate("yyyy-MM-dd HH:mm:ss",startTime);
            resultDataItemList.setStartTime(startTimeStr);
            Long finishTime = iTestResult.getEndMillis();     // endTime
            String finishTimeStr = DateFormat.getDate("yyyy-MM-dd HH:mm:ss",finishTime);
            resultDataItemList.setFinishTime(finishTimeStr);
            String duration = formatDuration(startTime,finishTime) + "s"; // 计算执行时间
            resultDataItemList.setDuration(duration); // 执行耗时
            resultDataItemList.setDescription(getDescription(iTestResult));
//            Float tempLast = (float) (iTestResult.getEndMillis()-iTestResult.getStartMillis());
//            Float lastTime = tempLast/1000; // 执行耗时

//            String description = GlobalSettings.description.get(xmlTestClass+"."+methodName); // 描述
            resultDataItemLists.add(resultDataItemList);
        }
        resultData.setItemList(resultDataItemLists);
        result.setData(resultData);


        String json = JSONFormat.getObjectToJson(result);
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        try {
            ResponseInfo responseInfo = httpClientUtil.executePostWithHeaders("http://"+ATDBackService+"/testcase/saveResult",header,json);
//            ResponseInfo responseInfo = httpClientUtil.executePostWithHeaders("http://localhost:8080/testcase/saveResult",header,json);
            logger.info("/testcase/saveResult rsp status:"+responseInfo.getStatus());
            logger.info("/testcase/saveResult rsp content:"+responseInfo.getContent());
            logger.info("/testcase/saveResult rsp time:"+responseInfo.getTime());
        } catch (HTTPException e) {
            logger.info("/testcase/saveResult Save fail."+e.toString());
            e.printStackTrace();
        }
    }

    public String getStatus(int status){
        String statusString = null;
        switch (status) {
            case 1:
                statusString = "SUCCESS";
                break;
            case 2:
                statusString = "FAILURE";
                break;
            case 3:
                statusString = "SKIP";
                break;
            default:
                break;
        }
        return statusString;
    }

}
