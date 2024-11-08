package edu.du.sb1031.service;


import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Item;
import edu.du.sb1031.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    public void save(String name, int price, int stock, String company, String imageName, Category category) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setStock(stock);
        item.setCompany(company);
        item.setImageName(imageName);
        item.setCategory(category);
        itemRepository.save(item);
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public void setQuantity(Item item, Integer quantity) {

    }

    public List<Item> findAll() {return itemRepository.findAll();}

    public List<Item> findByNameLike(String name) {
        return itemRepository.findByNameLike(name);
    }

    public void createComputerPartsItems(List<Category> categories){
        save("Intel Core i9", 1200000, 50, "Intel", "intel_core_i9.jpg", categories.get(0));
        save("AMD Ryzen 7", 800000, 40, "AMD", "amd_ryzen_7.jpg", categories.get(0));
        save("Intel Core i7", 650000, 100, "Intel", "intel_core_i7.jpg", categories.get(0));
        save("AMD Ryzen 5", 550000, 70, "AMD", "amd_ryzen_5.jpg", categories.get(0));
        save("Intel Core i5", 400000, 200, "Intel", "intel_core_i5.jpg", categories.get(0));

        // 메인보드 카테고리
        save("ASUS ROG Strix", 250000, 30, "ASUS", "asus_rog_strix.jpg", categories.get(1));
        save("MSI MAG", 200000, 50, "MSI", "msi_mag.jpg", categories.get(1));
        save("Gigabyte AORUS", 230000, 60, "Gigabyte", "gigabyte_aorus.jpg", categories.get(1));
        save("ASRock Phantom Gaming", 210000, 40, "ASRock", "asrock_phantom.jpg", categories.get(1));
        save("Biostar Racing", 180000, 80, "Biostar", "biostar_racing.jpg", categories.get(1));

        // 메모리 카테고리
        save("Corsair Vengeance LPX", 100000, 120, "Corsair", "corsair_vengeance_lpx.jpg", categories.get(2));
        save("G.SKILL Ripjaws V", 90000, 150, "G.SKILL", "gskill_ripjaws_v.jpg", categories.get(2));
        save("Kingston HyperX", 85000, 100, "Kingston", "kingston_hyperx.jpg", categories.get(2));
        save("ADATA XPG", 95000, 110, "ADATA", "adata_xpg.jpg", categories.get(2));
        save("Crucial Ballistix", 80000, 200, "Crucial", "crucial_ballistix.jpg", categories.get(2));

        // categories.get(3) 카테고리
        save("Samsung 970 EVO", 150000, 50, "Samsung", "samsung_970_evo.jpg", categories.get(3));
        save("Western Digital Black SN850", 200000, 40, "WD", "wd_black_sn850.jpg", categories.get(3));
        save("Crucial MX500", 90000, 100, "Crucial", "crucial_mx500.jpg", categories.get(3));
        save("Kingston A2000", 85000, 120, "Kingston", "kingston_a2000.jpg", categories.get(3));
        save("SanDisk Ultra 3D", 95000, 80, "SanDisk", "sandisk_ultra_3d.jpg", categories.get(3));

        // categories.get(4) 카테고리
        save("Seagate Barracuda", 60000, 150, "Seagate", "seagate_barracuda.jpg", categories.get(4));
        save("Western Digital Blue", 70000, 130, "WD", "wd_blue.jpg", categories.get(4));
        save("Toshiba X300", 75000, 120, "Toshiba", "toshiba_x300.jpg", categories.get(4));
        save("Hitachi Deskstar", 80000, 100, "Hitachi", "hitachi_deskstar.jpg", categories.get(4));
        save("Seagate IronWolf", 95000, 90, "Seagate", "seagate_ironwolf.jpg", categories.get(4));

        // 그래픽카드 카테고리
        save("NVIDIA GeForce RTX 4090", 2500000, 30, "NVIDIA", "rtx_4090.jpg", categories.get(5));
        save("AMD Radeon RX 7900 XT", 2200000, 40, "AMD", "rx_7900_xt.jpg", categories.get(5));
        save("NVIDIA GeForce RTX 3080", 1600000, 60, "NVIDIA", "rtx_3080.jpg", categories.get(5));
        save("MSI GeForce RTX 3070", 1400000, 100, "MSI", "msi_rtx_3070.jpg", categories.get(5));
        save("Gigabyte Radeon RX 6800", 1800000, 50, "Gigabyte", "gigabyte_rx_6800.jpg", categories.get(5));

        // categories.get(6) 카테고리
        save("LG Electronics Super Multi", 30000, 150, "LG", "lg_odd.jpg", categories.get(6));
        save("ASUS Blu-ray Writer", 80000, 80, "ASUS", "asus_blu_ray.jpg", categories.get(6));
        save("Samsung DVD Writer", 25000, 200, "Samsung", "samsung_dvd_writer.jpg", categories.get(6));
        save("Pioneer Blu-ray Burner", 95000, 60, "Pioneer", "pioneer_blu_ray.jpg", categories.get(6));
        save("Lite-On Internal DVD Writer", 20000, 180, "Lite-On", "liteon_dvd_writer.jpg", categories.get(6));

        // 케이스 카테고리
        save("NZXT H510", 95000, 50, "NZXT", "nzxt_h510.jpg", categories.get(7));
        save("Corsair iCUE 4000X", 120000, 40, "Corsair", "corsair_4000x.jpg", categories.get(7));
        save("Cooler Master MasterBox", 70000, 70, "Cooler Master", "cooler_master_box.jpg", categories.get(7));
        save("Fractal Design Meshify C", 100000, 60, "Fractal Design", "meshify_c.jpg", categories.get(7));
        save("Thermaltake Core V21", 80000, 90, "Thermaltake", "thermaltake_core_v21.jpg", categories.get(7));

        // 파워 카테고리
        save("Corsair RM850x", 150000, 50, "Corsair", "corsair_rm850x.jpg", categories.get(8));
        save("Seasonic Focus GX-750", 130000, 70, "Seasonic", "seasonic_focus.jpg", categories.get(8));
        save("EVGA SuperNOVA 650 G5", 110000, 80, "EVGA", "evga_supernova.jpg", categories.get(8));
        save("Cooler Master MWE Gold", 90000, 100, "Cooler Master", "coolermaster_mwe.jpg", categories.get(8));
        save("MSI MPG A750GF", 120000, 60, "MSI", "msi_mpg.jpg", categories.get(8));

        // 쿨러 카테고리
        save("Noctua NH-D15", 150000, 30, "Noctua", "noctua_nhd15.jpg", categories.get(9));
        save("Cooler Master Hyper 212", 35000, 100, "Cooler Master", "coolermaster_hyper212.jpg", categories.get(9));
        save("Corsair iCUE H115i", 160000, 40, "Corsair", "corsair_icue_h115i.jpg", categories.get(9));
        save("NZXT Kraken X73", 200000, 50, "NZXT", "nzxt_kraken_x73.jpg", categories.get(9));
        save("Thermaltake Floe Riing", 190000, 60, "Thermaltake", "thermaltake_floe.jpg", categories.get(9));

        // 사운드카드 카테고리
        save("Creative Sound Blaster Z", 120000, 60, "Creative", "creative_soundblaster_z.jpg", categories.get(10));
        save("ASUS Xonar AE", 90000, 80, "ASUS", "asus_xonar_ae.jpg", categories.get(10));
        save("EVGA Nu Audio", 200000, 40, "EVGA", "evga_nu_audio.jpg", categories.get(10));
        save("Focusrite Scarlett 2i2", 160000, 50, "Focusrite", "focusrite_scarlett.jpg", categories.get(10));
        save("Behringer UMC22", 70000, 100, "Behringer", "behringer_umc22.jpg", categories.get(10));
    }
}
