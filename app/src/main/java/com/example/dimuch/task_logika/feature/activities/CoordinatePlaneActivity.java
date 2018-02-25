package com.example.dimuch.task_logika.feature.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.dimuch.task_logika.R;
import com.example.dimuch.task_logika.feature.presenters.CoordinatePlaneActivityPresenter;
import com.example.dimuch.task_logika.feature.views.ICoordinatePlaneActivityView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import timber.log.Timber;

public class CoordinatePlaneActivity extends MvpAppCompatActivity
    implements ICoordinatePlaneActivityView {

  private int x = 1;
  @BindView(R.id.gvCoordinatePlane) GraphView gvCoordinatePlane;

  @InjectPresenter CoordinatePlaneActivityPresenter coordinatePlaneActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coordinate_plane);
    ButterKnife.bind(this);
  }

  @Override public void configureGraph(int minX, int maxX, int minY, int maxY, boolean isScaleX,
      boolean isScaleY) {
    gvCoordinatePlane.getViewport().setXAxisBoundsManual(true);
    gvCoordinatePlane.getViewport().setMinX(minX);
    gvCoordinatePlane.getViewport().setMaxX(maxX);

    gvCoordinatePlane.getViewport().setYAxisBoundsManual(true);
    gvCoordinatePlane.getViewport().setMinY(minY);
    gvCoordinatePlane.getViewport().setMaxY(maxY);

    gvCoordinatePlane.getViewport().setScalable(isScaleX);
    gvCoordinatePlane.getViewport().setScalableY(isScaleY);
  }

  @Override public void paintGraph(LineGraphSeries<DataPoint> series) {
    gvCoordinatePlane.addSeries(series);
  }

  @Override public void paintPoint(PointsGraphSeries<DataPoint> series) {
    gvCoordinatePlane.addSeries(series);
  }

  @Override public void showToast(String sToastMessage) {
    Timber.wtf(sToastMessage);
    Toast.makeText(getApplicationContext(), sToastMessage, Toast.LENGTH_LONG).show();
  }
}
