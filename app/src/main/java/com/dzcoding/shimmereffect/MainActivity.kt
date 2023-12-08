package com.dzcoding.shimmereffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dzcoding.shimmereffect.ui.theme.ShimmerEffectTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShimmerEffectTheme {
                var isLoading by remember{
                    mutableStateOf(true)
                }
                LaunchedEffect(key1 = true){
                    delay(2000)
                    isLoading = false
                }
                // A surface container using the 'background' color from the theme
           LazyColumn (modifier = Modifier.fillMaxSize()){
               items(20){
                   ShimmerListItem(
                       isLoading = isLoading,
                       isContentAfterLoading = {
                           Row (modifier = Modifier.fillMaxWidth().padding(16.dp)){
                               Icon(imageVector = Icons.Default.Home,
                                   contentDescription = null,
                                   modifier = Modifier.size(100.dp))
                               Text("This is Shimmer Effect Tutorial for Jetpack compose.",
                                   modifier = Modifier.padding(start = 10.dp))
                           }
                       },
                       modifier = Modifier.fillMaxWidth().padding(16.dp)
                   )
               }
           }
            }
        }
    }
}

