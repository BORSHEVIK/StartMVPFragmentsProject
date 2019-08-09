package com.borshevik.mvpfragments.abs.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.Abs
import com.borshevik.mvpfragments.abs.BaseController
import com.borshevik.mvpfragments.abs.dialog.presenter.BaseDialogPresenter
import com.borshevik.mvpfragments.abs.dialog.presenter.DialogArguments
import com.borshevik.mvpfragments.abs.model.BaseModel
import com.borshevik.mvpfragments.abs.model.BaseModelImpl
import com.borshevik.mvpfragments.abs.presenter.DataHolder
import com.borshevik.mvpfragments.abs.view.BaseView
import com.borshevik.mvpfragments.abs.view.ViewHolder

open abstract class BaseDialogController<H: ViewHolder, V: BaseView, D: DataHolder, P: BaseDialogPresenter<L>, A: DialogArguments, L: BaseDialogEventListener> :
        BaseController<H, V, BaseModel, D, P, A> () {

    override fun createModel(abs: Abs): BaseModel {
        return BaseModelImpl(this, abs)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dialog_root_container, container, false)
        val dialogView = view.findViewById<View>(R.id.dialogView)
        //This line is necessary so that the clicks are not detected on the screen under the dialog.
        dialogView.setOnClickListener {}

        if (fullSizeView()) {
            dialogView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        } else {
            dialogView.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
            dialogView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }

        val padding = view.context.resources.getDimensionPixelSize(dialogViewPaddingResource())
        view.setPadding(padding, padding, padding, padding)

        val containerGroup: ViewGroup = view.findViewById(R.id.container)
        containerGroup.addView(super.onCreateView(inflater, container, savedInstanceState))

        val cancelable = arguments?.cancelable.let { true }
        view.setOnClickListener{ if(cancelable){
            abs.getNavigator().closeDialogByTag(arguments!!.controllerTag)
        }}

        val titleText = arguments!!.title
        if (!titleText.isEmpty()) {
            val title: TextView = view.findViewById(R.id.title)
            title.visibility = View.VISIBLE
            title.text = titleText
        }

        getPresenter().attachEventListener(abs.getControllerEventListnerByTag(arguments!!.parrentTag!!) as L)

        return view
    }

    open fun fullSizeView(): Boolean {
        return false
    }

    open fun dialogViewPaddingResource(): Int {
        return R.dimen.base_dialog_padding
    }


}