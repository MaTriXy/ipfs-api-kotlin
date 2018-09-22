package io.ipfs.kotlin

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.ipfs.kotlin.model.MessageWithCode
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody

open class IPFSConnection(val base_url: String,
                          val okHttpClient: OkHttpClient,
                          val moshi: Moshi) {

    var lastError: MessageWithCode? = null

    private val errorAdapter: JsonAdapter<MessageWithCode> by lazy {
        moshi.adapter(MessageWithCode::class.java)
    }

    fun callCmd(cmd: String): ResponseBody {
        val request = Request.Builder()
                .url(base_url + cmd)
                .build()

        return okHttpClient.newCall(request).execute().body()!!
    }

    fun setErrorByJSON(jsonString: String) {
        lastError = errorAdapter.fromJson(jsonString)
    }
}