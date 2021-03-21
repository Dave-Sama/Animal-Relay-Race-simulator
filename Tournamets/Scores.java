package Tournamets;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scores {
    private Map<String, Date> scores;

    public Scores() {
        //Map<String, Date> scores = new HashMap<>();
        Map<String, Date> scores = Collections.synchronizedMap(new HashMap<>());
    }

    public void Add(String name){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
        scores.put(name,date);
    }

    public Map<String, Date> GetAll(){
        return scores;
    }
}
