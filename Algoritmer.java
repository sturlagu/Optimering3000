import static java.lang.Math.random;
import java.util.Random;

public class Algoritmer {
    public static void main(String[] args) {

        // Bygger et nettverk av byer
        int size = 100; //Antall byer
        int cost = 100; //Kostnad � dra mellom byer (Mellom 1 og cost)
        int[][] cities = new int[size][size]; // Tabellen for � holde byer
        int[][] oldSolution = new int[size][size];
        int[][] bestSolution = new int[size][size];
        boolean[][] visited = new boolean[size][size]; // Tabellem for bes�kte byer

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
        
        // Forskjellige variabler for � holde resultater
        int randomResultat;
        int iterativeResultat;
        int greedyResultat;
        int greedyOptResultat;
        int greedyRandomOptResultat;

        // TESTING av forskjellige algoritmer

        // RANDOM ALGORITHM
        randomResultat = randomAlg(cities, visited, oldSolution, size);
        System.out.print("Random resultat: " + randomResultat + "\n");
        display(oldSolution);


        greedyOptResultat = greedyOpt(cities, visited, size, randomResultat, oldSolution, bestSolution, 1);
        System.out.print("Greedy optimalisert på random: " + greedyOptResultat + "\n");
        display(bestSolution);

        greedyRandomOptResultat = greedyRandomOpt(cities, visited, size, randomResultat, oldSolution, bestSolution, 1);
        System.out.println("Greedy random optimalisert på random: " + greedyRandomOptResultat);
        display(bestSolution);
        
        System.out.println("");
        
        // ITERATIV RANDOM ALGORITHM
        iterativeResultat = iterativeRandomAlg(cities, visited, oldSolution, size);
        System.out.print("Iterativ Random resultat: " + iterativeResultat + "\n");
        display(oldSolution);

        
        greedyOptResultat = greedyOpt(cities, visited, size, iterativeResultat, oldSolution, bestSolution, 2);
        System.out.print("Greedy optimalisert på iterativ random: " + greedyOptResultat + "\n");
        display(bestSolution);
        
       
        greedyRandomOptResultat = greedyRandomOpt(cities, visited, size, iterativeResultat, oldSolution, bestSolution, 2);
        System.out.println("Greedy random optimalisert på iterativ random: " + greedyRandomOptResultat);
        display(bestSolution);
        
        System.out.println("");
        
        // GREEDY ALGORTIHM
        greedyResultat = greedyAlg(cities, visited, oldSolution ,size);
        System.out.print("Greedy resultat: " + greedyResultat + "\n");
        display(oldSolution);
        
        greedyOptResultat = greedyOpt(cities, visited, size, greedyResultat, oldSolution, bestSolution, 3);
        System.out.print("Greedy optimalisert på greedy: " + greedyOptResultat + "\n");
        display(bestSolution);
        
        greedyRandomOptResultat = greedyRandomOpt(cities, visited, size, greedyResultat, oldSolution, bestSolution, 3);
        System.out.println("Greedy random optimalisert på greedy: " + greedyRandomOptResultat);
        display(bestSolution);
        
        
    } // Main END

