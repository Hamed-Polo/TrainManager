public class TrainLinkedList {
    /**
     * head is for the head of the list
     * tail is for the tail of the list
     * cursor is for the cursor of the list
     * valueCount is for the total value for the product
     * weightCount is for the total value for the product
     * lengthCount is for the total length for the product
     * dangerousCount is for the total dangerous products
     * size is for how many cars are in the train
     */
    TrainCarNode head;
    TrainCarNode tail;
    TrainCarNode cursor;
    double valueCount;
    double weightCount;
    double lengthCount;
    int dangerousCount;
    int size;

    /**
     * Constructor for TrainLinkedList, setting head, tail and cursor to null
     */
    public TrainLinkedList() {
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * if the cursor is null, return a null pointer exception, other wise
     * @return The reference to the TrainCar at the node currently
     * referenced by the cursor.
     */
    public TrainCar getCursorData() {
        if (cursor == null) { // if the list isn't empty
            throw new NullPointerException();
        }
        return cursor.getCarData();
    }

    /**
     *
     * @param car - car argument
     * Places car in the node currently referenced by the cursor.
     */
    public void setCursorData(TrainCar car) {
        cursor.setCarData(car);
    }

    /**
     * Moves the cursor to point at the next TrainCarNode.
     * It only moves if the list is not empty and if cursor is not equal to
     * tail.
     */
    public void cursorForward() {
        if (cursor != null) { // if the list isn't empty
            if (cursor != tail) { // if the cursor is not tail
                cursor = cursor.getNextNode();
            }
        }
    }

    /**
     * Moves the cursor to point at the previous TrainCarNode.
     * It only moves if the list is not empty and if cursor is not equal to
     * head.
     */
    public void cursorBackward() {
        if (cursor != null) { // if the list is not empty
            if (cursor != head) { // if cursor does not equal head
                cursor = cursor.getPrevNode();
            }
        }
    }

    /**
     * Inserts a car into the train after the cursor position.
     * @param newCar - the new TrainCar to be inserted into the train.
     * @throws IllegalArgumentException - Indicates that newCar is null.
     */
    public void insertAfterCursor(TrainCar newCar) {
        TrainCarNode tempNode; // temporary node
        // if newCar is null, throw an IllegalArgumentException
        if (newCar == null) {
            throw new IllegalArgumentException("Car is null/empty.");
        }
        // if cursor isn't null or empty, set our temporary node to the next
        // node in the chain else, set it to null
        if (cursor != null) {
            tempNode = cursor.getNextNode();
        } else {
            tempNode = null;
        }

        // our next node to place the cursor
        TrainCarNode nextNode = new TrainCarNode(tempNode, cursor, newCar);

        // if the cursor is not null, add the length and weight of the car to
        // the total length and total weight
        if (cursor != null) {
            lengthCount += newCar.length;
            weightCount += newCar.weight;

            // if the load is not null, add the load weight and load value to
            // the total weight and total value
            if (newCar.load != null) {
                weightCount += newCar.load.weight;
                valueCount += newCar.load.value;
            }

            cursor.setNextNode(nextNode); // setting the next node to next node
            cursor = cursor.getNextNode(); // setting cursor to the next node

            // if the cursor next node is null, set tail to cursor
            if (cursor.getNextNode() == null) {
                tail = cursor;
            }

        }
        // else if cursor is null, add the new car information to the list
        else {
            lengthCount += newCar.length;
            weightCount += newCar.weight;

            if (newCar.load != null) {
                weightCount += newCar.load.weight;
                valueCount += newCar.load.value;
            }
            // set cursor to next node and both head and tail to cursor
            cursor = nextNode;
            head = cursor;
            tail = cursor;
        }
        // after the process is done, increase the total of cars by 1.
        size += 1;
    }

    /**
     * Removes the TrainCarNode referenced by the cursor and returns the
     * TrainCar contained within the node.
     * @return the removed cursor data
     */
    public TrainCar removeCursor() {
        TrainCar removedCursor = null; // the cursor we want to remove

        if (cursor != null) { // if the cursor isn't null
            removedCursor = cursor.getCarData(); // set removedCursor to the
            // data referenced by the cursor
            if (cursor == head) { // if cursor is head
                if (cursor == tail) { // if it becomes tail
                    // set everything to null or 0
                    cursor = tail = head = null;
                    lengthCount = weightCount  = valueCount = 0;
                    size = dangerousCount = 0;
                }
                else {
                    // else remove all it's data, decrease the size of the
                    // train by 1 and the dangerous count by 1 if it was
                    // dangerous
                    lengthCount -= cursor.getCarData().getLength();
                    weightCount -= cursor.getCarData().getWeight();
                    if (cursor.getCarData().getLoad() != null) {
                        weightCount -= cursor.getCarData().getLoad().
                                getWeight();
                        valueCount -= cursor.getCarData().getLoad().getValue();
                        if (cursor.getCarData().getLoad().isDangerous()) {
                            dangerousCount -= 1;
                        }
                    }
                    size -= 1;
                    // The cursor now references the next node
                    cursorForward();
                    cursor.setPrevNode(null);
                    head = cursor;
                }
            } // if it's equal to tail then remove all it's data, decrease the
            // size of the train by 1 and the dangerous count by 1 if it was
            // dangerous
            else if (cursor == tail) {
                lengthCount -= cursor.getCarData().getLength();
                weightCount -= cursor.getCarData().getWeight();
                if (cursor.getCarData().getLoad() != null) {
                    weightCount -= cursor.getCarData().getLoad().getWeight();
                    valueCount -= cursor.getCarData().getLoad().getValue();
                    if (cursor.getCarData().getLoad().isDangerous()) {
                        dangerousCount -= 1;
                    }
                }
                size -= 1;
                // the previous node if no next node exists.
                cursorBackward();
                cursor.setNextNode(null);
                tail = cursor;
            }
            else {
                // just remove all it's data, decrease the size of the
                // train by 1 and the dangerous count by 1 if it was
                // dangerous
                lengthCount -= cursor.getCarData().getLength();
                weightCount -= cursor.getCarData().getWeight();
                if (cursor.getCarData().getLoad() != null) {
                    weightCount -= cursor.getCarData().getLoad().getWeight();
                    valueCount -= cursor.getCarData().getLoad().getValue();
                    if (cursor.getCarData().getLoad().isDangerous())
                        dangerousCount -= 1;
                }
                size -= 1;
                // get the prev node and set next node to cursor, set the prev
                // node to the last cursor prev node
                cursorForward();
                cursor.setPrevNode(cursor.getPrevNode());
                cursor.getPrevNode().setNextNode(cursor);
            }
        }
        // return the removed cursor
        return removedCursor;
    }

    /**
     *
     * @return the size of the train
     */
    public int size() {
        return size;
    }

    /**
     *
     * @return the total length count of the train
     */
    public double getLength() {
        return lengthCount;
    }

    /**
     *
     * @return the total value count of the train
     */
    public double getValue() {
        return valueCount;
    }

    /**
     *
     * @return the total weight count of the train
     */
    public double getWeight() {
        return weightCount;
    }

    /**
     *
     * @return whether the train was dangerous or not
     */
    public boolean dangerousCount() {
        return dangerousCount > 0;
    }

    /**
     * Searches the list for all ProductLoad objects with the indicated name
     * and sums together their weight and value (Also keeps track of whether
     * the product is dangerous or not), then prints a single ProductLoad
     * record to the console.
     * @param name - the name of the ProductLoad to find on the train.
     */
    public void findProduct(String name) {
        TrainCarNode tempHead = head; // temporary head
        double weight = 0; // weight for the product car
        double value = 0; // value for the product car
        int total = 0; // the amount of times we see the same car name
        boolean condition = false; // whether we found a match or not
        String dangerous = ""; // to hold "YES" or "NO" if it's dangerous

        // do while loop
        do {
            if (tempHead.getCarData().getLoad().getName().
                    equalsIgnoreCase(name)) { // if we find our product car
                // if the car is dangerous
                if (tempHead.getCarData().getLoad().isDangerous()) {
                    // set dangerous to "YES" or "NO"
                    dangerous = "YES";
                } else {
                    dangerous = "NO";
                }
                // add the weight of the car to weight
                // add the value of the car to value
                // set the condition to true since we found our match
                // increase the amount of times we see it by 1.
                weight += tempHead.getCarData().getLoad().getWeight();
                value += tempHead.getCarData().getLoad().getValue();
                condition = true;
                total += 1;
            }
            tempHead = tempHead.getNextNode(); // continue to the next node
        }
        // do all this while our temporary head is not null
        while (tempHead != null);

        // if we found our match, print a single ProductLoad record to the
        // console.
        if (condition) {
            System.out.print("The following products were found on " + total +
                    " cars:" + "\n");
            System.out.print("        Name      Weight (t)     Value ($)   " +
                    "Dangerous\n" + "    ===================================" +
                    "================\n" + "        " + name + "        " +
                    weight + "        " + value + "0 " +
                    "        " + dangerous + "\n");
        } else {
            // else print that there's no record of the name on the train
            System.out.print("No record of " + name + " on board train." +
                    "\n");
        }
    }

    /**
     * Prints a neatly formatted table of the car number, car length,
     * car weight, load name, load weight, load value, and load dangerousness
     * for all of the car on the train.
     *
     * For each car, print the data of the car, followed by the ProductLoad
     * data if the car is not empty. If the car is empty, print "Empty" for
     * name, 0 for weight and value, and "NO" for dangerousness
     */
    public void printManifest() {
        System.out.println("CAR:                               LOAD:");
        System.out.println("Num   Length (m)    Weight (t)    |  Name      " +
                "Weight (t)     Value ($)   Dangerous");
        System.out.println("==================================+=============" +
                "======================================");
        int h = 1; // the number of cars
        TrainCarNode tempHead = head; // temporary head
        String dangerous; // to hold "YES" or "NO" if it's dangerous


        while (tempHead != null) {
            // while our temporary head is not null
            if (tempHead == cursor) {
                // if it's equal to cursor
                // if the load is not null and if it's dangerous
                if (tempHead.getCarData().getLoad() != null) {
                    if (tempHead.getCarData().getLoad().isDangerous()) {
                        // print with the cursor, the information for the car
                        // with dangerous being YES
                        System.out.printf("-> %-7d%-13.1f%-11.1f| %-15s%-15" +
                                        ".1f%,-12.2f%s" + "\n", h,
                                tempHead.getCarData().getLength(),
                                tempHead.getCarData().getWeight(),
                                tempHead.getCarData().getLoad().getName(),
                                tempHead.getCarData().getLoad().getWeight(),
                                tempHead.getCarData().getLoad().getValue(),
                                "YES");
                    } else {
                        // print with the cursor, the information for the car
                        // with dangerous being NO
                        System.out.printf("-> %-7d%-13.1f%-11.1f| %-15s%-" +
                                        "15.1f%,-12.2f%s" + "\n", h,
                                tempHead.getCarData().getLength(),
                                tempHead.getCarData().getWeight(),
                                tempHead.getCarData().getLoad().getName(),
                                tempHead.getCarData().getLoad().getWeight(),
                                tempHead.getCarData().getLoad().getValue(),
                                "NO");
                    }
                } else {
                    // print with the cursor, the information for the car
                    // with the car being empty
                    System.out.printf("-> %-7d%-13.1f%-11.1f| %-18s%-16.1s%" +
                                    "-12.2s%s" + "\n", h,
                            tempHead.getCarData().getLength(),
                            tempHead.getCarData().getWeight(),
                            "Empty", 0, 0, "NO");
                }
            } else {
                // if the car is dangerous, set the string to YES, else set
                // it to NO
                if (tempHead.getCarData().getLoad().isDangerous()) {
                    dangerous = "YES";
                } else {
                    dangerous = "NO";
                }
                // print without cursor, the information for car
                System.out.printf(" %-7d%-13.1f%-11.1f  | %-15s%-15.1f%," +
                                "-12.2f%s" + "\n", h,
                        tempHead.getCarData().getLength(),
                        tempHead.getCarData().getWeight(),
                        tempHead.getCarData().getLoad().getName(),
                        tempHead.getCarData().getLoad().getWeight(),
                        tempHead.getCarData().getLoad().getValue(), dangerous);
            }
            // go to the next node, increase h by 1 to keep track of the
            // amount of cars in the train.
            tempHead = tempHead.getNextNode();
            h += 1;
        }
    }

    /**
     * Removes all dangerous cars from the train, maintaining the order of the
     * cars in the train.
     */
    public void removeDangerousCars() {
        cursor = head; // set cursor to head
        // while it's not null
        while (cursor != null) {
            // if it's load is not null
            if (cursor.getCarData().getLoad() != null) {
                // if it's dangerous
                if (cursor.getCarData().getLoad().isDangerous()) {
                    // remove it
                    removeCursor();
                }
                // if it's not, keep moving through the list
                cursorForward();
            }
            // if it ends up being tail, break out of the loop
            if (cursor == tail) {
                break;
            }
        }
    }

    /**
     * Returns a neatly formatted String representation of the train.
     * @return A neatly formatted string containing information about the
     * train, including it's size (number of cars), length in meters, weight in
     * tons, value in dollars, and whether it is dangerous or not.
     */
    public String toString() {
        String dangerous; // string to hold "DANGEROUS" or "not dangerous"
        // if the train is dangerous
        if (dangerousCount > 0) {
            dangerous = "DANGEROUS"; // set it to "DANGEROUS"
        } else {
            dangerous = "not dangerous"; // else set it to "not dangerous"
        }
        // return a neatly formatted string containing information about the
        // train, including it's size (number of cars), length in meters,
        // weight in tons, value in dollars, and whether it is dangerous or not
        return "Train: " + size() + " cars, " + lengthCount + " " +
                "meters, " +
                String.format("%.1f", weightCount) + " tons, $" + valueCount +
                "0" + " " + "value, " + dangerous + "." + "\n";
    }
}