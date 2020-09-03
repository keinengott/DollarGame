//Needed for user input.
import java.util.ArrayList;
import java.util.Scanner;

import java.util.InputMismatchException;

public class DollarGame {

    //VERT_MAX and VERT_MIN:  Two constants for holding the 
    public final static int VERT_MAX = 7;

    public final static int VERT_MIN = 2;
    public static final Scanner reader = new Scanner(System.in);
    
    
    public static final ArrayList<Vertex> verticesArray = new ArrayList();

    public static class Vertex {

        char id;
        int dollarAmount;
        char[] connectedVertices;

        public Vertex(char id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {

        //vertices:  the vertices count that the user inputs later.
        int vertices;

        //edges: the edges the user enters for later.
        int edges;

        vertices = getVertices();

        edges = getEdges(vertices);
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        
        for (int i = 0; i < vertices;i++)
        {
            verticesArray.add(new Vertex(alphabet[i]));
        }
        for (Vertex v : verticesArray)
        {
            System.out.println(v.id);
        }
        
        while (true)
        {
            
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
        //vertValue: Should hold the number of vertices from the user's input.
        int edgeValue = 0;

        //isValid:  If this is true, then the user entered valid input.
        boolean isValid = false;

        System.out.println("Please enter a number of edges for the Dollar Game.");

        System.out.println("The minimum amount of edges required is " + (vertices - 1));

        do {

            try {
                edgeValue = reader.nextInt();

                if (edgeValue >= (vertices - 1)) {
                    //If the input is valid, isValid = true.
                    isValid = true;

                } else {
                    //The user entered an integer, but out of bounds.
                    System.out.println("Error.\nInvalid Value\nPlease enter an whole number value greater than " + vertices);

                    isValid = false;

                }

            } catch (InputMismatchException e) {

                //The user entered something that wasn't an integer.
                System.out.println("Error.\nInvalid Entry\n Please enter an whole number value greater than " + vertices);

                isValid = false;

            }

        } while (isValid == false);

        return edgeValue;
    }

}
