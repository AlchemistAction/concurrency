package transfer;

public class Transfer {
    private final Bank bank;
    private static final Object tieLock = new Object();

    public Transfer(Bank bank) {
        this.bank = bank;
    }

    public void transfer(String from, String to, int amount) {
        String fr = from.intern();
        String too = to.intern();

        int fromHash = System.identityHashCode(fr);
        int toHash = System.identityHashCode(too);

        if (fromHash < toHash) {
            synchronizedTransfer(from, to, amount, fr, too);
        } else if (fromHash > toHash) {
            synchronizedTransfer(to, from, amount, too, fr);
        } else {
            synchronized (tieLock) {
                synchronizedTransfer(from, to, amount, fr, too);
            }
        }
    }

    private void synchronizedTransfer(String from, String to, int amount, String fr, String too) {
        synchronized (fr) {
            System.out.println("thread " + Thread.currentThread().getId() + " lock " + from);
            synchronized (too) {
                System.out.println("thread " + Thread.currentThread().getId() + " lock " + from + "_" + to);
                if (bank.getAccount(from).getAmount() < amount) {
                    System.out.println("Abort!");
                } else {
                    System.out.println("thread " + Thread.currentThread().getId() + " From: " + from + " _ " + bank.getAccount(from).getAmount() + ", " +
                            "To: " + to + " _ " + bank.getAccount(to).getAmount() + ", Amount: " + amount);
                    bank.getAccount(from).transferFrom(amount);
                    bank.getAccount(to).transferTo(amount);
                }
                System.out.println("thread " + Thread.currentThread().getId() + " transfer end");
            }
        }
    }
}
