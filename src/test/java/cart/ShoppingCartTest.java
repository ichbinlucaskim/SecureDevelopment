package cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    private ShoppingCart cart;
    private Catalog catalog;

    @BeforeEach
    public void setUp() throws CartException {
        catalog = new Catalog();
        cart = new ShoppingCart("ABC-12345-XY-Q", catalog);
    }

    @Test
    public void testAddItem() throws CartException {
        cart.addItem("Laptop", 1);
        assertEquals(1, cart.getItems().get("Laptop"));
    }

    @Test
    public void testInvalidCustomerId() {
        assertThrows(CartException.class, () -> new ShoppingCart("INVALID-ID", catalog));
    }

    @Test
    public void testTotalCost() throws CartException {
        cart.addItem("Laptop", 1);
        cart.addItem("Phone", 2);
        assertEquals(2800.00, cart.getTotalCost(), 0.01);
    }

    @Test
    public void testInvalidProduct() {
        assertThrows(CartException.class, () -> cart.addItem("Tablet", 1));
    }

    @Test
    public void testNegativeQuantity() {
        assertThrows(CartException.class, () -> cart.addItem("Phone", -1));
    }
}