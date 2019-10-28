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
public class Morsa extends SerVivo
{
    private int masa;
    
    public Morsa(int diaNacimiento, int masa) 
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
    
    public static Comparator<Morsa> comparador = new Comparator<Morsa>() {
        @Override
        public int compare(Morsa m, Morsa m1) {
            return (m.getMasa() - m1.getMasa());
        }
    };
    
}
