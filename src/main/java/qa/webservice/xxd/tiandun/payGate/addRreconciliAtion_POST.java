package qa.webservice.xxd.tiandun.payGate;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.common.Constructor;

import java.io.IOException;
import java.util.Iterator;

public class addRreconciliAtion_POST {

    Constructor constructor;

    @BeforeClass
    public void init() throws Exception {
        constructor = new Constructor(this.getClass().getName());
    }

    @DataProvider(name = "dp")
    public Iterator<Object[]> dataFortestMethod() throws IOException {
        return constructor.getDp();
    }

    @AfterClass
    public void termination(){
        constructor.termination();
    }

    
    @Test(dataProvider = "dp")
    public void case001(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case002(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case003(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case004(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case005(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case006(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case007(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case008(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case009(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case010(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case011(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case012(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case013(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case014(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case015(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case016(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case017(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case018(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case019(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case020(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case021(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case022(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case023(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case024(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case025(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case026(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case027(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case028(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case029(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case030(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case031(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case032(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case033(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case034(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case035(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case036(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case037(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }


}
