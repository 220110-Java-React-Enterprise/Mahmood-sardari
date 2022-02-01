package View;

import Persistence.AccountClientsModel;
import Persistence.AccountClientsRepo;
import Persistence.DataAccountCRUD;
import Utils.CustomInterfaceList;
import Utils.DataStore;
import Utils.ViewManager;

import java.io.IOException;
import java.sql.SQLException;



public class AccountView extends View {
    public AccountView() {
        viewName = "Account";
        viewManager = ViewManager.getViewManager();
    }
    @Override
    public void renderView() throws SQLException, IOException {
        System.out.println("Choose the account you want! ");
        DataAccountCRUD repo = new AccountClientsRepo();
// we are making a list of accounts for a current user
        CustomInterfaceList<AccountClientsModel> list = repo.getAllAccounts(DataStore.getCurrentUser().getUserId());
// we are printing out the accounts in order
        int i;
        for ( i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + list.get(i).getAccount_name());
        }
        System.out.println("\t" + (i+1) + ". Create New Account");
        System.out.println("\t" + (i+2) + ". Logout");
        String input = viewManager.getScanner().nextLine();
        if (input.equals((i+1)+"")) { //user picked to create a new account

            System.out.println(" Enter an account name: " );
            input = viewManager.getScanner().nextLine();

            repo.create(new AccountClientsModel(DataStore.getCurrentUser().getUserId(), 0.0, input));


        } else if (input.equals((i+2)+"")) { //log out
            System.out.println("Have a good day!");
            viewManager.quit();
        }
        else {
            DataStore.setCurrentAccount(list.get(Integer.parseInt(input) - 1));
            viewManager.navigate("transaction");
        }

    }
}




