package com.github.Four04Bank.models.Holders.Legal;

import com.github.Four04Bank.exceptions.HolderException.HolderException;
import com.github.Four04Bank.models.Holders.ClientData;
import com.github.Four04Bank.models.Holders.Holder;

public class LegalHolder extends Holder {
    private String cnpj;

    public LegalHolder(ClientData clientData, String cnpj) {
        super(clientData);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return formatCNPJ(this.cnpj);
    }

    public String formatCNPJ(String cnpj){
        cnpjValidate(cnpj);
        return cnpj.substring(0,2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
    }

    private void cnpjValidate(String cnpj){
        if(cnpj == null || cnpj.length() != 14)
            throw new HolderException("[ERROR] CNPJ inválido!");

    }

    @Override
    public String toString() {
        return "Titular Jurídico [Nome do Orgão: " +getName() + " - CNPJ: "+ getCnpj()+
                "\nEndereço: "+getAddress() + " - Telefone: "+getPhone()+"]\n";
    }

}
