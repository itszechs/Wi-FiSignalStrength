package zechs.wifi.signalstrength.repository

import android.app.Activity
import android.net.wifi.WifiManager
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import zechs.wifi.signalstrength.ThisApp.Companion.context

class MainRepository {

    fun fetchSignalStrength(): Flow<Int> = flow {
        while (true) {
            val wifiManager = context?.getSystemService(
                Activity.WIFI_SERVICE
            ) as WifiManager
            val dbm = wifiManager.connectionInfo.rssi
            val percent = 2 * (dbm + 100)
            val finalPercent =
                when {
                    percent >= 100 -> 100
                    percent <= 0 -> 0
                    else -> percent
                }
            emit(finalPercent)
            Log.d("fetchSignalFlow", "percent=$finalPercent")
            delay(500L)
        }
    }
}