package com.docbackup.app.screen.mainscreen

import android.os.Bundle
import android.view.View
import com.docbackup.app.R
import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.BaseController
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.dialog.BaseDialogEventListener
import com.docbackup.app.abs.presenter.Arguments
import com.docbackup.app.screen.mainscreen.model.MainControllerModel
import com.docbackup.app.screen.mainscreen.model.MainControllerModelImpl
import com.docbackup.app.screen.mainscreen.presenter.MainControllerDataHolder
import com.docbackup.app.screen.mainscreen.presenter.MainControllerPresenter
import com.docbackup.app.screen.mainscreen.presenter.MainControllerPresenterImpl
import com.docbackup.app.screen.mainscreen.view.MainControllerView
import com.docbackup.app.screen.mainscreen.view.MainControllerViewHolder
import com.docbackup.app.screen.mainscreen.view.MainControllerViewImpl

class MainController : BaseController<MainControllerViewHolder, MainControllerView, MainControllerModel, MainControllerDataHolder, MainControllerPresenter, Arguments>() {

    override fun getViewLayoutId(): Int {
        return R.layout.main_controller
    }

    override fun createViewHolder(view: View): MainControllerViewHolder {
        return MainControllerViewHolder(view)
    }

    override fun createDataHolder(): MainControllerDataHolder {
        return MainControllerDataHolder()
    }

    override fun createView(viewHolder: MainControllerViewHolder): MainControllerView {
        return MainControllerViewImpl(viewHolder, this)
    }

    override fun createModel(abs: Abs): MainControllerModel {
        return MainControllerModelImpl(this, abs)
    }

    override fun createPresenter(view: MainControllerView, model: MainControllerModel, dataHolder: MainControllerDataHolder, arguments: Arguments, abs: PAbs): MainControllerPresenter {
        return MainControllerPresenterImpl(view, model, dataHolder, arguments, abs)
    }

    override fun provideEvent(): BaseDialogEventListener {
        return getPresenter()
    }

}
