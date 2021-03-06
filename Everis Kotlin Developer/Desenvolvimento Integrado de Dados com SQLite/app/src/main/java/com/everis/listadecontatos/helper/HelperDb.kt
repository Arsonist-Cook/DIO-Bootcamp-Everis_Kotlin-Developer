package com.everis.listadecontatos.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.everis.listadecontatos.feature.listacontatos.model.ContatosVO

class HelperDB(
        context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, CURRENT_VERSION) {
    companion object {
        private const val DATABASE_NAME = "contatos.db"
        private const val CURRENT_VERSION = 1
        private const val TABLE_NAME = "contatos"
        private const val COLUMNS_ID = "id"
        private const val COLUMNS_NAME = "nome"
        private const val COLUMNS_PHONE = "telephone"
        private const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        private const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMNS_ID INTEGER NOT NULL," +
                "$COLUMNS_NAME TEXT NOT NULL," +
                "$COLUMNS_PHONE TEXT NOT NULL," +
                "" +
                "PRIMARY KEY($COLUMNS_ID AUTOINCREMENT)" +
                ")"
        private const val ID_EQUALS_TO = "$COLUMNS_ID = ?"
        private const val NAME_LIKE_TO = "$COLUMNS_NAME LIKE ?"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (newVersion > oldVersion) {
            //update database
            db?.execSQL(DROP_TABLE)
            onCreate(db)
        } else if (newVersion < oldVersion) {
            throw Exception("Downgrade Not Available!")
        }
    }

    fun searchContacts(search: String, isIdSearch: Boolean = false): List<ContatosVO> {

        //val query: String = "SELECT * FROM $TABLE_NAME WHERE $COLUMNS_NAME LIKE ? "
        val resultList: MutableList<ContatosVO> = mutableListOf<ContatosVO>()
        val db: SQLiteDatabase = readableDatabase
        //declaration, followed by configuration depending on isIDSearch param.
        val arguments: Array<String>
        val where: String

        if (isIdSearch) {
            where = ID_EQUALS_TO
            arguments = arrayOf(search)

        } else {
            where = NAME_LIKE_TO
            arguments = arrayOf("%$search%")
        }
        // val cursor: Cursor = db.rawQuery(query, arguments)
        val cursor: Cursor = db.query(TABLE_NAME, null, where, arguments, null, null, null)

        while (cursor.moveToNext()) {

            val contact: ContatosVO = ContatosVO(
                    cursor.getInt(cursor.getColumnIndex(COLUMNS_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMNS_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMNS_PHONE))
            )

            resultList.add(contact)
        }

        cursor.close()
        return resultList
    }

    fun saveContact(contact: ContatosVO) {
        //val query:String = "INSERT INTO $TABLE_NAME ($COLUMNS_NAME, $COLUMNS_PHONE) VALUES (?,?)"
        //val arguments:Array<String> = arrayOf(contact.nome, contact.telefone)
        val db: SQLiteDatabase = readableDatabase
        val content: ContentValues = ContentValues()
        content.put(COLUMNS_NAME, contact.nome)
        content.put(COLUMNS_PHONE, contact.telefone)

        db.insert(TABLE_NAME, null, content)

        //db.execSQL(query,arguments)
        db.close()
    }

    fun updateContact(contact: ContatosVO) {
        val db = writableDatabase
        val arguments: Array<String> = arrayOf("${contact.id}")
        val content: ContentValues = ContentValues()
        content.put(COLUMNS_NAME, contact.nome)
        content.put(COLUMNS_PHONE, contact.telefone)

        db.update(TABLE_NAME, content, ID_EQUALS_TO, arguments)

        db.close()
    }

    fun deleteContact(contactId: Int) {
        val db: SQLiteDatabase = writableDatabase
        //val where: String = "$COLUMNS_ID = ?"
        val arguments: Array<String> = arrayOf("$contactId")

        db.delete(TABLE_NAME, ID_EQUALS_TO, arguments)

        db.close()
    }
}