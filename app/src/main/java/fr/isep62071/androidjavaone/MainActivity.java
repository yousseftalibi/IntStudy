package fr.isep62071.androidjavaone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    boolean isFirstnameOk = false;
    boolean isNationalityOk = false;
    boolean isDestinationOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button beginButton = findViewById(R.id.begin_button);
        TextInputEditText firstNameEditText = findViewById(R.id.editText);

        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                isFirstnameOk = !editable.toString().isEmpty();
                beginButton.setEnabled(isFirstnameOk && isNationalityOk && isDestinationOk);
            }
        });

        Spinner spinner = findViewById(R.id.nationality_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nationalities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                isNationalityOk = position!=0;
                beginButton.setEnabled(isFirstnameOk && isNationalityOk && isDestinationOk);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                isNationalityOk = false;
                beginButton.setEnabled(false);
            }

        });

        Spinner destinationSpinner = findViewById(R.id.destination_spinner);
        ArrayAdapter<CharSequence> destinationAdapter = ArrayAdapter.createFromResource(this, R.array.destination_countries_array, android.R.layout.simple_spinner_item);
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(destinationAdapter);

        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                isDestinationOk = position != 0;
                beginButton.setEnabled(isFirstnameOk && isNationalityOk && isDestinationOk);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                isDestinationOk = false;
                beginButton.setEnabled(false);
            }
        });

        beginButton.setOnClickListener(view -> {
            String firstName = ((TextInputEditText) findViewById(R.id.editText)).getText().toString();
            String nationality = ((Spinner) findViewById(R.id.nationality_spinner)).getSelectedItem().toString();
            String destination = ((Spinner) findViewById(R.id.destination_spinner)).getSelectedItem().toString();

            Intent intent = new Intent(MainActivity.this, PaperworkActivity.class);
            intent.putExtra("FIRST_NAME", firstName);
            intent.putExtra("NATIONALITY", nationality);
            intent.putExtra("DESTINATION", destination);
            startActivity(intent);
        });
    }
}