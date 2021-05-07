package ir.ac.aut.hesabgar.domain.data.events;

import java.util.Date;

public class InvoiceAdminInvAndDelEvent {
    private String invoiceAdminUser;
    private boolean added;
    private Date creationDate;

    public InvoiceAdminInvAndDelEvent(String invoiceAdminUser, boolean added, Date creationDate) {
        this.invoiceAdminUser = invoiceAdminUser;
        this.added = added;
        this.creationDate = creationDate;
    }

    public String getInvoiceAdminUser() {
        return invoiceAdminUser;
    }

    public void setInvoiceAdminUser(String invoiceAdminUser) {
        this.invoiceAdminUser = invoiceAdminUser;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
