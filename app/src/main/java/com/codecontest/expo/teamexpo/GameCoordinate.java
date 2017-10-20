package com.codecontest.expo.teamexpo;

public class GameCoordinate {
	
	public int TIME_NOT_SET = -1;
	public double x;
	public double y;

	private Integer timestamp;
	
	public GameCoordinate(double x, double y, int timestamp) {
		this.x = x;
		this.y = y;
		this.timestamp = timestamp;
	}

	public GameCoordinate(double x, double y) {
		this.x = x;
		this.y = y;
		this.timestamp = null;;
	}

	public int getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	
	public boolean isValidTime() {
		return timestamp > TIME_NOT_SET;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "GameCoordinate [x=" + x + ", y=" + y + "]";
	}
	
	
	
}
