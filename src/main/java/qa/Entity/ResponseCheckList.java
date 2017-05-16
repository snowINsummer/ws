package qa.Entity;

/**
 * Created by zhangli on 27/4/2017.
 */
public class ResponseCheckList {

    private String key;
    private String value;

    public ResponseCheckList(){}

    public ResponseCheckList(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResponseCheckList){
            return key.equals(((ResponseCheckList) obj).getKey()) &&
                    value.equals(((ResponseCheckList) obj).getValue());
        }
        return false;
    }
}
