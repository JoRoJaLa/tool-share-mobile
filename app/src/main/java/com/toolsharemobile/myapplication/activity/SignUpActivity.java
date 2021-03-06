package com.toolsharemobile.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.google.android.material.snackbar.Snackbar;
import com.toolsharemobile.myapplication.R;

public class SignUpActivity extends AppCompatActivity {

    String TAG = "SIGN UP ACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signupButtonSetUp();


    }


    public void signupButtonSetUp(){
        LinearLayout buttonSignUpToVerify = findViewById(R.id.buttonSignUpToVerify);
        buttonSignUpToVerify.setOnClickListener(view -> {
            System.out.println("Signup signup Button!");
            Log.e(TAG, "onClick: Signup signup Button!");
            String confirmPassword = ((EditText) findViewById(R.id.editTextTextPasswordConfirm)).getText().toString();


            String email = ((EditText) findViewById(R.id.signupTextEmailAddress)).getText().toString();
            String password = ((EditText) findViewById(R.id.signupTextPassword)).getText().toString();
            String username = ((EditText) findViewById(R.id.signupTextUserame)).getText().toString();
            if(!confirmPassword.equals(password)){
                Snackbar.make(findViewById(R.id.signUpactivity), "Passwords Don't Match!", Snackbar.LENGTH_SHORT).show();
            }

            if(confirmPassword.equals(password)) {
                Amplify.Auth.signUp(
                        email,
                        password,
                        AuthSignUpOptions.builder()
                                .userAttribute(AuthUserAttributeKey.email(), email)
                                .userAttribute(AuthUserAttributeKey.preferredUsername(), username)


                                .build(),
                        good -> {
                            Log.i(TAG, "Signup completed: " + good);
                            Intent goToVerifyIntent = new Intent(SignUpActivity.this, VerifyAccountActivity.class);
                            goToVerifyIntent.putExtra(TAG, email);
                            SignUpActivity.this.startActivity(goToVerifyIntent);
                        },
                        bad -> {
                            Log.i(TAG, "Signup not completed: " + bad);
                            runOnUiThread(() ->
                            {
                                Toast.makeText(SignUpActivity.this, "Signup not successful!", Toast.LENGTH_SHORT);
                            });

                        }
                );


            }
        });

    }

}