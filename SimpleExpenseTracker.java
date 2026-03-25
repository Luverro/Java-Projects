import java.util.Scanner;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

public class SimpleExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int SIZE = 10;
        int[] expenses = new int[SIZE];

        double totalExpenses = 0;

        System.out.println("=== Expense Tracker ===");

        for (int i = 0; i < SIZE; i++) {
            System.out.println("Expense " + (i+1));

            while (true) {
                System.out.print("Enter amount: ");
                if (scanner.hasNextInt()) {
                    expenses[i] = scanner.nextInt();
                    if (expenses[i] >= 0) break;
                    else System.out.println("Input cannot be negative. Try again.");
                } else {
                    System.out.println("Invalid input. Please try again.");
                    scanner.next();
                }
            }
        }

        double firstExpenses = expenses[0];
        double highestExpenses = firstExpenses;
        double lowestExpenses = firstExpenses;

        for (int i = 0; i < SIZE; i++) {
            firstExpenses = expenses[i];

            totalExpenses += firstExpenses;

            if (firstExpenses > highestExpenses) {
                highestExpenses = firstExpenses;
            }

            if (firstExpenses < lowestExpenses) {
                lowestExpenses = firstExpenses;
            }
        }

        double averageExpenses = totalExpenses / SIZE;

        System.out.println("=== Expense Summary ===");
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Highest Expense: " + highestExpenses);
        System.out.println("Lowest Expense: " + lowestExpenses);
        System.out.println("Average Expense: " + averageExpenses);

        scanner.close();
    }
}// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
