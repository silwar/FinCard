package com.silwar.cardanimate.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CardBackItem(
    modifier: Modifier = Modifier,
    viewModel: CardViewModel,
    state: CardInfoState
) {
    Column(
        modifier = modifier
            .aspectRatio(1.75f)
            .background(Color.Black, shape = RoundedCornerShape(20.dp))
            .padding(top = 25.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .height(50.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Authorized Signature",
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(start = 20.dp, top = 10.dp)
            )
            Text(
                text = "Not Valid Unless Signed",
                fontSize = 8.sp,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, top = 10.dp)
            )
        }
        Row {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 2.dp)
                    .background(Color.White)
                    .size(250.dp, 30.dp)
            ) {
                repeat(4) {
                    Spacer(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .background(Color(0xFF0099FF))
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 4.dp, top = 1.dp)
                    .size(60.dp, 30.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(4.dp))
            ) {
                Text(
                    text = "CVV",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(
                        start = 2.dp
                    )
                )
                Text(
                    text = state.frontInfoState.cardCvv,
                    fontSize = 14.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    letterSpacing = 5.sp,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp)
                )
            }
        }
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 10.dp)
        )
    }
}

@Preview
@Composable
fun CardBackItemPreview() {
    val viewModel: CardViewModel = viewModel()
    val state = viewModel.uiState.collectAsState().value
    CardBackItem(viewModel = viewModel, state = state)
}