import java.util.ArrayList;
import java.util.Scanner;

// Class representing a person in the family tree
class FamilyMember {
    String name;
    ArrayList<FamilyMember> children;

    FamilyMember(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    // Method to add a child to this family member
    void addChild(FamilyMember child) {
        children.add(child);
    }

    // Recursive method to display the family tree
    void display(String prefix) {
        System.out.println(prefix + "|-- " + name);
        for (FamilyMember child : children) {
            child.display(prefix + "    ");
        }
    }
}

// Class representing the family tree
class FamilyTree {
    FamilyMember root;

    FamilyTree(String rootName) {
        root = new FamilyMember(rootName);
    }

    // Recursive method to find a family member by name
    FamilyMember findMember(FamilyMember current, String name) {
        if (current.name.equalsIgnoreCase(name)) {
            return current;
        }
        for (FamilyMember child : current.children) {
            FamilyMember found = findMember(child, name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    // Method to add a family member as a child to a specific parent
    void addFamilyMember(String parentName, String childName) {
        FamilyMember parentMember = findMember(root, parentName);
        if (parentMember != null) {
            parentMember.addChild(new FamilyMember(childName));
            System.out.println(childName + " has been added as a child of " + parentName);
        } else {
            System.out.println("Parent not found!");
        }
    }

    // Display the entire family tree
    void displayFamilyTree() {
        System.out.println("\nFamily Tree:");
        root.display("");
    }
}

// Main class
public class FamilyTreeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the family root: ");
        String rootName = scanner.nextLine();

        FamilyTree familyTree = new FamilyTree(rootName);

        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a family member");
            System.out.println("2. Display family tree");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the parent: ");
                    String parentName = scanner.nextLine();
                    System.out.print("Enter the name of the child: ");
                    String childName = scanner.nextLine();
                    familyTree.addFamilyMember(parentName, childName);
                    break;

                case 2:
                    familyTree.displayFamilyTree();
                    break;

                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

