package qa.webservice.xxd.tiandun.integrationPlatform.forms;

import org.testng.Assert;
import org.testng.annotations.*;
import qa.Entity.ResponseCheckList;

/**
 * Created by zhangli on 3/5/2017.
 */
public class test {

    private String caseName = "test001";

    @BeforeClass
    public void init() throws Exception {
        System.out.println("BeforeClass");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("AfterMethod");
    }

    @AfterClass
    public void termination(){
        System.out.println("AfterClass");
    }

    @Test
    public void case001() throws Exception {

        System.out.println(caseName);
        Assert.assertEquals(12,1,"描述");
//        Assert.assertTrue(false);
    }





    @Test
    public void case002() throws Exception {

        ResponseCheckList responseCheckListF = new ResponseCheckList();
        responseCheckListF.setKey("a");
        responseCheckListF.setValue("1");
        ResponseCheckList responseCheckListT = new ResponseCheckList();
        responseCheckListT.setKey("a");
        responseCheckListT.setValue("12");

        ResponseCheckList responseCheckListD = responseCheckListF;

        Assert.assertEquals(responseCheckListF,responseCheckListT);

    }
}
