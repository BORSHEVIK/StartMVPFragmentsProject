package com.borshevik.mvpfragments.menu

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.borshevik.mvpfragments.R

class Menu(val context: Context, val rootView: ViewGroup) {

    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    fun setMenu(menuItems: List<MenuItem>, menuCallback: MenuCallback) {
        rootView.removeAllViews()

        menuItems.forEach {
            val view = layoutInflater.inflate(R.layout.menu_item, rootView,false) as AppCompatImageView
            val itemId = it.itemID
            view.setOnClickListener { menuCallback.menuItemClick(itemId) }
            view.setImageResource(it.imageResID)

            rootView.addView(view)
        }

    }

}