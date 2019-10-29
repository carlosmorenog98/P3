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
public class Pez extends SerVivo
{
    private int masa;
    private int num;
    private String tipo = "";
    
    public Pez(int diaNacimiento, int masa) 
    {
        super(diaNacimiento);
        this.masa = masa;
        num = numeroAleatorio(1,100);
        if(num < 33)
        {
            tipo = "bacalao";
        }
        else if(num > 33 && num < 66)
        {
            tipo = "raya";
        }
        else
        {
            tipo = "merluza negra";
        }
    }
    
    public int getMasa()
    {
        return masa;
    }
    
    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }
    
    public String geTipo()
    {
        return tipo;
    }
    
    public int comer(int min, int max)
    {
        int prob = numeroAleatorio(min,max);                           //Muerte por causas naturales
        
        return prob;
    }
    
    @Override
    public String toString() 
    {
        return "Pez -> Fecha de nacimiento: Día " + diaNacimiento + ", Masa Muscular:" + masa + "\nTipo: " + tipo + "\n";
    }
    
    public static Comparator<Pez> comparador = new Comparator<Pez>() 
    {
        @Override
        public int compare(Pez p, Pez p1) {
            return (p.getMasa() - p1.getMasa());
        }
    };
    
}
