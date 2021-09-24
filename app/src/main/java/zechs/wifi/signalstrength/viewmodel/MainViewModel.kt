package zechs.wifi.signalstrength.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import zechs.wifi.signalstrength.repository.MainRepository

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getSignal(): Flow<Int> {
        return mainRepository.fetchSignalStrength()
    }

}