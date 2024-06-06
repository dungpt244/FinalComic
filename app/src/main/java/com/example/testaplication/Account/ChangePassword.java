package com.example.testaplication.Account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testaplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        user = findViewById(R.id.passwordNew);
        pass = findViewById(R.id.password);
        repass = findViewById(R.id.passwordNew);
        change = findViewById(R.id.btnChangePass);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickChangePass();
            }
        });
    }
    private void onClickChangePass(){
        String newPassword = repass.getText().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChangePassword.this, "Đổi mật khẩu thành công vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ChangePassword.this, Login.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
    private EditText user;
    private EditText pass;
    private Button change;
    private EditText repass;
    private EditText userverifi;
    private EditText passverifi;
}