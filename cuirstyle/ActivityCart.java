package mastersidi.fste.umi.ac.ma.cuirstyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ActivityCart extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<Product> productListe;
    private Database dbHelper;
    private ImageView cart_back_arrow,delete_cart;
    private Button check_out_btn;
    private TextView cartTotalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart_back_arrow = findViewById(R.id.cart_back_arrow);
        cartTotalPriceView = findViewById(R.id.cart_total_price_view);
        check_out_btn = findViewById(R.id.check_out_btn);
        delete_cart= findViewById(R.id.delete_cart);

        dbHelper = new Database(this);

        // Initialisation de RecyclerView
        cartRecyclerView = findViewById(R.id.cart_recyclerview);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Récupérer les produits du panier de la base de données
        productListe = dbHelper.getCartProducts();

        cartAdapter = new CartAdapter(productListe);
        cartRecyclerView.setAdapter(cartAdapter);

        cart_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        check_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityCart.this, AllProductsActivity.class));
                Toast.makeText(ActivityCart.this, "Thank you, your command is confirmed !", Toast.LENGTH_LONG).show();
            }
        });
        delete_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.clearCart();
                productListe.clear();
                cartAdapter.notifyDataSetChanged();

                // mis à jour l'affichage du prix total
                calculateAndDisplayTotalPrice();

                Toast.makeText(ActivityCart.this, "Cart is cleared", Toast.LENGTH_LONG).show();
            }
        });

        // Calculer et afficher le prix total
        calculateAndDisplayTotalPrice();
    }

    // Méthode de calcul et d'affichage du prix total
    private void calculateAndDisplayTotalPrice() {
        double totalPrice = 0.0;

        for (Product product : productListe) {
            int quantity = product.getQuantity();
            double price = Double.parseDouble(product.getPrice().replace("MAD", ""));
            totalPrice += quantity * price;
        }

        // Afficher le prix total
        cartTotalPriceView.setText( totalPrice+" MAD" );
    }
}
