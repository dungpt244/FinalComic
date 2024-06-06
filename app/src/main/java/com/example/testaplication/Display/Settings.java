package com.example.testaplication.Display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.testaplication.Account.ChangePassword;
import com.example.testaplication.Account.Login;
import com.example.testaplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings,container,false);
        textUsername = view.findViewById(R.id.textUsername);
        btnContact = view.findViewById(R.id.btnContact);
        btnSupport = view.findViewById(R.id.btnSupport);
        btnLanguage = view.findViewById(R.id.btnLanguge);
        btnSignOut = view.findViewById(R.id.btnSignOut);
        btnHistory = view.findViewById(R.id.btnhistory_view);
        btnFavorite = view.findViewById(R.id.btnfavorite);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        changePass = view.findViewById(R.id.ChangePass);
        textGmail  = view.findViewById(R.id.textGmail);
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), Login.class);
                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyUserName", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Xóa tất cả dữ liệu
                editor.apply(); // Lưu thay đổi

                startActivity(intent);
                getActivity().finish();
            }
        });
        showUser();
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Mọi yêu cầu hỗ trợ xin gửi về gmail quocta.gov@gmail.com", Toast.LENGTH_SHORT).show();
            }
        });
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Chức năng đang được hoàn thiện", Toast.LENGTH_SHORT).show();
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), History.class);
                startActivity(intent);
            }
        });
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Contact Me");
                builder.setMessage("Gmail : quocta.gov@gmail.com \n" + "Phone : 0943756357");
                builder.show();
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListFavorite.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private  void showUser(){
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MyUserName", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("email", "");
        if(user == null){
            return;
        }
            String[] parts = user.split("@");
            String userin = parts[0];
            textUsername.setVisibility(View.VISIBLE);
            textUsername.setText("User: "+userin);
            textGmail.setText("Email: " +user);
    }
    public TextView textGmail;
    public TextView textUsername;
    private Button btnContact;
    private Button btnSignOut;
    private Button btnHistory;
    private Button btnFavorite;
    private ImageView imgAvatar;
    private Button btnLanguage;
    private Button btnSupport;
    private Button changePass;
}