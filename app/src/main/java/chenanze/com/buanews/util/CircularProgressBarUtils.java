package chenanze.com.buanews.util;

import android.view.View;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

/**
 * Created by duian on 15/10/24.
 */
public class CircularProgressBarUtils {
    /**
     * 设置CircularProgressBar的可见性及动画的开启于停止
     *
     * @param visiblity
     */
    public static void setCircularProgressBar(int visiblity, CircularProgressBar circularProgressBar) {
        circularProgressBar.setVisibility(visiblity);
        switch (visiblity) {
            case View.INVISIBLE:
                ((CircularProgressDrawable) circularProgressBar.getIndeterminateDrawable()).progressiveStop();
                break;
            case View.VISIBLE:
                ((CircularProgressDrawable) circularProgressBar.getIndeterminateDrawable()).start();
                break;
        }
    }
}
