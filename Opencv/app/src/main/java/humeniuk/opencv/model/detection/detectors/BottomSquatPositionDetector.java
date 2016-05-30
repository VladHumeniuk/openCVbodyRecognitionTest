package humeniuk.opencv.model.detection.detectors;

import org.opencv.core.Mat;

import humeniuk.opencv.R;
import humeniuk.opencv.model.Position;
import humeniuk.opencv.model.detection.DetectionListener;
import humeniuk.opencv.model.detection.PositionDetector;

public class BottomSquatPositionDetector extends PositionDetector {

    public static final String FILE_NAME = "/haarcascade_bottomsquat.xml";
    public static final int RES = 0;

    public BottomSquatPositionDetector(DetectionListener listener, Mat mat) {
        super(listener, mat);
    }

    @Override
    protected String getHaarFileName() {
        return FILE_NAME;
    }

    @Override
    protected Position getDetectingPosition() {
        return Position.SQUAT_BOTTOM;
    }
}
