package chenanze.com.buanews.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by duian on 15/12/22.
 */
public class BaseFragment extends Fragment implements ScreenShotable{
    public static String TAG;
    protected Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mContext = getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        //MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        //MobclickAgent.onPageEnd(TAG);
    }

    @Override
    public void takeScreenShot() {


    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
