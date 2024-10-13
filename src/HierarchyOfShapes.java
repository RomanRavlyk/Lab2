import java.util.Scanner;

public class HierarchyOfShapes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter base and height for Triangle: ");
        double triangleBase = scanner.nextDouble();
        double triangleHeight = scanner.nextDouble();
        FlatShape triangle = new Triangle(triangleBase, triangleHeight);

        System.out.println("Enter side length for Square: ");
        double squareSide = scanner.nextDouble();
        FlatShape square = new Square(squareSide);

        System.out.println("Enter width and height for Rectangle: ");
        double rectangleWidth = scanner.nextDouble();
        double rectangleHeight = scanner.nextDouble();
        FlatShape rectangle = new Rectangle(rectangleWidth, rectangleHeight);

        System.out.println("Enter radius for Circle: ");
        double circleRadius = scanner.nextDouble();
        FlatShape circle = new Circle(circleRadius);

        System.out.println("Enter side length for Cube: ");
        double cubeSide = scanner.nextDouble();
        SolidShape cube = new Cube(cubeSide);

        System.out.println("Enter base length and height for Pyramid: ");
        double pyramidBase = scanner.nextDouble();
        double pyramidHeight = scanner.nextDouble();
        SolidShape pyramid = new Pyramid(pyramidBase, pyramidHeight);

        System.out.println("Enter radius for Sphere: ");
        double sphereRadius = scanner.nextDouble();
        SolidShape sphere = new Sphere(sphereRadius);

        System.out.println("Triangle Area: " + triangle.calculateArea());
        System.out.println("Square Area: " + square.calculateArea());
        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        System.out.println("Circle Area: " + circle.calculateArea());

        System.out.println("Cube Area: " + cube.calculateArea() + " Volume: " + cube.calculateVolume());
        System.out.println("Pyramid Area: " + pyramid.calculateArea() + " Volume: " + pyramid.calculateVolume());
        System.out.println("Sphere Area: " + sphere.calculateArea() + " Volume: " + sphere.calculateVolume());

        scanner.close();
    }

    abstract static class Shape {
        abstract double calculateArea ();
    }

    abstract static class FlatShape extends Shape {
        @Override
        abstract double calculateArea ();
    }

    abstract static class SolidShape extends Shape {
        abstract double calculateVolume();

        @Override
        abstract double calculateArea();
    }

    static class Triangle extends FlatShape {
        private final double base;
        private final double height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        @Override
        double calculateArea() {
            return 0.5 * base * height;
        }
    }

    static class Square extends FlatShape {
        private final double side;

        public Square(double side) {
            this.side = side;
        }

        @Override
        double calculateArea() {
            return side * side;
        }
    }

    static class Rectangle extends FlatShape {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        double calculateArea() {
            return width * height;
        }
    }

    static class Circle extends FlatShape {
        private final double r;

        public Circle(double radius) {
            this.r = radius;
        }

        @Override
        double calculateArea() {
            return Math.PI * r * r;
        }
    }

    static class Cube extends SolidShape {
        private final double side;

        public Cube(double side) {
            this.side = side;
        }

        @Override
        double calculateArea() {
            return 6 * side * side;
        }

        @Override
        double calculateVolume() {
            return side * side * side;
        }
    }

    static class Pyramid extends SolidShape {
        private final double baseLength;
        private final double height;

        public Pyramid(double baseLength, double height) {
            this.baseLength = baseLength;
            this.height = height;
        }

        @Override
        double calculateArea() {
            double baseArea = baseLength * baseLength;
            double slantHeight = Math.sqrt((baseLength / 2) * (baseLength / 2) + height * height);
            double lateralArea = 2 * baseLength * slantHeight;
            return baseArea + lateralArea;
        }

        @Override
        double calculateVolume() {
            return (1.0 / 3.0) * baseLength * baseLength * height;
        }
    }

    static class Sphere extends SolidShape {
        private final double r;

        public Sphere(double radius) {
            this.r = radius;
        }

        @Override
        double calculateArea() {
            return 4 * Math.PI * r * r;
        }

        @Override
        double calculateVolume() {
            return (4.0 / 3.0) * Math.PI * Math.pow(r, 3);
        }
    }
}


