package View;


import Utils.ViewManager;

import java.util.Scanner;

    public class WelcomeView extends View {
        public WelcomeView() {
            viewName = "welcome";
            viewManager = ViewManager.getViewManager();
        }


        @Override
        public void renderView() {
            System.out.println("Welcome to MicroBank!\n" +
                    "=======================\n" +
                    "1) Please register if you are a new client!\n" +
                    "-------------------- OR -------------------------" +
                    "\n2) login if you already have an account!\n" +
                    "=======================\n");

            String input = viewManager.getScanner().nextLine();


            switch(input) {
                case "1":
                    viewManager.navigate("register");
                    break;
                case "2":
                    viewManager.navigate("login");
                    break;
                default:
                    System.out.println("\nOops, try again...\n\n\n");
                    break;
            }

        }
    }

