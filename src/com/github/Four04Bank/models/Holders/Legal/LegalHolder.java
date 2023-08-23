package com.github.Four04Bank.models.Holders.Legal;

import com.github.Four04Bank.models.Holders.Holder;

public class LegalHolder extends Holder {
    private String cnpj;

    public LegalHolder(String name, Integer old, String address, String phone, String cnpj) {
        super(name, old, address, phone);
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
            throw new NullPointerException("[ERROR] CPF inválido!");

    }

}