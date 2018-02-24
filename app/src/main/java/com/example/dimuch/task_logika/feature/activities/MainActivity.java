package com.example.dimuch.task_logika.feature.activities;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.dimuch.task_logika.R;
import com.example.dimuch.task_logika.feature.presenters.MainActivityPresenter;
import com.example.dimuch.task_logika.feature.views.IMainActivityView;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements IMainActivityView {

  //@BindView(R.id.tvResultPost) TextView tvResultPost;

  @InjectPresenter MainActivityPresenter mainActivityPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @Override public void showToast(String sToastMessage) {
    Timber.e("showToast");
    Toast.makeText(getApplicationContext(), sToastMessage, Toast.LENGTH_LONG).show();
  }
}
