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
public class Krill_Plancton extends SerVivo
{
    public Krill_Plancton(int diaNacimiento) 
    {
        super(diaNacimiento);
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
    
    @Override
    public String toString() 
    {
        return "Krill/Plancton -> Fecha de nacimiento: Día " + diaNacimiento + "\n";
    }
    
}
