package edu.du.sb1031.controller;

import edu.du.sb1031.dto.AuthInfo;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Wishlist;
import edu.du.sb1031.exception.ItemNotFoundException;
import edu.du.sb1031.repository.ItemRepository;
import edu.du.sb1031.repository.MemberRepository;
import edu.du.sb1031.repository.WishlistRepository;
import edu.du.sb1031.service.MemberService;
import edu.du.sb1031.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WishlistController {

    private final ItemRepository itemRepository;
    private final WishlistService wishlistService;
    private final MemberService memberService;

    @GetMapping("/wishlist/add/{itemId}")
    public String addWishlist(@PathVariable("itemId") Long itemId, @SessionAttribute AuthInfo authInfo) {
        if(!itemRepository.existsById(itemId)) throw new ItemNotFoundException();
        wishlistService.addWishlist(authInfo.getId(), itemId);// 체크필요잇나?


        //성공시 추가 알림 필요

        return "redirect:/item/" + itemId;
    }

    @GetMapping("/wishlists")
    public String getAllWishlists(@SessionAttribute AuthInfo authInfo, Model model) {
        model.addAttribute("wishlists", wishlistService.findByMemberId(authInfo.getId()));
        return "/wishlist/wishlist";
    }

}
