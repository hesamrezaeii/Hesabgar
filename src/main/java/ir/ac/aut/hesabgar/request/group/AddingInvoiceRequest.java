package ir.ac.aut.hesabgar.request.group;

import java.util.Map;

public class AddingInvoiceRequest {
    private String userId;
    private String groupId;
    private String topic;
    private String description;
    private float totalPaidValue;
    private Map<String, Float> groupShare;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Map<String, Float> getGroupShare() {
        return groupShare;
    }

    public void setGroupShare(Map<String, Float> groupShare) {
        this.groupShare = groupShare;
    }

    public float getTotalPaidValue() {
        return totalPaidValue;
    }

    public void setTotalPaidValue(Float totalPaidValue) {
        this.totalPaidValue = totalPaidValue;
    }
}
