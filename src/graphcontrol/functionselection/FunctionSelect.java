/**
 * @author Jay Doody
 * @version 12/15/2022
 * A function selection controller containing either a QuadraticControl, or a SineControl.
 * Handles communication between function controllers, and functions, as well as writing functions to files.
 */
package graphcontrol.functionselection;

import core.Main;
import graph.CoordinatePlane;
import graph.FunctionTable;
import graphcontrol.functions.Function;
import graphcontrol.functions.Quadratic;
import graphcontrol.functions.Sine;
import graphcontrol.functionselection.functioncontrols.*;
import interfaces.FunctionControl;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.Arrays;
import java.util.UUID;


public class FunctionSelect extends GridPane implements FunctionControl {

    /**
     * The current function
     */
    private Function function;

    /**
     * Coordinate plane for plotting function values
     */
    private CoordinatePlane coordinatePlane;

    /**
     * Function table for solving the function for user inputted values
     */
    private FunctionTable functionTable;

    /**
     * Dropdown for selecting the desired function
     */
    private final ComboBox<String> cbfuncSel;

    /**
     * Button for updating function coefficients, and writing function to file
     */
    private Button btUpdate;

    /**
     * Button for displaying the function history
     */
    private Button btFunctionHistory;

    /**
     * Controller for quadratic functions
     */
    private QuadraticControl quadControl;

    /**
     * Controller for sine functions
     */
    private SineControl sineControl;

    /**
     * The current function type
     */
    private String currentFunctionType;

    /**
     * Style of this pane
     */
    private final String paneStyle = "-fx-background-color: lightblue";

    /**
     * Stage for displaying the function history
     */
    private FunctionHistoryViewer fhv;

    /**
     * File to output the function to when update button is pressed
     */
    private File fFunctionDirectory;

    /**
     * Creates a new function selection controller with default layout
     */
    public FunctionSelect(){
        this.setStyle(this.paneStyle);

        this.cbfuncSel = new ComboBox<>();

        initComboBox();
        initButtons();

        Main.getStage().setTitle("ax²+bx+c");
        this.function = new Quadratic(-5, 5, 0.01);
        this.currentFunctionType = this.function.getFunctionType();
        this.fFunctionDirectory = new File("FunctionHistory/");
        System.out.println(Arrays.toString(this.fFunctionDirectory.list()));
        this.initControls();
        this.setHgap(5);
        this.add(cbfuncSel, 0, 0);
        this.add(quadControl, 1, 0);
        this.add(btUpdate, 2, 0);
        this.add(btFunctionHistory, 3, 0);
    }

    /**
     * Sets the coordinate plane to the given CoordinatePlane
     * @param c CoordinatePlane to plot function values
     */
    public void setCoordPlane(CoordinatePlane c){
        this.coordinatePlane = c;
    }

    /**
     * Sets the function table to the given FunctionTable
     * @param ft FunctionTable to solve for user inputted values
     */
    public void setFunctionTable(FunctionTable ft){
        this.functionTable = ft;
    }

    /**
     * Loads the given function from history into the current function controllers, and updates coordinate plane
     * @param f function to load
     */
    protected void loadFunctionFromHistory(Function f){
        this.setFunction(f);
        this.cbfuncSel.getSelectionModel().select(f.getFunctionType());
        if(f.getFunctionType().equals("Quadratic")){
            double[] coeff = f.getCoeff();
            this.quadControl.setTextFieldText(""+coeff[0], ""+coeff[1], ""+coeff[2]);
        } else if(f.getFunctionType().equals("Sine")){
            double[] coeff = f.getCoeff();
            this.sineControl.setTextFieldText(""+coeff[0], ""+coeff[1], ""+coeff[2]);
        }
        this.UpdateFunction(false);
        System.out.println("INFO: Function loaded");
    }
    @Override
    public Function getFunction(){
        return this.function;
    }
    @Override
    public void setFunction(Function func){
        this.function = func;
        this.currentFunctionType = func.getFunctionType();
    }

