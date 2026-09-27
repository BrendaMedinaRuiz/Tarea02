package com.example.tarea02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CompraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        EditText etNombre = findViewById(R.id.etNombre);
        EditText etCantidad = findViewById(R.id.etCantidad);
        RadioGroup rgPelicula = findViewById(R.id.rgPelicula);
        CheckBox cbPalomitas = findViewById(R.id.cbPalomitas);
        CheckBox cbRefresco = findViewById(R.id.cbRefresco);
        CheckBox cbNachos = findViewById(R.id.cbNachos);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        Button btnRegresar = findViewById(R.id.btnRegresar);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString().trim();
                String cantidadStr = etCantidad.getText().toString().trim();

                if (nombre.isEmpty()) {
                    Toast.makeText(CompraActivity.this,
                            "Por favor escribe tu nombre", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cantidadStr.isEmpty()) {
                    Toast.makeText(CompraActivity.this,
                            "Por favor indica la cantidad de boletos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad <= 0) {
                    Toast.makeText(CompraActivity.this,
                            "La cantidad debe ser mayor a 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedId = rgPelicula.getCheckedRadioButtonId();
                String pelicula = "No seleccionada";
                double precio = 0;

                if (selectedId == R.id.rbKids) {
                    pelicula = "Cars - Aniversario";
                    precio = 75;
                } else if (selectedId == R.id.rbMarvel) {
                    pelicula = "Avengers - End game";
                    precio = 80;
                } else if (selectedId == R.id.rbComedia) {
                    pelicula = "Coyote vs Acme";
                    precio = 70;
                } else {
                    Toast.makeText(CompraActivity.this,
                            "Selecciona una película", Toast.LENGTH_SHORT).show();
                    return;
                }

                double totalExtras = 0;
                StringBuilder extras = new StringBuilder();
                if (cbPalomitas.isChecked()) {
                    extras.append("Palomitas, ");
                    totalExtras += 45;
                }
                if (cbRefresco.isChecked()) {
                    extras.append("Refresco, ");
                    totalExtras += 35;
                }
                if (cbNachos.isChecked()) {
                    extras.append("Nachos, ");
                    totalExtras += 55;
                }

                double total = (precio * cantidad) + totalExtras;

                String mensaje = " Compra confirmada\n" +
                        "Cliente: " + nombre + "\n" +
                        "Película: " + pelicula + "\n" +
                        "Boletos: " + cantidad + "\n" +
                        "Extras: " + (extras.length() > 0 ? extras.toString() : "Ninguno") + "\n" +
                        "TOTAL: $" + String.format("%.2f", total);

                Toast.makeText(CompraActivity.this, mensaje, Toast.LENGTH_LONG).show();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}