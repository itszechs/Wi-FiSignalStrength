package zechs.wifi.signalstrength.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import zechs.wifi.signalstrength.ui.theme.WiFiSignalStrengthTheme
import zechs.wifi.signalstrength.viewmodel.MainViewModel

class ComposeActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                        mainViewModel.fetchSignalStrength().collect {
                            strengthValue = it
                        }
                    }
                    CustomComponent(indicatorValue = strengthValue)
                }
            }
        }
    }
}
