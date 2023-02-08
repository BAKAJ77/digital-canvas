package core;

import static org.lwjgl.opengl.GL11.*;

public class Main 
{
	public static void main(String[] args)
	{
		// Create the application window
		WindowInterface applicationWindow = new WindowInterface("Digital Canvas", 1600, 900, false, false);
		
		while (!applicationWindow.HasRequestedClose()) // The main application loop
		{
			glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // TEMPORARY
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // TEMPORARY
			
			applicationWindow.Update();
		}
		
		applicationWindow.Destroy(); // Make sure to destroy the window once done with it
	}
}
