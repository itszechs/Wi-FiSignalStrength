package zechs.wifi.signalstrength.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import zechs.wifi.signalstrength.repository.MainRepository
import zechs.wifi.signalstrength.ui.theme.WiFiSignalStrengthTheme
import zechs.wifi.signalstrength.viewmodel.MainViewModel
import zechs.wifi.signalstrength.viewmodel.MainViewModelProviderFactory


@InternalCoroutinesApi
class ComposeActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainRepository = MainRepository()
        val viewModelProviderFactory = MainViewModelProviderFactory(mainRepository)
        mainViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        setContent {
            WiFiSignalStrengthTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var strengthValue by remember { mutableStateOf(0) }

                    lifecycleScope.launch {
                        mainViewModel.getSignal().collect {
                            strengthValue = it
                        }
                    }

                    CustomComponent(indicatorValue = strengthValue)
                }
            }
        }
    }
}
