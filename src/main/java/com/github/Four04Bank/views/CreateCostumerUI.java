package com.github.Four04Bank.views;




import com.github.Four04Bank.controllers.AccountController.AccountController;
import com.github.Four04Bank.controllers.HolderController.HolderController;
import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.views.InterfaceViews.AccountUI;
import com.github.Four04Bank.views.InterfaceViews.HolderInterfaceUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateCostumerUI {

    private Scanner scanner = new Scanner(System.in);
    private HolderInterfaceUI holderInterface;
    private AccountUI accountUI;

    protected static List<Account> listAccounts = new ArrayList<>();
    protected static List<Holder> holders = new ArrayList<>();

    public CreateCostumerUI(){
        holderInterface = new HolderController();
        accountUI = new AccountController();
    }

    public void newCostumer() {

        System.out.println("""
                        Qual o tipo do titular: Físico ou Jurídico
                        F - Físico
                        J - Jurídico
                        V - Voltar
                        """);
        String type = scanner.next();

        while(!type.equalsIgnoreCase("V")) {

            if (type.equalsIgnoreCase("F")) {

                Holder holder = typeOfCostumer(type);
                Account account = typeOfAccount(type, holder);
                listAccounts.add(account);
                holders.add(holder);
                break;

            } else if (type.equalsIgnoreCase("J")) {

                Holder holder = typeOfCostumer(type);
                Account account = typeOfAccount(type, holder);
                listAccounts.add(account);
                holders.add(holder);
                break;

            } else {
                System.out.println("Opção inválida!\n");
                System.out.println("""  
                        Você é Pessoa Física ou Jurídica
                        F - Física
                        J - Jurídica
                        V - Voltar
                        """);
                type = scanner.next();
            }
        }
    }


    public Holder typeOfCostumer(String type){

        if (type.equalsIgnoreCase("F")){
            scanner.nextLine();
            System.out.print("Digite o nome do cliente: ");
            var nome = scanner.nextLine();
            System.out.print("Digite o endereço do cliente: ");
            var address = scanner.nextLine();
            System.out.print("Digite o telefone de contato do cliente \nNo Formato = XX-XXXXXXXXX : ");
            var phone = scanner.nextLine();
            System.out.print("Digite o CPF do cliente: ");
            var cpf = scanner.nextLine();

            return  holderInterface.createPhysicalHolder(nome, address, phone, cpf);


        }else{
            scanner.nextLine();
            System.out.print("Digite o nome do orgão: ");
            var nome = scanner.nextLine();
            System.out.print("Digite o endereço do orgão: ");
            var address = scanner.nextLine();
            System.out.print("Digite o telefone de contato do Orgão\nNo Formato = XX-XXXXXXXXX : ");
            var phone = scanner.nextLine();
            System.out.print("Digite o CNPJ do Orgão: ");
            var cpf = scanner.nextLine();

            return holderInterface.createLegalHolder(nome, address, phone, cpf);
        }
    }

    public Account typeOfAccount(String type, Holder holder) {

        System.out.println("""
                        \nQual Tipo de conta deseja: Conta Poupança ou Conta Conrrente
                        P -  Conta Poupança
                        C -  Conta Conrrente
                        """);
        type = scanner.next();

        while (!type.equalsIgnoreCase("sair")) {
            if (type.equalsIgnoreCase("P")) {
                scanner.nextLine();
                System.out.print("Digite o número da Agência: ");
                var agency = scanner.nextLine();

                Account account = accountUI.createSavingAccount(agency, holder);

                System.out.println("\n"+account);
                return account;

            } else if (type.equalsIgnoreCase("C")) {

                scanner.nextLine();
                System.out.print("Digite o nome o número da Agência: ");
                var agency = scanner.nextLine();

                Account account =accountUI.createCreditAccount(agency, holder);

                System.out.println("\n"+account);
                return account;

            }
                System.out.println("Opção inválida!\n");
                System.out.println("""  
                        Qual Tipo de conta deseja: Conta Poupança ou Conta Conrrente
                        P -  Conta Poupança
                        C - Conta Conrrente
                        """);
                type = scanner.next();

        }
        return null;
    }


}
