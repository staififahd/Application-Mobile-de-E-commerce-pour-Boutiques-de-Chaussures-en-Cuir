package mastersidi.fste.umi.ac.ma.cuirstyle;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView backButton, productImageView;
    private TextView productNameTextView, productDescriptionTextView, productPriceTextView, quantityTextView,minusButton, plusButton;
    private CardView addToCartButton;
    private Database dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        backButton = findViewById(R.id.ic_back);
        productImageView = findViewById(R.id.product_img);
        minusButton = findViewById(R.id.minus_btn);
        plusButton = findViewById(R.id.plus_btn);

        productNameTextView = findViewById(R.id.product_name);
        productDescriptionTextView = findViewById(R.id.product_description);
        productPriceTextView = findViewById(R.id.product_price);
        quantityTextView = findViewById(R.id.quantity_tv);
        addToCartButton = findViewById(R.id.add_to_cart_btn);

        String productName = getIntent().getStringExtra("productName");
        String productDescription = getIntent().getStringExtra("productDescription");
        String productPrice = getIntent().getStringExtra("productPrice");
        int productImageResId = getIntent().getIntExtra("productImageResId", 0);

        productNameTextView.setText(productName);
        productDescriptionTextView.setText(productDescription);
        productPriceTextView.setText(productPrice);
        productImageView.setImageResource(productImageResId);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementQuantity();
            }
        });


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementQuantity();
            }
        });

        dbHelper = new Database(this);

        //Add to Cart
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productName = productNameTextView.getText().toString();
                String productDescription = productDescriptionTextView.getText().toString();
                String productPrice = productPriceTextView.getText().toString();
                int quantity = Integer.parseInt(quantityTextView.getText().toString());

                // Ajouter le produit à la base de données
                long result = dbHelper.addProduct(productName, productDescription, productPrice, quantity);

                if (result != -1) {
                    Toast.makeText(ProductDetailsActivity.this, "Product added to cart", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(ProductDetailsActivity.this, "Failed to add product to cart", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void decrementQuantity() {
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        if (quantity > 1) {
            quantity--;
            quantityTextView.setText(String.valueOf(quantity));
        }
    }
    private void incrementQuantity() {
        int quantity = Integer.parseInt(quantityTextView.getText().toString());
        quantity++;
        quantityTextView.setText(String.valueOf(quantity));
    }
}
