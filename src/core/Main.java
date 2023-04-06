package core;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;

import gui.*;

public class Main extends Application 
{
	// This is the method where the application is set-up and started
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			final int windowWidth = 800;
			final int windowHeight = 600;
			
			WindowFrame window = new WindowFrame(primaryStage, "Digital Canvas", windowWidth, windowHeight, false);
			ScrollCanvas canvas = new ScrollCanvas(windowWidth, windowHeight);
			
			GraphicsContext canvasContent = canvas.GetDrawingCanvas().getGraphicsContext2D();
			canvasContent.setFill(Color.LIGHTGREY);
			canvasContent.fillRect(0, 0, windowWidth, windowHeight);
			
			ToolBar drawToolBar = CreateToolbar(50, 100);
			
			window.setCenter(canvas);
		    window.setLeft(drawToolBar);
			window.Show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	// Creates the tool bar for the drawing tools
	ToolBar CreateToolbar(int minWidth, int maxWidth) 
	{	
		ToggleGroup currentDrawTool = new ToggleGroup();
		ToggleButton penButton = new ToggleButton("Pen");
		ToggleButton pencilButton = new ToggleButton("Pencil");
		ToggleButton rubberButton = new ToggleButton("Rubber");
		
		penButton.setToggleGroup(currentDrawTool);
		pencilButton.setToggleGroup(currentDrawTool);
		rubberButton.setToggleGroup(currentDrawTool);
	    
		ToggleGroup currentToolSize = new ToggleGroup();
		ToggleButton smallButton = new ToggleButton("Small");
		ToggleButton mediumButton = new ToggleButton("Medium");
		ToggleButton largeButton = new ToggleButton("Large");
		
		smallButton.setToggleGroup(currentToolSize);
		mediumButton.setToggleGroup(currentToolSize);
		largeButton.setToggleGroup(currentToolSize);
	    
		ToolBar toolbar = new ToolBar(new Label("Tool"), penButton, pencilButton, rubberButton, new Separator(),
		     new Label("Size"), smallButton, mediumButton, largeButton);
		
		toolbar.setMinWidth(minWidth);
		toolbar.setMaxWidth(maxWidth);
		toolbar.setOrientation(Orientation.VERTICAL);
		return toolbar;
	}
	
	// The main method
	public static void main(String[] args) 
	{
		launch(args);
	}
}
