package com.tonyodev.fetch2.database.migration

import androidx.sqlite.db.SupportSQLiteDatabase
import com.tonyodev.fetch2.database.DownloadDatabase
import com.tonyodev.fetch2.util.DEFAULT_AUTO_RETRY_ATTEMPTS

class MigrationSevenToEight : Migration(7, 8) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            """
                CREATE TABLE '${DownloadDatabase.TABLE_TAG_NAME}' (
                    '${DownloadDatabase.COLUMN_TAG_ID}' INTEGER, 
                    '${DownloadDatabase.COLUMN_TITLE}' TEXT NOT NULL, 
                    PRIMARY KEY('${DownloadDatabase.COLUMN_TAG_ID}')
                )
            """.trimIndent()
        )
        database.execSQL(
            """
                CREATE TABLE '${DownloadDatabase.TABLE_TAG_REF_NAME}' (
                    '${DownloadDatabase.COLUMN_TAG_ID}' INTEGER, 
                    '${DownloadDatabase.COLUMN_ID}' INTEGER, 
                    PRIMARY KEY('${DownloadDatabase.COLUMN_TAG_ID}', '${DownloadDatabase.COLUMN_ID}'), 
                    FOREIGN KEY('${DownloadDatabase.COLUMN_TAG_ID}') REFERENCES Tag('${DownloadDatabase.COLUMN_TAG_ID}'), 
                    FOREIGN KEY('${DownloadDatabase.COLUMN_ID}') REFERENCES DownloadInfo('${DownloadDatabase.COLUMN_ID}')
                )
            """.trimIndent()
        )
    }

}
