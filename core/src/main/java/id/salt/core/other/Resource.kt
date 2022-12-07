package id.salt.core.other

data class Resource<out T>(
    val status: Status,
    val response: T?,
    val message:String?,
    val errorCode: Int? = 1
){
    companion object{

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg:String?, errorCode: Int = 0): Resource<T> {
            return Resource(Status.ERROR, null, msg, errorCode)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

    }
}