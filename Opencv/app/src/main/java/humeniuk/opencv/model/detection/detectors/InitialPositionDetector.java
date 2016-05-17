package humeniuk.opencv.model.detection.detectors;

import org.opencv.core.Mat;

import humeniuk.opencv.model.Position;
import humeniuk.opencv.model.detection.DetectionListener;
import humeniuk.opencv.model.detection.PositionDetector;

public class InitialPositionDetector extends PositionDetector {

    public InitialPositionDetector(DetectionListener listener, Mat mat) {
        super(listener, mat);
    }

    @Override
    protected String getHaarFileName() {
        return "/haarcascade_fullbody.xml";
    }

    @Override
    protected Position getDetectingPosition() {
        return Position.INITIAL;
    }
}
