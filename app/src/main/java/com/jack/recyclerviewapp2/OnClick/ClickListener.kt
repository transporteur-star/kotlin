package com.jack.recyclerviewapp2.OnClick

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface ClickListener {

    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: ClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener{
        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.bindingAdapterPosition, view)
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }
    })
}
