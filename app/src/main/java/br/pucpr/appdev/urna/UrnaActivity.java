package br.pucpr.appdev.urna;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import br.pucpr.appdev.urna.model.Candidato;
import br.pucpr.appdev.urna.model.Urna;

public class UrnaActivity extends AppCompatActivity {

    private Urna urna = Urna.getInstance();
    private Candidato candidatoSelecionado;

    private void setCandidatosOnRadioGroup() {
        RadioGroup group = findViewById(R.id.candidatosRadio);
        for (Candidato c : urna.getCandidatos()) {
            RadioButton rb = new RadioButton(this);
            rb.setText(c.getName());
            group.addView(rb);
        }
    }

    private void startRadioGroupListener() {
        RadioGroup group = findViewById(R.id.candidatosRadio);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ImageView img = findViewById(R.id.candidatoImg);
                int totalCandidatos = urna.getCandidatos().size();

                candidatoSelecionado = urna.getCandidatos().get((i - 1) % totalCandidatos);
                img.setImageResource(candidatoSelecionado.getImgId());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urna);
        setCandidatosOnRadioGroup();
        startRadioGroupListener();
        candidatoSelecionado = null;
    }

    public void onClickCancelarBtn(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void onClickConfirmarBtn(View v) {
        urna.addVoto(candidatoSelecionado);
        setResult(RESULT_OK);
        finish();
    }

}
