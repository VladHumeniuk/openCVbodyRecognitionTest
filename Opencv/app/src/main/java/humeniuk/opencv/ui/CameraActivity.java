package humeniuk.opencv.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import humeniuk.opencv.R;
import humeniuk.opencv.utils.DetectorExecutionManager;
import humeniuk.opencv.utils.ExerciseHandler;

public class CameraActivity extends Activity {

    private static final String haarFileName = "/haarcascade_fullbody.xml";
    private String haarFile = "";
    private int frameCount = 0;
    private DetectorExecutionManager mDetectorManager;

    @Bind(R.id.HelloOpenCvView) CameraBridgeViewBase mCameraViewBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initHaarFile();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDetectorManager = new DetectorExecutionManager();
        mCameraViewBase.setMaxFrameSize(360, 240);
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
                int n;
                while (-1 != (n = inputStream.read(buffer))) {
                    learnedDataOutputStream.write(buffer, 0, n);
                }
            }
        } catch (IOException e) {
            Log.d("testCV", e.getMessage());
        }
    }

    private CameraBridgeViewBase.CvCameraViewListener2 mCameraListener = new CameraBridgeViewBase.CvCameraViewListener2() {

        @Override
        public void onCameraViewStarted(int width, int height) {

        }

        @Override
        public void onCameraViewStopped() {

        }

        @Override
        public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            frameCount = (frameCount+1) % 5;
            return detectFullBody(inputFrame);
        }

        private Mat detectFullBody(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            Mat mat = inputFrame.rgba();
            if (frameCount  == 0) {
                mDetectorManager.pushMat(mat);
            }
            return mat;
        }
    };

    //http://coding-robin.de/2013/07/22/train-your-own-opencv-haar-classifier.html - haar classifier creation
    //https://solarianprogrammer.com/2015/05/08/detect-red-circles-image-using-opencv/ - circles
    //https://gist.github.com/dynamicguy/3d1fce8dae65e765f7c4
    //http://opencvuser.blogspot.in/2011/08/creating-haar-cascade-classifier-aka.html
}
//opencv_traincascade -data classifier -vec samples.vec -bg negatives.txt\
//        -numStages 20 -minHitRate 0.999 -maxFalseAlarmRate 0.5 -numPos 1000\
//        -numNeg 600 -w 160 -h 160 -mode ALL -precalcValBufSize 1024\
//        -precalcIdxBufSize 1024