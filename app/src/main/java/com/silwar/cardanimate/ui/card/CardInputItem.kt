package com.silwar.cardanimate.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.core.text.isDigitsOnly

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CardInputItem(
    modifier: Modifier = Modifier,
    viewModel: CardViewModel,
    state: CardInfoState
) {
    var pan by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var monthExpanded by remember {
        mutableStateOf(false)
    }
    var selectedMonth by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("") }
    var yearExpanded by remember { mutableStateOf(false) }
    var textFiledSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (monthExpanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    val panLength = 16
    val nameLength = 20
    val cvvLength = 3
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(22.dp))
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(18.dp)
    ) {
        Text(
            text = "Card Number",
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(top = 4.dp))
        OutlinedTextField(
            value = pan,
            modifier = Modifier.fillMaxWidth(),

            onValueChange = {
                if (it.isDigitsOnly() && it.length <= panLength) {
                    pan = it
                    viewModel.setCardNumber(it)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            text = "Card Holder",
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(top = 4.dp))
        OutlinedTextField(
            value = name,
            modifier = Modifier.fillMaxWidth(),

            onValueChange = { value ->
                if (value.length <= nameLength &&
                    value.all { it.isLetter() || it.isWhitespace() }
                ) {
                    name = value
                    viewModel.setCardHolderName(name.uppercase())
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Row {
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = "Expiration Date",
                    color = Color.Black
                )
                Spacer(modifier = Modifier.padding(top = 4.dp))
                Row {
                    OutlinedTextField(
                        value = selectedMonth,
                        readOnly = true,
                        onValueChange = { selectedMonth = it },
                        modifier = Modifier
                            .weight(1f)
                            .onGloballyPositioned { coordinates ->
                                textFiledSize = coordinates.size.toSize()
                            },
                        label = { Text(text = "MM") },
                        trailingIcon = {
                            Icon(icon, "", Modifier.clickable { monthExpanded = !monthExpanded })
                        }
                    )
                    DropdownMenu(
                        expanded = monthExpanded,
                        onDismissRequest = { monthExpanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { 300.toDp() })
                            .offset(y = with(LocalDensity.current) { 50.toDp() })
                    ) {
                        (1..12).map { it.toString() }.forEach { label ->
                            DropdownMenuItem(
                                text = {
                                    Text(text = with(label) {
                                        if (this.length < 2) {
                                            "0$this"
                                        } else {
                                            this
                                        }
                                    })
                                },
                                onClick = {
                                    selectedMonth = label
                                    monthExpanded = false
                                    viewModel.setCardExpiryMonth(label)
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(4.dp))

                    OutlinedTextField(
                        value = selectedYear,
                        readOnly = true,
                        onValueChange = { selectedYear = it },
                        modifier = Modifier
                            .weight(1f)
                            .onGloballyPositioned { coordinates ->
                                textFiledSize = coordinates.size.toSize()
                            },
                        label = { Text(text = "YY") },
                        trailingIcon = {
                            Icon(icon, "", Modifier.clickable { yearExpanded = !yearExpanded })
                        }
                    )
                    DropdownMenu(
                        expanded = yearExpanded,
                        onDismissRequest = { yearExpanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current) { 300.toDp() })
                            .offset(y = with(LocalDensity.current) { 50.toDp() })
                    ) {
                        (24..30).map { it.toString() }.forEach { label ->
                            DropdownMenuItem(
                                text = { Text(text = label) },
                                onClick = {
                                    selectedYear = label
                                    yearExpanded = false
                                    viewModel.setCardExpiryYear(label)
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "CVV",
                    color = Color.Black
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))
                OutlinedTextField(
                    value = cvv,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged {
                            viewModel.handleCvvFieldFocus(it.isFocused)
                        },
                    onValueChange = { value ->
                        if (value.isDigitsOnly() &&
                            value.length <= cvvLength &&
                            value.startsWith("0").not()
                        ) {
                            cvv = value
                            viewModel.setCardCvv(cvv)
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        }
    }
}