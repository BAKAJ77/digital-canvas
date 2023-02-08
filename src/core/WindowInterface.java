package core;

import java.nio.*;
import math.*;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * <p> A wrapper class for handling window operations using GLFW.
 */
public class WindowInterface 
{
	private long windowID;
	
	/**
	 * <p> The class constructor method.
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @param resizable
	 * @param fullscreen
	 */
	public WindowInterface(String title, int width, int height, boolean resizable, boolean fullscreen)
	{
		// Initialise the GLFW module
		if (!glfwInit())
			throw new IllegalStateException("Failed to initialize the GLFW module.");
		
		GLFWErrorCallback.createPrint(System.err).set();
		
		// Setup the window hints
		glfwWindowHint(GLFW_RESIZABLE, resizable ? GL_TRUE : GL_FALSE);
		
		// Create the window
		windowID = fullscreen ? 
			glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), NULL) :
			glfwCreateWindow(width, height, title, NULL, NULL);
		
		if (windowID == NULL)
			throw new RuntimeException("Failed to create GLFW window.");
		
		// Make sure the window is positioned at the centre of the screen
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor()); // Get the resolution of the primary monitor
		glfwSetWindowPos(windowID, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		
		// Make the OpenGL context current
		glfwMakeContextCurrent(windowID);
		GL.createCapabilities();
	}
	
	/**
	 * <p> Cleans up window allocations, callbacks and de-initialises the GLFW module.
	 */
	public void Destroy()
	{
		glfwFreeCallbacks(windowID);
		glfwDestroyWindow(windowID);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	/**
	 * <p> Swaps the window render buffers and checks for window events.
	 */
	public void Update()
	{
		glfwSwapBuffers(windowID);
		glfwPollEvents();
	}
	
	/**
	 * <p> Requests for the window to be closed.
	 */
	public void RequestClose()
	{
		glfwSetWindowShouldClose(windowID, true);
	}
	
	/**
	 * <p> Sets the size of the window.
	 * 
	 * @param width
	 * @param height
	 */
	public void SetSize(int width, int height)
	{
		glfwSetWindowSize(windowID, width, height);
	}
	
	/**
	 * @return TRUE if the window has been requested to close, else FALSE is returned.
	 */
	public boolean HasRequestedClose()
	{
		return glfwWindowShouldClose(windowID);
	}
	
	/**
	 * @return A Vector2 containing the current width and height of the window.
	 */
	public Vector2 GetSize()
	{
		try (MemoryStack stack = stackPush()) 
		{
			IntBuffer pWidth = stack.mallocInt(1); // Basically an int*
			IntBuffer pHeight = stack.mallocInt(1);

			// Get the current size of the window
			glfwGetWindowSize(windowID, pWidth, pHeight);

			return new Vector2(pWidth.get(0), pHeight.get(0));
		}
	}
	
	/**
	 * @return The version of the LWJGL library.
	 */
	public String GetLWJGLVersion()
	{
		return Version.getVersion();
	}
	
	/**
	 * @return The identifier of the GLFW window.
	 */
	public long GetWindowID()
	{
		return windowID;
	}
}
