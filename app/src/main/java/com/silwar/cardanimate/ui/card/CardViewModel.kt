package com.silwar.cardanimate.ui.card

import androidx.lifecycle.ViewModel
import com.silwar.cardanimate.ui.CardFace
import kotlinx.coroutines.flow.MutableStateFlow

class CardViewModel : ViewModel() {
    val uiState = MutableStateFlow(CardInfoState())

    fun setCardNumber(number: String) {
        val frontState = uiState.value.frontInfoState
        uiState.value = uiState.value.copy(
            frontInfoState = frontState.copy(
                cardNumber = number.toCardFormat()
            )
        )
    }

    fun setCardHolderName(name: String) {
        val frontState = uiState.value.frontInfoState
        uiState.value = uiState.value.copy(
            frontInfoState = frontState.copy(
                cardHolderName = name
            )
        )
    }

    fun setCardExpiryMonth(month: String) {
        val frontState = uiState.value.frontInfoState
        uiState.value = uiState.value.copy(
            frontInfoState = frontState.copy(
                cardExpiryMonth = with(month) {
                    if (this.length < 2) {
                        "0$this"
                    } else {
                        this
                    }
                }
            )
        )
    }

    fun setCardExpiryYear(year: String) {
        val frontState = uiState.value.frontInfoState
        uiState.value = uiState.value.copy(
            frontInfoState = frontState.copy(
                cardExpiryYear = year
            )
        )
    }

    fun setCardCvv(cvv: String) {
        var result = DEFAULT_CARD_CVV
        cvv.forEach {
            result = result.replaceFirst('0', it)
        }
        val frontState = uiState.value.frontInfoState
        uiState.value = uiState.value.copy(
            frontInfoState = frontState.copy(
                cardCvv = cvv
            )
        )
    }

    fun handleCvvFieldFocus(isCvvFocused: Boolean) {
        val frontState = uiState.value.frontInfoState
        uiState.value = uiState.value.copy(
            frontInfoState = frontState.copy(
                cardFace = if (isCvvFocused) {
                    CardFace.Back
                } else {
                    CardFace.Front
                }
            )
        )
    }

    private fun String.toCardFormat(): String {
        var result = DEFAULT_CARD_NUMBER
        this.forEach {
            result = result.replaceFirst('#', it)
        }
        return result
    }
}