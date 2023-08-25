package com.github.Four04Bank.models.Account.Credit;

import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.models.Tax.Taxes;

import java.math.BigDecimal;

public class CreditAccount extends Account {

    private String numberCreditAccount;
    private BigDecimal limitBalance = new BigDecimal("500");
    private Taxes tax;

    public CreditAccount(String agency ,Holder holder) {
        super(agency, holder);
        generateNumberCreditAccount();
    }

    public String getNumberAccount() {
        return numberCreditAccount;
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

    private void generateNumberCreditAccount(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("03");
        for (int i = 0; i <= 3; i++){
            int numberAccount = (int)(Math.random() * 10);
            stringBuilder.append(numberAccount);
        }
        numberCreditAccount = stringBuilder.toString();
    }
    private void validateBalance(){
        if(getBalance().compareTo(limitBalance) <= 0){
            throw new AccountException("[ERROR] Seu Saldo é menor ou igual ao limite de crédito");
        }
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
    public void transfer(Account recipientAccount, BigDecimal money) {
        validateMoney(money);
        if(recipientAccount == this || recipientAccount == null){
            throw new AccountException("[ERROR] Conta inválida.");
        }
        this.withdraw(money);
        recipientAccount.deposit(money);
    }
    @Override
    public String getCompleteNumberAccount() {
        return getNumber() + "-"+ this.numberCreditAccount;
    }

    @Override
    public String toString() {
        return "Conta Corrente: Agência: "+getAgency()+" - Número da Conta: " + getCompleteNumberAccount() +
                "\nTitular: \n"+ getHolder()+ ".";
    }

}
