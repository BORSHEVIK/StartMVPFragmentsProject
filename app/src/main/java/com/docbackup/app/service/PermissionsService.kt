package com.docbackup.app.service

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import com.docbackup.app.R
import com.docbackup.app.abs.LocalSchedulers
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap

class PermissionsService(private val activity: AppCompatActivity, private val toastManager: ToastManager) {

    companion object {
        private val TAG = PermissionsService.javaClass.simpleName
        const val PERMISSION_STATUS_GRANTED = 1
    }

    private var listener: PermissionsCallbacks? = null

    private val context: Context

    private val rxPermission: RxPermissions

    private val currentPermissionSubscrubers = ConcurrentHashMap<Permission, ObservableEmitter<Int>>()

    init {
        this.context = activity
        rxPermission = RxPermissions(activity)
    }

    enum class Permission constructor(val mask: Int, @param:StringRes @field:StringRes
    val requestText: Int, val key: String) {
        CAMERA(1, R.string.camera, Manifest.permission.CAMERA),
        WRITE_STORAGE(2, R.string.write_storage, Manifest.permission.WRITE_EXTERNAL_STORAGE),
        RECORD_AUDIO(3, R.string.record_audio, Manifest.permission.RECORD_AUDIO);
    }

    fun getGrantedPermissionObservable(permission: Permission): Observable<Int> {
        return Observable.create { subscriber ->
            currentPermissionSubscrubers.put(permission, subscriber)
            checkPermission(permission)
        }
    }

    @SuppressLint("CheckResult")
    private fun checkPermission(permission: Permission) {
        activity.runOnUiThread {
            rxPermission.requestEach(permission.key)
                    .subscribe { permis ->
                        when {
                            permis.granted -> {
                                currentPermissionSubscrubers.get(permission)?.onNext(PERMISSION_STATUS_GRANTED)
                                currentPermissionSubscrubers.get(permission)?.onComplete()
                                currentPermissionSubscrubers.remove(permission)
                            }
                            permis.shouldShowRequestPermissionRationale -> {
                                listener!!.onPermissionPending(permission)
                                listener!!.onPermissionShowExplanation(context.getString(permission.requestText))
                                permis.name
                            }
                            else -> {
                                toastManager.showToast("Access denied")
                                currentPermissionSubscrubers[permission]?.onError(Throwable("Access denied"))
                                Timber.e("Could not access for %s", permission.name)
                            }
                        }
                    }
        }
    }

    fun requestPermission(permission: Permission) {
        checkPermission(permission)
    }

    fun setListener(callbacks: PermissionsCallbacks) {
        listener = callbacks
    }

    fun removeListener(callbacks: PermissionsCallbacks) {
        if (listener === callbacks) {
            listener = null
        }
    }

    interface PermissionsCallbacks {

        fun onPermissionRequest(keys: Array<String>, requestCode: Int)

        fun onPermissionPending(permission: Permission)

        fun onPermissionShowExplanation(explanation: String?): Boolean
    }
}
