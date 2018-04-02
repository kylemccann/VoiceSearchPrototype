package proto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

/* This Class contains any symbols which are to be substituted for their english equivalent**/
public class symbolsDictionary {

    String[] sharp = new String[]{" hash", " sharp"};
    String[] doubleHyphen = new String[]{"double hyphen", "double dash"};
    String[] hyphen = new String[]{"hyphen", "dash"};
    String[] leftBracket = new String[]{"open bracket ", "left bracket"};
    String[] rightBracket = new String[]{"close bracket ", "right bracket"};
    String[] equalsEquals = new String[]{"double equals ", "equals equals"};
    String[] equalsEqualsEquals = new String[]{"triple equals", "equals equals equals"};
    String[] vs = new String[]{"versus "};
    String[] leftSpeechMark = new String[]{"open speech mark", "left speech mark",  "double quote"};
    String[] rightSpeechMark = new String[]{"close speech mark",  "right speech mark", "double quote"};
    String[] rightArrow = new String[]{"right arrow", "greater than"};
    String[] plusPlus = new String[]{"plus plus"};



    //In order to correctly score the returned strings we need to check for the included symbols phonetic equivalent
    public QualityMetrics removeValidSymbols(QualityMetrics m) {
        String expected = m.getExpectedResult().toLowerCase();
        String actualResult = m.getActualResult();
        Optional<String> rtn = null;

        int phraseId = m.getPhraseId();
        //Check expected result
        if (phraseId == 2) {
            //Symbol in question is c#
            rtn = stringContainsItemFromList(actualResult, sharp);

            if(rtn.isPresent()){
                String value = rtn.get();
                expected = expected.replace("#", value);
//                measuredResult = measuredResult.replace(value, "");
            }
        } else if (phraseId == 7) {
            rtn = stringContainsItemFromList(actualResult, doubleHyphen);

            if(rtn.isPresent()){
                String value = rtn.get();
                System.out.println("value = " + value);
                expected = expected.replace("--", value);
            }
        } else if (phraseId == 5) {
            //Left Bracket
            rtn = stringContainsItemFromList(actualResult, leftBracket);

            if(rtn.isPresent()){
                String value = rtn.get();
                expected = expected.replace("(", value);
//                measuredResult = measuredResult.replace(value, "");
            }

            //===
            rtn = stringContainsItemFromList(actualResult, equalsEqualsEquals);

            if(rtn.isPresent()){
                String value = rtn.get();
                expected = expected.replace("===", value);
            }

            //==
            rtn = stringContainsItemFromList(actualResult, equalsEquals);

            if(rtn.isPresent()){
                String value = rtn.get() + " ";
                expected = expected.replace("==", value);
            }

            System.out.println("measuredResult = " + actualResult);

            //vs
            rtn = stringContainsItemFromList(actualResult, vs);

            if(rtn.isPresent()){
                String value = rtn.get() + " ";
                expected = expected.replace("vs", value);
            }



            //right bracket
            rtn = stringContainsItemFromList(actualResult, rightBracket);

            if(rtn.isPresent()){
                String value = rtn.get() + " ";
                expected = expected.replace(")", "");
            }

        } else if (phraseId == 9){

            //hyphen
            rtn = stringContainsItemFromList(actualResult, doubleHyphen);

            if(rtn.isPresent()){
                String value = rtn.get()+ " ";
                expected = expected.replace("--", value);
            }else {

                //hyphen
                rtn = stringContainsItemFromList(actualResult, hyphen);

                if (rtn.isPresent()) {
                    String value = rtn.get() + " ";
                    expected = expected.replace("-", value);
                }
            }
            //hyphen
            rtn = stringContainsItemFromList(actualResult, rightArrow);

            if(rtn.isPresent()){
                String value = rtn.get()+ " ";
                expected = expected.replace(">", value);
            }

            //left speech marks
            rtn = stringContainsItemFromList(actualResult, leftSpeechMark);

            if(rtn.isPresent()){
                String value = rtn.get()+ " ";
                expected = expected.replaceAll("\\“",value);
            }

            //right speech amrks
            rtn = stringContainsItemFromList(actualResult, rightSpeechMark);

            if(rtn.isPresent()){
                String value = rtn.get()+ " ";
                expected = expected.replaceAll("\\”",value);
            }

            //Plus plus
            rtn = stringContainsItemFromList(actualResult, plusPlus);

            if(rtn.isPresent()){
                String value = " " +rtn.get();
                expected = expected.replaceAll("\\++",value);
            }

        }

        expected= expected.trim().replaceAll(" +", " ");
        actualResult = actualResult.trim().replaceAll(" +", " ");


        m.setTransformedResult(expected);
//        System.out.println("rtn = " + rtn);
        System.out.println("Transformed = " + expected);
        return m;
    }

    public static void main(String... args) throws Exception {
        QualityMetrics q = new QualityMetrics();

        q.setExpectedResult(2);
        q.setActualResult("c sharp");

        symbolsDictionary s = new symbolsDictionary();
        s.removeValidSymbols(q);
    }

    public static Optional<String> stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).filter(inputStr::contains).findAny();
    }
}
