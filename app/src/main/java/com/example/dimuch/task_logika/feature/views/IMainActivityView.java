package com.example.dimuch.task_logika.feature.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Dimuch on 06.02.2018.
 */

@StateStrategyType(AddToEndStrategy.class) public interface IMainActivityView extends MvpView {

  void showToast(String sToastMessage);
}
