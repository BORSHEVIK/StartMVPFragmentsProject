package com.docbackup.app.screen.testscreen.view

import com.docbackup.app.abs.PresenterProvider
import com.docbackup.app.abs.presenter.BasePresenter
import com.docbackup.app.abs.view.BaseViewImpl

class TestControllerViewImpl : BaseViewImpl<TestControllerViewHolder, BasePresenter>, TestControllerView  {

    constructor(myControllerViewHolder: TestControllerViewHolder, presenterProvider: PresenterProvider<BasePresenter>)
            : super(myControllerViewHolder, presenterProvider) {
    }

    override fun updateMeessage(message: String) {
        viewHolder.messageTextView.setText("This is second screen with shared value: " + message);
    }

}