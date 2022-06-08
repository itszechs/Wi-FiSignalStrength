package zechs.wifi.signalstrength.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import zechs.wifi.signalstrength.ui.component.CustomComponent
import zechs.wifi.signalstrength.ui.theme.WiFiSignalStrengthTheme
import zechs.wifi.signalstrength.viewmodel.MainViewModel

class ComposeActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainScreen(mainViewModel) }
    }
}

@Composable
private fun MainScreen(mainViewModel: MainViewModel) {

    val strengthValue by mainViewModel
        .fetchSignalStrength()
        .collectAsState(initial = 0)

    WiFiSignalStrengthTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomComponent(indicatorValue = strengthValue)
        }
    }
}
