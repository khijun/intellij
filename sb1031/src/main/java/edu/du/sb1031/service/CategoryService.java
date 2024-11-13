package edu.du.sb1031.service;

import edu.du.sb1031.entity.Category;
import edu.du.sb1031.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(String name, Category parent) {
        Category category = new Category(null, name, parent, null, null);
        return categoryRepository.save(category);
    }

    public void createAll(){
        createElectronicsCategories();
        createMonitorCategories();
        createComputerPartsCategories();
        createKeyboardMouseStorageCategories();
    }

    public List<Category> createComputerPartsCategories() {
        Category computerParts = save("컴퓨터 부품", null);

        Category cpu = save("CPU", computerParts);
        Category motherboard = save("메인보드", computerParts);
        Category memory = save("메모리", computerParts);
        Category ssd = save("SSD", computerParts);
        Category hdd = save("HDD", computerParts);
        Category graphicCard = save("그래픽카드", computerParts);
        Category odd = save("ODD", computerParts);
        Category caseCategory = save("케이스", computerParts);
        Category power = save("파워", computerParts);
        Category cooler = save("쿨러/튜닝용품", computerParts);
        Category soundCard = save("사운드카드", computerParts);
        Category customCooling = save("커스텀수랭", computerParts);

        return categoryRepository.findByParent(computerParts);
    }

    public void createMonitorCategories() {
        Category monitor = save("모니터", null);
        save("일반 모니터", monitor);
        save("고해상도 모니터", monitor);
        save("게이밍 모니터", monitor);
        save("터치/산업용 모니터", monitor);
        save("어댑터/주변기기", monitor);
        save("모니터암/액세서리", monitor);
        save("모니터 보안기/받침대", monitor);
        save("모니터 관련 케이블", monitor);
    }

    public void createElectronicsCategories() {
        Category electronics = save("전자제품", null);
        save("스마트폰", electronics);
        save("노트북", electronics);
        save("카메라 및 캠코더", electronics);
        save("오디오 및 이어폰", electronics);
    }

    public void createKeyboardMouseStorageCategories() {
        // 최상위 카테고리 생성
        Category keyboardMouseStorage = save("키보드/마우스/저장장치", null);

        // 키보드/마우스/저장장치 하위 카테고리 생성
        save("키보드", keyboardMouseStorage);
        save("마우스", keyboardMouseStorage);
        save("데스크탑세트", keyboardMouseStorage);
        save("마우스패드", keyboardMouseStorage);
        save("타블렛/프리젠터", keyboardMouseStorage);
        save("인쇄전용관", keyboardMouseStorage);
        save("외장하드", keyboardMouseStorage);
        save("외장케이스/외장도킹", keyboardMouseStorage);
        save("USB", keyboardMouseStorage);
        save("메모리카드/리더기", keyboardMouseStorage);
        save("NAS/DAS", keyboardMouseStorage);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

}