package com.borshevik.mvpfragments.screen.mainscreen.view

import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.webkit.ValueCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import com.borshevik.mvpfragments.abs.PresenterProvider
import com.borshevik.mvpfragments.abs.view.BaseViewImpl
import com.borshevik.mvpfragments.screen.mainscreen.presenter.MainControllerPresenter
import com.borshevik.mvpfragments.utils.CustomTabActivityHelper


class MainControllerViewImpl : BaseViewImpl<MainControllerViewHolder, MainControllerPresenter>, MainControllerView {

    constructor(myControllerViewHolder: MainControllerViewHolder, presenterProvider: PresenterProvider<MainControllerPresenter>)
            : super(myControllerViewHolder, presenterProvider) {

        viewHolder.webView.apply {
            val mCustomTabActivityHelper = CustomTabActivityHelper()


            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            //settings.allowFileAccess = false
            settings.setSupportZoom(true)
            settings.useWideViewPort = true
            settings.loadWithOverviewMode = true
            settings.userAgentString = "Internet Explorer"

            /*
                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)

                    if (url.contains("?code=") && authComplete != true) {
                        val uri = Uri.parse(url)
                        authCode = uri.getQueryParameter("code")
                        Log.i("", "CODE : " + authCode!!)
                        authComplete = true
                        resultIntent.putExtra("code", authCode)
                    } else if (url.contains("error=access_denied")) {
                        Log.i("", "ACCESS_DENIED_HERE")
                        resultIntent.putExtra("code", authCode)
                        authComplete = true
                    } else if (url.contains("")) {

                    }
                }
                */

            webViewClient = object : WebViewClient() {

                var authComplete = false
                var resultIntent = Intent()
                var authCode: String? = null
                var shouldOverride = false

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    viewHolder.webView.evaluateJavascript(
                            "(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();",
                            ValueCallback<String> { html ->
                                if (html.contains("Success code=") && authComplete !== true) {
                                    Log.d("Success code", html.substringAfterLast("Success code=").substringBefore("&amp;"))
                                } else if (html.contains("error=access_denied")) {
                                    Log.i("", "ACCESS_DENIED_HERE")
                                } /*else if (url.contains("accounts.google.com/o/oauth2/approval")) {
                                    Log.i("", "GET_CODE")
                                }*/
                                //Log.d("Success code", html.substringAfterLast("Success code=").substringBefore("&amp;"))
                            })
                }
            }
        }
    }

    override fun setUrl(url: String) {
        viewHolder.webView.loadUrl(url)
    }

}