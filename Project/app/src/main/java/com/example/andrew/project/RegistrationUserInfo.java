package com.example.andrew.project;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegistrationUserInfo extends AppCompatActivity {

    private User user;
    private DatabaseReference mDatabase;

    //Used to ensure no white spaces allowed in password input
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=\\S+$)$");

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_user_info);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Added by Matt
        textInputEmail = findViewById(R.id.text_input_email);
        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            //textInputEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field can't be empty");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        }
        /*else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password can't contain white spaces");
            return false;
        }*/
        else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void continueButton(View view) {
        //get the text from the three fields
        //store them as three dif strings
        //search for the pass and username and make no spaces
        //if it fails, show warning
        //Do same thing for email
        //IF ALL THOSE ARE TRUE
        //Push to database
        //Bring to the next page (welcome page)

        //startActivity(newIntent(RegistrationUserInfo.this, WelcomeScreen.class));

        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

        String email =  textInputEmail.getEditText().getText().toString().trim();
        String username = textInputUsername.getEditText().getText().toString().trim();
        String password = textInputPassword.getEditText().getText().toString().trim();

        String input = "Email: " + email;
        input += "\n";
        input += "Username: " + username;
        input += "\n";
        input += "Password: " + password;

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        Intent intent = new Intent(RegistrationUserInfo.this,  WelcomeScreen.class);
        intent.putExtra("User", user);

        if (user instanceof Admin) {
            mDatabase.child("users").child("admin").child(user.getUsername()).setValue(user);
        } else if (user instanceof HomeOwner) {
            mDatabase.child("users").child("homeOwners").child(user.getUsername()).setValue(user);
        } else if (user instanceof ServiceProvider) {
            mDatabase.child("users").child("serviceProviders").child(user.getUsername()).setValue(user);
        }

        startActivity(intent);

    }
}