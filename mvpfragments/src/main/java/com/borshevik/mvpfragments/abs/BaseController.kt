package com.borshevik.mvpfragments.abs

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.borshevik.mvpfragments.abs.dialog.BaseDialogEventListener
import com.borshevik.mvpfragments.abs.dialog.DialogEventListenerStub
import com.borshevik.mvpfragments.abs.dialog.DialogEventProvider
import com.borshevik.mvpfragments.abs.model.BaseModel
import com.borshevik.mvpfragments.abs.presenter.Arguments
import com.borshevik.mvpfragments.abs.presenter.BasePresenter
import com.borshevik.mvpfragments.abs.presenter.DataHolder
import com.borshevik.mvpfragments.abs.view.BaseView
import com.borshevik.mvpfragments.abs.view.ViewHolder
import com.borshevik.mvpfragments.activity.MainActivity
import java.io.Serializable

abstract class BaseController<H: ViewHolder, V: BaseView, M: BaseModel, D: DataHolder, P: BasePresenter, A: Arguments> : Fragment(),
        PresenterProvider<P>, DialogEventProvider, HandleNavigation {

    private val BUNDLE_DATA_HOLDER = "BUNDLE_DATA_HOLDER"

    private var viewHolder: H? = null
    private var view: V? = null
    private var presenter: P? = null
    private var model: M? = null
    private var dataHolder: D? = null
    internal var arguments: A? = null
    internal lateinit var abs: PAbs

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(getViewLayoutId(), container, false)

        val args = getArguments()

        val serializeble: Serializable? = args?.getSerializable(BUNDLE_DATA_HOLDER)

        if (dataHolder == null) {
            if (serializeble == null) {
                this.dataHolder = createDataHolder()
            } else {
                this.dataHolder = args.getSerializable(BUNDLE_DATA_HOLDER) as D
            }
        }

        (activity as MainActivity).showToolBar(showToolBar())

        this.abs = activity as PAbs

        this.arguments = args?.getSerializable(Screen.SCREEN_ARGUMENTS) as A

        this.viewHolder = createViewHolder(view)
        this.view = createView(this.viewHolder!!)
        this.model = createModel(this.abs)
        this.presenter = createPresenter(this.view!!, this.model!!, this.dataHolder!!, this.arguments!!, abs)

        this.presenter!!.onCreate()

        return view
    }

    override fun onResume() {
        super.onResume()
        this.presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        this.presenter?.onPause()
    }

    override fun onDestroyView() {
        presenter?.onDestroy()

        this.viewHolder = null
        this.view = null
        this.presenter = null
        this.model = null
        this.arguments = null

        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        getArguments()?.putSerializable(BUNDLE_DATA_HOLDER, dataHolder)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getPresenter().onActivityResult(requestCode, resultCode, data)
    }

    abstract fun createDataHolder(): D
    abstract fun createViewHolder(view: View): H
    abstract fun createView(viewHolder: H): V
    abstract fun createPresenter(view: V, model: M, dataHolder: D, arguments: A, abs: PAbs): P
    abstract fun createModel(abs: Abs): M
    abstract fun getViewLayoutId(): Int

    override fun getPresenter(): P {
        return this.presenter!!
    }

    override fun provideEvent(): BaseDialogEventListener {
        return DialogEventListenerStub()
    }

    fun showToolBar(): Boolean {
        return true
    }

    override fun hadleBack(): Boolean {
        return false
    }
}