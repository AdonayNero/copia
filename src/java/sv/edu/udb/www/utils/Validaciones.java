package sv.edu.udb.www.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

    private static int entero;
    private static double decimal;
    private static String cadena;
    static SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");

    public static boolean esEntero(String cadena) {
        try {
            entero = Integer.parseInt(cadena.trim());
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean esEnteroPositivo(String cadena) {
        try {
            entero = Integer.parseInt(cadena.trim());
            if (entero <= 0) {
                return false;
            }
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean isEmpty(String mensaje) {
        return mensaje.trim().equals("");
    }

    public static boolean esDecimal(String cadena) {
        try {
            decimal = Double.parseDouble(cadena.trim());
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean esDecimalPositivo(String cadena) {
        try {
            decimal = Double.parseDouble(cadena.trim());
            if (decimal <= 0) {
                return false;
            }
            return true;
        } catch (Exception a) {
            return false;
        }
    }

    public static boolean esTelefono(String cadena) {
        Pattern pat = Pattern.compile("[267][0-9]{3}-[0-9]{4}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }
    
    public static boolean esCodigoRubro(String cadena) {
        Pattern pat = Pattern.compile("RU[0-9]{4}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }
    
    public static boolean esCodigoAdmin(String cadena) {
        Pattern pat = Pattern.compile("AD[0-9]{4}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }
    
     public static boolean esCodigoEmpresa(String cadena) {
        Pattern pat = Pattern.compile("EMP[0-9]{3}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }
     
     public static boolean esCodigoDependiente(String cadena) {
        Pattern pat = Pattern.compile("DEP[0-9]{3}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }
    
     public static boolean esCodigoPromocion(String cadena) {
        Pattern pat = Pattern.compile("PRO[0-9]{3}");
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    } 
     
    public static Date convertirFecha(String cadena) throws ParseException {
        return dt.parse(cadena);
    }

    public static String formatearFecha(Date fecha) {
        return dt.format(fecha);
    }
    
    public static boolean esCorreo(String cadena){
        Pattern pat=Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$");
        Matcher mat=pat.matcher(cadena);
        return mat.matches();
    }
    
    public static boolean esContraseña(String cadena){
        Pattern pat=Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
        Matcher mat=pat.matcher(cadena);
        return mat.matches();
    }
    

}
