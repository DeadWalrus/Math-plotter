/**
 * @author Jay Doody
 * @version 12/15/2022
 * A point for a function plot
 */
package graph.fplot;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Point extends Circle {

    /**
     * X coordinate of the point on the coordinate plane
     */
    private final double XCOORD;

    /**
     * Y coordinate of the point on the coordinate plane
     */
    private final double YCOORD;

    /**
     * Radius of the point
     */
    private final double SIZE;

    /**
     * Color of the point
     */
    private Color COLOR;

    /**
     * Create a point at x,y
     * @param x x coordinate of point
     * @param y y coordinate of point
     */
    public Point(double x, double y){
        super(1);
        this.XCOORD = x;
        this.YCOORD = y;
        this.SIZE = 1;
        this.COLOR = Color.BLUE;
        applyProperties();
    }

    /**
     * Create a point at x,y with a given size
     * @param x x coordinate of point
     * @param y y coordinate of point
     * @param size radius of point
     */
    public Point(double x, double y, double size){
        super(size);
        this.XCOORD = x;
        this.YCOORD = y;
        this.SIZE = size;
        this.COLOR = Color.BLUE;
        applyProperties();
    }

    /**
     * Create a point at x,y with given size and color
     * @param x x coordinate of point
     * @param y y coordinate of point
     * @param size radius of point
     * @param color color of point
     */
    public Point(double x, double y, double size, Color color){
        this(x, y, size);
        this.COLOR = color;
        applyProperties();
    }

    /**
     * Apply the field values to the point
     */
    private void applyProperties(){
        this.setLayoutX(this.XCOORD);
        this.setLayoutY(this.YCOORD);
        this.setFill(this.COLOR);
        this.setStroke(null);
    }

    /**
     * {@return the point x coordinate}
     */
    public double getXCoord(){
        return this.XCOORD;
    }

    /**
     * {@return the point y coordinate}
     */
    public double getYCoord(){
        return this.YCOORD;
    }
}
