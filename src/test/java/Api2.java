import org.json.JSONArray;
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
            JSONArray jsonArray = new JSONArray(resultado.toString());
            for(int i=0;i<jsonArray.length();i++){
                System.out.println("id: "+jsonArray.getJSONObject(i).get("id")+" and name:"+jsonArray.getJSONObject(i).get("name"));
            }
        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }
}