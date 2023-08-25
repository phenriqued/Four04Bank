package com.github.Four04Bank.controllers.AccountController;

import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.models.Account.Savings.SavingsAccount;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.views.InterfaceViews.AccountUI;

import java.math.BigDecimal;

public class AccountController implements AccountUI {

    @Override
    public Account createSavingAccount(String agency, Holder holder){
        return new SavingsAccount(agency, holder);
    }

    @Override
    public Account createCreditAccount(String agency, Holder holder){
        return new SavingsAccount(agency, holder);
    }

    @Override
    public void deposit(Account account, BigDecimal money){
        account.deposit(money);
    }
    @Override
    public void withdraw(Account account, BigDecimal money){
        account.withdraw(money);
    }
    @Override
    public void ted(Account senderAccount, Account recipientAccount, BigDecimal money){
        senderAccount.transfer(recipientAccount, money);
    }


}
