package com.khavronsky.appbar2test;


import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.khavronsky.appbar2test.InnerCollapsedCard.IMAGE_NAME;
import static com.khavronsky.appbar2test.InnerCollapsedCard.SUB_TITLE_NAME;
import static com.khavronsky.appbar2test.InnerCollapsedCard.TITLE_NAME;
import static com.khavronsky.appbar2test.InnerCollapsedCard.VALUE_LAYOUT_NAME;

public class CollapsedCardBehavior extends CoordinatorLayout.Behavior<InnerCollapsedCard> {

    private static int sToolbarHeight;

    private final float ALPHA_COEFFICIENT = 0.5f;

    private float alpha;

    private boolean mFirstSet = true;

    private Context mContext;

    private int mPrimaryY;

    private float mStartLayoutY;

    private float mStartLayoutX;

    private float mStartTitleLayoutY;

    private float mStartTitleLayoutX;

    private float mStartImageViewY;

    private ICollapsedListener mListener;

    public CollapsedCardBehavior() {
    }

    public CollapsedCardBehavior(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public boolean layoutDependsOn(final CoordinatorLayout parent, final InnerCollapsedCard child,
            final View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(final CoordinatorLayout parent, final InnerCollapsedCard child,
            final View dependency) {
        setAlpha(dependency);
        setToolbarHeight();

        child.setY((float) (sToolbarHeight));
        child.setAlpha((alpha));
        //сохраняем координаты элементов в полностью раскрытом состоянии
        if (mFirstSet) {
            startPosition(child);
        }
        //изменение размеров элементов
        setScale(child.getTitleLayout());
        setScale(child.getImageView());
        setScale(child.getDescriptionLayout());
        //анимация движения элементов
        child.getTitleLayout().setY((mStartTitleLayoutY + dependency.getY() * 0.45f));
        child.getTitleLayout().setX((mStartTitleLayoutX - horizontalCorrection(child.getTitleLayout())));
        if (dependency.getY() < -450.0f) {
            child.getImageView().setY(mStartImageViewY + (dependency.getY() + 450.0f));
            child.getDescriptionLayout().setY((mStartLayoutY + dependency.getY() * 0.8f));
        } else {
            child.getImageView().setY(mStartImageViewY);
            child.getDescriptionLayout().setY(((mStartLayoutY - 360.0f) + dependency.getY() + 450.0f));
        }
        child.getDescriptionLayout().setX((mStartLayoutX - horizontalCorrection(child.getDescriptionLayout())));
        //скрытие элементов
        hideView(child, TITLE_NAME);
        hideView(child, SUB_TITLE_NAME);
        hideView(child, IMAGE_NAME);

        return true;
    }

    private void setScale(final View v) {
        v.setScaleY(alpha);
        v.setScaleX(alpha);
    }

    private int getYCoordinate(final View v) {
        int[] coordinates = {0, 0};
        v.getLocationOnScreen(coordinates);
        return coordinates[1];
    }

    private void setToolbarHeight() {
        sToolbarHeight = (int) (mContext.getResources()
                .getDimension(R.dimen.action_bar_size));
    }

    private void setAlpha(final View dependency) {
        float maxForAlpha = dependency.getHeight() - sToolbarHeight;
        alpha = (1 - Math.abs(dependency.getY()) / maxForAlpha * ALPHA_COEFFICIENT);
    }

    private void hideView(InnerCollapsedCard customLayout, String tag) {
        View v;
        int visibility = VISIBLE;
        switch (tag) {
            case TITLE_NAME:
                v = customLayout.getTitle();
                if (mPrimaryY > getYCoordinate(v) - (v.getY() * (1 - alpha))) {
                    visibility = INVISIBLE;
                }
                v.setVisibility(visibility);
                mListener.setTitleVisibility(visibility);
                break;
            case SUB_TITLE_NAME:
                v = customLayout.getSubTitle();
                if (mPrimaryY > getYCoordinate(v) - (v.getY() * (1 - alpha))) {
                    visibility = INVISIBLE;
                }
                v.setVisibility(visibility);
                mListener.setSubTitleVisibility(visibility);
                break;
            case IMAGE_NAME:
                v = customLayout.getImageView();
                if (mPrimaryY > getYCoordinate(v) - (v.getHeight() / 2 - v.getHeight() / 2 * alpha)) {
                    visibility = INVISIBLE;
                }
                v.setVisibility(visibility);
                customLayout.getDescriptionLayout().setVisibility(visibility);
                break;
            case VALUE_LAYOUT_NAME:
                break;
        }
    }

    private float horizontalCorrection(View v) {
        return (v.getWidth() - v.getWidth() * alpha) / 2;
    }

    private void startPosition(final InnerCollapsedCard child) {

        mStartLayoutY = child.getDescriptionLayout().getY();
        mStartLayoutX = child.getDescriptionLayout().getX();
        mStartTitleLayoutY = child.getTitleLayout().getY();
        mStartTitleLayoutX = child.getTitleLayout().getX();
        mStartImageViewY = child.getImageView().getY();
        //верхняя граница CollapsedCard, при контакте с которой элементы скрываются
        mPrimaryY = getYCoordinate(child);

        mFirstSet = false;
    }

    void setICollapsedListener(ICollapsedListener listener) {
        this.mListener = listener;
    }

    public interface ICollapsedListener {

        void setTitleVisibility(int visibility);

        void setSubTitleVisibility(int visibility);
    }
}

