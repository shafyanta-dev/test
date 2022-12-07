package id.salt.core.data.remote

import id.salt.core.data.base.BaseData
import id.salt.core.other.Url
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WebService {

    @FormUrlEncoded
    @POST(Url.LOGIN)
    suspend fun login(
        @Field("email") email: String?,
        @Field("password") password: String?,
    ): Response<BaseData<String>>

}