package com.example.common.file;

import com.example.book.vo.ImageVO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class FileIO {
//    //로컬 파일의 절대경로
//    @Value("${local.file.dir}")
//    private String filePath;

    //파일 업로드는 서버에 파일저장 + db에 uuid로 파일 이름 경로 저장

    /**
     * 파일 업로드 후 vo를 리턴
     */
    public ImageVO uploadFile(MultipartFile multipartFile, String fileDir) {
        System.out.println("multipartFile = " + multipartFile);
        //업로드할 파일이 비어있으면 return null
        if(multipartFile.isEmpty()){
            return null;
        }

        //사용자가 업로드한 파일 네임
        String originalFileName = multipartFile.getOriginalFilename();
        //서버에 업로드할 파일 네임
        String storedFileName = getStoredFileName(extracteExt(originalFileName));
        //이미지 카테고리 cover=c,book_imgs=i
        String imgCategory = "c";
        //업로드 파일의 확장자
        String ext = extracteExt(originalFileName);
        //업로드 파일의 사이즈
        long size = multipartFile.getSize();

        try {
            //파일 업로드(서버에 저장)
            multipartFile.transferTo(new File(getFullPath(fileDir,storedFileName)));
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패");
        }
        ImageVO imageVO = new ImageVO(originalFileName,storedFileName,imgCategory,ext,size,fileDir);
        System.out.println("imageVO!!!!!!in FileIO = " + imageVO);
        //사용자업로드 파일이름,서버업로드 파일이름 저장한 vo 리턴
        return new ImageVO(originalFileName,storedFileName,imgCategory,ext,size,fileDir);
    }

    /**
     * 파일목록 업로드
     */
    public List<ImageVO> uploadFiles(List<MultipartFile> multipartFiles, String fileDir) {
        //업로드한 파일 목록을 저장할 리스트 생성
        List<ImageVO> uploadFilesList = new ArrayList<>();
        //넘어온 multipartfiles를 순회하며 각각 upload 실행후 list에 저장
        for (MultipartFile multipartFile : multipartFiles) {
            ImageVO bookimgVO = uploadFile(multipartFile, fileDir);
            //이미지 카테고리 cover=c,book_imgs=i
            if(bookimgVO!=null){
                bookimgVO.setImgCategory("i");
            }
            uploadFilesList.add(bookimgVO);
        }
        return uploadFilesList;
    }


    /**
     * 파일의 fullpath 리턴
     */
    private String getFullPath(String fileDir,String fileName){
        return fileDir+fileName;
    }

    /**
     * 서버에 저장할 파일 네임 추출 메서드
     */
    private String getStoredFileName(String ext) {
        //서버에 저장하는 파일네임(uuid이용) <- book db에 저장될값
        //사용자가 업롤드한 파일의 확장자를 추출한뒤 uuid뒤에 붙임
        //uuid 사용
        String uuid= UUID.randomUUID().toString();
        return uuid+"."+ ext;
    }

    /**
     * 확장자 추출 메서드
     */
    private static String extracteExt(String originalFilename) {
        int pos = Objects.requireNonNull(originalFilename).lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
