package com.shopping.cart;

/**
 *
 * @author Alpesh
 */
public class CustomterBill {

    // Final help for adding purchase detail
    public static final String HELP = "Please pass the purchase detail as :  customerName CustomerType purchaseAmount";

    private String customerName;
    private String customerType;
    private Long purchaseAmount;
    private Long billAmount;

    /**
     *
     * @param customerPurchaseDetail in format of ( customerName CustomerType
     * purchaseAmount)
     */
    public CustomterBill(String customerPurchaseDetail) {

        String[] customerDetails = customerPurchaseDetail.split("[ ]");
        if (customerDetails.length == 3) {
            this.customerName = customerDetails[0];
            this.customerType = customerDetails[1];
            this.purchaseAmount = Long.parseLong(customerDetails[2]);
        } else {
            throw new IllegalArgumentException(HELP);
        }
    }

    public CustomterBill(String customerName, String customerType, Long purchaseAmount) {
        this.customerName = customerName;
        this.customerType = customerType;
        this.purchaseAmount = purchaseAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Long getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Long purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Long getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Long billAmount) {
        this.billAmount = billAmount;
    }

    @Override
    public String toString() {
        return "CustomterBill{" + "customerName=" + customerName + ", customerType=" + customerType + ", purchaseAmount=" + purchaseAmount + '}';
    }

}
