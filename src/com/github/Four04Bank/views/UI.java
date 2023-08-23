package com.github.Four04Bank.views;

import java.util.Scanner;

public class UI {

    private Scanner scanner = new Scanner(System.in);

    public UI(){
        printMenu();
    }

    private void printMenu() {
        Integer response = displayMenu();
        while (response != 4) {
            switch (response) {
                case 1:
                    System.out.println("Venha ser cliente\n\n");
                    //newHolder();
                    break;
                case 2:
                    System.out.println("Seja bem nosso cliente\n\n");
                    //holderClient();
                    break;
                case 3:
                    System.out.println("Senha de Admin\n\n");
                    //colaborador();
                    break;
            }
            response = displayMenu();
        }
    }

    private Integer displayMenu(){
        System.out.println("""
                    Seja Bem Vindo ao Banco Four04.
                       Escolha uma opção:
                    1  - Não sou Cliente.
                    2  - Sou Cliente. 
                    3  - Sou Colaborador.
                    4  - Sair.
                    """);
        return scanner.nextInt();
    }




}
