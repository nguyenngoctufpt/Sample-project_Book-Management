package com.example.thuvienphuongnam.manhinhchao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Toast;

import com.example.thuvienphuongnam.DAO.ThuThuDAO;
import com.example.thuvienphuongnam.MainActivity;
import com.example.thuvienphuongnam.R;
import com.example.thuvienphuongnam.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ManHinhChao extends AppCompatActivity {
    ThuThuDAO dao;
    List<ThuThu> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        dao = new ThuThuDAO(ManHinhChao.this);
        list = new ArrayList<>();
        list = dao.getAll();

        if (list.size()==0){
            dao.insertadmin();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        },3000);
    }
}