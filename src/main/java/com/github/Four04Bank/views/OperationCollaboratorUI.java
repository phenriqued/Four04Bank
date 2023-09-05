package com.github.Four04Bank.views;

import com.github.Four04Bank.controllers.CollaboratorController.CollaboratorController;
import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Account.Collaborator.Collaborator;
import com.github.Four04Bank.views.InterfaceViews.CollaboratorUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OperationCollaboratorUI {

    private Scanner scanner = new Scanner(System.in);
    private CreateCostumerUI createCostumerUI;
    private CollaboratorUI collaboratorUI;

    private static List<Collaborator> collaborators = new ArrayList<>();

    public OperationCollaboratorUI() {
        this.createCostumerUI = new CreateCostumerUI();
        this.collaboratorUI = new CollaboratorController();
    }

    public void operationCollaborator(){

        Integer typeMenu = displayMenuCollaborator();

        while(typeMenu != 3){

            if(typeMenu == 1){
                createCollaborator();
                typeMenu = displayMenuCollaborator();
            }else if(typeMenu ==2){
                operationsCollaborator();
                typeMenu = displayMenuCollaborator();
            }else{
                System.out.println("Opção inválida!\n");
               typeMenu = displayMenuCollaborator();
            }

        }

    }

    private void createCollaborator(){
        System.out.print("\nDigite o nome do Colaborador: ");
        String name = scanner.nextLine();
        System.out.print("Digite o CPF do colaborador: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o DRT do colaborador: ");
        String drt = scanner.next();
        System.out.print("Digite a nova Senha do colaborador: ");
        String password = scanner.next();
        Collaborator newCollaborator = collaboratorUI.createCollaborator(name, cpf, drt, password);
        collaborators.add(newCollaborator);
        System.out.println("\nColaborador registrado com sucesso!");
    }

    private void operationsCollaborator(){
        System.out.print("\nDigite o DRT do Colaborador: ");
        String drt = scanner.nextLine();
        System.out.print("Digite a Senha do Colaborador: ");
        String password = scanner.next();

        validateCollaborator(drt, password);

        Integer typeMenuAdmin = displayMenuADMIN();
        menuAdmin(typeMenuAdmin);
    }

    private void validateCollaborator(String drt, String password){
        Collaborator worker = null;

        for (Collaborator collaborator : collaborators) {
            if(collaborator.getDrt().equalsIgnoreCase(drt)){
                worker = collaborator;
                break;
            }
        }
        if (worker == null){
            throw new AccountException("[ERROR] Colaborador não existe");
        }
        if(!worker.getAdminPassword().equals(password)){
            throw new AccountException("Senha inválida.");
        }
    }
    private void menuAdmin(Integer typeMenuAdmin) {
        while (typeMenuAdmin != 3) {
            if (typeMenuAdmin == 1) {
                createCostumerUI.listAccounts.stream().forEach(System.out::println);
                typeMenuAdmin = displayMenuADMIN();
            } else if (typeMenuAdmin == 2) {
                createCostumerUI.holders.stream().forEach(System.out::println);
                typeMenuAdmin = displayMenuADMIN();
            } else {
                System.out.println("Opção inválida!\n");
                typeMenuAdmin = displayMenuADMIN();
            }
        }

    }

    private Integer displayMenuCollaborator(){
        System.out.println("""
                Menu *Colaborador* Four04
                1 - Registrar um novo Colaborador
                2 - Sou um Colaborador
                3 - Voltar
                """);
        Integer typeMenu = scanner.nextInt();
        scanner.nextLine();
        return typeMenu;
    }
    private Integer displayMenuADMIN(){
        System.out.println("""
                1 - Listar Contas
                2 - Listar Clientes
                3 - Sair
                """);
        Integer typeMenuAdmin = scanner.nextInt();
        scanner.nextLine();
        return typeMenuAdmin;
    }


}
