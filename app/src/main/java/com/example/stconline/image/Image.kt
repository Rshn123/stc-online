package com.example.stconline.image

import android.graphics.drawable.Drawable
import java.util.*

class Image{
    val imageId =  UUID.randomUUID().toString()
    var imageName : Int = 0

    @Suppress("unused")
    constructor()

    constructor(imageName : Int)
    {
        this.imageName = imageName
    }
}