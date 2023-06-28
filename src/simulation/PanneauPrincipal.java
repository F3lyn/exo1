package simulation;


import simulation.XMLParser.UsineMetadonees;
import simulation.XMLParser.UsineSimulation;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;
    private List<UsineMetadonees> metadoneesList;
    private List<UsineSimulation> simulationList;

    public void setUsineData(List<UsineMetadonees> metadoneesList, List<UsineSimulation> simulationList) {
        this.metadoneesList = metadoneesList;
        this.simulationList = simulationList;

        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage icone = null;

        // dessin des icones
        if (metadoneesList != null && simulationList != null) {
            try {
                // Dessin des chemins
                for (UsineSimulation usineSimulation : simulationList) {
                    for (Integer idPointFinal : usineSimulation.getCheminVers()) {
                        Point pointInitial = usineSimulation.getPosition();

                        UsineSimulation pointFinalSimulation = trouveUsine(idPointFinal);
                        if (pointFinalSimulation != null) {
                            Point PointFinal = pointFinalSimulation.getPosition();
                            g.drawLine(pointInitial.x + 15, pointInitial.y + 15, PointFinal.x + 15, PointFinal.y + 15);
                        }
                    }
                }

                // Dessin des icones
                for (UsineSimulation usineSimulation : simulationList) {
                    for (UsineMetadonees usineMetadonees : metadoneesList) {
                        if (Objects.equals(usineSimulation.getType(), usineMetadonees.getType())) {
                            icone = ImageIO.read(new File(usineMetadonees.getIcone().get("vide")));
                            g.drawImage(icone, usineSimulation.getPosition().x, usineSimulation.getPosition().y, null);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // On ajoute ? la position le delta x et y de la vitesse
        //position.translate(vitesse.x, vitesse.y);
        //g.fillRect(position.x, position.y, taille, taille);
    }

    private UsineSimulation trouveUsine(int id) {
        for (UsineSimulation usine : simulationList) {
            if (usine.getId() == id) {
                return usine;
            }
        }
        return null;
    }

}