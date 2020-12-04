package application.utils;

import application.Main;
import application.action.Action;
import application.action.ActionType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Router {
    private Router(){

    }
    private static Router instance;
    public static Router getInstance() {
        if (instance == null){
            instance = new Router();
        }
        return instance;
    }

    private final HashMap<ActionType, List<ActionType>> router = new HashMap<>();
    private ActionType currentAction;


    public void addRoute(ActionType key, ActionType...values ){
        router.put(
                key,
                Arrays.stream(values).collect(Collectors.toList())
        );
    }


    public ActionType getParrentOf(ActionType key){
        for (Map.Entry<ActionType, List<ActionType>> entry : router.entrySet()) {
            if (entry.getValue().contains(key)){
                return entry.getKey();
            }
        }
        return null;

    }
    public List<ActionType> getChildsOf(ActionType key){
        return router.getOrDefault(key, null);

    }

    public List<ActionType> getCurrentChilds(){
        return router.getOrDefault(this.getCurrentAction(),null);
    }

    public ActionType getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(ActionType currentAction) {
        this.currentAction = currentAction;
    }
}
