/**
 * @author Jay Doody
 * @version 12/15/2022
 * A Quadratic function in the form 'ax²+bx+c'
 */
package graphcontrol.functions;

import java.util.ArrayList;

public class Quadratic extends Function{
    /**
     * Coefficient a
     */
    private double a;
    /**
     * Coefficient b
     */
    private double b;
    /**
     * Coefficient c
     */
    private double c;

    /**
     * Creates a quadratic function with given domain, and dx
     * @param xmin domain minimum
     * @param xmax domain maximum
     * @param step difference in x when calculating outputs
     */
    public Quadratic(double xmin, double xmax, double step){
        super(xmin, xmax, step);
        functionType = "Quadratic";
        functionOutputs = new ArrayList<>();
    }

    @Override
    public Double solveFor(double x){
        return (a * Math.pow(x, 2) + (b * x) + c);
    }

    @Override
    public void setCoeff(double[] coeff) {
        if(coeff.length < 3){
            System.out.println("ERROR: Number of coefficients less than 3");
            return;
        }
        this.a = coeff[0];
        this.b = coeff[1];
        this.c = coeff[2];
        computeValues();
    }

    @Override
    public double[] getCoeff() {
        return new double[] {this.a, this.b, this.c};
    }

    @Override
    public String toString(){
        return this.a + "x²+" + this.b + "x+" + this.c;
    }
}
