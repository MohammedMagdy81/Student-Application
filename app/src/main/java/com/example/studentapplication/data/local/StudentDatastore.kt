package com.example.studentapplication.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class StudentDatastore(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "students")

    private val dataStore = context.dataStore

    companion object {
        private val ID = intPreferencesKey("id")
        private val TOKEN = stringPreferencesKey("token")
        private val NAME = stringPreferencesKey("name")
        private val PHONE = stringPreferencesKey("phone")
        private val EMAIL = stringPreferencesKey("email")

    }

    suspend fun setToken(tokenValue: String?) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = tokenValue?:""
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val token = preferences[TOKEN] ?: ""
                token
            }
    }

    suspend fun setUserName(userName: String?) {
        dataStore.edit { preferences ->
            preferences[NAME] = userName?:""
        }
    }

    fun getUserName(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val userName = preferences[NAME] ?: ""
                userName
            }
    }

    suspend fun setPhoneNo(phoneNo: String?) {
        dataStore.edit { preferences ->
            preferences[PHONE] = phoneNo?:""
        }
    }

    fun getPhoneNo(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val phoneNo = preferences[PHONE] ?: ""
                phoneNo
            }
    }

    suspend fun setTeacherId(id: Int?) {
        dataStore.edit { preferences ->
            preferences[ID] = id ?: 0
        }
    }

    fun getTeacherId(): Flow<Int> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val id = preferences[ID] ?: 0
                id
            }
    }

    suspend fun setEmail(email: String?) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = email?:""
        }
    }

    fun getEmail(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val email = preferences[EMAIL] ?: ""
                email
            }
    }

}