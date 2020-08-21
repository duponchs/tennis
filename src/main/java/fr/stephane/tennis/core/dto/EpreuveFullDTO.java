package fr.stephane.tennis.core.dto;


import fr.stephane.tennis.core.entity.Tournoi;



public class EpreuveFullDTO {

    private Long id;
    private Short annee;

    private TournoiDTO tournoi;

    private Character typeEpreuve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getAnnee() {
        return annee;
    }

    public void setAnnee(Short annee) {
        this.annee = annee;
    }

    public TournoiDTO getTournoi() {
        return tournoi;
    }

    public void setTournoi(TournoiDTO tournoi) {
        this.tournoi = tournoi;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }
}
