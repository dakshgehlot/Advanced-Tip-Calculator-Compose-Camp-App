package com.example.advancedtipcalculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.advancedtipcalculator.data.TipHistoryItem
import com.example.advancedtipcalculator.data.TipHistoryViewModel
import java.text.NumberFormat
import com.example.advancedtipcalculator.R

@Composable
fun TransactionHistoryPage(
    tipHistoryViewModel: TipHistoryViewModel = viewModel()
) {
    val items = tipHistoryViewModel.readAllData.observeAsState(listOf()).value
    Column {
        ColoringBelowAppBar()
        HistoryList(list = items)
    }
}

@Composable
fun HistoryList(
    list: List<TipHistoryItem>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        items(
            items = list.reversed(),
            itemContent = { HistoryListItem(listItem = it) }
        )
    }
}

@Composable
fun HistoryListItem(
    listItem: TipHistoryItem
) {
    val totalBill = NumberFormat.getCurrencyInstance().format(listItem.totalBill)
    val billPerPerson = NumberFormat.getCurrencyInstance().format(listItem.billPerPerson)
    val tipPerPerson = NumberFormat.getCurrencyInstance().format(listItem.tipPerPerson)
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(25),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .padding(vertical = 10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.transaction_green),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterVertically)
            )
            Column {
                HistoryListItemTextLabel(text = stringResource(R.string.history_total))
                Text(
                    text = totalBill,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color(0xFF0D8A53)
                )
                HistoryListItemTextLabel(text = stringResource(R.string.per_person))
            }
            Divider(
                modifier = Modifier
                    .width(2.dp)
                    .height(80.dp)
                    .background(Color.Black)
                    .align(Alignment.CenterVertically)
            )
            Column {
                HistoryListItemTextLabel(text = stringResource(R.string.history_bill))
                HistoryListItemText(text = billPerPerson)
                HistoryListItemTextLabel(text = stringResource(R.string.history_tip))
                HistoryListItemText(text = tipPerPerson)
            }
        }
    }
}

@Composable
fun HistoryListItemTextLabel(
    text: String
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        fontSize = 14.sp
    )
}

@Composable
fun HistoryListItemText(
    text: String
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color(0xFF0D8A53)
    )
}

