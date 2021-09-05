package ir.ac.aut.hesabgar.domain.data;

import java.util.List;

public class UserReportStatus {
    private int reportCount;
    private List<String> reportDescription;

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public List<String> getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(List<String> reportDescription) {
        this.reportDescription = reportDescription;
    }
}
