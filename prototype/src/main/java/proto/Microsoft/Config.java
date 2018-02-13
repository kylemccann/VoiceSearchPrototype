package proto.Microsoft;

/* Use this class to set your configuration for connecting to the proto.Microsoft Bing Speech API.
    https://docs.microsoft.com/en-gb/azure/cognitive-services/speech/home
    https://docs.microsoft.com/en-us/azure/cognitive-services/speech/concepts#recognition-modes
*/


public class Config {
    //These are the omly variables which need to be changed for your configuration.̵
    private String ApiKey1 = "43fca961c43b4ea6b4a60ca779eb20e9";
    private String ApiKey2 = "ca6bd76c6bd740e5b9c2859ef6bf5395";
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
