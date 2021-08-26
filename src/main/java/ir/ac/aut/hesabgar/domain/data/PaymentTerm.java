package ir.ac.aut.hesabgar.domain.data;

public class PaymentTerm {
    private String user;
    private float amount;

    public PaymentTerm(String user, float amount) {
        this.user = user;
        this.amount = amount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
