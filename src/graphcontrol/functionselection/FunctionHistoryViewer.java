/**
 * @author Jay Doody
 * @version 12/15/2022
 * A function history viewer for viewing the function history, and loading functions from the history into the main
 * application
 */
package graphcontrol.functionselection;

import graphcontrol.functions.Function;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.*;


public class FunctionHistoryViewer extends Stage{

    /**
     * Viewport for the function history display
     */
    private ScrollPane functionViewContainer;

    /**
     * The function history display
     */
    private VBox functionView;

    /**
     * The function selection controller
     */
    private final FunctionSelect functionSelect;

    /**
     * The function history directory to read in the functions
     */
    private final File fHistoryDirectory;

    /**
     * The functions loaded from history directory
     */
    private final ArrayList<Function> functions;

    /**
     * The labels used for displaying the functions in the function history display
     */
    private final ArrayList<Label> functionLabels;

    /**
     * The map associating the labels to their corresponding functions
     */
    private final LinkedHashMap<Label, Function> labelsAndFunctions;

    /**
     * Create a new FunctionHistoryViewer with given file, and function selection controller
     * @param functionDirectory the directory of the function history
     * @param funcSelect the function selection controller
     */
    public FunctionHistoryViewer(File functionDirectory, FunctionSelect funcSelect){
        this.functionSelect = funcSelect;
        this.fHistoryDirectory = functionDirectory;
        this.setTitle("Function History");

        initFunctionView();
        initFunctionViewContainer();

        Scene scene = new Scene(this.functionViewContainer);
        this.setScene(scene);
        this.show();
        this.functions = new ArrayList<>();
        this.functionLabels = new ArrayList<>();
        this.labelsAndFunctions = new LinkedHashMap<>();
        this.displayFunctions();
    }

    /**
     * Retrieve the functions from the directory, and create labels with their string representation along with styles,
     * and actions.
     * Add to the function map, and display them.
     */
    private void displayFunctions(){
        this.getFunctionsFromFile();
        this.functions.sort(Comparator.comparing(Function::getTimeCreated));
        this.functions.forEach(System.out::println);
        for(Function f : this.functions){
            this.labelsAndFunctions.put(new Label(f.toString() + " : " + f.getTimeCreated()), f);
        }
        System.out.println(this.labelsAndFunctions.keySet());
        for(Label l : this.labelsAndFunctions.keySet()){
            l.setOnMouseEntered(e ->{
                l.setStyle("-fx-border-color: #6f55ce");
            });
            l.setOnMouseExited(e ->{
                l.setStyle("-fx-border-color: none");
            });
            l.setOnMouseClicked(e -> this.loadFunction(this.labelsAndFunctions.get(l)));
            this.functionView.getChildren().add(0, l);
        }
    }

    /**
     * Load function from map, and give it to the function selection controller for updating the coordinate plane,
     * and function controllers with new values.
     * @param f the label associated with the function to load
     */
    private void loadFunction(Function f){
        this.functionSelect.loadFunctionFromHistory(f);
        System.out.println(f);
        System.out.println("Loaded!!!");
    }

    /**
     * Retrieve the function files from the function history directory, and add the functions to the functions list
     */
    private void getFunctionsFromFile(){

        File[] functionFileList = this.fHistoryDirectory.listFiles();
        assert functionFileList != null;
        for(File f : functionFileList){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
                this.functions.add((Function) ois.readObject());
            }catch(IOException e){
                System.out.println("ERROR: Stream not initialized!");
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                System.out.println("ERROR: Class could not be loaded from file!");
            }
        }
    }

    /**
     * Initialize the history view display
     */
    private void initFunctionView(){
        this.functionView = new VBox();
        this.functionView.setSpacing(5);
        this.functionView.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Initialize the history view display container
     */
    private void initFunctionViewContainer(){
        this.functionViewContainer = new ScrollPane();
        this.functionViewContainer.setPrefSize(400, 200);
        this.functionViewContainer.setContent(this.functionView);
    }
}
