package proto.Microsoft;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okio.ByteString;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpService {


    public String post(String filePath) throws UnirestException, IOException {
        Config conf = new Config();
        String query = null;

        try {

        FileInputStream fin = null;
            File file = new File(filePath);
            // create FileInputStream object
            fin = new FileInputStream(file);

            byte[] fileContent = IOUtils.toByteArray(fin);

            HttpResponse<JsonNode> jsonResponse = Unirest.post("https://speech.platform.bing.com/speech/recognition/"+ conf.getRecognitionMode() +"/cognitiveservices/v1?language=" + conf.getLanguage() +"&format=detailed")
                    .header("accept", "application/json;text/xml")
                    .header("Content-Type", "audio/wav; codec=audio/pcm; samplerate=16000")
                    .header("Ocp-Apim-Subscription-Key", conf.getApiKey1())
                    .header("Host", "speech.platform.bing.com")
                    .header("Expect", "100-continue")
                    .body(fileContent)
                        .asJson();
            if(jsonResponse!=null) { //Check for no response
                JSONArray array = (JSONArray) jsonResponse.getBody().getObject().get("NBest");
                JSONObject obj = (JSONObject) array.get(0);

                query = (String) obj.get("Lexical");
            }




            System.out.println("jsonResponse = "  + jsonResponse.getBody() + jsonResponse.getHeaders());

        }
        catch(Exception e){
            System.out.println("e = " + e);
            //throw e;
        }
        return query;

    }
}
