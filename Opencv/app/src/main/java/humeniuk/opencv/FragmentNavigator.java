package humeniuk.opencv;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import humeniuk.opencv.ui.fragments.TrainingDetailsFragment;
import humeniuk.opencv.ui.fragments.TrainingListFragment;

public class FragmentNavigator {

    private FragmentManager mFragmentManager;

    public FragmentNavigator(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
    }

    public void showTrainingList() {
        replace(TrainingListFragment.newInstance());
    }

    public void showTrainingDetails(String id) {
        replace(TrainingDetailsFragment.newInstance(id));
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_holder, fragment);
        transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }
}
