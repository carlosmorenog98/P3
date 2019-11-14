/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;

/**
 *
 * @author moren
 */

public class Simulacion implements Serializable
{
    private boolean buques = false;
    private boolean calentamiento = false;
    private int num;
    private int dia;
    private float temperatura;
    private final ArrayList<Esquimal> esquimales = new ArrayList<>();
    private final ArrayList<OsoPolar> osos = new ArrayList<>();
    private final ArrayList<Morsa> morsas = new ArrayList<>();
    private final ArrayList<Foca> focas = new ArrayList<>();
    private final ArrayList<Pez> peces = new ArrayList<>();
    private final ArrayList<Krill_Plancton> krill_plancton = new ArrayList<>();
    
    public Simulacion()
    {}

    public void crearEntorno()
    {
        dia = 0;
        temperatura = 4;
        esquimales.clear();
        osos.clear();
        morsas.clear();
        focas.clear();
        peces.clear();
        krill_plancton.clear();
        System.out.println("Creamos los esquimales");
        //Creamos todos los esquimales
        num = this.numeroAleatorio(12, 14);
        for(int i = 0; i < num; i++)
        {
            Esquimal e = new Esquimal(dia, numeroAleatorio(35,48));
            esquimales.add(e);
        }
        
        System.out.println("Creamos los osos");
        //Creamos todos los osos polares
        num = this.numeroAleatorio(22, 28);
        for(int i = 0; i < num; i++)
        {
            OsoPolar op = new OsoPolar(dia, numeroAleatorio(40,55));
            osos.add(op);
        }
        
        System.out.println("Creamos las morsas");
        //Creamos todas las morsas
        num = this.numeroAleatorio(42, 48);
        for(int i = 0; i < num; i++)
        {
            Morsa m = new Morsa(dia, numeroAleatorio(30,42));
            morsas.add(m);
        }
        
        System.out.println("Creamos las focas");
        //Creamos todas las focas
        num = this.numeroAleatorio(260, 290);
        for(int i = 0; i < num; i++)
        {
            Foca f = new Foca(dia, numeroAleatorio(25,32));
            focas.add(f);
        }
        
        System.out.println("Creamos los peces");
        //Creamos todos los peces
        num = this.numeroAleatorio(7000, 8000);
        for(int i = 0; i < num; i++)
        {
            Pez p = new Pez(dia, numeroAleatorio(55,70));
            peces.add(p);
        }
        
        System.out.println("Krill");
        //Creamos todos los krill y Plancton
        num = this.numeroAleatorio(65, 75);
        long valor = num /* * 1000000000*/; 
        System.out.println(valor);
        //Aqui peta y no pilla bien el numero
        for(int i = 0; i < valor; i++)
        {
            Krill_Plancton kp = new Krill_Plancton(dia);
            krill_plancton.add(kp);
        }
        System.out.println("Ordenamos los vectores");
        System.out.println(krill_plancton.size());
        Collections.sort(esquimales,Esquimal.comparador);
        Collections.sort(osos,OsoPolar.comparador);
        Collections.sort(morsas,Morsa.comparador);
        Collections.sort(focas,Foca.comparador);
        Collections.sort(peces,Pez.comparador);
    }
    /**
     * Todo lo que pasa en 1 dia
     */
    private void eventosDia(boolean buques)
    {
        System.out.println("Día: " + dia);
        this.cambioTemperaturaDiaria();
        this.eventosEsquimales();
        this.eventosOsos(buques);
        this.eventosMorsas(buques);
        this.eventosFocas();
        this.eventosPeces();
        this.eventosKrillPlancton();
    }
    
