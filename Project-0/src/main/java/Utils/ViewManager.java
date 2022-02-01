package Utils;


import View.View;
//import Views.View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class ViewManager {
        private static ViewManager viewManager;
        private boolean running;
        private final Scanner scanner;
        CustomArrayList<View> viewList;
        View nextView;



        private ViewManager() {
            //set up starting values and references
            running = true;
            scanner = new Scanner(System.in);
            viewList = new CustomArrayList<>();
        }

        public static ViewManager getViewManager(){
            if(viewManager == null) {
                viewManager = new ViewManager();
            }
            return viewManager;
        }

        public void navigate(String destination) {
//            for(View view : viewList) {
//                if(view.getViewName().equals(destination)){
//                    nextView = view;
//                }
//            }
            for(int i = 0; i < viewList.size(); i++) {
                if (viewList.get(i).getViewName().equals(destination)){
                    nextView = viewList.get(i);
                }
            }
        }

        public void registerView(View view) {
            viewList.add(view);
        }

        public void render() throws SQLException, IOException {
            nextView.renderView();
        }

        public Scanner getScanner() {
            return scanner;
        }

        public void quit() {
            running = false;
        }

        public boolean isRunning() {
            return running;
        }
}
