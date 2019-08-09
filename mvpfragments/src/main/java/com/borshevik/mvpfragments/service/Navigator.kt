package com.borshevik.mvpfragments.service

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.borshevik.mvpfragments.abs.App
import com.borshevik.mvpfragments.abs.HandleNavigation
import com.borshevik.mvpfragments.abs.MySupportFragmentNavigator
import com.borshevik.mvpfragments.abs.Screen
import com.borshevik.mvpfragments.abs.dialog.DialogControllerStub
import com.borshevik.mvpfragments.abs.dialog.presenter.DialogArguments
import com.borshevik.mvpfragments.abs.presenter.Arguments
import com.borshevik.mvpfragments.dialog.message.MessageDialogController
import com.borshevik.mvpfragments.screen.mainscreen.MainController
import com.borshevik.mvpfragments.screen.testscreen.TestController
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import java.io.Serializable
import java.util.*

class Navigator : Serializable {

    private val screenRouter: Router
    private val dialogRouter: Router
    private val ciceroneNavigator: Navigator
    private val ciceroneDialogNavigator: Navigator
    private val screenFragmentManager: FragmentManager
    private val dialogFragmentManager: FragmentManager

    constructor(router: Router, dialogRouter: Router, screenFragmentManager: FragmentManager, dialogFragmentManager: FragmentManager, screensContainer: Int, dialogsContainer: Int) {
        this.screenRouter = router
        this.dialogRouter = dialogRouter
        this.screenFragmentManager = screenFragmentManager
        this.dialogFragmentManager = dialogFragmentManager

        ciceroneNavigator = object : MySupportFragmentNavigator(screenFragmentManager, screensContainer) {
            override fun createFragment(screenKey: String?, data: Any?): Fragment {
                var controller: Fragment = getRootController(Bundle())
                val arguments = data as Arguments

                when (screenKey?.toInt()) {
                    Screen.SCREEN_MAIN -> {
                        controller = MainController()
                    }
                    Screen.SCREEN_TEST -> {
                        controller = TestController()
                    }
                }

                arguments.controllerTag = screenKey ?: ""
                controller?.arguments = Bundle()
                controller?.arguments?.putSerializable(Screen.SCREEN_ARGUMENTS, arguments)

                return controller
            }

            override fun exit() {
                //Not implemented
            }

            override fun showSystemMessage(message: String?) {
                //Not implemented
            }

        }

        ciceroneDialogNavigator = object : MySupportFragmentNavigator(dialogFragmentManager, dialogsContainer) {
            override fun createFragment(screenKey: String?, data: Any?): Fragment {
                val arguments = data as DialogArguments

                var controller: Fragment? = null

                when (screenKey?.toInt()) {
                    Screen.DIALOG_STUB -> {
                        controller = DialogControllerStub()
                    }
                    Screen.DIALOG_MESSAGE -> {
                        controller = MessageDialogController()
                    }
                }

                if (controller != null) {
                    arguments.controllerTag = screenKey ?: ""
                    controller?.arguments = Bundle()
                    controller?.arguments?.putSerializable(Screen.SCREEN_ARGUMENTS, arguments)
                }

                return controller!!
            }

            override fun exit() {
                //Not implemented
            }

            override fun showSystemMessage(message: String?) {
                //Not implemented
            }

        }

        router.newRootScreen(Screen.SCREEN_MAIN.toString(), Arguments())
        dialogRouter.newRootScreen(Screen.DIALOG_STUB.toString(), DialogArguments(""))
    }

    fun showScreen(screenId: Int) {
        this.showScreen(screenId, Arguments())
    }

    fun showScreen(screenId: Int, arguments: Arguments) {
        screenRouter.navigateTo(screenId.toString(), arguments)
    }

    fun showDialog(dialogId: Int, parrentTag: String) {
        this.showDialog(dialogId, DialogArguments(parrentTag))
    }

    fun showDialog(dialogId: Int, arguments: DialogArguments) {
        arguments.controllerTag = dialogId.toString()
        dialogRouter.navigateTo(dialogId.toString(), arguments)
    }

    fun closeDialogByTag(tag: String) {
        for (i in 0 until dialogFragmentManager.getBackStackEntryCount()) {
            if (tag == dialogFragmentManager.getBackStackEntryAt(i).getName()) {
                if (i > 0) {
                    dialogFragmentManager.popBackStackImmediate(dialogFragmentManager.getBackStackEntryAt(i - 1).getName(), 0)
                } else {
                    dialogFragmentManager.popBackStackImmediate()
                }
                break
            }
        }
    }

    fun getRootController(bundle: Bundle): Fragment {
        bundle.putSerializable(Screen.SCREEN_ARGUMENTS, Arguments())
        val root = MainController()
        root.setArguments(bundle)
        return root
    }

    fun getControllerByTag(controllerTag: String): Fragment {
        return getControllerByTag(controllerTag, false)
    }

    fun getControllerByTag(controllerTag: String, isDialog: Boolean): Fragment  {
        //TODO not good with !! but needed for target version api 28
        return (if (!isDialog) screenFragmentManager.findFragmentByTag(controllerTag) else dialogFragmentManager.findFragmentByTag(controllerTag))!!
    }

    private fun createTag(controller: Fragment): String {
        return controller.javaClass.simpleName + UUID.randomUUID()
    }

    fun setNavigators(screenNavigatorHolder: NavigatorHolder, dialogNavigatorHolder: NavigatorHolder) {
        screenNavigatorHolder.setNavigator(ciceroneNavigator)
        dialogNavigatorHolder.setNavigator(ciceroneDialogNavigator)
    }

    fun restoreNavigators(screenNavigatorHolder: NavigatorHolder, dialogNavigatorHolder: NavigatorHolder) {
        screenNavigatorHolder.removeNavigator()
        dialogNavigatorHolder.removeNavigator()
    }

    fun onHandleBack(): Boolean {
        var result = true

        val lastFragment = getLastFragment(screenFragmentManager)
        val lastfragmentFragmentManager = lastFragment.childFragmentManager

        if (dialogFragmentManager.backStackEntryCount > 0) {
            App.INSTANCE.getDialogRouter().exit()
        } else if ((lastFragment as HandleNavigation).hadleBack() && lastfragmentFragmentManager.backStackEntryCount > 0) {
            lastfragmentFragmentManager.popBackStackImmediate()
        } else if (screenFragmentManager.backStackEntryCount > 0) {
            App.INSTANCE.getScreenRouter().exit()
        } else {
            result = false
        }

        return result
    }

    private fun getLastFragment (fragmentManager: FragmentManager): Fragment {
        val fragments = screenFragmentManager.fragments
        return fragments.get(fragments.size - 1)
    }

}