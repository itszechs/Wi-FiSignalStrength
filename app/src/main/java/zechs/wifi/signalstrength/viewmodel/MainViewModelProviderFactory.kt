package zechs.wifi.signalstrength.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import zechs.wifi.signalstrength.repository.MainRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelProviderFactory(
    private val mainRepository: MainRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}