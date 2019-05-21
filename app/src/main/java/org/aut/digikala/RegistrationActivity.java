package org.aut.digikala;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.aut.digikala.MainActivity;
import org.aut.digikala.R;
import org.aut.digikala.model.DatabaseAccess;

/**
 * Created by tutlane on 08-01-2018.
 */

public class RegistrationActivity extends AppCompatActivity {
    Button btnLogin;
    TextView txtPassword, txtName;
    TextView txtEmail, inkLogin;// = findViewById(R.id.txtEmailReg);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final DatabaseAccess dbAccess  = new DatabaseAccess(this);
        txtName = findViewById(R.id.txtNameReg);
        txtPassword = findViewById(R.id.txtPwdReg);
        txtEmail = findViewById(R.id.txtEmailReg);
        inkLogin = findViewById(R.id.lnkLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor userCursor = dbAccess.getDb().rawQuery("SELECT * FROM Users", null);
                ContentValues usercv = new ContentValues();
                usercv.put("user_name", txtName.getText().toString());
                usercv.put("user_id", userCursor.getCount());
                usercv.put("password", txtPassword.getText().toString());
                usercv.put("email", txtEmail.getText().toString());

                Log.d("RegistrationActivity",txtName.getText().toString() + " " +
                        userCursor.getCount()+1 + " " +
                        txtPassword.getText().toString() + " " +
                        txtEmail.getText().toString());

                dbAccess.getDb().insert("Users", null, usercv);

                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                intent.putExtra("email", txtEmail.getText().toString());
                startActivity(intent);

            }
        });
        TextView login = (TextView)findViewById(R.id.lnkLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                intent.putExtra("email", txtEmail.getText().toString());
                startActivity(intent);
            }
        });
    }
}