package simulation.Usines;

import simulation.Composants.ComposantAile;
import simulation.Composants.ComposantMetal;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class UsineAile extends Usine {

    private List<ComposantMetal> composantsNecessaires;

    public UsineAile(int id, Point position) {
        super(id, position);
        this.composantsNecessaires = new ArrayList<>();
    }

    @Override
    public void produire() {
        if (composantsNecessaires.size() >= QUANTITY_REQUIRED) {
            ComposantAile aile = new ComposantAile();
            this.composantsProduits.add(aile);
            this.composantsNecessaires.removeRange(0, QUANTITY_REQUIRED-1);
        }
    }
}
