package edu.du.sb1031.service;

import edu.du.sb1031.entity.Cart;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.exception.CartNotFoundException;
import edu.du.sb1031.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void add(Cart cart) {
        Cart existCart = cartRepository.findByMemberAndItem(cart.getMember(), cart.getItem());
        if(existCart != null){
            existCart.setQuantity(existCart.getQuantity() + cart.getQuantity());
            save(existCart);
            return;
        }
        save(cart);
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }

    public void save(Cart cart){
        cartRepository.save(cart);
    }

    public List<Cart> findByMember(Member member) {
        return cartRepository.findByMember(member);
    }

    public Cart findByMemberAndItem(Member member, Item item) {
        return cartRepository.findByMemberAndItem(member, item);
    }

    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

}
