package com.docbackup.app.abs.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.docbackup.app.R
import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.BaseController
import com.docbackup.app.abs.dialog.presenter.BaseDialogPresenter
import com.docbackup.app.abs.dialog.presenter.DialogArguments
import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.abs.model.BaseModelImpl
import com.docbackup.app.abs.presenter.DataHolder
import com.docbackup.app.abs.view.BaseView
import com.docbackup.app.abs.view.ViewHolder

open abstract class BaseDialogController<H: ViewHolder, V: BaseView, D: DataHolder, P: BaseDialogPresenter<L>, A: DialogArguments, L: BaseDialogEventListener> (args: Bundle?) :
        BaseController<H, V, BaseModel, D, P, A> (args), DialogStatusListener {

    override var cancellable: Boolean = true;

    override fun createModel(abs: Abs): BaseModel {
        return BaseModelImpl(this, abs);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.dialog_container, container, false);
        view.setOnClickListener{ if(cancellable){
            abs.getNavigator().closeCurrentDialog(this.javaClass.simpleName);
        }}
        //This line is necessary so that the clicks are not detected on the screen under the dialog.
        view.findViewById<View>(R.id.dialogView).setOnClickListener {}
        val containerGroup: ViewGroup = view.findViewById(R.id.container);
        containerGroup.addView(super.onCreateView(inflater, container))

        val titleText = arguments!!.title;
        if (!titleText.isEmpty()) {
            val title: TextView = view.findViewById(R.id.title)
            title.visibility = View.VISIBLE;
            title.text = titleText;
        }

        getPresenter().attachEventListener(abs.getControllerEventListnerByTag(arguments!!.parrentTag!!) as L);

        return view;
    }

}