package com.example.easycashchallenge.utils

import java.io.InputStream

interface OnImageFetched {

    fun onFetch(stream: InputStream?)
}