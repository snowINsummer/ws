package qa.common;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import qa.utils.FileUtil;
import qa.utils.JSONFormat;
import qa.utils.RegExp;
import qa.utils.StringUtil;

import java.io.*;
import java.util.*;

/**
 * Created by zhangli on 19/4/2017.
 */

@SuppressWarnings("unchecked")
public class ExcelDataTest {

    public static boolean isSameFile = false;

    private String xlFilePath = "testcase/";

    private String testcaseKey = "id";

    private int startRow = 3;

    public static String excelPath = "";

    private String jsonTemplateFilePath = "";

    private List<String> headerKeys = new ArrayList<>();
    private Set<String> bodykeys = Collections.synchronizedSet(new LinkedHashSet<String>());
    private Set<String> wsInfoKeys = Collections.synchronizedSet(new LinkedHashSet<String>());
    private Set<String> caseInfoKeys = Collections.synchronizedSet(new LinkedHashSet<String>());

    private Workbook excelBook = null;

    private LinkedList<Map<String,String>> caseData = new LinkedList<>();

    private Map<String,String> webserviceInfo;

    private LinkedList<Map<String,?>> caseInfo = new LinkedList<>();

    private LinkedList<String> requestBody = new LinkedList<>();

    private Logger logger = Logger.getLogger(this.getClass().getName());



    public ExcelDataTest(){}

