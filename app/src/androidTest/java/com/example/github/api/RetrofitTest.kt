package com.example.github.api

import com.example.githubui.api.Retrofit
import com.google.gson.internal.GsonBuildConfig
import org.junit.Assert.*
import org.junit.Test

class RetrofitTest {
    @Test
    fun testRetrofitInstance() {
        val instance: retrofit2.Retrofit = Retrofit().retrofit
        assert(instance.baseUrl().url().toString() == "https://api.github.com/")
    }
}