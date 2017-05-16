package qa.webservice.xxd.tiandun.integrationPlatform.forms;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import qa.common.Constructor;

/**
 * Created by zhangli on 18/4/2017.
 */
public class applyCode {

    Constructor constructor;

    @BeforeClass
    public void init() throws Exception {
        constructor = new Constructor(this.getClass().getName());
    }

    @AfterClass
    public void termination(){
        constructor.termination();
    }

    @Test
    public void case001() throws Exception {
        constructor.executeCase();
    }
}
