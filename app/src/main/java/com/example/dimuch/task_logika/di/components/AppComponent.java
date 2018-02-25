package com.example.dimuch.task_logika.di.components;

import com.example.dimuch.task_logika.di.modules.AppModule;
import com.example.dimuch.task_logika.feature.adapters.PointAdapter;
import com.example.dimuch.task_logika.feature.presenters.CoordinatePlaneActivityPresenter;
import com.example.dimuch.task_logika.feature.presenters.MainActivityPresenter;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules = AppModule.class) @Singleton public interface AppComponent {

  void inject(MainActivityPresenter mainActivityPresenter);

  void inject(CoordinatePlaneActivityPresenter coordinatePlaneActivityPresenter);
}