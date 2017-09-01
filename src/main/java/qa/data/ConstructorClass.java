package qa.data;

/**
 * Created by zhangli on 18/5/2017.
 */

import org.apache.log4j.Logger;
import qa.common.ExcelData;
import qa.common.Parameters;
import qa.utils.FileUtil;
import qa.utils.StringUtil;

import java.io.File;
import java.util.Map;

/**
 * 解析excel自动生成.java类
 */
public class ConstructorClass {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private final static String rootPath = "src/main/java/qa/webservice/";

    public void create(){
        ExcelData excelData = new ExcelData();
        try {
            excelData.openExcelTestCase("testcase/XXD/TIANDUN/approvingSystem/approvingSystem.xlsx");
//            excelData.setExcelPath("testcase/XXD/TIANDUN/IntegrationPlatform/IntegrationPlatform.xlsx");
            excelData.initVar("testcase/XXD/TIANDUN/approvingSystem/approvingSystem.xlsx");
            String classTemplateText = FileUtil.readFile("class-template/class_ApprovingSystem.txt");
            String functionTemplateText = FileUtil.readFile("class-template/function.txt");
            if (classTemplateText.isEmpty()){
                logger.error("classTemplateText is null");
            }else {
                Map<String, Integer> map = excelData.getCaseRowCount();
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    // 生成.java文件
                    String className = entry.getKey();
                    String classText = classTemplateText.replace("${className}", className);
                    Integer caseRowCount = entry.getValue();
                    String projectPath = rootPath + Parameters.PRODUCT_NAME + File.separator + Parameters.PROJECT_NAME;
                    if (!FileUtil.exists(projectPath)){
                        FileUtil.createFloder(projectPath);
                    }
                    String modulePath = projectPath + File.separator + Parameters.MODULE_NAME;
                    if (!FileUtil.exists(modulePath)){
                        FileUtil.createFloder(modulePath);
                    }
                    String createFilePath = modulePath + File.separator + className + ".java";
                    FileUtil.createFile(createFilePath);
                    String functionTemp = functionTemplateText;
                    String allFunctionText = "";
                    for(int i=1;i<=caseRowCount;i++){
                        String no = StringUtil.addZeroToIntStr(i,3);
                        String functionText = functionTemp.replace("${no}", no);
//                        functionText = functionTemplateText;
                        allFunctionText += functionText;
                    }
                    classText = classText.replace("${allTest}",allFunctionText);
                    FileUtil.writeFile(createFilePath, classText);
                    // 生成 json模板文件
                    String jsonRootPath = "json-template/" + Parameters.PRODUCT_NAME + File.separator + Parameters.PROJECT_NAME +
                            File.separator + Parameters.MODULE_NAME + File.separator;
                    FileUtil.copyFile(jsonRootPath + "muban/ws.json",jsonRootPath+className+".json");
                    FileUtil.copyFile(jsonRootPath + "muban/responseBody.json",jsonRootPath+className+"-responseBody.json");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args){
        new ConstructorClass().create();
    }



}
