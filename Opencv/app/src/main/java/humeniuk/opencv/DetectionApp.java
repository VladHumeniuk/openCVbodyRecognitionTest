package humeniuk.opencv;

import android.app.Application;

import org.opencv.android.OpenCVLoader;

public class DetectionApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!OpenCVLoader.initDebug()) {
            //TODO handle error
        }
    }
}
