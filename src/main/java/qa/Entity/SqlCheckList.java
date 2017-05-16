package qa.Entity;

/**
 * Created by zhangli on 27/4/2017.
 */
public class SqlCheckList {

    private String key;
    private String sql;

    public SqlCheckList(){}

    public SqlCheckList(String key, String value){
        this.key = key;
        this.sql = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
