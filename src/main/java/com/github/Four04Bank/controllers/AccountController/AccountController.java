package com.github.Four04Bank.controllers.AccountController;

import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.models.Account.Credit.CreditAccount;
import com.github.Four04Bank.models.Account.Savings.SavingsAccount;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.models.Tax.CreditTax;
import com.github.Four04Bank.models.Tax.SavingsTax;
import com.github.Four04Bank.views.InterfaceViews.AccountUI;

import java.math.BigDecimal;

public class AccountController implements AccountUI {

    @Override
    public Account createSavingAccount(String agency, Holder holder){
        return new SavingsAccount(agency, holder);
    }

    @Override
    public Account createCreditAccount(String agency, Holder holder){
        return new CreditAccount(agency, holder);
    }

    @Override
    public void deposit(Account account, BigDecimal money){
        account.deposit(money);
    }
    @Override
    public void withdraw(Account account, BigDecimal money){
        createTaxes(account);
        account.withdraw(money);
    }
    @Override
    public void ted(Account senderAccount, Account recipientAccount, BigDecimal money){
        createTaxes(senderAccount);
        senderAccount.transfer(recipientAccount, money);
    }

    private void createTaxes(Account account){
        if(account instanceof SavingsAccount){
            account.setTaxes(new SavingsTax());
        }else{
            account.setTaxes(new CreditTax());
        }
    }
}
