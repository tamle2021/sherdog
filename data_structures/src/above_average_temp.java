/*
Find average of temperatures and number of days above average.


**** above average temperature ****

 */
import java.util.*;

class AboveAvgTemperature {
    public static void main(String[] args) {
        System.out.println("**** above average temperature ****");
        Scanner console = new Scanner(System.in);
        System.out.print("how many days of temperatures? ");
        int numDays = console.nextInt();
        int[] temps = new int[numDays];
        // record temperatures and find average
        int sum = 0;
        for (int i = 0; i < numDays; i++) {
            System.out.print("day " + (i + 1) + " high temperature: ");
            temps[i] = console.nextInt();
            sum += temps[i];
        }

        double average = sum / numDays;
        // count days above average
        int above = 0;
        for (int i = 0; i < temps.length; i++) {
            if (temps[i] > average) {
                above++;
            }
        }
        System.out.println();
        System.out.println("average temperature: " + average);
        System.out.println(above + " days above average");
    }
}
