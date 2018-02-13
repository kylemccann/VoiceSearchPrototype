package proto.IBM;

/* Use this class to set your configuration for connecting to the IBM Watson Speech API.
    https://console.bluemix.net/docs/services/speech-to-text/getting-started.html#gettingStarted
*/


public class Config {
    //These are the omly variables which need to be changed for your configuration.̵
    private String userName = "";
    private String password = "";
    private String endPointUrl = "https://stream.watsonplatform.net/speech-to-text/api";

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }


}
