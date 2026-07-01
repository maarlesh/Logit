package com.chojikun.logit.feature.auth.domain.repository

import com.chojikun.logit.core.network.util.ApiResult
import com.chojikun.logit.feature.auth.data.model.LoginPayload
import com.chojikun.logit.feature.auth.data.model.RegisterPayload
import com.chojikun.logit.feature.auth.domain.model.LoginResult
import com.chojikun.logit.feature.auth.domain.model.RegisterResult

interface AuthRepository {
    suspend fun register(payload: RegisterPayload): ApiResult<RegisterResult>
    suspend fun login(payload: LoginPayload): ApiResult<LoginResult>
}