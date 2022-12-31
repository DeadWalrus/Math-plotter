/**
 * @author Jay Doody
 * @version 12/15/2022
 * Defines behaviors for handling a function
 */
package interfaces;

import graphcontrol.functions.Function;

public interface FunctionControl {
    /**
     * @return the current function
     */
    Function getFunction();

    /**
     * Sets the current function
     * @param func function to set current function to
     */
    void setFunction(Function func);
}
