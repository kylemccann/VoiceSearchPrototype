package proto;

import proto.Google.GoogleVoiceSearch;
import proto.IBM.IbmVoiceSearch;
import proto.Microsoft.MicrosoftVoice;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String... args) throws Exception {

        GoogleVoiceSearch google = new GoogleVoiceSearch();
        MicrosoftVoice microsoft = new MicrosoftVoice();
        IbmVoiceSearch ibm = new IbmVoiceSearch();
        WriteToSpreadSheet sheet = new WriteToSpreadSheet();
        Main m = new Main();
        sheet.initialise();
        String recordingsHomeDir = "AudioRecordings";
        //For Current folder
        File[] folders = new File(recordingsHomeDir).listFiles();

        spoofApi spoof = new spoofApi();
        spoof.initialise();
        showFiles(recordingsHomeDir, folders, m, null, spoof, microsoft, google, ibm, sheet);

        sheet.createSheet();
        sheet.writeToFile(sheet.getWb()); //Write data to spreadsheet

    }


    public static void showFiles(String recordingsHomeDir, File[] files, Main m, String directoryName, spoofApi spoof, MicrosoftVoice microsoft, GoogleVoiceSearch google, IbmVoiceSearch ibm, WriteToSpreadSheet sheet) throws IOException {
        String fileName = null;

        System.out.println("recordingsHomeDir = [" + recordingsHomeDir + "], files = [" + files + "], m = [" + m + "], directoryName = [" + directoryName + "], spoof = [" + spoof + "], microsoft = [" + microsoft + "], sheet = [" + sheet + "]");
        try {
            for (File file : files) {
                if (file.isDirectory()) {

                    if (!file.getName().contains(".DS_Store")) {
                        directoryName = file.getName();
                    System.out.println("directoryName = " + directoryName);
                    }
                    showFiles(recordingsHomeDir, file.listFiles(), m, directoryName, spoof, microsoft, google, ibm, sheet); // Calls same method again.
                } else {
                    fileName = file.getName();
                    if (!fileName.contains(".DS_Store")) { //Hide .DS_Store files on MAC
//                    System.out.println("fileName = " + fileName);
//                        m.startTest(recordingsHomeDir, directoryName, fileName, spoof, sheet);
                        m.startTest(recordingsHomeDir, directoryName, fileName, ibm, sheet);
                        m.startTest(recordingsHomeDir, directoryName, fileName, microsoft, sheet);
                        m.startTest(recordingsHomeDir, directoryName, fileName, google, sheet);
                    }

                }
            }


        } catch (Exception e) {
            System.out.println("e = " + e);
            throw e;
        }
    }

    private void startTest(String recordingsHomeDir, String folderName, String fileName, IApi api, WriteToSpreadSheet sheet) {


            QualityMetrics m = new QualityMetrics();
            String response = "";
            long startTime = System.currentTimeMillis();

            //Send request with Audio File
            try {

                response = api.SendRequest(recordingsHomeDir + "/" +fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();

            //Use file name to figure out expected voice query
//            System.out.println("folderName = " + folderName);
            splitFileNameIntoParticipantDetails(fileName, m);
            m.setApiName(api.getClass().getSimpleName());
            m.setResponseTime(startTime, endTime);

            if(response!=null){
                m.setActualResult(response);
                m.setQuality();
            }else{
                m.setActualResult("N/A");
            }

            System.out.println(m.toString());

            sheet.addData(m);




    }

    public String splitFolderNameIntoPhrase(String folderName) {

        String result = "";
        if (folderName != null) {
            if (folderName.contains("-")) {
                String[] split = folderName.split("-");
                for (String s : split) {
                    result += s + " ";
                }
            } else {
                result = folderName;

            }

        } else {
            return null;
        }

//        System.out.println("result = " + result);
        return result;
    }

    private void splitFileNameIntoParticipantDetails(String fileName, QualityMetrics m) {
        fileName = fileName.substring(0, fileName.length() - 4);
        String[] split = fileName.split("-");
        System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
        int id = Integer.parseInt(split[0]);
        m.setId(id);

        int phrase = Integer.parseInt(split[1]);
        System.out.println("split = " + split[1]);
        m.setExpectedResult(phrase);


        String accent = split[2];
        m.setAccent(accent);


    }

}
