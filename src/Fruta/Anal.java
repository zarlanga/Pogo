/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruta;

import java.sql.SQLException;

/**
 *
 * @author Alumno
 */
public class Anal {

    Frutautil f;
//    int desdeID;

    Anal(Frutautil fru) {
        f = fru;

    }

    public String[] getCampos(String tabla) {
        String[] campos;

        try {
            f.rs1 = f.hacerQuery("SELECT * FROM " + tabla + " LIMIT 1");

            campos = new String[f.rs1.getMetaData().getColumnCount()];

            for (int i = 0; i < f.rs1.getMetaData().getColumnCount(); i++) {
                campos[i] = f.rs1.getMetaData().getColumnName(i+1);
            }
            
            return campos;
        } catch (SQLException ex) {
            System.out.println("tabla invalida?");
            
            System.out.println("SQLException: " + ex.getMessage());
        }
        campos=new String[0];
        return campos;
    }
    
    public Object[][] getMatriz(String tabla) {
        Object[][] matriz;
        try{
            f.rs1 = f.hacerQuery("SELECT * FROM " + tabla );
            f.rs1.last();
            matriz= new Object[f.rs1.getRow()][f.rs1.getMetaData().getColumnCount()];
            f.rs1.first();
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    matriz[i][j] = f.rs1.getString(j+1);
                }
                f.rs1.next();
            }
            
            return matriz;

        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
        matriz= new Object[0][0];
        return matriz;
    }
    
    public String[] getCamposQ (String query) {
        String[] campos;

        try {
            f.rs1 = f.hacerQuery("SELECT " + query + " LIMIT 1");

            campos = new String[f.rs1.getMetaData().getColumnCount()];

            for (int i = 0; i < f.rs1.getMetaData().getColumnCount(); i++) {
                campos[i] = f.rs1.getMetaData().getColumnName(i+1);
            }
            
            return campos;
        } catch (SQLException ex) {
            System.out.println("tabla invalida?");
            
            System.out.println("SQLException: " + ex.getMessage());
        }
        campos=new String[0];
        return campos;
    }
    
    public Object[][] getMatrizQ(String query) {
        Object[][] matriz;
        try{
            f.rs1 = f.hacerQuery("SELECT " + query );
            f.rs1.last();
            matriz= new Object[f.rs1.getRow()][f.rs1.getMetaData().getColumnCount()];
            f.rs1.first();
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    matriz[i][j] = f.rs1.getString(j+1);
                }
                f.rs1.next();
            }
            
            return matriz;

        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
        matriz= new Object[0][0];
        return matriz;
    }

    public void calcularPorc() {
        try {
            f.rs1 = f.hacerQuery("select * FROM analwachos");
            while (f.rs1.next()) {
                f.rs1.updateInt("porcV", 100 * f.rs1.getInt("victorias") / f.rs1.getInt("pogos"));
//                System.out.println(100 * f.rs1.getInt("victorias") / f.rs1.getInt("pogos"));
                f.rs1.updateInt("porcE", 100 * f.rs1.getInt("empates") / f.rs1.getInt("pogos"));
                f.rs1.updateInt("porcD", 100 * f.rs1.getInt("derrotas") / f.rs1.getInt("pogos"));
                f.rs1.updateRow();
//                System.out.println("kheondavatooo?");

            }
        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

    }

    public void limpiarVED() {
        try {

            f.rs1 = f.sta1.executeQuery("SELECT * FROM analwachos");

            while (f.rs1.next()) {
                //f.rs1.deleteRow();

                f.rs1.updateInt(3, 0);
                f.rs1.updateInt(4, 0);
                f.rs1.updateInt(5, 0);
                f.rs1.updateInt(6, 0);
                f.rs1.updateRow();
            }

        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
    }

    public void calcularVED(String idAsDes) {
        try {

            System.out.println("1");
            f.rsp = f.sta.executeQuery("SELECT p1, p2, hp1, hp2 FROM pogo WHERE id " + idAsDes + " AND (hp1 <= 0 OR hp2 <= 0)");
            System.out.println("2");
            f.rs1 = f.sta1.executeQuery("SELECT * FROM analwachos");
            System.out.println("3");
            while (f.rsp.next()) {
                f.rs1.beforeFirst();
                while (f.rs1.next()) {
                    int hp1 = f.rsp.getInt("hp1");
                    int hp2 = f.rsp.getInt("hp2");
                    if (f.rsp.getString("p1").equals(f.rs1.getString("nombre"))) {

                        f.rs1.updateInt("pogos", f.rs1.getInt("pogos") + 1);
                        if (hp1 <= 0 && hp2 <= 0) {
                            f.rs1.updateInt("empates", f.rs1.getInt("empates") + 1);
                        } else if (hp1 >= 0) {
                            f.rs1.updateInt("victorias", f.rs1.getInt("victorias") + 1);
                        } else {
                            f.rs1.updateInt("derrotas", f.rs1.getInt("derrotas") + 1);
                        }
                    }

                    if (f.rsp.getString("p2").equals(f.rs1.getString("nombre"))) {

                        f.rs1.updateInt("pogos", f.rs1.getInt("pogos") + 1);
                        if (hp1 <= 0 && hp2 <= 0) {
                            f.rs1.updateInt("empates", f.rs1.getInt("empates") + 1);
                        } else if (hp1 <= 0) {
                            f.rs1.updateInt("victorias", f.rs1.getInt("victorias") + 1);
                        } else {
                            f.rs1.updateInt("derrotas", f.rs1.getInt("derrotas") + 1);
                        }
                    }
                    f.rs1.updateRow();

                }
            }
            //f.rsp = f.sta.executeQuery("SELECT pogo.p1, pogo.p2, pogo.hp1, pogo.hp2, analwachos.id FROM pogo"
            //        + "JOIN analwachos on pogo.p1=analwachos.nombre WHERE id > 231 AND (hp1 < 0 OR hp2 < 0) ");

            //f.rsp = f.sta.executeQuery("SELECT pogo.p1, pogo.p2, pogo.hp1, pogo.hp2, analwachos.* FROM pogo "
            //        + " JOIN analwachos ON pogo.p1=analwachos.nombre WHERE pogo.id > 231 AND (pogo.hp1 < 0 OR pogo.hp2 < 0) ");
            //f.rsp.last();
            //f.rsp.updateInt("victorias", 2);
            //System.out.println(f.rsp.getRow()); f.rsp.getInt("victorias") + 1
        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

        //return f.rsp;
    }

    public void cargarBuachosAnal() {
        try {

            f.rs1 = f.sta.executeQuery("SELECT nombre, id FROM wachos");
            //System.out.println("cargobuachos");
            f.rs1.last();
            String[] nw = new String[f.rs1.getRow()];
            f.rs1.absolute(1);
            for (int i = 0; i < nw.length; i++) {
                nw[i] = f.rs1.getString("nombre");
                f.rs1.next();
            }
            f.rs1 = f.sta.executeQuery("SELECT * FROM analwachos");
            f.rs1.moveToInsertRow();
            for (int i = 0; i < nw.length; i++) {

                f.rs1.updateInt("id", i + 1);
                f.rs1.updateString("nombre", nw[i]);
                f.rs1.insertRow();
            }

        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

    }

}
