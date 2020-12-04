package application.action.implemented;

import application.action.Action;
import application.action.context.BaseContext;
import application.action.context.ContextWithRouter;

public class AccountsListsAction implements Action<BaseContext> {
    public AccountsListsAction() {
    }

    @Override
    public String message() {
        return "";
    }

    @Override
    public String title() {
        return "list of all accounts";
    }

    @Override
    public void execute(BaseContext context) {

        System.out.println(context.content().getBankAgency().getAccounts());

    }
}
