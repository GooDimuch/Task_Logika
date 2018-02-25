package com.example.dimuch.task_logika.data.model;

/**
 * Created by Dimuch on 25.02.2018.
 */

public class UserPoint {

  private double x;
  private double y;
  private boolean isEmptyX;
  private boolean isEmptyY;

  public UserPoint() {
    isEmptyX = true;
    isEmptyY = true;
  }

  public UserPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public boolean isEmpty() {
    return isEmptyX || isEmptyY;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
    isEmptyX = false;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
    isEmptyY = false;
  }

  @Override public String toString() {
    return "UserPoint{" + "x=" + x + ", y=" + y + '}';
  }
}
