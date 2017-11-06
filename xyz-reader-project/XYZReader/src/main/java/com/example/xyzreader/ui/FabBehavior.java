/**
 * Created by Jun Xian for Udacity Android Developer Nanodegree project 4
 * Date: Sep 2, 2017
 * Reference:
 *      http://saulmm.github.io/mastering-coordinator
 *      https://medium.com/google-developers/intercepting-everything-with-coordinatorlayout-behaviors-8c6adc140c26
 *      https://stackoverflow.com/questions/41807601/onnestedscroll-called-only-once
 *      https://stackoverflow.com/questions/41153619/floating-action-button-not-visible-on-scrolling-after-updating-google-support/41386278#41386278
 *
 */

package com.example.xyzreader.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class FabBehavior extends FloatingActionButton.Behavior {
    private Context mContext;

    /**
     * Default constructor for instantiating a FancyBehavior in code.
     */
    public FabBehavior() {
        super();
    }

    /**
     * Default constructor for inflating a FabBehavior from layout.
     *
     * @param context The {@link Context}.
     * @param attrs The {@link AttributeSet}.
     */
    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        // Extract any custom attributes out
        // preferably prefixed with behavior_ to denote they
        // belong to a behavior
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, FloatingActionButton child,
                                       View directTargetChild, View dependency,
                                       int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        boolean val = nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(parent, child, directTargetChild,
                dependency, nestedScrollAxes);
        return val;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout parent, FloatingActionButton child,
                               View dependency, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(parent, child, dependency, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed);
        if (dyConsumed != 0 && child.getVisibility() == View.VISIBLE) {
            child.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onShown(FloatingActionButton fab) {
                    super.onShown(fab);
                }

                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onHidden(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            });
        } else {
            child.show();
        }
    }

    @Override
    public void onStopNestedScroll (CoordinatorLayout parent, FloatingActionButton child,
                                    View dependency) {
        super.onStopNestedScroll(parent, child, dependency);
        if (child.getVisibility() != View.VISIBLE) {
            child.show();
        }
    }
}





