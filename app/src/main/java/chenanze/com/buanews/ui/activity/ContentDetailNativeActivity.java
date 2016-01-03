package chenanze.com.buanews.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import chenanze.com.buanews.Constant;
import chenanze.com.buanews.R;
import chenanze.com.buanews.entity.WebContent;
import chenanze.com.buanews.network.crawler.WebCrawler;
import chenanze.com.buanews.util.CircularProgressBarUtils;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;

public class ContentDetailNativeActivity extends BaseActivity {

    private WebContent webContent;
    private WebContent contentDetail;
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private TextView mCreationTimeTextView;
    private CircularProgressBar mCircularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_detail_native);
        mTitleTextView = (TextView) findViewById(R.id.title_tv);
        mContentTextView = (TextView) findViewById(R.id.content_tv);
        mCreationTimeTextView = (TextView) findViewById(R.id.content_creation_time_tv);
        mCircularProgressBar = (CircularProgressBar) findViewById(R.id.fragment_circularProgressbar);

        webContent = (WebContent) getIntent().getSerializableExtra(Constant.INTENT_CONTENT_DETAIL_NATIVE);

        fetchData();

    }

    private void fetchData() {
        CircularProgressBarUtils.setCircularProgressBar(View.VISIBLE, mCircularProgressBar);
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
                try {
                    contentDetail = WebCrawler.getInstance().getWebContent(webContent.getUrl());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                CircularProgressBarUtils.setCircularProgressBar(View.INVISIBLE, mCircularProgressBar);

                if (contentDetail.getTitle() != null) {
                    mTitleTextView.setText(contentDetail.getTitle());
                }
                if (contentDetail.getContent() != null) {
                    mContentTextView.setText(contentDetail.getContent());
                }
                if (contentDetail.getCreationTime() != null) {
                    mCreationTimeTextView.setText(contentDetail.getCreationTime());
                }
            }
        };

        asyncTask.execute();
    }
}
