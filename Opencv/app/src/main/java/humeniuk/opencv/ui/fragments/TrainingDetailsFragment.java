package humeniuk.opencv.ui.fragments;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.widget.TextView;

import butterknife.Bind;
import humeniuk.opencv.R;
import humeniuk.opencv.model.exercises.BendExercise;
import humeniuk.opencv.model.exercises.SquatExercise;
import humeniuk.opencv.model.Training;
import humeniuk.opencv.model.TrainingItem;
import humeniuk.opencv.ui.adapters.BaseRecyclerAdapter;
import humeniuk.opencv.ui.adapters.TrainingItemsAdapter;
import io.realm.Realm;
import io.realm.Sort;

public class TrainingDetailsFragment extends BaseListFragment {

    private static final String TRAINING_ID_ARG = "training_id";

    @Bind(R.id.training_date) TextView mDate;
    @Bind(R.id.squats_count) TextView mSquats;
    @Bind(R.id.bends_count) TextView mBends;

    private String mTrainingId;

    public static TrainingDetailsFragment newInstance(String trainingId) {

        Bundle args = new Bundle();
        args.putString(TRAINING_ID_ARG, trainingId);

        TrainingDetailsFragment fragment = new TrainingDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void restoreArguments(Bundle args) {
        super.restoreArguments(args);
        mTrainingId = args.getString(TRAINING_ID_ARG);
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new TrainingItemsAdapter(LayoutInflater.from(getContext()), mListener,
                Realm.getDefaultInstance().where(TrainingItem.class)
                        .equalTo("training.id", mTrainingId).findAllSorted("time", Sort.ASCENDING));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.training_details_fragment;
    }

    @Override
    protected void setupViews() {
        super.setupViews();
        mSquats.setText(getString(R.string.squats_label) + String.valueOf(Realm.getDefaultInstance().where(TrainingItem.class).equalTo("training.id", mTrainingId)
                .equalTo("name", SquatExercise.TAG).findAll().size()));
        mBends.setText(getString(R.string.bends_label) + Realm.getDefaultInstance().where(TrainingItem.class).equalTo("training.id", mTrainingId)
                .equalTo("name", BendExercise.TAG).findAll().size());
        mDate.setText(getString(R.string.training_start_time_label) + DateFormat.format("dd MMMM yyyy HH:mm:ss", Realm.getDefaultInstance().where(Training.class)
                .equalTo("id", mTrainingId).findAll().get(0).getTime()));
    }

    private BaseRecyclerAdapter.OnItemSelectedListener mListener = new BaseRecyclerAdapter.OnItemSelectedListener() {
        @Override
        public void onItemSelected(String id) {

        }
    };
}
