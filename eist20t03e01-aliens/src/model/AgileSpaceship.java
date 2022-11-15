package model;

public class AgileSpaceship extends Shooter {

	public AgileSpaceship(int maxX, int maxY) {
		super(maxX, maxY);
		setIcon("Agile.png");
		cooldown = 0.5;
	}

	@Override
	public void shoot() {
		
	}

	public void move() {

	}

	@Override
	public void setDestroyed() {
		
	}

}
