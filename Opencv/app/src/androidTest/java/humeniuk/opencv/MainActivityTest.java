package humeniuk.opencv;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.robotium.solo.Solo;

import humeniuk.opencv.ui.CameraActivity;
import humeniuk.opencv.ui.MainActivity;
import humeniuk.opencv.ui.fragments.TrainingDetailsFragment;
import humeniuk.opencv.ui.fragments.TrainingListFragment;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testNotNull() throws Exception {
        Solo solo = new Solo(getInstrumentation(), getActivity());
        Button goToList = solo.getButton(0);
        Button goToCamera = solo.getButton(0);
        assertNotNull(goToList);
        assertNotNull(goToCamera);
    }

    public void testCameraOpen() {
        Solo solo = new Solo(getInstrumentation(), getActivity());
        solo.clickOnButton(solo.getString(R.string.start_training));
        assertTrue(solo.waitForActivity(CameraActivity.class));
        solo.goBack();
    }

    public void testHistory() {
        Solo solo = new Solo(getInstrumentation(), getActivity());
        solo.clickOnButton(solo.getString(R.string.show_history_label));
        solo.waitForFragmentByTag(TrainingListFragment.class.getSimpleName(), 5000);
        solo.goBack();
    }

    public void testTrainingDetails() {
        Solo solo = new Solo(getInstrumentation(), getActivity());
        solo.clickOnButton(solo.getString(R.string.show_history_label));
        solo.clickInRecyclerView(1);
        solo.waitForFragmentByTag(TrainingDetailsFragment.class.getSimpleName(), 5000);
        solo.goBack();
        solo.goBack();
    }
}
