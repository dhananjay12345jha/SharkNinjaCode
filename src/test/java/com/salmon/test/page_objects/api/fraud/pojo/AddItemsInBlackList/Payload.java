package com.salmon.test.page_objects.api.fraud.pojo.AddItemsInBlackList;

import java.util.List;

public class Payload {
    private List<AddressList> addressList;

    private String valueType;

    private List<String> valuesList;

    public List<AddressList> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressList> addressList) {
        this.addressList = addressList;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public List<String> getValuesList() {
        return valuesList;
    }

    public void setValuesList(List<String> valuesList) {
        this.valuesList = valuesList;
    }
}
