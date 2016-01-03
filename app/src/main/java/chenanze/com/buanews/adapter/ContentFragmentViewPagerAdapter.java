package chenanze.com.buanews.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

import chenanze.com.buanews.R;
import chenanze.com.buanews.ui.fragment.MediaContentFragment;
import chenanze.com.buanews.ui.fragment.NewsContentFragment;
import chenanze.com.buanews.ui.fragment.NoticeContentFragment;

/**
 * Created by duian on 15/9/24.
 */
public class ContentFragmentViewPagerAdapter extends SmartFragmentStatePagerAdapter {
    //private static int NUM_ITEMS = 1;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Context mContext;
    private NewsContentFragment newsContentFragment;
    private MediaContentFragment mediaContentFragment;
    private NoticeContentFragment noticeContentFragment;
    public ContentFragmentViewPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;

        newsContentFragment = new NewsContentFragment();
        fragments.add(newsContentFragment);
        mediaContentFragment = new MediaContentFragment();
        fragments.add(mediaContentFragment);
        noticeContentFragment = new NoticeContentFragment();
        fragments.add(noticeContentFragment);
    }

//    public void initData(int index) {
//        if (index == 0) {
//            if (!newsContentFragment.isInitializedData()) {
//                newsContentFragment.startGetContentListNetwork();
//                newsContentFragment.setIsInitializedData(true);
//            }
//        }else if (index == 1) {
//            if (!mediaContentFragment.isInitializedData()) {
////                mediaContentFragment.startGetContentListNetwork();
//                mediaContentFragment.setIsInitializedData(true);
//            }
//
//        }else if (index == 2) {
//            if (!noticeContentFragment.isInitializedData()) {
////                noticeContentFragment.startGetContentListNetwork();
//                noticeContentFragment.setIsInitializedData(true);
//            }
//
//        }
//    }

    @Override
    public Fragment getItem(int position) {
        if(this.getRegisteredFragment(position)!=null){
            return getRegisteredFragment(position);
        }else {
//            Log.d("Adapter", "getItem: " + position);
//            switch (position) {
//                case 0:
//                    return NewsContentFragment.newInstance();
//                case 1:
//                    return MediaContentFragment.newInstance();
//                case 2:
//                    return NoticeContentFragment.newInstance();
//                default:
//                    return null;
//            }
            return fragments.get(position);
        }
//		return MenuContentFragment.newInstance("pager" + position);
    }

    @Override
    public int getCount() {
//        return 3;
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position % 3) {
            case 0:
                return mContext.getString(R.string.bua_news);
            case 1:
                return mContext.getString(R.string.bua_media);
            case 2:
                return mContext.getString(R.string.notice_announcement);
        }
        return "";
    }

}
