package View;


import Persistence.ClientModel;
import Persistence.ClientRepo;
import Utils.DataStore;
import Utils.ViewManager;

import java.io.IOException;
import java.sql.SQLException;

    public class LoginView extends View {
        public LoginView() {
            viewName = "login";
            viewManager = ViewManager.getViewManager();
        }
        @Override
        public void renderView() throws SQLException, IOException {
            System.out.println("Uesr Login\n===============");
            System.out.println("Enter username: ");
            String username =  viewManager.getScanner().nextLine();

            System.out.println("Enter password: ");
            String password =  viewManager.getScanner().nextLine();

            ClientRepo repo = new ClientRepo();
            ClientModel model = repo.authenticate(username, password);

            if(model == null) {
                System.out.println("\nIncorrect credentials... \n\n\n");
           //     viewManager.navigate("welcome");
                return;
            }

            DataStore.setCurrentUser(model);
            viewManager.navigate("Account");

        }
    }

