package com.github.Four04Bank.controllers.HolderController;

import com.github.Four04Bank.models.Holders.ClientData;
import com.github.Four04Bank.models.Holders.Legal.LegalHolder;
import com.github.Four04Bank.models.Holders.Physical.PhysicalHolder;
import com.github.Four04Bank.util.CPF.CPF;
import com.github.Four04Bank.views.InterfaceViews.HolderInterfaceUI;

public class HolderController implements HolderInterfaceUI {

    @Override
    public PhysicalHolder createPhysicalHolder(String name, String address, String phone, String cpf){
        CPF newCpf = new CPF(cpf);
        ClientData clientData = new ClientData(name, address, phone);
        return new PhysicalHolder(clientData, newCpf);
    }

    @Override
    public LegalHolder createLegalHolder(String name, String address, String phone, String CNPJ){
        ClientData clientData = new ClientData(name, address, phone);
        return new LegalHolder(clientData, CNPJ);
    }





}
