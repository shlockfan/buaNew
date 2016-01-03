package chenanze.com.buanews.ui.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import chenanze.com.buanews.R;
import chenanze.com.buanews.adapter.ContentRecyclerViewAdapter;
import chenanze.com.buanews.entity.WebContent;
import chenanze.com.buanews.network.crawler.WebCrawler;
import chenanze.com.buanews.util.CircularProgressBarUtils;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class NewsContentFragment extends BaseFragment implements SwipyRefreshLayout.OnRefreshListener {


    private RecyclerView mRecyclerView;
    private ArrayList<WebContent> webContentListItems = new ArrayList<>();
    private ContentRecyclerViewAdapter mContentRecyclerViewAdapter;
    private boolean isInitializedData = false;
    private String TAG = getClass().getSimpleName();
    private SwipyRefreshLayout mSwipyRefreshLayout;
    private CircularProgressBar mCircularProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static NewsContentFragment newInstance() {
        return new NewsContentFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_news_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mContentRecyclerViewAdapter = new ContentRecyclerViewAdapter(webContentListItems);
        mRecyclerView.setAdapter(mContentRecyclerViewAdapter);

        mCircularProgressBar = (CircularProgressBar) view.findViewById(R.id.fragment_circularProgressbar);

        mSwipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.fragment_news_swipy_refresh_layout);
        mSwipyRefreshLayout.setOnRefreshListener(this);

        // 适配数据
        fetchData();
        Log.d(TAG, "onViewCreated: ");
    }

    public void fetchData() {
        Logger.d(TAG,"on fetchData");
        CircularProgressBarUtils.setCircularProgressBar(View.VISIBLE, mCircularProgressBar);
        // 启动获取内容列表网络线程
        startGetContentListNetwork();
    }

    /**
     * 启动获取内容列表网络线程
     */
    private void startGetContentListNetwork() {
        Log.d(TAG, "startGetContentListNetwork: ");
        AsyncTask<Integer, Void, Void> asyncTask = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... params) {
                WebCrawler.getInstance().execteCrawls();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                CircularProgressBarUtils.setCircularProgressBar(View.INVISIBLE, mCircularProgressBar);
                mSwipyRefreshLayout.setRefreshing(false);

                ArrayList<WebContent> webContents = WebCrawler.getInstance().getWebContents();
                if (webContents != null) {
                    webContentListItems.clear();
                    webContentListItems.addAll(webContents);
                    mContentRecyclerViewAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onPostExecute: notifyDataSetChanged");
                }

            }
        };

        asyncTask.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_content, container, false);
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

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        Logger.d(TAG,"onRefresh");
        switch (direction) {
            case TOP:
                fetchData();
                break;
        }
    }
}
