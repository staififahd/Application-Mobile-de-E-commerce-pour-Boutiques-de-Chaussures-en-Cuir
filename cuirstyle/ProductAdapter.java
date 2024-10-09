package mastersidi.fste.umi.ac.ma.cuirstyle;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productImageView.setImageResource(product.getImageResource());
        holder.brandNameTextView.setText(product.getProductBrandName());
        holder.priceTextView.setText(product.getPrice());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetailsActivity.class);

            intent.putExtra("productName", product.getProductBrandName());
            intent.putExtra("productDescription", product.getDescription());
            intent.putExtra("productPrice", product.getPrice());
            intent.putExtra("productImageResId", product.getImageResource());

            // Start the activity
            context.startActivity(intent);
        });
    }
    public void filterList(List<Product> filteredList) {
        productList = filteredList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productImageView;
        TextView brandNameTextView;
        TextView priceTextView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.category_image);
            brandNameTextView = itemView.findViewById(R.id.product_brand_name);
            priceTextView = itemView.findViewById(R.id.price_tv);
        }
    }

}
