package mastersidi.fste.umi.ac.ma.cuirstyle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> productListe;
    private Context context;

    public CartAdapter(List<Product> productListe) {
        this.productListe = productListe;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.single_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Product product = productListe.get(position);

        holder.productNameTextView.setText(product.getProductBrandName());
        holder.priceTextView.setText(product.getPrice());
        holder.quantityTextView.setText(String.valueOf(product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return productListe.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView;
        TextView priceTextView;
        TextView quantityTextView;
        public CartViewHolder(View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.product_name);
            priceTextView = itemView.findViewById(R.id.cart_price_view);
            quantityTextView = itemView.findViewById(R.id.quantity_tv);
        }
    }
}
