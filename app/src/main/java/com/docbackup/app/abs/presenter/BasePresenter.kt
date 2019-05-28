package com.docbackup.app.abs.presenter

import android.content.Intent
import com.docbackup.app.abs.AndroidLifeCycle

interface BasePresenter : AndroidLifeCycle {

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

}