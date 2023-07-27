import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Api2 {
    public static void main(String[] args) throws Exception {
        peticionHttpGet("sold");
    }


    public static void peticionHttpGet(String estado) throws Exception {
        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL
        //String url = "https://petstore.swagger.io/v2/user/juan";
        URL url = new URL("https://petstore.swagger.io/v2/pet/findByStatus?status=" + estado);


        // Abrir la conexión e indicar que será de tipo GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        // Búferes para leer
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
            String linea;
            // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
            while ((linea = rd.readLine()) != null) {
                resultado.append(linea);
            }
            // Cerrar el BufferedReader
            rd.close();
            // Regresar resultado, pero como cadena, no como StringBuilder
            // return resultado.toString();
            //      JSONObject jsonobject= new JSONObject(resultado.toString());
            //    System.out.println("json object "+jsonobject);
            System.out.println("La respuesta es:\n" + resultado.toString());
/*          JSONParser parser = new JSONParser();
          FileReader reader = new FileReader("C:\\Users\\kevin\\Desktop\\LeerMenu\\Menu.json");
          Object obj = parser.parse(reader);
          JSONObject pJsonObj = (JSONObject)obj;
          JSONArray array = (JSONArray)pJsonObj.get("PlatoFuerte");*/

        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }

}