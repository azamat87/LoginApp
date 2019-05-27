package azamat.kz.loginapp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText


/**
 * Created by Asus on 26.05.2019.
 */
class CustomEditText : EditText {

    private var dRight: Drawable? = null
    private lateinit var clickListener: OnDrawableClickListener

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {

    }

    override fun setCompoundDrawables(left: Drawable?, top: Drawable?, right: Drawable?, bottom: Drawable?) {
        if (right != null) {
            dRight = right
        }
        super.setCompoundDrawables(left, top, right, bottom)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP && dRight != null) {
            val x = event.x
            val y = event.y
            val bounds = dRight?.bounds
            if (x >= (right - bounds!!.width()) && x <= (right - paddingRight) && y >= paddingTop && y <= (height - paddingBottom)) {
                clickListener.onDrawableClick()
            }
        }
        return super.onTouchEvent(event)
    }

    fun setOnDrawableClickListener(listener: OnDrawableClickListener){
        clickListener = listener
    }

    interface OnDrawableClickListener {
        fun onDrawableClick()
    }
}