package com.example.codelab5.`interface`

import android.graphics.Bitmap

interface DetailsInterface {
    fun detailsInterface(
        id: String,
        image:ByteArray? = byteArrayOf(),
        name: String,
        team: String,
        country: String,
        match: String,
        run: String,
        wicket: String
    )
}

