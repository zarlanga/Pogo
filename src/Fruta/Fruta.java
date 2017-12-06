/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruta;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 *
 * boton para conectar y para crear las tablas (y cargar los wachos) (vista aparte?) <-- on it
 *       conneccion (con input) <--- listo
 *      verificar que esten tablas creadas y los personajes cargados
 *      hacer un solo boton que resetee todo a lo pijo?
 *       crear tablas
 *       cargar wachos (en wachos y en anal) (mover todo a frutautil?)
 *
 *      textfield en tablas para hacer selects a medida
 *      (se puede hacer un add al choice que detecte las tablas / campos?
 * 
 * que muestre un nro del daño que hacen
 * revisar algoritamo de daño, las i y las j
 * 
 *
  * colores para el que gana o pierde?
 * 
 * 
 * checkbox para "espera" <------ listoooooooo
 * textfield o algo para cantidad de peleas <----istooooo 
 * *que al iniciar busque la tabla wachos (o muestre vacia) <---- listoooo
 * * bajar el countdown cuando hay muchos pogos juntos <---- ponele que esta
 * ver que onda con los threads y las progressbars <--vamolopibeeeee
 * hacer las vistas de las tablas <---- sale con fritassssss
 * borrar boolean de inicio fin (buscar d1 null) <---listo (formula) almacenar
 * victorias/derrotas/empates <---- listo
 **** tabla ANALWACHOS y su trigger <----- me chupa la pija vo y tu trigger, ponele que la tabla maomeno esta
 ******** como se moludariza Frutautil? <------ maomenooooooo
 * calcular porcentajes de victorias derrotas empates <----- listooooooo
 * averiguar como hacer que un dato sea dinamico <----- triggers, pajaaaaaa 
 * como van las distintas  clases? donde las pongo? <----- medio cavernicola mi mvc, pero poneeeeele
 * 
 * 
 ***** CRIT con mayuscula
 ***** valores FINAL en BUACHOS
 ***** tabla ANALPOGO y su trigger <----- mghhhhhhhhhhhh
 *
 * hacer una tabla de combinatorias de personajes? 
 * analpogo porcentaes de daño, longitud de peleas
 *
 *
 * 
 * 
 * 
 * healing? SELECT wachos.*, pogo.p1, pogo.hp1, pogo.hp2,
 * pogo.p2 FROM `pogo` JOIN wachos ON pogo.p1 = wachos.nombre WHERE (pogo.hp1 <0
 * or pogo.hp2 <0) AND pogo.id
 *
 *
 * > 231 AND (pogo.d1 >0 or pogo.d2 >0) <---- crit 1.4 566 <--- crit 2 1128
 * <---- crit 1.8 1685	<----- crit 1.6 2188 <----- sigue1.6?
 *
 * @author Alumno
 */
public class Fruta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
//        Frutautil f = new Frutautil();
//        f.conectarse();
//        f.mandarSQL("CREATE TABLE analwachos (id int(20) PRIMARY KEY, nombre VARCHAR(20), victorias int (20), empates int (20), derrotas int (20) , pogos int (20), porcV int(20), porcE int(20), porcD int(20));");


//        f.mandarSQL("DELETE FROM pogo");
      
        Pogo p = new Pogo();
        p.iniciar();
//        p.pogoRandom1();
//        p.pogoRandom2();
//        p.pogoRandom(50);
//////        p.pogoRandom();
        

//        Anal a = new Anal(f);
//        
//        a.cargarBuachosAnal();
//        a.calcularVED(">= 1");//    74
//        a.calcularPorc();
//        batata(f);

    }

    static void batata(Frutautil f) {

        try {
            ResultSet rs;
//            rs = f.sta1.executeQuery("SELECT * FROM pogo");
//            recorrerResultSet1(rs);
//            
//            rs = f.sta1.executeQuery("SELECT * FROM wachos");
//            recorrerResultSet1(rs);
             rs = f.sta1.executeQuery("SELECT * FROM analwachos");
            recorrerResultSet1(rs);
            
           

           } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

    }

    



    static String randomizarPalabra(int a, int b) {
        String palabra = "";
        char[] vocales = {'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < a + Math.random() * (b - a); i++) {
            int x;
            if (i == 0) {
                x = 65 + (int) (Math.random() * 26);
            } else if (i % (int) (1 + Math.random() * 3) == 0) {
                x = vocales[(int) (vocales.length * Math.random())];
                //System.out.println("--");
            } else {
                x = (97 + (int) (Math.random() * 26));
            }

            palabra += (char) x;
        }
        return palabra;
    }

    

    static void recorrerResultSet1(ResultSet rese) {

        try {
            for (int i = 1; i <= rese.getMetaData().getColumnCount(); i++) {
                System.out.print(rese.getMetaData().getColumnName(i) + "\t | ");
            }
            System.out.println("");

            while (rese.next()) {

                for (int i = 1; i <= rese.getMetaData().getColumnCount(); i++) {
                    System.out.print(rese.getString(i) + "\t");
                }
                System.out.println("");
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
