
package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcherBloc;
    private TextView tvRezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnAdaugaBloc = findViewById(R.id.btnAdaugaBloc);
        tvRezultat = findViewById(R.id.tvRezultat);

        launcherBloc = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK
                            && result.getData() != null) {


                        Intent data = result.getData();

                        if (data != null) {
                            Bundle dateBloc = data.getExtras();

                            if (dateBloc != null) {
                                Bloc bloc;

                                if (android.os.Build.VERSION.SDK_INT >= 33) {
                                    bloc = dateBloc.getParcelable("obiectBloc", Bloc.class);
                                } else {
                                    bloc = dateBloc.getParcelable("obiectBloc");
                                }

                                if (bloc != null) {
                                    String mesaj = bloc.toString()
                                            + "\nMaterial: " + dateBloc.getString("material")
                                            + "\nTip: " + dateBloc.getString("tip")
                                            + "\nEvaluare: " + dateBloc.getFloat("evaluare")
                                            + "\nEste locuit: "
                                            + (dateBloc.getBoolean("locuit") ? "Da" : "Nu");

                                    tvRezultat.setText(mesaj);
                                }
                            }
                        }


                    } else {
                        Toast.makeText(this,
                                "Nu au fost primite date.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

        btnAdaugaBloc.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    ActivitateBloc.class
            );
            launcherBloc.launch(intent);
        });
    }
}
