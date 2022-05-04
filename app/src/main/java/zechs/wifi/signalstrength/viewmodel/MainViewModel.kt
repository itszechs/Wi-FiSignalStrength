package zechs.wifi.signalstrength.viewmodel

import android.app.Activity
import android.app.Application
import android.net.wifi.WifiManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val context = getApplication<Application>()
    private val wifiManager = context.getSystemService(
        Activity.WIFI_SERVICE
    ) as WifiManager

    fun fetchSignalStrength(): Flow<Int> = flow {
        while (true) {
            val dbm = wifiManager.connectionInfo.rssi
            val percent = 2 * (dbm + 100)
            val finalPercent = when {
                percent >= 100 -> 100
                percent <= 0 -> 0
                else -> percent
            }
            emit(finalPercent)
            Log.d("fetchSignalFlow", "percent=$finalPercent")
            delay(250L)
        }
    }
}