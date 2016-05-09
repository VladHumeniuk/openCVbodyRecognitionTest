package humeniuk.opencv.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import humeniuk.opencv.R;
import humeniuk.opencv.model.TrainingItem;

public class TrainingItemsAdapter extends BaseRecyclerAdapter<TrainingItem> {

    public TrainingItemsAdapter(LayoutInflater mInflater, OnItemSelectedListener mListener) {
        super(mInflater, mListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrainingItemHolder(mInflater.inflate(R.layout.training_item_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TrainingItemHolder) holder).bind(mData.get(position));
    }

    private class TrainingItemHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.date) TextView mDate;
        @Bind(R.id.name) TextView mName;

        private String id;

        public TrainingItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(mOnClickListener);
        }

        public void bind(TrainingItem trainingItem) {
            mDate.setText(DateFormat.format("dd MMMM yyyy hh:mm:ss", trainingItem.getTime()));
            mName.setText(trainingItem.getName());
            id = trainingItem.getId();
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
