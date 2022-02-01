package Utils;



import Persistence.AccountClientsModel;
import Persistence.ClientModel;

    public class DataStore {
        private static ClientModel currentUser;
        private static AccountClientsModel currentAccount;

        public static AccountClientsModel getCurrentAccount() {
            return currentAccount;
        }

        public static void setCurrentAccount(AccountClientsModel currentAccount) {
            DataStore.currentAccount = currentAccount;
        }

        public static void setCurrentUser(ClientModel user) {
            currentUser = user;
        }

        public static ClientModel getCurrentUser() {
            return currentUser;
        }





    }

