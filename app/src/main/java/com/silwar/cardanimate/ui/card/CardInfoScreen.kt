package com.silwar.cardanimate.ui.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.silwar.cardanimate.ui.CardFace
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
            .padding(all = 16.dp),
    ) {
        FlipView(cardFace = state.frontInfoState.cardFace,
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(2f),
            front = {
                CardFrontItem(
                    modifier = Modifier,
                    viewModel,
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
            viewModel,
            state
        )
    }
}

@Preview
@Composable
fun CardInfoScreenPreview() {
    CardInfoScreen()
}