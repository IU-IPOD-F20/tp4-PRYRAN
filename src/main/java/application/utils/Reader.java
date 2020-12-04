package application.utils;

import java.util.List;
import java.util.Scanner;

public class Reader {
    private static Reader instance;

    public static Reader getInstance() {
        if (instance == null){
            instance = new Reader();
        }
        return instance;
    }

    private Reader(){
        scanner = new Scanner(System.in);
    }

    Scanner scanner;

    public String readLine(){

        return scanner.next();
    }
    public Integer readInt(){
        try {
            return scanner.nextInt();

        }catch (Exception e){
            System.out.println("invalid input, try again");
            return readInt();
        }
    }
    public Double readDouble(){
        try {
            return scanner.nextDouble();
        }catch (Exception e){
            System.out.println("invalid input, try again");
            return readDouble();
        }
    }

    public void printChoises(List<String> options){
        int i = 0;

        System.out.println("select one of: ");
        for (String option : options) {
            System.out.println(i++ + ": " + option);
        }
        System.out.println("");
    }

    public int getChoise(List<String> options){


        printChoises(options);

        int choise;
        try {
            do {
                choise = scanner.nextInt();

            }while (choise < 0 || choise > options.size());
        } catch (Exception e){
            System.out.println("invalid input, try again");
            choise = getChoise(options);
        }

        return choise;

    }
}
