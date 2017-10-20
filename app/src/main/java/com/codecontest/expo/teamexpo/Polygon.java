package com.codecontest.expo.teamexpo;

public class Polygon { 
    private int N;        // number of points in the polygon
    private Point[] a;    // the points, setting points[0] = points[N]
   
    // default buffer = 4
    public Polygon() {
        N = 0;
        a = new Point[4];
    }


    // double size of array
    private void resize() {
        Point[] temp = new Point[2*N+1];
        for (int i = 0; i <= N; i++) temp[i] = a[i];
        a = temp;
    }

    // return size
    public int size() { return N; }

   
    // add point p to end of polygon
    public void add(Point p) {
        if (N >= a.length - 1) resize();   // resize array if needed
        a[N++] = p;                        // add point
        a[N] = a[0];                       // close polygon
    }

   
    // does this Polygon contain the point p?
    // if p is on boundary then 0 or 1 is returned, and p is in exactly one point of every partition of plane
    // Reference: http://exaflop.org/docs/cgafaq/cga2.html
    public boolean contains2(Point p) {
        int crossings = 0;
        for (int i = 0; i < N; i++) {
            int j = i + 1;
            boolean cond1 = (a[i].y <= p.y) && (p.y < a[j].y);
            boolean cond2 = (a[j].y <= p.y) && (p.y < a[i].y);
            if (cond1 || cond2) {
                // need to cast to double
                if (p.x < (a[j].x - a[i].x) * (p.y - a[i].y) / (a[j].y - a[i].y) + a[i].x)
                    crossings++;
            }
        }
        if (crossings % 2 == 1) return true;
        else                    return false; 
    }

    // does this Polygon contain the point p?
    // Reference: http://softsurfer.com/Archive/algorithm_0103/algorithm_0103.htm
    public boolean contains(Point p) {
        int winding = 0;
        for (int i = 0; i < N; i++) {
            int ccw = ccw(a[i], a[i+1], p);
            if (a[i+1].y >  p.y && p.y >= a[i].y)  // upward crossing
                if (ccw == +1) winding++;
            if (a[i+1].y <= p.y && p.y <  a[i].y)  // downward crossing
                if (ccw == -1) winding--;
        }
        return winding != 0;
    }


    // is a->b->c a counter-clockwise turn?
    // +1 if counter-clockwise, -1 if clockwise, 0 if collinear
    public int ccw(Point a, Point b, Point c) {
        // return a.x*b.y - a.y*b.x + a.y*c.x - a.x*c.y + b.x*c.y - b.y*c.x;
        double area2 = (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
        if      (area2 < 0) return -1;
        else if (area2 > 0) return +1;
        else                return  0;
    }
}
