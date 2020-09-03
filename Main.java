




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
 * 
 * 
 * 
 * ---------------------------
 * 
 * 
 * 
 */



//Needed for user input.
import java.util.Scanner;

import java.util.InputMismatchException;


public class Main {

	
	public final static int VERT_MAX = 7;
	
	public final static int VERT_MIN = 2;
	
	public final static char STARTING_CHAR = 'a'; 
	
	
	
	public static void main (String [] args)
	{
	
	//vertices: the number of vertices for this assignment.
	int vertices;
	
	//edges:  the number of edges for this assignment.
	int edges;
	
	
	
		vertices = getVertices();
		
		//If getVertices returns 0, that's the signal to quit.
		if (vertices == 0)
		{
			return;
		}
	
		
		edges = getEdges(vertices);
	
		if (edges == 0)
		{
			return;
		}
		
		
		System.out.println("Please enter the connections between each edge as \"AB\"");
		
		
		String[] edgeList = defineEdges(vertices, edges);
		
		if (edgeList == null)
		{
			
			return;
		}
		
		
		Vertex [] vertexList = initializeVertices(vertices);
		
		
		Scanner reader = new Scanner(System.in);
		
		boolean isValid = false;
		
		int dollars = 0;
		
		for(int x = 0 ; x < vertexList.length ; x++)
		{
			System.out.println("Please enter a value for the dollars of vertex " + vertexList[x].getName());
			
			
			do
			{	
				try
				{
					dollars = reader.nextInt();
				
					isValid = true;
				
				}
				catch(InputMismatchException e)
				{
					System.out.println("Error.  Please enter a valid integer for the dollar count.");
					
					isValid = false;
				}
			}	
			while(isValid == false);
			
			vertexList[x].setDollars(dollars);
			
		}
		
		
		
		for(int x = 0 ; x < vertexList.length ; x++)
		{
			System.out.println(vertexList[x]);
			
		}
		
		
		
		
	
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
		
		
		//userInput:  Holds the input from the keyboard.
		String userInput;
		
		//isValid:  If this is true, then the user entered valid input.
		boolean isValid = false;
		
		
		//reader:  This scanner takes in input.
		Scanner reader = new Scanner(System.in);
		
		System.out.println("Please enter a number of vertices for the Dollar Game between "  + VERT_MIN + " and " + VERT_MAX);
		
		
		do
		{
			
			userInput = reader.nextLine();
			
			
			if(isInteger(userInput) == false)
			{
				if(userInput.equals("q") || userInput.equals("Q"))
				{
					System.out.println("Quitting program.");
					return 0;
				}
				
				else
				{
					System.out.println("Error. Invalid Input Type.  Please enter a whole number between " + VERT_MIN +" and " + VERT_MAX + ".");
					
					isValid = false;
				}	
			}
			else
			{
				vertValue = Integer.parseInt(userInput);
				
				
				if(vertValue < VERT_MIN || vertValue > VERT_MAX)
				{
					System.out.println("Error. Invalid Value.  Please enter a whole number between " + VERT_MIN +" and " + VERT_MAX + ".");
					
					isValid = false;
				}
				else
				{
					isValid = true;
				}
				
			}
			
		} while(isValid == false);
		
		
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
				
				
				//At the least, we must have v - 1 edges for a connected graph.
				int edge_min = vertices - 1;
				
				
				//Directed Graphs (The maximum) can only have v * (v - 1) / 2 edges.
				int edge_max = ((vertices - 1) * vertices) / 2;
				
				
				//userInput:  Holds the input from the keyboard.
				String userInput;
				
				//reader:  This scanner takes in input.
				Scanner reader2 = new Scanner(System.in);
				
				System.out.println("Please enter a number of edges for the Dollar Game.");
				
				System.out.println("The minimum amount of edges required is " + edge_min);
				
				System.out.println("The maximum amount of edges is " + edge_max);
				
				
				do
				{
					
					userInput = reader2.nextLine();
					
					
					if(isInteger(userInput) == false)
					{
						if(userInput.equals("q") || userInput.equals("Q"))
						{
							System.out.println("Quitting program.");
							return 0;
						}
						
						else
						{
							System.out.println("Error. Invalid Input Type.  Please enter a whole number between " + edge_min +" and " + edge_max + ".");
							
							isValid = false;
						}	
					}
					else
					{
						edgeValue = Integer.parseInt(userInput);
						
						
						if(edgeValue < edge_min || edgeValue > edge_max)
						{
							System.out.println("Error. Invalid Value.  Please enter a whole number between " + edge_min +" and " + edge_max + ".");
							
							isValid = false;
						}
						else
						{
							isValid = true;
						}
						
					}
					
				} while(isValid == false);
				
				
				
				

				return edgeValue;
	}
	
	

