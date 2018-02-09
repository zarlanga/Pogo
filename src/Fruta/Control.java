/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import vistas.MainMenu;
import vistas.Setup;
import vistas.Tablas;

/**
 *
 * @author Alumno
 */
public class Control {

    Frutautil f;
    Analisis a;
    Buacho[] buachos;
    int proximoID;
    boolean connST = false;
    MainMenu vM;
    Tablas vT;
    Setup vS;

    public Control() {
        this.f = new Frutautil(); //esto se hace asi?
        a = new Analisis(f);
        this.buachos = new Buacho[2];
        vM = new MainMenu();

    }

    public void iniciar() {

//        vM.setConnStatus();
        try {
            f.conectarse();
            connST = true;
            //proximoID = ultimoID() + 1;
            vM.iniciar(connST);
            System.out.println("entro " + connST);
        } catch (SQLException e) {
            vM.iniciar(connST);
            System.out.println("no entro " + connST);

        } finally {
            vM.addDaleHandler(new DaleHandler());
            vM.addTablasHandler(new TablasHandler());
            vM.addSetupHandler(new SetupHandler());
        }
    }

    public class TablasHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            vT = new Tablas(f);
            vT.iniciar();
            vT.addChoiceTablasListener(new CTHandler());
            vT.addEXQListener(new QueryHandler()); // hacerle que tire un mensaje si hay excepcion
            elegirTabla(vT.getChoiceTablas().getSelectedItem());
        }

    }

    public class QueryHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            elegirTablaQ(vT.getTextQ());
        }

    }

    public void elegirTablaQ(String query) {
        String[] campos = a.getCamposQ(query);
        Object[][] matriz = a.getMatrizQ(query); //new Object[campos.length][0]; 

        vT.mostrarTabla(campos, matriz);

    }

    public class CTHandler implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            elegirTabla(e.getItem().toString());
//         System.out.println(e.getItem().toString());
        }

    }

    public void elegirTabla(String tabla) {
        String[] campos = a.getCampos(tabla);
        Object[][] matriz = a.getMatriz(tabla); //new Object[campos.length][0]; 

        vT.mostrarTabla(campos, matriz);

    }

    public class DaleHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("laconchadetumadreallboyssss");
            proximoID = ultimoID() + 1;
            pogoRandom(vM.getCantidadDeVeces());

            //System.out.println("lamadredetuconchalboyysss");
        }

    }

    public class SetupHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            vS = new Setup();
            vS.iniciar();
            vS.setConnStatus(connST);
            vS.addConectarHandler(new sConectarHandler());
            vS.addCrearTablasHandler(new sCrearTablas());
            vS.addResetearPogosHandler(new sResetearPogos());
            vS.habilitarBotonCrearTablas(connST); // <---- hacer metodo para verificar
            vS.habilitarBotonResetearPogos(connST);
        }
    }

    public class sResetearPogos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            f.limpiarVED();
            f.resetearPogos();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public class sCrearTablas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            f.crearTablas();
            f.cargarPersonajes();
            f.cargarBuachosAnal();
            //proximoID = ultimoID() + 1; // esto tiraba error la primera vez? validar
            vM.habilitarBotones(true);

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public class sConectarHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                f.conectarse(vS.getText1(), vS.getText2(), vS.getText3());
                connST = true;

                vM.setConnStatus(connST);
                //   vM.habilitarBotones(connST);
                vS.setConnStatus(connST);
                vS.habilitarBotonCrearTablas(connST);
                vS.habilitarBotonResetearPogos(connST);

                
            
            } catch (SQLException ex) {
                System.out.println("no se pudo conectar");
            }
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

//alpedooooo vvvvvvv
    public void pogoRandom1() {
        elegirWachos();
        vM.elegirBuachos(buachos[0].getNombre(), buachos[1].getNombre(), (int) buachos[0].getHP(), (int) buachos[1].getHP());

    }

    public void pogoRandom2() {
        int idAnal = proximoID;

        saludo();
        bifes();
        a.calcularVED(">=" + idAnal); //zuper ineficiente
        a.calcularPorc();

    }

    public void pogoRandom() {
        int idAnal = proximoID;
        elegirWachos();

        vM.elegirBuachos(buachos[0].getNombre(), buachos[1].getNombre(), (int) buachos[0].getHP(), (int) buachos[1].getHP());

        saludo();
        bifes();
        a.calcularVED(">=" + idAnal); //zuper ineficiente
        a.calcularPorc();
    }
