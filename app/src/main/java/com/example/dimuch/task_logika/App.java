package com.example.dimuch.task_logika;

import android.app.Application;
import com.example.dimuch.task_logika.di.components.AppComponent;
import com.example.dimuch.task_logika.di.components.DaggerAppComponent;
import com.example.dimuch.task_logika.di.modules.AppModule;
import timber.log.Timber;

public class App extends Application {

  public static AppComponent getComponent() {
    return component;
  }

  private static AppComponent component;

  @Override public void onCreate() {
    super.onCreate();
    component = buildComponent();

    Timber.plant(new Timber.DebugTree());
  }

  private AppComponent buildComponent() {
    return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }
}