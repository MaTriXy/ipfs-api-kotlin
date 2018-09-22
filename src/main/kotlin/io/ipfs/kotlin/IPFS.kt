package io.ipfs.kotlin

import com.squareup.moshi.Moshi
import io.ipfs.kotlin.commands.*
import io.ipfs.kotlin.model.MessageWithCode
import okhttp3.OkHttpClient

open class IPFS @JvmOverloads constructor(protected val base_url: String = "http://127.0.0.1:5001/api/v0/",
                                          okHttpClient: OkHttpClient = OkHttpClient.Builder().build(),
                                          protected val moshi: Moshi = Moshi.Builder().build()) {

    private val connection = IPFSConnection(base_url, okHttpClient, moshi)

    val add by lazy { Add(connection) }
    val get by lazy { Get(connection) }
    val info by lazy { Info(connection) }
    val stats by lazy { Stats(connection) }
    val pins by lazy { Pins(connection) }
    val repo by lazy { Repo(connection) }
    val name by lazy { Name(connection) }

    val lastError: MessageWithCode? get() = connection.lastError
}