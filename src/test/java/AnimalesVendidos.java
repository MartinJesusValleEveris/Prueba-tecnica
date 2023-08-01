import net.bytebuddy.asm.Advice;
import org.json.JSONArray;

public class AnimalesVendidos {
    private JSONArray jsonArray;

    public AnimalesVendidos(){}


    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public JSONArray getJsonArray(){
        return jsonArray;
    }
}
