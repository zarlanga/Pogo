/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruta;

/**
 *
 * @author Alumno
 */
public class Buacho {

    private int id;
    private String nombre;
    private int hp;
    private int[] daño = new int[2];
    private int[] vel = new int[2];
//    private int daño1;
//    private int velocidad1;
//    private int daño2;
//    private int velocidad2;
    private double armadura;
    private static final double[] crit = {0.8, 0.9, 1, 1, 1.6};
    private Buacho riv;    

    public Buacho() {

    }

    public Buacho(int id, String nombre, int hp, int daño1, int velocidad1, int daño2, int velocidad2, int armadura) {
        this.id = id;
        this.nombre = nombre;
        this.hp = hp;
        this.daño[0] = daño1;
        this.daño[1] = daño2;
        this.vel[0]= velocidad1;
        this.vel[1]= velocidad2;
//        this.daño1 = daño1;
//        this.velocidad1 = velocidad1;
//        this.daño2 = daño2;
//        this.velocidad2 = velocidad2;
        this.armadura = armadura;
    }

    @Override
    public String toString() {
        return "Buacho{" + "id=" + id + ", nombre=" + nombre + ", hp=" + hp + ", da\u00f1o1=" + daño[0] + ", velocidad1=" + vel[0] + ", da\u00f1o2=" + daño[1] + ", velocidad2=" + vel[1] + ", armadura=" + armadura + '}';
    }

    public Buacho getRiv() {
        return riv;
    }

    public void setRiv(Buacho riv) {
        this.riv = riv;
    }
    
    
    
    public int darPalo(int i) {
        return (int) (daño[i] * crit[(int) (Math.random()*5) ]);
    }
//    public double darPalo1() {
//        return daño1 * crit[(int) (Math.random()*5) ];
//    }
//    public double darPalo2() {
//        return daño2 * crit[(int) (Math.random()*5) ];
//    }
    
    public void recibirPalo(double d) {
//        System.out.println("||||" + d + "||" + (d * (1 - armadura/100)));
        hp-= d * (1 - armadura/100.0);
    }
    
    public double getHP() {
        return hp;
    }
    
    public int[] getVel(){
        return this.vel; 
    }
    
//    public int getVel1(){
//        return velocidad1;
//    }
//    
//    public int getVel2(){
//        return velocidad2;
//    }

    public String getNombre() {
        return nombre;
    }

    public double getHp() {
        return hp;
    }
    
    

}

