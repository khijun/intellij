package edu.du.sb1031.service;

import edu.du.sb1031.entity.Item;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.entity.StockIn;
import edu.du.sb1031.exception.ItemNotFoundException;
import edu.du.sb1031.exception.MemberNotFoundException;
import edu.du.sb1031.repository.ItemRepository;
import edu.du.sb1031.repository.MemberRepository;
import edu.du.sb1031.repository.StockInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockInRepository stockInRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public void stockIn(Long itemId, int quantity, Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        Item item = itemRepository.findById(itemId).orElseThrow(ItemNotFoundException::new);
        StockIn stockIn = StockIn.builder()
                .item(item)
                .quantity(quantity)
                .member(member)
                .build();
        stockInRepository.save(stockIn);

        item.setStock(item.getStock()+quantity);
        itemRepository.save(item);
    }
}
