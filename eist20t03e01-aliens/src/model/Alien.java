package model;

public class Alien extends Shooter{
	
	public Alien(int maxX, int maxY) {
		super(maxX, maxY);
	}

	protected boolean isDestroyed = false;
	
	
	public void shoot() {
		///////////
	}

	
	public void setDestroyed() {
		this.isDestroyed = true;
	}
	
	public boolean isDestroyed() {
		return this.isDestroyed;
	}

}
