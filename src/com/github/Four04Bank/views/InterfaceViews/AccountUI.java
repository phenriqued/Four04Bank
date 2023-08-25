package com.github.Four04Bank.views.InterfaceViews;

import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.models.Holders.Holder;

import java.math.BigDecimal;

public interface AccountUI {

    Account createSavingAccount(String agency, Holder holder);
    Account createCreditAccount(String agency, Holder holder);

    void deposit(Account account, BigDecimal money);
    void withdraw(Account account, BigDecimal money);
    void ted(Account senderAccount, Account recipientAccount, BigDecimal money);


}
