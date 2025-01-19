/**
 * @author Jay Doody
 * @version 12/15/2022
 * A function table for inputting values, and displaying function output
 */
package graph;

import graphcontrol.functions.Function;
import core.TextFieldControls;
import interfaces.FunctionControl;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FunctionTable extends VBox implements FunctionControl {
    /**
     * The function to input values for
     */
    private Function function;

    /**
     * Label for displaying current function at top of table
     */
    private final Label lTitle;

    /**
     * Labels for x and y locations on table
     */
    private final Group xyLabels;

    /**
     * Label to tell user where x value is outputted
     */
    private final Label lXLabel;

    /**
     * Label to tell user where y value is outputted
     */
    private final Label lYLabel;

    /**
     * Text field for inputting x values
     */
    private final TextField tfXValue;

    /**
     * Content container to enable scrolling when function table size is large
     */
    private final ScrollPane functionOutputParentPane;

    /**
     * Display for function outputs on table
     */
    private final VBox functionOutputValues;

    /**
     * Create a new function table with given function, and default layout. Update content to reflect function properties.
     *
     * @param f function to input values for
     */
    public FunctionTable(Function f){
        this.setPrefWidth(200);
        this.function = f;
        this.lTitle = new Label(this.function.toString());
        this.lXLabel = new Label("X: ");
        this.lYLabel = new Label("\tY: ");
        this.tfXValue = new TextField("input");
        this.tfXValue.setPrefColumnCount(3);
        this.setStyle("-fx-background-color: lightgray");

        this.lYLabel.setLayoutX(this.getWidth()/2);
        this.xyLabels = new Group(this.lXLabel, this.lYLabel);
        getChildren().addAll(this.lTitle, this.tfXValue, this.xyLabels);

        this.functionOutputParentPane = new ScrollPane();
        getChildren().add(this.functionOutputParentPane);
        this.functionOutputValues = new VBox();
        this.functionOutputParentPane.setContent(this.functionOutputValues);

        this.tfXValue.setOnAction(e -> this.outputXY());
    }

    /**
     * Clear the table values
     */
    public void clearValues(){
        this.functionOutputValues.getChildren().clear();
    }

    /**
     * Solve the function for x value in text field, and display the input and output of the function on the table
     */
    private void outputXY(){
        System.out.println(this.function.solveFor(TextFieldControls.getTextFieldDouble(this.tfXValue)));
        Label functionX = new Label("" + TextFieldControls.getTextFieldDouble(this.tfXValue));
        Label functionY = new Label(String.format(" %.2f", this.function.solveFor(TextFieldControls.getTextFieldDouble(this.tfXValue))));
        this.functionOutputValues.getChildren().add(0, new HBox(functionX, functionY));
    }

    /**
     * Set the title of the table
     * @param title string representation of the function to be used
     */
    public void setTitle(String title){
        this.lTitle.setText(title);
    }

    @Override
    public Function getFunction() {
        return this.function;
    }

    @Override
    public void setFunction(Function func) {
        this.function = func;
    }
}
