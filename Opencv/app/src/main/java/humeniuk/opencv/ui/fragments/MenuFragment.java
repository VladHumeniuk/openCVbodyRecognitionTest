package humeniuk.opencv.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import humeniuk.opencv.R;
import humeniuk.opencv.ui.CameraActivity;

public class MenuFragment extends BaseFragment {

    public static MenuFragment newInstance() {

        Bundle args = new Bundle();

        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.menu_fragment;
    }

    @OnClick(R.id.show_history)
    protected void onOpenHistoryClick() {
        getNavigator().showTrainingList();
    }

    @OnClick(R.id.start_training)
    protected void onStartTrainingClick() {
        Intent camera = new Intent(getBaseActivity(), CameraActivity.class);
        getBaseActivity().startActivity(camera);
    }

}
