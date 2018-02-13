package proto.Google;// Imports the proto.Google Cloud client library
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
import proto.IApi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GoogleVoiceSearch implements IApi {


    @Override
    public String SendRequest(String filePath) throws Exception {
        // Instantiates a client
        SpeechClient speech = SpeechClient.create();

        // The path to the audio file to transcribe
//        fileName = "/Users/kylemccann/Development/proto.Google.IbmVoiceSearch/rec_3s.wav";
        System.out.println("filePath = " + filePath);
        // Reads the audio file into memory
        Path path = Paths.get(filePath);
        byte[] data = Files.readAllBytes(path);
        ByteString audioBytes = ByteString.copyFrom(data);

        // Builds the sync recognize request
        RecognitionConfig config = RecognitionConfig.newBuilder()
                .setEncoding(AudioEncoding.LINEAR16)
                .setSampleRateHertz(16000)
                .setLanguageCode("en-US")
                .build();
        RecognitionAudio audio = RecognitionAudio.newBuilder()
                .setContent(audioBytes)
                .build();

        // Performs speech recognition on the audio file
        RecognizeResponse response = speech.recognize(config, audio);
        List<SpeechRecognitionResult> results = response.getResultsList();

        String returnedResult = null;

        for (SpeechRecognitionResult result: results) {
            // There can be several alternative transcripts for a given chunk of speech. Just use the
            // first (most likely) one here.
            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
            returnedResult = alternative.getTranscript();
            System.out.printf("Transcription: %s%n", result);
            return returnedResult;
        }
        speech.close();
        System.out.println("returnedResult = " + returnedResult);
        return returnedResult;
    }
}