    private void eventosEsquimales()
    {
        ArrayList<Pez> peces2 = new ArrayList<>();
        ArrayList<Esquimal> esquimales2 = new ArrayList<>();
        //Acciones Realizadas por los Esquimales
        ListIterator it = esquimales.listIterator();
        while(it.hasNext())
        {
            boolean muerto = false;
            Esquimal e = (Esquimal) it.next();
            
            int comer1 = e.comer(2,4);
            int comer2 = e.comer(0,1);
            
            if(peces.size() - comer1 >= 0)
            {
                for(int i = 0; i < comer1; i++)
                {
                    peces2.add(peces.get(i));
                }
            }
            else
            {
                peces2.addAll(peces);
            }

            if(focas.size() - comer2 >= 0)
            {
                for(int i = 0; i < comer2; i++)
                {
                    focas.remove(i);
                }
            }
            else
            {
                focas.removeAll(focas);
            }
            
            if(e.morir(24))
            {
                //O aqui
               esquimales.remove(esquimales.listIterator(0));
               muerto = true;
            }
            else if(!e.morir(24) && (peces.size() == 0 && focas.size() == 0))
            {
                muerto = true;
                esquimales.remove(esquimales.listIterator(0));
            }
            if(muerto == false)
            {
                boolean hijos = e.reproducirse(32);
                if(hijos = true)
                {
                    //Aqui peta por completo
                   esquimales2.add(new Esquimal(dia, numeroAleatorio(35,48)));
                }
            }
        }
        peces.removeAll(peces2);
        esquimales.addAll(esquimales2);
        Collections.sort(esquimales,Esquimal.comparador);
        Collections.sort(peces,Pez.comparador);
        Collections.sort(focas,Foca.comparador);
        System.out.println("Hola Esquimal");
    }
    
    private void eventosOsos(boolean buques)
    {
        ArrayList<OsoPolar> osos2 = new ArrayList<>();
        //Acciones Realizadas por los Osos
        ListIterator it = osos.listIterator();
        while(it.hasNext())
        {
            OsoPolar o = (OsoPolar) it.next();
            
            int comer1 = o.comer(1,2);
            int comer2 = o.comer(10,15);
            
            if(focas.size() - comer1 >= 0)
            {
                for(int i = 0; i < comer1; i++)
                {
                    focas.remove(i);
                }
            }
            else
            {
                focas.removeAll(focas);
            }
            
            if(peces.size() - comer2 >= 0)
            {
                for(int i = 0; i < comer2; i++)
                {
                    peces.remove(i);
                }
            }
            else
            {
                peces.removeAll(peces);
            }
            
            if(buques == false)
            {
                if(o.morir(95))
                {
                   osos.remove(osos.listIterator(0));
                }
                else if(!o.morir(95) && (focas.size() == 0 && peces.size() == 0))
                {
                    osos.remove(osos.listIterator(0));
                }
            }
            else
            {
                if(o.morir(150))
                {
                    osos.remove(osos.listIterator(0));
                }
                else if(!o.morir(150) && (focas.size() == 0 && peces.size() == 0))
                {
                    osos.remove(osos.listIterator(0));
                }
            }
            
            boolean hijos = o.reproducirse(153);
            if(hijos == true)
            {
               osos2.add(new OsoPolar(dia, numeroAleatorio(40,55)));
            }
        }
        osos.addAll(osos2);
        Collections.sort(osos,OsoPolar.comparador);
        Collections.sort(focas,Foca.comparador);
        Collections.sort(peces,Pez.comparador);
        System.out.println("Hola Oso");
    }
    
