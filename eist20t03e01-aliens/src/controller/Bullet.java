package controller;

import model.Alien;

public class Bullet {
	
	protected boolean playerBullet;
	
    private String iconLocation = "Bullet.png";
	protected Point2D position;
	protected Dimension2D size = new Dimension2D(30, 13);
	
	private GameBoard gameBoard;


	public Bullet(boolean fromPlayer)
	{
		this.position = new Point2D(-10, -10);
		playerBullet = fromPlayer;
	}

	public void setGameBoard(GameBoard gb)
	{
		gameBoard = gb;
	}

	public GameBoard getGameBoard(GameBoard gb)
	{
		return gameBoard;
	}
	
	public boolean isPlayerBullet()
	{
		return playerBullet;
	}

	public Dimension2D getSize() {
		return this.size;
	}
	
    public String getIconLocation() {
        return this.iconLocation;
    }

    protected void setIcon(String iconLocation) throws IllegalArgumentException {
        if (iconLocation == null) {
            throw new IllegalArgumentException("The chassis image of a car connot be null.");
        }
        this.iconLocation = iconLocation;
    }

	public Point2D getPosition() {
		return this.position;
	}

	public void setPosition(int x, int y) {
		this.position = new Point2D(x, y);
	}
	
    public void evaluateDamage() 
    {
    	Point2D bulletPos = getPosition();
        Dimension2D bulletSize = getSize();
        if(playerBullet)
        {
	        for(Alien alien : gameBoard.getAliens())
	        {
	            Point2D     p2 = alien.getPosition();
	            Dimension2D d2 = alien.getSize();
	            if(bulletPos.getY() + bulletSize.getHeight() <  p2.getY() || bulletPos.getY() > p2.getY() + d2.getHeight()) continue;
	            if(Math.abs(((bulletPos.getX() + bulletSize.getWidth()/2) - ((p2.getX()+d2.getWidth()/2)))) < 10  && !(bulletPos.getX() > p2.getX() + d2.getWidth()))
	            {
	            	alien.setPosition(99999, 99999);
	            	this.setPosition(99999, 99999);
	            	alien.setDestroyed();
	            	boolean won = true;
	            	for(Alien al : gameBoard.getAliens())
	            	{
	            		if(!al.isDestroyed()) { won = false;}
	            	}
	            	if(won)
	            	{
	            		gameBoard.setWon(true);
	            		gameBoard.stopGame();
	            	}
	            	return;
	            	
	            }
	        }
        }
        else
        {
        	Point2D     p2 = gameBoard.getPlayerShip().getPosition();
            Dimension2D d2 = gameBoard.getPlayerShip().getSize();
            if(bulletPos.getY() + bulletSize.getHeight() <  p2.getY() || bulletPos.getY() >  p2.getY() + d2.getHeight()/5) return;
            if(Math.abs(((bulletPos.getX() + bulletSize.getWidth()/2) - ((p2.getX()+d2.getWidth()/2)))) < gameBoard.getPlayerShip().getSize().getWidth()/4  && !(bulletPos.getX() > p2.getX() + d2.getWidth()))
            {
            	this.setPosition(99999, 99999);
            	
            	gameBoard.getPlayer().setLifeCounter(gameBoard.getPlayer().getLifeCounter() -1);
            	
         
            	
            	if(gameBoard.getPlayer().getLifeCounter() > 0)
            	{
            		Point2D canvasPosition = gameBoard.getGUI().convertPosition(p2);
            		gameBoard.getGUI().getGraphicsContext2D().drawImage(gameBoard.getGUI().getImage("explosion.png"), (double) canvasPosition.getX(), (double) canvasPosition.getY(),
            				20.0, 20.0);
            		gameBoard.getGUI().getGraphicsContext2D().drawImage(gameBoard.getGUI().getImage("explosion.png"), (double) canvasPosition.getX()+gameBoard.getPlayerShip().getSize().getWidth()/2, (double) canvasPosition.getY(),
            				20.0, 20.0);
            		gameBoard.getAudioPlayer().playSound("explosion.wav", 0);
            	}
            	else
    			{
            		gameBoard.getPlayerShip().setPosition(99999, 99999);
            		gameBoard.setWon(false);
            		gameBoard.stopGame();
        		}
            	return;
            	
            }
        }
	}

}
