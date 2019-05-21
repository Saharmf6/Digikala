package org.aut.digikala;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.aut.digikala.model.DatabaseAccess;
import org.aut.digikala.model.Product;

public class LoginActivity extends AppCompatActivity {
    TextView txtEmail;
    TextView txtPassword;
    TextView register;
    boolean userFound = false;
    Button btnLogin;
    private String password;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPwd);
        register = (TextView)findViewById(R.id.lnkRegister);
        btnLogin = findViewById(R.id.btnLogin);
        final DatabaseAccess dbAccess = new DatabaseAccess(this);

        email = getIntent().getStringExtra("email");

        /*
        Cursor productCursor = dbAccess.getDb().rawQuery("SELECT * FROM Products", null);
        productCursor.moveToFirst();
        while(!productCursor.isAfterLast()){
            int productId = productCursor.getInt(0);
            String productName = productCursor.getString(1);
            double price = productCursor.getDouble(2);
            double dicount = productCursor.getDouble(3);
            int stock = productCursor.getInt(4);
            Product product = new Product(productId,productName, price, dicount, stock, "ShortDesc", 4.5,);
            Log.d("LoginActivity", product.toString());
            productCursor.moveToNext();
        }
*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = txtPassword.getText().toString();
                email = txtEmail.getText().toString();
                Cursor userCursor = dbAccess.getDb().rawQuery("SELECT * FROM Users", null);
                userCursor.moveToFirst();
                Log.d("LoginActivity","email: " + email + " pass: " + password);
                userFound = false;
                while (!userCursor.isAfterLast()){
                    String thisEmail = userCursor.getString(3);
                    String thisPass = userCursor.getString(2);
                    if(thisPass.equals(password) && thisEmail.equals(email)) {
                        userFound = true;
                        break;
                    }
                    userCursor.moveToNext();
                }
                if(userFound){
                    Log.d("LoginActivity", "User was found");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
                else {
                    Log.d("LoginActivity", "User was not found");
                    Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                }

            }
        });
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}