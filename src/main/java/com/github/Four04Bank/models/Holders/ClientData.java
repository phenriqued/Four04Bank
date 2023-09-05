package com.github.Four04Bank.models.Holders;


import com.github.Four04Bank.exceptions.HolderException.HolderException;

public record ClientData(String name, String address, String phone) {
    public static void phoneValidation(String phone){
        if(!phone.matches("^\\d{2}-\\d{9}$")){
            throw new HolderException("O número de celular deve conter dois números de DDD seguido por um hífen e mais nove dígitos numéricos para o número de celular.");
        }
    }
}
