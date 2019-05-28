package com.docbackup.app.screen.mainscreen.presenter

import com.docbackup.app.abs.Abs
import com.docbackup.app.abs.LocalSchedulers
import com.docbackup.app.abs.PAbs
import com.docbackup.app.abs.Screen
import com.docbackup.app.abs.presenter.Arguments
import com.docbackup.app.abs.presenter.BasePresenterImpl
import com.docbackup.app.screen.mainscreen.model.MainControllerModel
import com.docbackup.app.screen.mainscreen.view.MainControllerView
import com.docbackup.app.screen.testscreen.presenter.TestArguments
import com.docbackup.app.service.PermissionsService
import java.util.*

class MainControllerPresenterImpl(view: MainControllerView, model: MainControllerModel, dataHolder: MainControllerDataHolder, arguments: Arguments, abs: PAbs) :
        BasePresenterImpl<MainControllerView, MainControllerModel, MainControllerDataHolder, Arguments>(view, model, dataHolder, arguments, abs), MainControllerPresenter {

    private val MESSAGE = "Message form presenter and randome int value = ";

    override fun onResume() {
        super.onResume();
        view.updateMeessage(MESSAGE + dataHolder.value);
    }

    override fun updateButtonClick() {
        dataHolder.value = (0 .. 1000000).random();
        view.updateMeessage(MESSAGE + dataHolder.value);
    }

    override fun nextButtonClick() {
        val testArguments = TestArguments();
        testArguments.value = dataHolder.value;
        abs.getNavigator().showScreen(Screen.SCREEN_TEST, testArguments);
    }

    override fun dialogButtonClick() {
        /*
        val dialogArguments = MessageDialogArguments(arguments.controllerTag);
        dialogArguments.title = "Title";
        dialogArguments.value = "Hi, this is dialog message from arguments"
        abs.getNavigator().showDialog(Screen.DIALOG_MESSAGE, dialogArguments);
        */
        abs.getPermissionService().getGrantedPermissionObservable(PermissionsService.Permission.CAMERA)
                .observeOn(LocalSchedulers.mainThread())
                .subscribeOn(LocalSchedulers.networking())
                .subscribe(
                        { result ->  abs.getToastManager().showToast("Access granted") },
                        { error -> abs.getToastManager().showToast("Access denied")}
                )
    }

    override fun okPressed() {
        abs.getToastManager().showToast("okPressed()");
    }

    private fun ClosedRange<Int>.random() =
            Random().nextInt((endInclusive + 1) - start) +  start;

}