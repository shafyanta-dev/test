package id.salt.core.data.base

data class BaseData<T>(
    val success: Boolean,
    val message: String,
    val data: T?
)