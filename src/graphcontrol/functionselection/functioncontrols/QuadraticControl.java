/**
 * @author Jay Doody
 * @version 12/15/2022
 * A controller for graphing a quadratic function
 */
package graphcontrol.functionselection.functioncontrols;


import core.TextFieldControls;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class QuadraticControl extends FunctionController{

    /**
     * Text field for entering the 'a' coefficient
     */
    private final TextField tfAValue;

    /**
     * Text field for entering the 'b' coefficient
     */
    private final TextField tfBValue;

    /**
     * Text field for entering the 'c' coefficient
     */
    private final TextField tfCValue;

    /**
     * Text field pref column count
     */
    private final int textFieldSize = 4;

    /**
     * Creates a new QuadraticControl with default layout and values for text fields, as well as labels for each text field
     */
    public QuadraticControl(){
        this.setHgap(5);
        this.setPadding(new Insets(5, 5, 5, 5));
        Label lAValue = new Label("a");
        Label lBValue = new Label("b");
        Label lCValue = new Label("c");
        this.tfAValue = new TextField("1");
        this.tfBValue = new TextField("0");
        this.tfCValue = new TextField("0");
        this.setTextFieldSize();
        getChildren().addAll(lAValue, tfAValue, lBValue, tfBValue, lCValue, tfCValue);
    }

    /**
     * Sets the text field sizes
     */
    protected void setTextFieldSize(){
        this.tfAValue.setPrefColumnCount(this.textFieldSize);
        this.tfBValue.setPrefColumnCount(this.textFieldSize);
        this.tfCValue.setPrefColumnCount(this.textFieldSize);
    }


    /**
     * Sets the text field text with given values
     * @param tfAText value for 'a' coefficient
     * @param tfBText value for 'b' coefficient
     * @param tfCText value for 'c' coefficient
     */
    public void setTextFieldText(String tfAText, String tfBText, String tfCText){
        this.tfAValue.setText(tfAText);
        this.tfBValue.setText(tfBText);
        this.tfCValue.setText(tfCText);
    }

    /**
     * {@return the function parameters from text field values}
     */
    public double[] getFunctionParameters(){
        return new double[]{this.getAValue(), this.getBValue(), this.getCValue()};
    }

    /**
     * {@return the value from 'a' text field}
     */
    private double getAValue(){
        return TextFieldControls.getTextFieldDouble(this.tfAValue);
    }

    /**
     * {@return the value from 'b' text field}
     */
    private double getBValue(){
        return TextFieldControls.getTextFieldDouble(this.tfBValue);
    }

    /**
     * {@return the value from 'c' text field}
     */
    private double getCValue(){
        return TextFieldControls.getTextFieldDouble(this.tfCValue);
    }
}
