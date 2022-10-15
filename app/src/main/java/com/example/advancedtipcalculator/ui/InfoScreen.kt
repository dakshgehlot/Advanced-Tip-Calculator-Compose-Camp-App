package com.example.advancedtipcalculator.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.advancedtipcalculator.R

@Composable
fun InfoScreen() {
    Column {
        ColoringBelowAppBar()
        Spacer(modifier = Modifier.height(48.dp))
        InfoContent()
    }
}

@Composable
fun InfoContent() {

    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(
                scrollState,
            )
    ) {
        Text(
            text = stringResource(R.string.advanced_tip_calculator),
            style = MaterialTheme.typography.h1,
            color = Color(0xFF08502F)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.compose_camp_logo),
            contentDescription = stringResource(R.string.compose_camp_logo)
        )
        Spacer(modifier = Modifier.height(30.dp))
        InfoContentText(text = R.string.info_content_1)
        Spacer(modifier = Modifier.height(25.dp))
        InfoContentText(
            text = R.string.info_content_2,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
    }
}

@Composable
fun InfoContentText(
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        style = MaterialTheme.typography.body1,
        color = Color(0xFF0D8A53),
        text = stringResource(text),
        textAlign = TextAlign.Justify
    )
}