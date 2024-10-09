package mastersidi.fste.umi.ac.ma.cuirstyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.text.TextUtils;
import android.view.View;

import android.os.Bundle;
public class LoginActivity extends AppCompatActivity {
    private TextView registerTextView;
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new Database(this);

        usernameEditText = findViewById(R.id.editText);
        passwordEditText = findViewById(R.id.editPassword);
        loginButton = findViewById(R.id.LoginBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        registerTextView = findViewById(R.id.textView6);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });
    }

    private void loginUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_LONG).show();
            return;
        }

        if (dbHelper.checkUser(username, password)) {
            // Successful login
            Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this, AllProductsActivity.class));
        } else {
            // Login failed
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show();
        }
    }
}
