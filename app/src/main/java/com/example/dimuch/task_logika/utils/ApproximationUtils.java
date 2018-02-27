package com.example.dimuch.task_logika.utils;

import com.jjoe64.graphview.series.DataPoint;
import java.util.Arrays;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunctionLagrangeForm;
import timber.log.Timber;

/**
 * Created by Dimuch on 26.02.2018.
 */

public class ApproximationUtils {
  private static final int POLINOM_DEGREE = 3;
  private static final int COORDINATE_X = 1;
  private static final int COORDINATE_Y = 2;
  private final int numberPointForLine;
  private DataPoint[] points;
  private final double[] x;
  private final double[] y;

  public ApproximationUtils(DataPoint[] points) {
    this.points = points;
    numberPointForLine = (points.length + 1) * 100;

    x = fillArrays(COORDINATE_X);
    y = fillArrays(COORDINATE_Y);

    //Timber.wtf("x =" + Arrays.toString(x));
    //Timber.wtf("y =" + Arrays.toString(y));
  }

  public DataPoint[] approximateMLS() {
    double[] a = fillConstantsArray(POLINOM_DEGREE, points.length, x, y);

    return buildFunctionMLS(a);
  }

  private DataPoint[] buildFunctionMLS(double[] a) {
    DataPoint[] dataPoints = new DataPoint[600];
    double x = 0;
    for (int i = 0; i < dataPoints.length; i++) {
      dataPoints[i] = new DataPoint(x, a[0] + a[1] * x + a[2] * x * x + a[3] * x * x * x);
      x += 0.01;
    }
    return dataPoints;
  }

  private double[] fillArrays(int trigger) {
    double[] coordinate = new double[points.length];
    for (int i = 0; i < points.length; i++) {
      if (trigger == COORDINATE_X) coordinate[i] = points[i].getX();
      if (trigger == COORDINATE_Y) coordinate[i] = points[i].getY();
    }
    return coordinate;
  }

  private double[] fillConstantsArray(int n, int N, double[] x, double[] y) {
    double X[] = new double[2 * n + 1];
    for (int i = 0; i < 2 * n + 1; i++) {
      X[i] = 0;
      for (int j = 0; j < N; j++)
        X[i] = X[i] + Math.pow(x[j],
            i);        //consecutive positions of the array will store N,sigma(xi),sigma(xi^2),sigma(xi^3)....sigma(xi^2n)
    }
    double B[][] = new double[n + 1][n + 2];
    double a[] = new double[n
        + 1];            //B is the Normal matrix(augmented) that will store the equations, 'a' is for value of the final coefficients
    for (int i = 0; i <= n; i++)
      for (int j = 0; j <= n; j++)
        B[i][j] = X[i
            + j];            //Build the Normal matrix by storing the corresponding coefficients at the right positions except the last column of the matrix
    double Y[] = new double[n
        + 1];                    //Array to store the values of sigma(yi),sigma(xi*yi),sigma(xi^2*yi)...sigma(xi^n*yi)
    for (int i = 0; i < n + 1; i++) {
      Y[i] = 0;
      for (int j = 0; j < N; j++)
        Y[i] = Y[i]
            + Math.pow(x[j], i)
            * y[j];        //consecutive positions will store sigma(yi),sigma(xi*yi),sigma(xi^2*yi)...sigma(xi^n*yi)
    }
    for (int i = 0; i <= n; i++)
      B[i][n + 1] =
          Y[i];                //load the values of Y as the last column of B(Normal Matrix but augmented)
    n = n + 1;
    for (int i = 0; i < n;
        i++)                    //From now Gaussian Elimination starts(can be ignored) to solve the set of linear equations (Pivotisation)
      for (int k = i + 1; k < n; k++)
        if (B[i][i] < B[k][i]) {
          for (int j = 0; j <= n; j++) {
            double temp = B[i][j];
            B[i][j] = B[k][j];
            B[k][j] = temp;
          }
        }

    for (int i = 0; i < n - 1; i++)            //loop to perform the gauss elimination
      for (int k = i + 1; k < n; k++) {
        double t = B[k][i] / B[i][i];
        for (int j = 0; j <= n; j++)
          B[k][j] = B[k][j]
              - t
              * B[i][j];    //make the elements below the pivot elements equal to zero or elimnate the variables
      }
    for (int i = n - 1; i >= 0; i--)                //back-substitution
    {                        //x is an array whose values correspond to the values of x,y,z..
      a[i] =
          B[i][n];                //make the variable to be calculated equal to the rhs of the last equation
      for (int j = 0; j < n; j++)
        if (j
            != i)            //then subtract all the lhs values except the coefficient of the variable whose value                                   is being calculated
        {
          a[i] = a[i] - B[i][j] * a[j];
        }
      a[i] = a[i]
          / B[i][i];            //now finally divide the rhs by the coefficient of the variable to be calculated
    }
    return a;
  }

  public DataPoint[] approximateLagrange() {
    PolynomialFunctionLagrangeForm polynomialFunctionLagrangeForm =
        new PolynomialFunctionLagrangeForm(x, y);
    double[] a = polynomialFunctionLagrangeForm.getCoefficients();
    return buildFunctionLagrange(a);
  }

  private DataPoint[] buildFunctionLagrange(double[] a) {
    DataPoint[] dataPoints = new DataPoint[numberPointForLine];
    double coordinateX = 0;
    for (int i = 0; i < dataPoints.length; i++) {
      dataPoints[i] = new DataPoint(coordinateX, getCoordinateY(a, coordinateX));
      coordinateX += 0.01;
    }
    return dataPoints;
  }

  private double getCoordinateY(double[] a, double coordinateX) {
    double y = 0;
    for (int i = 0; i < a.length; i++) {
      y += a[i] * Math.pow(coordinateX, i);
    }
    return y;
  }
}
