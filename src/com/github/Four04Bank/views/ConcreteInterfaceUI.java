package com.github.Four04Bank.views;

import com.github.Four04Bank.views.InterfaceViews.InterfaceUI;

public class ConcreteInterfaceUI implements InterfaceUI {

    private CreateCostumerUI createCostumerUI;
    private OperationAccountsUI operationAccountsUI;
    private OperationCollaboratorUI collaboratorUI;


    public ConcreteInterfaceUI(){
        this.createCostumerUI = new CreateCostumerUI();
        this.operationAccountsUI = new OperationAccountsUI();
        this.collaboratorUI = new OperationCollaboratorUI();
    }


    @Override
    public void newCostumer() {
        createCostumerUI.newCostumer();
    }

    @Override
    public void operationCostumer() {
        operationAccountsUI.operationCostumer();
    }

    @Override
    public void menuCollaborator() {
        collaboratorUI.operationCollaborator();
    }
}
