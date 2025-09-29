package com.square.root;

public class SquareRootService {

    public double[] solve(double a, double b, double c, double epsilon) {
        if ((a == 0) || Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c)
                || Double.isInfinite(a) || Double.isInfinite(b) || Double.isInfinite(c)) {
            throw new IllegalArgumentException();
        }

        double discriminant = b*b-4*a*c;

        if (discriminant == 0.0 || (Math.abs(discriminant) < epsilon)) {
            double root = -b/(2*a);
            return new double[] {root};
        } else if (discriminant < 0) {
            return new double[0];
        } else {
            double sqrtD = Math.sqrt(discriminant);
            double root1 = (-b+sqrtD)/(2*a);
            double root2 = (-b-sqrtD)/(2*a);
            return new double[] {root1,root2};
        }
    }

}
