import java.util.Scanner;
import java.util.InputMismatchException;

class Node {
    String title;
    Node next;
    Node prev;

    public Node(String title) {
        this.title = title;
        this.next = null;
        this.prev = null;
    }
}

class BookReader {
    private Node head = null;
    private Node current = null;

    public void addBook(String title) {
        Node newNode = new Node(title);

        // If head is null, head and current should be equal to newNode.
        // If not, the next current should be the newNode, and the previous newNode should be the current, thus just making current and newNode the same.

        if (head == null) {
            head = newNode;
            current = newNode;
        } else {
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }

        System.out.println("Book added: " + title);
    }

    public void removeBook(String title) {
        // If head is null, then the system should be empty.
        // Like this:
        if (head == null) {
            System.out.println("System is empty.");
            return;
        }

        // Now that the head isn't null, we have to make a variable "temp" that acts as substitute for the head.
        // Note that temp should ALWAYS be a substitute to head, not current.
        Node temp = head;

        // We use a while loop because this is a noncircular list.
        // Instead of referencing temp to head, we condition the loop to stop if the temp becomes null.
        while (temp != null) {
            // We can just use .equalsIgnoreCase, but this time, let's go with manual.
            // Let's assign a boolean we'll cancel if the conditions aren't met.
            boolean isSame = true;

            // Now, we should FIRST check if the input length is the same as the target length.
            if (temp.title.length() != title.length()) isSame = false;
            // ALWAYS use temp.title because that serves as our input the program will try to reference to the target.
            else {
                for (int i = 0; i < title.length(); i++) {
                    char c1 = temp.title.charAt(i);
                    char c2 = title.charAt(i);

                    if (c1 >= 'A' && c1 <= 'Z') c1 += 32;

                    if (c2 >= 'A' && c2 <= 'Z') c2 += 32;

                    if (c1 != c2) {
                        isSame = false;
                        break;
                    }
                }
            }

            if (isSame) {
                // If the next temp is just equal to temp (head), then head and current should be null.
                // If not, then we proceed to another condition:
                // If temp is equal to head, head should be the next temp.
                // If head is not null, then the previous head should be, thus deleting the previous head.
                // if temp is NOT equal to head, we can just delete the previous temp.
                // Now, if the next temp is not null, we can delete the next temp instead.
                // If temp is equal to current, we move the temp to the next or previous, depending on whether the next temp is null.
                // ONLY APPLICABLE TO NONCIRCULAR LISTS!
                if (temp.next == null && temp.prev == null) {
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

                    if (temp == current) {
                        if (temp.next != null) current = temp.next;
                        else current = temp.prev;
                    }
                }

                // Printing should ALWAYS be inside the if statement.
                System.out.println("Book removed: " + title);
                return;
            }

            // Lastly, we always have to move the temp in the end while still being INSIDE the do-while loop.
            // Therefore, the program stops if temp reaches head.
            temp = temp.next;
        }

        // This code should be OUTSIDE the do-while loop since it can only be reached once isSame is false.
        System.out.println("Book not found.");
    }

    public void nextBook() {
        // We just have to confirm that both the current and the next are not null.
        // If it's not null, we can just move current to the next.
        if (current == null || current.next == null) {
            System.out.println("No new book.");
        } else {
            current = current.next;
            System.out.println("Current book: " + current.title);
        }
    }

    public void previousBook() {
        if (current == null || current.prev == null) {
            System.out.println("No previous book.");
        } else {
            current = current.prev;
            System.out.println("Current book: " + current.title);
        }
    }

    public void displayBooks() {
        // If head is null, the system/list is automatically empty.
        if (head == null) {
            System.out.println("System is empty.");
            return;
        }

        // Just like before, we want something to move across the nodes, so we'll use temp as a substitute for head again.
        Node temp = head;

        System.out.println("\nBook System:");
        // Like before, we condition another while loop to stop if the temp becomes null.
        while (temp != null) {
            // Like the current earlier, we specify the title using the variable temp.
            if (temp == current) System.out.println("-> " + temp.title + " (Current)");
            else System.out.println("   " + temp.title);

            // ALWAYS have this code if we're using a substitute like temp.
            temp = temp.next;
        }
    }
}
void main() {
    Scanner scanner = new Scanner(System.in);
    BookReader bookReader = new BookReader();

    int choice;

    System.out.println("\n=== Book Reader System ===");
    System.out.println("1. Add New Book");
    System.out.println("2. Remove Book");
    System.out.println("3. Next Book");
    System.out.println("4. Previous Book");
    System.out.println("5. Show Book System");
    System.out.println("6. Exit");

    // This time, we use a do-while loop that has to run at least ONCE before a switch-case decides whether to continue.
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
                System.out.print("Enter new book: ");
                String input = scanner.nextLine();
                bookReader.addBook(input);
                break;

            case 2:
                System.out.print("Enter book name to remove: ");
                String remove = scanner.nextLine();
                bookReader.removeBook(remove);
                break;

            case 3:
                bookReader.nextBook();
                break;

            case 4:
                bookReader.previousBook();
                break;

            case 5:
                bookReader.displayBooks();
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
