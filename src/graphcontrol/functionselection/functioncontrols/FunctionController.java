package graphcontrol.functionselection.functioncontrols;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public abstract class FunctionController extends FlowPane{
    protected ArrayList<TextField> coeffInputs;
    protected ArrayList<Label> coeffLabels;
    protected final int textFieldSize;
    public FunctionController(){
        this.textFieldSize = 4;
        this.coeffInputs = new ArrayList<>();
        this.coeffLabels = new ArrayList<>();
    }

    protected void setTextFieldSize(){
        for(TextField tf : coeffInputs){
            tf.setPrefColumnCount(this.textFieldSize);
        }
    }

}
