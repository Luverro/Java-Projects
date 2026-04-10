import java.util.Scanner;
import java.util.InputMismatchException;

class Node {
    String task;
    Node next;
    Node prev;

    public Node(String task) {
        this.task = task;
        this.next = null;
        this.prev = null;
    }
}

class TaskManager {
    private Node head = null;
    private Node current = null;

    public void addTask(String task) {
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
            current = newNode;
        } else {
            current.next = null;

            newNode.prev = current;
            current.next = newNode;
            current = newNode;
        }

        System.out.println("Task added: " + task);
    }

    public void removeTask(String task) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Node temp = head;

        do {
            boolean isSame = true;

            if (temp.task.length() != task.length()) isSame = false;
            else {
                for (int i = 0; i < task.length(); i++) {
                    char c1 = temp.task.charAt(i);
                    char c2 = task.charAt(i);

                    if (c1 >= 'A' && c1 <= 'Z') c1 += 32;

                    if (c2 >= 'A' && c2 <= 'Z') c2 += 32;

                    if (c1 != c2) {
                        isSame = false;
                        return;
                    }
                }
            }

            if (isSame) {
                if (temp.next == temp) {
                    head = null;
                    current = null;
                } else {
                    if (temp == head) {
                        head = temp.next;

                        if (head != null) head.prev = null;
                    } else {
                        temp.prev.next = temp.next;

                        if (temp.next != null) temp.next.prev = temp.prev;
                    }

                    if (temp == current) current = temp.next;
                }

                System.out.println("Task removed: " + task);
                return;
            }

            temp = temp.next;
        } while (temp != head);

        System.out.println("Task not found.");
    }

    public void nextTask() {
        if (current == null || current.next == null) System.out.println("No next task.");
        else {
            current = current.next;
            System.out.println("Current task: " + current.task);
        }
    }

    public void previousTask() {
        if (current == null || current.prev == null) System.out.println("No previous task.");
        else {
            current = current.prev;
            System.out.println("Current task: " + current.task);
        }
    }

    public void displayTask() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Node temp = head;

        System.out.println("\nTask Manager:");

        while (temp != null) {
            if (temp == current) System.out.println("-> " + temp.task + " (Current)");
            else System.out.println("   " + temp.task);

            temp = temp.next;
        }
    }
}

void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TaskManager task = new TaskManager();

    int choice;

    System.out.println("\n=== Task Manager ===");
    System.out.println("1. Add New Task");
    System.out.println("2. Remove Task");
    System.out.println("3. Show Next Task");
    System.out.println("4. Show Previous Task");
    System.out.println("5. Display Task List");
    System.out.println("6. Exit");

    do {
        while (true) {
            try {
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        switch (choice) {
            case 1:
                System.out.print("Enter task: ");
                String input = scanner.nextLine();
                task.addTask(input);
                break;

            case 2:
                System.out.print("Enter task name to remove: ");
                String remove = scanner.nextLine();
                task.removeTask(remove);
                break;

            case 3:
                task.nextTask();
                break;

            case 4:
                task.previousTask();
                break;

            case 5:
                task.displayTask();
                break;

            case 6:
                System.out.println("Exiting program...");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    } while (choice != 6);

    scanner.close();
}
