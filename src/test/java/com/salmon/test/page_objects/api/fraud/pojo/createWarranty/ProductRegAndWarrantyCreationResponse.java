package com.salmon.test.page_objects.api.fraud.pojo.createWarranty;

import java.util.List;

public class ProductRegAndWarrantyCreationResponse
{
    private int status;

    private String message;

    private List<Data> data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Data> getData() {
        return data;
    }
}
