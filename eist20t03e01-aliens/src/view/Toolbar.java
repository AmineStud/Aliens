package view;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import model.Shooter;

import java.util.Optional;

import com.sun.javafx.text.TextLine;



/**
 *
 * This class visualizes the tool bar with start, stop and exit buttons above
 * the game board.
 *
 */
public class Toolbar extends ToolBar {
    private InvadersApplication gameWindow;
    private Button start;
    private Button stop;
    private Button shiptype;
    private Text  lifecounter;
    
    

    public Toolbar(InvadersApplication gameWindow) {
        this.start = new Button("Start");
        this.stop = new Button("Stop");
        this.shiptype = new Button("Choose Ship");
        this.lifecounter = new Text("Lives: 3");
        initActions();
        this.getItems().addAll(start, new Separator(), stop, new Separator(), shiptype, new Separator(), lifecounter);
        this.setGameWindow(gameWindow);
    }

    public void setLives(int lives)
    {
    	lifecounter = new Text("Lives: "+lives);
    	this.getItems().remove(6);
    	this.getItems().add(lifecounter);
    }
    /**
     * Initialises the actions
     */
    private void initActions() {
    	
    	
        this.start.setOnAction(event -> { 
        	getGameWindow().gameBoardUI.startGame(); 
        });
        this.stop.setOnAction(event -> {

            Toolbar.this.getGameWindow().gameBoardUI.stopGame();

            ButtonType YES = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType NO = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(AlertType.CONFIRMATION, "Do you really want to stop the game ?", YES, NO);
            alert.setTitle("Stop Game Confirmation");
            alert.setHeaderText("");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == YES) {
                getGameWindow().gameBoardUI.setupGame();
            } else {
                getGameWindow().gameBoardUI.startGame();
            }
        });
        this.shiptype.setOnAction(event -> {
        	
            ButtonType YES = new ButtonType("Standard Ship", ButtonBar.ButtonData.YES);
            ButtonType NO = new ButtonType("Agile Ship", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert alert = new Alert(AlertType.CONFIRMATION, "Choose the ship type", YES, NO);
            alert.setTitle("Ship Type Change");
            alert.setHeaderText("");

            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.get() == YES) {
            	gameWindow.gameBoardUI.getGameBoard().setPlayerShip(1);
            } else {
            	gameWindow.gameBoardUI.getGameBoard().setPlayerShip(2);
            }
            
        });
    }

    /**
     * Resets the toolbar button status
     * @param running Used to disable/enable buttons
     */
    public void resetToolBarButtonStatus(boolean running) {
        this.start.setDisable(running);
        this.stop.setDisable(!running);
        this.shiptype.setDisable(!running);
    }

    /**
     * @return current gameWindow
     */
    public InvadersApplication getGameWindow() {
        return this.gameWindow;
    }

    /**
     * @param gameWindow New gameWindow to be set
     */
    public void setGameWindow(InvadersApplication gameWindow) {
        this.gameWindow = gameWindow;
    }
}