package com.salmon.test.page_objects.api.fraud.pojo.warrantyRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductWarrantyDTO {

    private String brand;
    private String regId;
    private String regCountry;
    private String recordSource;
    private String purchaseSourceName;
    private String orderNo;
    private String customerId;
    private String sku;
    private String kitsku;
    private String factoryCode;
    private String productionCode;
    private String smartSerialNumber;
    private String regStatus;
    private String warrantyType;
    private long warrantyEffectiveDate;
    private int warrantyDuration;
    private long warrantyEndDate;
    private int createdDate;
    private int lastModifiedDate;
    private String email;
    private String firstName;
    private String lastName;
    private String customerLocale;
    private String productName;
    private String postcode;
    private boolean offers;
    private boolean tips;
    private boolean competitions;
    private String oldSystemId;
    private String productUrl;
    private String imageUrl;
    private List<String > partWarrantyRegId;
    private String replaceRegId;
    private String warrantyPartTypeCode;
    private int warrantyPartInMonth;
    private String warrantyPartName;
    public String getReplaceRegId() {
        return replaceRegId;
    }
    private String warrantyUserManualURL;
    private String warrantyInfoURL;
    private String warrantyFAQURL;

    private static final Logger log = LoggerFactory.getLogger(ProductWarrantyDTO.class);

    public String getBrand() {
        return brand;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<String> getPartWarrantyRegId() {
        return partWarrantyRegId;
    }

    public String getRegId() {
        log.info("Returning  regId from response-->" + regId);
        return regId;
    }

    public String getRegCountry() {
        log.info("Returning regCountry from response-->" + regCountry);
        return regCountry;
    }

    public String getRecordSource() {
        log.info("Returning record Source from response-->" + recordSource);
        return recordSource;
    }

    public String getPurchaseSourceName() {
        log.info("Returning purchase Source Name-->" + purchaseSourceName);
        return purchaseSourceName;
    }

    public String getOrderNo() {
        log.info("Returning order No from response-->" + orderNo);
        return orderNo;
    }

    public String getCustomerId() {
        log.info("Returning customerId from response-->" + customerId);
        return customerId;
    }

    public String getSku() {
        log.info("Returning sku-->" + sku);
        return sku;
    }

    public String getKitsku() {
        log.info("Returning kitsku -->" + kitsku);
        return kitsku;
    }

    public String getFactoryCode() {
        log.info("Returning factoryCode-->" + factoryCode);
        return factoryCode;
    }

    public String getProductionCode() {
        log.info("Returning productionCode -->" + productionCode);
        return productionCode;
    }

    public String getSmartSerialNumber() {
        log.info("Returning smartSerialNumber -->" + smartSerialNumber);
        return smartSerialNumber;
    }

    public String getRegStatus() {
        log.info("Returning regStatus -->" + regStatus);
        return regStatus;
    }

    public String getWarrantyType() {
        log.info("Returning warrantyType -->" + warrantyType);
        return warrantyType;
    }

    public long getWarrantyEffectiveDate() {
        log.info("Returning warrantyEffectiveDate-->" + warrantyEffectiveDate);
        return warrantyEffectiveDate;
    }


    public int getWarrantyDuration() {
        log.info("Returning warrantyDuration-->" + warrantyDuration);
        return warrantyDuration;
    }

    public long getWarrantyEndDate() {
        log.info("Returning warrantyEndDate-->" + warrantyEndDate);
        return warrantyEndDate;
    }

    public int getCreatedDate() {
        log.info("Returning createdDate-->" + createdDate);
        return createdDate;
    }

    public int getLastModifiedDate() {
        log.info("Returning lastModifiedDate-->" + lastModifiedDate);
        return lastModifiedDate;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCustomerLocale() {
        return customerLocale;
    }

    public String getProductName() {
        return productName;
    }

    public String getPostcode() {
        return postcode;
    }

    public boolean isOffers() {
        return offers;
    }

    public boolean isTips() {
        return tips;
    }

    public boolean isCompetitions() {
        return competitions;
    }

    public String getOldSystemId() {
        return oldSystemId;
    }

    public String getWarrantyPartTypeCode() {
        return warrantyPartTypeCode;
    }

    public int getWarrantyPartInMonth() {
        return warrantyPartInMonth;
    }

    public String getWarrantyPartName() {
        return warrantyPartName;
    }

    public String getWarrantyUserManualURL() {
        return warrantyUserManualURL;
    }

    public String getWarrantyInfoURL() {
        return warrantyInfoURL;
    }

    public String getWarrantyFAQURL() {
        return warrantyFAQURL;
    }
}