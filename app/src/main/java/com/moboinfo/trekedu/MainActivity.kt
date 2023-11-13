package com.moboinfo.trekedu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moboinfo.trekedu.ui.screens.ChooseYourExamScrn
import com.moboinfo.trekedu.ui.screens.HorizontalPagerWithIndicators
import com.moboinfo.trekedu.ui.screens.listGetStartedItems
import com.moboinfo.trekedu.ui.theme.TrekeduTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrekeduTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    HorizontalPagerWithIndicators(listGetStartedItems, Modifier)
                    ChooseYourExamScrn(Modifier)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrekeduTheme {

    }
}