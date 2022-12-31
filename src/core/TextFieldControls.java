/**
 * @author Jay Doody
 * @version 12/15/2022
 * Collection of methods for working with TextFields across the application
 */
package core;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class TextFieldControls {
    /**
     * Returns double value from given text field, and shows error in the event of a NumberFormatException
     * @param tfValue text field to get value from
     * @return the double representation of the value
     */
    public static double getTextFieldDouble(TextField tfValue){
        double val;
        try{
            val = Double.parseDouble(tfValue.getText());
        } catch(NumberFormatException e){
            tfValue.setStyle("-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "Invalid value for coefficient", ButtonType.OK).show();
            return 0;
        }
        tfValue.setStyle("-fx-border-color: none");
        return val;
    }
}
