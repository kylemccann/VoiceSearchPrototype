package proto.Microsoft;

/* Use this class to set your configuration for connecting to the proto.Microsoft Bing Speech API.
    https://docs.microsoft.com/en-gb/azure/cognitive-services/speech/home
    https://docs.microsoft.com/en-us/azure/cognitive-services/speech/concepts#recognition-modes
*/


public class Config {
    //These are the omly variables which need to be changed for your configuration.̵
    private String ApiKey1 = "676111df5bb14e9a83f44ef0d7aa6343";
    private String ApiKey2 = "5d6dbd1eb8294352a2c48c5018dff365";
    private String endPointUrl = "https://api.cognitive.microsoft.com/sts/v1.0";
    private String language = "en-GB";
    private String recognitionMode = "interactive"; //Utterances of 2-3 seconds

    public String getApiKey1() {
        return ApiKey1;
    }

    public String getApiKey2() {
        return ApiKey2;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public String getLanguage() { return language; }

    public String getRecognitionMode() { return recognitionMode; }
}
