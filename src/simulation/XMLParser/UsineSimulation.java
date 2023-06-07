package simulation.XMLParser;

import java.awt.*;
import java.util.*;
import java.util.List;

public class UsineSimulation {
    private String type;
    private int id;
    private Point position;
    private List<Integer> cheminVers = new ArrayList<>();



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public List<Integer> getCheminVers() {
        return cheminVers;
    }
    public void addCheminVers(Integer vers) {
        this.cheminVers.add(vers);
    }


}
