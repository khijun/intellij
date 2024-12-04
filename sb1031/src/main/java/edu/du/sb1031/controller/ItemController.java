package edu.du.sb1031.controller;

import edu.du.sb1031.dto.*;
import edu.du.sb1031.entity.*;
import edu.du.sb1031.repository.ItemRepository;
import edu.du.sb1031.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    private final StockService stockService;
    private final CategoryService categoryService;
    private final ItemRepository itemRepository;
    private final ImageService imageService;
    private final AuthService authService;
    private final ItemDetailService itemDetailService;
    private final MessageSource messageSource;

    @GetMapping("/add")
    public String addItem(Model model) {
        ItemFormCommand command = new ItemFormCommand();
        command.setCategories(categoryService.findChildrenOrderByIdAsc());
        model.addAttribute("command", command);
        return "/item/itemForm";
    }

    @PostMapping("/add")
    @Transactional
    public String addItem(@ModelAttribute ItemFormCommand command) throws Exception {
        String filename = command.getItem().getName();
        String contentType = imageService.getContentType(command.getImageFile());
        imageService.saveImage(Define.ITEMIMAGESPATH, command.getImageFile(), filename);
        command.getItem().setImageName(filename + "." + contentType);
        itemService.save(command.getItem());
        Files.delete(Paths.get(Define.ITEMIMAGESPATH + "/" + filename + "." + contentType));
        return "redirect:/item/add";
    }

    @GetMapping("/stockIn")
    public String stockPage(Model model) {
        StockInForm stockInForm = new StockInForm(new StockDto(), categoryService.findChildrenOrderByIdAsc(), itemRepository.findAll());
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
    public String stockIn(@ModelAttribute StockInForm stockInForm) {
        StockDto stockDto = stockInForm.getStockDto();
        stockService.stockIn(stockDto.getItemId(), stockDto.getQuantity(), authService.getCurrentMember().getId());
        return "redirect:/";
    }

    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model) {
        model.addAttribute("itemDetailDto", itemDetailService.createItemDetailDto(itemId));
        model.addAttribute("reviewDto", ReviewDto.builder().itemId(itemId).build());
        return "/item/item_page";
    }

    @GetMapping("/payment/{id}")
    public String itemPayment(@PathVariable(name = "id") Long itemId, HttpServletRequest request, Model model) {
        Member member = authService.getCurrentMember();
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);

        model.addAttribute("orderTotal", item.getPrice());
        model.addAttribute("deliveryCost", Define.DELIVERY); // 임의의 값
        PaymentWrapper pw = new PaymentWrapper();
        pw.getCarts().add(new Cart(null, null, item, 1));
        model.addAttribute("paymentWrapper", pw);
        return "/item/payment";
    }

    @PostMapping("/review")
    public String addReview(@ModelAttribute ReviewDto reviewDto, RedirectAttributes redirectAttributes) {
        String message = "";
        try{
            System.out.println("리뷰 DTO" + reviewDto);
            reviewService.save(reviewDto);
            message = messageSource.getMessage("review.add.success", null, LocaleContextHolder.getLocale());
        }catch(Exception e){
            message = messageSource.getMessage("review.add.fail", null, LocaleContextHolder.getLocale());
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/item/" + reviewDto.getItemId();
    }

}
