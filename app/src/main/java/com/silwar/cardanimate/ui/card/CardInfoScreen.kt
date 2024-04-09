package com.silwar.cardanimate.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.silwar.cardanimate.ui.FlipView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardInfoScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: CardViewModel = viewModel()
    val state: CardInfoState = viewModel.uiState.collectAsState().value
    Column(
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
        ) {
            FlipView(cardFace = state.frontInfoState.cardFace,
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(2f),
                front = {
                    CardFrontItem(
                        modifier = Modifier,
                        state
                    )
                },
                back = {
                    CardBackItem(
                        modifier = Modifier,
                        viewModel,
                        state
                    )
                }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            CardInputItem(
                modifier = Modifier,
                viewModel
            )
        }
        Text(
            text = "*RuPay logo is registered trademark of NPCI India and is only used here to show Network Provider for the card issued and can be removed/replaced if required.",
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

@Preview
@Composable
fun CardInfoScreenPreview() {
    CardInfoScreen()
}