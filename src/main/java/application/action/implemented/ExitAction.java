package application.action.implemented;

import application.action.Action;
import application.action.ActionType;
import application.action.context.BaseContext;
import application.action.context.ContextWithRouter;
import application.utils.Router;

public class ExitAction implements Action<ContextWithRouter> {
    @Override
    public String message() {
        return "";
    }

    @Override
    public String title() {
        return "Exit";
    }

    @Override
    public void execute(ContextWithRouter context) {
        Router router = context.content().getRouter();

        ActionType type = router.getParrentOf(router.getCurrentAction());
        if (type == null){
            System.out.println("you are at root");
        }else {
            router.setCurrentAction(type);
        }
    }

}
