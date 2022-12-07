package id.salt.core.data.pref

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

interface PrefStoreInterface {

    fun <T> getData(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> saveData(key: Preferences.Key<T>, value: T)

    suspend fun clearPreference()

    companion object{
        val TOKEN = stringPreferencesKey("token")
    }

}