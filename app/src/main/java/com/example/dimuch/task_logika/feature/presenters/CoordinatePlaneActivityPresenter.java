package com.example.dimuch.task_logika.feature.presenters;

import android.graphics.Color;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dimuch.task_logika.App;
import com.example.dimuch.task_logika.data.local.mappers.UserPointsToDataPointsMapper;
import com.example.dimuch.task_logika.data.model.UserPoint;
import com.example.dimuch.task_logika.feature.views.ICoordinatePlaneActivityView;
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

    getViewState().configureGraph(-1, 10, -1, 10, IS_SCALE, IS_SCALE);

    Timber.wtf("onFirstViewAttach");
    for (UserPoint userPoint : userPoints) Timber.wtf("userPoint" + userPoint.toString());

    DataPoint[] points = new UserPointsToDataPointsMapper().transform(userPoints);
    for (DataPoint point : points) Timber.wtf("point" + point.toString());

    PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(points);
    series.setTitle("User points");
    series.setColor(Color.MAGENTA);
    series.setSize(10);
    getViewState().paintPoint(series);
  }
}
