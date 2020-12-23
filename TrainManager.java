import java.util.Scanner;

// Driver class

public class TrainManager {
    /**
     * The main method runs a menu driven application which first creates
     * an empty TrainLinkedList object. The program prompts the user for a
     * command to execute an operation. Once a command has been chosen, the
     * program may ask the user for additional information if necessary, and
     * performs the operation
     */
    public static void main(String[] args){
        Scanner h = new Scanner(System.in);
        String letter = ""; // letter for the options
        TrainLinkedList train = new TrainLinkedList();

        // while the user does not enter q, keep the menu
        while (!letter.equalsIgnoreCase("q")) {
            System.out.print("(F) Cursor Forward\n" +
                    "(B) Cursor Backward\n" +
                    "(I) Insert Car After Cursor\n" +
                    "(R) Remove Car At Cursor\n" +
                    "(L) Set Product Load\n" +
                    "(S) Search For Product\n" +
                    "(T) Display Train\n" +
                    "(M) Display Manifest\n" +
                    "(D) Remove Dangerous Cars\n" +
                    "(Q) Quit" + "\n");
            System.out.print("Enter a selection: ");
            letter = h.nextLine().toUpperCase(); // turn whatever letter to
            // uppercase

            try { // try all the cases
                if (letter.equalsIgnoreCase("f")) {
                    try {
                        train.cursorForward();
                        System.out.print("Cursor moved forward." + "\n");
                    } catch (Exception e) {
                        System.out.print("No next car, cannot move cursor" +
                                " forward." + "\n");
                    }
                } else if (letter.equalsIgnoreCase("b")) {
                    try {
                        train.cursorBackward();
                        System.out.print("Cursor moved backward." + "\n");
                    } catch (Exception e) {
                        System.out.print("No previous car, cannot move " +
                                "cursor backward." + "\n");
                    }
                } else if (letter.equalsIgnoreCase("i")) {
                    try {
                        System.out.print("Enter car length in meters: ");
                        double length = h.nextDouble();
                        System.out.print("Enter car weight in tons: ");
                        double weight = h.nextDouble();
                        h.nextLine();

                        if (length < 0 || weight < 0) {
                            throw new IllegalArgumentException("Invalid " +
                                    "value");
                        }
                        TrainCar insertCar = new TrainCar(length, weight);
                        train.insertAfterCursor(insertCar);
                        System.out.print("New train car " + length +
                                " meters " + weight +
                                " tons inserted into train." + "\n");
                    } catch (IllegalArgumentException e) {
                        System.out.print(e.getMessage() + ", Returning to " +
                                "main menu" + "\n");
                    } catch (Exception e) {
                        System.out.print("Invalid value, Returning to main" +
                                " menu" + "\n");
                        h.nextLine();
                    }
                } else if (letter.equalsIgnoreCase("r")) {
                    String dangerous;
                    try {
                        TrainCar removedLink = train.removeCursor();
                        System.out.println("Car successfully unlinked. The " +
                                "following load has been removed " +
                                "from the train:");
                        if (removedLink.load != null) {
                            if (removedLink.load.isDangerous) {
                                dangerous = "YES";
                            } else {
                                dangerous = "NO";
                            }
                            System.out.print("        Name     " +
                                    " Weight (t)     " + "Value ($)   " +
                                    "Dangerous\n" + "    ======" +
                                    "=============================" +
                                    "================\n" + "        " +
                                    removedLink.load.getName() + "        " +
                                    removedLink.load.getWeight() + "        " +
                                    removedLink.load.getValue() + "0 "
                                    + "        " + dangerous + "\n");
                        } else {
                            System.out.print("        Name      " +
                                    "Weight (t)     " +
                                    "Value ($)   " + "Dangerous\n" +
                                    "    ======" +
                                    "=============================" +
                                    "================\n" + "        " +
                                    "Empty" + "        " +
                                    0 + "        " +
                                    0 + " " + "        "
                                    + "NO" + "\n");
                        }
                    } catch (Exception e) {
                        System.out.print("Train empty." + "\n");
                    }
                } else if (letter.equalsIgnoreCase("l")) {
                    try {
                        System.out.print("Enter product name: ");
                        String name = h.nextLine();
                        System.out.print("Enter product weight in tons: ");
                        double weight = h.nextDouble();
                        System.out.print("Enter product value in dollars: ");
                        double value = h.nextDouble();
                        h.nextLine();
                        if (value < 0 || weight < 0) {
                            throw new IllegalArgumentException("Invalid " +
                                    "value");
                        }
                        System.out.print("Enter is product dangerous? " +
                                "(y/n): ");
                        String dangerous = h.nextLine();
                        // if users press y
                        if (dangerous.equalsIgnoreCase("y")) {
                            try {
                                if (train.getCursorData().load != null) {
                                    train.weightCount -= train.getCursorData().
                                            load.weight;
                                    train.valueCount -= train.getCursorData().
                                            load.value;
                                    if (train.getCursorData().load.
                                            isDangerous) {
                                        train.dangerousCount -= 1;
                                    }
                                }
                                ProductLoad isDangerous = new ProductLoad(name,
                                        weight, value, true);
                                train.getCursorData().setLoad(isDangerous);
                                train.weightCount += weight;
                                train.valueCount += value;
                                train.dangerousCount += 1;
                                System.out.print(weight + " tons of " +
                                        name + " " + "added to the " +
                                        "current car." + "\n");
                            } catch (Exception e) {
                                System.out.print(e.getMessage());
                            }
                        }
                        // if user press n
                        else if (dangerous.equalsIgnoreCase("n")) {
                            try {
                                ProductLoad isDangerous = new ProductLoad(name,
                                        weight, value, false);
                                train.getCursorData().setLoad(isDangerous);
                                train.weightCount += weight;
                                train.valueCount += value;
                                System.out.print(weight + " tons of " +
                                        name + " " + "added to the " +
                                        "current car." + "\n");
                            } catch (Exception e) {
                                System.out.print("Invalid.");
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.print(e.getMessage() + ", Returning to" +
                                " main menu" + "\n");
                    } catch (Exception e) {
                        System.out.print("Invalid, Returning to main " +
                                "menu" + "\n");
                        h.nextLine();
                    }
                } else if (letter.equalsIgnoreCase("s")) {
                    System.out.print("Enter product name: ");
                    String name = h.nextLine();
                    train.findProduct(name);
                } else if (letter.equalsIgnoreCase("t")) {
                    System.out.print(train.toString());
                } else if (letter.equalsIgnoreCase("m")) {
                    train.printManifest();
                } else if (letter.equalsIgnoreCase("d")) {
                    train.removeDangerousCars();
                    System.out.println("Dangerous cars successfully " +
                            "removed from the train.");
                }
            }
             catch (Exception e){ // if any errors, print this message
                System.out.println("Invalid, Returning to main menu.");
             }
        }
        // when q is pressed, terminate the program and print this message
        System.out.print("\n" + "Program terminating successfully...");
    }
}