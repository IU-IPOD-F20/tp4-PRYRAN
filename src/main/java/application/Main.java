package application;

import application.action.Action;
import application.action.ActionType;
import application.action.context.BaseContext;
import application.action.context.ContextWithRouter;
import application.action.context.Context;
import application.action.implemented.*;
import application.utils.Reader;
import application.utils.Router;
import bank.Account;
import bank.BankAgency;
import bank.exception.ABAccountAlreadyExistingException;
import bank.exception.ABNullAccountException;
import bank.exception.AccountException;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    // scanner wrapper for input reading
    static Reader reader = Reader.getInstance();

    // router to manipulate actions and submenus
    static Router router = Router.getInstance();
    static {
        // init configuration of menus
        router.addRoute(ActionType.INIT, ActionType.ACCOUNT_INFO,ActionType.ACCOUNT_LIST,ActionType.OPERATION_ON_ACCOUNT);
        router.addRoute(ActionType.OPERATION_ON_ACCOUNT,ActionType.DEPOSIT_MONEY);


        router.setCurrentAction(ActionType.INIT);
    }

    // bank agency example
    static BankAgency ag = new BankAgency("Tinkoff Bank", "Kazan");

    static {
        try {
            ag.addAccount(new Account("010101", "Durand"));
            ag.addAccount(new Account("010102", "Durand"));

            ag.addAccount(new Account("050501", "Leon"));
            ag.addAccount(new Account("050502", "Leon"));
            ag.addAccount(new Account("050503", "Leon"));
            ag.addAccount(new Account("090904", "Zeste"));
            ag.addAccount(new Account("090905", "Zeste"));
        } catch (ABNullAccountException e) {
            e.printStackTrace();
        } catch (ABAccountAlreadyExistingException e) {
            e.printStackTrace();
        }


    }

    /**
     * here is the main part:
     * i have implemented fully scalable and generic action menus. In order to create new actions - you implement interface Action<Context>
     *     with specific implementation of interface Context. Context is something like container, in whice all required params are passed to action. Also there is no restctions
     *     Context. You can implement context in every shape and types, but return all paarams wrapped in Container. Finally, you create new ENUM for new action
     *     After that, you follow rules and aztualize info about actions in these 3 maps.
     *     Everything is clear, i think.
     */
    static HashMap<ActionType, Action> actions = new HashMap<>();
    static {
        actions.put(ActionType.INIT,new InitAction());
        actions.put(ActionType.ACCOUNT_INFO,new AccountInfoAction());
        actions.put(ActionType.ACCOUNT_LIST,new AccountsListsAction());
        actions.put(ActionType.OPERATION_ON_ACCOUNT,new OperationOnAccountAction());
        actions.put(ActionType.EXIT,new ExitAction());
        actions.put(ActionType.DEPOSIT_MONEY,new DepositMoneyAction());
    }

    static HashMap<Class<? extends Action>, ActionType> types = new HashMap<>();
    static {
        types.put(InitAction.class,ActionType.INIT);
        types.put(AccountInfoAction.class,ActionType.ACCOUNT_INFO);
        types.put(AccountsListsAction.class,ActionType.ACCOUNT_LIST);
        types.put(OperationOnAccountAction.class,ActionType.OPERATION_ON_ACCOUNT);
        types.put(ExitAction.class,ActionType.EXIT);
        types.put(DepositMoneyAction.class,ActionType.DEPOSIT_MONEY);
    }


    static HashMap<ActionType,Context> contextx = new HashMap<>();
    static {
        contextx.put(ActionType.INIT, new BaseContext(reader, ag));
        contextx.put(ActionType.ACCOUNT_INFO,new BaseContext(reader,ag));
        contextx.put(ActionType.ACCOUNT_LIST,new BaseContext(reader,ag));
        contextx.put(ActionType.OPERATION_ON_ACCOUNT,new ContextWithRouter(reader,ag,router));
        contextx.put(ActionType.EXIT,new ContextWithRouter(reader,ag,router));
        contextx.put(ActionType.DEPOSIT_MONEY,new BaseContext(reader,ag));

    }


    /*
        I have been focusing on generic actions system, so there are some bugs related with Reader or bank-agency logic. I think, it is not the key of that assignment, but Actions.
     */
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ABAccountAlreadyExistingException, ABNullAccountException, AccountException {

        int choise;
        do {
            List<Action> choises = router.getCurrentChilds().stream().map(action ->
                    actions.get(action)).collect(Collectors.toList());
            choises.add(actions.get(ActionType.EXIT));

            choise = reader.getChoise(choises.stream().map(action -> action.title()).collect(Collectors.toList()));


            Action action = choises.get(choise);
            System.out.println(action.message());

            action.execute(contextx.get(types.get(action.getClass())));

        } while (true);






    }
}
