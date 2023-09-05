package com.github.Four04Bank.views;





import com.github.Four04Bank.views.InterfaceViews.InterfaceUI;

import java.util.Scanner;

public class UI {

    private Scanner scanner = new Scanner(System.in);

    InterfaceUI interfaceUI;

    public UI(InterfaceUI interfaceUI){
        this.interfaceUI = interfaceUI;
    }

    public void printMenu() {
        Integer response = displayMenu();
        while (response != 4) {
            try {
                switch (response) {
                    case 1:
                        interfaceUI.newCostumer();
                        break;
                    case 2:
                        interfaceUI.operationCostumer();
                        break;
                    case 3:
                        interfaceUI.menuCollaborator();
                        break;
                }
                response = displayMenu();
            }catch (Exception e){
                System.out.println("\n"+e.getMessage());
                System.out.println("Pressione \"Enter\" para continuar!\n");
                scanner.nextLine();
                scanner.nextLine();
            }
        }
    }

    private Integer displayMenu(){
        System.out.println("""
                    \nSeja Bem Vindo ao Banco Four04.
                          Escolha uma opção:
                    1  - Não sou Cliente.
                    2  - Sou Cliente. 
                    3  - Sou Colaborador.
                    4  - Sair.
                    """);
        return scanner.nextInt();
    }




}
