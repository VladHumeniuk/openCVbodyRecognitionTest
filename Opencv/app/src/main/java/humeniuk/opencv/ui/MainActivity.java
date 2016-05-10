package humeniuk.opencv.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import humeniuk.opencv.FragmentNavigator;
import humeniuk.opencv.R;
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

        mNavigator = new FragmentNavigator(getSupportFragmentManager());
        mNavigator.showMainFragment();
    }

    public FragmentNavigator getNavigator() {
        return mNavigator;
    }
}
