/**
 * @author Jay Doody
 * @version 12/15/2022
 * A controller for graphing a sine function
 */
package graphcontrol.functionselection.functioncontrols;

import core.TextFieldControls;
import javafx.geometry.Insets;
import javafx.scene.control.*;


public class SineControl extends FunctionController{
    /**
     * Text field for entering the amplitude of the function
     */
    private TextField tfAmplitude;
    /**
     * Text field for entering the frequency of the function
     */
    private TextField tfFrequency;

    /**
     * Text field for entering the midline of the function
     */
    private TextField tfKMidline;

    /**
     * Text field pref column count
     */
    private int textFieldSize = 4;

    /**
     * Creates a new SineControl with default layout and values for text fields, as well as labels for each text field
     */
    public SineControl(){
        this.setHgap(5);
        this.setPadding(new Insets(5, 5, 5, 5));
        Label lAValue = new Label("Amplitude");
        Label lBValue = new Label("Frequency");
        Label lKValue = new Label("Midline");
        this.tfAmplitude = new TextField("1");
        this.tfFrequency = new TextField("1");
        this.tfKMidline = new TextField("0");
        this.setTextFieldSize();

        getChildren().addAll(lAValue, this.tfAmplitude, lBValue, this.tfFrequency, lKValue, this.tfKMidline);
    }

    /**
     * Set the text fields pref column count
     */
    protected void setTextFieldSize(){
        this.tfAmplitude.setPrefColumnCount(this.textFieldSize);
        this.tfFrequency.setPrefColumnCount(this.textFieldSize);
        this.tfKMidline.setPrefColumnCount(this.textFieldSize);
    }

    /**
     * Sets the text field text with given values
     * @param amplitude value for amplitude text field
     * @param frequency value for frequency text field
     * @param k value for midline text field
     */
    public void setTextFieldText(String amplitude, String frequency, String k) {
        this.tfAmplitude.setText(amplitude);
        this.tfFrequency.setText(frequency);
        this.tfKMidline.setText(k);
    }

    /**
     * {@return the function parameters from text field values}
     */
    public double[] getFunctionParameters() {
        return new double[]{this.getAmplitude(), this.getFrequency(), this.getMidline()};
    }

    /**
     * {@return the amplitude of the function}
     */
    private double getAmplitude() {
        return TextFieldControls.getTextFieldDouble(this.tfAmplitude);
    }

    /**
     * {@return the frequency of the function}
     */
    private double getFrequency() {
        return TextFieldControls.getTextFieldDouble(this.tfFrequency);
    }

    /**
     * {@return the midline of the function}
     */
    private double getMidline() {
        return TextFieldControls.getTextFieldDouble(this.tfKMidline);
    }
}