// alpedooooo ^^^^^^^^^^

    public void pogoRandom(int veces) {
        Thread t = new Thread() {
            @Override
            public void run() {
                int idAnal = proximoID;
                for (int i = 0; i < veces; i++) {
                    elegirWachos();

                    vM.elegirBuachos(buachos[0].getNombre(), buachos[1].getNombre(), (int) buachos[0].getHP(), (int) buachos[1].getHP());

                    saludo();
                    bifes();
                    vM.cambiarCantidadDeVeces(veces - 1 - i);
                }
                a.calcularVED(">=" + idAnal); //zuper ineficiente
                a.calcularPorc();
            }
        };
        t.start();
    }

    public void bifes() {
//        f.rsp=f.sta.executeQuery("SELECT * FROM pogo LIMIT 1");
        String testo = f.crearTestoValues("pogo");
//        System.out.println("testo:" + testo);
        vM.cambiarColorPersonajes(0);
        while (buachos[0].getHP() > 0 && buachos[1].getHP() > 0) {
            int[] da = new int[]{0, 0};
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (proximoID % buachos[i].getVel()[j] == 0) {
                        da[i] += buachos[i].darPalo(j);
//                        buachos[i].getRiv().recibirPalo(buachos[i].darPalo(j));
                    }

                }
                vM.setLabelText(da[i] + "", i);
                buachos[i].getRiv().recibirPalo(da[i]); // <------- esto no hace que cada uno reciba su propio daño? revisarrrrrrr
            }
            String elResto = proximoID + "', '" + buachos[0].getNombre() + "', '" + buachos[0].getHP() + "', '"
                    + da[0] + "', '" + da[1] + "', '" + buachos[1].getHP() + "', '" + buachos[1].getNombre()
                    + "');";
//            System.out.println(testo + elResto);
            f.mandarSQL(testo + elResto);
//            System.out.println(proximoID + "|" + buachos[0].getNombre() + "|" + buachos[0].getHP()
//                    + "|" + buachos[1].getHP() + "|" + buachos[1].getNombre());

            vM.setPB1Value((int) buachos[0].getHP());
            vM.setPB2Value((int) buachos[1].getHP());

            proximoID++;

            if (!vM.getTurbo()) {
                try {
                    System.out.println("proximoid" + proximoID);
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
            } else {
                try {
                    System.out.println("proximoid" + proximoID);
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }
            }

        }
        if (buachos[0].getHP() > 0) {
            vM.cambiarColorPersonajes(1);
        } else if (buachos[1].getHP() > 0) {
            vM.cambiarColorPersonajes(2);
        } else {
            vM.cambiarColorPersonajes(3);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }

    public void saludo() {
        String ins;
        try {
//            f.rsp = f.sta.executeQuery("SELECT * FROM pogo");////////////////////////
            ins = f.crearTestoValues("pogo");//////////////////
            ins += proximoID + "', '" + buachos[0].getNombre() + "', '" + buachos[0].getHP()
                    + "', null, null, '" + buachos[1].getHP() + "', '" + buachos[1].getNombre() + "');";

            //System.out.println(ins);
            f.sta.execute(ins);
            proximoID++;

        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
    }

    public void elegirWachos() {

        //Buacho[] b = new Buacho[2];
        try {

            //todo esto tendria que estar encapsulado??!?!?!?
            f.rs1 = f.sta.executeQuery(crearQueryW()); // ??????
            f.rs1.absolute(1);

            buachos[0] = new Buacho(f.rs1.getInt("id"), f.rs1.getString("nombre"), f.rs1.getInt("hp"), f.rs1.getInt("daño1"),
                    f.rs1.getInt("velocidad1"), f.rs1.getInt("daño2"), f.rs1.getInt("velocidad2"), f.rs1.getInt("armadura"));

            f.rs1.absolute(2);

            buachos[1] = new Buacho(f.rs1.getInt("id"), f.rs1.getString("nombre"), f.rs1.getInt("hp"), f.rs1.getInt("daño1"),
                    f.rs1.getInt("velocidad1"), f.rs1.getInt("daño2"), f.rs1.getInt("velocidad2"), f.rs1.getInt("armadura"));

            buachos[0].setRiv(buachos[1]);
            buachos[1].setRiv(buachos[0]);

            //b[0] = b0;
            //b[1] = b1;
//            System.out.println(this.buachos[0].toString());
//            System.out.println(this.buachos[1].toString());
        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

    }

    public String crearQueryW() {
        int a;
        int b;

        do {
            a = 1 + (int) (Math.random() * 6);
            b = 1 + (int) (Math.random() * 6);
            //System.out.println(a);
            //System.out.println(b);
        } while (a == b);

        String q = "SELECT * FROM wachos WHERE id = '" + a + "' OR id = '" + b + "'";
        return q;
    }

    public int ultimoID() {
        try {

            f.rsp = f.sta.executeQuery("SELECT id FROM pogo ORDER BY id DESC LIMIT 1");//dudoso
            if (f.rsp.next()) {

                return f.rsp.getInt(1);//dudoso
            } else {
                return 0;
            }

        } catch (SQLException ex) {
            // handle the error
            System.out.println("kk");

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("pogo vacio");
            return 0;
        }

    }
}
