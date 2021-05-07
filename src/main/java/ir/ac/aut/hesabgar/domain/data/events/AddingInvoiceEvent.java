package ir.ac.aut.hesabgar.domain.data.events;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddingInvoiceEvent {
    private String topic;
    private String description;
    private List<String> eventMembers;
    private String invoiceAdmin;
    private Map<String, Integer> eventBalance;
    private Date creationDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getEventMembers() {
        return eventMembers;
    }

    public void setEventMembers(List<String> eventMembers) {
        this.eventMembers = eventMembers;
    }

    public String getInvoiceAdmin() {
        return invoiceAdmin;
    }

    public void setInvoiceAdmin(String invoiceAdmin) {
        this.invoiceAdmin = invoiceAdmin;
    }

    public Map<String, Integer> getEventBalance() {
        return eventBalance;
    }

    public void setEventBalance(Map<String, Integer> eventBalance) {
        this.eventBalance = eventBalance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
