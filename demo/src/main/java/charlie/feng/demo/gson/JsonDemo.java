package charlie.feng.demo.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonDemo {

    // run with -enableassertions
    public static void main(String[] args) {
        parseString();
    }

    public static void parseString() {
        String jsonString = """
                { "input" : {"inputKey1" : "inputValue1"}, "output" : {outputKey1: "outputValue1"}, "array1" : [{"eleKey1" : "eleValue1"}]}""";
        JsonElement jelement = new JsonParser().parse(jsonString);
        JsonObject jobject = jelement.getAsJsonObject();
        JsonObject inputJobject = jobject.getAsJsonObject("input");
        assert inputJobject.toString().equals("""
                {"inputKey1":"inputValue1"}""");
        JsonObject outputobject = jobject.getAsJsonObject("output");
        // Notice that the outputKey1 is double quoted
        assert outputobject.toString().equals("""
                {"outputKey1":"outputValue1"}""");
        JsonArray jarray = jobject.getAsJsonArray("array1");
        jobject = jarray.get(0).getAsJsonObject();
        assert jobject.toString().equals("""
                {"eleKey1":"eleValue1"}""");

        JsonObject newJsonObject = new JsonObject();
        newJsonObject.add("input", inputJobject);
        newJsonObject.add("output", outputobject);
        newJsonObject.add("array1", jarray);
        System.out.println(newJsonObject);
        assert newJsonObject.toString().equals("""
               {"input":{"inputKey1":"inputValue1"},"output":{"outputKey1":"outputValue1"},"array1":[{"eleKey1":"eleValue1"}]}""");
    }

}
