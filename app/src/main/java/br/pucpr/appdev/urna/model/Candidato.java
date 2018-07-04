package br.pucpr.appdev.urna.model;

public class Candidato {

    private String name;
    private int imgId;
    private int votos;

    public Candidato(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
        this.votos = 0;
    }

    void addVoto() {
        votos++;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }

    public int getVotos() {
        return votos;
    }
}
