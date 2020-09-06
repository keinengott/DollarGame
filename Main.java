/**
 *  Project 2:  Dollar Game.
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
import static java.lang.Character.toUpperCase;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Main {

    //VERT_MAX and VERT_MIN:  Two constants for holding the 
    public final static int VERT_MAX = 7;
    public final static int VERT_MIN = 2;
    // This flag flips allows the game to exit in the main loop after the user quits
    public static boolean isPlaying = true;
    // Used to keep track of the user moves and print to console on game exit
    public static int totalMoves = 0;

    // Convenience array for assigning Vertex ids
    public static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    // Scanner object to read from the terminal
    public static final Scanner reader = new Scanner(System.in);

    // This array is used to store all of the vertices in the game state
    public static final ArrayList<Vertex> verticesArray = new ArrayList();

    /*
    * This is the vertex objects which holds the id of the vertex as well as the 
    * dollar values and its connected vertices
     */
    public static class Vertex {

        char id;
        int dollarAmount;
        ArrayList<Character> connectedVertices;

        public Vertex(char id) {
            this.id = id;
            connectedVertices = new ArrayList();
        }
    }

    // Return true when the board is in a winning state, else false
    private static boolean isSolved() {
        for (Vertex x : verticesArray) {
            if (x.dollarAmount < 0) {
                return false;
            }
        }
        return true;
    }

    // prints the current state of the game
    private static void printBoardState() {
        for (Vertex v : verticesArray) {
            System.out.println("Vertex " + v.id + "'s value is " + v.dollarAmount + " and is connected to " + v.connectedVertices);
        }
    }

    // This function enables the user to connect vertices
    private static void setupEdges(int edges) {
        // 'pat' creates an allowable pattern for an input
        String pat = "[A-";
        // 'c' sets the final character in the pattern
        char c = alphabet[verticesArray.size() - 1];
        pat += toUpperCase(c);
        pat = pat + "a-" + c + "]";
        pat += pat;
        // 'pattrn is used by the scanner to validate the input'
        Pattern pattern = Pattern.compile(pat);
        // this loop sets up the vertices in the game and exits when they are all setup
        while (true) {
            if (edges == 0) {
                break;
            }
            try {
                System.out.println("Please connect the edges in the form of " + pat);
                // 'input' reads the user input and ensures the appropriate pattern is used
                String input = reader.next(pattern);
                if (validateEdge(input)) {
                    connectVertices(input);
                    edges -= 1;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. " + "Input must match: " + pat);
                reader.next();
            }
        }
    }

    /*
    * validateEdge() returns whether the edge entered by the user is valid
    * invalid edges include connecting a vertex to itself or attempting to 
    * connect already connected vertices
     */
    private static boolean validateEdge(String input) {
        if (input.charAt(0) == input.charAt(1)) {
            System.out.println("You may not connect a vertex to itself. Try again");
            reader.next();
            return false;
        }
        for (Vertex v : verticesArray) {
            if (v.id == input.charAt(0) || v.id == input.charAt(1)) {
                if (v.connectedVertices != null) {
                    for (char c : v.connectedVertices) {
                        if (c == input.charAt(1) || c == input.charAt(0)) {
                            System.out.println("That edge already exists. Try again.");
                            reader.next();
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /*
    * This function performs the task of connected a vertex to another by
    * appending the new vertex id to the 'connectedVertices' member of Vertex
     */
    private static void connectVertices(String input) {
        char[] vertices = input.toCharArray();
        for (Vertex v : verticesArray) {
            if (v.id == vertices[0]) {
                v.connectedVertices.add(vertices[1]);
            }
            if (v.id == vertices[1]) {
                v.connectedVertices.add(vertices[0]);
            }
        }
    }

    /*
    * getMove() prompts the user for a move and ensures that the inputted move is valid.
    * To be valid, the input must be a 'q' or an existing vertex id.
     */
    private static void getMove() {
        while (true) {
            try {
                System.out.println("Enter a vertex: ");
                String input = reader.next();
                if (input.length() != 1) {
                    System.out.println("Ivalid input. Input must be a vertex or \'q\' to quit");
                    reader.next();
                }
                if (input.equalsIgnoreCase("q")) {
                    isPlaying = false;
                    return;
                }
                if (isValidVertex(input)) {
                    String action;
                    do {
                        System.out.println("Do you want to give or take (g/t)?");
                        action = reader.next();
                    } while (!action.equalsIgnoreCase("g") && !action.equalsIgnoreCase("t"));
                    takeAction(action, input);
                    totalMoves += 1;
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("weird");
            }
        }
    }

    /*
    * This function validates that the argument is the id of a valid Vertex in the game state
     */
    private static boolean isValidVertex(String move) {
        for (Vertex v : verticesArray) {
            if (v.id == move.charAt(0)) {
                return true;
            }
        }
        System.out.println(move.charAt(0) + " is not a valid vertex id");
        printValidMoves();
        return false;
    }

    /*
    * takeAction() takes an action (t/g) and an input (valid Vertex id) and increments and decrements
    * the dollarAmount contained in each vertex depeding on whether the user chose to give or take from
    * the vertex
     */
    private static void takeAction(String action, String input) {
        for (Vertex v : verticesArray) {
            if (action.equalsIgnoreCase("g")) {
                if (v.id == input.charAt(0)) {
                    v.dollarAmount -= v.connectedVertices.size();
                }
                if (v.connectedVertices.contains(input.charAt(0))) {
                    v.dollarAmount += 1;
                }
            } else {
                if (v.id == input.charAt(0)) {
                    v.dollarAmount += v.connectedVertices.size();
                }
                if (v.connectedVertices.contains(input.charAt(0))) {
                    v.dollarAmount -= 1;
                }
            }
        }
    }

    // helper function to print vertex ids
    private static void printValidMoves() {
        String validMoves = "";
        for (Vertex v : verticesArray) {
            validMoves = validMoves + v.id + ", ";
        }
        System.out.println("Valid vertex ids are " + validMoves);
    }

    /*
    * This is the main game loop. It initializes the board state and runs until the user decides to quit
     */
    public static void main(String[] args) {

        //vertices:  the vertices count that the user inputs later.
        int vertices;

        //edges: the edges the user enters for later.
        int edges;

        vertices = getVertices();
        edges = getEdges(vertices);

        // this loop creates the Vertex objects and assigns them an alphabetical id
        for (int i = 0; i < vertices; i++) {
            verticesArray.add(new Vertex(alphabet[i]));
        }
        setupEdges(edges);
        assignValues();

        // this loop runs until the user requests to exit the game
        // it will print the board state before getting the move from the user
        while (isPlaying) {
            if (isSolved()) {
                System.out.println("Board is in a winning configuration!");
            }
            printBoardState();
            getMove();
        }
        if (isSolved()) {
            System.out.println("You win!" + " You made " + totalMoves + " moves");
        } else {
            System.out.println("You lose!" + " You made " + totalMoves + " moves");
        }
    }

    /**
     * Gets the integer value from the user.
     *
     * @return an integer value between VERT_MIN and VERT_MAX (Inclusive).
     */
    public static int getVertices() {

        //vertValue: Should hold the number of vertices from the user's input.
        int vertValue = 0;

        //isValid:  If this is true, then the user entered valid input.
        boolean isValid = false;

        System.out.println("Please enter a number of vertices for the Dollar Game between " + VERT_MIN + " and " + VERT_MAX);

        //Get input from the user until the user enters a valid integer.
        do {
            try {
                vertValue = reader.nextInt();

                if (vertValue >= VERT_MIN && vertValue <= VERT_MAX) {
                    isValid = true;
                } else {
                    //The user entered an integer, but out of bounds.
                    System.out.println("Error.\nInvalid Value\nPlease enter an whole number value between " + VERT_MIN + " and " + VERT_MAX);
                    isValid = false;
                }

            } catch (InputMismatchException e) {
                //The user entered something that wasn't a string.
                System.out.println("Error.\nInvalid Entry\n Please enter an whole number value between " + VERT_MIN + " and " + VERT_MAX);
                reader.next();
                isValid = false;

            }

        } while (isValid == false);

        return vertValue;
    }

    /**
     * This function requires the user to enter an integer with a minimum of
     * (vertices - 1).
     *
     * If the user enters something invalid, or an integer that is greater than
     * (vertices - 1): -The method displays an error message. -The method
     * re-prompts.
     *
     * @param vertices
     * @return number of edges
     */
    public static int getEdges(int vertices) {
        //edgeValue: Should hold the number of vertices from the user's input.
        int edgeValue = 0;
        
        
        int edgeMax = (vertices * (vertices-1))/2;
        
        int edgeMin = (vertices - 1);
        
        

        //isValid:  If this is true, then the user entered valid input.
        boolean isValid = false;

        System.out.println("Please enter a number of edges for the Dollar Game.");
        System.out.println("The minimum amount of edges required is " + edgeMin);
        System.out.println("The maximum amount of edges required is " + edgeMax);
        

        do {
            try {
                edgeValue = reader.nextInt();
                if (edgeValue >= edgeMin && edgeValue <= edgeMax) {
                    //If the input is valid, isValid = true.
                    isValid = true;
                } else {
                    //The user entered an integer, but out of bounds.
                    System.out.println("Error.\nInvalid Value\nPlease enter an whole number between " + edgeMin  + " and " + edgeMax);

                    isValid = false;
                }
            } catch (InputMismatchException e) {

                //The user entered something that wasn't an integer.
                System.out.println("Error.\nInvalid Entry\nPlease enter an whole number between " + edgeMin  + " and " + edgeMax);
                reader.next();
                isValid = false;
            }
        } while (!isValid);

        return edgeValue;
    }

    /*
    * assignValues() prompts the user for the dollar value that is input in each
    * vertex. It then assigns the appropriate value when a valid integer is entered
     */
    private static void assignValues() {
        // the dollar value entered by the user
        int value;
        System.out.println("Please assign a dollar amound for each vertex.");

        // for each vertex, prompt the user for a value and assign it to the vertex
        for (Vertex v : verticesArray) {
            boolean isValid = false;
            do {
                try {
                    System.out.println("What should be the value of " + v.id + "?");
                    value = reader.nextInt();
                    v.dollarAmount = value;
                    isValid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid value. Please enter an integer");
                    reader.next();
                }
            } while (!isValid);
        }
    }
}
