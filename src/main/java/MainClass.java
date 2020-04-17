import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MainClass
{
    public static void main(String[] args) {
        final String APIKEY="7010e29efb5c5e0165282fa8e4e71669";
        String URL="https://api.openweathermap.org/data/2.5/weather?q=";
        String city="Ahmedabad";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new  Request.Builder().url(URL+city+"&units=metric&appid=" + APIKEY).build();
        JSONParser jsonParser = new JSONParser(); //JOSN Parser to parese json data

        try{
            Response response = okHttpClient.newCall(request).execute();
            String data = response.body().string(); //All resonse data in Sting
            //System.out.println(data);
            JSONObject jsonObject = (JSONObject)jsonParser.parse(data);

            //create Main Object
            JSONObject main =(JSONObject)jsonParser.parse(jsonObject.get("main").toString());
            String temperature = main.get("temp").toString();
            String mintemp = main.get("temp_min").toString();
            String  temp_max = main.get("temp_max").toString();

            //create wind Object
            JSONObject wind = (JSONObject)jsonParser.parse(jsonObject.get("wind").toString());
            String speed = wind.get("speed").toString();
            String deg = wind.get("deg").toString();

            System.out.println("Temperature of "+city+" is "+temperature+"°C");
            System.out.println("Minimum Temperature: "+mintemp );
            System.out.println("Maximum Temperature: "+temp_max);
            System.out.println("Wind speed:"+speed);
            System.out.println("Degree:"+deg);
        }
        catch (Exception e ){e.printStackTrace();}
    }
}
