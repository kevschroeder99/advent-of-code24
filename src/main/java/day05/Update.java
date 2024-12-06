package day05;

import java.util.List;

public class Update {

    private List<String> orderingRules;
    private String update;

    public Update(List<String> orderingRules, String update) {
        this.orderingRules = orderingRules;
        this.update = update;
    }

    public boolean isUpdateCorrect(){
        String[] updateSplitted = this.update.split(",");
        //String[] orderingRules = this.orderingRules.toArray(String[]::new);

        for(int i = 0; i < update.length(); i++){
            String current = updateSplitted[i];
            //orderingRules.
        }

        return false;

    }
}
