package application.action;


import application.action.context.Context;
import bank.exception.AccountException;

public interface Action <CONTEXT extends Context> {

    String message();

    String title();

    void execute(CONTEXT context) throws AccountException;



}