    // Trekker et tilfeldig tall og returnerer det (Ferdig)
    public static int randomNumber(int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max) + 1;
        return randomNum;
    }

    // Resetter bes�kte byer (Ferdig)
    public static boolean[][] resetVisited(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                visited[i][j] = false;
            }
        }
        return visited;
    }

    // Flushing Array
    public static void flushArray(int[][] a){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length; j++){
                a[i][j] = 0;
            }
        }
    }
    // Copy array
    public static void copyArray(int[][] a, int[][] c){
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length; j++){
                a[i][j] = c[i][j];
            }
        }
    }

    public static void display(int[][] a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if(a[i][j] != 0)
                    System.out.print("" +a[i][j]+" -> ");
            }
        }
        System.out.println("");
    }

    // Random algorithm (Ferdig)
    public static int randomAlg(int[][] cities, boolean[][] visited, int[][] oldSolution, int size) {
        visited = resetVisited(visited);
        int a = randomNumber(size) - 1;
        int b;
        int total = 0;
        int counter = 1;
        flushArray(oldSolution);
        while(counter != cities.length){
            do{
                b = randomNumber(size) - 1;
            }while(a == b || visited[a][b] || visited[b][a] || cities[a][b] == 0);
            
            visited[a][b] = true;
            visited[b][a] = true;
            oldSolution[a][b] = cities[a][b];
            total += cities[a][b];
            counter++;
            a = b;
        }
        //System.out.println("total cost random algorithm: " + total + "\n");
        return total;
    }

    // Iterative Random Algorithm
    public static int iterativeRandomAlg(int[][] cities, boolean[][] visited, int[][] oldSolution, int size){
        visited = resetVisited(visited);
        int iterativeAmount = 100;
        int currentRoute;
        int bestRoute = Integer.MAX_VALUE;
        int [][] tempSolution = new int[size][size];
        for(int i = 0; i < iterativeAmount; i++){
            currentRoute = randomAlg(cities, visited, oldSolution ,size);
            if(currentRoute < bestRoute){
                bestRoute = currentRoute;
                copyArray(tempSolution, oldSolution);
            }
        }
        copyArray(oldSolution, tempSolution);
        return bestRoute;
    }

    // Greedy Algorithm (Ferdig)
    public static int greedyAlg(int[][] cities, boolean[][] visited, int[][] oldSolution, int size) {
        visited = resetVisited(visited);
        int a = randomNumber(size) - 1;
        int total = 0;
        int cost = 0;
        int best;
        int current = 0;
        int previous;
        int counter = 0;
        flushArray(oldSolution);
        for (int i = 0; i < cities.length-1; i++) {
            best = Integer.MAX_VALUE;
            for (int j = 0; j < cities.length-1; j++) {
                if (cities[a][j] < best && a != j && !visited[a][j] && !visited[j][a] && cities[a][j] != 0) {
                    best = cities[a][j];
                    current = j;
                }
            }
            visited[a][current] = true;
            visited[current][a] = true;
            oldSolution[a][current] = cities[a][current];
            total += cities[a][current];
            cost = cities[a][current];
            previous = a;
            a = current;
        }
        return total;
    }

    public static int greedyOpt(int[][] cities, boolean[][] visited, int size, int oldCost, int[][] oldSolution, int[][] bestSolution, int c){
        int newCost = 0;
        int bestCost = oldCost;
        int a;
        int b;
        int[][] newSolution = new int[size][size];
        copyArray(bestSolution, oldSolution);
        int[][] tempCities = new int[size][size];
        copyArray(tempCities,cities);
        int temp[][] = new int[size][size]; // Holder verdier mens vi bytter byer;
        int maxTries = 10; // Bestemmer hvor lenge vi skal holde p�
        for(int i = 0; i < maxTries; i++){
            do{
                a = randomNumber(size) - 1; // Trekker tilfeldig by som skal
                b = randomNumber(size) - 1; // Byttes med en annen tilfeldig by
            }while(a == b);
            
            // Bytter byer
            for(int j = 0; j < tempCities.length; j++){
                temp[a][j] = tempCities[a][j];
            }
            for(int j = 0; j < tempCities.length; j++){
                tempCities[a][j] = tempCities[b][j];
            }
            for(int j = 0; j < tempCities.length; j++){
                tempCities[b][j] = temp[a][j];
            }  
            
            // Velger initiall l�sning
            if(c == 1){
                newCost = randomAlg(tempCities, visited, oldSolution, size);
                copyArray(newSolution, oldSolution);
            }
            else if(c == 2){
                newCost = iterativeRandomAlg(tempCities, visited, oldSolution, size);
                copyArray(newSolution, oldSolution);
            }
            else if(c == 3){
                newCost = greedyAlg(tempCities, visited, oldSolution, size);
                copyArray(newSolution, oldSolution);
            }
            if(newCost < oldCost){
                oldCost = newCost;
                copyArray(oldSolution, newSolution);
                if(newCost < bestCost){
                    bestCost = newCost;
                    copyArray(bestSolution, oldSolution);
                }
            }
        }
        return bestCost;
    }

    public static int greedyRandomOpt(int[][] cities, boolean[][] visited, int size, int oldCost, int[][] oldSolution, int[][] bestSolution, int c){
        int newCost = 0;
        int bestCost = oldCost;
        int a; // Trekker tilfeldig by som skal
        int b; // Byttes med en annen tilfeldig by
        int[][] newSolution = new int[size][size];
        copyArray(bestSolution, oldSolution);
        int[][] tempCities = new int[size][size];
        copyArray(tempCities, cities);
        int temp[][] = new int[size][size]; // Holder verdier mens vi bytter byer;
        int maxTries = 10; // Bestemmer hvor lenge vi skal holde p�
        double rnd;
        double probability = 0.9; // Bestemmer hvor lenge vi skal holde p�

        do{
            for(int i = 0; i < maxTries; i++){
                
                do{
                    a = randomNumber(size) - 1; // Trekker tilfeldig by som skal
                    b = randomNumber(size) - 1; // Byttes med en annen tilfeldig by
                }while(a == b); 
                
                // Bytter byer
                for(int j = 0; j < tempCities.length; j++){
                    temp[a][j] = tempCities[a][j];
                }
                for(int j = 0; j < tempCities.length; j++){
                    tempCities[a][j] = tempCities[b][j];
                } 
                for(int j = 0; j < tempCities.length; j++){
                    tempCities[b][j] = temp[a][j];
                 }
                
                // Velger initiall l�sning
                if(c == 1){
                    newCost = randomAlg(tempCities, visited, oldSolution, size);
                    copyArray(newSolution, oldSolution);
                }
                else if(c == 2){
                    newCost = iterativeRandomAlg(tempCities, visited, oldSolution, size);
                    copyArray(newSolution, oldSolution);
                }
                else if(c == 3){
                    newCost = greedyAlg(tempCities, visited, oldSolution, size);
                    copyArray(newSolution, oldSolution);
                }
                if(newCost < oldCost){
                    oldCost = newCost;
                    copyArray(oldSolution, newSolution);
                    if(newCost < bestCost){
                        bestCost = newCost;
                        copyArray(bestSolution, oldSolution);
                    }
                }
                else{
                    rnd = ((random() % 100)/100);
                    if(rnd < probability){
                        oldCost = newCost;
                        copyArray(oldSolution, newSolution);
                    }
                }
            }
            probability = probability * 0.9;
        }while(probability > 0.0000001);
        return bestCost;
    }
} // Class END