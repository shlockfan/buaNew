package chenanze.com.buanews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;

import java.util.List;

import chenanze.com.buanews.Constant;
import chenanze.com.buanews.R;
import chenanze.com.buanews.entity.WebContent;
import chenanze.com.buanews.ui.activity.ContentDetailNativeActivity;
import chenanze.com.buanews.ui.activity.ContentDetailWebActivity;

/**
 * Created by duian on 24/04/15.
 */
public class ContentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<WebContent> contents;
    private static final String TAG = ContentRecyclerViewAdapter.class.getSimpleName();

    public interface OnRecyclerViewListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public ContentRecyclerViewAdapter(List<WebContent> contents) {
        this.contents = contents;
    }

    public void addItem(WebContent content, int position) {
        contents.add(content);
        notifyItemInserted(position);
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return Constant.CONTENT_LIST_ITEM_TYPE_CELL;
            default:
                return Constant.CONTENT_LIST_ITEM_TYPE_CELL;
        }
    }


    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int itemType) {
        View view;

        switch (itemType) {
            case Constant.CONTENT_LIST_ITEM_TYPE_HEADER:
                break;
            case Constant.CONTENT_LIST_ITEM_TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_content_card_cell, parent, false);
                return new ContentListItemSmallViewHolder(view);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder, i: " + position + ", viewHolder: " + holder);
        WebContent content = contents.get(position);
        switch (getItemViewType(position)) {
            case Constant.CONTENT_LIST_ITEM_TYPE_HEADER:
//                final ContentListItemBigViewHolder contentListItemBigViewHolder = (ContentListItemBigViewHolder) holder;
//                //设置内容标题
//                contentListItemBigViewHolder.contentTitleTextView.setText(content.getTitle());
//                contentListItemBigViewHolder.contentDescribeTextView.setText(content.getIntro());
//                // 从网络获取,设置Header item user Logo
//                setLogoCircleImageView(
//                        //获取高清图片URL
//                        NetworkUtils.getImageUrl(content.getUserLogo()), holder);
//                // 从网络获取,设置 ImageView
//                setImageView(
//                        //获取高清图片URL
//                        NetworkUtils.getImageUrl(content.getCoverImageFileName()),
//                        Config.TYPE_HEADER,
//                        contentListItemBigViewHolder);
//                //设置监听
//                contentListItemBigViewHolder.itemBigRippleView.setOnRippleCompleteListener(new OnRippleCompleteListener() {
//                    @Override
//                    public void onComplete(RippleView rippleView) {
//                        //设置ContentFragment 中 CircularProgressBar为不可见并停止动画
////                        ContentFragment.setLoadingDialog(View.INVISIBLE);
//                        CircularProgressBarUtils.setCircularProgressBar(View.INVISIBLE, ContentFragment.circularProgressBar);
//                        //启动ContentDetailActivity
//                        intentToContentDetailActivity(rippleView, position);
//                    }
//                });


        //设置监听
//                contentListItemBigViewHolder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //设置CircularProgressBar为不可见并停止动画
//                        ContentFragment.setLoadingDialog(View.INVISIBLE);
//                        //启动ContentDetailActivity
//                        intentToContentDetailActivity(v,position);
//                    }
//                });
                break;
            case Constant.CONTENT_LIST_ITEM_TYPE_CELL:
                final ContentListItemSmallViewHolder contentListItemSmallViewHolder = (ContentListItemSmallViewHolder) holder;
                //设置内容标题
                contentListItemSmallViewHolder.contentTitleTextView.setText(content.getTitle());

                //设置内容简介
//                contentListItemSmallViewHolder.contentDescribeTextView.setText(content.getIntro());

                //设置内容封面
//                setImageView(
//                        //获取缩略图片URL
//                        NetworkUtils.getThumbnailImageUrl(content.getCoverImageFileName()),
//                        Config.TYPE_CELL,
//                        contentListItemSmallViewHolder);
                //设置内容更新时间
//                contentListItemSmallViewHolder.timeTextView.setText(
//                        //获取 计算两日期之间相差的时间
//                        TimeUtils.getDiffTimeBetweenTwoDate(
//                                //当前时间
//                                new Date(),
//                                //将Timestamp转换为本地Date
//                                TimeUtils.Timestamp2LocateDate(content.getCreationData()))
//                                //获取格式化时间差信息
//                                .getFormatDiffTime());

                //设置监听
                contentListItemSmallViewHolder.itemCellRippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                    @Override
                    public void onComplete(RippleView rippleView) {
                        //设置CircularProgressBar为不可见并停止动画
//                        CircularProgressBarUtils.setCircularProgressBar(View.INVISIBLE, ContentFragment.circularProgressBar);
                        //启动ContentDetailActivity
                        intentToContentDetailActivity(rippleView, position, Constant.INTENT_CONTENT_DETAIL_NATIVE);
                    }
                });


