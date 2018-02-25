package com.example.dimuch.task_logika.feature.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

/**
 * Created by Dimuch on 06.02.2018.
 */

@StateStrategyType(AddToEndStrategy.class) public interface ICoordinatePlaneActivityView
    extends MvpView {

  void showToast(String sToastMessage);

  void paintGraph(LineGraphSeries<DataPoint> series);

  void paintPoint(PointsGraphSeries<DataPoint> series);

  void configureGraph(int minX, int maxX, int minY, int maxY, boolean scaleX, boolean scaleY);
}
