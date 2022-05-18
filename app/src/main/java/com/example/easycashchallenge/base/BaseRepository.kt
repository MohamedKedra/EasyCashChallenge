package com.example.easycashchallenge.base

import com.example.easycashchallenge.local.AppDao
import com.example.easycashchallenge.network.AppService

abstract class BaseRepository(protected val appService: AppService,appDao: AppDao)