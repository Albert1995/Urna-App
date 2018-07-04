package br.pucpr.appdev.urna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.pucpr.appdev.urna.model.Candidato;
import br.pucpr.appdev.urna.model.Urna;

public class MainActivity extends AppCompatActivity {

    private Urna urna = Urna.getInstance();

    private void setupCandidatos() {
        Candidato kb = new Candidato("Karina Bacchi", R.mipmap.kb);
        Candidato lula = new Candidato("Lula", R.mipmap.lula);
        Candidato ronaldinho = new Candidato("Ronaldinho", R.mipmap.r10);

        urna.addCandidato(kb);
        urna.addCandidato(lula);
        urna.addCandidato(ronaldinho);
    }

    private void addCandidatoView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupCandidatos();
        setContentView(R.layout.activity_main);
    }








}
