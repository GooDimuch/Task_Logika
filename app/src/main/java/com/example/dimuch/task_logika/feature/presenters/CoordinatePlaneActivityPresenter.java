package com.example.dimuch.task_logika.feature.presenters;

import android.graphics.Color;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dimuch.task_logika.App;
import com.example.dimuch.task_logika.data.local.mappers.UserPointsToDataPointsMapper;
import com.example.dimuch.task_logika.data.model.UserPoint;
import com.example.dimuch.task_logika.feature.views.ICoordinatePlaneActivityView;
import com.example.dimuch.task_logika.utils.ApproximationUtils;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import java.util.ArrayList;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Dimuch on 06.02.2018.
 */

@InjectViewState public class CoordinatePlaneActivityPresenter
    extends MvpPresenter<ICoordinatePlaneActivityView> {
  //private static final boolean IS_SCALE = true;
  private static final boolean IS_SCALE = false;

  //@Inject DataManager mDataManager;
  @Inject ArrayList<UserPoint> userPoints;

  public CoordinatePlaneActivityPresenter() {
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    App.getComponent().inject(this);

    getViewState().configureGraph(0, 7, 0, 10, IS_SCALE, IS_SCALE);

    DataPoint[] dataPoints = new UserPointsToDataPointsMapper().transform(userPoints);

    paintGraphMLS(dataPoints);
    paintGraphLagrange(dataPoints);
    paintUserPoints(dataPoints);
  }

  private void paintUserPoints(DataPoint[] points) {
    PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(points);
    series.setTitle("User points");
    series.setColor(Color.RED);
    series.setSize(10);
    getViewState().paintPoint(series);
  }

  private void paintGraphMLS(DataPoint[] dataPoints) {
    DataPoint[] points = new ApproximationUtils(dataPoints).approximateMLS();

    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
    series.setTitle("MLS");
    series.setColor(Color.BLUE);
    getViewState().paintGraph(series);
  }

  private void paintGraphLagrange(DataPoint[] dataPoints) {
    DataPoint[] points = new ApproximationUtils(dataPoints).approximateLagrange();

    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(points);
    series.setTitle("Lagrange");
    series.setColor(Color.GREEN);
    getViewState().paintGraph(series);
  }
}
