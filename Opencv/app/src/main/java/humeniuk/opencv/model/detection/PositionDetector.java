package humeniuk.opencv.model.detection;

import android.os.Environment;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

import humeniuk.opencv.model.Position;
import humeniuk.opencv.model.detection.DetectionListener;

public abstract class PositionDetector implements Runnable {

    private CascadeClassifier mClassifier;
    private DetectionListener mListener;
    private Mat mMat;

    public PositionDetector(DetectionListener listener, Mat mat) {
        mListener = listener;
        mMat = mat;
        mClassifier = new CascadeClassifier(
                Environment.getExternalStorageDirectory().getAbsolutePath() + getHaarFileName());
    }

    @Override
    public void run() {
        MatOfRect rect = new MatOfRect();
        mClassifier.detectMultiScale(mMat, rect);
        if (rect.toArray().length > 0) {
            mListener.onDetected(getDetectingPosition());
        } else {
            mListener.onNothingDetected();
        }
    }

    protected abstract String getHaarFileName();
    protected abstract Position getDetectingPosition();
}
