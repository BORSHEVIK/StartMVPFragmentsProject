package com.docbackup.app.abs

import com.docbackup.app.abs.presenter.BasePresenter

interface PresenterProvider<P : BasePresenter> {

    fun getPresenter(): P

}