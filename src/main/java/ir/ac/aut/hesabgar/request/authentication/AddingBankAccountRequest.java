package ir.ac.aut.hesabgar.request.authentication;

public class AddingBankAccountRequest {
    private String userId;
    private String bankName;
    private String bankAccountNumber;
    private String atmCardNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getAtmCardNumber() {
        return atmCardNumber;
    }

    public void setAtmCardNumber(String atmCardNumber) {
        this.atmCardNumber = atmCardNumber;
    }
}
