package humeniuk.opencv.model;

import org.opencv.core.Mat;

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
