package TMGE;

// GameFrame class

// Imports
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameFrame extends JFrame {
    
    private JFrame frame;
    private JPanel board1;
    private JPanel board2;
    
    public GameFrame(List<Board> boardList){
        // Setting up the external game window
        this.frame = new JFrame("GameFrame");
        this.board1 = new JPanel();
        this.board2 = new JPanel();

        this.board1.setLayout(new GridBagLayout());

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
    };

    public void execute(){
    }

    public void populateBoard(Board board, int boardId) {
        JPanel tempPanel = (boardId == 1) ? this.board1 : this.board2;
        tempPanel.removeAll();
        for (int i = 0; i < board.row; i++) {
            for (int j = 0; j < board.col; j++) {
                JPanel tilePanel = new JPanel();
                tilePanel.setBorder(BorderFactory.createLineBorder(Color.black));
                switch (board.getTile(i, j).getColor()) {
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
