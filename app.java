import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class app {
    private static final String FILE = "data.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to my CRUD APP! Here's the menu: ");
            System.out.println("________________________________________");
            System.out.println("1. Create a new item");
            System.out.println("2. Read items");
            System.out.println("3. Update an item");
            System.out.println("4. Delete an item");
            System.out.println("5. Exit");
            System.out.println("________________________________________");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    System.out.print("Enter an ID: ");
                    int id = scanner.nextInt();
                    System.out.print("First Name: ");
                    String firstName = scanner.next();
                    scanner.nextLine(); 
                    System.out.print("Last Name: ");
                    String lastName = scanner.nextLine();
                    create(id, firstName, lastName);
                    break;
                case 2:
                    List<Item> items = readFile();
                    for (Item item : items) {
                        System.out.println("ID: " + item.getId() + ", First Name: " + item.getFirstName() + ", Last Name: " + item.getLastName());
                    }
                    break;
                case 3:
                    System.out.print("Enter the ID of the item to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the new name: ");
                    String newFirstName = scanner.nextLine();
                    System.out.print("Enter new last name: ");
                    String newLastName = scanner.nextLine();
                    updateItem(updateId, newFirstName, newLastName);
                    break;
                case 4:
                    System.out.print("Enter the ID of the item to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteItem(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                default:
                    System.out.println("Please select a valid option.");
            }
        }
    }
// Creates data for data.txt
    public static void create(int id, String firstName, String lastName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE, true))) {
            writer.println(id + ", " + firstName + ", " + lastName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// Reads data from data.txt
    public static List<Item> readFile() {
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                items.add(new Item(id, firstName, lastName));
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
// Updates fields in the data.txt 
    public static void updateItem(int id, String newFirst, String newLast) {
        List<Item> items = readFile();
        for (Item item : items) {
            if (item.getId() == id) {
                item.setFirstName(newFirst);
                item.setLastName(newLast);
                break;
            }
        }
        writeItems(items);
    }
// Deletes item from file if the ID matches one from data.txt
    public static void deleteItem(int id) {
        List<Item> items = readFile();
        items.removeIf(item -> item.getId() == id);
        writeItems(items);
    }
// Writes items from List<Item> to data.txt
private static void writeItems(List<Item> items) {
    try (PrintWriter writer = new PrintWriter(FILE)) {
        for (Item item : items) {
            writer.println(item.getId() + "," + item.getFirstName() + "," + item.getLastName());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
// Constructor section
class Item {
    private int id;
    private String firstName;
    private String lastName;

    public Item(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
