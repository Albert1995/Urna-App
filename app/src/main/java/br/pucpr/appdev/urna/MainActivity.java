package br.pucpr.appdev.urna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.pucpr.appdev.urna.model.Candidato;
import br.pucpr.appdev.urna.model.Urna;

public class MainActivity extends AppCompatActivity {

    private Urna urna = Urna.getInstance();

    /**
     * Adiciona os candidatos inicias na Urna
     */
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

    /**
     * Adiciona os candidatos na View
     * @param candidato Candidato para adicionar
     * @param position Posição no Ranking
     */
    private void addCandidatoView(Candidato candidato, int position) {
        LinearLayout rankingCandidatos = this.findViewById(R.id.candidatos);
        LinearLayout candidatoLayout = new LinearLayout(this);
        TextView rankingPosition = new TextView(this);
        ImageView imgCandidato = new ImageView(this);
        LinearLayout infoCandidato = new LinearLayout(this);
        TextView candidatoName = new TextView(this);
        TextView candidatoVotos = new TextView(this);

        LinearLayout.LayoutParams candidatoParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        candidatoParams.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        txtParams.setMargins(10, 10, 10, 10);

        candidatoLayout.setLayoutParams(candidatoParams);
        candidatoLayout.setOrientation(LinearLayout.HORIZONTAL);
        candidatoLayout.setGravity(Gravity.CENTER);

        rankingPosition.setText(position + "º");
        rankingPosition.setGravity(Gravity.CENTER);
        rankingPosition.setLayoutParams(txtParams);

        imgCandidato.setImageResource(candidato.getImgId());

        infoCandidato.setOrientation(LinearLayout.VERTICAL);
        infoCandidato.setLayoutParams(txtParams);

        candidatoName.setText(candidato.getName());

        double votos = candidato.getVotos();
        double totalVotos = urna.getTotalVotos();
        candidatoVotos.setText(getString(R.string.perc_votos_lbl) + " " + (totalVotos > 0 ? (Math.round((votos / totalVotos) * 10000.0) / 100.0) + " %" : "0 %"));

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

    /**
     * Listener para o botão 'Votar' para chamar a Activity Urna
     */
    public void onClickVotarBtn(View v) {
        Intent intent = new Intent(this, UrnaActivity.class);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                LinearLayout rankingCandidatos = this.findViewById(R.id.candidatos);

                List<Candidato> temp = new ArrayList<>(urna.getCandidatos());
                Collections.sort(temp, new Comparator<Candidato>() {
                    @Override
                    public int compare(Candidato c1, Candidato c2) {
                        if (c1.getVotos() > c2.getVotos())
                            return -1;
                        else if (c1.getVotos() == c2.getVotos())
                            return 0;
                        else
                            return 1;
                    }
                });

                rankingCandidatos.removeAllViews();
                for (int pos = 1; pos <= temp.size(); pos++)
                    addCandidatoView(temp.get(pos - 1), pos);
            }
        }
    }
}
