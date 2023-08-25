package com.github.Four04Bank.models.Account.Savings;

import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.models.Account.Credit.CreditAccount;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.models.Tax.Taxes;

import java.math.BigDecimal;

public class SavingsAccount extends Account {

    private String numberSavingAccount;
    private Taxes tax;


    public SavingsAccount(String agency, Holder holder) {
        super(agency, holder);
        generateNumberSavingAccount();
    }

    public String getNumberSavingAccount() {
        return numberSavingAccount;
    }
    public Taxes getTax() {
        return tax;
    }
    public void setTax(Taxes tax) {
        this.tax = tax;
    }

    private void generateNumberSavingAccount(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("06");
        for (int i = 0; i <= 1; i++){
            int numberAccount = (int)(Math.random() * 10);
            stringBuilder.append(numberAccount);
        }
        numberSavingAccount = stringBuilder.toString();
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
    public void transfer( Account recipientAccount, BigDecimal money) {
        validateMoney(money);
        if(recipientAccount == this || recipientAccount == null){
            throw new AccountException("[ERROR] Conta inválida.");
        }
        if(recipientAccount instanceof CreditAccount){
            BigDecimal fess = tax.tax().multiply(money);
            this.setBalance(getBalance().subtract(fess));
        }
        this.withdraw(money);
        recipientAccount.deposit(money);
    }

    @Override
    public String getCompleteNumberAccount() {
        return getNumber() + "-"+ this.numberSavingAccount;
    }

    @Override
    public String toString() {
        return "Conta Poupança: Agência: "+getAgency()+" - Número da Conta: " + getCompleteNumberAccount() +
                    "\nTitular: \n"+ getHolder()+ ".";
    }
}
