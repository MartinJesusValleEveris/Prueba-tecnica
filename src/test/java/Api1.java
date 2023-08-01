import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api1 {
    public static void main(String[] args) throws Exception {
        peticionHTTPPost("Nami");
        peticionHttpGet("Nami");
    }


    public static void peticionHttpGet(String userName) throws Exception {
        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL
        //String url = "https://petstore.swagger.io/v2/user/juan";
        URL url = new URL("https://petstore.swagger.io/v2/user/"+userName);


        // Abrir la conexión e indicar que será de tipo GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        // Búferes para leer
      try( BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()))){
        String linea;
        // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        // Cerrar el BufferedReader
        rd.close();

        //Pintamos el resultado del get
        System.out.println("La respuesta es: " + resultado.toString());
    } catch (Exception e) {
        // Manejar excepción
        e.printStackTrace();
    }
    }

    public static void peticionHTTPPost(String userName) throws Exception {
        //Montamos el body
        String body = "{\n" +
                "  \"id\": 4,\n" +
                "  \"username\": \""+userName+"\",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": 0\n" +
                "}";
        String urlPost = "https://petstore.swagger.io/v2/user";
        URL url = new URL(urlPost);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
        try (DataOutputStream dos = new DataOutputStream(con.getOutputStream())) {
            dos.writeBytes(body);
        }

        //pintamos la respuesta del post
        System.out.println("Response code: " + con.getResponseCode());

        //Read Response from and API
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }

        }

    }
}