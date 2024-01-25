package com.example.rest.photo;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    /**
     * 이미지 개별 조회
     */
    @Transactional(readOnly = true)
    public PhotoDto findByFileId(Long id){

        Photo entity = photoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 파일이 존재하지 않습니다."));

        PhotoDto photoDto = PhotoDto.builder()
                .origFileName(entity.getOrigFileName())
                .filePath(entity.getFilePath())
                .fileSize(entity.getFileSize())
                .build();

        return photoDto;
    }

    /**
     * 이미지 전체 조회
     */
    @Transactional(readOnly = true)
    public List<PhotoResponseDto> findAllByBoard(Long boardId){

        List<Photo> photoList = photoRepository.findAllByArticleId(boardId);

        return photoList.stream()
                .map(PhotoResponseDto::new)
                .collect(Collectors.toList());
    }

}
