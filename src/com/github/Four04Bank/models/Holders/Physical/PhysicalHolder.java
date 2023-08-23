package com.github.Four04Bank.models.Holders.Physical;

import com.github.Four04Bank.models.Holders.Holder;

public class PhysicalHolder extends Holder {
    private String cpf;

    public PhysicalHolder(String name, Integer old, String address, String phone, String cpf) {
        super(name, old, address, phone);
        cpfValidate(cpf);
        this.cpf = cpf;
    }

    public String getCpf() {
        return formatCPF(this.cpf);
    }

    public String formatCPF(String cpf){
        cpfValidate(cpf);
        return cpf.substring(0,3) + "." + cpf.substring(3, 6) +"."+ cpf.substring(6, 9) +"-"+ cpf.substring(9);
    }

    private void cpfValidate(String cpf){
        if(cpf == null || cpf.length() != 11)
            throw new NullPointerException("[ERROR] CPF inválido!");

    }

}
