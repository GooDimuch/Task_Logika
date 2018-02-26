package com.example.dimuch.task_logika.feature.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.dimuch.task_logika.App;
import com.example.dimuch.task_logika.data.model.UserPoint;
import com.example.dimuch.task_logika.feature.views.IMainActivityView;
import java.util.ArrayList;
import java.util.Comparator;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by Dimuch on 06.02.2018.
 */

@InjectViewState public class MainActivityPresenter extends MvpPresenter<IMainActivityView> {

  @Inject String testMessage;
  @Inject ArrayList<UserPoint> userPoints;
  //@Inject DataManager mDataManager;

  public MainActivityPresenter() {
  }

  @Override protected void onFirstViewAttach() {
    super.onFirstViewAttach();
    App.getComponent().inject(this);

    //userPoints = new ArrayList<>();
    getViewState().setAdapter(userPoints);
    if (userPoints.isEmpty()) {
      addPoint();
    }
  }

  public void addPoint() {
    Timber.wtf("addPoint");
    userPoints.add(new UserPoint(userPoints.size() + 1));
    getViewState().showPointArray();
  }

  public ArrayList<UserPoint> getUserPoints() {
    return userPoints;
  }

  public boolean isFullList() {
    for (UserPoint userPoint : userPoints) Timber.wtf("userPoint" + userPoint.toString());

    for (UserPoint userPoint : userPoints) {
      if (userPoint.isEmpty()) return false;
    }
    return true;
  }
}
