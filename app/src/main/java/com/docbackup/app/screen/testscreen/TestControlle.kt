package com.docbackup.app.screen.testscreen

import android.os.Bundle
import android.view.View
import com.docbackup.app.R
import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.BaseController
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.abs.model.BaseModelImpl
import com.docbackup.app.abs.presenter.BasePresenter
import com.docbackup.app.abs.presenter.DataHolder
import com.docbackup.app.screen.testscreen.presenter.TestArguments
import com.docbackup.app.screen.testscreen.presenter.TestControllerPresenterImpl
import com.docbackup.app.screen.testscreen.view.TestControllerView
import com.docbackup.app.screen.testscreen.view.TestControllerViewHolder
import com.docbackup.app.screen.testscreen.view.TestControllerViewImpl

class TestControlle(args: Bundle?) : BaseController<TestControllerViewHolder, TestControllerView, BaseModel, DataHolder, BasePresenter, TestArguments>(args) {

    override fun createDataHolder(): DataHolder {
        return DataHolder()
    }

    override fun createViewHolder(view: View): TestControllerViewHolder {
        return TestControllerViewHolder(view)
    }

    override fun createView(viewHolder: TestControllerViewHolder): TestControllerView {
        return TestControllerViewImpl(viewHolder, this)
    }

    override fun createPresenter(view: TestControllerView, model: BaseModel, dataHolder: DataHolder, arguments: TestArguments, abs: PAbs): BasePresenter {
        return TestControllerPresenterImpl(view, model, dataHolder, arguments, abs)
    }

    override fun createModel(abs: Abs): BaseModel {
        return BaseModelImpl(this, abs)
    }

    override fun getViewLayoutId(): Int {
        return R.layout.test_controller
    }
}