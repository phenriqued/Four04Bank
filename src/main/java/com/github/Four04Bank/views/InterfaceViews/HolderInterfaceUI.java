package com.github.Four04Bank.views.InterfaceViews;


import com.github.Four04Bank.models.Holders.Legal.LegalHolder;
import com.github.Four04Bank.models.Holders.Physical.PhysicalHolder;

public interface HolderInterfaceUI {


    PhysicalHolder createPhysicalHolder(String name, String address, String phone, String cpf);

    LegalHolder createLegalHolder(String name, String address, String phone, String CNPJ);
}
