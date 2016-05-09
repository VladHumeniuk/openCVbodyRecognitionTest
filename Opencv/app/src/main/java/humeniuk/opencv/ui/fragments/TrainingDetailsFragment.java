package humeniuk.opencv.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;

import humeniuk.opencv.model.TrainingItem;
import humeniuk.opencv.ui.adapters.BaseRecyclerAdapter;
import humeniuk.opencv.ui.adapters.TrainingItemsAdapter;
import io.realm.Realm;

public class TrainingDetailsFragment extends BaseListFragment {

    private static final String TRAINING_ID_ARG = "training_id";

    private String mTrainingId;

    public static TrainingDetailsFragment newInstance(String trainingId) {

        Bundle args = new Bundle();
        args.putString(TRAINING_ID_ARG, trainingId);

        TrainingDetailsFragment fragment = new TrainingDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadData() {
        Realm realm = Realm.getDefaultInstance();
        getAdapter().swapData(realm.where(TrainingItem.class).equalTo("training.id", mTrainingId).findAll());
    }

    @Override
    protected void restoreArguments(Bundle args) {
        super.restoreArguments(args);
        mTrainingId = args.getString(TRAINING_ID_ARG);
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new TrainingItemsAdapter(LayoutInflater.from(getContext()), mListener);
    }

    private BaseRecyclerAdapter.OnItemSelectedListener mListener = new BaseRecyclerAdapter.OnItemSelectedListener() {
        @Override
        public void onItemSelected(String id) {

        }
    };
}
