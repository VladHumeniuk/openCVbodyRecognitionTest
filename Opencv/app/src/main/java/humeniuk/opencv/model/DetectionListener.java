package humeniuk.opencv.model;

public interface DetectionListener {

    void onDetected(Position position);
    void onNothingDetected();
}
