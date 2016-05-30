package humeniuk.opencv.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import humeniuk.opencv.R;
import humeniuk.opencv.model.detection.detectors.BendBottomPositionDetector;
import humeniuk.opencv.model.detection.detectors.BottomSquatPositionDetector;
import humeniuk.opencv.model.detection.detectors.InitialPositionDetector;
import humeniuk.opencv.utils.DetectorExecutionManager;

public class CameraActivity extends Activity {

    private int frameCount = 0;
    private DetectorExecutionManager mDetectorManager;

    @Bind(R.id.HelloOpenCvView) CameraBridgeViewBase mCameraViewBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initHaars();

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

    private void initHaarFile(String fileName, int res) {
        try {
            InputStream inputStream = getResources().openRawResource(res);

            File learnedInputFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + fileName);
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

    private void initHaars() {
        initHaarFile(InitialPositionDetector.FILE_NAME, InitialPositionDetector.RES);
//        initHaarFile(BottomSquatPositionDetector.FILE_NAME, BottomSquatPositionDetector.RES);
//        initHaarFile(BendBottomPositionDetector.FILE_NAME, BendBottomPositionDetector.RES);
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