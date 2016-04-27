package humeniuk.opencv.utils;

import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import humeniuk.opencv.model.DetectionListener;
import humeniuk.opencv.model.ExerciseState;
import humeniuk.opencv.model.InitialPositionDetector;
import humeniuk.opencv.model.Position;

public class DetectorExecutionManager {

    private static final int DETECTORS_COUNT = 1;

    private final ThreadPoolExecutor mExecutor;
    private final BlockingQueue<Runnable> mSyncQueue;
    private ExerciseHandler mExerciseHandler;

    public DetectorExecutionManager() {
        mSyncQueue = new LinkedBlockingDeque<>();
        mExecutor = new ThreadPoolExecutor(4, 12, 0, TimeUnit.MILLISECONDS, mSyncQueue);
        mExerciseHandler = new ExerciseHandler();
    }

    public void pushMat(Mat mat) {
        initDetectors(mat);
    }

    private void initDetectors(Mat mat) {
        PositionDetectionListener listener = new PositionDetectionListener();
        mExecutor.execute(new InitialPositionDetector(listener, mat));
    }

    private class PositionDetectionListener implements DetectionListener {

        private List<ExerciseState> mDetected;
        private int answerCount;

        public PositionDetectionListener() {
            mDetected = new ArrayList<>();
            answerCount = 0;
        }

        @Override
        public void onDetected(Position position) {
            mDetected.add(new ExerciseState(System.currentTimeMillis(), position));
            answerCount++;
            checkDetected();
        }

        @Override
        public void onNothingDetected() {
            answerCount++;
            checkDetected();
        }

        private void checkDetected() {
            if (answerCount == DETECTORS_COUNT) {
                submitResult();
            }
        }

        private void submitResult() {
            if (mDetected.size() == 0) {
                return;
            } else if (mDetected.size() == 1) {
                mExerciseHandler.addExerciseState(mDetected.get(0));
            } else {
                mExerciseHandler.addExerciseState(mDetected.get(0));
                //TODO deal with it properly
            }
        }
    }
}
