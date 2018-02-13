package proto.Evaluator;

public class Participant {

    private String name;
    private String emailAddress;
    private String nationality;
    private int id;


    public void getCurrentID(){
        int id = 1;
        //Get current MAX id from DB

        this.id = id;

    }

    public void writeParticipantToDb(){

        //Write Name
        //Write Email
        //Write Nationality
       //Write ID
       getCurrentID();
    }

}
