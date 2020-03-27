package tat.mukhutdinov.challenger.infrastructure

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import tat.mukhutdinov.challenger.Database

actual fun provideSqlDriver(): SqlDriver =
    NativeSqliteDriver(Database.Schema, "challenger.db")