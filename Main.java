import java.util.Scanner;

class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int SIZE = 7;

        int[] days = new int[SIZE];

        int hotDays = 0;
        double totalTemp = 0;

        System.out.println("=== Weekly Temperature Tracker ===");

        for (int i = 0; i < SIZE; i++) {
            System.out.println("Day " + (i+1));

            while (true) {
                System.out.print("Enter temperature: ");
                if (scanner.hasNextInt()) {
                    days[i] = scanner.nextInt();
                    if (days[i] >= 0) break;
                    else System.out.println("Input cannot be negative. Try again.");
                } else {
                    System.out.println("Invalid input. Please try again.");
                    scanner.next();
                }
            }
        }

        double firstTemp = days[0];
        double highestTemp = firstTemp;
        double lowestTemp = firstTemp;

        for (int i = 0; i < SIZE ; i++) {
            firstTemp = days[i];

            totalTemp += firstTemp;

            if (firstTemp > highestTemp) highestTemp = firstTemp;

            if (firstTemp < lowestTemp) lowestTemp = firstTemp;

            if (days[i] > 30) hotDays++;
        }

        double averageTemp = totalTemp / SIZE;

        System.out.println("=== Temperature Summary ===");
        System.out.println("Average Temperature: " + averageTemp);
        System.out.println("Highest Temperature: " + highestTemp);
        System.out.println("Lowest Temperature: " + lowestTemp);
        System.out.println("Hot Days: " + hotDays);

        scanner.close();
    }
}
