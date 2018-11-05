import java.util.Random;

public class graphColoring 
{
	public static void main(String[] args) 
	{
		int nodeSize = 10; //antall nodes
		int[] nodes1 = new int[nodeSize]; //oppretter array for nodes
		int[] nodes2 = new int[nodeSize];
		
		int brw = 3; //black red white
		int nodeInsertTemp = Integer.MAX_VALUE;
		
		nodes1 = createArray(nodes1, brw, nodeInsertTemp); //fyller array med tilfeldige tall
		nodes2 = createArray(nodes2, brw, nodeInsertTemp);
		
		printArray(nodes1); //sender inn i metode som oversetter arrayet til black red white
		printArray(nodes2);
		
		//lag metode som bytter innholdet i de to arrayene mellon to tilfeldige tall
		//3 if tester som gjør at det går fra minste tall til høyeste
		
		
	}
	
	public static int randomNumber(int s) //returnerer et tilfeldig tall med max verdi = s
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(s);
		return randomNum;
	}
	
	public static int[] createArray(int []a, int b, int c)
	{
		for(int i=0; i<a.length; i++)
		{
			int nodeInsert = randomNumber(b);
			while(nodeInsert == c) //trekker nytt tall så lenge forrige er likt det nye
			{
				nodeInsert = randomNumber(b);
			}
			a[i] = nodeInsert; 
			c = nodeInsert;
		}
		return a;
	}
	
	public static void printArray(int []a) //oversetter arrayet
	{
		for(int i = 0; i<a.length; i++)
		{
			if(a[i] == 0)
			{
				System.out.print("[B] ");
			}
			else if(a[i] == 1)
			{
				System.out.print("[R] ");
			}
			else if(a[i] == 2)
			{
				System.out.print("[W] ");
			}
		}
		System.out.println("");
	}

	/*
	public static int fitness(int []a)
	{
		int fitnessSatisfaction = 0;
		
		for(int i = 0; i<a.length; i++)
		{
			if(a[i] == a[i+1] && i<=(a.length-1))
			{
				fitnessSatisfaction++;
			}
		}
		
		return fitnessSatisfaction;
	}
	*/
	
} //Class END
