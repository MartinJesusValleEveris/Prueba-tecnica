import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Api3 {
    public static AnimalesVendidos animalesVendidos = new AnimalesVendidos();

    public static void main(String[] args) throws Exception {
        peticionHttpGet("sold");
        printResultado();
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
            animalesVendidos.setJsonArray(jsonArray);
        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }

    public static void printResultado() {
        int i;
        ArrayList<String> names = new ArrayList<>();
        //por cada mascota vendida
        for (i = 0; i < animalesVendidos.getJsonArray().length(); i++) {
            boolean animalRepetido=false;
            int repeticiones = 0;

            if (names.size() == 0) {
                for (int z = i; z < animalesVendidos.getJsonArray().length(); z++) {
                    if (animalesVendidos.getJsonArray().getJSONObject(i).get("name").equals(animalesVendidos.getJsonArray().getJSONObject(z).get("name"))) {
                        repeticiones++;
                    }
                }
                System.out.println(animalesVendidos.getJsonArray().getJSONObject(i).get("name") + " : " + repeticiones);
                names.add(animalesVendidos.getJsonArray().getJSONObject(i).get("name").toString());
            } else {
                //Por cada posicion en la lista
                for (int m = 0; m < names.size(); m++) {
                    if (names.get(m).equals(animalesVendidos.getJsonArray().getJSONObject(i).get("name"))) {
                        animalRepetido = true;
                    }
                }
                if(animalRepetido==false){
                    for (int z = i; z < animalesVendidos.getJsonArray().length(); z++) {
                        if (animalesVendidos.getJsonArray().getJSONObject(i).get("name").equals(animalesVendidos.getJsonArray().getJSONObject(z).get("name"))) {
                                repeticiones++;
                        }
                    }
                    names.add(animalesVendidos.getJsonArray().getJSONObject(i).get("name").toString());
                    System.out.println(animalesVendidos.getJsonArray().getJSONObject(i).get("name") + " : " + repeticiones);

                }
                        //Puede llamarse doggie, y el primer caso no se llame doggie,
                        // y el tercero si(entonces entrara, aunque ya haya otro King kong


            }

        }
        System.out.println("names " + names.size() + " lista " + names);
        System.out.println("Total Pets: " + i +" total length"+animalesVendidos.getJsonArray().length());
    }
}