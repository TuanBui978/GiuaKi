package com.example.buiquangtuan.ui.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buiquangtuan.ui.Presentation.Home.Home
import com.example.buiquangtuan.ui.Presentation.Order.OrderScreen
import com.example.buiquangtuan.ui.theme.BuiquangtuanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var show by remember {
        mutableStateOf(true)
    }
    BuiquangtuanTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                if (show)
                {
                    FloatingActionButton(onClick = { navController.navigate("Order") }, ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                }
            }
        ) {

            NavHost(navController = navController, startDestination = "Home", modifier =  Modifier.padding(it)) {
                composable("Home") {
                    Home()
                }
                composable("Order") {
                    OrderScreen()
                    show = false
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BuiquangtuanTheme {

    }
}