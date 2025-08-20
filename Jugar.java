import java.sql.SQLOutput;

public class Jugar extends Generales{
    public static void facil(){
        inicio();
        while(true){
            System.out.print("> ");
            String intento = scan.nextLine();
            intento = intento.toLowerCase();
            if(intento.equals(mensajeDecifrado)){
                System.out.println("¡Enhorabuena lo lograste!");
                break;
            }
            else{
                System.out.println("¡Perder la paciencia es perder la batalla!");
            }
        }
    }
    public static void palabraSimple(){
        System.out.println("Vas a cifrar o descifrar[c/d]");
        System.out.print("> ");
        String intento = scan.nextLine();
        intento = intento.toLowerCase();


        if(intento.equals("c")){
            System.out.print("Ingrese la palabra que quiere encriptar: ");
            String palabra = scan.nextLine();
            System.out.println("Ingrese la clave con la que lo quiere hacer: ");
            int clave = scan.nextInt();
            System.out.print("El mensaje cifrado es: ");
            System.out.println(mensajeC(palabra,clave));
        }
        else if(intento.equals("d")){
            System.out.print("Ingrese la palabra que quiere desencriptar: ");
            String palabra = scan.nextLine();
            System.out.println("Ingrese la clave con la que lo quiere hacer: ");
            int clave = scan.nextInt();
            System.out.print("El mensaje descifrado es: ");
            System.out.println(mensajeDc(palabra,clave));
        }
        scan.close();

    }
    public static void medio(){
        System.out.println("*En este modo de juego tan solo tienes tres intentos, ¡Muy buena suerte!.");
        inicio();
        for(int i = 1; i<=3;i++){
            System.out.print("> ");
            String intento = scan.nextLine();
            intento = intento.toLowerCase();
            if(intento.equals(mensajeDecifrado)){
                System.out.println("¡Enhorabuena lo lograste!");
                break;
            }
            else if(i==3){
                System.out.println("Perdiste.");
            }
            else{
                System.out.println("¡Perder la paciencia es perder la batalla!, y tú todavía tienes "+(3-i)+" batalla'(s).");
            }
        }
    }
    public static void dificil(){
        cambiodif();
        System.out.println("* En este modo de juego tan solo tienes tres intentos y cada tres minutos cambiará de mensaje, ¡Muy buena suerte!.");
        inicio();
        for(int i = 1; i<=3;i++){
            System.out.print("> ");
            String intento = scan.nextLine();
            intento = intento.toLowerCase();
            if(intento.equals(mensajeDecifrado)){
                System.out.println("¡Enhorabuena lo lograste!");
                timer.cancel();
                break;
            }
            else if(i==3){
                System.out.println("Perdiste.");
                timer.cancel();
            }
            else{
                System.out.println("¡Perder la paciencia es perder la batalla!, y tú todavía tienes "+(3-i)+" batalla'(s).");
            }
        }
    }
    public static void insano(){
        cambioIns();
        System.out.println("* En este modo de juego tan solo tienes tres intentos, además posees de dos minutos iniciales,\ndespúes cambiará de mensaje y de clave cada minuto, ¡Muy buena suerte!.");
        inicio();
        for(int i = 1; i<=3;i++){
            System.out.print("> ");
            String intento = scan.nextLine();
            intento = intento.toLowerCase();
            if(intento.equals(mensajeDecifrado)){
                System.out.println("¡Enhorabuena lo lograste!");
                timer.cancel();
                break;
            }
            else if(i==3){
                System.out.println("Perdiste.");
                timer.cancel();
            }
            else{
                System.out.println("¡Perder la paciencia es perder la batalla!, y tú todavía tienes "+(3-i)+" batalla'(s).");
            }
        }
    }
}


