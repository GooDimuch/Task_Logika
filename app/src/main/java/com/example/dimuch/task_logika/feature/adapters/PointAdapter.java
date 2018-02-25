package com.example.dimuch.task_logika.feature.adapters;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import com.example.dimuch.task_logika.R;
import com.example.dimuch.task_logika.data.model.UserPoint;
import java.util.ArrayList;
import timber.log.Timber;

/**
 * Created by Dimuch on 25.02.2018.
 */

public class PointAdapter extends RecyclerView.Adapter<PointAdapter.ViewHolder> {
  private ArrayList<UserPoint> pointArray;

  public PointAdapter() {
    pointArray = new ArrayList<>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_point, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    if (getItemCount() == 1) holder.fabMinus.setVisibility(View.INVISIBLE);
    else holder.fabMinus.setVisibility(View.VISIBLE);

    holder.tvNumberPoint.setText(String.valueOf(position + 1) + ": ");

    UserPoint point = pointArray.get(position);
    if (point.isEmpty()) {
      holder.etX.setText("");
      holder.etY.setText("");
    } else {
      holder.etX.setText(String.valueOf(point.getX()));
      holder.etY.setText(String.valueOf(point.getY()));
    }
  }

  @Override public int getItemCount() {
    return pointArray == null ? 0 : pointArray.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvNumberPoint) TextView tvNumberPoint;
    @BindView(R.id.etCoordinateX) EditText etX;
    @BindView(R.id.etCoordinateY) EditText etY;
    @BindView(R.id.fabMinus) FloatingActionButton fabMinus;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.fabMinus) public void onClickMinus() {
      Timber.wtf("onClickMinus");
      pointArray.remove(this.getLayoutPosition());
      notifyDataSetChanged();
    }

    @OnTextChanged(R.id.etCoordinateX) public void onChangeCoordinateX() {
      Timber.wtf("onChangeCoordinateX");
      //Timber.wtf(etX.getText().toString());
      if (!etX.getText().toString().isEmpty()) {
        pointArray.get(this.getLayoutPosition())
            .setX(Double.parseDouble(etX.getText().toString()));
      }
    }

    @OnTextChanged(R.id.etCoordinateY) public void onChangeCoordinateY() {
      Timber.wtf("onChangeCoordinateY");
      if (!etY.getText().toString().isEmpty()) {
        pointArray.get(this.getLayoutPosition())
            .setY(Double.parseDouble(etY.getText().toString()));
      }
    }
  }

  public void setPointArray(ArrayList<UserPoint> pointArray) {
    Timber.wtf("setPointArray");
    this.pointArray.clear();
    this.pointArray = pointArray;

  }
}
