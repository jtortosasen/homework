/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Especies;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import pandorum_jg.Pandorum_JG;
import pandorum_jg.Planeta;
import Utils.Posicio;
import static pandorum_jg.Pandorum_JG.TAM;

/**
 *
 * @author Joan Gerard Camarena
 */
public abstract class Habitant 
extends Thread{
    
    protected char sexe;            // com soc?
    protected boolean viu;          // com estic?
    protected Planeta elPlaneta;    // el meu planeta
    protected Posicio laPosicio;    // casella on estic a un moment donat
    protected Random r;             // generador d'aleatoris
    protected int TAM=Pandorum_JG.TAM;  // tamany del planeta

    public Habitant(char sexe, boolean viu, Planeta elPlaneta,Posicio p) {

        this.sexe = sexe;
        this.viu = viu;
        this.elPlaneta = elPlaneta;
        this.laPosicio=p;
        this.r=new Random(System.currentTimeMillis()+Thread.currentThread().getId());
    }

    public boolean isViu() {
        return viu;
    }

    public char getSexe() {
        return sexe;
    }

    public Posicio getPosicio() {
        return laPosicio;
    }

    public void setPosicio(Posicio laPosicio) {
        this.laPosicio = laPosicio;
    }
    
    public void aturar(){
        viu=false;
        System.out.println(getName() + " aturat.");
    }
    
    public void run(){
        // mentre estic viu he de menejar-me pel planeta
        while(viu){
            
            // sols em moure si el planeta ho permet
            if (elPlaneta.isMenejant_se())
                moure();
            
            // espere 200ms entre moviments
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Habitant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * En aquest mètode he de decidir on vull anar i anar-hi
     * Bàsicament és sortejar la posició on aniré
     * i canviar la meua posició per la nova
     */
<<<<<<< HEAD

    public void moure(){
        // COMPLETAR
=======
    public void moure(){
>>>>>>> d2d6cff6d87d270f879a5580d95cd53327ed9fcd
        int moviment = r.nextInt(4);
        switch (moviment){
            case 0:{
                moureSud();
                break;
            }
            case 1:{
                moureNord();
                break;
            }
            case 2:{
                moureEst();
                break;
            }
            case 3:{
                moureOest();
                break;
            }
        }
    }

    public abstract void moureSud();
    public abstract void moureNord();
    public abstract void moureEst();
    public abstract void moureOest();

    public String toString(){
        
        String res="";
        if (this instanceof Avatar)
            res+="Avatar";
        else
            res+="Huma";
        res+="-" + getName() + " en ";
        res+="("+ laPosicio.getX() + ", "+ laPosicio.getY()+")";
        
        return res;
    }
}
