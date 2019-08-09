package com.borshevik.mvpfragments.abs

import com.borshevik.mvpfragments.abs.presenter.BasePresenter

interface PresenterProvider<P : BasePresenter> {

    fun getPresenter(): P

}