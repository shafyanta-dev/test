package id.salt.core.data.remote

import id.salt.core.data.remote.source.AccountDataSource
import javax.inject.Inject


class RemoteApp @Inject constructor(
    val account: AccountDataSource
)