package DataStructure;

public class Bill {
    private long billingId;//شناسه قبض
    private long paymentCode;// شناسه پرداخت
    private String condition;//وضعیت

    public long getBillingId() {
        return billingId;
    }

    public void setBillingId(long billingId) {
        this.billingId = billingId;
    }

    public long getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(long paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
