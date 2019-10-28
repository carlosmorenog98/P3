/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author moren
 */
public abstract class SerVivo implements Serializable
{
    protected int diaNacimiento;

    public SerVivo(int diaNacimiento) 
    {
        this.diaNacimiento = diaNacimiento;
    }

    public int getDiaNacimiento() 
    {
        return diaNacimiento;
    }

    public void setDiaNacimiento(int diaNacimiento) 
    {
        this.diaNacimiento = diaNacimiento;
    }
    
    public boolean reproducirse(int nacer)
    {
        int prob = numeroAleatorio(1,1000);
        
        if(prob <= nacer){
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean morir(int morir)
    {
        int prob = numeroAleatorio(1,1000);
        
        if(prob <= morir){
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Función que genera un numero aleatorio entre un minimo y un maximo
     * @param min el minimo numero que puede salir
     * @param max el maximo numero que puede salir
     * @return el numero aleatorio generado
     */
    public int numeroAleatorio(int min, int max)
    {
        max = max + 1;
        return ((int) (Math.random() * (max - min) + min));
    }
}
