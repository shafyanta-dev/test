package id.salt.core.utils

import android.webkit.MimeTypeMap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

object RetrofitUploadHelper {

    fun prepareFilePart(partName: String, file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody(file.getMimeType().toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(partName, "${System.currentTimeMillis()}${file.name}", requestFile)
    }

    fun prepareStringPart(value: String): RequestBody {
        return value.toRequestBody(MultipartBody.FORM)
    }

    fun prepareIntPart(value: Int): RequestBody {
        return value.toString().toRequestBody(MultipartBody.FORM)
    }

}

fun File.getMimeType(fallback: String = "image/*"): String {
    return MimeTypeMap.getFileExtensionFromUrl(toString())
        ?.run { MimeTypeMap.getSingleton().getMimeTypeFromExtension(lowercase()) }
        ?: fallback // You might set it to */*
}