package com.example.dimuch.task_logika.feature.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.dimuch.task_logika.R;
import com.example.dimuch.task_logika.data.model.UserPoint;
import com.example.dimuch.task_logika.feature.adapters.PointAdapter;
import com.example.dimuch.task_logika.feature.presenters.MainActivityPresenter;
import com.example.dimuch.task_logika.feature.views.IMainActivityView;
import com.example.dimuch.task_logika.utils.CustomLayoutManager;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements IMainActivityView {

  @BindView(R.id.rvPoint) RecyclerView rvPoint;
  @BindView(R.id.ivPreview) ImageView ivPreview;

  @InjectPresenter MainActivityPresenter mainActivityPresenter;
  private PointAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.fabAdd) public void onClickAdd() {
    showTimber("onClickAdd");
    if (mainActivityPresenter.isFullList()) {
      mainActivityPresenter.addPoint();
    } else {
      showToast("Fill in all the fields");
    }
  }

  @OnClick(R.id.ivPreview) public void onClickPreview() {
    showTimber("onClickPreview");
    if (mainActivityPresenter.isFullList()) {
      Intent intent = new Intent(this, CoordinatePlaneActivity.class);
      startActivity(intent);
    } else {
      showToast("Fill in all the fields");
    }
  }

  @Override public void setAdapter(ArrayList<UserPoint> userPoints) {
    rvPoint.setLayoutManager(new CustomLayoutManager(this));
    adapter = new PointAdapter();
    adapter.setUserPoints(userPoints);
    rvPoint.setAdapter(adapter);
  }

  @Override public void uploadPreviewImage() {
    Picasso.with(getApplicationContext())
        .load(R.drawable.preview)
        .into(ivPreview);
  }

  @Override public void showPointArray() {
    //showTimber("showPointArray");
    adapter.notifyDataSetChanged();
    Timber.wtf(String.valueOf(adapter.getItemCount()));
    rvPoint.smoothScrollToPosition(adapter.getItemCount());
  }

  @Override public void showToast(String sToastMessage) {
    Timber.wtf("showToast");
    Toast.makeText(getApplicationContext(), sToastMessage, Toast.LENGTH_LONG).show();
  }

  @Override public void showTimber(String sToastMessage) {
    Timber.wtf(sToastMessage);
  }
}
