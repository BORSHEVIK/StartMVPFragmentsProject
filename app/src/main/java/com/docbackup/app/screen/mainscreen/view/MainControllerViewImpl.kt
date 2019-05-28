package com.docbackup.app.screen.mainscreen.view

import com.docbackup.app.abs.PresenterProvider
import com.docbackup.app.abs.view.BaseViewImpl
import com.docbackup.app.screen.mainscreen.presenter.MainControllerPresenter

class MainControllerViewImpl : BaseViewImpl<MainControllerViewHolder, MainControllerPresenter>, MainControllerView {

    constructor(myControllerViewHolder: MainControllerViewHolder, presenterProvider: PresenterProvider<MainControllerPresenter>)
            : super(myControllerViewHolder, presenterProvider) {
        viewHolder.updateButton.setOnClickListener { presenterProvider.getPresenter().updateButtonClick() }
        viewHolder.nextButton.setOnClickListener { presenterProvider.getPresenter().nextButtonClick() }
        viewHolder.showDialog.setOnClickListener { presenterProvider.getPresenter().dialogButtonClick() }
    }

    override fun updateMeessage(message: String) {
        viewHolder.messageTextView.setText(message);
    }

}