    public ExcelDataTest(String path, String classFullPath, String functionName) throws Exception {

        String excelFile = xlFilePath + path;
        String className = classFullPath;
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

//            setWebserviceInfoData(className);
//            setTestcaseData(className);
        }
    }

    private void getExcelData(String className, String classFullPath) throws Exception {
        initVar();
        setJsonTemplateFilePath(classFullPath);
//        setJsonTemplateKeys();
        openExcelTestCase(this.excelPath);

        setJsonTemplateData(className);
//        setWebserviceInfoData(className);
//        setTestcaseData(className);
    }

    private void setJsonTemplateData(String sheetName) throws Exception {
        if(excelBook != null) {
            Sheet apiSheet = excelBook.getSheet(sheetName);
            if (null != apiSheet){
                String jsonTemplate = FileUtil.readFile(jsonTemplateFilePath);
                int i=0;
                while (true){
                    String key = getValue(apiSheet, 0, i);
                    if (null == key){
                        break;
                    }else {
                        String value = getValue(apiSheet, 1, i++);
                        if (null == value){
                            value = "";
                        }
                        jsonTemplate = jsonTemplate.replace("${"+key+"}",value);
                    }
                }
                int apiSheetRows = apiSheet.getPhysicalNumberOfRows(); //获取物理行数
                for(int j=startRow;j<apiSheetRows;j++){
                    String singleJson = jsonTemplate;
                    i = 0;
                    while (true){
                        String key = getValue(apiSheet, startRow - 1, i);
                        if (null == key){
                            break;
                        }else {
                            String value = getValue(apiSheet, j, i++);
                            if (null == value){
                                value = "";
                            }
                            singleJson = singleJson.replace("${"+key+"}",value);
                        }
                    }
                    String[] arr = singleJson.split("////");
                    if (arr.length > 1){
                        arr[1] = RegExp.filterString(arr[1],"\r\n");
                        requestBody.add(arr[1]);
                    }else {
                        requestBody.add("");
                    }
                    Map map = JSONFormat.getMapFromJson(arr[0]);
                    caseInfo.add(map);
                }
//                webserviceInfo.put(iterator.next(),getSql(apiSheet, 1, i++));
            }else {
                throw new Exception("没有找到"+sheetName+" sheet");
            }
        }else {
            throw new Exception("文件读取失败。");
        }
    }

    /**
     * 按照json-template文件，定义headers、body所有key
     */
    private void setJsonTemplateKeys() {
        String jsonTemplate = FileUtil.readFile(jsonTemplateFilePath);
        Map<String,?> map = JSONFormat.getMapFromJson(jsonTemplate);
        Map<String,?> headers = (Map<String, ?>) map.get(Parameters.JSON_TEMPLATE_HEADERS);
        for(String keys : headers.keySet()){
            headerKeys.add(keys);
        }
        Map<String,?> body = (Map<String, ?>) map.get(Parameters.JSON_TEMPLATE_BODY);
        setAllKeys(bodykeys,body,Parameters.JSON_MAP_TYPE);

        Map<String,?> wsInfo = (Map<String, ?>) map.get(Parameters.JSON_TEMPLATE_WSINFO);
        setAllKeys(wsInfoKeys, wsInfo, Parameters.JSON_MAP_TYPE);

        Map<String,?> caseInfo = (Map<String, ?>) map.get(Parameters.JSON_TEMPLATE_CASEINFO);
        setAllKeys(caseInfoKeys, caseInfo, Parameters.JSON_MAP_TYPE);

    }

    /**
     * 定义body所有key
     * @param bodykeys
     * @param jsonTemp
     * @param jsonType json类型 0：map ，1：list
     */
    private void setAllKeys(Set<String> bodykeys, Object jsonTemp, int jsonType) {
        if (jsonType == Parameters.JSON_MAP_TYPE){
            Map<String,?> map = (Map<String, ?>) jsonTemp;
            for(Map.Entry entry : map.entrySet()){
                if (entry.getValue().toString().startsWith("{")){
                    setAllKeys(bodykeys, entry.getValue(),0);
                }else if(entry.getValue().toString().startsWith("[")){
                    setAllKeys(bodykeys, entry.getValue(),1);
                }else {
                    bodykeys.add(entry.getKey().toString());
                }
            }
        }else {
            List list = (List) jsonTemp;
            for(Object obj : list){
                if (obj.toString().startsWith("{")){
                    setAllKeys(bodykeys, obj,0);
                }else if(obj.toString().startsWith("[")){
                    setAllKeys(bodykeys, obj,1);
                }else {
                    String str = obj.toString();
                    if (str.contains("$$")){
                        str = str.replace("$$","");
                    } else if (str.contains("$")){
                        str = str.replace("$","");
                    }
                    bodykeys.add(str);
                }
            }
        }

    }


    /**
     * 根据class路径获取对应的json-template文件路径
     * @param className
     */
    private void setJsonTemplateFilePath(String className) {
        String[] arrClass = className.split("\\.");
        jsonTemplateFilePath = "./json-template" + File.separator +
                Parameters.PRODUCT_NAME + File.separator +
                Parameters.PROJECT_NAME + File.separator +
                Parameters.MODULE_NAME + File.separator +
                arrClass[arrClass.length-2] + File.separator +
                arrClass[arrClass.length-1] + ".json";
    }


    private void initVar() {
        String fileName = FileUtil.getName(this.excelPath);
        Parameters.TESTCASE_EXCEL_NAME = fileName.split("\\.")[0];
        String tempPath = FileUtil.getParent(this.excelPath);
        Parameters.MODULE_NAME = FileUtil.getName(tempPath);
        tempPath = FileUtil.getParent(tempPath);
        Parameters.PROJECT_NAME = FileUtil.getName(tempPath);
        tempPath = FileUtil.getParent(tempPath);
        Parameters.PRODUCT_NAME = FileUtil.getName(tempPath);
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
     * 根据模板字段，按照顺序，把excel中的数据替换到json模板中
     * @param sheetName
     * @throws Exception
     */
    private void setWebserviceInfoData(String sheetName) throws Exception {
        if(excelBook != null) {
            Sheet apiSheet = excelBook.getSheet(sheetName);
            if (null != apiSheet){
//                Map<Integer, String> sheetKeys = ClassUtil.getClassFieldsName(WebserviceType.class.getName());
//                int apiSheetRows = apiSheet.getPhysicalNumberOfRows(); //获取物理行数

                webserviceInfo = new HashMap<>();
//                for(Integer colNum:sheetKeys.keySet()) {
//                    webserviceInfo.put(sheetKeys.get(colNum), getSql(apiSheet, 1, colNum));
//                }
                int i=0;
                Iterator<String> iterator = wsInfoKeys.iterator();
                while (iterator.hasNext()){
                    webserviceInfo.put(iterator.next(),getValue(apiSheet, 1, i++));
                }
                iterator = headerKeys.iterator();
                while (iterator.hasNext()){
                    webserviceInfo.put(iterator.next(),getValue(apiSheet, 1, i++));
                }
            }else {
                throw new Exception("没有找到"+sheetName+" sheet");
            }
        }else {
            throw new Exception("文件读取失败。");
        }
    }

    /**
     * 根据模板字段，按照顺序，把excel中的数据替换到json模板中
     * 将sheetl中的测试数据读取至map
     * @param sheetName
     * @throws Exception
     */
    private void setTestcaseData(String sheetName) throws Exception {
        if(excelBook != null) {
            Sheet apiSheet = excelBook.getSheet(sheetName);
            if (null != apiSheet){
//                Map<Integer, String> sheetKeys = ClassUtil.getClassFieldsName(CaseType.class.getName());
                int apiSheetRows = apiSheet.getPhysicalNumberOfRows(); //获取物理行数
                for(int j=startRow;j<apiSheetRows;j++){
                    Map<String,String> map = Collections.synchronizedMap(new LinkedHashMap<String,String>());
//                    for(Integer colNum:sheetKeys.keySet()) {
//                        map.put(sheetKeys.get(colNum), getSql(apiSheet, j, colNum));
//                    }
                    int colmun=0;
                    map.put(Parameters.JSON_TEMPLATE_ID,getValue(apiSheet, j, colmun++));
                    Iterator<String> iterator = bodykeys.iterator();
                    while (iterator.hasNext()){
                        map.put(iterator.next(), getValue(apiSheet, j, colmun++));
                    }
                    iterator = caseInfoKeys.iterator();
                    while (iterator.hasNext()){
                        map.put(iterator.next(), getValue(apiSheet, j, colmun++));
                    }
                    caseData.add(map);
                    map = null;
                }
            }else {
                throw new Exception("没有找到"+sheetName+" sheet");
            }
        }else {
            throw new Exception("文件读取失败。");
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

    public LinkedList<Map<String,?>> getJSON() {
        String jsonTemplate = FileUtil.readFile(jsonTemplateFilePath);
        LinkedList<Map<String,?>> listJson = new LinkedList<>();
        for(Map<String,String> map : caseData){
            String json = jsonTemplate;
            for(Map.Entry<String, String> entry : map.entrySet()){
                json = json.replace("${"+entry.getKey()+"}",String.valueOf(entry.getValue()));
            }
            for(Map.Entry<String, String> entry : webserviceInfo.entrySet()){
                json = json.replace("${"+entry.getKey()+"}",String.valueOf(entry.getValue()));
            }
            Map<String,?> mapTemp = JSONFormat.getMapFromJson(json);
            // 是否处理没有被替换掉的变量
            listJson.add(mapTemp);
        }

        return listJson;
    }

    public LinkedList getCaseData() {
        return caseData;
    }

    public void setCaseData(LinkedList<Map<String, String>> caseData) {
        this.caseData = caseData;
    }

    public Map<String, String> getWebserviceInfo() {
        return webserviceInfo;
    }

    public void setWebserviceInfo(Map<String, String> webserviceInfo) {
        this.webserviceInfo = webserviceInfo;
    }

    public LinkedList<Map<String, ?>> getCaseInfo() {
        return caseInfo;
    }

    public LinkedList<String> getRequestBody() {
        return requestBody;
    }
}
