package id.salt.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import id.salt.core.di.AppRepo
import id.salt.core.other.Resource
import id.salt.core.other.Status
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ContainerViewModel @Inject constructor(
    private val repo: AppRepo
) : ViewModel() {

    fun login(email: String?, password: String?): LiveData<Resource<String>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val request = repo.remote.account.login(email, password)
            if (request.status == Status.ERROR) {
                emit(Resource.error(request.message, request.errorCode ?: 0))
            } else {
                request.response?.let {
                    emit(Resource.success(it.data.toString()))
                } ?: kotlin.run {
                    emit(Resource.error(request.message, request.errorCode ?: 0))
                }
            }
        }

}