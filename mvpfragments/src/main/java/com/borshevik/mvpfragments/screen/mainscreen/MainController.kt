package com.borshevik.mvpfragments.screen.mainscreen

import android.view.View
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.Abs
import com.borshevik.mvpfragments.abs.BaseController
import com.borshevik.mvpfragments.abs.PAbs
import com.borshevik.mvpfragments.abs.dialog.BaseDialogEventListener
import com.borshevik.mvpfragments.abs.presenter.Arguments
import com.borshevik.mvpfragments.screen.mainscreen.model.MainControllerModel
import com.borshevik.mvpfragments.screen.mainscreen.model.MainControllerModelImpl
import com.borshevik.mvpfragments.screen.mainscreen.presenter.MainControllerDataHolder
import com.borshevik.mvpfragments.screen.mainscreen.presenter.MainControllerPresenter
import com.borshevik.mvpfragments.screen.mainscreen.presenter.MainControllerPresenterImpl
import com.borshevik.mvpfragments.screen.mainscreen.view.MainControllerView
import com.borshevik.mvpfragments.screen.mainscreen.view.MainControllerViewHolder
import com.borshevik.mvpfragments.screen.mainscreen.view.MainControllerViewImpl

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
