package com.borshevik.mvpfragments.abs

import android.support.v4.app.FragmentManager
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.*

abstract class MySupportFragmentNavigator(val fragmentManager: FragmentManager, val containerId: Int) : SupportFragmentNavigator(fragmentManager, containerId) {

    override fun applyCommand(command: Command?) {
        if (command is Forward) {
            fragmentManager
                    .beginTransaction()
                    .replace(containerId, createFragment(command.screenKey, command.transitionData), command.screenKey)
                    .addToBackStack(command.screenKey)
                    .commit()
        } else if (command is Back) {
            if (fragmentManager.backStackEntryCount > 0) {
                fragmentManager.popBackStackImmediate()
            } else {
                exit()
            }
        } else if (command is Replace) {
            if (fragmentManager.backStackEntryCount > 0) {
                fragmentManager.popBackStackImmediate()
                fragmentManager
                        .beginTransaction()
                        .replace(containerId, createFragment(command.screenKey, command.transitionData), command.screenKey)
                        .addToBackStack(command.screenKey)
                        .commit()
            } else {
                fragmentManager
                        .beginTransaction()
                        .replace(containerId, createFragment(command.screenKey, command.transitionData), command.screenKey)
                        .commit()
            }
        } else if (command is BackTo) {
            val key = command.screenKey

            if (key == null) {
                super.applyCommand(command)
            } else {
                var hasScreen = false
                for (i in 0 until fragmentManager.backStackEntryCount) {
                    if (key == fragmentManager.getBackStackEntryAt(i).name) {
                        fragmentManager.popBackStackImmediate(key, 0)
                        hasScreen = true
                        break
                    }
                }
                if (!hasScreen) {
                    backToUnexisting()
                }
            }
        } else if (command is SystemMessage) {
            showSystemMessage(command.message)
        }
    }

}