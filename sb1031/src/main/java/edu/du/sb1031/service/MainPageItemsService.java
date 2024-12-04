package edu.du.sb1031.service;

import edu.du.sb1031.dto.MainPageData;
import edu.du.sb1031.dto.MainPageItemsDto;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainPageItemsService {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public List<MainPageItemsDto> getMainPageData(){
        List<MainPageData> data = categoryRepository.findForMainPage();// 아이템, 아이템 판매수
        data.removeIf(d-> d.getCategoryId()==null||d.getItem()==null); // 카테고리가 없거나 아이템이 없으면 데이터 자체를 삭제
        Map<Long, List<MainPageData>> dataMap = data.stream().collect(Collectors.groupingBy(MainPageData::getCategoryId)); // 카테고리별로 묶어서 Map으로 만듬
        List<MainPageItemsDto> mainPageItemsDtos = new ArrayList<>();
        for(Long id : dataMap.keySet()){
            List<MainPageData> mainPageDataList = dataMap.get(id);
            mainPageDataList.sort(Comparator.comparing(MainPageData::getSellCount)); // 판매수별로 재정렬, null == 0
            mainPageDataList = mainPageDataList.subList(0, Math.min(mainPageDataList.size(), 7)); // 리스트를 최대 7개 또는 자신의 최대 크기까지 자름.
            List<Item> items = new ArrayList<>();
            for(MainPageData mainPageData : mainPageDataList){
                items.add(mainPageData.getItem()); // 아이템을 새로운 리스트에 담음
            }
            MainPageItemsDto itemsDto = new MainPageItemsDto(); 
            itemsDto.setBannerItem(items.get(0)); // 아이템리스트에서 제일 판매수높은 아이템(인덱스 0)을 배너 아이템에 등록
            itemsDto.setItems(items.subList(1, items.size())); // 나머지 아이템 리스트에서 첫번째 인덱스를 자름
            mainPageItemsDtos.add(itemsDto);
            itemsDto.setCategoryId(id);
        }
        return mainPageItemsDtos;
    }
}
