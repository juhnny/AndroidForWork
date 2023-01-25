package com.example.tp01driver.data

data class UserVO(var accessToken:String, var tdId:Int, var tuId:Int)

/**
 *     /**
 * 토큰발행
*/
@SerializedName("accessToken")
@Expose
var accessToken: String? = ""

/**
 * 기사 시퀀스 아이디
*/
@SerializedName("tdid")
@Expose
var tdId: Int? = null

/**
 * 사용자 아이디
*/
@SerializedName("tuid")
@Expose
var tuId: Int? = null
 */
