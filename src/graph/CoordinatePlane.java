/**
 * @author Jay Doody
 * @version 12/15/2022
 * A coordinate plane. Used for plotting the points computed by a function
 */
package graph;
import graph.fplot.Plot;
import graph.fplot.Point;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CoordinatePlane extends Pane{
    /**
     * X and Y axis
     */
    private final Axes axes;
    /**
     * Coordinate plane width
     */
    private final int WIDTH = 600;
    /**
     * Coordinate plane height
     */
    private final int HEIGHT = 600;
    /**
     * Axis width
     */
    private final int AXIS_WIDTH = WIDTH - 20;
    /**
     * Axis height
     */
    private final int AXIS_HEIGHT = HEIGHT - 20;
    /**
     * Distance (in pixels) between x axis ticks
     */
    private final double XAXES_OFFSET;
    /**
     * Distance (in pixels) between y axis ticks
     */
    private final double YAXES_OFFSET;
    /**
     * A collection of points (coordinates)
     */
    private Plot functionPlot;

    /**
     * Creates a new Coordinate plane with default values for the axis min and max.
     */
    public CoordinatePlane(){
        this.setPrefSize(this.WIDTH, this.HEIGHT);
        this.setMinSize(300, 300);
        this.axes = new Axes(-5, 5, 1, -25, 25, 5, this.AXIS_WIDTH, this.AXIS_HEIGHT);
        this.XAXES_OFFSET = this.getXOffset();
        this.YAXES_OFFSET = this.getYOffset();
        this.setStyle("-fx-background-color: darkgrey");
        getChildren().add(this.axes);
        addListeners();
        positionNumberAxes();
    }

    /**
     * Adds the function outputs to the plot, fits them to the axis, and displays them on the plane.
     * @param functionValues x, and y values given by function
     */
    public void plotPoints(ArrayList<ArrayList<Double>>functionValues){
        getChildren().remove(this.functionPlot);
        this.functionPlot = new Plot();
        ArrayList<Point> points = this.functionPlot.getPoints();

        double centerX = this.axes.getLayoutX() + this.axes.getWidth()/2;
        double centerY = this.axes.getLayoutY() + this.axes.getHeight()/2;

        for(ArrayList<Double> coord : functionValues){
            Point p = new Point(coord.get(0), coord.get(1) * -1, 1);
            p.setLayoutX((centerX + this.XAXES_OFFSET * p.getXCoord()));
            p.setLayoutY((centerY + this.YAXES_OFFSET * p.getYCoord()));
            points.add(p);
        }
        this.functionPlot.addPointsToPlot();
        getChildren().add(functionPlot);
    }

    /**
     * Clears the current plot from the coordinate plane
     */
    public void clearPlot(){
        getChildren().remove(this.functionPlot);
    }

    /**
     * {@return the number of pixels between each tick}
     */
    private double getXOffset(){
        double totalAxisLength = this.axes.getXMax() - this.axes.getXMin();
        return (this.axes.getWidth()/totalAxisLength);
    }

    /**
     * {@return the number of pixels between each tick}
     */
    private double getYOffset(){
        double totalAxisLength = this.axes.getYMax() - this.axes.getYMin();
        return (this.axes.getHeight()/totalAxisLength);
    }

    /**
     * Binds the axes to the center of the coordinate plane
     */
    private void positionNumberAxes() {
        this.axes.layoutYProperty().bind(this.heightProperty().divide(2).subtract(this.axes.getHeight()/2));
        this.axes.layoutXProperty().bind(this.widthProperty().divide(2).subtract(this.axes.getWidth()/2));
    }

    /**
     * Adds changeListeners to keep the plot centered on the axis when coordinate plane dimensions change
     */
    private void addListeners(){
        this.widthProperty().addListener((observableValue, oldWidth, newWidth) -> {
            if(oldWidth.doubleValue() > 0 && this.functionPlot != null){
                    functionPlot.transformX((newWidth.doubleValue() - oldWidth.doubleValue()) / 2);
            }
        });

        this.heightProperty().addListener((observableValue, oldHeight, newHeight) -> {
            if(oldHeight.doubleValue() > 0 && this.functionPlot != null){
                functionPlot.transformY((newHeight.doubleValue() - oldHeight.doubleValue()) / 2);
            }
        });
    }


}
