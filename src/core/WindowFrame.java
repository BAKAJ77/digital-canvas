package core;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

// Simple class for wrapping the BorderPane class and handling the window stage
public class WindowFrame extends BorderPane
{
	private Stage primaryStage;
	
	public WindowFrame(Stage primaryStage, String windowName, int width, int height, boolean resizable)
	{
		Scene windowContent = new Scene(this, width, height);
		windowContent.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		this.primaryStage = primaryStage;
		this.primaryStage.setScene(windowContent);
		this.primaryStage.setTitle(windowName);
		this.primaryStage.setResizable(resizable);
	}
	
	// Makes the window stage visible
	public void Show()
	{
		primaryStage.show();
	}
}
