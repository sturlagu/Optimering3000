import java.util.Random;

public class graphColoring {
    private static final int nodeSize = 4; //antall nodes
    private static int[][] child1 = new int[nodeSize][nodeSize];
    private static int[][] child2 = new int[nodeSize][nodeSize];
    private static int[][] child3 = new int[nodeSize][nodeSize];

    public static void main(String[] args) {

        int[][] nodes1 = new int[nodeSize][nodeSize]; //oppretter array for nodes
        int[][] nodes2 = new int[nodeSize][nodeSize];


        int brw = 3; //black red white
        nodes1 = createArray(nodes1, brw); //fyller array med tilfeldige tall
        nodes2 = createArray(nodes2, brw);


        int endCrossOverPoint = randomNumber(nodes1.length - 1);

        System.out.println("Original parents: \n");
        printArray(nodes1); //sender inn i metode som oversetter arrayet til black red white
        printFitness(nodes1);
        printArray(nodes2);
        printFitness(nodes2);

        //single-point crossover
        System.out.println("Single-point crossover\nChildren:");
        singlePointCrossOver(nodes1, nodes2, startCrossOverPoint());
        printArray(child1);
        printFitness(child1);
        printArray(child2);
        printFitness(child2);
        printArray(child3);
        printFitness(child3);

/*        if (findFitness(child1)> findFitness(child2)){
            singlePointCrossOver(child1, child3, startCrossOverPoint());
        }*/
        //two-point crossover
/*
        System.out.println("Two-point crossover\nChildren:");
        twoPointCrossOver(nodes1, nodes2, startCrossOverPoint, endCrossOverPoint);
        printArray(child1);
        printFitness(child1);
        printArray(child2);
        printFitness(child2);
*/

        //lag metode som bytter innholdet i de to arrayene mellon to tilfeldige tall
        //3 if tester som gjoer at det gaar fra minste tall til hoeyeste


    }
    public static int startCrossOverPoint(){

        return randomNumber(4);
    }


    private static int randomNumber(int s) //returnerer et tilfeldig tall med max verdi = s
    {
        Random rand = new Random();
        return rand.nextInt(s);
    }

    private static int[][] createArray(int[][] a, int b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                int nodeInsert = randomNumber(b);
                a[i][j] = nodeInsert;
            }
        }
        return a;
    }

    private static void printArray(int[][] a) //oversetter arrayet
    {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++){
                if (a[i][j] == 0) {
                    System.out.print("[B] ");
                } else if (a[i][j] == 1) {
                    System.out.print("[R] ");
                } else if (a[i][j] == 2) {
                    System.out.print("[W] ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    private static int findFitness(int[][] a) {
        int fitnessSatisfaction = 0;

        for (int x = 0; x < a.length; x++) {
            for (int y = 0; y < a.length; y++){
                if (x < (a.length - 1)) {
                    if (a[x][y] == a[x + 1][y]){
                        fitnessSatisfaction++;
                    }
                }
                else if (x > 0){
                    if (a[x][y] == a[x-1][y]){
                        fitnessSatisfaction++;
                    }
                }
                else if (y > 0){
                    if (a[x][y] == a[x][y+1]){
                        fitnessSatisfaction++;
                    }
                }
                else if (y < (a.length - 1)){
                    if (a[x][y] == a[x][y+1]){
                        fitnessSatisfaction++;
                    }
                }
            }
        }

        return fitnessSatisfaction;
    }

    private static void printFitness(int[][] array) {
        System.out.println("Fitness: " + findFitness(array) + "\n------\n");
    }

    private static void singlePointCrossOver(int[][] parent1, int[][] parent2, int crossOverPoint) {
        System.out.println("Crossover point: " + crossOverPoint);
        for (int i = 0; i < parent1.length; i++) {
            if (i < crossOverPoint) {
                child1[i] = parent1[i];
                child2[i] = parent2[i];
            } else {
                child1[i] = parent2[i];
                child2[i] = parent1[i];
            }
        }
    }

    private static void twoPointCrossOver(int[][] parent1, int[][] parent2, int startCrossOverPoint, int endCrossOverPoint) {

        System.out.println("Crossover start point: " + startCrossOverPoint);
        System.out.println("Crossover end point: " + endCrossOverPoint);

        for (int i = 0; i < parent1.length; i++) {
            if (i > startCrossOverPoint && i < endCrossOverPoint) {
                child1[i] = parent1[i];
                child2[i] = parent2[i];
            } else {
                child1[i] = parent2[i];
                child2[i] = parent1[i];
            }
        }
    }

} //Class END