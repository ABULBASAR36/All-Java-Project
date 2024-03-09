package finalprectice;

abstract class Shape {
    abstract void calculateArea(); // A single abstract method to calculate area
}

class Rectangle extends Shape {
    private int length;
    private int breadth;

    Rectangle(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    void calculateArea() {
        System.out.println("Area of rectangle: " + (length * breadth));
    }
}

class Square extends Shape {
    private int side;

    Square(int side) {
        this.side = side;
    }

    @Override
    void calculateArea() {
        System.out.println("Area of square: " + (side * side));
    }
}

class Circle extends Shape {
    private int radius;

    Circle(int radius) {
        this.radius = radius;
    }

    @Override
    void calculateArea() {
        System.out.println("Area of circle: " + (3.14 * radius * radius));
    }
}

public class Abstract{
    public static void main(String[] args) {
        Shape[] shapes = new Shape[9]; // Array to hold all shapes

        // Initialize shapes
        shapes[0] = new Rectangle(10, 20);
        shapes[1] = new Rectangle(15, 18);
        shapes[2] = new Rectangle(8, 6);
        shapes[3] = new Rectangle(3, 30);

        shapes[4] = new Square(10);
        shapes[5] = new Square(15);
        shapes[6] = new Square(100);
        shapes[7] = new Square(1);

        shapes[8] = new Circle(10);
        shapes[9] = new Circle(15);
        shapes[10] = new Circle(13);
        shapes[11] = new Circle(1);
        shapes[12] = new Circle(3);

        // Calculate and print areas for all shapes
        for (Shape shape : shapes) {
            shape.calculateArea();
        }
    }
}
