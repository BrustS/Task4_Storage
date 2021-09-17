package by.brust.animalslist

const val TABLE_NAME = "animal_table"
const val COLUMN_NAME = "title"
const val COLUMN_AGE = "age"
const val COLUMN_BREED = "breed"

const val DATABASE_NAME = "animal_db.db"
const val DATABASE_VERSION = 1
const val SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "id INTEGER PRIMARY KEY,$COLUMN_NAME TEXT, $COLUMN_AGE INTEGER,$COLUMN_BREED TEXT)"
const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"