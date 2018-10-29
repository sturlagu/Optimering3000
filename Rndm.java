import java.util.Random;

public class Rndm {

	
	public static void main(String[] args) 
	{
		
		int size = 100;
		int cost = 1000;
		int[][] cities = new int[size][size];	
		boolean[][] visited = new boolean[size][size];	
		//funksjon som gir tilfeldige tall i hele arrayet
		for(int i=0; i<cities.length; i++)
		{
			for(int j=0; j<cities.length; j++)
			{
				cities[i][j] = randomNumber(cost);
				visited[i][j] = false;
				
			}
			
		}
		
		//funksjon som nuller
		for(int a=0; a<cities.length; a++)
		{
			for(int b=0; b<cities.length; b++)
			{
				if(a==b)
				{
					cities[a][b] = 0;

				}
				//System.out.print(cities[a][b]+"| ");
			}
			//System.out.println("");
		}

		for (int greedy = 0; greedy < 1; greedy++)
		{
			greedyAlg(cities, visited, size);
			for(int x=0; x<cities.length; x++)
				{
					for(int y=0; y<cities.length; y++)
					{
						visited[x][y] = false;	
					}
					
				}
		}
		

		//iterative random
					
		int best = Integer.MAX_VALUE;
		int current = 0;
		
		for (int i = 0; i < 100; i++)
		{

			
			current = randomAlg(cities, visited, size);
			if (current < best)
			{
				best = current;
			}
			for(int x=0; x<cities.length; x++)
			{
				for(int y=0; y<cities.length; y++)
				{
					visited[x][y] = false;	
				}
				
			}

		}
		System.out.println("Best: " +  best);
	}

		
		
	

	
	public static int randomNumber(int max) //returnerer et tilfeldig tall
	{
		Random rand = new Random();
		int randomNum = rand.nextInt(max)+1;
		return randomNum;
	}

	public static int randomAlg(int[][] cities, boolean [][] visited, int size) 
	{
		int a = randomNumber(size) -1;
		int b = randomNumber(size) -1;
		int total = 0;
		for (int i = 0; i <= cities.length-1; i++)
		{
			
			if (a!=b && visited[a][b] == false && visited[b][a] == false)
			{
				visited[a][b] = true;
				visited[b][a] = true;
				//System.out.print(a + "-" + b + " cost: "+ cities[a][b] + " -> \n");
				total += cities[a][b];
			}
			a = b;
			b = randomNumber(size)-1;

		}
		//System.out.println("total cost: " + total + "\n");
		return total;
	}

	public static int greedyAlg(int[][] cities, boolean [][] visited, int size) 
	{
		int a = randomNumber(size) -1;
		int b = randomNumber(size) -1;
		int total = 0;
		int cost = 0;
		int best = cities[a][b];
		int current = 0;
		int previous = b;

		for (int i = 0; i <= cities.length-1; i++)
		{
			best = 999999; //Integer.MAX_VALUE;

			for (int j = 0; j < cities.length -1; j++)
			{
				if (cities[a][j] < best && a!=j && visited[a][j] == false && visited[j][a] == false)
				{
					best = cities[a][j];
					current = j;		
				}
			}

			visited[a][current] = true;
			visited[current][a] = true;
			total += cities[a][current];
			
			cost = cities[a][current];
			previous = a;
			a = current;
			System.out.print("cost: " + cost + " city "+ previous + " -> " + current);
			System.out.println("");
			//b = randomNumber(size)-1;

		}
		System.out.println("total cost greedy: " + total + "\n");
		return total;
	}



}
