package qa.webservice.xxd.tiandun.integrationPlatform.forms;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.common.Constructor;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by zhangli on 18/4/2017.
 */
public class forms_GET {

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
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2]);
    }

//    @Test(dataProvider = "dp")
    public void case002() throws Exception {
        constructor.executeCase();
    }
}
