package mastersidi.fste.umi.ac.ma.cuirstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    private TextView logintextView;
    private EditText nameEditText, surnameEditText, usernameEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new Database(this);

        nameEditText = findViewById(R.id.editName);
        surnameEditText = findViewById(R.id.editSurname);
        usernameEditText = findViewById(R.id.editUsername);
        passwordEditText = findViewById(R.id.editPassword);
        confirmPasswordEditText = findViewById(R.id.editConfirmPassword);
        registerButton = findViewById(R.id.RegisterBtn);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        logintextView =findViewById(R.id.textView6);
        logintextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String surname = surnameEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(username)
                || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
            return;
        } else{
            if (dbHelper.checkUser(username,password)){
                Toast.makeText(this, "User already exists, Please login !", Toast.LENGTH_LONG).show();
            }else{
                long result = dbHelper.addUser(name, surname, username, password);

                if (result != -1) {
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
