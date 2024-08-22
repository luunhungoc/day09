package mvc.session;

import mvc.entity.ProductEntity;

import java.util.List;

public class CartSession {
    List<CartSession> cartSessionList;
    ProductEntity product;
    private int quantity;

    public ProductEntity getProduct() {
        return product;
    }
    public CartSession(ProductEntity product, int quantity) {
        this.product=product;
        this.quantity = quantity;
    }

    public void addItem(ProductEntity product){
        CartSession cartSession=new CartSession();
        cartSession.setProduct(product);
        cartSession.setQuantity(1);

    }

    public List<CartSession> getCartSessionList() {
        return cartSessionList;
    }

    public void setCartSessionList(List<CartSession> cartSessionList) {
        this.cartSessionList = cartSessionList;
    }

    public CartSession() {
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartSession{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

}
