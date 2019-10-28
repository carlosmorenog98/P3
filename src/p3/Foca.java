/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3;

import java.util.Comparator;

/**
 *
 * @author moren
 */
public class Foca extends SerVivo
{
    private int masa;
    
    public Foca(int diaNacimiento, int masa) 
    {
        super(diaNacimiento);
        this.masa = masa;
    }
    
    public int getMasa()
    {
        return masa;
    }
    
    public int comer(int min, int max)
    {
        int prob = numeroAleatorio(min,max);                           //Muerte por causas naturales
        
        return prob;
    }
    
    public static Comparator<Foca> comparador = new Comparator<Foca>() {
        @Override
        public int compare(Foca f, Foca f1) {
            return (f.getMasa() - f1.getMasa());
        }
    };
}
