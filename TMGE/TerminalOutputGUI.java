package TMGE;
// TerminalOutputGUI Class
/*
    A class built around displaying to terminal.
 */

// Imports

public class TerminalOutputGUI implements GUI {
    @Override
    public void printToScreen(Object printable) {
        System.out.println(printable);
    }
}
