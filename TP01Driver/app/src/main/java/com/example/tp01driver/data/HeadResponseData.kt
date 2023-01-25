package com.example.tp01driver.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HeadResponseData {
    /**
     * 응답 코드
     */
    @SerializedName("code")
    @Expose
    var code = 0

    /**
     * 응답 메시지
     */
    @SerializedName("message")
    @Expose
    var message = ""

    /**
     * 응답 상세 내용
     */
    @SerializedName("detail")
    @Expose
    var detail = ""
}