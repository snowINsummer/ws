package qa.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangli on 27/4/2017.
 */
public class CheckList {

    private List<SqlCheckList> sqlCheckList = new ArrayList<>();
    private List<ResponseCheckList> responseCheckList = new ArrayList<>();

    public List<SqlCheckList> getSqlCheckList() {
        return sqlCheckList;
    }

    public List<ResponseCheckList> getResponseCheckList() {
        return responseCheckList;
    }

    public void addSqlCheckList(String key, String value){
        this.sqlCheckList.add(new SqlCheckList(key, value));
    }

    public void addRequestList(String key, String value){
        this.responseCheckList.add(new ResponseCheckList(key, value));
    }

    public void addSqlCheckList(SqlCheckList sqlCheckList){
        this.sqlCheckList.add(sqlCheckList);
    }

    public void addRequestList(ResponseCheckList responseCheckList){
        this.responseCheckList.add(responseCheckList);
    }

    public int getSqlCheckListSize(){
        return this.sqlCheckList.size();
    }

    public int getResponseCheckListSize(){
        return this.responseCheckList.size();
    }
}
