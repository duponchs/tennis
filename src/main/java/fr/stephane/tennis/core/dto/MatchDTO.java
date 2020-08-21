package fr.stephane.tennis.core.dto;

import fr.stephane.tennis.core.entity.Epreuve;
import fr.stephane.tennis.core.entity.Score;



public class MatchDTO {

    private Long id;
    private JoueurDTO vainqueur;
    private JoueurDTO finaliste;
    private EpreuveFullDTO epreuve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JoueurDTO getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(JoueurDTO vainqueur) {
        this.vainqueur = vainqueur;
    }

    public JoueurDTO getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(JoueurDTO finaliste) {
        this.finaliste = finaliste;
    }

    public EpreuveFullDTO getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(EpreuveFullDTO epreuve) {
        this.epreuve = epreuve;
    }
}
