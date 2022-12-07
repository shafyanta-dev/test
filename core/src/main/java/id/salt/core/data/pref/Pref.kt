package id.salt.core.data.pref

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

const val STORE_NAME = "salt_data_store"
val Context.dataStore by preferencesDataStore(name = STORE_NAME)