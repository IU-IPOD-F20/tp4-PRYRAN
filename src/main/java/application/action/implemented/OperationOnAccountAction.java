package application.action.implemented;

import application.action.Action;
import application.action.ActionType;
import application.action.context.ContextWithRouter;
import application.utils.Router;

public class OperationOnAccountAction implements Action<ContextWithRouter> {
    public OperationOnAccountAction() {
    }

    @Override
    public String message() {
        return null;
    }

    @Override
    public String title() {
        return "Operation on an account";
    }

    @Override
    public void execute(ContextWithRouter context) {
        Router router = context.content().getRouter();
        router.setCurrentAction(ActionType.OPERATION_ON_ACCOUNT);

    }
}
