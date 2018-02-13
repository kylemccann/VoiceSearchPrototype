package proto.Evaluator;

import java.util.ArrayList;

public class Queries {
    private ArrayList queries = new ArrayList();

    public Queries(){
        queries.add("Sail");
        queries.add("Aluminium");
        queries.add("Worcestershire");
        queries.add("How can I download Fusion");
        queries.add("Where can I buy a sail for a boat");
        queries.add("Boats for sale");
        queries.add("What is Ford's tops selling car");
        queries.add("Lets recognise speech");
        queries.add("Lets wreck a nice beach");
    }

    private int count =0;

    public String getCurrentQuery(){
        String rtn = "";
        if(count!=queries.size()) {
            rtn = (String) queries.get(count);

            count++;
        }else{
            //If the user has completed the study
            rtn = "Thank you for completing the Voice Search Study.";
        }
        return rtn;
    }
}
