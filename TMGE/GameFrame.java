package TMGE;

// GameFrame class

// Imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextArea;

public class GameFrame extends JFrame {
    
    private JFrame frame;
    private JPanel board1;
    private JPanel board2;
    
    public GameFrame(){
        // Setting up the external game window
        this.frame = new JFrame("GameFrame");
        this.board1 = new JPanel();
        this.board2 = new JPanel();

        this.frame.setSize(500, 1000);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new FlowLayout());
        this.frame.getContentPane().setBackground(Color.BLACK);
        
        JPanel test1 = new JPanel();
        test1.add(new TextArea());
        JPanel test2 = new JPanel();
        test2.add(new TextArea());

        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, test1, test2);
        this.frame.getContentPane().add(splitpane);

        this.frame.setVisible(true);

    };

    public void execute(){
    }

    public void populateBoard(Board board, int boardId){
        Jpanel tempPanel;
        if (boardId == 1){
            tempPanel = this.board1;
        } else {
            tempPanel = this.board2;
        }
    }
}
