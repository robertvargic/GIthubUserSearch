package com.robertvargic.githubusersearch.ui.custom

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper.*
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_CANCEL
import android.view.MotionEvent.ACTION_UP
import android.view.View



class SwipeController : Callback() {

    private var swipeBack: Boolean = true
    private var buttonShowedState = ButtonState.GONE
    private val buttonWidth = 300

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        return makeMovementFlags(0, LEFT or RIGHT)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {

    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = false
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (actionState == ACTION_STATE_SWIPE) {
            setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//        drawButtons(c!!, viewHolder!!)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView!!.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                swipeBack = event!!.action.equals(ACTION_CANCEL) or event.action.equals(ACTION_UP)
                if (swipeBack) {
                    if (dX < -buttonWidth) buttonShowedState = ButtonState.RIGHT_VISIBLE
                    else if (dX > buttonWidth) buttonShowedState = ButtonState.LEFT_VISIBLE
                }

                if (buttonShowedState != ButtonState.GONE) {
                    setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                    setItemsClickable(recyclerView, false)
                }
                return false
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchDownListener(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView!!.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                if (event!!.action.equals(ACTION_UP)) {
                    onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    recyclerView.setOnTouchListener(object : View.OnTouchListener {
                        override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                            return false
                        }
                    })
//                    setItemsClickable(recyclerView, true)
                    swipeBack = false
                    buttonShowedState = ButtonState.GONE

                }
                return false
            }
        })
    }

    private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {
        val buttonWidthWithoutPadding = buttonWidth - 20
        val corners = 16f

        val itemView = viewHolder.itemView
        val p = Paint()

        val leftButton = RectF(itemView.left.toFloat(), itemView.top.toFloat(), (itemView.left + buttonWidthWithoutPadding).toFloat(), itemView.bottom.toFloat())
        p.setColor(Color.BLUE)
        c.drawRoundRect(leftButton, corners, corners, p)
        drawText("EDIT", c, leftButton, p)

        val rightButton = RectF((itemView.right - buttonWidthWithoutPadding).toFloat(), itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
        p.setColor(Color.RED)
        c.drawRoundRect(rightButton, corners, corners, p)
        drawText("DELETE", c, rightButton, p)

        var buttonInstance: RectF
        if (buttonShowedState === ButtonState.LEFT_VISIBLE) {
            buttonInstance = leftButton
        } else if (buttonShowedState === ButtonState.RIGHT_VISIBLE) {
            buttonInstance = rightButton
        }
    }

    private fun drawText(text: String, c: Canvas, button: RectF, p: Paint) {
        val textSize = 60f
        p.setColor(Color.WHITE)
        p.setAntiAlias(true)
        p.setTextSize(textSize)

        val textWidth = p.measureText(text)
        c.drawText(text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2, p)
    }
}

enum class ButtonState {
    GONE,
    LEFT_VISIBLE,
    RIGHT_VISIBLE
}


//    fun setItemsClickable(recyclerView: RecyclerView, isClickable: Boolean) {
//        val size = recyclerView.childCount
//        for (i in size) {
//
//            recyclerView.getChildAt(i).isClickable(isClickable)
//        }
//    }




