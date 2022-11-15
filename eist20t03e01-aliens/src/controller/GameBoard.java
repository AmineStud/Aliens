package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.AgileSpaceship;
import model.Alien;
import model.Shooter;
import model.StandardSpaceship;
import view.GameBoardUI;

public class GameBoard {
	
	private AudioPlayerInterface audioPlayer;
	private ArrayList<Alien> aliens = new ArrayList<>();
	private ArrayList<Bullet> bullets = new ArrayList<>();
	ArrayList<Bullet> bulletsToBeAdded = new ArrayList<>();
    private Boolean gameWon = null;
	private Player player;
	
	private GameBoardUI gui;
	
	private int alPos = 0;
	private int steps = 15;
	private int direction = -1;
	private int RIGHT = 1, LEFT = 2;

    private boolean isRunning;

	private Dimension2D size;

	public static int NUMBER_OF_ALIENS = 25;
	

    ///////////////////////////////// 7adhrine
	
	public AudioPlayerInterface getAudioPlayer() {
        return this.audioPlayer;
    }

    public void setAudioPlayer(AudioPlayerInterface audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

	public void addAliens() {
		for (int i = 0; i < NUMBER_OF_ALIENS; i++) {
			Alien al = new Alien(this.size.getWidth(), this.size.getHeight());
			this.aliens.add(al);
			al.setPosition(105+ (i%5) *( al.getSize().getWidth() + 10) , 150+ (i/5)*( al.getSize().getHeight() + 10) );
		}
	}

	/**
	 * Removes all existing cars from the car list, resets the position of the
	 * player car Invokes the creation of new car objects by calling addAliens()
	 */
	public void resetShips() {
		this.player.getShip().reset(this.size.getHeight());
		this.player.getShip().setPosition(this.size.getWidth()/2 - this.player.getShip().getSize().getWidth()/2, this.player.getShip().getSize().getHeight());
		this.aliens.clear();
		addAliens();
	}

	public Player getPlayer()
	{
		return player;
	}
	
	public Shooter getPlayerShip() {
		return this.player.getShip();
	}
	
	public ArrayList<Bullet> getBullets() {
		return this.bullets;
	}
	
	
	public GameBoard(Dimension2D size) {
		Shooter playerShip = new StandardSpaceship(250, 250);
		this.player = new Player(playerShip);
		player.getShip().setPosition(55, 55);
		this.size = size;
		this.addAliens();
		direction = RIGHT;
	}
	
	public void setGUI(GameBoardUI g)
	{
		gui = g;
	}
	
	public GameBoardUI getGUI()
	{
		return gui;
	}
	
	public void setPlayerShip(int type)
	{
		if(type == 1) //standard
		{
			Point2D oldPos = this.getPlayerShip().getPosition();
			Shooter oldShip = player.getShip();
			Shooter newShip = new StandardSpaceship(250, 250);
			newShip.setPosition(player.getShip().getPosition().getX(), player.getShip().getPosition().getY());
			player.setShip(newShip);
			oldShip.setDestroyed(); //////
    		gui.getCarImages().put(newShip, gui.getImage(newShip.getIconLocation()));

    		gui.paintShip(getPlayerShip(), gui.getGraphicsContext2D());
    		
		}
		else //agile
		{

			Point2D oldPos = this.getPlayerShip().getPosition();
			Shooter oldShip = player.getShip();
			Shooter newShip = new AgileSpaceship(250, 250);
			newShip.setPosition(player.getShip().getPosition().getX(), player.getShip().getPosition().getY());
			player.setShip(newShip);
			oldShip.setDestroyed(); //////
    		gui.getCarImages().put(newShip, gui.getImage(newShip.getIconLocation()));

    		gui.paintShip(getPlayerShip(), gui.getGraphicsContext2D());
		}

		this.gui.paint(this.gui.getGraphicsContext2D());
	}
	
	public List<Alien> getAliens() {
		return this.aliens;
	}

    public boolean isRunning() {
        return this.isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    public void playMusic() 
    {
        this.audioPlayer.playBackgroundMusic();
    }

    public void stopMusic() {
        this.audioPlayer.stopBackgroundMusic();
    }
    
    public void startGame() {
    	playMusic();
		this.isRunning = true;
	}
	
    public void stopGame() {
        stopMusic();
        this.isRunning = false;
	}

	public Boolean hasWon() {
		return this.gameWon;
	}	
	public void setWon(boolean toggle) {
		this.gameWon = toggle;
	}	
	
	public void update() {
		moveAliens();
	}

    
    /////////////////////////////////
    
    public void moveAliens() {

    	List<Alien> aliens = getAliens();

		// maximum x and y values a car can have depending on the size of the game board
		int maxX = (int) size.getWidth();
		int maxY = (int) size.getHeight();

		// update the positions of the player car and the autonomous cars
		steps --;
		if(steps == 0)
		{
			if(direction == RIGHT)
			{
				if(alPos > 20)
				{
					direction = LEFT;
				}
				else alPos += 1;
			}
			else if(direction == LEFT)
			{
				if(alPos < -20)
				{
					direction = RIGHT;
				}
				else alPos -= 1;
			}
			for (Shooter alien : aliens) {
				if(direction == LEFT) alien.setPosition(alien.getPosition().getX() - 5 , alien.getPosition().getY());
				else  alien.setPosition(alien.getPosition().getX() +5 , alien.getPosition().getY());
				Random r = new Random();
				int result = r.nextInt(101);
				if(result < 1)
				{
					int bX, bY;
	        		bY = alien.getPosition().getY()-5;
	        		bX = alien.getPosition().getX() + alien.getSize().getWidth()/2  - 30;
	        		Bullet b = new Bullet(false);
	        		b.setGameBoard(this);
	    
	        		b.setIcon("AlienBullet.png");
	        		b.setPosition(bX, bY);
	        		getBulletsToBeAdded().add(b);
            		gui.getCarImages().put(b, gui.getImage(b.getIconLocation()));
            		gui.getGameBoard().getBullets().add(b);
				}
				
			}
			steps = 1;
		}

		//player.getShip().updatePosition(maxX, maxY);

		synchronized(bullets)
		{
			for (Bullet bullet : bullets) 
			{
				if(bullet.isPlayerBullet())
				{
					bullet.setPosition(bullet.getPosition().getX(), bullet.getPosition().getY()+5);
					bullet.evaluateDamage();
				}
				else
				{
					bullet.setPosition(bullet.getPosition().getX(), bullet.getPosition().getY()-5);
					bullet.evaluateDamage();
				}
			}
		}
	}

	public ArrayList<Bullet> getBulletsToBeAdded() {
		return bulletsToBeAdded;
	}

}
