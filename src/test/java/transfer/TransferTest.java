package transfer;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TransferTest {

    public static void main(String[] args) {
        TransferTest transferTest = new TransferTest();
        transferTest.transferTest();
    }

    public void transferTest() {
        System.out.println("Start");
        List<Account> accountList = Arrays.asList(
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000),
                new Account(1000));
        Bank bank = new Bank(accountList);
        Transfer transfer = new Transfer(bank);

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<Run> runs = Arrays.asList(
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer),
                new Run(transfer)
        );

        for (Run run : runs) {
            executorService.execute(run);
        }
        executorService.shutdown();
    }

    static class Run implements Runnable {
        private final Transfer transfer;

        public Run(Transfer transfer) {
            this.transfer = transfer;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Random random = new Random();
                int first = random.nextInt(10);
                int second;
                do {
                    second = random.nextInt(10);

                } while (second == first);

                int amount = random.nextInt(1000);
                transfer.transfer(String.valueOf(first), String.valueOf(second), amount);
            }
        }
    }
}