//import Persistence.ItemModel;
//import Persistence.ItemRepo;
//import Persistence.UserModel;
//import Persistence.UserRepo;
import Utils.ConnectionManager;
import Utils.ViewManager;
import View.*;
//import Views.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String... args) throws SQLException,IOException {
        //Connection conn = ConnectionManager.getConnection();

        ViewManager.getViewManager().registerView(new WelcomeView());
        ViewManager.getViewManager().registerView(new RegisterView());
        ViewManager.getViewManager().registerView(new LoginView());
        ViewManager.getViewManager().registerView(new AccountView());
        ViewManager.getViewManager().registerView(new TransactionView());
//        ViewManager.getViewManager().registerView(new ItemListView());
//        ViewManager.getViewManager().registerView(new CreateNewItemView());
//        ViewManager.getViewManager().registerView(new MarkCompleteView());
//        ViewManager.getViewManager().registerView(new NewItemView());


        try {
            Connection conn = ConnectionManager.getConnection();

            ViewManager.getViewManager().navigate("welcome");
            while(ViewManager.getViewManager().isRunning()) {
                ViewManager.getViewManager().render();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
   }
