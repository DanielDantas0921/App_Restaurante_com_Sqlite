package com.danx0921.apprestauranteebancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int versao = 1;
    public static String nameDB = "PedidosDB.dbs";


    String[] sql= {


            "CREATE TABLE Pedidos(nome TEXT PRIMARY KEY,preco REAL,descricao TEXT);",
            "INSERT INTO Pedidos VALUES ('Frango',5.5,'Um prato de frango muito saboroso');",
            "INSERT INTO Pedidos VALUES ('Lasanha',3.6,'Um prato de macarrão bem composto');",
            "INSERT INTO Pedidos VALUES ('Cuscuz',4.5 , 'Um prato nordestino muito delicioso');"

//n entendi oq vc fez kk
            //vou fechar e abrir denovo kk

    };

    public DBHelper(@Nullable Context context) {
        super(context, nameDB, null, versao);
    }

    //pode rodar ? sim

    @Override
    public void onCreate(SQLiteDatabase db) {

        for (int i = 0; i < sql.length; i++) {
            db.execSQL(sql[i]);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        versao++;
        db.execSQL("DROP TABLE IF EXISTS Pedidos ");
        onCreate(db);


    }

//---------------------------------------INSERT----------------------------------------------------------
    public long Insert_Pedidos(String nome, String descricao ,Double preco  ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome",nome);
        values.put("preco",preco);
        values.put("descricao",descricao);
        return db.insert("Pedidos",null,values);
    }

//---------------------------------------UPDATE---------------------------------------------------------
    public long Update_Pedidos(String nome, String descricao ,double preco ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("preco",preco);
        values.put("descricao",descricao);
        return db.update("Pedidos",values,"nome=?",new String[]{nome});
    }


//---------------------------------------DELETE---------------------------------------------------------
    public long Delete_Pedidos(String nome){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("Pedidos","nome=?",new String[]{nome});
    }

//---------------------------------------SELECT---------------------------------------------------------
//tive uma ideia. Tenta ver esse select all se ele vai selecionar os valores que vai colocar dentro da tabela
    //ali só tem o eduardo quando eu fui fazer o teste
    //é mano, acho que o problema é na hora de inserir mesmo
    public Cursor SelectAll_Pedidos(){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM Pedidos", null);
    }

    public Cursor SelectByNome_Pedidos(String nome){
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM Pedidos WHERE nome=?", new String[]{nome});

    }


}
