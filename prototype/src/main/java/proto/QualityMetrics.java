package proto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class QualityMetrics {

    private int id;
    private String ApiName;
    private String voiceQuery;
    private String accent ="";

    private int phraseId;
    private String expectedResult;
    private String actualResult;
    private String transformedResult;
    private String[] phrase = {"Why is it faster to process a sorted array than an unsorted array",
            "How to undo the most recent commits in Git",
            "What is the difference between String and string in C#",
            "What is the difference between INNER JOIN and OUTER JOIN",
            "What is the difference between a URI a URL and a URN",
            "Which equals operator (== vs ===) should be used in JavaScript comparisons",
            "ruby openssl api for encrypted key without nodes option",
            "Background Location updates using Google API - Fused Location Provider not accurate",
            "Can the text content of an element be read in Angular without reading from the DOM",
            "What is the “-->” operator in C++"};



    private double responseTime;
    private float quality;
    private float errorRate;

    private EditDistance e;
    private symbolsDictionary s;


    public QualityMetrics(){
        e = new EditDistance();
        s = new symbolsDictionary();
    }

    public double getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(double startTime, double endTime) {
        System.out.println("startTime = " + startTime);
        System.out.println("endTime = " + endTime);
        responseTime = endTime - startTime;

    }

    public void setQuality(){

        List mistakes = compareExpectedWithActual(expectedResult, actualResult);
        ArrayList expectedArray = getStringAsArray(expectedResult);

        int expectedQuerySize = expectedArray.size();
        int mistakesSize = mistakes.size();

        if(mistakes.size() == 0){
            //There were no mistakes
            quality = 100;
        }else if(mistakesSize == expectedQuerySize){
            //We can assume that the quality is 0
            quality = 0;
        }else{
//            System.out.println("mistakesSize = " + mistakesSize);
            int amountCorrect = expectedQuerySize - mistakesSize;
//            System.out.println("expectedQuerySize = " + expectedQuerySize);
            quality =  (amountCorrect * 100.0f) / expectedQuerySize;

            quality = 100 - quality;
        }

        //Remove symbols check actual for phonetic equivalent

        //If actual contains symbols remove from string

        //else send original expected and actual string to calculate minDistance

        s.removeValidSymbols(this);
        e.setEditDistance(e.minDistance(transformedResult, actualResult));

        int maxLen = Math.max(transformedResult .length(), actualResult.length());
        setErrorRate((e.getEditDistance()/maxLen)*100);
        System.out.println("errorRate = " + errorRate);


    }

    public ArrayList getStringAsArray(String str){
        String[] rtn = new String[0];
        if(str!=null) {


            rtn = str.split(" ");

        }
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(rtn));
        return arrayList;
    }

    public List<String> compareExpectedWithActual(String expectedResult, String actualResult){
        ArrayList expectedArray = getStringAsArray(expectedResult);
        ArrayList actualArray = getStringAsArray(actualResult);

        Collection<String> listOne = expectedArray;
        Collection<String> listTwo = actualArray;

        List<String> sourceList = new ArrayList<String>(listOne);
        List<String> destinationList = new ArrayList<String>(listTwo);


        sourceList.removeAll( listTwo );
        destinationList.removeAll( listOne );
        System.out.println("\n");
        System.out.println("expectedResult = [" + expectedResult + "]");
        System.out.println("actualResult = [" + actualResult  + "]");
        System.out.println("Destination list " + destinationList.toString());

        return destinationList;

    }



    public Float getQuality(){
        return this.quality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoiceQuery() {
        return voiceQuery;
    }

    public void setVoiceQuery(String voiceQuery) {
        this.voiceQuery = voiceQuery;
    }


    public String getAccent() {
        return accent;
    }

    public void setAccent(String accent) {
        this.accent = accent;
    }

    public String getApiName() {
        return ApiName;
    }

    public void setApiName(String apiName) {
        ApiName = apiName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(int phraseNo) {
        setPhraseId(phraseNo);
        String phrase = getPhrase(phraseNo);
        this.expectedResult = phrase.trim().toLowerCase();

        transformedResult = phrase.trim().toLowerCase();
    }

    public String getPhrase(int index) {
        return phrase[index];
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult.toLowerCase();
    }

    public float getErrorRate() {
        return errorRate;
    }

    public void setErrorRate(float errorRate) {
        this.errorRate = errorRate;
    }

    public float getEditDistance(){
        return e.getEditDistance();
    }

    public int getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(int phraseId) {
        this.phraseId = phraseId;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }


    public String getTransformedResult() {
        return transformedResult;
    }

    public void setTransformedResult(String transformedResult) {
        this.transformedResult = transformedResult;
    }

    @Override
    public String toString() {
        return "QualityMetrics{" +
                "id=" + id +
                ", ApiName='" + ApiName + '\'' +
                ", voiceQuery='" + voiceQuery + '\'' +
                ", accent='" + accent + '\'' +
                ", expectedResult='" + expectedResult + '\'' +
                ", actualResult='" + actualResult + '\'' +
                ", responseTime=" + responseTime +
                ", quality=" + quality +
                ", errorRate=" + errorRate +
                ", e=" + e.getEditDistance() +
                '}';
    }
}


