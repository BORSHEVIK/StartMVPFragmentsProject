package com.borshevik.mvpfragments.screen.testscreen.view

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.View
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.view.ViewHolder

class TestControllerViewHolder : ViewHolder {

    val searchView: SearchView
    val recyclerView: RecyclerView
    val progressView: View

    constructor(view: View) : super(view) {
        searchView = view.findViewById(R.id.search_view)
        recyclerView = view.findViewById(R.id.recycler_view)
        progressView = view.findViewById(R.id.progressbar)
    }

}