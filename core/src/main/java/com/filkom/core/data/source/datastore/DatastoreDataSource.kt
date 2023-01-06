package com.filkom.core.data.source.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.rootDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DatastoreDataSource @Inject constructor(
    private val context:Context
) {
    val datastore = context.rootDataStore

    //save first time state
    suspend fun saveFirstTimeState(isFirstTime:Boolean) =
        datastore.edit {
            it[booleanPreferencesKey("IS_FIRST_TIME")] = isFirstTime
        }

    //get first time state
    fun getFirstTimeState() = datastore.data.map {
        it[booleanPreferencesKey("IS_FIRST_TIME")] ?: true
    }

    //save bearer token
    suspend fun saveBearerToken(token:String) =
        datastore.edit {
            it[stringPreferencesKey("BEARER_TOKEN")] = token
        }

    //get bearer token
    fun getBearerToken() = datastore.data.map {
        it[stringPreferencesKey("BEARER_TOKEN")] ?: ""
    }
}