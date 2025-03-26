package com.example.swiftrescue1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTable = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_EMAIL TEXT UNIQUE COLLATE NOCASE,
                $COLUMN_PASSWORD TEXT
            )
        """.trimIndent()

        val createServiceRequestsTable = """
            CREATE TABLE $TABLE_SERVICE_REQUESTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_VEHICLE_TYPE TEXT,
                $COLUMN_PROBLEM_DESCRIPTION TEXT,
                $COLUMN_LOCATION TEXT
            )
        """.trimIndent()

        val createServiceCompletionsTable = """
            CREATE TABLE $TABLE_SERVICE_COMPLETIONS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_MECHANIC_NAME TEXT,
                $COLUMN_SERVICE_DESCRIPTION TEXT,
                $COLUMN_COST REAL,
                $COLUMN_TIME_TAKEN TEXT,
                $COLUMN_RATING REAL,
                $COLUMN_REVIEW TEXT
            )
        """.trimIndent()

        db.execSQL(createUsersTable)
        db.execSQL(createServiceRequestsTable)
        db.execSQL(createServiceCompletionsTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SERVICE_REQUESTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SERVICE_COMPLETIONS")
        onCreate(db)
    }

    // Method to register a user
    fun registerUser(name: String, email: String, password: String): Boolean {
        if (userExists(email)) return false // Prevent duplicate registration

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }

        val result = db.insert(TABLE_USERS, null, values)
        db.close()
        return result != -1L
    }

    // Method to authenticate login
    fun loginUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(email, password)
        )
        val isLoggedIn = cursor.count > 0
        cursor.close()
        db.close()
        return isLoggedIn
    }

    // Check if User Exists
    fun userExists(email: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ?", arrayOf(email))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    // Add Service Request
    fun addServiceRequest(vehicleType: String, problemDescription: String, location: String = "Unknown"): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_VEHICLE_TYPE, vehicleType)
            put(COLUMN_PROBLEM_DESCRIPTION, problemDescription)
            put(COLUMN_LOCATION, location)
        }

        val success = db.insert(TABLE_SERVICE_REQUESTS, null, values)
        db.close()
        return success != -1L
    }

    // Get All Service Requests
    fun getAllServiceRequests(): List<ServiceRequests> {
        val serviceRequestList = mutableListOf<ServiceRequests>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_SERVICE_REQUESTS", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val vehicleType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VEHICLE_TYPE))
                val problemDescription = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PROBLEM_DESCRIPTION))
                val location = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION))

                serviceRequestList.add(ServiceRequests(id, problemDescription, vehicleType, location))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return serviceRequestList
    }

    // Add Service Completion
    fun addServiceCompletion(mechanicName: String, serviceDescription: String, cost: Double, timeTaken: String, rating: Float, review: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MECHANIC_NAME, mechanicName)
            put(COLUMN_SERVICE_DESCRIPTION, serviceDescription)
            put(COLUMN_COST, cost)
            put(COLUMN_TIME_TAKEN, timeTaken)
            put(COLUMN_RATING, rating)
            put(COLUMN_REVIEW, review)
        }

        val success = db.insert(TABLE_SERVICE_COMPLETIONS, null, values)
        db.close()
        return success != -1L
    }

    // Get All Service Completions
    fun getAllServiceCompletions(): List<ServiceCompletion> {
        val serviceCompletionList = mutableListOf<ServiceCompletion>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_SERVICE_COMPLETIONS", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val mechanicName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MECHANIC_NAME))
                val serviceDescription = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SERVICE_DESCRIPTION))
                val cost = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_COST))
                val timeTaken = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_TAKEN))
                val rating = cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_RATING))
                val review = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REVIEW))

                serviceCompletionList.add(ServiceCompletion(id, mechanicName, serviceDescription, cost, timeTaken, rating, review))
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return serviceCompletionList
    }

    companion object {
        private const val DATABASE_NAME = "swiftrescue.db"
        private const val DATABASE_VERSION = 3 // Incremented because of schema change

        // Users Table
        const val TABLE_USERS = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"

        // Service Requests Table
        const val TABLE_SERVICE_REQUESTS = "service_requests"
        const val COLUMN_VEHICLE_TYPE = "vehicleType"
        const val COLUMN_PROBLEM_DESCRIPTION = "problemDescription"
        const val COLUMN_LOCATION = "location"

        // Service Completions Table
        const val TABLE_SERVICE_COMPLETIONS = "service_completions"
        const val COLUMN_MECHANIC_NAME = "mechanicName"
        const val COLUMN_SERVICE_DESCRIPTION = "serviceDescription"
        const val COLUMN_COST = "cost"
        const val COLUMN_TIME_TAKEN = "timeTaken"
        const val COLUMN_RATING = "rating"
        const val COLUMN_REVIEW = "review"
    }
}