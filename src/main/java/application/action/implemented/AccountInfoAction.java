package application.action.implemented;

import application.action.Action;
import application.action.context.BaseContext;
import application.action.context.ContextWithRouter;
import application.utils.Reader;
import bank.Account;
import bank.BankAgency;

public class AccountInfoAction implements Action<BaseContext> {
    @Override
    public String message() {
        return "Info of account, please provide number";
    }

    @Override
    public String title() {
        return "See an account (by its number)";
    }

    @Override
    public void execute(BaseContext context) {
        BankAgency bankAgency = context.content().getBankAgency();
        Reader reader = context.content().getReader();

        String numbers = reader.readLine();


        Account account = bankAgency.getAccount(numbers);
        if (account != null){
            System.out.println(account);
        }else {
            System.out.println("no such account");
        }


    }
}
