package com.example.advancedtipcalculator.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.advancedtipcalculator.R


@Composable
fun MainPage(
    viewModel: CalculatorViewModel,
    onNextButtonClicked: () -> Unit,
    onHistoryButtonClicked: () -> Unit
) {
    ColoringBelowAppBar()
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColoringBelowAppBar()
        Spacer(modifier = Modifier.height(48.dp))
        BillTipSplit(viewModel)
        Spacer(modifier = Modifier.height(50.dp))
        NextButton(
            label = stringResource(R.string.calculate),
            onNextButtonClicked = onNextButtonClicked,
            bgColor = Color(0xFF0D8A53),
            contentColor = Color.White
        )
        Spacer(modifier = Modifier.height(25.dp))
        NextButton(
            label = stringResource(R.string.history),
            onNextButtonClicked = onHistoryButtonClicked,
            bgColor = Color.White,
            contentColor = Color(0xFF0D8A53)
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun LabelText(label: String) {
    Text(
        fontSize = 18.sp,
        text = label,
        color = Color.Gray,
        fontWeight = FontWeight.ExtraBold,
    )
}

@Composable
fun BillTipSplit(
    viewModel: CalculatorViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(start = 60.dp, end = 60.dp)
    ) {
        LabelText(label = stringResource(R.string.enter_total_bill))
        BillInputField(
            value = uiState.inputBill,
            onValueChange = { viewModel.onUserValueChanged(it) })
        Spacer(modifier = Modifier.height(48.dp))
        LabelText(label = stringResource(R.string.choose_tip))
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TipButtons(
                label = stringResource(R.string.button_label_10_percent),
                uiState.tipPercent,
                value = 10
            ) { viewModel.onTipChange(10) }
            TipButtons(
                label = stringResource(R.string.button_label_15_percent),
                uiState.tipPercent,
                value = 15
            ) { viewModel.onTipChange(15) }
            TipButtons(
                label = stringResource(R.string.button_label_20_percent),
                uiState.tipPercent,
                value = 20
            ) { viewModel.onTipChange(20) }
        }
        Spacer(modifier = Modifier.height(64.dp))
        LabelText(label = stringResource(R.string.split))
        SplitBetweenPersons(
            splitValue = uiState.split,
            onSplitIncrease = { viewModel.onSplitIncrease() },
            onSplitDecrease = { viewModel.onSplitDecrease() }
        )
    }
}

@Composable
fun BillInputField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF08502F),
                cursorColor = Color(0xFF08502F),
            ),
            textStyle = TextStyle(
                fontSize = 36.sp,
                color = Color(0xFF0D8A53)
            ),
        )
    }
}

@Composable
fun TipButtons(
    label: String,
    tipPercent: Int,
    value: Int,
    onButtonClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (tipPercent == value) Color(0xFF0D8A53) else Color.White
        ),
        onClick = { onButtonClick() },
        shape = RoundedCornerShape(50),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .width(75.dp)
            .height(50.dp)
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (tipPercent == value) Color.White else Color(0xFF0D8A53)
        )
    }
}

@Composable
fun SplitBetweenPersons(
    splitValue: Int,
    onSplitIncrease: () -> Unit,
    onSplitDecrease: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                if (splitValue != 0) {
                    onSplitDecrease()
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                contentDescription = null,
                tint = Color(0xFF0D8A53)
            )
        }
        Text(
            text = splitValue.toString(),
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D8A53),
            fontSize = 36.sp
        )
        IconButton(
            onClick = { onSplitIncrease() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = null,
                tint = Color(0xFF0D8A53)
            )
        }
    }
}

@Composable
fun NextButton(
    label: String,
    onNextButtonClicked: () -> Unit,
    bgColor: Color,
    contentColor: Color
) {
    Button(
        onClick = { onNextButtonClicked() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = bgColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(50),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 6.dp,
        ),
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
