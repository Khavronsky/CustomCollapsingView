package com.khavronsky.appbar2test;


import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

@CoordinatorLayout.DefaultBehavior(CollapsedCardBehavior.class)
public class InnerCollapsedCard extends FrameLayout {

    public static final String TITLE_NAME = "title";
    public static final String SUB_TITLE_NAME = "subTitle";
    public static final String IMAGE_NAME = "image";
    public static final String VALUE_LAYOUT_NAME = "valueLayout";

    private TextView mTitle;

    private TextView mSubTitle;

    private TextView mValue;

    private TextView mUnit;

    private TextView mExtraDescription;

    private ImageView mImageView;

    private View mDescriptionLayout;

    private View mTitleLayout;

    private View mAlertView;

    private View mValueLayout;

    public InnerCollapsedCard(@NonNull final Context context) {
        super(context);
        init();
    }

    public InnerCollapsedCard(@NonNull final Context context,
            @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InnerCollapsedCard(@NonNull final Context context, @Nullable final AttributeSet attrs,
            @AttrRes final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.inner_collapsed_card_layout, this);
        mTitle = (TextView) findViewById(R.id.collapsed_card_title);
        mSubTitle = (TextView) findViewById(R.id.collapsed_card_sub_title);
        mValue = (TextView) findViewById(R.id.collapsed_card_value);
        mUnit = (TextView) findViewById(R.id.collapsed_card_unit);
        mExtraDescription = (TextView) findViewById(R.id.collapsed_card_extra_description);
        mImageView = (ImageView) findViewById(R.id.collapsed_card_thumbs);
        mDescriptionLayout = findViewById(R.id.collapsed_card_description_layout);
        mTitleLayout = findViewById(R.id.collapsed_card_title_layout);
        mAlertView = findViewById(R.id.collapsed_card_alert_view);
        mValueLayout = findViewById(R.id.collapsed_card_value_layout);

        mTitle.setTag(TITLE_NAME);
        mSubTitle.setTag(SUB_TITLE_NAME);
        mImageView.setTag(IMAGE_NAME);
        mDescriptionLayout.setTag(VALUE_LAYOUT_NAME);
    }

    public InnerCollapsedCard setTextToTitle(String title, String subTitle){
        mTitle.setText(title);
        mSubTitle.setText(subTitle);
        return this;
    }
    public InnerCollapsedCard setTextValue (String value, String unit){
        mValue.setText(value);
        mUnit.setText(unit);
        return this;
    }

    public InnerCollapsedCard setTextDescription(String extraDescription){
        mExtraDescription.setText(extraDescription);
        return this;
    }

    public InnerCollapsedCard setImageID(int id){
        mImageView.setImageResource(id);
        return this;
    }

    public View getAlertView() {
        return mAlertView;
    }

    public View getValueLayout() {
        return mValueLayout;
    }

    public View getTitleLayout() {
        return mTitleLayout;
    }

    public View getDescriptionLayout() {
        return mDescriptionLayout;
    }

    public TextView getTitle() {
        return mTitle;
    }

    public TextView getSubTitle() {
        return mSubTitle;
    }

    public TextView getValue() {
        return mValue;
    }

    public TextView getUnit() {
        return mUnit;
    }

    public TextView getExtraDescription() {
        return mExtraDescription;
    }

    public ImageView getImageView() {
        return mImageView;
    }

}