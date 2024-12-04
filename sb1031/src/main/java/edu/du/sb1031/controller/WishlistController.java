package edu.du.sb1031.controller;

import edu.du.sb1031.exception.ItemNotFoundException;
import edu.du.sb1031.repository.ItemRepository;
import edu.du.sb1031.service.AuthService;
import edu.du.sb1031.service.MemberService;
import edu.du.sb1031.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class WishlistController {

    private final ItemRepository itemRepository;
    private final WishlistService wishlistService;
    private final MemberService memberService;
    private final AuthService authService;

    @GetMapping("/wishlist/add/{itemId}")
    public String addWishlist(@PathVariable("itemId") Long itemId) {
        if(!itemRepository.existsById(itemId)) throw new ItemNotFoundException();
        wishlistService.addWishlist(authService.getCurrentMember().getId(), itemId);// 체크필요잇나?


        //성공시 추가 알림 필요

        return "redirect:/item/" + itemId;
    }

    @GetMapping("/wishlists")
    public String getAllWishlists(Model model) {
        model.addAttribute("wishlists", wishlistService.findByMemberId(authService.getCurrentMember().getId()));
        return "/wishlist/wishlist";
    }

}
