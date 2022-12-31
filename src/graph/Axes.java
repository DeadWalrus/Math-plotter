/**
 * @author Jay Doody
 * @version 12/15/2022
 * An x and y-axis for a coordinate plane
 */
package graph;

import graph.fplot.Point;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.chart.NumberAxis;
import javafx.beans.binding.Bindings;

public class Axes extends Group {

    /**
     * Minimum x value of the axes
     */
    private final double XMIN;

    /**
     * Maximum x value of the axes
     */
    private final double XMAX;

    /**
     * The step between the number displays on the x-axis
     */
    private final double XTICK;

    /**
     * Minimum y value of the axis
     */
    private final double YMIN;

    /**
     * Maximum y value of the axis
     */
    private final double YMAX;

    /**
     * The step between the number displays on the y-axis
     */
    private final double YTICK;

    /**
     * The Width of the axis
     */
    private final double WIDTH;

    /**
     * The height of the axis
     */
    private final double HEIGHT;

    /**
     * The x-axis
     */
    private NumberAxis x;

    /**
     * The y-axis
     */
    private NumberAxis y;

    /**
     * Creates a new Axes with given values
     * @param xmin minimum x value
     * @param xmax maximum x value
     * @param xTick step between x values
     * @param ymin minimum y value
     * @param ymax maximum y value
     * @param yTick step between y value
     * @param width width of the axis
     * @param height height of the axis
     */
    public Axes(double xmin, double xmax, double xTick, double ymin, double ymax, double yTick, double width, double height){
        this.XMIN = xmin;
        this.XMAX = xmax;
        this.XTICK = xTick;
        this.YMIN = ymin;
        this.YMAX = ymax;
        this.YTICK = yTick;
        this.WIDTH = width;
        this.HEIGHT = height;
        initAxes();
        getChildren().addAll(this.x, this.y);
    }

    /**
     * Initialize the axis with data fields, and configure the x and y axes
     */
    private void initAxes(){
        this.x = new NumberAxis(this.XMIN, this.XMAX, this.XTICK);
        this.y = new NumberAxis(this.YMIN, this.YMAX, this.YTICK);
        this.x.setPrefWidth(this.WIDTH);
        this.x.setSide(Side.BOTTOM);
        this.x.setLayoutY(this.HEIGHT/2.0);
        this.y.setSide(Side.LEFT);
        this.y.setPrefHeight(this.HEIGHT);
        this.y.layoutXProperty().bind(Bindings.subtract((this.WIDTH/2), this.y.widthProperty()));
    }

    /**
     * {@return the minimum x value}
     */
    public double getXMin(){
        return this.XMIN;
    }

    /**
     * {@return the maximum x value}
     */
    public double getXMax(){
        return this.XMAX;
    }

    /**
     * {@return the minimum y value}
     */
    public double getYMin(){
        return this.YMIN;
    }

    /**
     * {@return the maximum y value}
     */
    public double getYMax(){
        return this.YMAX;
    }

    /**
     * {@return the axis width}
     */
    public double getWidth(){
        return this.WIDTH;
    }

    /**
     * {@return the axis height}
     */
    public double getHeight(){
        return this.HEIGHT;
    }

}
