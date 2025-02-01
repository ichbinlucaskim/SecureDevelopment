package cart;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the list of available products in the store.
 * Ensures only valid products can be added to the shopping cart.
 */
public class Catalog {
    private final Map<String, Double> products;

    public Catalog() {
        Map<String, Double> tempProducts = new HashMap<>();
        tempProducts.put("Laptop", 1200.00);
        tempProducts.put("Phone", 800.00);
        tempProducts.put("Headphones", 150.00);
        this.products = Collections.unmodifiableMap(tempProducts); // Ensures immutability
    }

    /**
     * Retrieves the price of a product.
     *
     * @param productName The name of the product.
     * @return The price of the product.
     * @throws CartException if the product does not exist.
     */
    public double getPrice(String productName) throws CartException {
        if (!products.containsKey(productName)) {
            throw new CartException("Product not found: " + productName);
        }
        return products.get(productName);
    }

    /**
     * Checks if the product exists in the catalog.
     *
     * @param productName The name of the product.
     * @return True if the product exists, false otherwise.
     */
    public boolean isValidProduct(String productName) {
        return products.containsKey(productName);
    }

    /**
     * Returns an immutable map of available products.
     *
     * @return The product catalog.
     */
    public Map<String, Double> getProducts() {
        return products;
    }
}