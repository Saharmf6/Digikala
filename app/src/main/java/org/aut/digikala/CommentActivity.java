package org.aut.digikala;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.aut.digikala.model.DatabaseAccess;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    ListView listView;
    int productId;
    String userName, email;
    Button btnCompose, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        listView = (ListView) findViewById(R.id.listview);
        ArrayList<String> arrayList = new ArrayList<>();

        email = getIntent().getStringExtra("email");
        productId = getIntent().getIntExtra("productId", 0);
        userName = getIntent().getStringExtra("userName");
        final DatabaseAccess dbAccess = new DatabaseAccess(this);
        final Cursor commentsCursor = dbAccess.getDb().rawQuery("SELECT * FROM Comments", null);
        commentsCursor.moveToFirst();
        while(!commentsCursor.isAfterLast()){
            int commentId = commentsCursor.getInt(0);
            int commentProductId = commentsCursor.getInt(1);
            String commentText = commentsCursor.getString(2);
            String commentUserName = commentsCursor.getString(3);
            if(commentProductId == productId)
                arrayList.add("Comment by " + commentUserName + ": \n" + commentText);
            commentsCursor.moveToNext();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        btnCompose = findViewById(R.id.btnCompose);
        btnCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, ComposeCommentActivity.class);
                intent.putExtra("userName", userName);
                intent.putExtra("productId", productId);
                int commentId = commentsCursor.getCount();
                intent.putExtra("commentId", commentId);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(CommentActivity.this, MainActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}