//                contentListItemSmallViewHolder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //设置CircularProgressBar为不可见并停止动画
////                        ContentFragment.setLoadingDialog(View.INVISIBLE);
//                        //启动ContentDetailActivity
//                        intentToContentDetailActivity(v, position);
//                    }
//                });
                break;
        }
    }

    /**
     * 为用户Logo设置头像
     */
    private void setLogoCircleImageView(String userLogo, RecyclerView.ViewHolder viewHolder) {
//        ContentListItemBigViewHolder contentListItemBigViewHolder = (ContentListItemBigViewHolder) viewHolder;
//        //使用ImageLoader类库,根据URL联网获取图片,并设置默认预览图，及加载完成时的回调接口
//        ImageLoader.getInstance().displayImage(
//                userLogo,
//                contentListItemBigViewHolder.logoCircleImageView,
//                MyApplication.getInstance().getOptions(
//                        R.drawable.ic_account_circle_green_500_48dp),
//                new SimpleImageLoadingListener() {
//
//                    @Override
//                    public void onLoadingComplete(String imageUri,
//                                                  View view, Bitmap loadedImage) {
//                        super.onLoadingComplete(imageUri, view, loadedImage);
//                    }
//
//                });
    }

    private void intentToContentDetailActivity(View v, int position,String intentName) {

//        final Intent intent = new Intent(MainActivity.this, SubActivity.class);
//        ActivityTransitionLauncher.with(MainActivity.this).from(v).launch(intent);

        Context context = v.getContext();

        Intent intent = null;
        switch (intentName) {
            case Constant.INTENT_CONTENT_DETAIL_WEB:
                intent = new Intent(context, ContentDetailWebActivity.class);
                break;
            case Constant.INTENT_CONTENT_DETAIL_NATIVE:
                intent = new Intent(context, ContentDetailNativeActivity.class);
                break;
            default:
                break;
        }

        intent.putExtra(intentName, contents.get(position));
        context.startActivity(intent);
//
//        ActivityTransitionLauncher.with((Activity) context).from(v).launch(intent);
    }

    /**
     * 为ImageView设置图片
     *
     * @param imageUrl
     * @param itemViewType
     * @param viewHolder
     */
//    private void setImageView(String imageUrl, int itemViewType, RecyclerView.ViewHolder viewHolder) {
//
//        switch (itemViewType) {
//            case Config.TYPE_HEADER:
//                ContentListItemBigViewHolder contentListItemBigViewHolder = (ContentListItemBigViewHolder) viewHolder;
//                //使用ImageLoader类库,根据URL联网获取图片,并设置默认预览图，及加载完成时的回调接口
//                ImageLoader.getInstance().displayImage(
//                        imageUrl,
//                        contentListItemBigViewHolder.contentImageImageView,
//                        MyApplication.getInstance().getOptions(
//                                R.drawable.ic_now_wallpaper_white_48dp),
//                        new SimpleImageLoadingListener() {
//
//                            @Override
//                            public void onLoadingComplete(String imageUri,
//                                                          View view, Bitmap loadedImage) {
//                                super.onLoadingComplete(imageUri, view, loadedImage);
//                            }
//
//                        });
//                break;
//            case Config.TYPE_CELL:
//                ContentListItemSmallViewHolder contentListItemSmallViewHolder = (ContentListItemSmallViewHolder) viewHolder;
//                //同上
//                ImageLoader.getInstance().displayImage(
//                        imageUrl,
//                        contentListItemSmallViewHolder.contentImageImageView,
//                        MyApplication.getInstance().getOptions(
//                                R.drawable.profile),
//                        new SimpleImageLoadingListener() {
//
//                            @Override
//                            public void onLoadingComplete(String imageUri,
//                                                          View view, Bitmap loadedImage) {
//                                super.onLoadingComplete(imageUri, view, loadedImage);
//                            }
//
//                        });
//                break;
//        }
//    }

//    /**
//     * Header item ViewHolder
//     */
//    class ContentListItemBigViewHolder extends RecyclerView.ViewHolder {
//        private final ImageView contentImageImageView;
//        private final TextView contentTitleTextView;
//        private final TextView contentDescribeTextView;
//        private final CircleImageView logoCircleImageView;
//        private final View mView;
//        private final RippleView itemBigRippleView;
//
//        public ContentListItemBigViewHolder(View itemView) {
//            super(itemView);
//            mView = itemView;
//            itemBigRippleView = (RippleView) itemView.findViewById(R.id.list_item_big_more_rv);
//            contentImageImageView = (ImageView) itemView.findViewById(R.id.list_item_big_content_image_iv);
//            contentTitleTextView = (TextView) itemView.findViewById(R.id.list_item_big_content_title_tv);
//            contentDescribeTextView = (TextView) itemView.findViewById(R.id.list_item_big_content_describe_tv);
//            logoCircleImageView = (CircleImageView) itemView.findViewById(R.id.list_item_big_logo_civ);
//        }
//
//        @Override
//        public String toString() {
//            return super.toString();
//        }
//    }
//
    /**
     * Cell item ViewHolder
     */
    class ContentListItemSmallViewHolder extends RecyclerView.ViewHolder {
        private final ImageView contentImageImageView;
        private final TextView contentTitleTextView;
        private final TextView contentDescribeTextView;
        private final TextView timeTextView;
        private final View mView;
        private final RippleView itemCellRippleView;

        public ContentListItemSmallViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            itemCellRippleView = (RippleView) itemView.findViewById(R.id.list_item_cell_more_rv);
            contentImageImageView = (ImageView) itemView.findViewById(R.id.list_item_cell_content_image_civ);
            contentTitleTextView = (TextView) itemView.findViewById(R.id.list_item_cell_content_title_tv);
            contentDescribeTextView = (TextView) itemView.findViewById(R.id.list_item_cell_content_describe_tv);
            timeTextView = (TextView) itemView.findViewById(R.id.list_item_cell_time_tv);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }


}
