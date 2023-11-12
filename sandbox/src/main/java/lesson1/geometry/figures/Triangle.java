package lesson1.geometry.figures;

public record Triangle(double a, double b, double c) {

    public Triangle{
        if(a < 0 || b < 0 || c < 0){
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if(a+b < c || a+c < b || b+c < a){
            throw new IllegalArgumentException("The sum of two sides of a triangle must be greater than the third side");
        }
    }

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
