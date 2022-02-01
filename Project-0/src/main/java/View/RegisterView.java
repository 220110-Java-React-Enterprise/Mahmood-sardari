package View;


import Persistence. ClientModel;
import Persistence.ClientRepo;
import Utils.DataStore;
import Utils.ViewManager;

import javax.naming.Context;
import java.io.IOException;
import java.sql.SQLException;

    public class RegisterView extends View {
        public RegisterView() {
            viewName = "register";
            viewManager = ViewManager.getViewManager();
        }
        @Override
        public void renderView() throws SQLException, IOException {
            System.out.println("Register new user\n===============");
            System.out.println("Enter new username:");
            String username =  viewManager.getScanner().nextLine();

            System.out.println("Enter new password: ");
            String password =  viewManager.getScanner().nextLine();
            ClientModel newUser = new  ClientModel(username, password);
            ClientRepo repo = new ClientRepo();
            newUser.setUserId(repo.create(newUser));

            DataStore.setCurrentUser(newUser);

            viewManager.navigate("Account");

        }
    }

