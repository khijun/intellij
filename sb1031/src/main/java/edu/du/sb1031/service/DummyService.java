package edu.du.sb1031.service;

import edu.du.sb1031.dto.Define;
import edu.du.sb1031.entity.Category;
import edu.du.sb1031.entity.Member;
import edu.du.sb1031.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyService {
    private final MemberRepository memberRepository;
    private final ItemService itemService;
    private final StockService stockService;

    public void insertMember() {
        Member member1 = Member.builder()
                .name("어드민")
                .username("admin")
                .password("1234")
                .role(Define.ADMIN)
                .gender(Define.MALE)
                .build();
        Member member2 = Member.builder()
                .name("홍길동")
                .username("hong")
                .password("1234")
                .role(Define.USER)
                .gender(Define.MALE)
                .build();

        Member member3 = Member.builder()
                .name("삭제된 유저")
                .username("delete")
                .password("1234")
                .role(Define.DELETE)
                .gender(Define.MALE)
                .build();

        Member member4 = Member.builder()
                .name("동결된 유저")
                .username("freeze")
                .password("1234")
                .role(Define.FREEZE)
                .gender(Define.MALE)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
    }

    public void insertItem(List<Category> categories) {

        itemService.save("AMD Ryzen 7", 800000, 40, "AMD", "amd_ryzen_7.jpg", categories.get(0), "AMD Ryzen 7은 고성능 게임과 콘텐츠 생성에 적합한 프로세서로, 멀티코어 성능이 뛰어나 멀티스레드 작업에 강력한 성능을 발휘합니다.");
        itemService.save("Intel Core i7", 650000, 100, "Intel", "intel_core_i7.jpg", categories.get(0), "인텔의 Core i7은 고급형 프로세서로, 게임과 일상적인 작업에서 우수한 성능을 제공합니다. 뛰어난 처리 속도와 안정성으로 많은 사용자들에게 사랑받고 있습니다.");
        itemService.save("AMD Ryzen 5", 550000, 70, "AMD", "amd_ryzen_5.jpg", categories.get(0), "AMD Ryzen 5는 중급 성능의 프로세서로, 다양한 작업을 원활하게 처리할 수 있으며, 가성비가 뛰어나 많은 소비자들에게 적합한 선택입니다.");
        itemService.save("Intel Core i5", 400000, 200, "Intel", "intel_core_i5.jpg", categories.get(0), "인텔 Core i5는 뛰어난 성능과 가성비를 자랑하는 프로세서로, 일반적인 게임, 웹 서핑, 오피스 작업에 최적화되어 있습니다.");
        itemService.save("Intel Core i9-13900K", 1400000, 30, "Intel", "intel_core_i9_13900k.jpg", categories.get(0), "인텔의 Core i9-13900K는 13세대 프로세서로, 뛰어난 멀티코어 성능과 고속 클럭 속도를 자랑하여 고성능 게임 및 비디오 편집 작업에 최적입니다.");
        itemService.save("AMD Ryzen 9 7950X", 1500000, 25, "AMD", "amd_ryzen_9_7950x.jpg", categories.get(0), "AMD Ryzen 9 7950X는 16코어 32스레드를 지원하는 고급형 프로세서로, 멀티스레드 작업에서 탁월한 성능을 제공합니다.");
        itemService.save("Intel Core i7-12700K", 850000, 50, "Intel", "intel_core_i7_12700k.jpg", categories.get(0), "인텔 Core i7-12700K는 12세대 프로세서로, 뛰어난 게이밍 성능과 멀티태스킹 성능을 자랑하며, 고급 사용자들에게 적합한 선택입니다.");
        itemService.save("AMD Ryzen 7 7700X", 750000, 60, "AMD", "amd_ryzen_7_7700x.jpg", categories.get(0), "AMD Ryzen 7 7700X는 8코어 16스레드를 지원하는 중고급형 프로세서로, 게임과 생산성 작업에 균형 잡힌 성능을 제공합니다.");
        itemService.save("Intel Core i5-12600K", 600000, 120, "Intel", "intel_core_i5_12600k.jpg", categories.get(0), "인텔 Core i5-12600K는 12세대 프로세서로, 가격 대비 뛰어난 성능을 제공하여 예산을 고려한 사용자들에게 매우 인기 있는 선택입니다.");


        // 메인보드 카테고리
        itemService.save("ASUS ROG Strix", 250000, 30, "ASUS", "asus_rog_strix.jpg", categories.get(1), "ASUS의 ROG Strix는 뛰어난 성능과 디자인을 갖춘 고성능 그래픽 카드로, 최신 게임을 원활하게 실행할 수 있는 힘을 제공합니다.");
        itemService.save("MSI MAG", 200000, 50, "MSI", "msi_mag.jpg", categories.get(1), "MSI MAG는 뛰어난 내구성과 효율적인 열 관리로, 고성능 그래픽 작업을 위한 이상적인 선택입니다. 다양한 게임과 멀티미디어 작업에 적합합니다.");
        itemService.save("Gigabyte AORUS", 230000, 60, "Gigabyte", "gigabyte_aorus.jpg", categories.get(1), "Gigabyte AORUS는 고급 게임 성능을 자랑하는 그래픽 카드로, 고해상도 게임을 원활하게 실행할 수 있는 뛰어난 성능을 제공합니다.");
        itemService.save("ASRock Phantom Gaming", 210000, 40, "ASRock", "asrock_phantom.jpg", categories.get(1), "ASRock Phantom Gaming은 게임에서의 성능을 최적화하여 고사양 게임과 멀티태스킹 환경에서도 안정적인 성능을 발휘합니다.");
        itemService.save("Biostar Racing", 180000, 80, "Biostar", "biostar_racing.jpg", categories.get(1), "Biostar Racing은 게임과 그래픽 작업에 적합한 성능을 제공하며, 가격 대비 성능이 뛰어난 제품으로 가성비를 중시하는 사용자들에게 적합합니다.");


        // 메모리 카테고리
        itemService.save("Corsair Vengeance LPX", 100000, 120, "Corsair", "corsair_vengeance_lpx.jpg", categories.get(2), "Corsair Vengeance LPX는 고성능의 DDR4 메모리로, 안정적인 오버클럭 성능과 탁월한 내구성을 제공합니다. 게임 및 멀티태스킹 작업에 최적화되어 있습니다.");
        itemService.save("G.SKILL Ripjaws V", 90000, 150, "G.SKILL", "gskill_ripjaws_v.jpg", categories.get(2), "G.SKILL Ripjaws V는 뛰어난 성능과 안정성을 자랑하는 DDR4 메모리로, 고속 데이터 전송 속도와 효율적인 열 관리를 제공합니다.");
        itemService.save("Kingston HyperX", 85000, 100, "Kingston", "kingston_hyperx.jpg", categories.get(2), "Kingston HyperX는 우수한 성능과 고속 속도를 제공하는 DDR4 메모리로, 게임, 비디오 편집 등 고사양 작업에 최적화되어 있습니다.");
        itemService.save("ADATA XPG", 95000, 110, "ADATA", "adata_xpg.jpg", categories.get(2), "ADATA XPG는 고성능 오버클럭과 빠른 반응 속도를 제공하는 DDR4 메모리로, 안정적이고 효율적인 열 관리 성능을 갖추고 있습니다.");
        itemService.save("Crucial Ballistix", 80000, 200, "Crucial", "crucial_ballistix.jpg", categories.get(2), "Crucial Ballistix는 탁월한 성능과 효율적인 열 관리를 제공하는 DDR4 메모리로, 안정적인 오버클럭 성능과 우수한 내구성을 자랑합니다.");


        // categories.get(3) 카테고리
        itemService.save("Samsung 970 EVO", 150000, 50, "Samsung", "samsung_970_evo.jpg", categories.get(3), "Samsung 970 EVO는 빠른 읽기/쓰기 속도를 제공하는 NVMe SSD로, 게임 로딩, 대용량 파일 처리 등 고속 작업에 최적화되어 있습니다.");
        itemService.save("Western Digital Black SN850", 200000, 40, "WD", "wd_black_sn850.jpg", categories.get(3), "WD Black SN850은 뛰어난 성능과 내구성을 자랑하는 PCIe Gen4 NVMe SSD로, 고속 데이터 전송과 게임 성능에 최적화된 제품입니다.");
        itemService.save("Crucial MX500", 90000, 100, "Crucial", "crucial_mx500.jpg", categories.get(3), "Crucial MX500은 안정적이고 빠른 성능을 제공하는 SATA SSD로, 일상적인 컴퓨터 작업과 게임 로딩에 적합합니다.");
        itemService.save("Kingston A2000", 85000, 120, "Kingston", "kingston_a2000.jpg", categories.get(3), "Kingston A2000은 뛰어난 가격 대비 성능을 제공하는 NVMe SSD로, 빠른 읽기/쓰기 속도와 안정성을 제공합니다.");
        itemService.save("SanDisk Ultra 3D", 95000, 80, "SanDisk", "sandisk_ultra_3d.jpg", categories.get(3), "SanDisk Ultra 3D는 3D NAND 기술을 활용한 SSD로, 고속 데이터 처리와 안정성 높은 성능을 제공하여 일반적인 작업에서 매우 효율적입니다.");


        // categories.get(4) 카테고리
        itemService.save("Seagate Barracuda", 60000, 150, "Seagate", "seagate_barracuda.jpg", categories.get(4), "Seagate Barracuda는 뛰어난 성능과 높은 용량을 제공하는 일반적인 소비자용 하드디스크입니다. 게임, 멀티미디어 저장 및 일반적인 컴퓨터 작업에 적합합니다.");
        itemService.save("Western Digital Blue", 70000, 130, "WD", "wd_blue.jpg", categories.get(4), "WD Blue는 안정적인 성능과 높은 내구성을 자랑하는 하드디스크로, 일상적인 컴퓨터 작업과 데이터 저장에 매우 적합합니다.");
        itemService.save("Toshiba X300", 75000, 120, "Toshiba", "toshiba_x300.jpg", categories.get(4), "Toshiba X300은 고성능과 대용량을 제공하는 하드디스크로, 고속 데이터 전송과 높은 신뢰성을 요구하는 사용자에게 적합합니다.");
        itemService.save("Hitachi Deskstar", 80000, 100, "Hitachi", "hitachi_deskstar.jpg", categories.get(4), "Hitachi Deskstar는 높은 안정성과 성능을 자랑하는 하드디스크로, 대용량 파일 저장과 고속 데이터 접근에 최적화되어 있습니다.");
        itemService.save("Seagate IronWolf", 95000, 90, "Seagate", "seagate_ironwolf.jpg", categories.get(4), "Seagate IronWolf는 NAS용으로 최적화된 하드디스크로, 높은 내구성과 안정성을 제공하여 24시간 작동이 요구되는 환경에 적합합니다.");


        // 그래픽카드 카테고리
        itemService.save("NVIDIA GeForce RTX 4090", 2500000, 30, "NVIDIA", "rtx_4090.jpg", categories.get(5), "NVIDIA GeForce RTX 4090은 최첨단 그래픽카드로, 4K 게이밍 및 고해상도 작업에 최적화되어 있으며, 뛰어난 성능과 AI 기반의 DLSS 기술을 지원합니다.");
        itemService.save("AMD Radeon RX 7900 XT", 2200000, 40, "AMD", "rx_7900_xt.jpg", categories.get(5), "AMD Radeon RX 7900 XT는 강력한 성능을 제공하는 고급형 GPU로, 고해상도 게임 및 VR 환경에 탁월한 성능을 발휘합니다.");
        itemService.save("NVIDIA GeForce RTX 3080", 1600000, 60, "NVIDIA", "rtx_3080.jpg", categories.get(5), "NVIDIA GeForce RTX 3080은 고성능 게임과 3D 렌더링에 적합한 그래픽카드로, 4K 해상도에서 우수한 성능을 제공합니다.");
        itemService.save("MSI GeForce RTX 3070", 1400000, 100, "MSI", "msi_rtx_3070.jpg", categories.get(5), "MSI GeForce RTX 3070은 뛰어난 성능과 가격 대비 우수한 가치를 제공하는 그래픽카드로, 1440p 게임 및 고속 작업에 적합합니다.");
        itemService.save("Gigabyte Radeon RX 6800", 1800000, 50, "Gigabyte", "gigabyte_rx_6800.jpg", categories.get(5), "Gigabyte Radeon RX 6800은 뛰어난 1440p 성능과 높은 VRAM 용량을 제공하는 그래픽카드로, 고속 게임 및 멀티태스킹에 적합합니다.");


        // categories.get(6) 카테고리
        itemService.save("LG Electronics Super Multi", 30000, 150, "LG", "lg_odd.jpg", categories.get(6), "LG Super Multi는 다양한 디스크 포맷을 지원하는 다기능 광학 디스크 드라이브로, 일반적인 CD, DVD 및 Blu-ray의 쓰기 및 읽기가 가능합니다.");
        itemService.save("ASUS Blu-ray Writer", 80000, 80, "ASUS", "asus_blu_ray.jpg", categories.get(6), "ASUS Blu-ray Writer는 Blu-ray, DVD 및 CD의 읽기 및 쓰기를 지원하며, 고화질 영화를 저장하고 감상하기에 적합한 제품입니다.");
        itemService.save("Samsung DVD Writer", 25000, 200, "Samsung", "samsung_dvd_writer.jpg", categories.get(6), "Samsung DVD Writer는 안정적인 성능과 가격 대비 우수한 가치를 제공하며, CD 및 DVD 디스크의 읽기 및 쓰기를 지원합니다.");
        itemService.save("Pioneer Blu-ray Burner", 95000, 60, "Pioneer", "pioneer_blu_ray.jpg", categories.get(6), "Pioneer Blu-ray Burner는 고속 Blu-ray 쓰기와 읽기를 지원하는 드라이브로, 대용량 데이터를 저장하고 고화질 영화를 기록할 수 있습니다.");
        itemService.save("Lite-On Internal DVD Writer", 20000, 180, "Lite-On", "liteon_dvd_writer.jpg", categories.get(6), "Lite-On Internal DVD Writer는 경제적인 가격에 안정적인 성능을 제공하는 DVD 드라이브로, CD 및 DVD 디스크의 쓰기 및 읽기 기능을 갖추고 있습니다.");


        // 케이스 카테고리
        itemService.save("NZXT H510", 95000, 50, "NZXT", "nzxt_h510.jpg", categories.get(7), "NZXT H510은 깔끔하고 모던한 디자인의 미들 타워 케이스로, 뛰어난 공기 흐름과 관리 용이성을 제공하며, 강화 유리 측면 패널로 내부 부품을 강조합니다.");
        itemService.save("Corsair iCUE 4000X", 120000, 40, "Corsair", "corsair_4000x.jpg", categories.get(7), "Corsair iCUE 4000X는 뛰어난 공기 흐름과 RGB 조명 옵션을 제공하는 ATX 미들 타워 케이스로, PC 빌드를 더욱 돋보이게 만듭니다.");
        itemService.save("Cooler Master MasterBox", 70000, 70, "Cooler Master", "cooler_master_box.jpg", categories.get(7), "Cooler Master MasterBox는 실용적이고 경제적인 미들 타워 케이스로, 다양한 시스템 빌드를 지원하며, 뛰어난 통풍 성능과 다양한 설치 옵션을 제공합니다.");
        itemService.save("Fractal Design Meshify C", 100000, 60, "Fractal Design", "meshify_c.jpg", categories.get(7), "Fractal Design Meshify C는 뛰어난 공기 흐름을 위한 메쉬 전면 패널을 특징으로 하는 미들 타워 케이스로, 고급스러운 디자인과 뛰어난 확장성을 제공합니다.");
        itemService.save("Thermaltake Core V21", 80000, 90, "Thermaltake", "thermaltake_core_v21.jpg", categories.get(7), "Thermaltake Core V21은 독특한 큐브 형태의 미니 ITX 케이스로, 높은 유연성 및 확장성을 제공하며, 컴팩트한 크기에서 강력한 성능을 구현할 수 있습니다.");


        // 파워 카테고리
        itemService.save("Corsair RM850x", 150000, 50, "Corsair", "corsair_rm850x.jpg", categories.get(8), "Corsair RM850x는 80 Plus Gold 인증을 받은 고효율 파워서플라이로, 뛰어난 안정성과 뛰어난 냉각 성능을 제공합니다.");
        itemService.save("Seasonic Focus GX-750", 130000, 70, "Seasonic", "seasonic_focus.jpg", categories.get(8), "Seasonic Focus GX-750은 고품질의 컴포넌트와 80 Plus Gold 인증을 갖춘 750W 파워서플라이로, 안정적이고 효율적인 전력 공급을 보장합니다.");
        itemService.save("EVGA SuperNOVA 650 G5", 110000, 80, "EVGA", "evga_supernova.jpg", categories.get(8), "EVGA SuperNOVA 650 G5는 80 Plus Gold 인증과 함께 탁월한 안정성 및 효율성을 자랑하는 650W 파워서플라이입니다.");
        itemService.save("Cooler Master MWE Gold", 90000, 100, "Cooler Master", "coolermaster_mwe.jpg", categories.get(8), "Cooler Master MWE Gold는 80 Plus Gold 인증을 제공하는 파워서플라이로, 고성능 시스템에 안정적인 전력 공급을 합니다.");
        itemService.save("MSI MPG A750GF", 120000, 60, "MSI", "msi_mpg.jpg", categories.get(8), "MSI MPG A750GF는 750W 용량과 80 Plus Gold 인증을 갖춘 파워서플라이로, 고급스러운 디자인과 효율적인 전력 관리를 제공합니다.");


        // 쿨러 카테고리
        itemService.save("Noctua NH-D15", 150000, 30, "Noctua", "noctua_nhd15.jpg", categories.get(9), "Noctua NH-D15는 뛰어난 성능과 조용한 작동을 자랑하는 공랭 CPU 쿨러로, 고성능 시스템에서 최고의 냉각 효과를 제공합니다.");
        itemService.save("Cooler Master Hyper 212", 35000, 100, "Cooler Master", "coolermaster_hyper212.jpg", categories.get(9), "Cooler Master Hyper 212는 뛰어난 가성비와 효율적인 냉각 성능을 제공하는 인기 있는 공랭 쿨러입니다.");
        itemService.save("Corsair iCUE H115i", 160000, 40, "Corsair", "corsair_icue_h115i.jpg", categories.get(9), "Corsair iCUE H115i는 280mm 크기의 수냉 쿨러로, 고성능 냉각 성능과 RGB 조명 기능을 제공합니다.");
        itemService.save("NZXT Kraken X73", 200000, 50, "NZXT", "nzxt_kraken_x73.jpg", categories.get(9), "NZXT Kraken X73은 360mm 수냉 쿨러로, 효율적인 냉각과 세련된 디자인을 자랑합니다.");
        itemService.save("Thermaltake Floe Riing", 190000, 60, "Thermaltake", "thermaltake_floe.jpg", categories.get(9), "Thermaltake Floe Riing은 수냉 쿨러로, 뛰어난 냉각 성능과 RGB 조명이 특징입니다.");


        // 사운드카드 카테고리
        itemService.save("Creative Sound Blaster Z", 120000, 60, "Creative", "creative_soundblaster_z.jpg", categories.get(10), "Creative Sound Blaster Z는 고급 오디오 성능을 제공하는 PCIe 사운드 카드로, 게임과 영화, 음악에 최적화된 음향을 제공합니다.");
        itemService.save("ASUS Xonar AE", 90000, 80, "ASUS", "asus_xonar_ae.jpg", categories.get(10), "ASUS Xonar AE는 뛰어난 음질과 다양한 오디오 포맷을 지원하는 PCIe 사운드 카드로, 고음질의 게임과 음악 경험을 제공합니다.");
        itemService.save("EVGA Nu Audio", 200000, 40, "EVGA", "evga_nu_audio.jpg", categories.get(10), "EVGA Nu Audio는 32비트 고해상도 오디오 지원과 뛰어난 음질을 제공하는 외장 오디오 인터페이스입니다.");
        itemService.save("Focusrite Scarlett 2i2", 160000, 50, "Focusrite", "focusrite_scarlett.jpg", categories.get(10), "Focusrite Scarlett 2i2는 2개의 입력과 2개의 출력을 지원하는 USB 오디오 인터페이스로, 프로페셔널한 오디오 녹음과 편집이 가능합니다.");
        itemService.save("Behringer UMC22", 70000, 100, "Behringer", "behringer_umc22.jpg", categories.get(10), "Behringer UMC22는 경제적인 가격에 2개의 입력과 2개의 출력을 제공하는 USB 오디오 인터페이스로, 녹음 및 스트리밍에 적합한 제품입니다.");

    }

    public void insertStock(){
        stockService.stockIn(1L, 10, 1L);
        stockService.stockIn(2L, 12, 1L);
        stockService.stockIn(3L, 14, 1L);
        stockService.stockIn(4L, 16, 1L);
    }

}
