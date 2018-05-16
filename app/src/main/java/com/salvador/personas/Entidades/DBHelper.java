package com.salvador.personas.Entidades;

import android.content.ContentValues;
import android.content.Context;
import android.content.RestrictionEntry;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.salvador.personas.Datos.Persona;

/**
 * Created by UCA on 16/05/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="bd_usuarios";
    public static final String TABLA_USUARIO="Persona";
    public static final String CAMPO_ID="dui";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+ TABLA_USUARIO
            +"("+CAMPO_ID+" TEXT,"+ CAMPO_NOMBRE +" TEXT)";

    public static DBHelper myDB = null;
    private Context context;
    SQLiteDatabase db;

    //Singleton
    public static DBHelper getInstance(Context ctx){
        if(myDB == null){
            myDB = new DBHelper(ctx.getApplicationContext());
        }
        return myDB;
    }


    public DBHelper(Context context){
        super(context,DB_NAME,null,1);
        this.context= context;
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_NOMBRE);
        onCreate(db);
    }


    public boolean add(Persona p){
        ContentValues values = new ContentValues();
        values.put(CAMPO_ID,p.getDui());
        values.put(CAMPO_NOMBRE,p.getNombre());

        db.insert(TABLA_USUARIO,null,values);
        Toast.makeText(context,"Insertado con exito", Toast.LENGTH_SHORT).show();
        return  true;
    }

    //Encontrar un usuario.

    public Persona findUser(String dui){
        Persona p;

        String[] parametros = {dui};
        String[] campos = {CAMPO_NOMBRE};

        try{
            Cursor cursor = db.query(TABLA_USUARIO,campos,
                    CAMPO_ID+"=?", parametros,
                    null,null,null);
            cursor.moveToFirst();
            p= new Persona(dui,cursor.getString(0));
        }catch (Exception e){
            p= null;
        }
        return p;
    }

    public boolean editUser(Persona p){
        String[] parametros = {p.getDui()};
        String[] campos = {CAMPO_NOMBRE};
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOMBRE,p.getNombre());
        db.update(TABLA_USUARIO,values, CAMPO_ID+"=?",parametros);
        Toast.makeText(context,"Usuario Actualizado con Exito",Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean deleteUser(String dui){
        String[] parametros = {dui};
        db.delete(TABLA_USUARIO, CAMPO_ID+"=?",parametros);
        Toast.makeText(context,"Usuario Eliminado con exito",Toast.LENGTH_LONG).show();
        return true;
    }

}
