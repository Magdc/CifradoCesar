import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
public class Generales
{
    //Mensajes secretos
    static String[] wordbank = {"Isomero","Irracionalidad","Homocromia", "Enologia","Ionizacion",
    "1 1 2 3 5 8 13 21","para el cesar lo que es del cesar","y yo soy y yo soy y","O bebo lo vivo o vivo lo bebo","3 14149",
    "2 7182","911","Racionalizar","da vinci 1518","virgilio","dante","febrero 11", "27 de enero","17 el damian","34 33 33 cantos",
    "Hermes","El cuarteto de nos","Mike"};
    //abecedario
    static char[] abecedario = Abecedario.abecedario;  
    //numeros
    static char[] numeros = Numeros.numeros;
    static Scanner scan = new Scanner(System.in);
    static int clave;
    static String mensajeOriginal;
    static String mensajeCifrado;
    static String mensajeDecifrado;
    static Timer timer = new Timer();
    public static void cambiodif(){
        TimerTask tarea = new TimerTask(){
        @Override
        public void run(){
            inicioDif();
            }
        };
        timer.scheduleAtFixedRate(tarea,180000,180000);
    }
     public static void cambioIns(){
        TimerTask tarea = new TimerTask(){
        @Override
        public void run(){
            inicioIns();
            }
        };
        timer.scheduleAtFixedRate(tarea,120000,60000);
    }
    public static void inicio(){
        clave = claveAl();
        mensajeOriginal = wordbank[clave%wordbank.length];
        mensajeCifrado =mensajeC(mensajeOriginal.toLowerCase(), clave);
        mensajeDecifrado =mensajeDc(mensajeC(mensajeOriginal.toLowerCase(), clave), clave);
        System.out.println("* Mensaje cifrado: "+mensajeCifrado);
        pistas(mensajeDecifrado);
    }
    public static void inicioIns(){
        clave = claveAl();
        mensajeOriginal = wordbank[clave%wordbank.length];
        mensajeCifrado =mensajeC(mensajeOriginal.toLowerCase(), clave);
        mensajeDecifrado =mensajeDc(mensajeC(mensajeOriginal.toLowerCase(), clave), clave);
        System.out.println("¡El mensaje cambió!, ahora es: "+mensajeCifrado);
        pistas(mensajeDecifrado);
    }
    public static void inicioDif(){
        int alt;
        Random rd = new Random();
        alt = rd.nextInt(100) +1;
        mensajeOriginal = wordbank[alt%wordbank.length];
        mensajeCifrado =mensajeC(mensajeOriginal.toLowerCase(), clave);
        mensajeDecifrado =mensajeDc(mensajeC(mensajeOriginal.toLowerCase(), clave), clave);
        System.out.println("¡El mensaje cambió!, ahora es: "+mensajeCifrado);
    }
    public static void ciberseguridad(){
        clave = claveAl();
        mensajeOriginal = wordbank[clave%wordbank.length];
        mensajeCifrado =mensajeC(mensajeOriginal.toLowerCase(), clave);
        mensajeDecifrado =mensajeDc(mensajeC(mensajeOriginal.toLowerCase(), clave), clave);
        System.out.println("* Mensaje cifrado: "+mensajeCifrado);
        pistas(mensajeDecifrado);
    }
    //Genera clave aleatoria.   
    public static int claveAl(){
        int clave;
        Random rd = new Random();
        return rd.nextInt(100) +1;
    }
    //Pistas
    public static void pistas(String palabra){
        System.out.println("* Tienes unos tips para adivinar la palabra secreta ¡Úsalos con sabiduría!");
        System.out.println("+Pistas: \n-La primera letra es \""+palabra.charAt(0)+"\".\n-La última letra es \""+palabra.charAt(palabra.length()-1)+"\".\n-Los @ son espacios.");
    }
    //Index inicial, revisa el caracter ingresado y revisa qué posición le coresponde normalmente, funciona para Facil.
    public static int indexInicial(char current){
        int posInicial = 0;
        if(Character.isDigit(current)){
         for(int i = 0; i<numeros.length;i++){
            if(current == numeros[i]){
                posInicial = i;
                break;
            }
        } 
        }
        else{
         for(int i = 0; i<abecedario.length;i++){
            if(current == abecedario[i]){
                posInicial = i;
                break;
            }
        }
        }
        return posInicial;
    }
    // Devuelve mensaje codificado para fácil.
    public static String mensajeC(String palabra, int clave){
        StringBuilder Final = new StringBuilder();
        for(int i = 0; i<palabra.length(); i++){
            if(Character.isDigit(palabra.charAt(i))){
            Final.append(Numeros.devolucionNum(Numeros.indexFinalNum(indexInicial(palabra.charAt(i)), clave)));
            }
            else if(Character.isWhitespace(palabra.charAt(i))){
                Final.append('@');
            }
            else{
            Final.append(Abecedario.devolucionABC(Abecedario.indexFinal(indexInicial(palabra.charAt(i)), clave)));
            }
        }
        return Final.toString();
    }
    
    //hace el proceso inverso y devuelve la primera palabra
    public static String mensajeDc(String mensajeCifrado,int clave){
        StringBuilder Final = new StringBuilder();
        for(int i = 0; i<mensajeCifrado.length(); i++){
            if(Character.isDigit(mensajeCifrado.charAt(i))){
            Final.append(Numeros.devolucionNum(Numeros.procesoInvNu(indexInicial(mensajeCifrado.charAt(i)), clave)));
            }
            else if(mensajeCifrado.charAt(i)=='@'){
            Final.append(' ');
            }
            else{
            Final.append(Abecedario.devolucionABC(Abecedario.procesoInv(indexInicial(mensajeCifrado.charAt(i)), clave)));
            }
        }
        return Final.toString();
    }
}
