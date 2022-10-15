package com.example.advancedtipcalculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import com.example.advancedtipcalculator.R

@Composable
fun TransactionDetails(
    viewModel: CalculatorViewModel,
    onHistoryButtonClicked: () -> Unit
) {
    Column {
        ColoringBelowAppBar()
        FinalAmount(viewModel)
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { onHistoryButtonClicked() },
            backgroundColor = Color.White,
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 25.dp, end = 25.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.clock),
                tint = Color(0xFF08502F),
                contentDescription = stringResource(R.string.history_button),
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun BillField(
    text: String
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        color = Color(0xFF0D8A53)
    )
}

@Composable
fun FinalAmount(
    viewModel: CalculatorViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val finalBillInCurrency = NumberFormat.getCurrencyInstance().format(uiState.finalTotalBill)
    val billInCurrency = NumberFormat.getCurrencyInstance().format(uiState.finalOnlyBill)
    val tipInCurrency = NumberFormat.getCurrencyInstance().format(uiState.finalOnlyTip)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 50.dp)
            .fillMaxSize()
            .background(Color(0xFFCEF5E6), RoundedCornerShape(25.dp))
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.total_per_person),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 24.dp)
            )
            Text(
                text = finalBillInCurrency,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                color = Color(0xFF0D8A53)
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LabelText(label = stringResource(R.string.bill))
                BillField(text = billInCurrency)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LabelText(label = stringResource(R.string.tip))
                BillField(text = tipInCurrency)
            }
        }
    }
}

