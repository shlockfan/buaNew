package chenanze.com.buanews.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chenanze.com.buanews.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class MediaContentFragment extends BaseFragment {


    private boolean isInitializedData = false;

    public MediaContentFragment() {
        // Required empty public constructor
    }

    public static MediaContentFragment newInstance() {
        return new MediaContentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_media_content, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public boolean isInitializedData() {
        return isInitializedData;
    }

    public void setIsInitializedData(boolean isInitializedData) {
        this.isInitializedData = isInitializedData;
    }

}
