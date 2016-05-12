package humeniuk.opencv.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import humeniuk.opencv.R;
import humeniuk.opencv.model.TrainingItem;
import humeniuk.opencv.utils.ListColors;

public class TrainingItemsAdapter extends BaseRecyclerAdapter<TrainingItem> {

    public TrainingItemsAdapter(LayoutInflater mInflater, OnItemSelectedListener mListener, List<TrainingItem> data) {
        super(mInflater, mListener, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrainingItemHolder(mInflater.inflate(R.layout.training_item_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TrainingItemHolder) holder).bind(mData.get(position), position);
    }

    protected class TrainingItemHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.date) TextView mDate;
        @Bind(R.id.name) TextView mName;
        @Bind(R.id.colorizer1) View mColorizer1;
        @Bind(R.id.colorizer2) View mColorizer2;

        private String id;

        public TrainingItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(mOnClickListener);
        }

        public void bind(TrainingItem trainingItem, int position) {
            mDate.setText(DateFormat.format("dd MMMM yyyy HH:mm:ss", trainingItem.getTime()));
            mName.setText(trainingItem.getName());
            id = trainingItem.getId();

            int color = ListColors.values().length;
            color = color - (position % color) -1;
            color = ListColors.values()[color].getColorRes();
            mColorizer1.setBackgroundResource(color);
            mColorizer2.setBackgroundResource(color);
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
