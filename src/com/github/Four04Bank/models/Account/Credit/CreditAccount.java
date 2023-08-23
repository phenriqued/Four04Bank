package com.github.Four04Bank.models.Account.Credit;

import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.models.Tax.Taxes;

import java.math.BigDecimal;

public class CreditAccount extends Account {

    private String numberAccount;
    private BigDecimal limitBalance = new BigDecimal("500");
    private Taxes tax;

    public CreditAccount(String agency, String number, String numberAccount ,Holder holder) {
        super(agency, number, holder);
        if(numberAccount == null || numberAccount.length() != 6) {
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
    public void setLimitBalance(BigDecimal newBalanceLimit){
        if(newBalanceLimit == null || newBalanceLimit.compareTo(limitBalance) <= 0 ){
            throw new AccountException("Erro ao alterar seu limite");
        }
        this.limitBalance = newBalanceLimit;
    }

    @Override
    public void withdraw(BigDecimal money) {
        if(getBalance().compareTo(BigDecimal.ZERO) <= 0 || money.compareTo(getBalance()) > 0){
            System.out.println("Seu saldo é " + getBalance() + ". Operação irá afetar seu Limite!");
        }
        validateBalance();
        BigDecimal fess = tax.tax().multiply(money);
        setBalance(getBalance().subtract(fess));
        setBalance(getBalance().subtract(money));
    }

    @Override
    public void transfer(Account account, BigDecimal money) {
        validateMoney(money);
        if(account == this || account == null){
            throw new AccountException("[ERROR] Conta inválida.");
        }
        withdraw(money);
        account.deposit(money);
    }

    private void validateBalance(){
        if(getBalance().compareTo(limitBalance) <= 0){
            throw new AccountException("[ERROR] Seu Saldo é menor ou igual ao limite de crédito");
        }
    }

}
