/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: ENE-JUN/2022    HORA: 10-11 HRS
:*
:*            Actividad que realiza las sentencias SQL de inicio
:*
:*  Archivo     : BDSQLite.java
:*  Autor       : Valentin Herrera Castorena     18131250
:*  Fecha       : 01/Junio/2022
:*  Compilador  : Android Studio Artic Fox 2020.3
:*  Descripci�n : Esta clase ejecuta las sentencias SQL que nos crea las tablas de la aplicacion:
                    -almuerzo
                    -comida
                    -cena
                    -bebidas
                    -pedido
                    Ademas de ejecutar las sentencias que hacen el llenado de las tablas al instalar
                    la aplicacion por primera vez.
:*  Ultima modif:
:*  Fecha       Modific�             Motivo
:*==========================================================================================
:*  dd/mmm/aaaa Fultano de tal       Motivo de la modificacion, puede ser en mas de 1 renglon.
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.c18131250.foodorder;

import androidx.annotation.Nullable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDSQLite extends SQLiteOpenHelper
{

    private String sqlAlmuerzo = "create table almuerzo(" +
            "idAlm int," +
            "nomAlm varchar(100)," +
            "precioAlm int" +
            ")";

    private String sqlComida = "create table comida(" +
            "idCom int," +
            "nomCom varchar(100)," +
            "precioCom int" +
            ")";

    private String sqlCena = "create table cena(" +
            "idCen int," +
            "nomCen varchar(100)," +
            "precioCen int" +
            ")";

    private String sqlBebida = "create table bebida(" +
            "idBeb int," +
            "nomBeb varchar(100)," +
            "precioBeb int" +
            ")";

    private String sqlInsertAlmuerzo = "insert into almuerzo " +
            "(idAlm, nomAlm, precioAlm) values " +
            "(1, 'Quesadillas vegetarianas', 49)," +
            "(2, 'Empanadas de pollo', 29)," +
            "(3, 'Huevos rellenos', 59)," +
            "(4, 'Sandwich de queso fundido', 39)," +
            "(5, 'Tortilla de espinacas', 50)," +
            "(6, 'Sandwich vegetal con aguacate', 40)," +
            "(7, 'Tortilla francesa con queso', 29)," +
            "(8, 'Rollitos de queso y jamon york', 39)," +
            "(9, 'Hot Cakes', 49)," +
            "(10, 'Waffles', 59)," +
            "(11, 'Crepas rellenas', 69)";

    private String sqlInsertComida = "insert into comida " +
            "(idCom, nomCom, precioCom) values " +
            "(1, 'Hamburguesa', 79)," +
            "(2, 'Enchiladas suizas', 129)," +
            "(3, 'Salchiburguer', 99)," +
            "(4, 'Orden de tacos', 39)," +
            "(5, 'Subway', 49)," +
            "(6, 'Papas fritas', 60)," +
            "(7, 'Pizza', 99)," +
            "(8, 'Pizza con boneless', 119)," +
            "(9, 'Sopa', 29)," +
            "(10, 'Pechuga de pollo', 69)," +
            "(11, 'Bistec', 89)";

    private String sqlInsertCena = "insert into cena " +
            "(idCen, nomCen, precioCen) values " +
            "(1, 'Rollos primavera', 49)," +
            "(2, 'Palitos de queso mozzarella', 39)," +
            "(3, 'Pollo crujiente', 69)," +
            "(4, 'Lomo a la sal', 79)," +
            "(5, 'Tortilla de acelgas con jamon', 39)," +
            "(6, 'Pan relleno', 19)," +
            "(7, 'Schinitzel', 59)," +
            "(8, 'Sashimi de Salmon', 49)," +
            "(9, 'Nuggets de Salmon', 69)";

    private String sqlInsertBebida = "insert into bebida " +
            "(idBeb, nomBeb, precioBeb) values " +
            "(1, 'Coca Cola', 29)," +
            "(2, 'Pepsi', 29)," +
            "(3, 'Manzanita', 29)," +
            "(4, 'Sprite', 29)," +
            "(5, 'Mirinda', 29)," +
            "(6, 'Agua', 9)," +
            "(7, 'Soda Italiana', 39)," +
            "(8, 'Cafe', 19)," +
            "(9, 'Leche', 19)";

    private String sqlPedido = "create table pedido (" +
            "idPed int," +
            "descripcionPed varchar(300)," +
            "costoPed int" +
            ")";

    public BDSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlAlmuerzo);
        db.execSQL(sqlComida);
        db.execSQL(sqlCena);
        db.execSQL(sqlBebida);
        db.execSQL(sqlPedido);
        db.execSQL(sqlInsertAlmuerzo);
        db.execSQL(sqlInsertComida);
        db.execSQL(sqlInsertCena);
        db.execSQL(sqlInsertBebida);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}