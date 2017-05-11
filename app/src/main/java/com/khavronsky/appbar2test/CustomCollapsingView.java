package com.khavronsky.appbar2test;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;


public class CustomCollapsingView extends CoordinatorLayout implements CollapsedCardBehavior.ICollapsedListener {

    private static final int ALPHA_ANIMATIONS_DURATION = 300;

    private Toolbar mToolbar;
    private InnerCollapsedCard mInnerCollapsedCard;
    private NestedScrollView mInnerScroll;

    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private int mTitleVisible;
    private int mSubTitleVisible;
    private boolean mShowAlertView;

    private String mTextTitle;
    private String mTextSubtitle;
    private String mTextValue;
    private String mTextUnit;
    private String mTextExtraDescription;
    private int mImageID;

    boolean canAdd;

    public CustomCollapsingView(final Context context) {
        super(context);
        init();
    }

    public CustomCollapsingView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCollapsingView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.custom_collapsing_view_layout, this);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.collapsing_view_app_bar);
        CollapsingToolbarLayout collapsingLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_view_toolbar_layout);
        mToolbar = (Toolbar) findViewById(R.id.collapsing_view_toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.collapsing_view_toolbar_title);
        mToolbarSubTitle = (TextView) findViewById(R.id.collapsing_view_toolbar_sub_title);
        mInnerCollapsedCard = (InnerCollapsedCard) findViewById(R.id.collapsed_card);
        ((CollapsedCardBehavior) ((CoordinatorLayout.LayoutParams) mInnerCollapsedCard.getLayoutParams())
                .getBehavior()).setICollapsedListener(this);
        mInnerScroll = (NestedScrollView) findViewById(R.id.inner_nested_scroll);
        canAdd = true;
    }

    @Override
    public void setTitleVisibility(final int visibility) {
        if (mTitleVisible != visibility) {
            mTitleVisible = visibility;
            mToolbarTitle.setText(mTextTitle);
            startAlphaAnimation(mToolbarTitle, ALPHA_ANIMATIONS_DURATION, visibility);
        }
    }

    @Override
    public void setSubTitleVisibility(final int visibility) {
        if (mSubTitleVisible != visibility) {
            mSubTitleVisible = visibility;
            mToolbarSubTitle.setText(mTextSubtitle);
            startAlphaAnimation(mToolbarSubTitle, ALPHA_ANIMATIONS_DURATION, visibility);
        }
    }

    private void setTextToCustomView() {

        mInnerCollapsedCard.setTextToTitle(mTextTitle, mTextSubtitle)
                .setTextValue(mTextValue, mTextUnit)
                .setTextDescription(mTextExtraDescription);
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = !(visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    void applyChanges() {
        setTextToCustomView();
        mInnerCollapsedCard.setImageID(mImageID);
        if (mShowAlertView) {
            mInnerCollapsedCard.getAlertView().setVisibility(VISIBLE);
            mInnerCollapsedCard.getValueLayout().setVisibility(GONE);
        }

    }

    public CustomCollapsingView showAlertView(boolean show){
        mShowAlertView = show;
        return this;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public int getImageID() {
        return mImageID;
    }

    public CustomCollapsingView setImageID(final int imageID) {
        this.mImageID = imageID;
        return this;
    }

    public String getTextTitle() {
        return mTextTitle;
    }

    public CustomCollapsingView setTextTitle(final String textTitle) {
        mTextTitle = textTitle;
        return this;
    }

    public String getTextSubtitle() {
        return mTextSubtitle;
    }

    public CustomCollapsingView setTextSubtitle(final String textSubtitle) {
        mTextSubtitle = textSubtitle;
        return this;
    }

    public String getTextValue() {
        return mTextValue;
    }

    public CustomCollapsingView setTextValue(final String textValue) {
        mTextValue = textValue;
        return this;
    }

    public String getTextUnit() {
        return mTextUnit;
    }

    public CustomCollapsingView setTextUnit(final String textUnit) {
        mTextUnit = textUnit;
        return this;
    }

    public String getTextExtraDescription() {
        return mTextExtraDescription;
    }

    public CustomCollapsingView setTextExtraDescription(final String textExtraDescription) {
        mTextExtraDescription = textExtraDescription;
        return this;
    }

    @Override
    public void addView(final View child) {
        if (canAdd) {
            mInnerScroll.addView(child);
        } else {
            super.addView(child);
        }
    }

    @Override
    public void addView(final View child, final int index) {
        if (canAdd) {
            mInnerScroll.addView(child, index);
        } else {
            super.addView(child, index);
        }
    }

    @Override
    public void addView(final View child, final int width, final int height) {
        if (canAdd) {
            mInnerScroll.addView(child, width, height);
        } else {
            super.addView(child, width, height);
        }
    }

    @Override
    public void addView(final View child, final ViewGroup.LayoutParams params) {
        if (canAdd) {
            mInnerScroll.addView(child, params);
        } else {
            super.addView(child, params);
        }
    }

    @Override
    public void addView(final View child, final int index, final ViewGroup.LayoutParams params) {
        if (canAdd) {
            mInnerScroll.addView(child, index, params);
        } else {
            super.addView(child, index, params);
        }
    }
}
