/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Especies;

import pandorum_jg.Planeta;
import Utils.Posicio;

/**
 *
 * @author Joan Gerard Camarena
 */
public class Avatar extends Habitant {

    public Avatar(char sexe, boolean viu, Planeta elPlaneta, Posicio p) {
        super(sexe, viu, elPlaneta, p);
    }

    public void moure(){

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

    private void moureSud(){
        Posicio novaPosicio = new Posicio();
        novaPosicio.setX(laPosicio.getX()-2);
        setCostat(novaPosicio);
        elPlaneta.moure(this,laPosicio,novaPosicio);
    }
    private void moureNord(){
        Posicio novaPosicio = new Posicio();
        novaPosicio.setX(laPosicio.getX()+2);
        setCostat(novaPosicio);
        elPlaneta.moure(this,laPosicio,novaPosicio);
    }
    private void moureEst(){
        Posicio novaPosicio = new Posicio();
        novaPosicio.setY(laPosicio.getY()+2);
        setCostat(novaPosicio);
        elPlaneta.moure(this,laPosicio,novaPosicio);
    }
    private void moureOest(){
        Posicio novaPosicio = new Posicio();
        novaPosicio.setY(laPosicio.getY()-2);
        setCostat(novaPosicio);
        elPlaneta.moure(this,laPosicio,novaPosicio);
    }

    private void setCostat(Posicio pos){
        int costat = r.nextInt(2);
        switch (costat){
            case 0:{
                pos.setY(laPosicio.getY()-1);
                break;
            }
            case 1:{
                pos.setY(laPosicio.getY()+1);
            }
        }
    }
}
