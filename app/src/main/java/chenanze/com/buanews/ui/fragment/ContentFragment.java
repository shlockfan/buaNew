package chenanze.com.buanews.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabWidget;

import chenanze.com.buanews.R;
import chenanze.com.buanews.adapter.ContentFragmentViewPagerAdapter;
import chenanze.com.buanews.ui.activity.MainActivity;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class ContentFragment extends BaseFragment {
    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    public static final String BOOK = "Book";
    public static final String PAINT = "Paint";
    public static final String CASE = "Case";
    public static final String SHOP = "Shop";
    public static final String PARTY = "Party";
    public static final String MOVIE = "Movie";

    private ViewPager mViewPager;
    private ContentFragmentViewPagerAdapter mContentFragmentViewPagerAdapter;
    private TabWidget mTabWidget;
    private Button[] mBtnTabs = new Button[3];
    private int currentIndex;


    private static View containerView = null;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private NewsContentFragment contentFragment;
    private ImageView mTabLineImageView;
    private int screenWidth;
    private int widthTmp;

    public static ContentFragment newInstance() {
        ContentFragment contentFragment = new ContentFragment();
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 设置 UI 视图
        setView(view);


    }

    /**
     * 设置 UI 视图
     * @param view
     */
    private void setView(View view) {
        containerView = view.findViewById(R.id.container);
        mBtnTabs[0] = (Button) view.findViewById(R.id.bua_news_bt);
        mBtnTabs[1] = (Button) view.findViewById(R.id.bua_media_bt);
        mBtnTabs[2] = (Button) view.findViewById(R.id.bua_notice_bt);
        mTabLineImageView = (ImageView) view.findViewById(R.id.tab_line_iv);

        mTabWidget = (TabWidget) view.findViewById(R.id.main_fragment_tabWidget);
        mTabWidget.setStripEnabled(false);
        for (int i = 0; i < mBtnTabs.length; i++) {
            mBtnTabs[i].setFocusable(true);
            mBtnTabs[i].setOnClickListener(mTabClickListener);
        }

        mViewPager = (ViewPager) view.findViewById(R.id.main_fragment_vp);
        mContentFragmentViewPagerAdapter = new ContentFragmentViewPagerAdapter(((MainActivity) mContext).getSupportFragmentManager(), mContext);
        mViewPager.setAdapter(mContentFragmentViewPagerAdapter);
        mViewPager.addOnPageChangeListener(mPageChangeListener);
        mViewPager.setOffscreenPageLimit(3);

        mTabWidget.setCurrentTab(0);

        initTabLineWidth();
    }

    /**
     * tab button 点击监听 切换页面
     */
    private View.OnClickListener mTabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBtnTabs[0]) {
                mViewPager.setCurrentItem(0);
                // 初始化 Fragment 数据
//                mContentFragmentViewPagerAdapter.initData(0);
                Log.d(TAG, "onClick: mBtnTabs 0");
            } else if (v == mBtnTabs[1]) {
                mViewPager.setCurrentItem(1);
                // 初始化 Fragment 数据
//                mContentFragmentViewPagerAdapter.initData(1);
                Log.d(TAG, "onClick: mBtnTabs 1");
            } else if (v == mBtnTabs[2]) {
                mViewPager.setCurrentItem(2);
                // 初始化 Fragment 数据
//                mContentFragmentViewPagerAdapter.initData(2);
                Log.d(TAG, "onClick: mBtnTabs 2");
            }
        }
    };

    /**
     * ViewPager 页面切换监听 切换页面
     */
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int index) {
            mTabWidget.setCurrentTab(index);
            currentIndex = index;
//            fetchIndexPagerData(index);
            // 初始化 Fragment 数据
//            mContentFragmentViewPagerAdapter.initData(index);
            Log.d(TAG, "onPageSelected "+ index);
        }

        /**
         * position: 当前页面，及你点击滑动的页面
         * offset: 当前页面偏移的百分比
         * offsetPixels: 当前页面偏移的像素位置
         */
        @Override
        public void onPageScrolled(int position, float offset, int offsetPixels) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineImageView
                    .getLayoutParams();

//            Log.e("offset:", offset + "");
            /**
             * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
             * 设置mTabLineIv的左边距 滑动场景：
             * 记3个页面,
             * 从左到右分别为0,1,2
             * 0->1; 1->2; 2->1; 1->0
             */

            if (currentIndex == 0 && position == 0)// 1->0
            {
                lp.width = (int)(widthTmp * (1 - offset));
//                float tmp = 1 - offset;
//                Log.e(TAG, "1 - offset: " + tmp);

                lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));
                Log.e(TAG, "1-0 width: "+lp.width);

            } else if (currentIndex == 1 && position == 0) // 0->1
            {
                lp.width = (int)(widthTmp * offset);
                lp.leftMargin = (int) (-(1 - offset)
                        * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));
                Log.e(TAG, "0->1 width: "+lp.width );

            } else if (currentIndex == 1 && position == 1) // 2->1
            {
                lp.width = (int)(widthTmp * (1 - offset));
                lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));
            } else if (currentIndex == 2 && position == 1) // 1->2
            {
                lp.width = (int)(widthTmp * offset);

                lp.leftMargin = (int) (-(1 - offset)
                        * (screenWidth * 1.0 / 3) + currentIndex
                        * (screenWidth / 3));
            }
//            Log.e(TAG, "leftMargin: "+lp.leftMargin);
//            Log.e(TAG, "position: "+position);
//            Log.e(TAG, "currentIndex: "+currentIndex);
//            Log.e(TAG, "width: "+ lp.width);
            mTabLineImageView.setLayoutParams(lp);

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        res = getArguments().getInt(Integer.class.getName());

        /*
         * Listener必须在mTabWidget.addView()之后再加入，用于覆盖默认的Listener，
         * mTabWidget.addView()中默认的Listener没有NullPointer检测。
         */

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);
//        mImageView = (ImageView) rootView.findViewById(R.id.image_content);
//        mImageView.setClickable(true);
//        mImageView.setFocusable(true);
//        mImageView.setImageResource(res);
        return rootView;
    }

    /**
     * 填充当前对应fragment数据
     *
     * @param index
     */
    public void fetchIndexPagerData(int index) {
        switch (index) {
            case 0:
                Log.d(TAG, "fetchIndexPagerData: "+index);
                contentFragment = (NewsContentFragment) mContentFragmentViewPagerAdapter.instantiateItem(mViewPager, index);
                contentFragment.fetchData();
//                contentFragment.preFetchData();
                break;
            case 1:
//                contentFragment = (ContentFragment) materialViewPagerAdapter.instantiateItem(mViewPager, index);
//                contentFragment.preFetchData();
                break;
            case 2:
                break;
            default:
                break;
        }
    }


    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {

        // 获取屏幕宽度
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;

        // 根据屏幕宽度设置 TabLineImageView 宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineImageView
                .getLayoutParams();
        lp.width = screenWidth / 3;
        widthTmp = lp.width;
        mTabLineImageView.setLayoutParams(lp);
    }

    @Override
    public void takeScreenShot() {

        ((MainActivity)mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                ContentFragment.this.bitmap = bitmap;
            }
        });

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

