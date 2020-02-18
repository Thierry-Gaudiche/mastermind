package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] tabps = new int[4];
    //initialisatoin try
    int essai = 1;
    int bestscore = 0;
    int nbessais= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void preset (View V){

        //nbr couleur
        EditText nbcolorText1 = findViewById(R.id.nbcolor);
        String nbcolorString1 = (nbcolorText1.getText().toString());
        int nbcolor = (Integer.parseInt(nbcolorString1));

        // creatoin random ps
        for (int i = 0; i < tabps.length; i++) {
            tabps[i] = new Random().nextInt((nbcolor));
            tabps[i] = tabps[i] +1;
        }


        // initialisation nb essais
        EditText nbessaisText = findViewById(R.id.nbessais);
        String nbessaisString1 = (nbessaisText.getText().toString());
        nbessais = (Integer.parseInt(nbessaisString1));

        // initialisation bestscore
        bestscore = nbessais;
        Log.d("nbo","c rentré" + bestscore);

        //indication partie prete
        TextView changeps1 = findViewById(R.id.ps);
        changeps1.setText("la solution secrete est définie tu as " + nbessais + " essais");

        TextView changehisto2 = findViewById(R.id.histo);
        changehisto2.setText("historique de vos propositions");

    }

    public void startgame(View v) {

        // initialisation des tableaux
        int[] tabt = new int[4];
        int[] tabt_copie = new int[4];
        int[] tabps_copie = new int[4];
        // copie de ps
        System.arraycopy(tabps,0,tabps_copie,0, tabps.length);

        // initialisation bp mp
        int bp = 0;
        int mp = 0;

        // initialisation nb essais
        EditText nbessaisText = findViewById(R.id.nbessais);
        String nbessaisString1 = (nbessaisText.getText().toString());
        int nbessais = (Integer.parseInt(nbessaisString1));

        // recupération des champs text
        EditText tentText1 = findViewById(R.id.tent1);
        EditText tentText2 = findViewById(R.id.tent2);
        EditText tentText3 = findViewById(R.id.tent3);
        EditText tentText4 = findViewById(R.id.tent4);

        String tentString1 = (tentText1.getText().toString());
        String tentString2 = (tentText2.getText().toString());
        String tentString3 = (tentText3.getText().toString());
        String tentString4 = (tentText4.getText().toString());

        tabt[0] = (Integer.parseInt(tentString1));
        tabt[1] = (Integer.parseInt(tentString2));
        tabt[2] = (Integer.parseInt(tentString3));
        tabt[3] = (Integer.parseInt(tentString4));

        System.arraycopy(tabt,0,tabt_copie,0, tabps.length);

        for (int i = 0; i < tabt.length; i++) {
            if (tabt[i] == tabps[i]) {
                bp++;
                tabt[i] = -1;
                tabps[i] = -2;
            }
        }


        for (int i = 0; i < tabt.length; i++) {
            for (int j = 0; j < tabps.length; j++) {


                if (tabt[i] == tabps[j]) {
                    mp++;
                    tabt[i] = -1;
                    tabps[j] = -2;
                }
            }
        }

        // copie de ps_copie
        System.arraycopy(tabps_copie,0,tabps,0, tabps_copie.length);


        if (essai == nbessais && bp != 4){
            TextView changeps3 = findViewById(R.id.ps);
            changeps3.setText("Perdu la proposition secrete etait :" + tabps[0] + tabps[1] + tabps[2] + tabps[3]);
            TextView changehisto3 = findViewById(R.id.histo);
            String histoString3 = (changehisto3.getText().toString());
            changehisto3.setText(histoString3 + "\n" +  "votre proposition est : " + tabt_copie[0] + tabt_copie[1] + tabt_copie[2] + tabt_copie[3] + " vous avez :" + bp +"bp  et " + mp + "mp");
            essai = 0;
        }
        else {
            TextView changeps4 = findViewById(R.id.ps);
            changeps4.setText("il vous reste :" + (nbessais-essai) + "essai(s)");

            TextView changehisto1 = findViewById(R.id.histo);
            String histoString1 = (changehisto1.getText().toString());
            changehisto1.setText(histoString1 + "\n" + "votre proposition est : " + tabt_copie[0] + tabt_copie[1] + tabt_copie[2] + tabt_copie[3] + " vous avez :" + bp +"bp  et " + mp + "mp");
        }

        // verification win
        if (bp == 4) {
            TextView changeps2 = findViewById(R.id.ps);
            changeps2.setText("Bravo tu as trouvé");

            if (essai <= bestscore) {
                Log.d("nbo","c rentré");
                bestscore = (essai);
                TextView changebestscore = findViewById(R.id.bestscore);
                changebestscore.setText("ton meilleur score est " + bestscore + " sur " + nbessais);
                essai = 0;
            }
            else{
                essai = 0;
            }
        }

            Log.d("nbpb", "il y a : "+bp+" bp" );
            Log.d("nbmb", "il y a : "+mp+" mp" );
            Log.d("nb", "il reste : "+ (nbessais-essai) +" essai" );

        // essai + 1
        essai = essai + 1;
        }

    }




