package main;

import pages.*;

/**
 * Provides main class that will start the app.
 * @author Lim Zhi Yong
 * @version 1.0
 * @since 2023-10-15
 */
public class MainApp{

    /**
     * Main function that starts the program 
     * @param default args parameter 
     */
    public static void main(String[] args) {

        Page currentPage = new Welcome(null);
        do {
            currentPage = currentPage.startExecution();
        } while (!(currentPage instanceof Exit));

    }
} 
