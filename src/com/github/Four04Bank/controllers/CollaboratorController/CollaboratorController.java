package com.github.Four04Bank.controllers.CollaboratorController;

import com.github.Four04Bank.models.Account.Collaborator.Collaborator;
import com.github.Four04Bank.util.CPF.CPF;
import com.github.Four04Bank.views.InterfaceViews.CollaboratorUI;

public class CollaboratorController implements CollaboratorUI {

    @Override
    public Collaborator createCollaborator(String name, String cpf, String drt, String adminPassword) {
        CPF myCpf = new CPF(cpf);
        return new Collaborator(name, myCpf, drt, adminPassword);
    }


}
