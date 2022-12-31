/**
 * @author Jay Doody
 * @version 12/15/2022
 * Provides definitions for common properties and behaviors of a function
 */
package graphcontrol.functions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public abstract class Function implements Serializable {
    /**
     * Domain minimum
     */
    protected double XMIN;
    /**
     * Domain maximum
     */
    protected double XMAX;
    /**
     * Domain step when calculating outputs
     */
    protected double dx;
    /**
     * Outputs of the function in {x,y} format
     */
    protected ArrayList<ArrayList<Double>> functionOutputs;
    /**
     * When the function was created
     */
    private final Date timeCreated;
    /**
     * Type of the function
     */
    protected String functionType;

    /**
     * Creates a new function with xmin, xmax, and dx
     * @param xmin minimum x domain
     * @param xmax maximum x domain
     * @param step difference in x when calculating outputs
     */
    public Function(double xmin, double xmax, double step){
        this.timeCreated = new Date();
        this.XMIN = xmin;
        this.XMAX = xmax;
        this.dx = step;
    }

    /**
     * Calculates a value from a given input
     * @param x input of the function
     * @return the output of the function
     */
    public abstract Double solveFor(double x);

    /**
     * Sets the coefficients of the function
     * @param coeff the coefficients of the function
     */
    public abstract void setCoeff(double[] coeff);

    /**
     * {@return the coefficients of the function}
     */
    public abstract double[] getCoeff();

    /**
     * {@return the function outputs}
     */
    public ArrayList<ArrayList<Double>> getFunctionValues(){
        return this.functionOutputs;
    }

    /**
     * {@return a string representation of the function}
     */
    public abstract String toString();

    /**
     * {@return the date and time the function was created}
     */
    public Date getTimeCreated(){
        return this.timeCreated;
    }

    /**
     * {@return the function type}
     */
    public String getFunctionType(){
        return this.functionType;
    }

    /**
     * Compute the function values, and store them in functionOutputs
     */
    protected void computeValues() {
        if(this.functionOutputs.size() != 0){
            functionOutputs.clear();
        }
        for(double x = this.XMIN; x <= this.XMAX; x+=this.dx){
            ArrayList<Double> xandy = new ArrayList<>();
            xandy.add(x);
            xandy.add(this.solveFor(x));
            functionOutputs.add(xandy);
        }
        outputXAndY();
    }

    /**
     * Print the function outputs to standard output
     */
    private void outputXAndY(){
        System.out.println(this.functionOutputs);
    }
}
