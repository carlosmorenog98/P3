/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3;

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
    
    public boolean comer(int comer)
    {
        int prob = numeroAleatorio(1,1000);                           //Muerte por causas naturales
        
        if(prob <= comer){
            return false;
        }
        else
        {
            return true;
        }
    }
    
}
