package org.aut.digikala;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.aut.digikala.model.DatabaseAccess;

import java.io.BufferedReader;

public class ComposeCommentActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText edtCommentText;
    String userName, commentText, email;
    int productId;
    int commentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_comment);
        final DatabaseAccess dbAccess = new DatabaseAccess(this);
        userName = getIntent().getStringExtra("userName");
        productId = getIntent().getIntExtra("productId", 0);
        commentId = getIntent().getIntExtra("commentId", 0);
        email = getIntent().getStringExtra("email");
        edtCommentText = findViewById(R.id.edtCommentText);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentText = edtCommentText.getText().toString();
                ContentValues commentCv = new ContentValues();
                commentCv.put("comment_id", commentId);
                commentCv.put("product_id", productId);
                commentCv.put("comment_text", commentText);
                commentCv.put("user_name", userName);
                dbAccess.getDb().insert("Comments", null, commentCv);
                Toast.makeText(getApplicationContext(), "Comment was submitted", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ComposeCommentActivity.this, CommentActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("productId", productId);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}