    private void eventosMorsas(boolean buques)
    {
        ArrayList<Morsa> morsas2 = new ArrayList<>();
        //Acciones Realizadas por las Morsas
        ListIterator it = morsas.listIterator();
        while(it.hasNext())
        {
            boolean muerto = false;
            try {
                Morsa m = (Morsa) it.next();
            
                int comer1 = m.comer(1,2);
                int comer2 = m.comer(0,2);

                if(focas.size() - comer1 >= 0)
                {
                    for(int i = 0; i < comer1; i++)
                    {
                        focas.remove(i);
                    }
                }
                else
                {
                    focas.removeAll(focas);
                }
                
                if(comer2 != 0)
                {
                    if(osos.size() - comer2 >= 0)
                    {
                        for(int i = 0; i < comer2; i++)
                        {
                            osos.remove(i);
                        }
                    }
                    else
                    {
                        osos.removeAll(osos);
                    }
                }            

                if(buques == false)
                {
                    if(m.morir(95))
                    {
                       morsas.remove(morsas.listIterator(0));
                       muerto = true;
                    }
                    else if(!m.morir(95) && (focas.size() == 0 && peces.size() == 0))
                    {
                        morsas.remove(morsas.listIterator(0));
                        muerto = true;
                    }
                }
                else
                {
                    if(m.morir(200))
                    {
                        morsas.remove(morsas.listIterator(0));
                        muerto = true;
                    }
                    else if(!m.morir(200) && (focas.size() == 0 && osos.size() == 0))
                    {
                        morsas.remove(morsas.listIterator(0));
                        muerto = true;
                    }
                }
                if(muerto == false)
                {
                    boolean hijos = m.reproducirse(98);
                    if(hijos == true)
                    {
                       morsas2.add(new Morsa(dia, numeroAleatorio(30,42)));
                    }
                }
            } 
            catch (ConcurrentModificationException e) 
            {
                it = morsas.listIterator(it.previousIndex());
                Morsa m = (Morsa) it.next();
            
                int comer1 = m.comer(1,2);
                int comer2 = m.comer(0,2);

                if(focas.size() - comer1 >= 0)
                {
                    for(int i = 0; i < comer1; i++)
                    {
                        focas.remove(i);
                    }
                }
                else
                {
                    focas.removeAll(focas);
                }
                
                if(comer2 != 0)
                {
                    if(osos.size() - comer2 >= 0)
                    {
                        for(int i = 0; i < comer2; i++)
                        {
                            osos.remove(i);
                        }
                    }
                    else
                    {
                        osos.removeAll(osos);
                    }
                }            

                if(buques == false)
                {
                    if(m.morir(95))
                    {
                       morsas.remove(morsas.listIterator(0));
                    }
                    else if(!m.morir(24) && (focas.size() == 0 && osos.size() == 0))
                    {
                        morsas.remove(morsas.listIterator(0));
                    }
                }
                else
                {
                    if(m.morir(200))
                    {
                        morsas.remove(morsas.listIterator(0));
                    }
                    else if(!m.morir(24) && (focas.size() == 0 && osos.size() == 0))
                    {
                        morsas.remove(morsas.listIterator(0));
                    }
                }
                
                boolean hijos = m.reproducirse(98);
                if(hijos == true)
                {
                   morsas2.add(new Morsa(dia, numeroAleatorio(30,42)));
                }
            }
        }
        morsas.addAll(morsas2);
        Collections.sort(morsas,Morsa.comparador);
        Collections.sort(focas,Foca.comparador);
        Collections.sort(osos,OsoPolar.comparador);
        System.out.println("Hola Morsa");
    }
    
    private void eventosFocas()
    {
        boolean muerto = false;
        ArrayList<Foca> focas2 = new ArrayList<>();
        //Acciones Realizadas por las Focas
        ListIterator it = focas.listIterator();
        while(it.hasNext())
        {
            Foca f = (Foca) it.next();
            
            int comer = f.comer(15,20);
            
            for(int i = 0; i < comer; i++)
            {
                peces.remove(i);
            }           
            
            if(f.morir(90))
            {
               focas.remove(focas.listIterator(0));
               muerto = true;
            }
            else if(!f.morir(90) && (peces.size() == 0))
            {
                focas.remove(focas.listIterator(0));
                muerto = true;
            }
            
            if(muerto == false)
            {
                boolean hijos = f.reproducirse(100);
                if(hijos == true)
                {
                   focas2.add(new Foca(dia, numeroAleatorio(25,32)));
                }
            }
        }
        focas.addAll(focas2);
        Collections.sort(focas,Foca.comparador);
        Collections.sort(peces,Pez.comparador);
        System.out.println("Hola Foca");
    }
    
