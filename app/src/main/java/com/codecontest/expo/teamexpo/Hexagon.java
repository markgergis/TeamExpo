package com.codecontest.expo.teamexpo;

public class Hexagon {

	private int id;

	private GameCoordinate center = new GameCoordinate(0, 0);

	private int guardPeriod;

    private int expiryTime;

    private int passingDuration;

    private static final long serialVersionUID = 1L;

    public static final int SIDES = 6;

    private GameCoordinate[] points;

    private int radius;

    private int rotation = 90;

    private int xIndex;

    private int yIndex;

    private Polygon polygon;

	public final static int HEXAGON_RADIUS = 30;

    private Hexagon() {
    	  	points = new GameCoordinate[SIDES];
    	  	rotation = 90;
    	  	radius = HEXAGON_RADIUS;
    }


    private Hexagon(GameCoordinate center, int radius, int id, int xIndex, int yIndex, int guardPeriod, int expiryTime, int passingDuration) {
        this();
    		this.center = center;
        this.radius = radius;
        this.id = id;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.guardPeriod = guardPeriod;
        this.expiryTime = expiryTime;
        this.passingDuration = passingDuration;
        updatePoints();
    }

    public Hexagon(double x, double y, int radius, int id, int guardPeriod, int expiryPeriod, int passingDuration) {
        this(new GameCoordinate(x, y), radius, id, 0, 0, guardPeriod, expiryPeriod, passingDuration);
    }

    public Hexagon(double x, double y, int radius, int id, int xIndex, int yIndex, int guardPeriod, int expiryPeriod, int passingDuration) {
        this(new GameCoordinate(x, y), radius, id, xIndex, yIndex, guardPeriod, expiryPeriod, passingDuration);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        updatePoints();
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;

        updatePoints();
    }

    public void setCenter(GameCoordinate center) {
        this.center = center;

        updatePoints();
    }

    public void setCenter(double x, double y) {
        setCenter(new GameCoordinate(x, y));
    }

    public GameCoordinate getCenter() {
    		return center;
    }

    private GameCoordinate findPoint(int p) {
    		double cosine, sine;
    		/**
	    		p: 0.0 xoff: -1.8369701987210297E-16 yoff: -1.0
			p: 1.0 xoff: 0.8660254037844384 yoff: -0.5000000000000004
			p: 2.0 xoff: 0.866025403784439 yoff: 0.4999999999999993
			p: 3.0 xoff: 3.061616997868383E-16 yoff: 1.0
			p: 4.0 xoff: -0.8660254037844388 yoff: 0.4999999999999998
			p: 5.0 xoff: -0.8660254037844392 yoff: -0.49999999999999917
    		 */

    		switch (p) {
    		case 0:
    			sine = -1.0;
    			cosine = 0;
    			break;
    		case 1:
    			sine = -0.5;
    			cosine = 0.866025403784438d;
    			break;
    		case 2:
    			sine = 0.5d;
    			cosine = 0.866025403784438;
    			break;
    		case 3:
    			sine = 1.0;
    			cosine = 0;
    			break;
    		case 4:
    			sine = 0.5;
    			cosine = -0.866025403784438;
    			break;
    		case 5:
    			sine = -0.5;
    			cosine = -0.866025403784438;
    			break;
    		default:
    			throw new IllegalArgumentException();
    		}
    		double x = center.x + cosine * radius;
        double y = center.y + sine * radius;

        return new GameCoordinate(x, y);
    }

    protected void updatePoints() {
        for (int p = 0; p < SIDES; p++) {
            GameCoordinate point = findPoint(p);
            points[p] = point;
        }

        polygon = new Polygon();
        polygon.add(new Point(points[0].x, points[0].y));
        polygon.add(new Point(points[1].x, points[1].y));
        polygon.add(new Point(points[2].x, points[2].y));
        polygon.add(new Point(points[3].x, points[3].y));
        polygon.add(new Point(points[4].x, points[4].y));
        polygon.add(new Point(points[5].x, points[5].y));

    }

    public double distanceTo(Hexagon otherHexagon) {
		return Math.sqrt(Math.pow(center.getX() - otherHexagon.getCenter().getX(), 2) + Math.pow(center.getY() - otherHexagon.getCenter().getY(), 2));
    }

    public boolean isConnected(Hexagon otherHexagon) {
    		return this.distanceTo(otherHexagon) < (2 * HEXAGON_RADIUS);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return ""+id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	public int getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(int expiryTime) {
		this.expiryTime = expiryTime;
	}

	public int getGuardPeriod() {
		return guardPeriod;
	}

	public GameCoordinate[] getPoints() {
		return points;
	}

	public int getPassingDuration() {
		return passingDuration;
	}

	public void setPassingDuration(int passingDuration) {
		this.passingDuration = passingDuration;
	}

	public boolean contains(GameCoordinate coordinate) {

		if(polygon == null) {
			updatePoints();
		}

		return polygon.contains(new Point(coordinate.x, coordinate.y));
	}
}