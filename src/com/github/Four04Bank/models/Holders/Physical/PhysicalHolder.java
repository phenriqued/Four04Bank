package com.github.Four04Bank.models.Holders.Physical;

import com.github.Four04Bank.exceptions.HolderException.HolderException;
import com.github.Four04Bank.models.Holders.ClientData;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.util.CPF.CPF;

public class PhysicalHolder extends Holder {
    private CPF cpf;


    public PhysicalHolder(ClientData data, CPF cpf) {
        super(data);
        cpf.cpfValidate(cpf.getCpf());
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf.formatCPF(this.cpf.getCpf());
    }

    @Override
    public String toString() {
        return "Titular Físico [Nome: " +getName() + " - CPF: "+ getCpf()+
                    "\nEndereço: "+getAddress() + " - Telefone: "+getPhone()+"]\n";
    }
}
