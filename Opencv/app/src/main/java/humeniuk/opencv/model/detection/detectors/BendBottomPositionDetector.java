package humeniuk.opencv.model.detection.detectors;

import org.opencv.core.Mat;

import humeniuk.opencv.model.Position;
import humeniuk.opencv.model.detection.DetectionListener;
import humeniuk.opencv.model.detection.PositionDetector;

public class BendBottomPositionDetector extends PositionDetector {

    public BendBottomPositionDetector(DetectionListener listener, Mat mat) {
        super(listener, mat);
    }

    @Override
    protected String getHaarFileName() {
        return null;
    }

    @Override
    protected Position getDetectingPosition() {
        return Position.BEND_BOTTOM;
    }
}
