/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

/**
 *
 * @author moren
 */

public class Simulacion implements Serializable
{
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
    {;}

    public void crearEntorno()
    {
        dia = 0;
        temperatura = 20;
        esquimales.clear();
        osos.clear();
        morsas.clear();
        focas.clear();
        peces.clear();
        krill_plancton.clear();
        
        //Creamos todos los esquimales
        num = this.numeroAleatorio(12, 14);
        for(int i = 0; i < num; i++)
        {
            Esquimal e = new Esquimal(dia, numeroAleatorio(35,48));
            esquimales.add(e);
        }
        
        //Creamos todos los osos polares
        num = this.numeroAleatorio(12, 14);
        for(int i = 0; i < num; i++)
        {
            Esquimal e = new Esquimal(dia, numeroAleatorio(35,48));
            esquimales.add(e);
        }
        
        //Creamos todas las morsas
        num = this.numeroAleatorio(12, 14);
        for(int i = 0; i < num; i++)
        {
            Esquimal e = new Esquimal(dia, numeroAleatorio(35,48));
            esquimales.add(e);
        }
        
        //Creamos todas las focas
        num = this.numeroAleatorio(12, 14);
        for(int i = 0; i < num; i++)
        {
            Esquimal e = new Esquimal(dia, numeroAleatorio(35,48));
            esquimales.add(e);
        }
        
        //Creamos todos los peces
        num = this.numeroAleatorio(12, 14);
        for(int i = 0; i < num; i++)
        {
            Esquimal e = new Esquimal(dia, numeroAleatorio(35,48));
            esquimales.add(e);
        }
        
        //Creamos todos los krill y Plancton
        num = this.numeroAleatorio(12, 14);
        for(int i = 0; i < num; i++)
        {
            Esquimal e = new Esquimal(dia, numeroAleatorio(35,48));
            esquimales.add(e);
        }
        
    }
    /**
     * Todo lo que pasa en 1 dia
     */
    private void eventosDia(){
        cambioTemperaturaDiaria();                                              //cambio temperatura
        eventosHumanoides();                                                    //humanos (reproduccion y muerte) cazavampiros(caza);   Modular
        eventosMonstruosos();
    }
    
    /**
     * Eventos que pasan cada dia protagonizados por los seres humanos
     */
    private void eventosHumanoides() {

        ListIterator it = humanos.listIterator();
        while(it.hasNext()){
            SerHumanoide h = (SerHumanoide) it.next();
            int hijos = h.reproduccion(temperatura);
            if(h.muerte()){
                //humanos.remove(h);
               it.remove();
            }
            for(int i = 0; i < hijos; i++){
               //humanos.add(new SerHumanoide(h.getVelocidad(),dia));
               it.add(new SerHumanoide(h.getVelocidad(),dia));
            }
            
        }
        
        it = cazavampiros.listIterator();
        while(it.hasNext()){
            CazaVampiro c = (CazaVampiro) it.next();
            int hijos = c.reproduccion(temperatura); 
            if(c.muerte()){
                //cazavampiros.remove(c);
                it.remove();
            }
            for(int i = 0; i < hijos; i++){
                //cazavampiros.add(new CazaVampiro(c.getVelocidad(),dia));
                it.add(new CazaVampiro(c.getVelocidad(),dia));
            }
            
            if(vampiros.size()>0){
                int pro = numeroAleatorio(1,3);
                if( pro == 1){
                    vampiros.remove(numeroAleatorio(0,vampiros.size()-1));
                    c.addAsesinato();
                }
            }
        }
        
        Collections.sort(humanos,SerHumanoide.comparador);
        Collections.sort(cazavampiros,SerHumanoide.comparador);
    }
    
    /**
     * Eventos que pasan cada dia protagonizados por los seres monstruosos
     */
    private void eventosMonstruosos() {
        
        ListIterator it = vampiros.listIterator();
        while(it.hasNext()){
            Vampiro v = (Vampiro) it.next();
            if(v.accion()){                                                     //si el vampiro tiene hambre
                if(humanos.size() > 0){                                             //si existen humanos
                    humanos.remove(numeroAleatorio(0,humanos.size()-1));                  //un humano muere o es convertido
                    if(v.accion()){                                                     //si el humano sobrevive
                        //vampiros.add(new Vampiro (dia));                                //se añade un nuevo vampiro
                        it.add(new Vampiro (dia));  
                        v.addConvertido();      
                    }
                }else{                                                              //si no existe humanos
                    //vampiros.remove(v);                                                 //vampiro muere inanicion
                    it.remove();
                }               
            }
        }


        //zombies (probabilodad de convertir, muerte de zombie)
        it = zombies.listIterator();
        while(it.hasNext()){
            Zombie z = (Zombie) it.next();
            
            if(z.muerte(dia)){
                //zombies.remove(z);
                it.remove();
            }
            
            if(z.convierte(probabilidadZombie)){
                //eliminar humano mas lento
                if(numeroAleatorio(1,2) == 1){                                      //elimina humano
                    if(humanos.size()>0){
                        humanos.remove(0);
                    }
                    
                }else{                                                              //elimina cazavampiros
                    if(cazavampiros.size()>0){
                        cazavampiros.remove(0);
                    }
                }
                z.addConvertido();
                //zombies.add(new Zombie(dia));
                it.add(new Zombie(dia));
                
            }
            
        }
    }
    
