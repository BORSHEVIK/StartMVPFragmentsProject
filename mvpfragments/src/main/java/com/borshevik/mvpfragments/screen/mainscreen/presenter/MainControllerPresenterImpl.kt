package com.borshevik.mvpfragments.screen.mainscreen.presenter

import android.annotation.SuppressLint
import com.borshevik.mvpfragments.abs.PAbs
import com.borshevik.mvpfragments.abs.Screen
import com.borshevik.mvpfragments.abs.presenter.Arguments
import com.borshevik.mvpfragments.abs.presenter.BasePresenterImpl
import com.borshevik.mvpfragments.dialog.message.presenter.MessageDialogArguments
import com.borshevik.mvpfragments.screen.mainscreen.model.MainControllerModel
import com.borshevik.mvpfragments.screen.mainscreen.view.MainControllerView
import com.borshevik.mvpfragments.screen.testscreen.presenter.TestArguments


class MainControllerPresenterImpl(view: MainControllerView, model: MainControllerModel, dataHolder: MainControllerDataHolder, arguments: Arguments, abs: PAbs) :
        BasePresenterImpl<MainControllerView, MainControllerModel, MainControllerDataHolder, Arguments>(view, model, dataHolder, arguments, abs), MainControllerPresenter {

    override fun okPressed() {
        abs.getToastManager().showToast("Dialog Ok Pressed")
    }

    private val URL = "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/drive&response_type=code&redirect_uri=urn:ietf:wg:oauth:2.0:oob&client_id=342986024446-mgd82vdnqp8i1ukt4o09m07oqfach8ko.apps.googleusercontent.com"

    /*
    https://accounts.google.com/o/oauth2/v2/auth?
    scope=https://www.googleapis.com/auth/drive&
    response_type=code&
    redirect_uri=https://www.docbackupapp.com&
    client_id=342986024446-09i8h8sh5n7528sp44frteharnjmp83d.apps.googleusercontent.com
     */

    override fun onResume() {
        super.onResume()

        /*
        val customTabsIntent = CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(abs.getContext().getResources()
                        .getColor(com.docbackup.app.R.color.colorPrimary))
                .setShowTitle(true)
                .build()

        customTabsIntent.launchUrl(abs.getContext(), Uri.parse(URL));
        */

        //view.setUrl(URL)


    }

    @SuppressLint("CheckResult")
    override fun onCreate() {
        super.onCreate()

        /*
        abs.getPermissionService().getGrantedPermissionObservable(PermissionsService.Permission.CAMERA)
                .subscribeOn(LocalSchedulers.networking())
                .observeOn(LocalSchedulers.mainThread())
                .subscribe({ result ->
                    Log.d("Permission", "Camera Granted")
                }, { error ->
                    Log.d("Permission", "Camera Not Granted")
                })
                */
        view.setUrl(URL)

        //abs.getNavigator().showScreen(Screen.SCREEN_TEST, TestArguments())

        abs.getNavigator().showDialog(Screen.DIALOG_MESSAGE, MessageDialogArguments(arguments.controllerTag).apply { value = "Message from Main Controller" })

        /*
        Thread(Runnable {

            Observable.just(1)
                    .doOnNext {
                        Log.d("TAG", "Current thread name 1 = " + Thread.currentThread().name)
                    }
                    .observeOn(Schedulers.io())
                    .doOnNext {
                        Log.d("TAG", "Current thread name 2 = " + Thread.currentThread().name)
                    }
                    .observeOn(Schedulers.newThread())
                    .doOnNext {
                        Log.d("TAG", "Current thread name 3 = " + Thread.currentThread().name)
                    }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.computation())
                    .doOnNext {
                        Log.d("TAG", "Current thread name 4 = " + Thread.currentThread().name)
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    //.subscribeOn(LocalSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .switchMap { Observable.just(2)
                            .doOnNext {
                                Log.d("TAG", "Current thread name 11 = " + Thread.currentThread().name)
                            }
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .doOnNext {
                                Log.d("TAG", "Current thread name 22 = " + Thread.currentThread().name)
                            }
                    }
                    .doOnNext {
                        Log.d("TAG", "Current thread name 5 = " + Thread.currentThread().name)
                    }
                    //.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnNext {
                        Log.d("TAG", "Current thread name 6 = " + Thread.currentThread().name)
                    }
                    .observeOn(Schedulers.newThread())
                    .doOnNext {
                        Log.d("TAG", "Current thread name 7 = " + Thread.currentThread().name)
                    }
                    .subscribe {
                        result ->
                        Log.d("TAG", "Current thread name 8 = " + Thread.currentThread().name + " Result = " + result)
                    }

        }).start()
        */

        /*
        var data = arrayOf("B2,E5,F6", "A1,B2,C3,D4", "D4,G7,I9", "G7,H8")

        val managers = mutableListOf<Employer>()

        for (items: String in data) {
            val separatedItems = items.split(",".toRegex()).toMutableList()
            val managerName = separatedItems[0]
            separatedItems.removeAt(0)
            var parent: Employer? = null

            val employer = Employer(parent, managerName).apply {
                for (separatedItem: String in separatedItems) {
                    employers.add(Employer(this, separatedItem))
                }
            }

            for (manager: Employer in managers) {
                manager.employers.forEach {
                    if (it.name.equals(managerName)) {
                        parent = manager
                        return@forEach
                    }
                }
            }

            managers.add(employer)
        }

        data = arrayOf("B2,E5,F6", "A1,B2,C3,D4", "D4,G7,I9", "G7,H8")
        */
    }

    private class Employer(var parent: Employer?, val name: String) {
        val employers: MutableList<Employer> = mutableListOf()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MainControllerPresenterImpl

        if (URL != other.URL) return false

        return true
    }

    override fun hashCode(): Int {
        return URL.hashCode()
    }

}