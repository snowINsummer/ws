package qa.common;

import org.testng.Assert;
import qa.Entity.CheckList;
import qa.Entity.ResponseCheckList;
import qa.Entity.SqlCheckList;
import qa.exception.DBException;
import qa.exception.RunException;
import qa.utils.DataBaseUtil;
import qa.utils.JSONFormat;
import qa.utils.StringUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangli on 26/4/2017.
 */
@SuppressWarnings("unchecked")
public class AssertResult {

//    private
    private Engine engine;
    private DataBaseUtil dataBaseUtil;
    private CheckList checkList;
    private Map<String, Object> responseBody;

    public AssertResult(Engine engine,DataBaseUtil dataBaseUtil,CheckList checkList,Map<String, Object> responseBody){
        this.engine = engine;
        this.dataBaseUtil = dataBaseUtil;
        this.checkList = checkList;
        this.responseBody = responseBody;
    }

    public void assertAll(){
        boolean flag = true;
        List expect = new ArrayList();
        List actual = new ArrayList();
        if (checkList.getSqlCheckListSize() != 0){
            try {
                dataBaseUtil.conn();
            } catch (DBException e) {
                e.printStackTrace();

            }
            if (null != dataBaseUtil){
                for(SqlCheckList sqlCheckList : checkList.getSqlCheckList()){
                    try {
                        String key = sqlCheckList.getKey();
                        engine.logBold("需要验证字段：" + key);
                        String sql = sqlCheckList.getSql();
                        Object obj = JSONFormat.analyzeJsonExpression(JSONFormat.getObjectToJson(responseBody), key);
                        engine.logBold("实际结果：" + obj);
                        engine.log("sql：" + sql);
                        dataBaseUtil.executeQueryOracle(sql);
                        Map<String, String> sqlData = dataBaseUtil.getFirstData();
                        String[] arr = key.split("\\.");
                        String expectSingle = sqlData.get(arr[arr.length-1].toUpperCase());
                        engine.logBold("预期结果：" + expectSingle);
                        engine.logEmpty();
                        expect.add(StringUtil.isNumericAndTransformDouble(expectSingle));
                        actual.add(StringUtil.isNumericAndTransformDouble(obj));
                    } catch (RunException e) {
                        flag = false;
                        engine.logRed(e.getMessage());
                        continue;
                    } catch (DBException e) {
                        flag = false;
                        engine.logRed(e.getMessage());
                        continue;
                    }
//                    Assert.assertEquals(expect,actualResult);
                }

            }

        }
        try {
            Assert.assertEquals(actual, expect);
        }catch (AssertionError assertionError){
            flag = false;
            engine.logRed(assertionError.getMessage());
        }
        expect = new ArrayList();
        actual = new ArrayList();
        if(checkList.getResponseCheckListSize() != 0){
            for(ResponseCheckList responseCheckList : checkList.getResponseCheckList()){
                try {
                    String key = responseCheckList.getKey();
                    engine.logBold("需要验证字段：" + key);
                    String value = responseCheckList.getValue();
                    Object obj = JSONFormat.analyzeJsonExpression(JSONFormat.getObjectToJson(responseBody), key);
                    String actualResult = String.valueOf(obj);
                    engine.logBold("实际结果：" + actualResult);
                    engine.logBold("预期结果：" + value);
                    engine.logEmpty();
                    actual.add(StringUtil.isNumericAndTransformDouble(actualResult));
                    expect.add(StringUtil.isNumericAndTransformDouble(value));
                } catch (RunException e) {
                    flag = false;
                    engine.logRed(e.getMessage());
                    continue;
                }
            }
        }
        try {
            Assert.assertEquals(actual, expect);
        }catch (AssertionError assertionError){
            flag = false;
            engine.logRed(assertionError.getMessage());
        }finally {
            Assert.assertTrue(flag);
        }
    }


    public void assertCode() {
        String code = String.valueOf(responseBody.get(Parameters.RESPONES_BODY_CODE));
        Assert.assertEquals(Parameters.RESPONES_BODY_CODE_SUCCESS, code ,"请求失败。");
    }

    public void assertSql(Map<String, ?> caseInfo, DataBaseUtil dataBaseUtil) throws DBException, UnsupportedEncodingException {
        dataBaseUtil.conn();
        String sql = caseInfo.get(Parameters.JSON_TEMPLATE_CASEINFO_SQL).toString();
        dataBaseUtil.executeQueryOracle(sql);
        engine.log(JSONFormat.getObjectToJson(dataBaseUtil.getSqlData()));
    }


}
