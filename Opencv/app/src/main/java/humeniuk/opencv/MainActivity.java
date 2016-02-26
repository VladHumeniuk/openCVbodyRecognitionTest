package humeniuk.opencv;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    private static final String haarFileName = "/haarcascade_fullbody.xml";
    private String haarFile = "";

    @Bind(R.id.HelloOpenCvView) CameraBridgeViewBase mCameraViewBase;

    static {
        if (!OpenCVLoader.initDebug()) {
            //TODO handle error
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initHaarFile();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mCameraViewBase.setMaxFrameSize(480, 300);
        mCameraViewBase.setVisibility(View.VISIBLE);
        mCameraViewBase.setCvCameraViewListener(mCameraListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraViewBase.enableView();
    }

    @Override
    protected void onPause() {
        mCameraViewBase.disableView();
        super.onPause();
    }

    private void initHaarFile() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.haarcascade_fullbody);

            File learnedInputFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + haarFileName);
            if (!learnedInputFile.exists()) {
                FileOutputStream learnedDataOutputStream = new FileOutputStream(learnedInputFile);
                byte[] buffer = new byte[300];
                int n = 0;
                while (-1 != (n = inputStream.read(buffer))) {
                    learnedDataOutputStream.write(buffer, 0, n);
                }
            }
        } catch (IOException e) {
            Log.d("testCV", e.getMessage());
        }
    }

    CameraBridgeViewBase.CvCameraViewListener2 mCameraListener = new CameraBridgeViewBase.CvCameraViewListener2() {

        private CascadeClassifier mCascadeClassifier = new CascadeClassifier(Environment.getExternalStorageDirectory().getAbsolutePath() + haarFileName);

        @Override
        public void onCameraViewStarted(int width, int height) {

        }

        @Override
        public void onCameraViewStopped() {

        }

        @Override
        public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            long time = System.currentTimeMillis();
            Log.d("testCVframe","start");
            Mat mat = inputFrame.rgba();
            MatOfRect rect = new MatOfRect();
            mCascadeClassifier.detectMultiScale(mat, rect);
            Log.d("testCVframe", "detect " + (System.currentTimeMillis() - time));
            Scalar renk = new Scalar(255, 0, 0);
            for(Rect dik : rect.toArray()) {
                Imgproc.rectangle(mat, new Point(dik.x, dik.y), new Point(dik.x + dik.width, dik.y + dik.height), renk);
            }
            Log.d("testCVframe","end");
            Log.d("testCV","" + rect.height());

            return mat;
        }
    };
    //http://coding-robin.de/2013/07/22/train-your-own-opencv-haar-classifier.html - haar classifier creation
}
