package humeniuk.opencv.model.detection.detectors;

import org.opencv.core.Mat;

import humeniuk.opencv.model.Position;
import humeniuk.opencv.model.detection.DetectionListener;
import humeniuk.opencv.model.detection.PositionDetector;

public class BottomSquatPositionDetector extends PositionDetector {

    public BottomSquatPositionDetector(DetectionListener listener, Mat mat) {
        super(listener, mat);
    }

    @Override
    protected String getHaarFileName() {
        return null;
    }

    @Override
    protected Position getDetectingPosition() {
        return Position.SQUAT_BOTTOM;
    }
}
