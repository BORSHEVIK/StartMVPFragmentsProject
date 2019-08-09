package com.borshevik.mvpfragments.screen.testscreen

import android.view.View
import com.borshevik.mvpfragments.R
import com.borshevik.mvpfragments.abs.Abs
import com.borshevik.mvpfragments.abs.BaseController
import com.borshevik.mvpfragments.abs.PAbs
import com.borshevik.mvpfragments.abs.presenter.DataHolder
import com.borshevik.mvpfragments.screen.testscreen.model.TestControllerModel
import com.borshevik.mvpfragments.screen.testscreen.model.TestControllerModelImpl
import com.borshevik.mvpfragments.screen.testscreen.presenter.TestArguments
import com.borshevik.mvpfragments.screen.testscreen.presenter.TestControllerPresenter
import com.borshevik.mvpfragments.screen.testscreen.presenter.TestControllerPresenterImpl
import com.borshevik.mvpfragments.screen.testscreen.view.TestControllerView
import com.borshevik.mvpfragments.screen.testscreen.view.TestControllerViewHolder
import com.borshevik.mvpfragments.screen.testscreen.view.TestControllerViewImpl

class TestController() : BaseController<TestControllerViewHolder, TestControllerView, TestControllerModel, DataHolder, TestControllerPresenter, TestArguments>() {

    override fun createDataHolder(): DataHolder {
        return DataHolder()
    }

    override fun createViewHolder(view: View): TestControllerViewHolder {
        return TestControllerViewHolder(view)
    }

    override fun createView(viewHolder: TestControllerViewHolder): TestControllerView {
        return TestControllerViewImpl(viewHolder, this)
    }

    override fun createPresenter(view: TestControllerView, model: TestControllerModel, dataHolder: DataHolder, arguments: TestArguments, abs: PAbs): TestControllerPresenter {
        return TestControllerPresenterImpl(view, model, dataHolder, arguments, abs)
    }

    override fun createModel(abs: Abs): TestControllerModel {
        return TestControllerModelImpl(this, abs)
    }

    override fun getViewLayoutId(): Int {
        return R.layout.test_controller
    }
}