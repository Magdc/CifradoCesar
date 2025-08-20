public class Numeros
{
    //numeros
    public static char[] numeros = {'0','1','2','3','4','5','6','7','8','9'};
    //Funciona igual que el método procesoInv solo que para la lista de numeros
    //Posición final para los números.
    public static  int indexFinalNum(int posIn, int clave){
        clave = clave % numeros.length;
        if(posIn+clave>=numeros.length){
            clave -= (numeros.length-posIn);
            return clave;
        }
        else{
            return posIn+clave;
        }
    }
    
    public static String devolucionNum(int posFinal){
        String mensajeFinal = "";
        for(int i = 0;i<numeros.length; i++){
            if(posFinal == i){
                mensajeFinal += numeros[i];
                break;
            }
        }
        return mensajeFinal;
    }
    //Funciona igual que el método procesoInv solo que para la lista de numeros
    public static int procesoInvNu(int posIn, int clave){ 
        clave = clave % numeros.length;
        if(posIn-clave<0){
            int temp = posIn -clave;
            return numeros.length+temp;
        }
        else{
            return posIn-clave;
        }
    }
}
