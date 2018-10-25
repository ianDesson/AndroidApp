package com.example.andrew.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private EditText textUsername;
    private EditText textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUsername = findViewById(R.id.username);
        textPassword = findViewById(R.id.password);

        mDatabase = FirebaseDatabase.getInstance().getReference("users");
    }

    public void login(View view) {
        String password = textPassword.getText().toString().trim();
        String username = textUsername.getText().toString().trim();
        signIn(username, password);
    }

    private void signIn(final String username, final String password) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(LoginScreen.this, "Yes", Toast.LENGTH_SHORT).show();
                if (dataSnapshot.child("admin").child(username).exists()) {
                    if (!username.isEmpty()) {
                        User login = dataSnapshot.child("users").child("admin").child(username).getValue(User.class);
                        if (login.getPassword().equals(password)){
                            Toast.makeText(LoginScreen.this, "Successful Login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginScreen.this, WelcomeScreen.class);
                            intent.putExtra("User", login);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginScreen.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginScreen.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }

                } else if (dataSnapshot.child("users").child("homeOwners").child(username).exists()) {
                    if (!username.isEmpty()) {
                        User login = dataSnapshot.child("homeOwners").child(username).getValue(User.class);
                        if (login.getPassword().equals(password)){
                            Toast.makeText(LoginScreen.this, "Successful Login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginScreen.this, WelcomeScreen.class);
                            intent.putExtra("User", login);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginScreen.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginScreen.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                } else if (dataSnapshot.child("serviceProviders").child(username).exists()) {
                    if (!username.isEmpty()) {
                        User login = dataSnapshot.child("serviceProviders").child(username).getValue(User.class);
                        if (login.getPassword().equals(password)){
                            Toast.makeText(LoginScreen.this, "Successful Login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginScreen.this, WelcomeScreen.class);
                            intent.putExtra("User", login);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginScreen.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginScreen.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
