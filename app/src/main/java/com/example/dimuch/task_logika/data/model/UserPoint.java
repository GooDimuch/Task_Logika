package com.example.dimuch.task_logika.data.model;

/**
 * Created by Dimuch on 25.02.2018.
 */

public class UserPoint {

  private boolean isEmptyX = true;
  private boolean isEmptyY = true;
  private double x;
  private double y;

  public UserPoint() {
  }

  public UserPoint(double x, double y) {
    isEmptyX = false;
    isEmptyY = false;
    this.x = x;
    this.y = y;
  }

  public UserPoint(int x) {
    isEmptyX = false;
    this.x = x;
  }

  public boolean isEmpty() {
    return isEmptyX || isEmptyY;
  }

  public boolean isEmptyX() {
    return isEmptyX;
  }

  public boolean isEmptyY() {
    return isEmptyY;
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
