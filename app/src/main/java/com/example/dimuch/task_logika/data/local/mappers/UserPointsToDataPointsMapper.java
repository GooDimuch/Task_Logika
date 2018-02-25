package com.example.dimuch.task_logika.data.local.mappers;

import com.example.dimuch.task_logika.data.model.UserPoint;
import com.jjoe64.graphview.series.DataPoint;
import java.util.ArrayList;

/**
 * Created by Dimuch on 13.10.2017.
 */

public class UserPointsToDataPointsMapper
    implements Mapper<ArrayList<UserPoint>, DataPoint[]> {

  @Override public DataPoint[] transform(ArrayList<UserPoint> userPoints)
      throws RuntimeException {
    DataPoint[] dataPoints = new DataPoint[userPoints.size()];
    for (int i = 0; i < userPoints.size(); i++) {
      dataPoints[i] = new DataPoint(userPoints.get(i).getX(), userPoints.get(i).getY());
    }
    return dataPoints;
  }
}