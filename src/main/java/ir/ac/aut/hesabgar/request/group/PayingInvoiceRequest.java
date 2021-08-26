package ir.ac.aut.hesabgar.request.group;

public class PayingInvoiceRequest {
    private String groupId;
    private String debtorUserId;
    private String creditorUserId;
    private float debtAmount;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDebtorUserId() {
        return debtorUserId;
    }

    public void setDebtorUserId(String debtorUserId) {
        this.debtorUserId = debtorUserId;
    }

    public String getCreditorUserId() {
        return creditorUserId;
    }

    public void setCreditorUserId(String creditorUserId) {
        this.creditorUserId = creditorUserId;
    }

    public float getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(Float debtAmount) {
        this.debtAmount = debtAmount;
    }
}
