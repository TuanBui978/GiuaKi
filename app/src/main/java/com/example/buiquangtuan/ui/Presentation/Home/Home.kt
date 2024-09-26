package com.example.buiquangtuan.ui.Presentation.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Home(homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory), onFloatClick: ()->Unit = {}) {
    val list = homeViewModel.list.observeAsState()
    var show by remember {
        mutableStateOf(false)
    }
    var searchText by remember {
        mutableStateOf("")
    }
    var id = -1
    LaunchedEffect(key1 = true) {
        homeViewModel.getAllOrder()
    }
    Box(modifier = Modifier.fillMaxSize()) {

        Column() {
            OutlinedTextField(value = searchText, onValueChange =
            {
                searchText = it
                homeViewModel.getByPrice(searchText)
            }, modifier = Modifier.fillMaxWidth())
            LazyColumn(contentPadding = PaddingValues(bottom = 30.dp)) {
                list.value?.forEach {
                    order->
                    item {
                        Item(cream = order.cream, chocolate = order.chocolate, price = order.price, status = order.status, quantity = order.quantity, onDeleteClick = {
                            homeViewModel.deleteEntity(order.toDataBaseEntity())
                        },
                            onEditClick = {
                                id = order.id!!
                                show = true
                            }
                        )
                    }
                }
            }
            if (show) {
                Dialog(onDismissRequest = { show = false }) {
                    Card {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            var text by remember {
                                mutableStateOf("")
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("status")
                                OutlinedTextField(value = text, onValueChange = {text = it})

                            }
                            Button(onClick = {
                                homeViewModel.updateEntity(text, id)
                                show = false
                            }) {
                                Text("OK")
                            }
                        }
                    }
                }
            }
        }
        FloatingActionButton(onClick = onFloatClick, modifier = Modifier.align(Alignment.BottomEnd) ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }

}
@Composable
fun Item(cream: Boolean, chocolate: Boolean, quantity: Int, price: Float, status: String, onDeleteClick: ()->Unit, onEditClick: ()->Unit = {}) {
    Column(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        .border(BorderStroke(1.dp, Color.Black))) {
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
        Row(modifier = Modifier.align(alignment = Alignment.End)) {
            Button(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
            }
            Button(onClick = {onEditClick()}) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
        }

    }
}