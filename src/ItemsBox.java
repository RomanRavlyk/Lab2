import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class ItemsBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the dimensions of the box (height, width, length): ");
            double boxHeight = scanner.nextDouble();
            double boxWidth = scanner.nextDouble();
            double boxLength = scanner.nextDouble();
            scanner.nextLine();
            Box box = new Box(boxHeight, boxWidth, boxLength);

            while (true) {
                System.out.println("Enter the name of the item (or 'exit' to finish): ");
                String itemName = scanner.next();

                if (itemName.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Enter the dimensions of the item (height, width, length): ");
                double itemHeight = scanner.nextDouble();
                double itemWidth = scanner.nextDouble();
                double itemLength = scanner.nextDouble();
                scanner.nextLine();

                Item item = new Item(itemHeight, itemWidth, itemLength, itemName);

                try {
                    box.addItemInBox(item);
                    System.out.println("Item added to the box.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("Items in the box: ");
            box.getAllItems();
        } catch (InputMismatchException e) {
            System.out.println("You typed wrong data!");
        } finally {
            scanner.close();
            System.out.println("Scanner closed.");
        }
    }

    public static abstract class Size {
        protected double height;
        protected double width;
        protected double length;

        public Size(double height, double width, double length) {
            this.height = height;
            this.width = width;
            this.length = length;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

    }

    public static class Box extends Size {
        private final ArrayList<Item> items = new ArrayList<>();

        public Box(double height, double width, double length) {
            super(height, width, length);
        }


        public void addItemInBox(Item item) throws Exception {
            if (item.getHeight() > this.getHeight() || item.getWidth() > this.getWidth() || item.getLength() > this.getLength()) {
                throw new Exception("Item dimensions exceed box dimensions!");
            }

            if (items.size() < 5) {
                items.add(item);
            } else {
                throw new Exception("This box is already full!");
            }
        }

        public void getAllItems() {
            if (items.isEmpty()) {
                System.out.println("The box is empty.");
            } else {
                for (Item item : items) {
                    System.out.println("Item: '" + item.getName() +
                            "' with height: " + item.getHeight() +
                            ", width: " + item.getWidth() +
                            ", length: " + item.getLength());
                }
            }
        }
    }

    public static class Item extends Size {
        private String name;

        public Item(double height, double width, double length, String name) {
            super(height, width, length);
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
