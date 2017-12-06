/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Alumno
 */
public class Frutautil {

    Connection conn = null;
    Statement sta = null;
    Statement sta1 = null;
    ResultSet rs1 = null;
    ResultSet rsp = null;

    //ResultSet rsw1 = null;
    //ResultSet rsw2 = null;
    public void conectarse() throws SQLException {

//        try {
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "");//????
                    //"jdbc:mysql://localhost:3306/mydb", "root", "dbdb");//????
            //"jdbc:mysql://sql10.freemysqlhosting.net/sql10184095",
            //"sql10184095",
            //"Kn1NMAyzIh"

            System.out.println("pudo conectar a la DB");
            System.out.println(conn.isValid(0));

            sta = conn.createStatement(1003, 1008);
            sta1 = conn.createStatement(1003, 1008);
        /*
        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } */

    }

    public void conectarse(String sv, String user, String pass) throws SQLException {

//        try {
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + sv, user, pass);//????
                    //"jdbc:mysql://localhost:3306/mydb", "root", "dbdb");//????
            //"jdbc:mysql://sql10.freemysqlhosting.net/sql10184095",
            //"sql10184095",
            //"Kn1NMAyzIh"

            System.out.println("pudo conectar a la DB");
            System.out.println(conn.isValid(0));

            sta = conn.createStatement(1003, 1008);
            sta1 = conn.createStatement(1003, 1008);
        /*
        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        } */

    }
    
    void crearTablas() {
        
            mandarSQL("CREATE TABLE pogo (id int(20) PRIMARY KEY, p1 VARCHAR(20), hp1 int (20), d1 int (20), d2 int (20) , hp2 int (20), p2 VARCHAR(20) );");
            mandarSQL("CREATE TABLE wachos (id int(20) PRIMARY KEY, nombre VARCHAR(20), hp int (20), daño1 int (20), velocidad1 int (20) , daño2 int (20), velocidad2 int (20), armadura int (20) );");
            mandarSQL("CREATE TABLE analwachos (id int(20) PRIMARY KEY, nombre VARCHAR(20), victorias int (20), empates int (20), derrotas int (20) , pogos int (20), porcV int(20), porcE int(20), porcD int(20));");

    }
    
        void cargarPersonajes() {
        try {
            String ins;
//            rs1 = sta.executeQuery("SELECT * FROM wachos");
            ins = crearTestoValues("wachos");
            sta.execute(ins + "1', 'Panfeu', '85', '10', '2', '10', '2', '20');");
            sta.execute(ins + "2', 'Miujou', '85', '5', '1', '5', '1', '20');");
            sta.execute(ins + "3', 'Yriceqa', '85', '5', '1', '10', '2', '20');");
            sta.execute(ins + "4', 'Welaohei', '125', '28', '4', '0', '1000', '20');");
            sta.execute(ins + "5', 'Kiwiubo', '100', '11', '2', '0', '1000', '50');");
            sta.execute(ins + "6', 'Tutsx', '100', '22', '4', '0', '1000', '50');");

        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

    }
    
    ResultSet hacerQuery(String testo) {
        try{ 
            return sta.executeQuery(testo);
        } catch (SQLException ex) {
            // handle the error
            System.out.println("o aca?");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return null;

        }
    }
    
    void mandarSQL(String testo) {
        try{ 
            sta.execute(testo);
        } catch (SQLException ex) {
            // handle the error
            System.out.println("o aca?");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            

        }
    }


    
        String crearTestoValues(String tabla) {

        String testo = "";
        try {
            rs1= sta.executeQuery("SELECT * FROM " + tabla + " LIMIT 1 ");
            testo = "INSERT INTO " + rs1.getMetaData().getTableName(1) + " (";

            testo += rs1.getMetaData().getColumnLabel(1);

            for (int i = 2; i <= rs1.getMetaData().getColumnCount(); i++) {
                testo += ", " + rs1.getMetaData().getColumnLabel(i);
            }

            testo += ") VALUE ('";
            

        } catch (SQLException ex) {
            // handle the error
//            System.out.println("cago aca?");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

        return testo;

    }

}


//    public void armarPogo() {
//        Buacho[] bu = elegirWachos();
//        //System.out.println(bu[0].toString());
//        //System.out.println(bu[1].toString());
//        inicioPogo(bu);
//        piñaVa(bu);
//        //pogoUtil();
//    }
//
//    public void ordenarPogo() {
//        try {
//            rsp = sta.executeQuery("SELECT * FROM pogo");
//            //System.out.println(rsp.getInt("id"));
//            int i = 1;
//            while (rsp.next()) {
//                System.out.println(rsp.getInt("id"));
//                System.out.println(i);
//
//                rsp.updateInt("id", i);
//                rsp.updateRow();
//                i++;
//            }
//
//        } catch (SQLException ex) {
//            // handle the error
//            System.out.println("kk");
//
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//
//        }
//    }
//
//    public void piñaVa(Buacho[] bua) {
//        int i;
//        //double[] crit = {0.8, 0.9, 1, 1, 1.4};
//        double[] crit = {0.8, 0.9, 1, 1, 1.6};
//        try {
//            rsp.last();
//            i = rsp.getInt("id") + 1;
//            rsp.moveToInsertRow();
//            while (bua[0].hp > 0 && bua[1].hp > 0) {
//                int d0 = 0;
//                int d1 = 0;
//                int c;
//                if (i % bua[0].velocidad1 == 0) {
//                    d0 += bua[0].daño1;
//                }
//                if (i % bua[0].velocidad2 == 0) {
//                    d0 += bua[0].daño2;
//                }
//                c = (int) (Math.random() * 5);
//                //System.out.println(crit[c]);
//                d0 *= crit[c];
//                if (i % bua[1].velocidad1 == 0) {
//                    d1 += bua[1].daño1;
//                }
//                if (i % bua[1].velocidad2 == 0) {
//                    d1 += bua[1].daño2;
//                }
//
//                c = (int) (Math.random() * 5);
//                //System.out.println(crit[c]);
//                d1 *= crit[c];
//
//                bua[0].hp -= d1 * (1 - bua[0].armadura / 100.0);
//                bua[1].hp -= d0 * (1 - bua[1].armadura / 100.0);
//
//                rsp.updateInt("id", i); //<--- esto que onda?
//                rsp.updateString("p1", bua[0].nombre);
//                rsp.updateString("p2", bua[1].nombre);
//                rsp.updateInt("d1", d0);
//                rsp.updateInt("d2", d1);
//                rsp.updateInt("hp1", bua[0].hp);
//                rsp.updateInt("hp2", bua[1].hp);
//                //rsp.updateBoolean(8, !(bua[0].hp > 0 && bua[1].hp > 0 ) );
//                rsp.insertRow();
//                //System.out.println(i + "\t" + bua[0].nombre + ": " + bua[0].hp + "\t" + bua[1].nombre + ":" + bua[1].hp);
//                i++;
//
//            }
//            
//            /*
//
//            rsp.updateInt("id", i);
//            rsp.updateString("p1", bua[0].nombre);
//            rsp.updateString("p2", bua[1].nombre);
//            rsp.updateInt("hp1", bua[0].hp);
//            rsp.updateInt("hp2", bua[1].hp);
//            //rsp.updateBoolean(8, true);
//            rsp.insertRow();
//            */
//
//            System.out.println("RESULTADO\t" + bua[0].nombre + ": " + bua[0].hp + "\t" + bua[1].nombre + ":" + bua[1].hp);
//
//        } catch (SQLException ex) {
//            // handle the error
//            System.out.println("kk");
//
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//
//        }
//    }
//
//    public void inicioPogo(Buacho[] bua) {
//        try {
//            rsp = sta.executeQuery("SELECT * FROM pogo");
//            
//            rsp.last();
//            int i = rsp.getInt("id") + 1;
//            
//            rsp.moveToInsertRow();
//            rsp.updateInt("id", i);
//            
//            rsp.updateString("p1", bua[0].nombre);
//            rsp.updateString("p2", bua[1].nombre);
//            rsp.updateInt("hp1", bua[0].hp);
//            rsp.updateInt("hp2", bua[1].hp);
//            //rsp.updateBoolean(8, true);
//            rsp.insertRow();
//
//        } catch (SQLException ex) {
//            // handle the error
//            System.out.println("kk");
//
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//
//        }
//    }
//
//    public Buacho[] elegirWachos() {
//
//        Buacho[] b = new Buacho[2];
//
//        try {
//
//            rs1 = sta.executeQuery(crearQueryW());
//            rs1.absolute(1);
//
//            Buacho b0 = new Buacho(rs1.getInt("id"), rs1.getString("nombre"), rs1.getInt("hp"), rs1.getInt("daño1"),
//                    rs1.getInt("velocidad1"), rs1.getInt("daño2"), rs1.getInt("velocidad2"), rs1.getInt("armadura"));
//            rs1.absolute(2);
//
//            Buacho b1 = new Buacho(rs1.getInt("id"), rs1.getString("nombre"), rs1.getInt("hp"), rs1.getInt("daño1"),
//                    rs1.getInt("velocidad1"), rs1.getInt("daño2"), rs1.getInt("velocidad2"), rs1.getInt("armadura"));
//
//            b[0] = b0;
//            b[1] = b1;
//            System.out.println(b[0].toString());
//            System.out.println(b[1].toString());
//            return b;
//
//            /*
//            System.out.println(b1.toString());
//            System.out.println(b2.toString());
//            
//            b1.nombre=rs1.getString("nombre");
//            b1.hp=rs1.getInt("hp");
//            b1.daño1=rs1.getInt("daño1");
//            b1.velocidad1=rs1.getInt("velocidad1");
//            b1.armadura=rs1.getInt("armadura");
//            b1.daño2=rs1.getInt("daño2");
//            b1.velocidad2=rs1.getInt("velocidad2");
//            
//             */
//            //rs1.absolute(2);
//        } catch (SQLException ex) {
//            // handle the error
//            System.out.println("kk");
//
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//            return b;
//
//        }
//
//    }
//
//    public String crearQueryW() {
//        int a;
//        int b;
//
//        do {
//            a = 1 + (int) (Math.random() * 6);
//            b = 1 + (int) (Math.random() * 6);
//            //System.out.println(a);
//            //System.out.println(b);
//        } while (a == b);
//
//        String q = "SELECT * FROM wachos WHERE id = '" + a + "' OR id = '" + b + "'";
//        return q;
//    }

