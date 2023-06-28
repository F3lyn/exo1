package simulation.Usines;

import java.awt.*;

public class UsineMetal extends Usine {

    public UsineMetal(int id, Point position) {
        super(id, position);
    }

    @Override
    public void produire() {
        ComposantMetal metal = new ComposantMetal();
        this.composantsProduits.add(metal);
    }
}
