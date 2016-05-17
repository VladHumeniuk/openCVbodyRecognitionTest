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
import butterknife.OnClick;
import humeniuk.opencv.R;
import humeniuk.opencv.model.Training;
import humeniuk.opencv.model.TrainingItem;
import humeniuk.opencv.utils.ListColors;
import io.realm.Realm;

public class TrainingAdapter extends BaseRecyclerAdapter<Training> {

    public TrainingAdapter(LayoutInflater mInflater, OnItemSelectedListener mListener, List<Training> data) {
        super(mInflater, mListener, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TrainingHolder(mInflater.inflate(R.layout.training_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TrainingHolder) holder).bind(mData.get(position), position);
    }

    protected class TrainingHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.date) TextView mDate;
        @Bind(R.id.colorizer1) View mColorizer1;
        @Bind(R.id.colorizer2) View mColorizer2;

        private String id;

        public TrainingHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(mOnClickListener);
        }

        public void bind(Training training, int position) {
            mDate.setText(DateFormat.format("dd MMMM yyyy HH:mm:ss", training.getTime()));
            id = training.getId();
            int color = position % ListColors.values().length;
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

        @OnClick(R.id.colorizer2)
        protected void delete() {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.where(Training.class)
                    .equalTo("id", getId()).findAll().deleteAllFromRealm();
            realm.where(TrainingItem.class)
                    .equalTo("training.id", getId()).findAll().deleteAllFromRealm();
            realm.commitTransaction();
            notifyDataSetChanged();
        }
    }


}
