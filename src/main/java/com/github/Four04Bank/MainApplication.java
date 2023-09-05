package com.github.Four04Bank;

import com.github.Four04Bank.views.ConcreteInterfaceUI;
import com.github.Four04Bank.views.UI;

public class MainApplication {
    public static void main(String[] args) {

        UI userInterface = new UI(new ConcreteInterfaceUI());
        userInterface.printMenu();
    }
}