/**
 * @author Jay Doody
 * @version 12/15/2022
 * This is the main class that starts the program, and displays the main content along with instructions on how to use the program
 */

package core;
import graph.CoordinatePlane;
import graph.FunctionTable;
import graphcontrol.functionselection.FunctionSelect;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application{
    /**
     * Instruction text
     */
    private Text helpText = new Text("Enter function coefficients, and graph them bu clicking 'Update'. \nYou can use the function table to enter in any value you want to see the result.\n" +
                                          "To view function history, simply click the 'History' button");

    /**
     * Pane displaying the main content
     */
    private BorderPane mainPane;
    /**
     * The coordinate plane for displaying function plots
     */
    private CoordinatePlane coordPlane;
    /**
     * Function table
     */
    private FunctionTable funcTable;
    /**
     * Display for controlling the type and coefficients of a function
     */
    private FunctionSelect funcSelect;
    /**
     * Default width of main display
     */
    private final double WIDTH = 900;
    /**
     * Default height of main display
     */
    private final double HEIGHT = 700;
    /**
     * The main stage
     */
    private static Stage s;

    /**
     * Start the application with a coordinate plane, a function selector and controller, and a table for viewing inputted
     * function values.
     * @param primaryStage Main stage containing the main content
     */
    public void start(Stage primaryStage){
        s = primaryStage;
        this.mainPane = new BorderPane();
        this.mainPane.setPrefSize(this.WIDTH, this.HEIGHT);

        this.funcSelect = new FunctionSelect();
        this.coordPlane = new CoordinatePlane();
        this.funcTable = new FunctionTable(funcSelect.getFunction());
        this.funcSelect.setCoordPlane(this.coordPlane);
        this.funcSelect.setFunctionTable(this.funcTable);

        mainPane.setCenter(this.coordPlane);
        mainPane.setBottom(this.funcSelect);
        mainPane.setRight(this.funcTable);

        Scene s = new Scene(this.mainPane);
        primaryStage.setScene(s);
        primaryStage.show();
        this.displayInstructions();

    }

    /**
     * Show an alert box informing the user on how to use the application
     */
    private void displayInstructions(){
        Alert instructions = new Alert(Alert.AlertType.INFORMATION);
        instructions.setTitle("Important!");
        instructions.getDialogPane().setHeaderText("How to use");
        this.helpText.setWrappingWidth(400);
        instructions.getDialogPane().setContent(this.helpText);
        instructions.show();
    }

    /**
     * {@return the main stage}
     */
    public static Stage getStage(){
        return s;
    }

}
