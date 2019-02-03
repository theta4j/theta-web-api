package org.theta4j.webapi.example

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import java.io.Closeable

class ConnectionManager(ctx: Context) : Closeable {
    companion object {
        private val TAG = "CONNECTION_MANAGER"
    }

    private val connManager = ctx.getSystemService(ConnectivityManager::class.java)!!

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            Log.d(TAG, "Bind to network " + network)
            connManager.bindProcessToNetwork(network)
        }
    }

    init {
        val req = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        connManager.registerNetworkCallback(req, networkCallback)
    }

    override fun close() {
        connManager.unregisterNetworkCallback(networkCallback)
    }
}
