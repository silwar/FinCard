package com.silwar.cardanimate.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.silwar.cardanimate.R

@Composable
fun CardFrontItem(
    modifier: Modifier = Modifier,
    viewModel: CardViewModel,
    state: CardInfoState
) {
    ConstraintLayout(
        modifier = modifier
            .aspectRatio(1.75f)
            .background(Color.Black, shape = RoundedCornerShape(12.dp))
    ) {
        val (header, image, pan, layout) = createRefs()
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp, start = 18.dp, end = 18.dp)
            .constrainAs(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }) {
            Text(
                text = "CREDIT CARD",
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.rupay),
                contentDescription = "Network Logo"
            )
        }
        Image(painter = painterResource(id = R.drawable.img_card_chip),
            contentDescription = "Image chip",
            modifier = Modifier
                .size(45.dp, 55.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom, margin = 50.dp)
                    start.linkTo(parent.start, margin = 18.dp)
                })
        Text(text = state.frontInfoState.cardNumber,
            fontSize = 24.sp,
            color = Color.White,
            letterSpacing = 5.sp,
            maxLines = 1,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp, vertical = 4.dp
                )
                .constrainAs(pan) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                })
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 4.dp)
            .constrainAs(layout) {
                top.linkTo(pan.bottom)
                start.linkTo(parent.start)
            }) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "CARD HOLDER",
                    fontSize = 11.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = state.frontInfoState.cardHolderName,
                    color = Color.White
                )
            }
            Column(modifier = Modifier) {
                Text(
                    text = "EXPIRY",
                    fontSize = 11.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = state.frontInfoState.cardExpiry, color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun CardFrontItemPreview() {
    val viewModel: CardViewModel = viewModel()
    val state = viewModel.uiState.collectAsState().value
    CardFrontItem(viewModel = viewModel, state = state)
}