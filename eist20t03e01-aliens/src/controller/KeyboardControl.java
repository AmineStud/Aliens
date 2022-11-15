
package controller;

import view.GameBoardUI;


import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Shooter;
/**
 * This class is responsible for the handling the MOUSE_PRESSED Event, 
 * i.e. the steering of the userShip 
 */

public class KeyboardControl {

	private GameBoardUI gameBoardUI;
	
	private long lastShot;
	
	/**
	 * Constructor, creates a MouseSteering instance with a specific playingFlied and userShip
	 * @param playingField
	 * @param userShip
	 */
	public KeyboardControl(GameBoardUI gameBoardUI) {
		lastShot = System.currentTimeMillis() / 1000L - 3;
		this.gameBoardUI = gameBoardUI;
		gameBoardUI.setFocusTraversable(true);
		gameBoardUI.requestFocus();
		gameBoardUI.addEventFilter(MouseEvent.ANY, (e) -> gameBoardUI.requestFocus());
		gameBoardUI.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	Shooter userShip = gameBoardUI.getGameBoard().getPlayerShip();
                switch (event.getCharacter()) {
                case "d": userShip.setPosition(userShip.getPosition().getX() >  gameBoardUI.getWidth() -41  ? userShip.getPosition().getX():userShip.getPosition().getX() + 10, userShip.getPosition().getY());break;
                case "q": userShip.setPosition(userShip.getPosition().getX() < -10  ? userShip.getPosition().getX():userShip.getPosition().getX() - 10, userShip.getPosition().getY());break;
                case "f": 
            	{
            		if(System.currentTimeMillis() / 1000L - lastShot > userShip.getCooldown())
            		{
	            		lastShot = System.currentTimeMillis() / 1000L;
	            		
	            		int bX, bY;
	            		bY = 37;
	            		bX = userShip.getPosition().getX() + userShip.getSize().getWidth()/2  - 15;
	            		Bullet b = new Bullet(true);
	            		b.setGameBoard(gameBoardUI.getGameBoard());
	            		b.setPosition(bX, bY);
	            		gameBoardUI.getGameBoard().getBulletsToBeAdded().add(b);
	            		gameBoardUI.getCarImages().put(b, gameBoardUI.getImage(b.getIconLocation()));
	                    gameBoardUI.getGameBoard().getAudioPlayer().playSound("playershot.wav", 0);
            		}
            		break;
            	}
				default:
					break;
                }
            }
        });
	}
	
	public Shooter getuserShip() {
		return gameBoardUI.getGameBoard().getPlayerShip();
	}

	
	public GameBoardUI getGameBoardUI() {
		return this.gameBoardUI;
	}
	
	public void setGameBoardUI(GameBoardUI gameBoardUI) {
		this.gameBoardUI = gameBoardUI;
	}

	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
        	System.out.println("a");
        }
     
    };
	/*
	EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {

	
		@Override
		public void handle(final KeyEvent e) {
			System.out.println("h");
        	switch(e.getCode())
        	{
				case KP_DOWN:
					break;
				case KP_LEFT:
				{
					System.out.println("working");
					userShip.setPosition(userShip.getPosition().getX()-15, userShip.getPosition().getY());
					break;
				}
				case KP_RIGHT:
					break;d
				case KP_UP:
					break;
				case SPACE:
					break;
				default:
				{
					break;
				}
        	
        	}
			
		}
     
    };*/


}