    private void eventosPeces()
    {
        boolean muerto = false;
        ArrayList<Krill_Plancton> krill2 = new ArrayList<>();
        ArrayList<Pez> peces2 = new ArrayList<>();
        //Acciones Realizadas por los peces
        ListIterator it = peces.listIterator();
        while(it.hasNext())
        {
            try {
                Pez p = (Pez) it.next();
            
                int comer = p.comer(1,2);
                long num2 = comer/* * 1000000000*/;

                for(int i = 0; i < num2; i++)
                {
                    //System.out.println("Eliminado");
                    krill2.add(krill_plancton.get(i));
                }           

                if(p.morir(163))
                {
                  peces.remove(peces.listIterator(0));
                }
                else if(!p.morir(163) && (krill_plancton.size() == 0))
                {
                    peces.remove(peces.listIterator(0));
                }
                
                boolean hijos = p.reproducirse(185);
                if(hijos == true)
                {
                   Pez p2 = new Pez(dia, numeroAleatorio(55,70));
                   p2.setTipo(p.geTipo());
                   peces2.add(p2);
                }
            } catch (ConcurrentModificationException e) {
                it = peces.listIterator(it.previousIndex());
                Pez p = (Pez) it.next();
            
                int comer = p.comer(1,2);
                long num2 = comer/* * 1000000000*/;

                for(int i = 0; i < num2; i++)
                {
                    //System.out.println("Eliminado");
                    krill2.add(krill_plancton.get(i));
                }           

                if(p.morir(163))
                {
                  peces.remove(peces.listIterator(0));
                  muerto = true;
                }
                else if(!p.morir(163) && (krill_plancton.size() == 0))
                {
                    peces.remove(peces.listIterator(0));
                    muerto = true;
                }
                
                if(muerto == false)
                {
                    boolean hijos = p.reproducirse(185);
                    if(hijos == true)
                    {
                       Pez p2 = new Pez(dia, numeroAleatorio(55,70));
                       p2.setTipo(p.geTipo());
                       peces2.add(p2);
                    }
                }
            }
            
        }
        krill_plancton.removeAll(krill2);
        peces.addAll(peces2);
        Collections.sort(peces,Pez.comparador);
        System.out.println("Hola Pez");
    }
    
    private void eventosKrillPlancton()
    {
        if(temperatura >= 5.5 || temperatura < 3)
        {
        }
        else if(temperatura > 3 && temperatura < 4)
        {
            long num2 = 18000 /** 1000000000*/;
            for(int i = 0; i < num2; i++)
            {
                Krill_Plancton kp2 = new Krill_Plancton(dia);
                krill_plancton.add(kp2);
            }
        }
        else if(temperatura > 4 && temperatura < 5)
        {
            long num2 = 22000/* * 1000000000*/;
            for(int i = 0; i < num2; i++)
            {
                Krill_Plancton kp2 = new Krill_Plancton(dia);
                krill_plancton.add(kp2);
            }
        }
        else if(temperatura > 5 && temperatura < 5.5)
        {
            long num2 = 12000 /* * 1000000000*/;
            for(int i = 0; i < num2; i++)
            {
                Krill_Plancton kp2 = new Krill_Plancton(dia);
                krill_plancton.add(kp2);
            }
        }
        System.out.println("Hola Krill");
    }
    
    /**
     * Funcion que hace transcurrir d dias en el simulador
     * @param d cantidad de dias que deben pasar
     */
    public void transcurrirDia(int d){
        calentamiento = false;
        buques = false;
        
        for(int i = 0; i < d; i++){
            dia++;
            eventosDia(buques);
        }
    }
    /**
     * Función que genera un numero aleatorio entre un minimo y un maximo
     * @param min el minimo numero que puede salir
     * @param max el maximo numero que puede salir
     * @return el numero aleatorio generado
     */
    public int numeroAleatorio(int min, int max){
        //Random r1 = new Random(System.currentTimeMillis());
        max = max + 1;
        return ((int) (Math.random() * (max - min) + min));
    }
    
