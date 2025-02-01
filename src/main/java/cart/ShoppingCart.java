package cart;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Represents a secure shopping cart that follows best security practices.
 * Implements data integrity, immutability, and validation for a secure system.
 */
public final class ShoppingCart {
    private final UUID cartId;
    private final String customerId;
    private final Map<String, Integer> items;
    private final Catalog catalog;
    private static final int MAX_QUANTITY = 100;
    private static final Pattern CUSTOMER_ID_PATTERN = Pattern.compile("^[A-Z]{3}-\\d{5}-[A-Z]{2}-[AQ]$");

    /**
     * Creates a new shopping cart.
     *
     * @param customerId The ID of the customer, which must follow the format: "XXX-12345-XX-[A|Q]".
     * @param catalog    The product catalog.
     * @throws CartException if the customer ID format is invalid.
     */
    public ShoppingCart(String customerId, Catalog catalog) throws CartException {
        if (!CUSTOMER_ID_PATTERN.matcher(customerId).matches()) {
            throw new CartException("Invalid customer ID format: " + customerId);
        }
        this.cartId = UUID.randomUUID(); // Generates a UUID v4
        this.customerId = customerId;
        this.items = new HashMap<>();
        this.catalog = catalog;
    }

    public UUID getCartId() {
        return cartId;
    }

    public String getCustomerId() {
        return customerId;
    }

    /**
     * Returns an immutable map of items in the cart.
     *
     * @return The items in the cart with their quantities.
     */
    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items); // Ensures immutability
    }

    /**
     * Adds an item to the cart.
     *
     * @param productName The product name.
     * @param quantity    The quantity to add (must be positive and within limits).
     * @throws CartException if validation fails.
     */
    public void addItem(String productName, int quantity) throws CartException {
        validateItem(productName, quantity);
        items.put(productName, items.getOrDefault(productName, 0) + quantity);
    }

    /**
     * Updates the quantity of an existing item in the cart.
     *
     * @param productName The product name.
     * @param quantity    The new quantity.
     * @throws CartException if the item does not exist or the quantity is invalid.
     */
    public void updateItem(String productName, int quantity) throws CartException {
        validateItem(productName, quantity);
        if (!items.containsKey(productName)) {
            throw new CartException("Product not in cart: " + productName);
        }
        items.put(productName, quantity);
    }

    /**
     * Removes an item from the cart.
     *
     * @param productName The product name to remove.
     * @throws CartException if the item is not in the cart.
     */
    public void removeItem(String productName) throws CartException {
        if (!items.containsKey(productName)) {
            throw new CartException("Product not in cart: " + productName);
        }
        items.remove(productName);
    }

    /**
     * Calculates the total cost of all items in the cart.
     *
     * @return The total price.
     * @throws CartException if any item in the cart is invalid.
     */
    public double getTotalCost() throws CartException {
        double total = 0.0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            total += catalog.getPrice(entry.getKey()) * entry.getValue();
        }
        return total;
    }

    /**
     * Validates an item before adding or updating it in the cart.
     *
     * @param productName The product name.
     * @param quantity    The quantity (must be within limits).
     * @throws CartException if validation fails.
     */
    private void validateItem(String productName, int quantity) throws CartException {
        if (!catalog.isValidProduct(productName)) {
            throw new CartException("Invalid product: " + productName);
        }
        if (quantity <= 0 || quantity > MAX_QUANTITY) {
            throw new CartException("Invalid quantity: " + quantity + " (must be 1-" + MAX_QUANTITY + ")");
        }
    }
}