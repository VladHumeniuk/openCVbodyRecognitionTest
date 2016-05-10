package humeniuk.opencv.ui.adapters;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import humeniuk.opencv.R;
import humeniuk.opencv.model.Training;

public class TrainingAdapter extends BaseRecyclerAdapter<Training> {

    public TrainingAdapter(LayoutInflater mInflater, OnItemSelectedListener mListener) {
        super(mInflater, mListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrainingHolder(mInflater.inflate(R.layout.training_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TrainingHolder) holder).bind(mData.get(position));
    }

    protected class TrainingHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.date) TextView mDate;

        private String id;

        public TrainingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(mOnClickListener);
        }

        public void bind(Training training) {
            mDate.setText(DateFormat.format("dd MMMM yyyy hh:mm:ss", training.getTime()));
            id = training.getId();
        }

        public String getId() {
            return id;
        }

        private View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemSelected(getId());
            }
        };
    }


}
