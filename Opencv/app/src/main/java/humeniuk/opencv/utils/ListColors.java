package humeniuk.opencv.utils;

import android.support.annotation.ColorRes;

import humeniuk.opencv.R;

public enum ListColors {

    GREEN(R.color.green),
    RED(R.color.red),
    BLUE(R.color.blue),
    YELLOW(R.color.yellow),
    PURPLE(R.color.purple);

    private int colorRes;

    ListColors(@ColorRes int res) {
        colorRes = res;
    }

    public int getColorRes() {
        return colorRes;
    }
}
