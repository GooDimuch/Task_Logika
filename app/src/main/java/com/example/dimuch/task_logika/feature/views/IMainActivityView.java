package com.example.dimuch.task_logika.feature.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.dimuch.task_logika.data.model.UserPoint;
import java.util.ArrayList;

/**
 * Created by Dimuch on 06.02.2018.
 */

@StateStrategyType(AddToEndStrategy.class) public interface IMainActivityView extends MvpView {

  void setAdapter(ArrayList<UserPoint> pointArray);

  void uploadPreviewImage();

  void showPointArray();

  void showToast(String sToastMessage);

  void showTimber(String sToastMessage);
}
