package com.example.dimuch.task_logika.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dimuch.task_logika.App;
import com.example.dimuch.task_logika.data.model.UserPoint;
import com.example.dimuch.task_logika.feature.views.IMainActivityView;
import java.util.ArrayList;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Dimuch on 06.02.2018.
 */

@InjectViewState public class MainActivityPresenter extends MvpPresenter<IMainActivityView> {

  @Inject String testMessage;
  private ArrayList<UserPoint> pointArray;
  //@Inject DataManager mDataManager;

  public MainActivityPresenter() {
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    App.getComponent().inject(this);

    pointArray = new ArrayList<>();
    getViewState().setAdapter(pointArray);
    //addPoint();
  }

  public void addPoint() {
    Timber.wtf("addPoint");
    pointArray.add(new UserPoint());
    getViewState().showPointArray();
  }

  public ArrayList<UserPoint> getPointArray() {
    return pointArray;
  }

  public boolean isFullList() {
    for (UserPoint userPoint : pointArray) {
      if (userPoint.isEmpty()) return false;
    }
    return true;
  }
}