    /**
     * Funcion toString sobrecargada
     * @return Como se debe mostrar la clase simulacion
     */
    @Override
    public String toString() {
        return "Dia: " + dia + "\nTemperatura: " + temperatura + "\nEsquimales: " + esquimales.size() + "\nOsos Polares: " + osos.size() + "\nMorsas: " + morsas.size() + "\nFocas: " + focas.size() + "\nPeces: " + peces.size() + "\nKrill y Plancton: " + krill_plancton.size();
    }
    
    /**
     * Funcion que calcula la variacion de la temperatura cada dia
     */
    private void cambioTemperaturaDiaria() 
    {
        int prob = numeroAleatorio(1,100);
        
        if(temperatura >= 5)
        {
            if(prob<45)
            {
                temperatura += 0.2;
            }
            else
            {
                temperatura -= 0.2;
            }
        }
        else if (temperatura < 5 && temperatura > 3)
        {
            if(prob < 5)
            {
                temperatura = temperatura; //no se modifique
            }
            else if(prob < 30)
            {
                temperatura -= 0.2;      
            }
            else
            {
                temperatura += 0.2;
            }
        }
        else if(temperatura <= 3)
        {
            if(prob<45)
            { 
                temperatura -= 0.2;
            }
            else
            {
                temperatura += 0.2;
            }
        }
    }
    
    public void calentamientoGlobal()
    {
        calentamiento = true;
        temperatura += 2;
        this.eventosDia(buques);
    }
    
    public void buquesPescaMayor()
    {
        buques = true;
        this.eventosDia(buques);
    }
    
    public String mostrarDetalles()
    {
        int bacalao = 0, raya = 0, merluza = 0;
        String s = "";
        s += "FECHA: " + dia + "\n";
        s += "TEMPERATURA: " + temperatura + "\n--------------------------\n";
        
        if(calentamiento == true)
        {
            s += "Calentamiento Global.\n";
        }
        else if(buques == true)
        {
            s += "Buques de pesca mayor.\n";
        }
        
        s += "Numero de Esquimales: " + esquimales.size() + "\n";
        s += "Esquimales:\n";
        for(Esquimal e: esquimales)
        {
            s += e.toString();
        }
        
        s += "Numero de Osos Polares: " + osos.size() + "\n";
        s += "Osos Polares:\n";
        for(OsoPolar o: osos)
        {
            s += o.toString();
        }
        
        s += "Numero de Morsas: " + morsas.size() + "\n";
        s += "Morsas:\n";
        for(Morsa m: morsas)
        {
            s += m.toString();
        }
        
        s += "Numero de Focas: " + focas.size() + "\n";
        s += "Focas:\n";
        for(Foca f: focas){
            s += f.toString();
        }
        
        s += "Numero de Peces: " + peces.size() + "\n";
        s += "Peces:\n";
        for(Pez p: peces){
            s += p.toString();
            if(p.geTipo().equals("bacalao"))
            {
                bacalao++;
            }
            else if(p.geTipo().equals("raya"))
            {
                raya++;
            }
            else if(p.geTipo().equals("merluza negra"))
            {
                merluza++;
            }
        }
        s += "Peces de tipo Bacalao: " + bacalao + "\nPeces de tipo Raya: " + raya + "\nPeces de tipo Merluza Negra: " + merluza + "\n";
        
        s += "Numero de Krill y Plancton: " + krill_plancton.size() + "\n";
        
        return s;
    }
    
    public int getDia()
    {
        return dia;
    }
    public float getTemperatura()
    {
        return temperatura;
    }
    public int getNumEsquimales()
    {
        return esquimales.size();
    }
    
    public int getNumOsos()
    {
        return osos.size();
    }
    
    public int getNumMorsas()
    {
        return morsas.size();
    }
    
    public int getNumFocas()
    {
        return focas.size();
    }
    
    public int getNumPeces()
    {
        return peces.size();
    }
    
    public int getNumKrill()
    {
        return krill_plancton.size();
    }
}
