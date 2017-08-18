package qa.common;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import qa.Entity.CheckList;
import qa.utils.*;

import java.io.*;
import java.util.*;

/**
 * Created by zhangli on 19/4/2017.
 */

@SuppressWarnings("unchecked")
public class ExcelData {

    private String xlFilePath = "testcase/";

    private int startRow = 3;

    private String excelPath = "";

    private String jsonTemplateFilePath = "";

    private String className = "";

    private Workbook excelBook = null;

    private LinkedList<Map<String,Object>> caseInfo = new LinkedList<>();

    private LinkedList<String> requestBody = new LinkedList<>();
    private LinkedList<String> description = new LinkedList<>();
    private Map<String,Object> responseBody = new HashMap<>();
    private LinkedList<CheckList> checkList = new LinkedList<>();

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public ExcelData(){}

    public ExcelData(String path, String classFullPath) throws Exception {

        String excelFile = xlFilePath + path;
        String className = classFullPath;
        this.className = className;
        int dotNum = className.indexOf(".");
        if (dotNum > 0) {
            className = className.substring(className.lastIndexOf(".") + 1,
                    className.length());
        }
        if (!this.excelPath.equals(excelFile)){
            if (FileUtil.exists(excelFile)){
                this.excelPath = excelFile;
                getExcelData(className,classFullPath);
            }else {
                throw new Exception("用例文件不存在。"+excelFile);
            }
        }else {
            setJsonTemplateData(className);
            setResponseBody(classFullPath);
        }
    }

    private void getExcelData(String className, String classFullPath) throws Exception {
        initVar(this.excelPath);
        setJsonTemplateFilePath(classFullPath);
        openExcelTestCase(this.excelPath);

        setJsonTemplateData(className);
        setResponseBody(classFullPath);
    }

    private void setJsonTemplateData(String sheetName) throws Exception {
        if(excelBook != null) {
            Sheet apiSheet = excelBook.getSheet(sheetName);
            if (null != apiSheet){
                String jsonTemplate = FileUtil.readFile(jsonTemplateFilePath);
                int i=0;
                while (true){
                    String key = getValue(apiSheet, 0, i);
                    if (null == key) {
                        break;
//                    }else if(key.equals(Parameters.JSON_TEMPLATE_WSINFO_URL)){
//                        String value = Parameters.environment;
//                        jsonTemplate = jsonTemplate.replace("${"+Parameters.JSON_TEMPLATE_WSINFO_ENVIRONMENT+"}",value);
//                        i++;
                    }else {
                        String value = getValue(apiSheet, 1, i++);
                        if (null == value){
                            value = "";
                        }
                        jsonTemplate = jsonTemplate.replace("${"+key+"}",value);
                    }
                }
                String environment = Parameters.environment;
                jsonTemplate = jsonTemplate.replace("${"+Parameters.JSON_TEMPLATE_WSINFO_ENVIRONMENT+"}",environment);
                int apiSheetRows = apiSheet.getPhysicalNumberOfRows(); //获取物理行数
                for(int j=startRow;j<apiSheetRows;j++){
                    CheckList checkList = new CheckList();
                    String singleJson = jsonTemplate;
                    i = 0;
                    while (true){
                        String key = getValue(apiSheet, startRow - 1, i);
                        if (null == key){
                            break;
                        }else {
                            String target = "${"+key+"}";
                            String value = getValue(apiSheet, j, i++);
                            if (null == value){
                                value = "";
                            }else if(RegExp.findCharacters(value,"^random\\(\\d+\\)$")){
                                String str = RegExp.filterString(value,"random\\(\\)");
                                value = StringUtil.getFixLenthString(Integer.parseInt(str));

                            }else if(RegExp.findCharacters(value,"^length\\(\\d+\\)$")){
                                String str = RegExp.filterString(value,"length\\(\\)");
                                value = StringUtil.createFixLenthString(Integer.parseInt(str));
                            }else if(value.toLowerCase().equals("null")){
                                value = "";
                                if (singleJson.contains(key + "=" + target)){
                                    target = key + "=" + target;
                                }
                            }else if(value.contains("[")){
                                value = StringUtil.urlEncoderUTF8(value);
                            }
                            singleJson = singleJson.replace(target ,value);
                            setCheckList(checkList, key, value);
                        }
                    }
                    // 获取body，请求报文 (?<=body":).*?\}(?=,)
                    ArrayList<String> list = RegExp.matcherCharacters(singleJson, "(?<=body\":).*");
                    if (list.size() > 0){
                        requestBody.add(list.get(0));
                    }else {
                        requestBody.add("");
                    }
//                    singleJson = singleJson.replaceAll("\"body\".*\\}","");

                    Map map = JSONFormat.getMapFromJson(singleJson);
                    description.add(map.get(Parameters.JSON_TEMPLATE_DESCRIPTION).toString());
                    map.remove(Parameters.JSON_TEMPLATE_BODY); // 删除body
                    map.remove(Parameters.JSON_TEMPLATE_DESCRIPTION); // 删除description
                    caseInfo.add(map);
                    this.checkList.add(checkList);
                }
            }else {
                throw new Exception("没有找到"+sheetName+" sheet");
            }
        }else {
            throw new Exception("文件读取失败。");
        }
    }

