package transfer;

public class Account {
    private int amount;

    public Account(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void transferFrom(int amount) {
        this.amount -= amount;
    }
    public void transferTo(int amount) {
        this.amount += amount;
    }
}
