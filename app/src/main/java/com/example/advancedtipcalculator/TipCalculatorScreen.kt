package com.example.advancedtipcalculator

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.advancedtipcalculator.data.TipHistoryItem
import com.example.advancedtipcalculator.data.TipHistoryViewModel
import com.example.advancedtipcalculator.ui.*

enum class TipCalculatorScreen(@StringRes val screenTitle: Int) {
    MainCalculator(screenTitle = (R.string.tip_calculator)),
    TransactionDetails(screenTitle = R.string.detailsPage),
    TransactionHistory(screenTitle = R.string.historyPage),
    InfoScreen(screenTitle = R.string.info)
}


@Composable
fun TipAppBar(
    currentScreen: TipCalculatorScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onInfoButtonTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = Color(0xFFCEF5E6),
        elevation = 0.dp,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = Color(0xFF08502F)
                    )
                }
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                    contentDescription = null,
                    tint = Color(0xFFCEF5E6)
                )
            }
            Text(
                stringResource(currentScreen.screenTitle),
                style = MaterialTheme.typography.h1,
                color = Color(0xFF08502F),
                modifier = modifier
                    .wrapContentWidth()
            )
            if (currentScreen != TipCalculatorScreen.InfoScreen) {
                IconButton(onClick = { onInfoButtonTap() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.info),
                        contentDescription = stringResource(R.string.about_us),
                        tint = Color(0xFF08502F),
                        modifier = modifier
                            .size(40.dp)
                    )
                }
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                    contentDescription = null,
                    tint = Color(0xFFCEF5E6)
                )
            }
        }
    }
}


@Composable
fun AdvancedTipCalculator(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    DatabaseTipViewModel: TipHistoryViewModel
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = TipCalculatorScreen.valueOf(
        backStackEntry?.destination?.route ?: TipCalculatorScreen.MainCalculator.name
    )

    Scaffold(
        topBar = {
            TipAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onInfoButtonTap = { navController.navigate(TipCalculatorScreen.InfoScreen.name) }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = TipCalculatorScreen.MainCalculator.name,
            modifier = modifier
                .padding(innerPadding)
        ) {
            composable(route = TipCalculatorScreen.MainCalculator.name) {
                MainPage(
                    viewModel = viewModel,
                    onNextButtonClicked = {
                        viewModel.setFinalQuantities()
                        if (
                            viewModel.uiState.value.inputBill.toDoubleOrNull() != 0.0 &&
                            viewModel.uiState.value.inputBill.toDoubleOrNull() != null
                        ) {
                            val transactionDetails = TipHistoryItem(
                                0,
                                viewModel.uiState.value.finalTotalBill,
                                viewModel.uiState.value.finalOnlyBill,
                                viewModel.uiState.value.finalOnlyTip
                            )
                            DatabaseTipViewModel.addItem(transactionDetails)
                        }
                        navController.navigate(TipCalculatorScreen.TransactionDetails.name)
                    },
                    onHistoryButtonClicked = { navController.navigate(TipCalculatorScreen.TransactionHistory.name) }
                )
            }
            composable(route = TipCalculatorScreen.TransactionDetails.name) {
                TransactionDetails(
                    viewModel = viewModel,
                    onHistoryButtonClicked = { navController.navigate(TipCalculatorScreen.TransactionHistory.name) }
                )
            }
            composable(route = TipCalculatorScreen.TransactionHistory.name) {
                TransactionHistoryPage()
            }
            composable(route = TipCalculatorScreen.InfoScreen.name) {
                InfoScreen()
            }
        }
    }
}



