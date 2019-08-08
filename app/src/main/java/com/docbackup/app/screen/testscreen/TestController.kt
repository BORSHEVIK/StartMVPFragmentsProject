package com.docbackup.app.screen.testscreen

import android.os.Bundle
import android.view.View
import com.docbackup.app.R
import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.BaseController
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.presenter.DataHolder
import com.docbackup.app.screen.testscreen.model.TestControllerModel
import com.docbackup.app.screen.testscreen.model.TestControllerModelImpl
import com.docbackup.app.screen.testscreen.presenter.TestArguments
import com.docbackup.app.screen.testscreen.presenter.TestControllerPresenter
import com.docbackup.app.screen.testscreen.presenter.TestControllerPresenterImpl
import com.docbackup.app.screen.testscreen.view.TestControllerView
import com.docbackup.app.screen.testscreen.view.TestControllerViewHolder
import com.docbackup.app.screen.testscreen.view.TestControllerViewImpl

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