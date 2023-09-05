package test.model.Account.Credit;

import com.github.Four04Bank.controllers.AccountController.AccountController;
import com.github.Four04Bank.exceptions.AccountException.AccountException;
import com.github.Four04Bank.models.Account.Credit.CreditAccount;
import com.github.Four04Bank.models.Account.Savings.SavingsAccount;
import com.github.Four04Bank.models.Holders.ClientData;
import com.github.Four04Bank.models.Holders.Holder;
import com.github.Four04Bank.models.Holders.Physical.PhysicalHolder;
import com.github.Four04Bank.util.CPF.CPF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CreditAccountTest {

    private CreditAccount creditAccount;
    private AccountController accountController;

    @BeforeEach
    void initializer(){
         ClientData data = new ClientData("Teste", "Rua de Teste", "11-989898987");
         Holder holder = new PhysicalHolder( data,new CPF("12345678909"));
         this.creditAccount = new CreditAccount("1234", holder);
         creditAccount.deposit(new BigDecimal("1000"));
         this.accountController = new AccountController();
    }

    @Test
    void testForDepositScenarios(){
        //O deposito NÃO pode aceitar valores menor que zero
        assertThrows(AccountException.class, () -> accountController.deposit(creditAccount, new BigDecimal("-1")));

        //O deposito tem que ser maior que zero!
        assertThrows(AccountException.class, () -> accountController.deposit(creditAccount, BigDecimal.ZERO));

        //Qualquer valor acima de 0, será efetuado o deposito! O valor de 1010, pois já foi efetuado um deposito no método "initializer()"
        accountController.deposit(creditAccount, new BigDecimal("10"));
        assertEquals(creditAccount.getBalance(), new BigDecimal("1010"));
    }

    @Test
    void testForWithdrawScenarios() {
        //Para efetuar o saque da conta. Onde há R$1.000 e será retirado 200, restando R$800.
        accountController.withdraw(creditAccount,new BigDecimal("200"));
        assertEquals(creditAccount.getBalance(), new BigDecimal("780.0"));

        //Para efetuar o saque retirando toda a quantidade da conta!
        accountController.withdraw(creditAccount,new BigDecimal("780"));
        assertEquals(creditAccount.getBalance(), new BigDecimal("-78.0"));

        //Para efetuar o saque retirando um dinheiro que não há na conta. Utilizando do crédito da conta
        accountController.withdraw(creditAccount,new BigDecimal("100"));
        assertEquals(creditAccount.getBalance(), new BigDecimal("-188.0"));

        //Passando do limite de crédito da conta
        assertThrows(AccountException.class, () -> accountController.withdraw(creditAccount,new BigDecimal("500")));

    }

    @Test
    void transferTestsToOtherCreditAccounts() {
        //Criando outra conta poupança.
        CreditAccount otherAccount = createCreditAccount();

        //transferências para outra conta poupança não há imposto, deve ser efetuada normalmente,
        // desde que a conta tenha dinheiro e a conta destinatario exista.
        accountController.ted(otherAccount, creditAccount, new BigDecimal("1000"));

        assertEquals(otherAccount.getBalance(), BigDecimal.ZERO);
        assertEquals(creditAccount.getBalance(), new BigDecimal("2000"));

        //Uma conta de crédito é capaz de fazer um TED, mesmo que não haja dinheiro em sua conta, mas
        // será cobrado o imposto em cima do dinheiro retirado
        accountController.ted(otherAccount, creditAccount, new BigDecimal("200"));
        assertEquals(otherAccount.getBalance(), new BigDecimal("-220.0"));
        assertEquals(creditAccount.getBalance(), new BigDecimal("2200"));

        //Uma conta não é capaz de fazer uma transferência para si mesmo
        assertThrows(AccountException.class, () -> accountController.ted(otherAccount,otherAccount, new BigDecimal("10")));

        //transferências não pode ser efetuadas para contas que não exista ou que são null;
        assertThrows(AccountException.class, () -> accountController.ted(otherAccount,null, new BigDecimal("10")));

    }
    @Test
    void transferTestsToSavingsAccounts() {
        //Criando uma conta poupança
        SavingsAccount savingsAccount = createAnotherSavingsAccount();

        accountController.ted(creditAccount, savingsAccount, new BigDecimal("500"));

        assertEquals(creditAccount.getBalance(), new BigDecimal("500"));
        assertEquals(savingsAccount.getBalance(), new BigDecimal("1500"));
    }



    private SavingsAccount createAnotherSavingsAccount(){
        ClientData data = new ClientData("Teste2", "Rua de Teste", "11-989898987");
        Holder holder = new PhysicalHolder( data,new CPF("12345678909"));
        SavingsAccount otherAccount = new SavingsAccount("4321", holder);
        otherAccount.deposit(new BigDecimal("1000"));
        return otherAccount;
    }

    private CreditAccount createCreditAccount(){
        ClientData data = new ClientData("Teste3", "Rua de Teste3", "11-989898987");
        Holder holder = new PhysicalHolder( data,new CPF("12345678909"));
        CreditAccount otherAccount = new CreditAccount("4321", holder);
        otherAccount.deposit(new BigDecimal("1000"));
        return otherAccount;
    }



}