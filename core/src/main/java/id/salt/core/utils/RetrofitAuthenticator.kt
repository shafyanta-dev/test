package id.salt.core.utils

import id.salt.core.data.pref.PrefStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class RetrofitAuthenticator(
    private val prefStore: PrefStore
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val authKey = runBlocking {
            prefStore.token.first()
        }

        if (authKey.isNotEmpty()) {
            return response.request.newBuilder()
                .header("Authorization", "Bearer $authKey")
                .build()
        }

        return null
    }
}
