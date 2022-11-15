package model;

import controller.Dimension2D;
import controller.Point2D;

public abstract class Shooter {
	
	
	protected double cooldown = 2; // default firing cooldown 

    private String iconLocation = "Alien.png";
	protected Point2D position;
	protected Dimension2D size = new Dimension2D(50, 25);
	
	private boolean isDestroyed = false;

	abstract void shoot();
	public abstract void setDestroyed();
	

	public Shooter(int maxX, int maxY) {
		int carX = (int) (Math.random() * (maxX - getSize().getWidth()));
		int carY = (int) (Math.random() * (maxY - getSize().getHeight()));
		this.position = new Point2D(carX, carY);
		if (carY < getSize().getHeight()) {
			this.position = new Point2D(carX, getSize().getHeight());
		}
		this.isDestroyed = false;
	}
	
	public double getCooldown()
	{
		return cooldown;
	}
	
	/**
	 * The car's position is reset to the top left corner of the game. The speed
	 * is set to 5 and the directions points to 90 degrees.
	 * 
	 * @param maxY Top left corner of the game board
	 */
	public void reset(int maxY) {
		this.position = new Point2D(0, maxY);
		this.isDestroyed = false;
	}

    public String getIconLocation() {
        return this.iconLocation;
    }

    /**
     * Sets the image of the car
     *
     * @param iconLocation path of the image file
     * @throws IllegalArgumentException
     */
    protected void setIcon(String iconLocation) throws IllegalArgumentException {
        if (iconLocation == null) {
            throw new IllegalArgumentException("The chassis image of a car connot be null.");
        }
        this.iconLocation = iconLocation;
    }

	public Point2D getPosition() {
		return this.position;
	}

	/**
	 * Sets the car's position
	 * 
	 * @param x the position along the x-axes
	 * @param y the position along the y-axes
	 */
	public void setPosition(int x, int y) {
		this.position = new Point2D(x, y);
	}

	public Dimension2D getSize() {
		return this.size;
	}
	
}
