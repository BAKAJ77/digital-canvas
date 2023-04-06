package gui;

import javafx.scene.canvas.*;
import javafx.scene.control.ScrollPane;

// Simple class that allows for scrolling functionality on the drawing canvas
public class ScrollCanvas extends ScrollPane
{
	private Canvas canvas;
	
	public ScrollCanvas(int width, int height)
	{
		canvas = new Canvas(width, height);
		this.setContent(canvas);
	}
	
	// Returns the drawing canvas.
	public Canvas GetDrawingCanvas()
	{
		return canvas;
	}
}
