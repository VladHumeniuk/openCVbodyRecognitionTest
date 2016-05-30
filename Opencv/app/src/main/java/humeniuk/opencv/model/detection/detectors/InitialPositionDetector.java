package humeniuk.opencv.model.detection.detectors;

import org.opencv.core.Mat;

import humeniuk.opencv.R;
import humeniuk.opencv.model.Position;
import humeniuk.opencv.model.detection.DetectionListener;
import humeniuk.opencv.model.detection.PositionDetector;

public class InitialPositionDetector extends PositionDetector {

    public static final String FILE_NAME = "/haarcascade_fullbody.xml";
    public static final int RES = R.raw.haarcascade_fullbody;

    public InitialPositionDetector(DetectionListener listener, Mat mat) {
        super(listener, mat);
    }

    @Override
    protected String getHaarFileName() {
        return FILE_NAME;
    }

    @Override
    protected Position getDetectingPosition() {
        return Position.INITIAL;
    }
}
