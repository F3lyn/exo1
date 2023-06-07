package simulation.XMLParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import simulation.FenetrePrincipale;
import simulation.PanneauPrincipal;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class XMLParser {
    private final File file;
    private final String filePath;
    private final List<UsineMetadonees> listeMetadonees;
    private final List<UsineSimulation> listeSimulation;


    public XMLParser(File file) {
        this.file = file;
        filePath = file.getAbsolutePath();
        this.listeMetadonees = new ArrayList<>();
        this.listeSimulation = new ArrayList<>();
    }

    public void parse() {
        try {
            System.out.println(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            // Parsing des metadonees

            // Récupère l'élément 'metadonnees' du document XML.
            Element metadonneesElement = (Element) doc.getElementsByTagName("metadonnees").item(0);

            // doc.getElementsByTagName("metadonnees") renvoie une liste de tous les éléments 'metadonnees'.
            // .item(0) est utilisé pour obtenir le premier (et dans ce cas, le seul) élément de cette liste.

            // extraction par type d'usine
            NodeList nodesMetadonnee = metadonneesElement.getElementsByTagName("usine");
            for (int i = 0; i < nodesMetadonnee.getLength(); i++) {
                Element element = (Element) nodesMetadonnee.item(i);
                UsineMetadonees metadonees = new UsineMetadonees();
                metadonees.setType(element.getAttribute("type"));

                // Extraction des icônes
                NodeList nodesIcone = element.getElementsByTagName("icone");
                HashMap<String, String> icone = new HashMap<>();
                for (int j = 0; j < nodesIcone.getLength(); j++) {
                    Element elementIcone = (Element) nodesIcone.item(j);
                    icone.put(elementIcone.getAttribute("type"), elementIcone.getAttribute("path"));
                }
                metadonees.setIcone(icone);

                // Extraction des entree pour les different usines
                NodeList nodesEntree = element.getElementsByTagName("entree");
                HashMap<String, Integer> entree = new HashMap<>();
                for (int k = 0; k < nodesEntree.getLength(); k++) {
                    Element elementEntree = (Element) nodesEntree.item(k);
                    if (elementEntree.hasAttribute("quantite")) {
                        entree.put(elementEntree.getAttribute("type"), Integer.parseInt(elementEntree.getAttribute("quantite")));
                    }
                    if (elementEntree.hasAttribute("capacite")) {
                        entree.put(elementEntree.getAttribute("type"), Integer.parseInt(elementEntree.getAttribute("capacite")));
                    }

                }
                metadonees.setEntree(entree);

                // Extraction des sorties pour les different usines
                NodeList nodesSortie = element.getElementsByTagName("sortie");
                for (int l = 0; l < nodesSortie.getLength(); l++) {
                    Element elementSortie = (Element) nodesSortie.item(l);
                    metadonees.setSortie(elementSortie.getAttribute("type"));
                }

                // Extraction de l'intervalle de production pour les different usines
                if (element.getElementsByTagName("interval-production").getLength() > 0) {
                    metadonees.setIntervalProduction(Integer.parseInt(element.getElementsByTagName("interval-production").item(0).getTextContent()));
                }

                listeMetadonees.add(metadonees);
            }

            // Récupère l'élément 'metadonnees' du document XML.
            Element simulationElement = (Element) doc.getElementsByTagName("simulation").item(0);

            // doc.getElementsByTagName("simulation") renvoie une liste de tous les éléments 'simulation'.
            // .item(0) est utilisé pour obtenir le premier (et dans ce cas, le seul) élément de cette liste.

            // extraction par type d'usine
            NodeList nodesSimulation = simulationElement.getElementsByTagName("usine");
            for (int i = 0; i < nodesSimulation.getLength(); i++) {
                Element element = (Element) nodesSimulation.item(i);
                UsineSimulation simulation = new UsineSimulation();
                simulation.setType(element.getAttribute("type"));
                simulation.setId(Integer.parseInt(element.getAttribute("id")));
                simulation.setPosition(new Point(Integer.parseInt(element.getAttribute("x")), Integer.parseInt(element.getAttribute("y"))));


                NodeList nodesChemins = simulationElement.getElementsByTagName("chemin");
                for (int j = 0; j < nodesChemins.getLength(); j++) {
                    Element elementChemin = (Element) nodesChemins.item(j);
                    if (simulation.getId() == Integer.parseInt(elementChemin.getAttribute("de"))) {
                        simulation.addCheminVers(Integer.parseInt(elementChemin.getAttribute("vers")));
                    }

                }

                listeSimulation.add(simulation);
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        for (UsineMetadonees metadonees : listeMetadonees) {
            System.out.println("Type: " + metadonees.getType());
            System.out.println("Icone: " + metadonees.getIcone());
            System.out.println("Entree: " + metadonees.getEntree());
            System.out.println("Sortie: " + metadonees.getSortie());
            System.out.println("IntervalProduction: " + metadonees.getIntervalProduction());
            System.out.println("-----------------------------------------");
        }
        System.out.println("////////////////////////////////////////////////");
        System.out.println("-----------------COORDONEES------------------");
        for (UsineSimulation simulation : listeSimulation) {
            System.out.println("Type: " + simulation.getType() + "  " + "id: " + simulation.getId() + "   " + "x: " + simulation.getPosition().x + "  " + "y: " + simulation.getPosition().y);
        }
        System.out.println("-----------------CHEMINS------------------");

        for (UsineSimulation simulation : listeSimulation) {
            System.out.println("Chemin de: " + simulation.getId()+ "  " + "vers:" + simulation.getCheminVers());
        }

        System.out.println("////////////////////////////////////////////////");

        PanneauPrincipal panneauPrincipal = FenetrePrincipale.getPanneauPrincipal();
        panneauPrincipal.setUsineData(listeMetadonees, listeSimulation);


    }

    public List<UsineMetadonees> getMetadoneesList() {
        return  this.listeMetadonees;
    }

    public List<UsineSimulation> getSimulationList() {
        return this.listeSimulation;
    }
}
