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
    private JPanel currentBoard;

    // ClickablePanel is a class to represent the Tiles in the game board
    private class ClickablePanel extends JPanel {
        int row, col;

        public ClickablePanel(JPanel brd, int row, int col) {
            super();
            this.row = row;
            this.col = col;
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    clicked = ClickablePanel.this;
                    currentBoard = brd;
                    System.out.println("Panel clicked at " + row + ", " + col);
                }
            });
        }
    }

    public GameFrame(List<Board> boardList){
        // Setting up the external game window
        this.frame = new JFrame("Game");
        this.board1 = new JPanel();
        this.board2 = new JPanel();

        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
        JButton startButton = new JButton("Start Game");
        JButton restartButton = new JButton("Restart Game");

        upButton.addActionListener(e -> swap_tile("w"));
        downButton.addActionListener(e -> swap_tile("s"));
        leftButton.addActionListener(e -> swap_tile("a"));
        rightButton.addActionListener(e -> swap_tile("d"));

        // WIP
        // startButton.addActionListener(e -> startGame());
        // restartButton.addActionListener(e -> restartGame());

        // Setting up the external game window
        this.frame.setSize(500, 1000);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new FlowLayout());
        this.frame.getContentPane().setBackground(Color.BLACK);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 6));
        buttonPanel.add(upButton);
        buttonPanel.add(downButton);
        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);
        buttonPanel.add(startButton);
        buttonPanel.add(restartButton);

        this.frame.setLayout(new BorderLayout());
        this.frame.add(buttonPanel, BorderLayout.SOUTH);

        this.frame.setVisible(true);
        populateBoard(boardList.get(0), 0);
        populateBoard(boardList.get(1), 1);

        this.frame.add(board1, BorderLayout.WEST);
        this.frame.add(board2, BorderLayout.EAST);
        this.clicked = null;
    };

    public void swap_tile(String movement) {
        if (this.clicked == null || !(this.clicked instanceof ClickablePanel) || this.currentBoard == null) {
            return;
        }

        ClickablePanel clickedTile = (ClickablePanel) this.clicked;
        int currentRow = clickedTile.row;
        int currentCol = clickedTile.col;
        int newRow = currentRow, newCol = currentCol;

        switch (movement) {
            case "w": newRow -= 1; break; // Up
            case "a": newCol -= 1; break; // Left
            case "s": newRow += 1; break; // Down
            case "d": newCol += 1; break; // Right
            default: return;
        }

        if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 4) {
            Component[] components = currentBoard.getComponents();
            int indexCurrent = currentRow * 4 + currentCol;
            int indexTarget = newRow * 4 + newCol;

            if (indexCurrent >= 0 && indexCurrent < components.length && indexTarget >= 0 && indexTarget < components.length) {
                ClickablePanel targetTile = (ClickablePanel)components[indexTarget];

                Color tempColor = clickedTile.getBackground();
                clickedTile.setBackground(targetTile.getBackground());
                targetTile.setBackground(tempColor);

                clicked = null;
            }
        }
    }

    // Currently not in use
    public void execute(){
    }

    public void populateBoard(Board board, int boardId) {
        JPanel tempPanel = (boardId == 0) ? this.board1 : this.board2;
        tempPanel.removeAll();
        tempPanel.setLayout(new GridLayout(4, 4)); // Set to 4x4 grid
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ClickablePanel tilePanel = new ClickablePanel(tempPanel, i, j);
                tilePanel.setBorder(BorderFactory.createLineBorder(Color.black));
                // Not enough colors
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
