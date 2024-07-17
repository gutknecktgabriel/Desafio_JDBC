package app;

import java.util.Scanner;

public class App {

    public void startApp(){
        AppView view = new AppView();
        Scanner terminal  = new Scanner(System.in);
        view.showMenu(terminal);
    }

}
