
package com.mycompany.optimazition_project.models;


public class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
        //x1-x2 karesi + y2-y1 karesi then bunun karekökü

    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    
    

}
