
package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitateBloc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate_bloc);

        EditText etAdresa = findViewById(R.id.etAdresa);
        EditText etApartamente = findViewById(R.id.etApartamente);
        EditText etEtaje = findViewById(R.id.etEtaje);

        CheckBox cbLift = findViewById(R.id.cbLift);
        Spinner spMaterial = findViewById(R.id.spMaterial);
        RadioGroup rgTipBloc = findViewById(R.id.rgTipBloc);
        RatingBar rbEvaluare = findViewById(R.id.rbEvaluare);
        Switch swLocuit = findViewById(R.id.swLocuit);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        String[] materiale = {
                "Beton", "Caramida", "BCA", "Structura metalica"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                materiale
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spMaterial.setAdapter(adapter);

        btnSalveaza.setOnClickListener(v -> {

            Toast.makeText(this, "Butonul functioneaza!",
                    Toast.LENGTH_SHORT).show();

            String adresa = etAdresa.getText().toString().trim();
            String textApartamente =
                    etApartamente.getText().toString().trim();
            String textEtaje = etEtaje.getText().toString().trim();

            if (adresa.isEmpty() || textApartamente.isEmpty()
                    || textEtaje.isEmpty()) {
                Toast.makeText(this,
                        "Completeaza toate campurile!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int apartamente;
            int etaje;

            try {
                apartamente = Integer.parseInt(textApartamente);
                etaje = Integer.parseInt(textEtaje);
            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Introdu numere valide!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (apartamente <= 0 || etaje <= 0) {
                Toast.makeText(this,
                        "Numerele trebuie sa fie pozitive!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int selectie = rgTipBloc.getCheckedRadioButtonId();

            if (selectie == -1) {
                Toast.makeText(this,
                        "Selecteaza Vechi sau Nou!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String tip = selectie == R.id.rbVechi ? "Vechi" : "Nou";


            Bloc bloc = new Bloc(
                    adresa,
                    apartamente,
                    etaje,
                    cbLift.isChecked()
            );

            Bundle dateBloc = new Bundle();
            dateBloc.putParcelable("obiectBloc", bloc);
            dateBloc.putString("material",
                    spMaterial.getSelectedItem().toString());
            dateBloc.putString("tip", tip);
            dateBloc.putFloat("evaluare", rbEvaluare.getRating());
            dateBloc.putBoolean("locuit", swLocuit.isChecked());

            Intent rezultat = new Intent();
            rezultat.putExtras(dateBloc);

            setResult(RESULT_OK, rezultat);
            finish();

        });
    }
}
