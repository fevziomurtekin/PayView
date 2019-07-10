package com.fevziomurtekin.payview.data

import com.fevziomurtekin.payview.commons.CardType

data class PayModel(
    var cardOwnerName:String="name surname",
    var cardMonth:Int=12,
    var cardYear:Int=20,
    var cardNo:String="1111111111111111",
    var cardCv:String="222",
    var cardType: CardType = CardType.UNDEFINED
)