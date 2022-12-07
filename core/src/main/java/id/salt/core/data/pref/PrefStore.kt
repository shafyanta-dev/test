package id.salt.core.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import id.salt.core.data.pref.PrefStoreInterface.Companion.TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

class PrefStore @Inject constructor(
    @ApplicationContext private val context: Context
): PrefStoreInterface {

    val token: Flow<String> = context.dataStore.getValueAsFlow(TOKEN, "")
    suspend fun setToken(token: String) { context.dataStore.setValue(TOKEN, token) }

    private suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }

    private fun <T> DataStore<Preferences>.getValueAsFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return this.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }

    override fun <T> getData(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        context.dataStore.getValueAsFlow(key, defaultValue)

    override suspend fun <T> saveData(key: Preferences.Key<T>, value: T) {
        context.dataStore.setValue(key, value)
    }

    override suspend fun clearPreference() {
        context.dataStore.edit {
            it.clear()
        }
    }

}