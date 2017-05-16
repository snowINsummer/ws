package qa.Entity;

import java.util.List;

/**
 * Created by zhangli on 15/5/2017.
 */
public class ResultData {

    private String caseType;
    private String productName;
    private String batchNo;
    private List<ResultDataItemList> itemList;

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public List<ResultDataItemList> getItemList() {
        return itemList;
    }

    public void setItemList(List<ResultDataItemList> itemList) {
        this.itemList = itemList;
    }
}
