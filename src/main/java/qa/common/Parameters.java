package qa.common;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by zhangli on 18/4/2017.
 */
public class Parameters {

    public static Properties prop = getProperties();

    public static String excelPath = prop.getProperty("excelPath");
    public static String environment = prop.getProperty("environment");
    public static String oracleUrl = prop.getProperty("oracleUrl");
    public static String oracleUserName = prop.getProperty("oracleUserName");
    public static String oraclePassword = prop.getProperty("oraclePassword");

    public static String PRODUCT_NAME = "";
    public static String PROJECT_NAME = "";
    public static String MODULE_NAME = "";
    public static String TESTCASE_EXCEL_NAME = "";
    public static final String JSON_TEMPLATE_HEADERS = "headers";
    public static final String JSON_TEMPLATE_HEADERS_REQUEST_TYPE = "Request-Tyoe";
    public static final String JSON_TEMPLATE_BODY = "body";
    public static final String JSON_TEMPLATE_DESCRIPTION = "description";
    public static final String JSON_TEMPLATE_BODY_DATA = "data";
    public static final String JSON_TEMPLATE_ID = "id";
    public static final String JSON_TEMPLATE_CASEINFO = "caseInfo";
    public static final String JSON_TEMPLATE_CASEINFO_SQL = "sql";
    public static final String JSON_TEMPLATE_WSINFO = "wsInfo";
    public static final String JSON_TEMPLATE_WSINFO_URL = "url";
    public static final String JSON_TEMPLATE_WSINFO_ENVIRONMENT = "environment";
    public static int JSON_MAP_TYPE = 0;
    public static int JSON_LIST_TYPE = 1;

    public static final String JSON_TEMPLATE_HEADERS_S = "s";
    public static final String JSON_TEMPLATE_HEADERS_CONTENT_TYPE = "Content-Type";
    public static final String JSON_TEMPLATE_HEADERS_TOKEN = "token";

    public static final String RESPONES_BODY_CODE = "code";
    public static final String RESPONES_BODY_CODE_SUCCESS = "200000";
    public static final String RESPONES_BODY_CODE_INVALID_SIGN = "200407";

    public static final String RESPONES_BODY_MESSAGE = "message";

    public static final String CHECKLIST_SQL_KEY = "sql_";
    public static final String CHECKLIST_RESPONSE_KEY = "rsp_";

    public static final String REQUEST_HEADERS_CLIENTID = "clientId";
    public static final String REQUEST_HEADERS_CLIENTTIME = "clientTime";

    public static final String Tyrant_Service_IP = prop.getProperty("Tyrant_Service_IP","192.168.33.47/tyrant");;

    public static final String SUITE_XML_FILE_PATH = prop.getProperty("suiteXmlFilePath","");

    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream(Parameters.class.getResource("/prop.properties").getPath());
            prop.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

}
