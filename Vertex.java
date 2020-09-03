package project2;

public class Vertex {

	private char name;
	
	public int dollars;
	
	
	
	public Vertex(char n)
	{
		name = n;
		
	}
	
	public Vertex(char n, int d)
	{
		name = n;
		dollars = d;
		
	}
	
	public int getDollars()
	{
		return dollars;
		
	}
	
	public void setDollars(int d)
	{
		dollars = d;
		
	}
	
	
}
