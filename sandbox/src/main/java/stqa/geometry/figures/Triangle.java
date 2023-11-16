package stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

    public static void printTriangleArea(Triangle t) {
        var text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.area());
        System.out.println(text);
    }

    public double perimeter() {
        return (a + b + c);
    }

    public double area() {
        double p = perimeter()/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
}
