package edu.du.sb1031.controller;

import edu.du.sb1031.dto.*;
import edu.du.sb1031.entity.*;
import edu.du.sb1031.repository.ItemRepository;
import edu.du.sb1031.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item/")
public class ItemController {
    private final ItemService itemService;
    private final ReviewService reviewService;
    private final MemberService memberService;
    private final StockService stockService;
    private final CategoryService categoryService;
    private final ItemRepository itemRepository;

    @GetMapping("/add")
    public String addItem(Model model) {
        ItemFormCommand command = new ItemFormCommand();
        command.setCategories(categoryService.findAll());
        model.addAttribute("command", command);
        return "/item/itemForm_needUpdate";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute ItemFormCommand command) throws IOException {
        try {
            String contentType = command.getImageFile().getContentType();
            String savePath = Define.ITEMIMAGESPATH + "/" + command.getItem().getName();
            switch (contentType) {
                case "image/jpeg":
                    savePath += ".jpeg";
                    break;
                case "image/png":
                    savePath += ".png";
                    break;
                case "image/gif":
                    savePath += ".gif";
                    break;
                case "image/jpg":
                    savePath += ".jpg";
                    break;
                default:
                    throw new Exception("Not image or Not Apply Content type");
            }
            command.getImageFile().transferTo(new File(savePath));
//            command.getItem().setImageName(command.getItem().getName() + contentType); // 뒤에 경로명 넣어야함
            itemService.save(command.getItem());
            System.out.println("itemController.addItem() 성공?");
        } catch (Exception e) {
            System.out.println("itemController.addItem() 실패");
        }
        return "redirect:/item/add";
    }

    @GetMapping("/stockIn")
    public String stockPage(Model model) {
        StockInForm stockInForm = new StockInForm(new StockDto(), categoryService.findChildren(), itemRepository.findAll());
        model.addAttribute("stockInForm", stockInForm);
        return "/item/stockIn";
    }

    @GetMapping("/byCategory/{categoryId}")
    @ResponseBody  // JSON 형식으로 반환
    public List<Map<String, Object>> getItemsByCategory(@PathVariable Long categoryId) {
        List<Object[]> items = itemRepository.findByCategoryIdByStockDto(categoryId);
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] item : items) {
            Map<String, Object> itemMap = new HashMap<>();
            itemMap.put("id", item[0]);
            itemMap.put("name", item[1]);
            response.add(itemMap);
        }
        return response;  // 카테고리 ID로 아이템 리스트 조회
    }

    @PostMapping("/stockIn")
    public String stockIn(@ModelAttribute StockInForm stockInForm, @SessionAttribute AuthInfo authInfo) {
        StockDto stockDto = stockInForm.getStockDto();
        stockService.stockIn(stockDto.getItemId(), stockDto.getQuantity(), authInfo.getId());
        return "redirect:/";
    }


    @PostMapping
    public void save(Item item) {
        itemService.save(item);
    }

    @GetMapping("/{id}")
    public String itemDetail(@PathVariable Long id, Model model) {
        Item item = itemService.findById(id);
        List<Review> reviews = reviewService.findByItem(item);
        model.addAttribute("item", item);
        model.addAttribute("reviews", reviews);
        Double ratingAvg = null;
        if (!reviews.isEmpty()) {
            ratingAvg = 0.0;
            for (Review review : reviews) {
                ratingAvg += review.getRating();
            }
            ratingAvg /= reviews.size();
        }
        model.addAttribute("rating_avg", ratingAvg);
        return "/item/item_page";
    }

    @GetMapping("/payment/{id}")
    public String itemPayment(@PathVariable(name = "id") Long itemId, HttpServletRequest request, Model model) {
        try {
            Long memberId = ((AuthInfo) request.getSession().getAttribute("authInfo")).getId();
            Member member = memberService.findById(memberId);
            Item item = itemService.findById(itemId);
            model.addAttribute("item", item);

            model.addAttribute("orderTotal", item.getPrice());
            model.addAttribute("deliveryCost", Define.DELIVERY); // 임의의 값
            PaymentWrapper pw = new PaymentWrapper();
            pw.getCarts().add(new Cart(null, null, null, 1));
            model.addAttribute("paymentWrapper", pw);
        } catch (Exception e) {
            System.out.println("ItemPaymentController.itemPayment: 인증 실패");
        }
        return "/item/payment";
    }

}
