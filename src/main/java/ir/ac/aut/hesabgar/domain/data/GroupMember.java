package ir.ac.aut.hesabgar.domain.data;

import java.util.List;

public class GroupMember {
    private String userId;
    private boolean isInvoiceAdmin;
    private List<PaymentTerm> paymentTerms;

    public List<PaymentTerm> getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(List<PaymentTerm> paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isInvoiceAdmin() {
        return isInvoiceAdmin;
    }

    public void setInvoiceAdmin(boolean invoiceAdmin) {
        isInvoiceAdmin = invoiceAdmin;
    }

}
