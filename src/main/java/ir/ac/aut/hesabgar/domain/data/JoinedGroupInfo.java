package ir.ac.aut.hesabgar.domain.data;

public class JoinedGroupInfo {
    private String groupId;
    private String groupName;
    private boolean isGroupAdmin;
    private boolean isInvoiceAdmin;

    public JoinedGroupInfo() {
    }

    public JoinedGroupInfo(String groupId, String groupName, boolean isGroupAdmin, boolean isInvoiceAdmin) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.isGroupAdmin = isGroupAdmin;
        this.isInvoiceAdmin = isInvoiceAdmin;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isGroupAdmin() {
        return isGroupAdmin;
    }

    public void setGroupAdmin(boolean groupAdmin) {
        isGroupAdmin = groupAdmin;
    }

    public boolean isInvoiceAdmin() {
        return isInvoiceAdmin;
    }

    public void setInvoiceAdmin(boolean invoiceAdmin) {
        isInvoiceAdmin = invoiceAdmin;
    }
}
