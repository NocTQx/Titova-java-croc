package task1;

import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    static class Point {
        double x;
        double y;
    }

    static double distanse(Point N, Point M) {
        return Math.sqrt(Math.pow(N.x - M.x,2) + Math.pow(N.y - M.y, 2));
    }

    static double result(Point a, Point b, Point c) {
        double l1, l2, l3;
        l1 = distanse(a, b);
        l2 = distanse(b, c);
        l3 = distanse(a, c);

        double P = (l1 + l2 + l3) / 2;
        return Math.sqrt(P * (P - l1) * (P - l2) * (P - l3));
    }

    public static Scanner in = new Scanner(System.in);

    public static double Input(String s) {
        out.println(s);
        return Double.parseDouble(in.nextLine());
    }

    public static void main(String[] args) {
        double S;
        Point a = new Point();
        a.x = 0.0;
        a.y = 0.0;

        Point b = new Point();
        b.x = 0.0;
        b.y = 0.0;

        Point c = new Point();
        c.x = 0.0;
        c.y = 0.0;

        a.x = Input("Введите координату x вершины №1:");
        a.y = Input("Введите координату y вершины №1:");
        b.x = Input("Введите координату x вершины №1:");
        b.y = Input("Введите координату y вершины №1:");
        c.x = Input("Введите координату x вершины №1:");
        c.y = Input("Введите координату y вершины №1:");
        S = result(a, b, c);
        out.print("Площадь треугольника: ");
        out.printf("%.1f",S);
    }
}