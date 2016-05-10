package humeniuk.opencv.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import humeniuk.opencv.FragmentNavigator;
import humeniuk.opencv.ui.MainActivity;

public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(getLayoutId(), container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, getActivity());

        if(getArguments() != null) {
            restoreArguments(getArguments());
        }
        setupViews();
    }

    protected void setupViews() {

    }

    protected void restoreArguments(Bundle args) {

    }

    protected MainActivity getBaseActivity() {
        return (MainActivity)getActivity();
    }

    protected FragmentNavigator getNavigator() {
        return getBaseActivity().getNavigator();
    }

    abstract protected int getLayoutId();
}
