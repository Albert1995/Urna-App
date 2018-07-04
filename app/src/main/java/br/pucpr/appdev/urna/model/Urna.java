package br.pucpr.appdev.urna.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Urna {

    private static final Urna ourInstance = new Urna();
    private List<Candidato> candidatos;
    private int totalVotos;

    public static Urna getInstance() {
        return ourInstance;
    }

    private Urna() {
        candidatos = new ArrayList<>();
    }

    public void addCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    public void addVoto(Candidato candidato) {
        candidato.addVoto();
        totalVotos++;
    }

    public List<Candidato> getCandidatos() {
        return Collections.unmodifiableList(candidatos);
    }

}
