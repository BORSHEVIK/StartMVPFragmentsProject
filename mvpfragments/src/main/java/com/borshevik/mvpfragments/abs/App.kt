package com.borshevik.mvpfragments.abs

import android.support.multidex.MultiDexApplication
import com.borshevik.mvpfragments.model.SchoolPerson
import io.reactivex.plugins.RxJavaPlugins
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber


class App : MultiDexApplication() {

    companion object {
        lateinit var INSTANCE: App
    }

    private var schoolPersons: List<SchoolPerson>? = null
    private lateinit var debugLogTree: DebugLogTree
    private lateinit var screenRouter: Cicerone<Router>
    private lateinit var dialogRouter: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        screenRouter = Cicerone.create()
        dialogRouter = Cicerone.create()

        debugLogTree = DebugLogTree(applicationContext)
        Timber.plant(debugLogTree)
        RxJavaPlugins.setErrorHandler { throwable ->
            Timber.e(throwable, "Error handler reported")
        }

        Timber.i(DebugLogTree.LOG_MESSAGE_APPLICATION_STARTED)
    }

    fun setSchoolPersons(schoolPersons: List<SchoolPerson>) {
        this.schoolPersons = schoolPersons
    }

    fun setLogMessageListener(timberMessageListener: DebugLogTree.TimberMessageListener) {
        debugLogTree.setTimberMessageListener(timberMessageListener)
    }

    fun clearLogMessageListener() {
        debugLogTree.clearLogMessageListener()
    }

    fun getScreenNavigatorHolder(): NavigatorHolder {
        return screenRouter.navigatorHolder
    }

    fun getScreenRouter(): Router {
        return screenRouter.router
    }

    fun getDialogNavigatorHolder(): NavigatorHolder {
        return dialogRouter.navigatorHolder
    }

    fun getDialogRouter(): Router {
        return dialogRouter.router
    }
}