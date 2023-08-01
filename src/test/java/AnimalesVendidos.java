import net.bytebuddy.asm.Advice;
import org.json.JSONArray;

public class AnimalesVendidos {
    private JSONArray jsonArray;

    //Creamos el contructo para que se pueda crear vacio sin pasarle valores
    public AnimalesVendidos(){}

    //Creamos el metodo para poder pasarle el Array a la clase
    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    //Creamos el metodo para poder recuperar el Array
    public JSONArray getJsonArray(){
        return jsonArray;
    }
}
