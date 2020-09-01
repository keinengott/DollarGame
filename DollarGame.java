

/* Project 2:  Dollar Game.
 * 
 * Names:
 * 	Robert Kaufman
 * 	Jack Crowley
 *  
 *  
 * E-mails:
 * 	
 * 	jrc8b8@mail.umsl.edu
 * 	
 * 
 * Class:
 * 	4500 Introduction to the Software Profession
 * 
 * Class Number:
 * 	001
 * 
 * 
 * Project Description:
 * 
 * ---------------------------
 * This project allows a user to play "The Dollar Game"
 * 
 * 1.  This program asks the user for 2 to 7 vertices.
 * 
 * 2.  This program asks the user for a number of edges.  The minimum is the number of vertices - 1.
 * 
 * 3.  This program asks the user to list each edge's connections.  Vertices cannot be connected to themselves.
 * 
 * 4.  After all edges have been defined, the user plays the game.
 * 
 * 5.  The user may quit at any time by typing "Q" or "q"
 * 
 * 6.  The user plays the game by typing the name of a vertex (a single letter).
 * 		-When selected, the vertex transfers one dollar to all connected vertices.
 * 
 * 7. If all vertices have 0 or more dollars, the player has "won."
 *   -If the player has won in this way, the player may still continue playing.
 *
 * 8. After the player quits, the program should list the following:
 *  -How many moves the player took.
 *  -Whether or not the player won.
 *  -The end dollar count for each vertex.
 *
 *
 * ---------------------------
 * 
 */
 
//Needed for user input.
import java.util.Scanner;

import java.util.InputMismatchException;



public class DollarGame {
 
 	 //VERT_MAX and VERT_MIN:  Two constants for holding the 
	  public final static int VERT_MAX = 7;
	
 	 public final static int VERT_MIN = 2;
	
 
    public static void main(String[] args)
    {
        System.out.println("oy");
     
        //vertices:  the vertices count that the user inputs later.
        int vertices;
     
        //edges: the edges the user enters for later.
        int edges;
     
        vertices = getVertices();
     
        edges = getEdges(vertices);
    }
 
 
 
 
 /* getVertices()
	 * 
	 * Returns an integer value between VERT_MIN and VERT_MAX (Inclusive).
	 * 
	 * Gets the integer value from the user.
	 * 
	 * 
	 */
	public static int getVertices()
	{
		
		//vertValue: Should hold the number of vertices from the user's input.
		int vertValue = 0;
		
		
		//isValid:  If this is true, then the user entered valid input.
		boolean isValid = false;
		
		
		//reader:  This scanner takes in input.
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Please enter a number of vertices for the Dollar Game between "  + VERT_MIN + " and " + VERT_MAX);
		
  //Get input from the user until the user enters a valid integer.
		do
		{
			
			try
			{
				vertValue = reader.nextInt();
				
				if (vertValue >= VERT_MIN && vertValue <= VERT_MAX )
				{
					isValid = true;
					
				}
				else
				{
     //The user entered an integer, but out of bounds.
					System.out.println("Error.\nInvalid Value\nPlease enter an whole number value between " + VERT_MIN + " and " + VERT_MAX);
					
					isValid = false;
					
				}
				
				
			}
			catch(InputMismatchException e)
			{
    //The user entered something that wasn't a string.
				System.out.println("Error.\nInvalid Entry\n Please enter an whole number value between " + VERT_MIN + " and " + VERT_MAX);
				
				isValid = false;
				
			}
			
			
			
		}while (isValid == false);
		
		
		return vertValue;
	}

	
	
 
 	
	/* getEdges()
	 * 
	 * Parameters:
	 * 	vertices: int
	 * 
	 * 
	 * This function requires the user to enter an integer with a minimum of (vertices - 1).
	 * 
	 * If the user enters something invalid, or an integer that is greater than (vertices - 1):
	 * 	-The method displays an error message.
	 * 	-The method re-prompts.
	 * 
	 */

	public static int getEdges(int vertices)
	{
		//vertValue: Should hold the number of vertices from the user's input.
				int edgeValue = 0;
				
				
				//isValid:  If this is true, then the user entered valid input.
				boolean isValid = false;
				
				
				//reader:  This scanner takes in input.
				Scanner reader2 = new Scanner(System.in);
				
				System.out.println("Please enter a number of edges for the Dollar Game.");
				
				System.out.println("The minimum amount of edges required is " + (vertices - 1));
				
				do
				{
					
					try
					{
						edgeValue = reader2.nextInt();
						
						if (edgeValue >= (vertices - 1))
						{
        //If the input is valid, isValid = true.
							isValid = true;
							
						}
						else
						{
        //The user entered an integer, but out of bounds.
							 System.out.println("Error.\nInvalid Value\nPlease enter an whole number value greater than " + vertices);
							
							 isValid = false;
							
						}
						
						
					}
					catch(InputMismatchException e)
					{
      
      //The user entered something that wasn't an integer.
						 System.out.println("Error.\nInvalid Entry\n Please enter an whole number value greater than " + vertices);
						
						 isValid = false;
						
					}
					
					
					
				}while (isValid == false);
				
				
				return edgeValue;

	}
 
 
 
 
 
}
