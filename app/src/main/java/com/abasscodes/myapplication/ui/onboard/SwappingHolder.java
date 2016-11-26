package com.abasscodes.myapplication.ui.onboard;

import android.animation.StateListAnimator;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by C4Q on 11/23/16.
 */
public abstract class SwappingHolder extends RecyclerView.ViewHolder {

    public SwappingHolder(View itemView) {
        super(itemView);
    }

    public abstract Drawable getSelectionModeBackgroundDrawable();
    public abstract Drawable getDefaultModeBackgroundDrawable();

    public abstract StateListAnimator getSelectionModeStateListAnimator();
    public abstract StateListAnimator getDefaultModeStateListAnimator();

    public abstract boolean isSelectable();
    public abstract boolean isActivated();
}
