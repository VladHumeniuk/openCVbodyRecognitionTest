package humeniuk.opencv.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;

import humeniuk.opencv.model.Training;
import humeniuk.opencv.ui.adapters.BaseRecyclerAdapter;
import humeniuk.opencv.ui.adapters.TrainingAdapter;
import io.realm.Realm;

public class TrainingListFragment extends BaseListFragment {

    public static TrainingListFragment newInstance() {

        Bundle args = new Bundle();

        TrainingListFragment fragment = new TrainingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadData() {
        Realm realm = Realm.getDefaultInstance();
        getAdapter().swapData(realm.where(Training.class).findAll());
    }

    @Override
    protected BaseRecyclerAdapter createAdapter() {
        return new TrainingAdapter(LayoutInflater.from(getContext()), mListener);
    }

    private BaseRecyclerAdapter.OnItemSelectedListener mListener = new BaseRecyclerAdapter.OnItemSelectedListener() {
        @Override
        public void onItemSelected(String id) {

        }
    };
}
