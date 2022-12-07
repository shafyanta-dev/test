package id.salt.core.di

import id.salt.core.data.pref.PrefStore
import id.salt.core.data.remote.RemoteApp
import javax.inject.Inject

class AppRepo @Inject constructor(
    val pref: PrefStore,
    val remote: RemoteApp
)