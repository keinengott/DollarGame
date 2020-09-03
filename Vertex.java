




/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class Vertex {

	private char name;
	
	public int dollars;
	
	
	
	public Vertex(char n)
	{
		name = n;
		
		dollars = 0;
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
	
	public char getName()
	{
		
		return name;
	}
	
	public String toString()
	{
		
		if(dollars >= 0)
		return name + ": $" + dollars;
		else
		{
			return name + ": -$" + (-1)*dollars;
		}
		
	}
	
	
}
