package controller;

import model.Shooter;


public class Player {
	
	private Shooter playerShip;
	private int pScore = 0;
	private int lifeCounter = 3;

	/**
	 * Constructor that allocates a car to the player
	 * 
	 * @param car the car that should be the player's car
	 */
	public Player(Shooter car) {
		this.playerShip = car;
	}

	/**
	 * @param car the player's new car
	 */
	public void setShip(Shooter car) {
		this.playerShip = car;
	}

	/**
	 * @return The player's current car
	 */
	public Shooter getShip() {
		return this.playerShip;
	}

	public int getScore() {
		return pScore;
	}

	public void setScore(int pScore) {
		this.pScore = pScore;
	}

	public int getLifeCounter() {
		return lifeCounter;
	}

	public void setLifeCounter(int lifeCounter) {
		this.lifeCounter = lifeCounter;
	}

}
