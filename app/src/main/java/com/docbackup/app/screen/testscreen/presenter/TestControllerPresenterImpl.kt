package com.docbackup.app.screen.testscreen.presenter

import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.model.BaseModel
import com.docbackup.app.abs.presenter.BasePresenter
import com.docbackup.app.abs.presenter.BasePresenterImpl
import com.docbackup.app.abs.presenter.DataHolder
import com.docbackup.app.screen.testscreen.view.TestControllerView

class TestControllerPresenterImpl(view: TestControllerView, model: BaseModel, dataHolder: DataHolder, arguments: TestArguments, abs: PAbs)
    : BasePresenterImpl<TestControllerView, BaseModel, DataHolder, TestArguments>(view, model, dataHolder, arguments, abs), BasePresenter {

    override fun onResume() {
        super.onResume();

        view.updateMeessage(arguments.value.toString());
    }
}