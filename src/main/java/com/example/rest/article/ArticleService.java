package com.example.rest.article;

import com.example.rest.RsData;
import com.example.rest.photo.FileHandler;
import com.example.rest.photo.Photo;
import com.example.rest.photo.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final PhotoRepository photoRepository;
    private final FileHandler fileHandler;

    @Transactional
    public RsData<ArticleCreateDto> create(String title, String subject, int price, String area, String category, List<MultipartFile> postImage) throws Exception {
        Article a = new Article();
        a.setContent(title);
        a.setSubject(subject);
        a.setPrice(price);
        a.setArea(area);
        a.setCategory(category);
        a.setCreateDate(LocalDateTime.now());

        List<Photo> photoList = fileHandler.parseFileInfo(postImage);

        if (!photoList.isEmpty()) {
            for (Photo photo : photoList) {
                a.addPhoto(photoRepository.save(photo));
            }
        }
        this.articleRepository.save(a).getId();

        // ArticleDto로 변환
        ArticleCreateDto articleDto = new ArticleCreateDto();
        articleDto.setSubject(a.getSubject());
        articleDto.setContent(a.getContent());
        articleDto.setPrice(a.getPrice());
        articleDto.setArea(a.getArea());
        articleDto.setCategory(a.getCategory());

        return RsData.of("S-2", "게시물이 생성되었습니다.", articleDto);
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
