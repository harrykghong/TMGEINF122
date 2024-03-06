package TMGE;
// TerminalOutputGUI Class
/*
    A class built around displaying to terminal.
 */

import java.util.Scanner;

// Imports

public class TerminalOutputGUI implements GUI {
    private static TerminalOutputGUI instance;
    public Scanner scanner;

    private TerminalOutputGUI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void printToScreen(Object printable) {
        System.out.println(printable);
    }

    @Override
    public String getInput(String inputText) {
        printToScreen(inputText);
        return scanner.nextLine();
    }

    public static TerminalOutputGUI getInstance() {
        if (TerminalOutputGUI.instance == null) {
            TerminalOutputGUI.instance = new TerminalOutputGUI();
        }
        
        return TerminalOutputGUI.instance;
    }
}
