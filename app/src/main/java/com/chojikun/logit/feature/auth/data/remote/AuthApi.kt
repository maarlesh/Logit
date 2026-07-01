package com.chojikun.logit.feature.auth.data.remote

import com.chojikun.logit.feature.auth.data.model.ApiResponse
import com.chojikun.logit.feature.auth.data.model.LoginData
import com.chojikun.logit.feature.auth.data.model.LoginPayload
import com.chojikun.logit.feature.auth.data.model.RegisterData
import com.chojikun.logit.feature.auth.data.model.RegisterPayload
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun register(@Body payload: RegisterPayload): ApiResponse<RegisterData>

    @POST("auth/login")
    suspend fun login(@Body payload: LoginPayload): ApiResponse<LoginData>
}