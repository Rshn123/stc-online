package com.example.stconline.item

import android.media.Rating
import android.widget.ImageView
import java.util.*

open class Item {
    var id: String = UUID.randomUUID().toString()
    var itemName: String? = null
    var itemPrice: Double = 0.0
    var itemRating: Float = 0.0f
    var image : Int = 0

    @Suppress("unused")
    constructor()

    constructor(itemName: String, itemPrice: Double, itemRating: Float) {
        this.itemName = itemName
        this.itemPrice = itemPrice
        this.itemRating = itemRating
//        this.image = image
    }
}