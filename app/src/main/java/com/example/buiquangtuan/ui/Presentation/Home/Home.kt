package com.example.buiquangtuan.ui.Presentation.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Home(homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)) {
    val list = homeViewModel.list.observeAsState()
    homeViewModel.getAllOrder()
    LazyColumn() {
        list.value?.forEach {
            order->
            item {
                Item(cream = order.cream, chocolate = order.chocolate, price = order.price, status = order.status, quantity = order.quantity)
            }
        }
    }
}
@Composable
fun Item(cream: Boolean, chocolate: Boolean, quantity: Int, price: Float, status: String) {
    Column(modifier = Modifier.padding(5.dp).fillMaxWidth().border(BorderStroke(1.dp, Color.Black))) {
        val hasCream = if (cream) {
            "yes"
        }
        else {
            "no"
        }
        val hasChocolate = if (chocolate) {
            "yes"
        }
        else {
            "no"
        }
        Text(text = "Add whipped cream? $hasCream", Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp))
        Text(text = "Add chocolate? $hasChocolate", Modifier.padding(start = 10.dp, end = 10.dp))
        Text(text = "Quantity: $quantity", Modifier.padding(start = 10.dp, end = 10.dp))
        Text(text = "Price: $price", Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp))
        Text(text = "Status: $status", Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp))

    }
}