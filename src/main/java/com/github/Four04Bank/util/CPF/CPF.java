package com.github.Four04Bank.util.CPF;


import com.github.Four04Bank.exceptions.HolderException.HolderException;

public class CPF {
    //validação simples
    private String cpf;


    public CPF(String cpf) {
        cpfValidate(cpf);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String formatCPF(String cpf){
        cpfValidate(cpf);
        return cpf.substring(0,3) + "." + cpf.substring(3, 6) +"."+ cpf.substring(6, 9) +"-"+ cpf.substring(9);
    }

    public void cpfValidate(String cpf){
        if(cpf == null || cpf.length() != 11)
            throw new HolderException("[ERROR] CPF inválido!");

    }
}
