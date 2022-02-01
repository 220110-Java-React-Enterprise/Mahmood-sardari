package View;

import Persistence.AccountClientsRepo;
import Utils.DataStore;
import Utils.ViewManager;

import java.io.IOException;
import java.sql.SQLException;

public class TransactionView extends View {
    public TransactionView() {
        viewName = "transaction";
        viewManager = ViewManager.getViewManager();
    }

    @Override
    public void renderView() throws SQLException, IOException {
        System.out.println(" Do you want to 1) deposit or 2) withdraw 3) delete this account? 4) Go back");
        String input = viewManager.getScanner().nextLine();

        switch (input) {
            case "1":
                deposit();
                break;
            case "2":
                withdraw();
                break;
            case "3":
                //      delete();
                break;
            case "4":
                viewManager.navigate("Account");
                break;
            default:
                System.out.println("\nOops, try again...\n\n\n");
                break;
        }


    }

    private void deposit() throws SQLException, IOException {
        System.out.println(" How much do you want to add to your account? ");
        String input = viewManager.getScanner().nextLine();


        double amount = Double.parseDouble(input);

        if (amount < 0) {
            System.out.println(" you cant deposit negative values, please try again!");
        } else {
            double newAmount = amount + DataStore.getCurrentAccount().getBalance();
            DataStore.getCurrentAccount().setBalance(newAmount);
            AccountClientsRepo repo = new AccountClientsRepo();
            repo.update(DataStore.getCurrentAccount());
            System.out.println(" you deposited " + amount + " to your account and the new amount in your is " + newAmount);
        }

        System.out.println("\n1. Do you want to Continue?");
        System.out.println("2. or do you want to Logout?");
        input = viewManager.getScanner().nextLine();


        switch (input) {
            case "1":
                viewManager.navigate("transaction");
                break;
            case "2":
                System.out.println("=================================\n" +
                        "Thank you for using MicroBank!\n" +
                        "=================================");
                viewManager.quit();
                break;
        }
    }
    //

    private void withdraw() throws SQLException, IOException {
        System.out.println("How much you want to withdraw today?");
        String input = viewManager.getScanner().nextLine();
        double amount = Double.parseDouble(input);

        if (amount < 0) {
            System.out.println(" ++++++++++Sorry! you cant withdraw negative values, please try again!+++++++++++");
        } else {


            double newAmount = DataStore.getCurrentAccount().getBalance() - amount;
            DataStore.getCurrentAccount().setBalance(newAmount);
            AccountClientsRepo repo = new AccountClientsRepo();
            repo.update(DataStore.getCurrentAccount());
            System.out.println(" you withdrew  " + amount + " from your account and new amount is " + newAmount);

        }
        System.out.println("1. Do you want to Continue?");
        System.out.println("2. or do you want to Logout?");
        input = viewManager.getScanner().

                nextLine();


        switch (input) {
            case "1":
                viewManager.navigate("transaction");
                break;
            case "2":
                System.out.println("=================================\n" +
                        "Thank you for using MicroBank!\n" +
                        "=================================");
                viewManager.quit();
                break;
        }
    }
}









