package edu.du.sb1031.service;

import edu.du.sb1031.dto.Define;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class ImageService {
    public boolean saveImage(String savePath, MultipartFile file, String filename){
        try {
            File path = new File(savePath + "/");
            if (!path.exists()) {
                System.out.println("디렉토리가 존재하지 않음. 생성 시도: " + savePath);
                path.mkdirs(); // 디렉토리 생성
            } else {
                System.out.println("디렉토리가 이미 존재함: " + savePath);
            }
            path = new File(createSaveFilePath(file, filename, savePath));
            file.transferTo(path);
            System.out.println("itemController.addItem() 성공?");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("itemController.addItem() 실패");
            return false;
        }
    }
    public String getContentType(MultipartFile file) {
        String contentType = file.getContentType();
        switch (Objects.requireNonNull(contentType)) { // 널 참조 회피
            case "image/jpeg":
                contentType = "jpeg";
                break;
            case "image/png":
                contentType = "png";
                break;
            case "image/gif":
                contentType = "gif";
                break;
            case "image/jpg":
                contentType = "jpg";
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 파일형식: " + contentType);
        }
        return contentType;
    }

    public String createSaveFilePath(MultipartFile file, String filename, String savePath) {
        String contentType = getContentType(file);
        String result = savePath + "/" + filename + "." + contentType;
        System.out.println(result);
        return result;
    }
}
