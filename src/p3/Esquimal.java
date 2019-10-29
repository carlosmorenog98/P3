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
public class Esquimal extends SerVivo
{
    private int masa;
    
    public Esquimal(int diaNacimiento, int masa) 
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
    
    @Override
    public String toString() 
    {
        return "Esquimal -> Fecha de nacimiento: Día " + diaNacimiento + ", Masa Muscular:" + masa + "\n";
    }
    
    public static Comparator<Esquimal> comparador = new Comparator<Esquimal>() {
        @Override
        public int compare(Esquimal e, Esquimal e1) {
            return (e.getMasa() - e1.getMasa());
        }
    };
}
