package proto.IBM;// Imports the proto.Google Cloud client library

import com.google.common.base.Function;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;
import com.google.protobuf.ByteString;
import proto.IApi;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class IbmVoiceSearch implements IApi {


    @Override
    public String SendRequest(String filePath) throws Exception {
        // Instantiates a client

        // The path to the audio file to transcribe
        System.out.println("filePath = " + filePath);
        // Reads the audio file into memory
        Path path = Paths.get(filePath);
        byte[] data = Files.readAllBytes(path);



        SpeechToText service = new SpeechToText();
        Config config = new Config();
        service.setUsernameAndPassword(config.getUserName(), config.getPassword());


         Object returnedResult = null;
        RecognizeOptions.Builder bldr = new RecognizeOptions.Builder().contentType(HttpMediaType.AUDIO_WAV).continuous(Boolean.TRUE);
        RecognizeOptions options = bldr.build();
        SpeechResults transcript = service.recognize(path.toFile(), options).execute();
        if (transcript != null) {

                returnedResult = getBestResult(transcript);

                }
            
    
        System.out.println("returnedResult = " + returnedResult);
        return returnedResult.toString();
    }

    private String getBestResult(SpeechResults transcript) {
        String bestResult = null;
        if (transcript != null) {
            double maxConfidence = Double.MIN_VALUE;
            for (Transcript item : transcript.getResults()) {
                for (SpeechAlternative speechAlternative : item.getAlternatives()) {
                    if (speechAlternative.getConfidence() > maxConfidence) {
                        maxConfidence = speechAlternative.getConfidence();
                        bestResult = speechAlternative.getTranscript();
                    }
                }
            }
        } else {
            System.out.println("Unable to process transcript: {}"); //transcript);
        }
        System.out.println("Transcribed best result: " + bestResult);
        if (bestResult != null) {
            bestResult = bestResult.trim();
        }
        return bestResult;
    }
    
    
}