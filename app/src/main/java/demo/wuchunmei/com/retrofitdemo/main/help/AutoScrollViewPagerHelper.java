package demo.wuchunmei.com.retrofitdemo.main.help;

import android.app.ActionBar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import demo.wuchunmei.com.retrofitdemo.R;
import demo.wuchunmei.com.retrofitdemo.main.adapter.InfinitePagerAdapter;
import demo.wuchunmei.com.retrofitdemo.main.util.ContextUtils;
import demo.wuchunmei.com.retrofitdemo.main.view.widget.InfiniteViewPager;

/**
 * 目前功能：水平轮播 viewpager 辅助类
 * 后续扩展成： 可以支持垂直轮播并且带有 3D 效果的轮播。
 * @author zhoujun  参考 天涯日报;  类似社区里面  AdGallery
 * @date 2016-10-26
 */

public class AutoScrollViewPagerHelper {

    private final Context mContext;
    private final RelativeLayout mViewPagerLayout;
    private InfinitePagerAdapter mAutoScrollAdapter;
    private InfiniteViewPager mViewPager;
    private TextView mTitleView;
    private RadioGroup mRadioGoup;
//    private RankAutoScrollAdapter.OnViewClickListener mLiveRankListener;
//    private LiveLoopScrollAdapter.OnViewClickListener mLivePrevListener;
    private View currentView;
    private boolean mIsFromWenda;

    public void setIsFromWenda(boolean mIsFromWenda) {
        this.mIsFromWenda = mIsFromWenda;
    }

    public AutoScrollViewPagerHelper(Context context) {
        mContext = context;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        mViewPagerLayout = (RelativeLayout) layoutInflater.inflate(R.layout.layout_infinite, null);
        mTitleView = (TextView) mViewPagerLayout.findViewById(R.id.recommand_microbbs_title);
        mRadioGoup = (RadioGroup) mViewPagerLayout.findViewById(R.id.dot_group);
        RelativeLayout.LayoutParams params =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, R.id.dot_group);
        mTitleView.setVisibility(View.GONE);
        mRadioGoup.setLayoutParams(params);
    }


//
//    public void initLiveRankViewPager(final List<Entity> list, RankAutoScrollAdapter.OnViewClickListener listener) {
//        mAutoScrollAdapter = new RankAutoScrollAdapter(mContext, list, listener);
//        mViewPager = (InfiniteViewPager) mViewPagerLayout.findViewById(R.id.viewpager);
//        mViewPager.setAdapter(mAutoScrollAdapter);
//        mViewPager.startAutoScroll();
//    }

//    public void initLivePrevViewPager(final List<Entity> list, LiveLoopScrollAdapter.OnViewClickListener listener) {
//        mAutoScrollAdapter = new LiveLoopScrollAdapter(mContext, list, listener);
//        mViewPager = (InfiniteViewPager) mViewPagerLayout.findViewById(R.id.viewpager);
//        initDots(list.size());
//        mViewPager.setSwitchListener(new OnSwitchListener() {
//            @Override
//            public void onSwitch(int position) {
////                Log.d("InfiniteViewPager", "onswitch.... position = " + position);
//                if(mRadioGoup != null && mRadioGoup.getChildAt(position) != null) {
//                    mRadioGoup.check(mRadioGoup.getChildAt(position).getId());
//                }
//
//            }
//        });
//        mViewPager.setAdapter(mAutoScrollAdapter);
//        mViewPager.startAutoScroll();
//    }

    /**
     * 热门问答轮播图
     */
//    public void initQaPrevViewPager(final List<Entity> list, QALoopScrollAdapter.OnViewClickListener listener) {
//        mAutoScrollAdapter = new QALoopScrollAdapter(mContext, list, listener);
//        RelativeLayout.LayoutParams params =new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, R.id.dot_group);
//        params.addRule(RelativeLayout.CENTER_VERTICAL, R.id.dot_group);
//        mRadioGoup.setLayoutParams(params);
//        mTitleView.setVisibility(View.VISIBLE);
//        mViewPager = (InfiniteViewPager) mViewPagerLayout.findViewById(R.id.viewpager);
//        initDots(list.size());
//        mViewPager.setSwitchListener(new OnSwitchListener() {
//            @Override
//            public void onSwitch(int position) {
//                if(mRadioGoup != null && mRadioGoup.getChildAt(position) != null) {
//                    mRadioGoup.check(mRadioGoup.getChildAt(position).getId());
//                }
//
//            }
//        });
//        mViewPager.setAdapter(mAutoScrollAdapter);
//        mViewPager.startAutoScroll();
//    }
//
//    /**
//     * 版块置顶直播展示主播轮播
//     * @param list
//     * @param listener
//     */
//    public void initLiveAnchorRankViewPager(final List<Entity> list, LiveAnchorLoopScrollAdapter.OnViewClickListener listener){
//        mAutoScrollAdapter = new LiveAnchorLoopScrollAdapter(mContext, list, listener);
//        mViewPager = (InfiniteViewPager) mViewPagerLayout.findViewById(R.id.viewpager);
//        mViewPager.setAdapter(mAutoScrollAdapter);
//        mViewPager.startAutoScroll();
//    }


   public interface OnSwitchListener {
        public void onSwitch(int position);
    }

    /**
     * 向外开放布局对象，使得可以将布局对象添加到外部的布局中去
     * */
    public RelativeLayout getLayout() {
        return mViewPagerLayout;
    }

    /**
     * 开始自动循环切换
     */
    public void startAutoSwitch() {
        // 如果已经在滚动则不需要重新开始
        mViewPager.startAutoScroll();
    }

    public void stopAutoSwitch() {
        mViewPager.stopAutoScroll();
    }

    public void refreshView() {
        if(mAutoScrollAdapter != null) {
            mAutoScrollAdapter.notifyDataSetChanged();
        }
    }

    public void refreshView(int size) {
        if(mAutoScrollAdapter != null) {
            initDots(size);
            mAutoScrollAdapter.notifyDataSetChanged();
        }
    }

    private void initDots(int size) {
        mRadioGoup.removeAllViews();
        for(int i = 0; i < size; i++) {
            RadioButton _rb = new RadioButton(mContext);
            _rb.setClickable(false);
            _rb.setId(0x1234 + i);
            if (mIsFromWenda) {
                _rb.setButtonDrawable(mContext.getResources().getDrawable(R.drawable.selector_ad_gallery_wenda_mark));
            }else{
                _rb.setButtonDrawable(mContext.getResources().getDrawable(R.drawable.selector_ad_gallery_mark));
            }
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ContextUtils.dip2px(mContext, 16),
                    ActionBar.LayoutParams.WRAP_CONTENT);
            mRadioGoup.addView(_rb, params);
            if(i==0){
                _rb.setChecked(true);
            }
        }
        mRadioGoup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        setDotVisible(true);
    }

    private void setDotVisible(boolean visible) {
        if(visible) {
            mRadioGoup.setVisibility(View.VISIBLE);
        } else {
            mRadioGoup.setVisibility(View.GONE);
        }
    }

}
