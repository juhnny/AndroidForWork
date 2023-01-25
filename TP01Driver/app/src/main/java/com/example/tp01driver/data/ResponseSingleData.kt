package com.example.tp01driver.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseSingleData<T> {
    /**
     * 응답 결과
     */
    @SerializedName("head")
    @Expose
    var head: HeadResponseData? = null

    /**
     * 응답 데이터
     */
    @SerializedName("body")
    @Expose
    var body: T? = null
}