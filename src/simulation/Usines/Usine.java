package simulation.Usines;

import simulation.Composants.Composant;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public abstract class Usine {

    protected int id;
    protected Point position;
    protected List<Composant> composantsProduits;

    public Usine(int id, Point position) {
        this.id = id;
        this.position = position;
        this.composantsProduits = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public List<Composant> getComposantsProduits() {
        return composantsProduits;
    }

    public abstract void produire();

}
