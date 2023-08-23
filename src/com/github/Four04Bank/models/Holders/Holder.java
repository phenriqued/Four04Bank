package com.github.Four04Bank.models.Holders;

import com.github.Four04Bank.exceptions.HolderException.HolderException;

public abstract class Holder {

    private String name;
    private Integer old;
    private String address;
    private String phone;

    public Holder(String name, Integer old, String address, String phone) {
        if(name == null || old == null || address == null || phone == null){
            throw new HolderException("[ERROR] Os dados não podem ser nulos!");
        }
        oldValidation(old);
        phoneValidation(phone);

        this.name = name;
        this.old = old;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getOld() {
        return old;
    }
    public void setOld(Integer old) {
        this.old = old;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        if(address.equalsIgnoreCase(this.address)){
            throw new HolderException("O endereço novo não pode ser o mesmo.");
        }
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        phoneValidation(phone);
        this.phone = phone;
    }

    private void oldValidation(Integer old){
        if(old < 18){
            throw new HolderException("Menor de idade não pode abrir conta!");
        }
    }
    private void phoneValidation(String phone){
        if(!phone.matches("^\\d{2}-\\d{9}$")){
            throw new HolderException("O número de celular deve conter dois números de DDD seguido por um hífen e mais nove dígitos numéricos para o número de celular.");
        }
    }


}
