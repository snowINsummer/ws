package qa.webservice.xxd.tiandun.userCenter;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.common.Constructor;

import java.io.IOException;
import java.util.Iterator;

public class realnameStatus {

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


}
