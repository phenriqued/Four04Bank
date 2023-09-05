package test.model.Account.Savings;

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

import static org.junit.jupiter.api.Assertions.*;


class SavingsAccountTest {

    private SavingsAccount savingsAccount;
    private AccountController accountController;

    @BeforeEach
    void initializer(){
         ClientData data = new ClientData("Teste", "Rua de Teste", "11-989898987");
         Holder holder = new PhysicalHolder( data,new CPF("12345678909"));
         this.savingsAccount = new SavingsAccount("1234", holder);
         savingsAccount.deposit(new BigDecimal("1000"));
         this.accountController = new AccountController();
    }

    @Test
    void testForDepositScenarios(){
        //O deposito NÃO pode aceitar valores menor que zero
        assertThrows(AccountException.class, () -> accountController.deposit(savingsAccount, new BigDecimal("-1")));

        //O deposito tem que ser maior que zero!
        assertThrows(AccountException.class, () -> accountController.deposit(savingsAccount, BigDecimal.ZERO));

        //Qualquer valor acima de 0, será efetuado o deposito! O valor de 1010, pois já foi efetuado um deposito no método "initializer()"
        accountController.deposit(savingsAccount, new BigDecimal("10"));
        assertEquals(savingsAccount.getBalance(), new BigDecimal("1010"));
    }

    @Test
    void testForWithdrawScenarios() {
        //Para efetuar o saque da conta. Onde há R$1.000 e será retirado 200, restando R$800.
        accountController.withdraw(savingsAccount,new BigDecimal("200"));
        assertEquals(savingsAccount.getBalance(), new BigDecimal("800"));

        //Para efetuar o saque retirando toda a quantidade da conta!
        accountController.withdraw(savingsAccount,new BigDecimal("800"));
        assertEquals(savingsAccount.getBalance(), BigDecimal.ZERO);

        //Para efetuar o saque retirando um dinheiro que não há na conta.
        assertThrows(AccountException.class, () -> accountController.withdraw(savingsAccount,new BigDecimal("100")));
    }

    @Test
    void transferTestsToOtherSavingsAccounts() {
        //Criando outra conta poupança.
        SavingsAccount otherAccount = createAnotherSavingsAccount();

        //transferências para outra conta poupança não há imposto, deve ser efetuada normalmente,
        // desde que a conta tenha dinheiro e a conta destinatario exista.
        accountController.ted(otherAccount,savingsAccount, new BigDecimal("1000"));

        assertEquals(otherAccount.getBalance(), BigDecimal.ZERO);
        assertEquals(savingsAccount.getBalance(), new BigDecimal("2000"));

        //transferências não devem ser feito quando a conta destino não tem dinheiro.
        assertThrows(AccountException.class, () -> accountController.ted(otherAccount,savingsAccount, new BigDecimal("10")));

        //Uma conta não é capaz de fazer uma transferência para si mesmo
        assertThrows(AccountException.class, () -> accountController.ted(otherAccount,otherAccount, new BigDecimal("10")));

        //transferências não pode ser efetuadas para contas que não exista ou que são null;
        assertThrows(AccountException.class, () -> accountController.ted(otherAccount,null, new BigDecimal("10")));

    }
    @Test
    void transferTestsToCreditAccounts() {
        //Criando uma conta corrente/credito
        CreditAccount creditAccount = createCreditAccount();
        AccountController controller = new AccountController();
        controller.ted(savingsAccount, creditAccount, new BigDecimal("500"));

        assertEquals(creditAccount.getBalance(), new BigDecimal("1500"));
        assertEquals(savingsAccount.getBalance(), new BigDecimal("475.00"));
        //
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