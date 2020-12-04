package application.action.implemented;

import application.action.Action;
import application.action.context.BaseContext;
import application.utils.Reader;
import bank.Account;
import bank.BankAgency;
import bank.exception.AccountException;

public class DepositMoneyAction implements Action<BaseContext> {
    @Override
    public String message() {
        return "Select ccount number and then number of money u want to deposit";
    }

    @Override
    public String title() {
        return "Deposit money on an account";
    }

    @Override
    public void execute(BaseContext context) throws AccountException {
        BaseContext.Container container = context.content();

        BankAgency agency = container.getBankAgency();
        Reader reader = container.getReader();


        Account account = agency.getAccount(reader.readLine());
        if (account == null){
            System.out.println("no accout wound");
        }else {
            account.deposit(reader.readDouble());
        }


    }
}