	/* isInteger()
	 * 
	 * Parameters:
	 * 	testCase: String
	 * 
	 * 
	 * 	Returns true if testCase can safely be parsed into an integer.
	 *  
	 *  If testCase contains any characters that are not digits, false is returned.
	 * 
	 */
	public static boolean isInteger(String testCase)
	{
		for (int x = 0 ; x < testCase.length(); x++)
		{
			
			//If the character at position x is NOT a digit, then we return false.
			if (Character.isDigit(testCase.charAt(x)) != true)
			{
				return false;
				
			}
			
			
			
		}
		
		
		return true;
		
	}
	
	
	
	public static String[] defineEdges(int vertices, int edges)
	{
		
		String [] edgeList = new String[edges];
		
		String currentString;
		
		Scanner reader = new Scanner(System.in);
		
		boolean validEdge = false;
		
		for (int x = 0 ; x < edgeList.length ; x++)
		{
			System.out.println("Enter the connection for Edge #" + (x+1));
			
			
			do 
			{
			
				
			
			
				currentString = reader.nextLine();
			
				if(currentString.equals("Q") || currentString.equals("q"))
				{
					System.out.println("Quitting.");
					return null;
				}
			
				validEdge = checkEdge(currentString, edgeList, x, vertices);
			
			}
			while(validEdge == false);
			
			
			currentString = currentString.toLowerCase();
			
			if (currentString.charAt(0) > currentString.charAt(1))
			{
				currentString = reverseEdge(currentString);
			}
			
			edgeList[x] = currentString;
			
			System.out.println(edgeList[x]);
			
			
		}
		
		

		return edgeList;
	}
	
	
	
	public static boolean checkEdge(String currentEdge, String[] currentEdges, int filledEdges, int vertexCount)
	{
		
		
		if (currentEdge.length() != 2)
		{
			System.out.println("Error.  Invalid edge length.  Please type the edge as \"XY\"");
			
			return false;
		}
		
	
		//This checks if the edge is something like "AA" or "Bb"
		
		
		//char0 and char1.  The two characters of the edge.
		
		//To make things simpler, we check by making the current string lowercase.
		currentEdge = currentEdge.toLowerCase();
		
		if(currentEdge.charAt(0) == currentEdge.charAt(1))
		{
			System.out.println("Error.  Invalid edge.  You cannot give a vertex an edge to itself.");
		}
		
		
		
		//Check and make sure the vertices are within A to whatever.
		
		int vertexLimit = (int)('a') + vertexCount-1;
		
		if ((int)currentEdge.charAt(0) > vertexLimit || (int)currentEdge.charAt(1) > vertexLimit)
		{
			System.out.println("Error.  Invalid vertex.  Please give vertices between " + 'a' + " and " + (char)(vertexCount-1));
			return false;
		}
		
		if ((int)currentEdge.charAt(0) < (int)('a') || (int)currentEdge.charAt(1) < (int)('a'))
		{
			System.out.println("Error.  Invalid vertex.  Please give vertices between " + 'a' + " and " + (char)(vertexCount-1));
			return false;
		}
		
		
		
		//Check and make sure there are no collisions between other edges.
		for (int x = 0 ; x < filledEdges ; x++)
		{
			if (currentEdge.equals(currentEdges[x]))
			{
				
				System.out.println("Error.  Edge already exists.  Please create a different connection than "  + currentEdges[x] );
				return false;
			}
			
			if(currentEdge.equals(reverseEdge(currentEdges[x])))
			{
				System.out.println("Error.  Edge already exists. Please create a different connection." + currentEdges[x]);
				return false;
			}
			
			
		}	
		
		return true;
	}
	
	
	
	/* reverseEdge()
	 * 
	 * Parameters:
	 *  s: String.
	 * 
	 * Reverses the characters at positions 0 and 1 of string s.
	 * 
	 */
	public static String reverseEdge(String s)
	{
		
		return s.charAt(1) + "" + s.charAt(0);
	}
	
	
	
	
	
	/* initializeVertices()
	 * 
	 * Parameters:
	 * 	vertexCount: int
	 * 
	 * Creates an array of Vertex objects of length "vertexCount"
	 * 
	 * The "names" of each Vertex are defined via STARTING_CHAR + the value of the current iteration of the for loop inside.
	 * 
	 * 
	 */
	public static Vertex[] initializeVertices(int vertexCount)
	{
		
		//vertexList: Vertex[]: This array is returned.
		Vertex[] vertexList = new Vertex[vertexCount];
	
		
		//reader: Scanner:  For initializing the Dollar count.
		Scanner reader = new Scanner(System.in);
		
		
		/*
		 * Each iteration increases the name by one character.
		 * 
		 */
		for(int x = 0 ; x < vertexCount ; x++)
		{
			
			
			vertexList[x] = new Vertex((char)(STARTING_CHAR + x));
			
			
			
		
			
		}
		
		
		
		return vertexList;
	}
	
	
	
	
	
}





