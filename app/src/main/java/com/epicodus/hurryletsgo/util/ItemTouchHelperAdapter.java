package com.epicodus.hurryletsgo.util;

/**
 * Created by Guest on 11/9/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
