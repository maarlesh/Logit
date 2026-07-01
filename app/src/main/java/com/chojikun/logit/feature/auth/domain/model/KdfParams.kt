package com.chojikun.logit.feature.auth.domain.model

data class KdfParams(
    val memory: Int,
    val iterations: Int,
    val parallelism: Int,
)
