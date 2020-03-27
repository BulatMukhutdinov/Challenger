package tat.mukhutdinov.challenger.infrastructure

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import tat.mukhutdinov.challenger.Database

lateinit var application: Application

actual fun provideSqlDriver(): SqlDriver =
    AndroidSqliteDriver(Database.Schema, application, "challenger.db")