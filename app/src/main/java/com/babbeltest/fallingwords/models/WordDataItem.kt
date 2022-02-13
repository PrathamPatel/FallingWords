package com.babbeltest.fallingwords.models

import com.google.gson.annotations.SerializedName

data class WordDataItem(
    @SerializedName("text_eng")
    var textEng: String = "",
    @SerializedName("text_spa")
    var textSpa: String = ""
)
