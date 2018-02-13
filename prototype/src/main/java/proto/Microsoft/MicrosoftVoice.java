package proto.Microsoft;
import proto.IApi;

import java.io.IOException;

public class MicrosoftVoice implements IApi {
    public static void main(String... args) throws Exception {
        HttpService http = new HttpService();
        http.post("/Users/kylemccann/Development/Project/Voice-Recordings/lets-recognise-speech/1-Scottish.wav");
    }


    @Override
    public String SendRequest(String filePath) throws Exception{
        String response = null;
        HttpService http = new HttpService();
        try {
             response = http.post(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

//        System.out.println("response = " + response);
        return response;
    }
}
