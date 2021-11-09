package com.eyepetizer.android.logic.network

import okhttp3.Call
import okhttp3.EventListener
import java.io.IOException
import java.net.InetAddress


/**
 * @Description
 * @Author lux
 * @Date 2021/11/5 6:17 下午
 * @Version
 */
class OkhttpEventListener : EventListener() {

    override fun dnsStart(call: Call, domainName: String) {
        super.dnsStart(call, domainName)
    }

    override fun dnsEnd(call: Call, domainName: String, inetAddressList: MutableList<InetAddress>) {
        super.dnsEnd(call, domainName, inetAddressList)
    }

    override fun responseBodyStart(call: Call) {
        super.responseBodyStart(call)
    }

    override fun responseBodyEnd(call: Call, byteCount: Long) {
        super.responseBodyEnd(call, byteCount)
    }

    override fun callStart(call: Call) {
        super.callStart(call)
    }

    override fun callEnd(call: Call) {
        super.callEnd(call)
    }

    override fun callFailed(call: Call, ioe: IOException) {
        super.callFailed(call, ioe)
    }

    companion object {
        val FACTORY = Factory { OkhttpEventListener() }
    }
}