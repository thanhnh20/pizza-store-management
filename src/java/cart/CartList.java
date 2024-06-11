/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import dto.ProductDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CartList {
    public List<ProductDTO> cartList;
    
    public void addBookToCart(ProductDTO product){
        if(cartList == null){
            cartList = new ArrayList<>();
        }        
        ProductDTO bookCheck = findBookInCart(product.getProductId());
        if(bookCheck == null){
            product.setQuantity(1);
            cartList.add(product);
        }else{
            bookCheck.setQuantity(1 + bookCheck.getQuantity());
        }
    }
    
    public ProductDTO findBookInCart(int productId){
        for (ProductDTO product : cartList) {
            if(product.getProductId() == (productId)){
                return product;
            }
        }
        return null;
    }
    
    public void removeFromCart(String[] productId){
        for (String id : productId) {
            cartList.remove(findBookInCart(Integer.parseInt(id)));
        }    
    }
    
    public List<ProductDTO> getCartList() {
        return cartList;
    }
    
}
