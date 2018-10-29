import java.util.Random;

public class Rndm {
    public static void main(String[] args) {
        // Bygger et nettverk av byer
        int size = 100; //Antall byer
        int cost = 100; //Kostnad å dra mellom byer (Mellom 1 og cost)
        int[][] cities = new int[size][size]; // Tabellen for å holde byer
        boolean[][] visited = new boolean[size][size]; // Tabellem for besøkte byer
        int route[] = new int[cities.length]; // Lagrer stien

        //Trekker tilfeldig kostnad mellom byer
        for (int i = 0; i < cities.length; i++) {
            for (int j = 0; j < cities.length; j++) {
                cities[i][j] = randomNumber(cost);
                visited[i][j] = false;
            }
        }

        //Eliminerer link til seg selv (diagonalen)
        for (int i = 0; i < cities.length; i++) {
            for (int j = 0; j < cities.length; j++) {
                if (i == j) {
                    cities[i][j] = 0;
                }
            }
        }

        // TESTING av forskjellige algoritmer
        int resultat;
        resultat = greedyAlg(cities, visited, size);
        System.out.println("total cost greedy algorithm: " + resultat + "\n");
        visited = resetVisited(visited);
        resultat = randomAlg(cities, visited, size);
        System.out.println("total cost random algorithm: " + resultat + "\n");
        visited = resetVisited(visited);
        resultat = iterativeRandomAlg(cities, visited, size);
        System.out.println("total cost iterative random algorithm: " + resultat + "\n");

    } // Main END

    // Trekker et tilfeldig tall og returnerer det (Ferdig)
    public static int randomNumber(int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max) + 1;
        return randomNum;
    }

    // Random algorithm (Ferdig)
    public static int randomAlg(int[][] cities, boolean[][] visited, int size) {
        int a = randomNumber(size) - 1;
        int b = randomNumber(size) - 1;
        int total = 0;
        for (int i = 0; i <= cities.length - 1; i++) {

            if (a != b && !visited[a][b] && !visited[b][a]) {
                visited[a][b] = true;
                visited[b][a] = true;
                //System.out.print(a + "-" + b + " cost: "+ cities[a][b] + " -> \n");
                total += cities[a][b];
            }
            a = b;
            b = randomNumber(size) - 1;
        }
        //System.out.println("total cost random algorithm: " + total + "\n");
        return total;
    }

    // Iterative Random Algorithm
    public static int iterativeRandomAlg(int[][] cities, boolean[][] visited, int size){
        int iterativeAmount = 100;
        int currentRoute;
        int bestRoute = Integer.MAX_VALUE;
        for(int i = 0; i < iterativeAmount; i++){
            currentRoute = randomAlg( cities, visited, size);
            if(currentRoute < bestRoute){
              bestRoute = currentRoute;
            }
            resetVisited(visited);
        }
        //System.out.println("total cost iterative random algorithm: " + bestRoute + "\n");
        return bestRoute;
    }

    // Greedy Algorithm (Ferdig)
    public static int greedyAlg(int[][] cities, boolean[][] visited, int size) {
        int a = randomNumber(size) - 1;
        int total = 0;
        int cost = 0;
        int best;
        int current = 0;
        int previous;
        for (int i = 0; i <= cities.length - 1; i++) {
            best = Integer.MAX_VALUE;
            for (int j = 0; j < cities.length - 1; j++) {
                if (cities[a][j] < best && a != j && !visited[a][j] && !visited[j][a]) {
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
            //System.out.print("cost: " + cost + " city " + previous + " -> " + current);
        }
        //System.out.println("total cost greedy: " + total + "\n");
        return total;
    }

    
    // Resetter besøkte byer (Ferdig)
    public static boolean[][] resetVisited(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                visited[i][j] = false;
            }
        }
        return visited;
    }
}