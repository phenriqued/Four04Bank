package com.github.Four04Bank.views;

import com.github.Four04Bank.controllers.AccountController.AccountController;
import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.views.InterfaceViews.AccountUI;

import java.math.BigDecimal;
import java.util.Scanner;

public class OperationAccountsUI{

    private Scanner scanner = new Scanner(System.in);
    private AccountUI accountUI;
    private CreateCostumerUI createCostumerUI;

    public OperationAccountsUI() {
        this.accountUI = new AccountController();
        this.createCostumerUI = new CreateCostumerUI();
    }


    public void operationCostumer(){
        System.out.print("Seja Bem Vindo(a). \nDigite o número da sua Conta: ");

        Account selectAccount = null;
        String numberAccount = scanner.nextLine();
        for (Account account : createCostumerUI.listAccounts){
            if (account.getNumber().equalsIgnoreCase(numberAccount)){
                selectAccount = account;
                break;
            }
        }
        if(selectAccount != null){
            OperationAccount(selectAccount);
        }else{
            System.out.println("[Conta não encontrada]: Verifique o número da conta.");
        }
    }

    private void OperationAccount(Account account){
        System.out.println("Seja Bem vindo (a) " + account.getHolder().getName());
        Integer operation = displayMenuOperation();
        while (operation != 4) {
            if (operation == 1) {
                System.out.print("Digite o valor a ser Depositado: ");
                String money = scanner.next();
                operationDeposit(account, money);
                System.out.println("Operação bem-sucedida!\nSaldo Atual: R$ "+account.getBalance());
                operation = displayMenuOperation();
            } else if (operation == 2) {
                System.out.print("Digite o valor a ser Sacado: ");
                String money = scanner.next();
                operationWithdraw(account, money);
                System.out.println("Operação bem-sucedida!\nSaldo Atual: R$ "+account.getBalance());
                operation = displayMenuOperation();
            } else if (operation == 3) {
                System.out.print("Digite o número da conta destino: ");
                String otherAccount = scanner.next();
                System.out.print("Digite o valor a ser transferido: ");
                String money = scanner.next();
                operationTed(account, otherAccount, money);
                System.out.println("Operação bem-sucedida!\nSaldo Atual: R$ "+account.getBalance());
                operation = displayMenuOperation();
            } else {
                System.out.println("Opção inválida!\n");
                operation = displayMenuOperation();
            }
        }
    }

    private void operationDeposit(Account account, String money){
        BigDecimal valueDeposit = new BigDecimal(money);
        accountUI.deposit(account, valueDeposit);
    }
    private void operationWithdraw(Account account, String money){
        BigDecimal valueDeposit = new BigDecimal(money);
        accountUI.withdraw(account, valueDeposit);
    }
    private void operationTed(Account myAccount, String numberOtherAccount,String money){
        Account otherAccount = null;
        for (Account account : createCostumerUI.listAccounts){
            if (account.getNumber().equalsIgnoreCase(numberOtherAccount)){
                otherAccount = account;
                break;
            }
        }
        if(otherAccount == null){
            throw new AccountException("Conta Destino não encontrada, verifique o número da conta.");
        }
        BigDecimal valueDeposit = new BigDecimal(money);
        accountUI.ted(myAccount,otherAccount, valueDeposit);
    }
    private Integer displayMenuOperation(){
        System.out.println("Qual Operação deseja efetuar?");
        System.out.println("""
                1 - Depositar
                2 - Sacar
                3 - Transferência
                4 - SAIR
                """);
        Integer operation = scanner.nextInt();
        return operation;
    }



}
