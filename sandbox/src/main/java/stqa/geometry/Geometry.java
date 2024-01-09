package stqa.geometry;

import stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.function.Consumer;

public class Geometry {
    public static void main(String[] args) {
        var triangles = List.of(new Triangle(8, 9, 12), new Triangle(7, 8, 11), new Triangle(6, 7, 10));
//        for (Triangle triangle : triangles){
//            Triangle.printTriangleArea(triangle);
//        }
        triangles.forEach(Triangle::printTriangleArea);
    }
}