    /**
     * 记录检查点
     * @param checkList
     * @param key
     * @param value
     */
    private void setCheckList(CheckList checkList, String key, String value) {
        if (!value.isEmpty()){
            if (key.startsWith(Parameters.CHECKLIST_SQL_KEY)){
                key = key.replace(Parameters.CHECKLIST_SQL_KEY,"");
                checkList.addSqlCheckList(key, value);
            }else if(key.startsWith(Parameters.CHECKLIST_RESPONSE_KEY)){
                key = key.replace(Parameters.CHECKLIST_RESPONSE_KEY,"");
                checkList.addRequestList(key, value);
            }
        }
    }

    /**
     * 根据class路径获取对应的json-template文件路径
     * @param classFullPath
     */
    private void setJsonTemplateFilePath(String classFullPath) {
        String[] arrClass = classFullPath.split("\\.");
        jsonTemplateFilePath = "./json-template" + File.separator +
                Parameters.PRODUCT_NAME + File.separator +
                Parameters.PROJECT_NAME + File.separator +
                Parameters.MODULE_NAME + File.separator +
//                arrClass[arrClass.length-2] + File.separator +
                arrClass[arrClass.length-1] + ".json";

    }

    private void setResponseBody(String classFullPath) {
        String[] arrClass = classFullPath.split("\\.");
        String reponseBodyFilePath = "./json-template" + File.separator +
                Parameters.PRODUCT_NAME + File.separator +
                Parameters.PROJECT_NAME + File.separator +
                Parameters.MODULE_NAME + File.separator +
//                arrClass[arrClass.length - 2] + File.separator +
                arrClass[arrClass.length - 1] + "-responseBody.json";
        String text = FileUtil.readFile(reponseBodyFilePath);
        responseBody = JSONFormat.getMapFromJson(text);
    }


