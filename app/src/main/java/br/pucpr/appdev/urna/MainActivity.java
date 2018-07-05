package br.pucpr.appdev.urna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.pucpr.appdev.urna.model.Candidato;
import br.pucpr.appdev.urna.model.Urna;

public class MainActivity extends AppCompatActivity {

    private Urna urna = Urna.getInstance();

    private void setupCandidatos() {
        Candidato kb = new Candidato("Karina Bacchi", R.drawable.kb);
        Candidato lula = new Candidato("Lula", R.drawable.lula);
        Candidato ronaldinho = new Candidato("Ronaldinho", R.drawable.r10);

        urna.addCandidato(kb);
        addCandidatoView(kb, 1);

        urna.addCandidato(lula);
        addCandidatoView(lula, 2);

        urna.addCandidato(ronaldinho);
        addCandidatoView(ronaldinho, 3);
    }


    // TODO: Adjust layout
    private void addCandidatoView(Candidato candidato, int position) {
        LinearLayout rankingCandidatos = this.findViewById(R.id.candidatos);
        LinearLayout candidatoLayout = new LinearLayout(this);
        TextView rankingPosition = new TextView(this);
        ImageView imgCandidato = new ImageView(this);
        LinearLayout infoCandidato = new LinearLayout(this);
        TextView candidatoName = new TextView(this);
        TextView candidatoVotos = new TextView(this);

        candidatoLayout.setOrientation(LinearLayout.HORIZONTAL);
        candidatoLayout.setGravity(Gravity.CENTER_VERTICAL);

        rankingPosition.setText(position + "º");
        rankingPosition.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        txtParams.setMargins(10, 10, 10, 10);
        rankingCandidatos.setLayoutParams(txtParams);

        imgCandidato.setImageResource(candidato.getImgId());

        infoCandidato.setOrientation(LinearLayout.VERTICAL);

        candidatoName.setText(candidato.getName());

        candidatoVotos.setText(getString(R.string.perc_votos_lbl) + (urna.getTotalVotos() > 0 ? ((candidato.getVotos() / urna.getTotalVotos()) * 100) + " %" : "0 %"));

        infoCandidato.addView(candidatoName);
        infoCandidato.addView(candidatoVotos);

        candidatoLayout.addView(rankingPosition);
        candidatoLayout.addView(imgCandidato);
        candidatoLayout.addView(infoCandidato);

        rankingCandidatos.addView(candidatoLayout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCandidatos();
    }








}
