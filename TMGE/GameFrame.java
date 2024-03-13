package TMGE;

// GameFrame class

// Imports
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {    
    private JFrame frame;
    private JPanel board1;
    private JPanel board2;
    private JPanel clicked;

    // ClickablePanel is a class to represent the Tiles in the game board
    private class ClickablePanel extends JPanel {
        public ClickablePanel() {
            super();
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clicked = ClickablePanel.this;
                    System.out.println("Panel clicked");
                }
            });
        }
    }

    public GameFrame(List<Board> boardList){
        // Setting up the external game window
        this.frame = new JFrame("Game");
        this.board1 = new JPanel();
        this.board2 = new JPanel();

        this.frame.setSize(500, 1000);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new FlowLayout());
        this.frame.getContentPane().setBackground(Color.BLACK);
        this.frame.setVisible(true);
        populateBoard(boardList.get(0), 0);
        populateBoard(boardList.get(1), 1);
        
        this.frame.add(this.board1);
        this.frame.add(this.board2);
        this.clicked = null;
    };

    public void swap_tile(String movement) {
        if (this.clicked == null) {
            return;
        }
        // implement buttons for movements
        // integrate move functions
        switch (movement) {
            case "w":
                
                break;
            case "a":
                
                break;
            case "s":
                
                break;
            case "d":
                
                break;
        
            default:
                break;
        }
    }

    public void execute(){
    }

    public void populateBoard(Board board, int boardId) {
        JPanel tempPanel = (boardId == 0) ? this.board1 : this.board2;
        tempPanel.removeAll();
        for (int i = 0; i < board.row; i++) {
            for (int j = 0; j < board.col; j++) {
                ClickablePanel tilePanel = new ClickablePanel();
                tilePanel.setBorder(BorderFactory.createLineBorder(Color.black));
                switch (board.getTile(i, j).getColor()) {
                    case BLUE:
                        tilePanel.setBackground(Color.BLUE);
                        break;
                    case YELLOW:
                        tilePanel.setBackground(Color.YELLOW);
                        break;
                    case ORANGE:
                        tilePanel.setBackground(Color.ORANGE);
                        break;
                    case BLACK:
                        tilePanel.setBackground(Color.BLACK);
                        break;
                    case RED:
                        tilePanel.setBackground(Color.RED);
                        break;
                    case GREEN:
                        tilePanel.setBackground(Color.GREEN);
                        break;
                    default:
                        tilePanel.setBackground(Color.WHITE);
                        break;
                }
                tempPanel.add(tilePanel);
            }
        }
        tempPanel.revalidate();
        tempPanel.repaint();
    }
}
