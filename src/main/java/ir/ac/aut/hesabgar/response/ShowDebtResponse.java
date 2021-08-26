package ir.ac.aut.hesabgar.response;

public class ShowDebtResponse {
    private boolean debtorInfo;

    public ShowDebtResponse(boolean debtorInfo) {
        this.debtorInfo = debtorInfo;
    }

    public boolean isDebtorInfo() {
        return debtorInfo;
    }

    public void setDebtorInfo(boolean debtorInfo) {
        this.debtorInfo = debtorInfo;
    }
}