    /**
     * Create the buttons, and set button actions
     */
    private void initButtons(){
        this.btUpdate = new Button("Update");
        this.btUpdate.setOnAction(e -> this.UpdateFunction(true));
        this.btFunctionHistory = new Button("History");
        this.btFunctionHistory.setOnAction(e -> this.viewHistory());
    }

    /**
     * Create a stage that displays the function history
     */
    private void viewHistory(){
        if(this.fhv == null){
            this.fhv = new FunctionHistoryViewer(new File(this.fFunctionDirectory.getAbsolutePath()), this);
        } else {
            System.out.println("INFO: Function history viewer already open!");
        }
        this.fhv = null;
    }

    /**
     * Update the function with coefficients from function controllers, and write the function to history if desired
     * @param write true when function is to be written, false when not.
     */
    private void UpdateFunction(boolean write) {
        double[] coeff = {};
        switch (currentFunctionType) {
            case "Quadratic" -> {
                this.function = new Quadratic(-5, 5, 0.01);
                coeff = this.quadControl.getFunctionParameters();
            }
            case "Sine" -> {
                this.function = new Sine(-5, 5, 0.01);
                coeff = this.sineControl.getFunctionParameters();
            }
        }
        this.functionTable.clearValues();
        this.function.setCoeff(coeff);
        Main.getStage().setTitle(this.function.toString());
        this.functionTable.setTitle(this.function.toString());
        this.functionTable.setFunction(this.function);
        this.coordinatePlane.plotPoints(this.function.getFunctionValues());

        if(write){
            this.writeFunctionToFile();
        }

    }

    /**
     * Write the function to its own file with a unique name
     */
    private void writeFunctionToFile() {
        try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(this.getUniqueFileName()))){
            objOut.writeObject(this.function);
            objOut.flush();
        } catch(IOException e){
            System.out.println("ERROR: IOException");
            e.printStackTrace();
        }
        System.out.println("Function " + this.function.toString() + " written to " + this.fFunctionDirectory.getAbsolutePath());
    }

    /**
     * {@return a unique file name for writing functions to disk}
     */
    private String getUniqueFileName(){
        return this.fFunctionDirectory + "/" + UUID.randomUUID();
    }

    /**
     * Initialize the ComboBox with function types.
     * ChangeListeners are used to monitor the currently selected item, and clear the coordinate plane and table
     * when selection changes.
     */
    private void initComboBox(){
        this.cbfuncSel.getItems().addAll("Quadratic", "Sine");
        this.cbfuncSel.getSelectionModel().selectFirst();
        this.cbfuncSel.prefWidth(200);
        this.cbfuncSel.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            System.out.println("old: " + s + " new: " + t1);
            switch (t1) {
                case "Quadratic" -> {
                    currentFunctionType = "Quadratic";
                    Main.getStage().setTitle("ax²+bx+c");
                    getChildren().removeAll(sineControl);
                    add(quadControl, 1, 0);
                }
                case "Sine" -> {
                    currentFunctionType = "Sine";
                    Main.getStage().setTitle("Asin(Bx) + k");
                    getChildren().removeAll(quadControl);
                    add(sineControl, 1, 0);
                }
            }
            functionTable.clearValues();
            coordinatePlane.clearPlot();
        });
    }

    /**
     * Initialize all controls
     */
    private void initControls(){
        this.initQuadraticControl();
        this.initSineControl();
    }

    /**
     * Initialize the quadratic controller with default style
     */
    private void initQuadraticControl(){
        this.quadControl = new QuadraticControl();
        this.quadControl.setStyle(this.paneStyle);
    }

    /**
     * Initialize the sine controller with default style
     */
    private void initSineControl(){
        this.sineControl = new SineControl();
        this.sineControl.setStyle(this.paneStyle);
    }

}

