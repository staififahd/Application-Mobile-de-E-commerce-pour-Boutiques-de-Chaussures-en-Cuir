package mastersidi.fste.umi.ac.ma.cuirstyle;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class AllProductsActivity extends AppCompatActivity {

    private List<Product> productList;
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddProduct;
    private EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allproducts);

        //Initialisation de la liste des produits avec les descriptions
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.ref_056, "HIGH TOP BOOT FOR MEN REF 056", "399.00 MAD", "cofortabl.\n" + "vrai cuir 100%"));
        productList.add(new Product(R.drawable.ref_051, "HIGH TOP BOOT FOR MEN REF 051", "349.00 MAD", "cofortabl.\n" + "vrai cuir 100%"));
        productList.add(new Product(R.drawable.ref_737, "HIGH TOP BOOT FOR MEN REF 737", "349.00 MAD", "cofortabl.\n" + "vrai cuir 100%"));
        productList.add(new Product(R.drawable.ref_072, "HIGH TOP BOOT FOR MEN REF 072", "499.00 MAD", "cofortabl.\n" + "vrai cuir 100%"));
        productList.add(new Product(R.drawable.ref_010, "HIGH TOP BOOT FOR MEN REF 010", "249.00 MAD", "cofortabl.\n" + "vrai cuir 100%"));
        productList.add(new Product(R.drawable.ref_082, "HIGH TOP BOOT FOR MEN REF 082", "399.00 MAD", "cofortabl.\n" + "vrai cuir 100%"));
        productList.add(new Product(R.drawable.ref_707, "HIGH TOP BOOT FOR MEN REF 707", "349.00 MAD", "cofortabl.\n" + "vrai cuir 100%"));
        productList.add(new Product(R.drawable.ref_066, "HIGH TOP BOOT FOR MEN REF 066", "399.00 MAD", "cofortabl.\n" + "vrai cuir 100%"));
        productList.add(new Product(R.drawable.ref_601, "HIGH TOP BOOT FOR MEN REF 601", "249.00 MAD", "cofortabl.\n" + "vrai cuir 100%"));

        // Initialisation de RecyclerView
        recyclerView = findViewById(R.id.product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productAdapter = new ProductAdapter(productList);

        recyclerView.setAdapter(productAdapter);

        fabAddProduct = findViewById(R.id.fab_add_product);

        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllProductsActivity.this, ActivityCart.class));
            }
        });

        // Initialisation de EditText pour rechercher des produits par nom
        nameInput = findViewById(R.id.name_input);
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        List<Product> filteredList = new ArrayList<>();

        for (Product product : productList) {
            if (product.getProductBrandName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(product);
            }
        }
        productAdapter.filterList(filteredList);
    }
}
