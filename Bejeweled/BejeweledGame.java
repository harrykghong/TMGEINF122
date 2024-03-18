package Bejeweled;

import TMGE.Board;
import TMGE.GUI;
import TMGE.Rule;
import TMGE.Game;

public class BejeweledGame extends Game{

    public BejeweledGame(GUI gui, Board template, int numberOfPlayers, Rule ruleSet) {
        super(gui, template, numberOfPlayers, ruleSet);
    }

    @Override
    public void updateGameState(int currentPlayerIndex) {
        // Rule Class added version
        Board currentBoard = boardList.get(currentPlayerIndex);
        

        int addscore = this.rule.runAllMatch(currentBoard);

        // if addscore != 0, there is matching happen
        while(addscore != 0){
            currentBoard.dropTiles();
            if (this.doesFill) { this.spawner.fill(currentBoard); };
            userManager.getUser(users.get(currentPlayerIndex)).addScore(addscore);

            // after drop and fill, do the run all match again
            addscore = this.rule.runAllMatch(currentBoard);
        }
    }

    @Override
    public boolean processInput(String input, int currentPlayerIndex) throws Exception {
        // Process player input or simulate game actions

        // WASD Controls
        // F to select
        // R to swap
        // w s a d to control the selector in board, r to confirm the selection
        Board currentBoard = boardList.get(currentPlayerIndex);
        switch(input){
            case "w":
                currentBoard.moveSelectorUp();
                return false;
            case "s":
                currentBoard.moveSelectorDown();
                return false;
            case "a":
                currentBoard.moveSelectorLeft();
                return false;
            case "d":
                currentBoard.moveSelectorRight();
                return false;
            case "f":
                currentBoard.selectorSelects();
                return false;
            case "r":

                // swap happens up or down
                if (Math.abs(currentBoard.saveX - currentBoard.selectorX) <= 1 && Math.abs(currentBoard.saveY - currentBoard.selectorY) == 0 
                || Math.abs(currentBoard.saveX - currentBoard.selectorX) == 0 && Math.abs(currentBoard.saveY - currentBoard.selectorY) <= 1) {
                    return currentBoard.selectorSwap();
                }else{
                    return false;
                }
                
                // Do not put only "return false" here, it is the condition to progress turns
            default:
                throw new Exception();
                // return false;
        }
    }
    
}