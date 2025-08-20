public class Abecedario
{
    //abecedario
    public static char[] abecedario = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q'
    ,'r','s','t','u','v','w','x','y','z'};
    //recibe la posicion inicial y asigna una nueva posición con la clave recibida.
    public static int indexFinal(int posIn, int clave){
        clave = clave % abecedario.length;
        if(posIn+clave>=abecedario.length){
            clave -= (abecedario.length-posIn);
            return clave;
        }
        else{
            return posIn+clave;
        }
    }
    //revisa que hay en la posición final
    public static String devolucionABC(int posFinal){
        String mensajeFinal = "";
        for(int i = 0;i<abecedario.length; i++){
            if(posFinal == i){
                mensajeFinal += abecedario[i];
                break;
            }
        }
        return mensajeFinal;
    }
    //Usa la posIn de la palabra Final y la clave, y busca la palabra original
    public static int procesoInv(int posIn, int clave){
        clave = clave % abecedario.length;
        if(posIn-clave<0){
            int temp = posIn -clave;
            return abecedario.length+temp;
            }
        else{
            return posIn-clave;
        }
    }
}
