package transfer;

import java.util.List;

public class Bank {
    private final List<Account> accountList;

    public Bank(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Account getAccount(String id) {
        return accountList.get(Integer.parseInt(id));
    }
}
