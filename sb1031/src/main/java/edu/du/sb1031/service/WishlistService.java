package edu.du.sb1031.service;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.entity.Wishlist;
import edu.du.sb1031.exception.ItemNotFoundException;
import edu.du.sb1031.exception.MemberNotFoundException;
import edu.du.sb1031.exception.WishlistNotFoundException;
import edu.du.sb1031.repository.ItemRepository;
import edu.du.sb1031.repository.MemberRepository;
import edu.du.sb1031.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Wishlist addWishlist(Long memberId, Long itemId){
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        Wishlist wishlist = Wishlist.builder()
                .member(member)
                .item(item)
                .build();
        wishlistRepository.save(wishlist);
        return wishlist;
    }

    public List<Wishlist> findByMemberId(Long memberId){
        List<Wishlist> wishlists = wishlistRepository.findByMemberIdOrderByIdDesc(memberId);
        if(wishlists.isEmpty()) throw new WishlistNotFoundException();
        return wishlists;
    }
}
