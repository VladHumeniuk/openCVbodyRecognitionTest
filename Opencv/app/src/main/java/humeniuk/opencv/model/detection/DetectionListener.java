package humeniuk.opencv.model.detection;

import humeniuk.opencv.model.Position;

public interface DetectionListener {

    void onDetected(Position position);
    void onNothingDetected();
}
