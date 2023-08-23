package com.github.Four04Bank.models.Account.Savings;

import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.models.Account.Credit.CreditAccount;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.models.Tax.Taxes;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    private String numberAccount;
    private Taxes tax;


    public SavingsAccount(String agency, String number, String numberAccount, Holder holder) {
        super(agency, number, holder);
        if(numberAccount == null || numberAccount.length() != 4) {
            throw new AccountException("[ERROR] Dados Inválidos!");
        }
        this.numberAccount = numberAccount;
    }

    public String getNumberAccount() {
        return numberAccount;
    }
    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public Taxes getTax() {
        return tax;
    }
    public void setTax(Taxes tax) {
        this.tax = tax;
    }

    @Override
    public void withdraw(BigDecimal money) {
        if(getBalance().compareTo(BigDecimal.ZERO) <= 0 || money.compareTo(getBalance()) > 0){
            throw new AccountException("[ERROR] Impossível efetuar a operação. Consulte seu Saldo!");
        }
        validateMoney(money);
        setBalance(getBalance().subtract(money));
    }

    @Override
    public void transfer(Account account, BigDecimal money) {
        validateMoney(money);
        if(account == this || account == null){
            throw new AccountException("[ERROR] Conta inválida.");
        }
        if(account instanceof CreditAccount){
            BigDecimal fess = tax.tax().multiply(money);
            setBalance(getBalance().subtract(fess));
        }
        withdraw(money);
        account.deposit(money);
    }



}
