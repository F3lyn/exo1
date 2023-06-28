package simulation.Usines;

import java.awt.*;
import java.util.ArrayList;

public class UsineMoteur extends Usine {

    private List<ComposantMetal> composantsNecessaires;

    public UsineMoteur(int id, Point position) {
        super(id, position);
        this.composantsNecessaires = new ArrayList<>();
    }

    @Override
    public void produire() {
        if (composantsNecessaires.size() >= QUANTITY_REQUIRED) {
            ComposantMoteur moteur = new ComposantMoteur();
            this.composantsProduits.add(moteur);
            this.composantsNecessaires.removeRange(0, QUANTITY_REQUIRED-1);
        }
    }
}
