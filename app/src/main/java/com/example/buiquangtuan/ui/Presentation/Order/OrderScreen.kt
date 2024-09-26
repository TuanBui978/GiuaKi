package com.example.buiquangtuan.ui.Presentation.Order

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buiquangtuan.R
import com.example.buiquangtuan.data.localdatasource.entity.DataBaseEntity


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun OrderScreen(viewModel: OrderViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = OrderViewModel.Factory)) {
    var cream by remember {
        mutableStateOf(false)
    }
    var chocolate by remember {
        mutableStateOf(false)
    }
    var quantity by remember {
        mutableIntStateOf(0)
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.coffe_img), contentDescription = null)
        Text(text = "Choose topping: ", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
        Row(modifier = Modifier
            .padding(10.dp)
            .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp)), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Cream", modifier = Modifier.padding(start = 10.dp))
            Checkbox(checked = cream, onCheckedChange = {cream = it})
            Text(text = "Chocolate")
            Checkbox(checked = chocolate, onCheckedChange = {chocolate = it})
        }
        Text(text = "Quantity: ", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
        Row(modifier = Modifier
            .padding(10.dp)
            .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp)), verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { quantity-- }, shape = RoundedCornerShape(8.dp), modifier = Modifier
                .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                .size(36.dp), contentPadding = PaddingValues(0.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
            Text(text = quantity.toString(), fontSize = 18.sp)
            Button(onClick = { quantity++ }, shape = RoundedCornerShape(8.dp), modifier = Modifier
                .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                .size(36.dp), contentPadding = PaddingValues(0.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
        Button(onClick = {
            var price = 4f

            if (chocolate) {
                price += 1f
            }
            if (cream) {
                price += 0.5f
            }

            price *= quantity.toFloat()

            val dataBaseEntity = DataBaseEntity(cream = cream, chocolate = chocolate, quantity = quantity, price = price, status = "Đang chế biến")
            viewModel.onOrderClick(dataBaseEntity)
                         }, shape = RoundedCornerShape(8.dp)) {
            Text(text = "ORDER")
        }
        Text(text = "ORDER SUMMARY", modifier = Modifier.padding(20.dp), fontWeight = FontWeight.SemiBold)
        Column(modifier = Modifier.border(BorderStroke(1.dp, Color.Black))) {
            var price = 4f
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

            if (chocolate) {
                price += 1f
            }
            if (cream) {
                price += 0.5f
            }

            price *= quantity.toFloat()



            Text(text = "Add whipped cream? $hasCream", Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp))
            Text(text = "Add chocolate? $hasChocolate", Modifier.padding(start = 10.dp, end = 10.dp))
            Text(text = "Quantity: $quantity", Modifier.padding(start = 10.dp, end = 10.dp))
            Text(text = "Price: $price", Modifier.padding(start = 10.dp, end = 10.dp, top = 20.dp))
            Text(text = "THANK YOU!", Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp))

        }
    }


}