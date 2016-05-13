package humeniuk.opencv.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.ButterKnife;
import humeniuk.opencv.FragmentNavigator;
import humeniuk.opencv.R;
import humeniuk.opencv.model.Training;
import humeniuk.opencv.model.TrainingItem;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private FragmentNavigator mNavigator;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity_layout);
        ButterKnife.bind(this);

        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(getApplicationContext())
                .deleteRealmIfMigrationNeeded().name(getApplication().getPackageName()).build());

//        initdb();

        mNavigator = new FragmentNavigator(getSupportFragmentManager());
        mNavigator.showMainFragment();
    }

    public FragmentNavigator getNavigator() {
        return mNavigator;
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 1) {
            manager.popBackStack();
        } else {
            finish();
        }
    }

    private void initdb() {
        long time = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        Training tr1 = realm.createObject(Training.class);
        tr1.setTime(time);
        tr1.setId(UUID.randomUUID().toString());

        List<TrainingItem> items = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            TrainingItem ti1 = realm.createObject(TrainingItem.class);
            ti1.setId(UUID.randomUUID().toString());
            ti1.setTime(time + i * 2000);
            ti1.setName(i % 2 == 1 ? "Bend" : "Squat");
            ti1.setTraining(tr1);
            items.add(ti1);
        }
        realm.commitTransaction();
    }
}
