package proto;

import java.util.ArrayList;

public class spoofApi implements IApi {
    ArrayList results = new ArrayList();
    private static int count =0;

    public void initialise(){
        results.add("how can I download Fusion");
        results.add("aluminium");
        results.add("let's read on a speech");
        results.add("sale");
        results.add("where can I buy a sail for a boat");
        results.add("Edinboro");
        results.add("boats for sale");
        results.add("what is Ford's top-selling car");
        results.add("Worcestershire");
        results.add("lets recognise beech");

    }

    public void setCount(){
        this.count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String SendRequest(String filePath) throws Exception {
        String rtn = (String) results.get(getCount());
        setCount();

        return rtn;
    }
}
