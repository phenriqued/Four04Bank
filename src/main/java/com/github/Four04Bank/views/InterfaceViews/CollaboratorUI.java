package com.github.Four04Bank.views.InterfaceViews;


import com.github.Four04Bank.models.Account.Collaborator.Collaborator;

public interface CollaboratorUI {

    Collaborator createCollaborator(String name, String cpf, String drt, String adminPassword);
}
