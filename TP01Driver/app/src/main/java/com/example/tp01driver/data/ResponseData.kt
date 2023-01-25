package com.example.tp01driver.data

data class ResponseData<T>(var head: HeadResponseData, var body: List<T>)

/**
/**
 * 응답 결과
*/
@SerializedName("head")
@Expose
var head: HeadResponseData? = null

/**
 * 응답 데이터(리스트)
*/
@SerializedName("body")
@Expose
var body: List<T>? = null
 */