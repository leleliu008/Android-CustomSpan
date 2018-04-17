package com.fpliu.newton.ui.span

import android.text.style.ClickableSpan
import android.view.View

abstract class ClickableSpanEx : ClickableSpan() {
    /**
     * 长按事件回掉
     */
    abstract fun onLongClick(widget: View)
}
