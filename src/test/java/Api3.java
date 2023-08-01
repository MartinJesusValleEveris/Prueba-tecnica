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

            //pasamos el resultado a un objeto jsonArray para poder tratarlo mejor
            JSONArray jsonArray = new JSONArray(resultado.toString());
            //pasamos el objeto jsonArray a la clase animalesVendidos para poderlo tratar en otra funcion
            animalesVendidos.setJsonArray(jsonArray);
        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }

    public static void printResultado() {
        int i;
        //Creamos una lista de nombres que ya hemos contado
        ArrayList<String> names = new ArrayList<>();

        //por cada mascota vendida
        for (i = 0; i < animalesVendidos.getJsonArray().length(); i++) {
            boolean animalRepetido = false;
            int repeticiones = 0;
            //Si no existen nombres en la lista ejecuta esta parte del codigo
            if (names.size() == 0) {
                //a partir de la posicion actual (i) cogemos el nombre, y lo comparamos con el resto de nombres (z)
                for (int z = i; z < animalesVendidos.getJsonArray().length(); z++) {
                    //Si el nombre coincide le sumamos 1 repeticion
                    if (animalesVendidos.getJsonArray().getJSONObject(i).get("name").equals(animalesVendidos.getJsonArray().getJSONObject(z).get("name"))) {
                        repeticiones++;
                    }
                }
                //Al final pintamos la posicion del nombre (i) y las repeticiones, y añadimmos el nombre a la lista
                System.out.println(animalesVendidos.getJsonArray().getJSONObject(i).get("name") + " : " + repeticiones);
                names.add(animalesVendidos.getJsonArray().getJSONObject(i).get("name").toString());
            } else {
                //Por cada posicion en la lista comparamos con el nombre actual
                //Si coincide el nombre con alguno de la lista, pasamos a true, y no ejecutamos el codigo de contar
                for (int m = 0; m < names.size(); m++) {
                    if (names.get(m).equals(animalesVendidos.getJsonArray().getJSONObject(i).get("name"))) {
                        animalRepetido = true;
                    }
                }
                //En el caso de que el nombre no este en la lista, ejecutamos el mismo codigo que la primera vez
                if (animalRepetido == false) {
                    for (int z = i; z < animalesVendidos.getJsonArray().length(); z++) {
                        if (animalesVendidos.getJsonArray().getJSONObject(i).get("name").equals(animalesVendidos.getJsonArray().getJSONObject(z).get("name"))) {
                            repeticiones++;
                        }
                    }
                    names.add(animalesVendidos.getJsonArray().getJSONObject(i).get("name").toString());
                    System.out.println(animalesVendidos.getJsonArray().getJSONObject(i).get("name") + " : " + repeticiones);
                }
            }
        }
        //Para asegurar que sale correctamente, pintamos cada nombre de la lista, y el total de animales vendidos y el tamaño de la array
        System.out.println("names " + names.size() + " lista " + names);
        System.out.println("Total Pets: " + i + " total length: " + animalesVendidos.getJsonArray().length());
    }
}