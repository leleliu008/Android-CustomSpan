package com.fpliu.newton.ui.span.sample

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import android.view.View
import com.fpliu.newton.ui.base.BaseActivity
import com.fpliu.newton.ui.base.BaseUIConfig
import com.fpliu.newton.ui.span.ClickableSpanEx
import com.fpliu.newton.ui.span.CustomLinkMovementMethod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        BaseUIConfig.setHeadHeight(resources.getDimension(R.dimen.dp750_96).toInt())

        super.onCreate(savedInstanceState)

        title = "CustomSpan使用示例"

        addViewInBody(R.layout.activity_main)

        textView.run {
            //1、first step
            movementMethod = CustomLinkMovementMethod

            text = SpannableString("我是测试文字---我可以点击也可以长按").apply {
                //我是测试文字，设置为红色
                setSpan(ForegroundColorSpan(Color.RED), 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

                //2、second step
                //我可以点击也可以长按
                setSpan(object : ClickableSpanEx() {
                    override fun updateDrawState(textPaint: TextPaint) {
                        super.updateDrawState(textPaint)
                        textPaint.color = Color.BLUE
                    }

                    override fun onClick(view: View) {
                        showToast("我可以点击也可以长按   onClick")
                    }

                    override fun onLongClick(widget: View) {
                        showToast("我可以点击也可以长按   onLongClick")
                    }
                }, 9, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            }
        }
    }

}