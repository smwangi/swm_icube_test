import java.util.Scanner;

public class Knapscack {
    
    public static void main(String[] args) {
        
        int n = 0, limit = 0;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter no. of items:");
        n = s.nextInt();

        System.out.print("Enter limit weight:");
        limit = s.nextInt();

        int weight[] = new int[n];
        int value[] = new int[n];
        System.out.println("Enter weights and values separated by space, example. 4 10");
        for(int i = 0; i < n; i++)
        {
            weight[i] = s.nextInt();
            value[i] = s.nextInt();
        }
        //int[] weight = {5,4,6,4};
        //int[] val = {10,40,30,50};

        System.out.println(knapsack(weight, value, 10, n));
    }

    private static int knapsack (int[] weight, int[] val, int W, int n){
        if (n <= 0 || W <= 0) {
            return 0;
        }
     
        //Dynamic 2-dimensional table with dimensions from 0 to n and 0 to W
        int[][] k = new int[n + 1][W + 1];
        for (int j = 0; j <= W; j++) {
            k[0][j] = 0;
        }
     
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) { 
                if (weight[i - 1] > j) {
                    k[i][j] = k[i - 1][j];
                } else {
                    k[i][j] = Math.max( k[i - 1][j], k[i - 1][j - weight[i - 1]] + val[i - 1]);
                }
            }
        }
        return k[n][W];
    }
}