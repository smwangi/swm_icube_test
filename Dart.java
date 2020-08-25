import java.util.Scanner;

public class Dart {

    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
       
        System.out.println("Enter value of x.");
        while(!scan.hasNextInt()) scan.next();
        int x = scan.nextInt();

        System.out.println("Enter value of y.");
        while(!scan.hasNextInt()) scan.next();
        int y = scan.nextInt();

        scan.close();
        System.out.format("Your score is: %d",score(x, y));
    }

    private static  int score(int x, int y) {
        double distance = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        
        if (distance <= 1.0) {
            return 10;
        } else if (distance <= 5.0) {
            return 5;
        } else if (distance <= 10.0) {
            return 1;
        }

        return 0;
    }
}