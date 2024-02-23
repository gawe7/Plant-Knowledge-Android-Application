package com.example.eartheden

import android.media.Image

class CateModel(var title: String?, var Image: String?) {
    // Default constructor
    constructor() : this(null, null)

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("title", title!!)
        result.put("Image", Image!!)
        return result
    }
}