package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;


public class DigitalCanvas extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane(); //Main(root) Window
			int height = 600;
			int width = 800;
			
			
			final Canvas canvas = new Canvas(width, height); //declare canvas
			GraphicsContext canvasContent = canvas.getGraphicsContext2D(); //Add Drawn content to canvasContent
			 
			canvasContent.setFill(Color.LIGHTGREY);
			canvasContent.fillRect(0, 35, width, height*0.9);
			

			ToolBar UtilityToolBar = toolbar(width);
			VBox vBox = new VBox(UtilityToolBar);
			
		    root.getChildren().add(canvas); //adds the canvas to root
		    root.getChildren().add(vBox);   //adds vBox(ToolBar area) to root
			
			Scene scene = new Scene(root,width,height); //content window, adds root to the window
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Digital Canvas");
			primaryStage.show();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	ToolBar toolbar(int width) {
		
		ToggleGroup CurrentDrawTool = new ToggleGroup();
		
		ToggleButton penButton = new ToggleButton("Pen");
		ToggleButton pencilButton = new ToggleButton("Pencil");
		ToggleButton rubberButton = new ToggleButton("Rubber");
		penButton.setToggleGroup(CurrentDrawTool);
		pencilButton.setToggleGroup(CurrentDrawTool);
		rubberButton.setToggleGroup(CurrentDrawTool);
	    
		ToggleGroup CurrentToolSize = new ToggleGroup();
		
		ToggleButton smallButton = new ToggleButton("Small");
		ToggleButton mediumButton = new ToggleButton("Medium");
		ToggleButton largeButton = new ToggleButton("Large");
		smallButton.setToggleGroup(CurrentToolSize);
		mediumButton.setToggleGroup(CurrentToolSize);
		largeButton.setToggleGroup(CurrentToolSize);
	    
	    
		ToolBar UtilityToolBar = new ToolBar( //declare nodes in the Utility tool bar (pen/pencil/rubber toggle buttons)
			     penButton,
			     pencilButton,
			     rubberButton,
			     new Separator(),
			     smallButton,
			     mediumButton,
			  	 largeButton
			 );
		
		UtilityToolBar.setMinWidth(width);
		return UtilityToolBar;
	}
	
	public static void main(String[] args) { //Main Method
		launch(args);
	}
}
