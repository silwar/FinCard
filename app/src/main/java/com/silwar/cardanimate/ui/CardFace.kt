package com.silwar.cardanimate.ui

enum class CardFace(val angle: Float) {
    Front(0F) {
        override val next: CardFace
            get() = Back
    },
    Back(180F) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}