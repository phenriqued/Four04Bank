package com.github.Four04Bank.models.Holders;

import com.github.Four04Bank.exceptions.HolderException.HolderException;

public abstract class Holder {

    private String name;
    private String address;
    private String phone;

    public Holder(ClientData clientData) {
        if(clientData.name() == null ||
                    clientData.address() == null || clientData.phone() == null){
            throw new HolderException("[ERROR] Os dados não podem ser nulos!");
        }
        ClientData.phoneValidation(clientData.phone());

        this.name = clientData.name();
        this.address = clientData.address();
        this.phone = clientData.phone();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
        ClientData.phoneValidation(phone);
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
