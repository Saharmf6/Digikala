package org.aut.digikala;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.aut.digikala.model.DatabaseAccess;
import org.aut.digikala.model.Product;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<Product> productList;
    private int cartId;
    private String email;
    public int totalPrice;

    public ProductAdapter(Context mCtx, List<Product> productList, int cartNumber, String email) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.cartId = cartNumber;
        this.email = email;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Product product = productList.get(i);
        productViewHolder.productId = product.getProductId();
        productViewHolder.textViewTitle.setText(product.getProductName());
        productViewHolder.textViewRating.setText(product.getRating() + "");
        productViewHolder.textViewPrice.setText(product.getPrice()+"");
        productViewHolder.textViewDesc.setText(product.getShortdesc());
        productViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

    }

    @Override
    public int getItemCount() {

        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        int productId;
        ImageView imageView;
        TextView textViewTitle, textViewDesc, textViewRating, textViewPrice;
        Button btnAddToCart, btnComment;
        EditText edtCount;
        final DatabaseAccess dbAccess = new DatabaseAccess(mCtx);
        public ProductViewHolder(@NonNull final View itemView) {

            super(itemView);
            btnAddToCart = itemView.findViewById(R.id.btnAddOrder);
            btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ProductAdapter", "add to cart button clicked");

                    //find user from users and find her name with her email
                    Cursor userCursor = dbAccess.getDb().rawQuery("SELECT * FROM Users", null);
                    userCursor.moveToFirst();
                    String userName = new String();
                    while (!userCursor.isAfterLast()){
                        String thisEmail = userCursor.getString(3);
                        String thisPass = userCursor.getString(2);
                        if(thisEmail.equals(email)) {
                            //userFound = true;
                            userName = userCursor.getString(0);
                            break;
                        }
                        userCursor.moveToNext();
                    }
                    int count = Integer.parseInt(edtCount.getText().toString());
                    double price = Double.parseDouble(textViewPrice.getText().toString());
                    int returned = 0;
                    //insert order with the cartId you took from mainActivity
                    Cursor ordersCursor = dbAccess.getDb().rawQuery("SELECT * FROM Orders", null);
                    ContentValues cv = new ContentValues();
                    cv.put("user_name", userName);
                    cv.put("user_email", email);
                    cv.put("product_id", productId);
                    cv.put("cart_id",cartId);
                    cv.put("count", count);
                    cv.put("price", price);
                    cv.put("returned", returned);
                    dbAccess.getDb().insert("Orders", null,cv);
                    Toast.makeText(mCtx.getApplicationContext(), "Order submitted", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(mCtx, MapsActivity.class);
                    //mCtx.startActivity(intent);
                }
            });
            edtCount = itemView.findViewById(R.id.edtCount);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewRating = itemView.findViewById(R.id.textViewRating);

            btnComment = itemView.findViewById(R.id.btnComment);
            btnComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor userCursor = dbAccess.getDb().rawQuery("SELECT * FROM Users", null);
                    userCursor.moveToFirst();
                    String userName = new String();
                    while (!userCursor.isAfterLast()){
                        String thisEmail = userCursor.getString(3);
                        String thisPass = userCursor.getString(2);
                        if(thisEmail.equals(email)) {
                            //userFound = true;
                            userName = userCursor.getString(0);
                            break;
                        }
                        userCursor.moveToNext();
                    }
                    Intent intent = new Intent(mCtx, CommentActivity.class);
                    //pass username to comment page
                    intent.putExtra("userName", userName);
                    intent.putExtra("productId", productId);
                    intent.putExtra("email", email);
                    mCtx.startActivity(intent);
                }
            });
        }
    }
}
