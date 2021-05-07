package ir.ac.aut.hesabgar.domain.data.events;

import java.util.Date;

public class PayingInvoiceEvent {
    private String Debtor;
    private String Creditor;
    private int debtAmount;
    private Date creationDate;

    public String getDebtor() {
        return Debtor;
    }

    public void setDebtor(String debtor) {
        Debtor = debtor;
    }

    public String getCreditor() {
        return Creditor;
    }

    public void setCreditor(String creditor) {
        Creditor = creditor;
    }

    public int getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(int debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
