/**
 * @author Jay Doody
 * @version 12/15/2022
 * A sine function in the form 'Asin(Bx) + k'
 */
package graphcontrol.functions;

import java.util.ArrayList;

public class Sine extends Function{
    /**
     * Amplitude of the function
     */
    private double amplitude;
    /**
     * Frequency of the function
     */
    private double frequency;
    /**
     * Midline of the function
     */
    private double k;

    /**
     * Creates a new Sine function with given domain, and dx
     * @param xmin domain minimum
     * @param xmax domain maximum
     * @param step difference in x when calculating outputs
     */
    public Sine(double xmin, double xmax, double step){
        super(xmin, xmax, step);
        functionType = "Sine";
        functionOutputs = new ArrayList<>();
    }


    @Override
    public Double solveFor(double x) {
        return (this.amplitude * Math.sin(this.frequency * x)) + this.k;
    }

    @Override
    public void setCoeff(double[] coeff) {
        if(coeff.length < 3){
            System.out.println("ERROR: Number of coefficients less than 3");
        }
        this.amplitude = coeff[0];
        this.frequency = coeff[1];
        this.k = coeff[2];
        computeValues();
    }

    @Override
    public double[] getCoeff(){
        return new double[] {this.amplitude, this.frequency, this.k};
    }

    @Override
    public String toString(){
        return this.amplitude + "sin(" + this.frequency + "x) + " + this.k;
    }
}
