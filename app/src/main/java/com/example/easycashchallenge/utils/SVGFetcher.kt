package com.example.easycashchallenge.utils

import android.content.Context
import okhttp3.*
import java.io.IOException

class SVGFetcher {

    companion object {
        fun loadSVG(context: Context, url: String, onImageFetched: OnImageFetched) {
            val client = OkHttpClient.Builder()
                .cache(Cache(context.cacheDir, 5 * 1024 * 1014))
                .build()

            val request = Request.Builder().url(url).build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onImageFetched.onFetch(null)
                }

                override fun onResponse(call: Call, response: Response) {
                    val stream = response.body?.byteStream()
                    onImageFetched.onFetch(stream)
                    stream?.close()
                }

            })

        }
    }
}