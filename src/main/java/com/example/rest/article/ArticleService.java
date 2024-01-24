package com.example.rest.article;

import com.example.rest.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    @Value("${custom.genFile.dirPath}")
    private String fileDirPath;

    public RsData<Article> create(String title, String subject, int price, String area, MultipartFile postImage) {
        String postImageRelPath = "postImage/" + UUID.randomUUID().toString() + ".jpg";
        File postImageFile = new File(fileDirPath + "/" + postImageRelPath);

        try {
            postImage.transferTo(postImageFile);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }

        Article a = new Article();

        a.setContent(title);
        a.setSubject(subject);
        a.setPrice(price);
        a.setArea(area);
        a.setPostImage(String.valueOf(postImage));
        a.setCreateDate(LocalDateTime.now());

        this.articleRepository.save(a);

        return RsData.of("S-2", "게시물이 생성되었습니다.", a);
    }

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public Article getArticle(Long id) {
        Optional<Article> optionalArticle = this.articleRepository.findById(id);

        if (optionalArticle.isEmpty()) {
            throw  new RuntimeException("없음");
        }

        return optionalArticle.get();
    }


    public RsData<Article> modify(Article article, String subject, String content, int price, String area) {
        article.setSubject(subject);
        article.setContent(content);
        article.setPrice(price);
        article.setArea(area);

        articleRepository.save(article);

        return RsData.of("S-3", "게시물이 수정되었습니다.", article);
    }

    public RsData<Article> delete(Article article) {
        this.articleRepository.delete(article);
        return RsData.of("S-3", "게시물이 삭제되었습니다.", article);

    }
}
