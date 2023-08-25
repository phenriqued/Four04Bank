package com.github.Four04Bank.models.Account.Collaborator;

import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Account.Account;
import com.github.Four04Bank.util.CPF.CPF;

import java.util.ArrayList;
import java.util.List;

public class Collaborator {

    private String name;
    private CPF cpf;
    private String drt;
    private String adminPassword;



    public Collaborator(String name, CPF cpf, String drt, String adminPassword) {
        if(name == null || drt == null){
            throw new AccountException("Os dados do(a) colaborador não podem ser Nulos!");
        }
        cpf.cpfValidate(cpf.getCpf());
        validateAdminPassword(adminPassword);

        this.name = name;
        this.cpf = cpf;
        this.drt = drt;
        this.adminPassword = adminPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CPF getCpf() {
        return cpf;
    }

    public String getDrt() {
        return drt;
    }

    public void setDrt(String drt) {
        this.drt = drt;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        validateAdminPassword(adminPassword);
        this.adminPassword = adminPassword;
    }

    private void validateAdminPassword(String adminPassword){
        if(!adminPassword.startsWith("admin") || adminPassword == null){
            throw new AccountException("[ERROR] Senha inválida.");
        }
    }

    @Override
    public String toString() {
        return "Collaborator{" +
                "name='" + name + '\'' +
                ", cpf=" + cpf +
                ", drt='" + drt + '\'' +
                '}';
    }
}
