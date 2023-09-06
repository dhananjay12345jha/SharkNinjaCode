package com.salmon.test.page_objects.api.fraud.pojo.warrantyRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SearchProdRegisWarrantyResponse {

        private int totalRecords;

        private int totalNumberOfPages;

        private List<ProductWarrantyDTO> productWarrantyDTO;

    private static final Logger log = LoggerFactory.getLogger(SearchProdRegisWarrantyResponse.class);


    public int getTotalRecords() {
        log.info("total Records returned-->"+totalRecords);
        return totalRecords;
    }
 
    public int getTotalNumberOfPages() {
        log.info("returning total number of pages-->"+totalNumberOfPages);
        return totalNumberOfPages;
    }

    public List<ProductWarrantyDTO> getProductWarrantyDTO() {
        log.info("product warranty DTO list size is-->"+productWarrantyDTO.size());
        return productWarrantyDTO;
    }
}
