package com.example.phong.android_studentprofilemanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity implements View.OnClickListener {

    EditText edtEmail;
    EditText edtPassword;
    Button btnLogIn;
    TextView linkResetPassword;
    TextView linkSignUp;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        findViewById(R.id.btn_log_in).setOnClickListener(this);
        findViewById(R.id.link_reset_password).setOnClickListener(this);
        findViewById(R.id.link_sign_up).setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        switch (resId) {
            case R.id.btn_log_in:
                String email = edtEmail.getText().toString().trim();
                final String password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    makeToast("Enter email address!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    makeToast("Enter password");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                edtPassword.setError(getString(R.string.minimum_password));
                            } else {
                                makeToast(getString(R.string.authentication_failed));
                            }
                        } else {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
                break;
            case R.id.link_reset_password:
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.link_sign_up:
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                break;
            default:
        }
    }

    private void makeToast(String message) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}