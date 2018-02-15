/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pandorum_jg;

import Utils.Posicio;
import Especies.Habitant;
import Especies.Avatar;
import Especies.Huma;
import java.util.Random;

/**
 *
 * @author Joan Gerard Camarena
 */
public class Planeta {

    private static Planeta elPlaneta = null;
    private Habitant[][] elTauler;
    private boolean menejant_se;
    private int TAM = Pandorum_JG.TAM;
    private Random r;

    public static Planeta getPlaneta() {
        if (elPlaneta == null) {
            elPlaneta = new Planeta();
        }
        return elPlaneta;
    }

    private Planeta() {
        elTauler = new Habitant[TAM][TAM];
        menejant_se = false;
        r = new Random(System.currentTimeMillis());
    }

    public synchronized void omplirPlaneta(int av, int hum) {

        int fila, col;
        Habitant h;

        //posem els avatars
        for (int i = 0; i < av; i++) {
            do {
                fila = r.nextInt(TAM);
                col = r.nextInt(TAM);
            } while (elTauler[fila][col] != null);

            if (i < av / 2) {
                h = new Avatar('H', true, elPlaneta, new Posicio(fila, col));
                setHabitantAt(h);
                System.out.println(h);
            } else {
                h = new Avatar('F', true, elPlaneta, new Posicio(fila, col));
                setHabitantAt(h);
                System.out.println(h);
            }
        }
        //posem els humans
        for (int i = 0; i < hum; i++) {
            do {
                fila = r.nextInt(TAM);
                col = r.nextInt(TAM);
            } while (elTauler[fila][col] != null);

            if (i < hum / 2) {
                h = new Huma('H', true, elPlaneta, new Posicio(fila, col));
                setHabitantAt(h);
                System.out.println(h);
            } else {
                h = new Huma('F', true, elPlaneta, new Posicio(fila, col));
                setHabitantAt(h);
                System.out.println(h);
            }
        }
    }

    public synchronized void iniciarPlaneta() {
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                if (elTauler[i][j] != null) {
                    elTauler[i][j].start();
                }
            }
        }
        menejant_se = true;
    }

    
    public synchronized void aturarPlaneta() {
        menejant_se = false;
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++) {
                if (elTauler[i][j] != null) {
                    elTauler[i][j].aturar();
                }
            }
        }
    }

    public synchronized boolean isMenejant_se() {
        return menejant_se;
    }

    public synchronized void setMenejant_se(boolean menejant_se) {
        this.menejant_se = menejant_se;
    }

    public synchronized Habitant getHabitantAt(Posicio p) {
        int i = p.getX();
        int j = p.getY();

        if (i < 0 || i >= TAM || j < 0 || j >= TAM) {
            return null;
        } else {
            return elTauler[i][j];
        }
    }

    public synchronized void setHabitantAt(Habitant h) {
        elTauler[h.getPosicio().getX()][h.getPosicio().getY()] = h;
    }

    /**
     * Desplaça a l'habitant h des de la posició posOritge a posDesti
     * @param h
     * @param posOritge
     * @param posDesti 
     */
    public synchronized void moure(Habitant h, Posicio posOritge, Posicio posDesti) {

        Habitant habitantDesti = elTauler[Math.floorMod(posDesti.getX(), TAM)][Math.floorMod(posDesti.getY(), TAM)];
        if(habitantDesti != null){
            if(habitantDesti instanceof Huma){
                if(h instanceof Huma){
                    if(h.getSexe() == habitantDesti.getSexe()){
                        elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)] = null;
                        elTauler[Math.floorMod(posOritge.getX(),TAM)][Math.floorMod(posOritge.getY(),TAM)] = null;
                        h.aturar();
                        habitantDesti.aturar();
                    }else{
                        elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)] = null;
                        elTauler[Math.floorMod(posOritge.getX(),TAM)][Math.floorMod(posOritge.getY(),TAM)] = null;
                        h.aturar();
                        habitantDesti.aturar();
                        elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)] = new Huma(r.nextInt(2) == 0 ? 'H' : 'F', true,this,posDesti);
                        elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)].start();
                    }
                }
                else if(h instanceof Avatar){
                    elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)] = h;
                    habitantDesti.aturar();
                }
            }
            else if(habitantDesti instanceof Avatar){
                if(h instanceof Huma){
                    elTauler[Math.floorMod(posOritge.getX(),TAM)][Math.floorMod(posOritge.getY(),TAM)] = null;
                    h.aturar();
                }
                else{
                    if(h.getSexe() == habitantDesti.getSexe()){
                        elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)] = null;
                        elTauler[Math.floorMod(posOritge.getX(),TAM)][Math.floorMod(posOritge.getY(),TAM)] = null;
                        h.aturar();
                        habitantDesti.aturar();
                    }else{
                        elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)] = null;
                        elTauler[Math.floorMod(posOritge.getX(),TAM)][Math.floorMod(posOritge.getY(),TAM)] = null;
                        h.aturar();
                        habitantDesti.aturar();
                        elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)] = new Avatar(r.nextInt(2) == 0 ? 'H' : 'F', true,this,posDesti);
                        elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)].start();
                    }
                }
            }
        }else{
            elTauler[Math.floorMod(posDesti.getX(),TAM)][Math.floorMod(posDesti.getY(),TAM)] = h;
            elTauler[Math.floorMod(posOritge.getX(),TAM)][Math.floorMod(posOritge.getY(),TAM)] = null;
        }
    }
}
