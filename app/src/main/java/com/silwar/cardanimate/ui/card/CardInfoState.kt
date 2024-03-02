package com.silwar.cardanimate.ui.card

import com.silwar.cardanimate.ui.CardFace

data class CardInfoState(
    val frontInfoState: CardFrontUiState = CardFrontUiState(),
    val inputUiState: CardInputUiState = CardInputUiState()
)

data class CardFrontUiState(
    val cardNumber: String = DEFAULT_CARD_NUMBER,
    val cardHolderName: String = DEFAULT_CARD_NAME,
    val cardExpiryMonth: String = DEFAULT_EXPIRY_MONTH,
    val cardExpiryYear: String = DEFAULT_EXPIRY_YEAR,
    val cardFace: CardFace = CardFace.Front,
    val cardCvv: String = DEFAULT_CARD_CVV
) {
    val cardExpiry: String
        get() = "$cardExpiryMonth/$cardExpiryYear"
}

data class CardInputUiState(
    val cardNumber: String = EMPTY
)