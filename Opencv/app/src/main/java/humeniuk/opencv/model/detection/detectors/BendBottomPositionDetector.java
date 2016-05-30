package humeniuk.opencv.model.detection.detectors;

import org.opencv.core.Mat;

import humeniuk.opencv.R;
import humeniuk.opencv.model.Position;
import humeniuk.opencv.model.detection.DetectionListener;
import humeniuk.opencv.model.detection.PositionDetector;

public class BendBottomPositionDetector extends PositionDetector {

    public static final String FILE_NAME = "/haarcascade_bottombend.xml";
    public static final int RES = 0;

    public BendBottomPositionDetector(DetectionListener listener, Mat mat) {
        super(listener, mat);
    }

    @Override
    protected String getHaarFileName() {
        return FILE_NAME;
    }

    @Override
    protected Position getDetectingPosition() {
        return Position.BEND_BOTTOM;
    }
}