    public void initVar(String excelPath) {
        // 根据xml路径解析项目信息，而不是根据excel路径，可能是一对多
//        String fileName = FileUtil.getName(this.excelPath);
//        if (!Parameters.SUITE_XML_FILE_PATH.isEmpty()){
//            excelPath = Parameters.SUITE_XML_FILE_PATH;
//        }
//        if (excelPath.isEmpty()){
//            excelPath = Parameters.SUITE_XML_FILE_PATH;
//        }
        String fileName = FileUtil.getName(excelPath);
        Parameters.TESTCASE_EXCEL_NAME = fileName.split("\\.")[0];
//        String tempPath = FileUtil.getParent(this.excelPath);
        String tempPath = FileUtil.getParent(excelPath);
        Parameters.MODULE_NAME = FileUtil.getName(tempPath);
        tempPath = FileUtil.getParent(tempPath);
        Parameters.PROJECT_NAME = FileUtil.getName(tempPath);
        tempPath = FileUtil.getParent(tempPath);
        Parameters.PRODUCT_NAME = FileUtil.getName(tempPath);
/*
        System.out.println(Parameters.TESTCASE_EXCEL_NAME);
        System.out.println(Parameters.MODULE_NAME);
        System.out.println(Parameters.PROJECT_NAME);
        System.out.println(Parameters.PRODUCT_NAME);
        */
    }



    /**
     * 加载excel中的testCase
     * @param filePath
     */
    public void openExcelTestCase(String filePath) throws Exception {
        File excelFile = FileUtil.getFile(filePath);
        InputStream fileIS = null;
        ByteArrayInputStream byteArrayInputStream;
        try {
            fileIS = new FileInputStream(excelFile);
            byte buf[] = IOUtils.toByteArray(fileIS);
            byteArrayInputStream = new ByteArrayInputStream(buf);
            if(filePath.endsWith(".xls")) {
                excelBook = new HSSFWorkbook(byteArrayInputStream);
            }else if(filePath.endsWith(".xlsx")) {
                excelBook = new XSSFWorkbook(byteArrayInputStream);
            }else {
                throw new Exception("文件格式不正确。");
            }

        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (fileIS != null) {
                try {
                    fileIS.close();
                } catch (IOException e) {
                    throw new Exception("文件流关闭异常："+e.getMessage());
                }
            }
        }
    }


    /**
     * 获取Excel单元格上的数据
     * @param col 列号 从0开始
     * @param row 行号 从0开始
     * @return 全部返回成String类型
     */
    private String getValue(Sheet excelSheet, int row, int col){
        if(excelSheet.getPhysicalNumberOfRows()<=0 || excelSheet.getRow(row)==null){
            return null;
        }else if(excelSheet.getRow(row).getCell(col)==null){
            return null;
        }

        int cellType = excelSheet.getRow(row).getCell(col).getCellType();

        switch(cellType){

            case Cell.CELL_TYPE_STRING:
                return excelSheet.getRow(row).getCell(col).getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return StringUtil.objectToString(excelSheet.getRow(row).getCell(col).getNumericCellValue());
//                return new DecimalFormat("0").format(excelSheet.getRow(row).getCell(col).getNumericCellValue());
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(excelSheet.getRow(row).getCell(col).getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return null;
            case Cell.CELL_TYPE_BLANK:
                return null;
            case Cell.CELL_TYPE_ERROR:
                return null;
            default :
                return null;
        }

    }

    public LinkedList<Map<String, Object>> getCaseInfo() {
        return caseInfo;
    }

    public LinkedList<String> getRequestBody() {
        return requestBody;
    }

    public Map<String,Object> getResponseBody() throws Exception {
        setResponseBody(this.className);
        return responseBody;
    }

    public LinkedList<CheckList> getCheckList() {
        return checkList;
    }

    public LinkedList<String> getDescription() {
        return description;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }

    public Map<String, Integer> getCaseRowCount() {
        Map<String, Integer> map = new HashMap<>();
        if(excelBook != null) {
            int sheetLen = excelBook.getNumberOfSheets();
            for(int i=0;i<sheetLen;i++){
                Sheet apiSheet = excelBook.getSheetAt(i);
                String sheetName = apiSheet.getSheetName();
                int apiSheetRows = apiSheet.getPhysicalNumberOfRows(); //获取物理行数
                int caseRows = apiSheetRows - startRow;
                logger.info("caseRows = "+caseRows);
                map.put(sheetName, caseRows);
            }
        }else {
            logger.error("excelBook = null");
        }
        return map;
    }
}