    /**
     * Funcion que hace transcurrir d dias en el simulador
     * @param d cantidad de dias que deben pasar
     */
    public void transcurrirDia(int d){
        for(int i = 0; i < d; i++){
            dia++;
            eventosDia();
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
     * Funcion que genera seres y los añade a la lista de estos 
     * @param i cantidad de seres a generar
     * @param ser el ser que va ha ser generado
     */
    private void generarSer(int i, char ser){
        switch(ser){
            case 'H':
                for(int j = 0; j < i; j++){
                    SerHumanoide h = new SerHumanoide(numeroAleatorio(60,100),dia);
                    humanos.add(h);
                }
                break;
            case 'C':
                for(int j = 0; j < i; j++){
                    CazaVampiro cv = new CazaVampiro(numeroAleatorio(60,100),dia);
                    cazavampiros.add(cv);
                }
                break;
            case 'V':
                for(int j = 0; j < i; j++){
                    Vampiro v = new Vampiro(dia);
                    vampiros.add(v);
                }
                break;
                
            case 'Z':
                for(int j = 0; j < i; j++){
                    Zombie z = new Zombie(dia);
                    zombies.add(z);
                }
                break;    
            default:
                System.out.println("No se ha encontrado el ser");
                System.exit(0);
        }
    }
    
    /**
     * Funcion toString sobrecargada
     * @return Como se debe mostrar la clase simulacion
     */
    @Override
    public String toString() {
        return "Dia: " + dia + "\nTemperatura = " + temperatura + "\nHumanos = " + humanos.size() + "\nVampiros = " + vampiros.size() + "\nCazavampiros = " + cazavampiros.size() + "\nZombies = " + zombies.size();
    }
    
    /**
     * Funcion que calcula la variacion de la temperatura cada dia
     */
    private void cambioTemperaturaDiaria() {
        int prob = numeroAleatorio(1,100);
        
        if(temperatura>= 22){                                       //Temperatura Mayor o igual a 22 grados
            if(prob<45){
                cambioTemperatura((float) 0.5);                              //Probabilidad de subir medio grado: 45 %
            }else{
                cambioTemperatura((float) -0.5);                             //Probabilidad de bajar medio grado: 55 %
            }
        }else if (temperatura > 18 && temperatura < 22){            //Temperatura Menor a 22 y mayor a 18 grados
            if(prob < 5){
                //cambioGlobal(0); NO VARIA LA TEMPERATURA              //Probabilidad de no variar su temperatura: 5 %
            }else if(prob < 35){
                cambioTemperatura((float) -0.5);                             //Probabilidad de bajar medio grado: 30 %
            }else{
                cambioTemperatura((float) 0.5);                              //Probabilidad de subir medio grado: 65 %
            }
        }else{
            if(prob<45){                                            //Temperatura Menor o igual a 18 grados 
                cambioTemperatura((float) -0.5);                             //Probabilidad de bajar medio grado: 45 %
            }else{
                cambioTemperatura((float) 0.5);                              //Probabilidad de subir medio grado: 55 %
            }
        }
    }

    /**
     * Funcion que cambia la probabilidad de convertir humanos a zombies
     */
    public void invasionZombie() {
        probabilidadZombie = 3;
    }
    
    /**
     * Funcion que permite cambiar la temperatura (Pensado para calentamiento y enfriamiento global)
     * @param t la temperatura que se le va a sumar a la temperatura actual
     */
    public void cambioTemperatura(float t){
        temperatura = temperatura + t;
    }
    
    public String mostrarDetalles(){
        String s = "";
        s = s + "HUMANOS:\n------------\n";
        for(SerHumanoide h: humanos){
            s = s + h.toString();
        }
        s = s + "\nNumero de humanos = " + humanos.size() + "\nCAZA VAMPIROS:\n------------------------\n";
        for(CazaVampiro cv: cazavampiros){
            s = s + cv.toString();
        }
        s = s + "\nNumero de CazaVampiros = " + cazavampiros.size() + "\nVAMPIROS:\n----------------\n";
        for(Vampiro v: vampiros){
            s = s + v.toString();
        }
        s = s + "\nNumero de vampiros = " + vampiros.size() + "\nZOMBIES:\n-------------\n";
        for(Zombie z: zombies){
            s = s + z.toString();
        }     
        s = s +  "\nNumero de zombies = " + zombies.size();
        return s;
    }

}
