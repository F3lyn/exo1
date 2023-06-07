package simulation.XMLParser;

import java.util.HashMap;
import java.util.Map;

public class UsineMetadonees {
    private String type;
    private String sortie;
    private Map<String, Integer> entree;
    private int intervalProduction;
    private Map<String, String> icone;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSortie() {
        return sortie;
    }

    public void setSortie(String sortie) {
        this.sortie = sortie;
    }

    public int getIntervalProduction() {
        return intervalProduction;
    }

    public void setIntervalProduction(int intervalProduction) {
        this.intervalProduction = intervalProduction;
    }

    public Map<String, String> getIcone() {
        return icone;
    }

    public void setIcone(Map<String, String> icone) {
        this.icone = icone;
    }

    public Map<String, Integer> getEntree() {
        return entree;
    }

    public void setEntree(HashMap<String, Integer> entree) {
        this.entree = entree;
    }
}
