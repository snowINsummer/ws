package qa.webservice.xxd.tiandun.approvingSystem;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.common.Constructor;

import java.io.IOException;
import java.util.Iterator;

public class uipinterface {

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

    @Test(dataProvider = "dp")
    public void case038(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case039(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case040(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case041(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case042(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case043(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case044(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case045(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case046(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case047(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case048(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case049(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case050(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case051(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case052(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case053(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case054(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case055(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case056(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case057(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case058(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case059(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case060(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case061(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case062(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case063(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case064(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case065(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case066(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case067(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case068(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case069(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case070(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case071(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case072(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case073(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case074(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case075(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case076(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case077(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case078(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case079(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case080(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case081(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case082(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case083(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case084(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case085(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case086(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case087(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case088(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case089(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case090(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case091(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case092(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case093(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case094(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case095(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case096(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case097(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case098(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case099(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case100(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case101(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case102(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case103(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case104(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case105(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case106(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case107(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case108(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case109(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case110(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case111(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case112(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case113(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case114(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case115(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case116(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case117(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case118(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case119(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case120(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case121(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case122(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case123(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case124(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case125(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case126(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case127(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case128(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case129(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case130(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case131(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case132(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case133(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case134(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case135(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case136(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case137(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case138(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }

    @Test(dataProvider = "dp")
    public void case139(Object[] arrObj) throws Exception {
        constructor.executeCase(arrObj[0],arrObj[1],arrObj[2],arrObj[3]);
    }


}
