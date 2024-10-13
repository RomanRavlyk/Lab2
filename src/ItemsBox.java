import java.util.Scanner;
import java.util.ArrayList;

public class ItemsBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the dimensions of the box (height, width, length): ");
        double boxHeight = scanner.nextDouble();
        double boxWidth = scanner.nextDouble();
        double boxLength = scanner.nextDouble();
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
        scanner.close();
    }

    abstract static class Size {
        protected double height;
        protected double width;
        protected double length;

        public Size(double height, double width, double length) {
            this.height = height;
            this.width = width;
            this.length = length;
        }

        abstract public double getHeight();
        abstract public void setHeight(double height);
        abstract public double getWidth();
        abstract public void setWidth(double width);
        abstract public double getLength();
        abstract public void setLength(double length);
        abstract double calculateVolume();
    }

    public static class Box extends Size {
        private final ArrayList<Item> items = new ArrayList<>();

        public Box(double height, double width, double length) {
            super(height, width, length);
        }

        @Override
        public double getHeight() {
            return height;
        }

        @Override
        public void setHeight(double height) {
            this.height = height;
        }

        @Override
        public double getWidth() {
            return width;
        }

        @Override
        public void setWidth(double width) {
            this.width = width;
        }

        @Override
        public double getLength() {
            return length;
        }

        @Override
        public void setLength(double length) {
            this.length = length;
        }

        @Override
        double calculateVolume() {
            return height * width * length;
        }

        public void addItemInBox(Item item) throws Exception {
            if (item.getHeight() > height || item.getWidth() > width || item.getLength() > length) {
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

        @Override
        double calculateVolume() {
            return height * width * length;
        }

        @Override
        public double getHeight() {
            return height;
        }

        @Override
        public void setHeight(double height) {
            this.height = height;
        }

        @Override
        public double getWidth() {
            return width;
        }

        @Override
        public void setWidth(double width) {
            this.width = width;
        }

        @Override
        public double getLength() {
            return length;
        }

        @Override
        public void setLength(double length) {
            this.length = length;
        }
    }
}
