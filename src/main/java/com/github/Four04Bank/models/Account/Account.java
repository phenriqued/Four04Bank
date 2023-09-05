package com.github.Four04Bank.models.Account;



import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.models.Tax.Taxes;


import java.math.BigDecimal;

public abstract class Account {

    private String agency;
    private String number;
    private BigDecimal balance;
    private Holder holder;
    protected Taxes taxes;

    public Account(String agency, Holder holder) {
        if(agency == null || holder == null || agency.length() != 4){
            throw new AccountException("[ERROR] Dados inválidos!");
        }
        this.agency = agency;
        generateAccountNumber();
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
    public Taxes getTaxes() {
        return taxes;
    }
    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }
    protected void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    private void generateAccountNumber(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++){
            int numberAccount = (int)(Math.random() * 10);
            stringBuilder.append(numberAccount);
        }
        number = stringBuilder.toString();
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
    public abstract void transfer(Account recipientAccount, BigDecimal money);

    protected abstract String getCompleteNumberAccount();

    @Override
    public String toString() {
        return "Account {" +
                "agency='" + agency + '\'' +
                ", number='" + number + '\'' +
                ", holder=" + holder +
                '}';
    }

}
