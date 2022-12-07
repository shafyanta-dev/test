package id.salt.core.data.remote.source

import id.salt.core.data.base.BaseData
import id.salt.core.data.base.BaseDataSource
import id.salt.core.data.remote.WebService
import id.salt.core.other.Resource
import javax.inject.Inject

class AccountDataSource @Inject constructor(
    private val webService: WebService
) : BaseDataSource() {

    suspend fun login(email: String?, password: String?): Resource<BaseData<String>> = getDataResult {
        webService.login(email, password)
    }

}