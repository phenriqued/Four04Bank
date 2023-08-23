package com.github.Four04Bank.models.Account;

import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Holders.Holder;

import java.math.BigDecimal;

public abstract class Account {

    private String agency;
    private String number;
    private BigDecimal balance;
    private Holder holder;

    public Account(String agency, String number, Holder holder) {
        if(agency == null || number == null || holder == null ||
                agency.length() != 4 || number.length() != 8){
            throw new AccountException("[ERROR] Dados inválidos!");
        }
        this.agency = agency;
        this.number = number;
        this.balance = BigDecimal.ZERO;
        this.holder = holder;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        if(agency.length() != 8 || agency.equalsIgnoreCase(this.agency)){
            throw new AccountException("[ERROR] Dados da Agência inválidos!");
        }
        this.agency = agency;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    protected void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    protected void validateMoney(BigDecimal money){
        if(money == null || money.compareTo(BigDecimal.ZERO) <= 0){
            throw new AccountException("[ERROR] Erro em efetuar a operação!");
        }
    }
    public void deposit(BigDecimal money){
        validateMoney(money);
        setBalance(getBalance().add(money));
    }
    public abstract void withdraw(BigDecimal money);
    public abstract void transfer(Account account, BigDecimal money);

}
