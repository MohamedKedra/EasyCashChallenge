package com.example.easycashchallenge.ui.main.ui

import com.example.easycashchallenge.utils.Status

interface OnDataInsertedListener {

    fun onInsert(status: Status, msg: String? = "")
}