package com.fpliu.newton.ui.span

import android.text.Selection
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.TextView

object CustomLinkMovementMethod : LinkMovementMethod() {

    private var clickableSpanEx: ClickableSpanEx? = null

    private var lastDownTime: Long = 0

    //把LinkMovementMethod中的onTouchEvent方法copy过来，进行修改，区分点击事件和长按事件
    override fun onTouchEvent(widget: TextView, buffer: Spannable, event: MotionEvent): Boolean {
        val action = event.action

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
            var x = event.x.toInt()
            var y = event.y.toInt()

            x -= widget.totalPaddingLeft
            y -= widget.totalPaddingTop

            x += widget.scrollX
            y += widget.scrollY

            val layout = widget.layout
            val line = layout.getLineForVertical(y)
            val off = layout.getOffsetForHorizontal(line, x.toFloat())
            val links = buffer.getSpans(off, off, ClickableSpanEx::class.java)

            if (links.size != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    if (links[0] === clickableSpanEx && System.currentTimeMillis() - lastDownTime > ViewConfiguration.getLongPressTimeout()) {
                        links[0].onLongClick(widget)
                    } else {
                        links[0].onClick(widget)
                    }
                    clickableSpanEx = null
                    lastDownTime = 0
                    Selection.removeSelection(buffer)
                } else if (action == MotionEvent.ACTION_DOWN) {
                    Selection.setSelection(buffer,
                        buffer.getSpanStart(links[0]),
                        buffer.getSpanEnd(links[0]))
                    clickableSpanEx = links[0]
                    lastDownTime = System.currentTimeMillis()
                }
                return true
            } else {
                Selection.removeSelection(buffer)
            }
        }
        return super.onTouchEvent(widget, buffer, event)
    }
}