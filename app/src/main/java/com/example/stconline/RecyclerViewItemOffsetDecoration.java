package com.example.stconline;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mItemOffset;


    private RecyclerViewItemOffsetDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public RecyclerViewItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {

        this(context.getResources().getDimensionPixelSize(itemOffsetId));

    }

    @Override

    public void getItemOffsets(@NotNull Rect outRect, @NotNull View view, @NotNull RecyclerView parent,

                               @NotNull RecyclerView.State state) {

        int position = parent.getChildViewHolder(view).getAdapterPosition();
//        int itemCount = state.getItemCount();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        setSpacingForDirection(outRect, layoutManager, position);

    }

    private void setSpacingForDirection(Rect outRect, RecyclerView.LayoutManager layoutManager, int position) {
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
        int cols = gridLayoutManager.getSpanCount();
//        int rows = itemCount / cols;
        outRect.left = mItemOffset;
        outRect.right = position % cols == cols -1 ? mItemOffset : 0;
        outRect.top = 0;
        outRect.bottom = 0;
    }

}

