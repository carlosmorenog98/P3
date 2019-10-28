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
public class OsoPolar extends SerVivo
{
    private int masa;
    
    public OsoPolar(int diaNacimiento, int masa) 
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
    
    public static Comparator<OsoPolar> comparador = new Comparator<OsoPolar>() {
        @Override
        public int compare(OsoPolar o, OsoPolar o1) {
            return (o.getMasa() - o1.getMasa());
        }
    };
    
}
