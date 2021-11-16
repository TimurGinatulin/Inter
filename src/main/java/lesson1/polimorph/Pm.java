package lesson1.polimorph;

import java.util.ArrayList;
import java.util.List;

public class Pm {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle());
        shapes.add(new Square());
        shapes.add(new Triangles());
        shapes.forEach(s-> s.getSquare());
    }
}
