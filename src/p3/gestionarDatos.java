/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author moren
 */
public class gestionarDatos 
{
    public void guardarDatos(Simulacion s) 
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("config.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s);
            oos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public Simulacion cargarDatos() 
    {
        Simulacion s = new Simulacion();
        try
        {
            FileInputStream fis = new FileInputStream("config.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s = (Simulacion) ois.readObject();
            ois.close(); 
            fis.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return s;
    }
}
