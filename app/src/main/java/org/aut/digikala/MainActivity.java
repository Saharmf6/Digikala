package org.aut.digikala;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.aut.digikala.model.DatabaseAccess;
import org.aut.digikala.model.Product;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //https://www.youtube.com/watch?v=a4o9zFfyIM4
    RecyclerView recyclerView;
    //Button btnAddCart;
    ProductAdapter adapter;
    List<Product> productList;
    public String email;
    public int cartId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatabaseAccess dbAccess = new DatabaseAccess(this);
        email = getIntent().getStringExtra("email");

        Log.d("MainActivity", "onCreate");

        productList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.d("MainActivity", "onCreate");

        final Button btnAddCart = (Button) findViewById(R.id.btnAddCart);
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "addCart clicked");
                double totalPrice = 0;
                Cursor orderCursor = dbAccess.getDb().rawQuery("SELECT * FROM Orders", null);
                orderCursor.moveToFirst();
                while (!orderCursor.isAfterLast()){
                    if(orderCursor.getInt(3) == cartId) {
                        totalPrice += orderCursor.getDouble(5) * orderCursor.getInt(4);
                    }
                    orderCursor.moveToNext();
                }

                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                Cursor cartCursor = dbAccess.getDb().rawQuery("SELECT * FROM Carts", null);
                ContentValues cartCv = new ContentValues();
                cartCv.put("cart_id", cartId);
                cartCv.put("order_date", formattedDate);
                cartCv.put("deliver_date", formattedDate);
                cartCv.put("is_delivered", 1);
                cartCv.put("price", totalPrice);
                dbAccess.getDb().insert("Carts", null, cartCv);

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        Cursor productCursor = dbAccess.getDb().rawQuery("SELECT * FROM Products", null);
        productCursor.moveToFirst();
        int[] image = {R.drawable.pearl, R.drawable.earrings, R.drawable.necklace ,R.drawable.watch, R.drawable.ring};//new int[5];
        int i = 0;
        while(!productCursor.isAfterLast()){
            int id = productCursor.getInt(0);
            String name = productCursor.getString(1);
            double price = productCursor.getDouble(2);
            double discount = productCursor.getDouble(3);
            int stock = productCursor.getInt(4);
            String shortDesc = productCursor.getString(5);
            double rating = productCursor.getDouble(6);
            productList.add(new Product(id,name,price,discount,stock,shortDesc,rating, image[i]));
            i++;
            productCursor.moveToNext();
        }
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        cartId = random.nextInt();
        //creating recyclerview adapter
        ProductAdapter adapter = new ProductAdapter(this, productList, cartId, email);
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }
}
