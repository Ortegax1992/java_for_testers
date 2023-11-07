package lesson1.geometry.figures;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle() {
    }
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void printTriangleArea(Triangle t) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.area());
        System.out.println(text);
    }

    public double perimeter(double a, double b, double c) {
        return (a + b + c);
    }
    public double semiperimeter(double a, double b, double c) {
        return perimeter(a,b,c)/2;
    }

    public double area() {
        double p = semiperimeter(a, b, c);
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}
