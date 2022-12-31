/**
 * @author Jay Doody
 * @version 12/15/2022
 * A function plot containing output from a function fitted on a coordinate plane
 */
package graph.fplot;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Plot extends Pane {
    /**
     * Function outputs
     */
    private final ArrayList<Point> points;

    /**
     * Creates a plot with no points
     */
    public Plot(){
        this.points = new ArrayList<>();
    }


    /**
     * {@return the points list to be filled with coordinates from the coordinate plane}
     */
    public ArrayList<Point> getPoints(){
        return this.points;
    }

    /**
     * Add the points to the plot for display on coordinate plane
     */
    public void addPointsToPlot(){
        for(Point p : points){
            getChildren().add(p);
        }
    }

    /**
     * Move the plot horizontally by dx
     * @param dx how far along x-axis to move the plot
     */
    public void transformX(double dx){
        this.setLayoutX(this.getLayoutX() + dx);
    }

    /**
     * Move the plot Vertically by dy
     * @param dy how far along the y-axis to move the plot
     */
    public void transformY(double dy){
        this.setLayoutY(this.getLayoutY() + dy);
    }
}
