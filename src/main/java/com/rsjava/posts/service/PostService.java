package com.rsjava.posts.service;

import com.rsjava.posts.model.Comment;
import com.rsjava.posts.model.Post;
import com.rsjava.posts.repository.CommentRepository;
import com.rsjava.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private static final int PAGE_SIZE = 10;

    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<Post> getAllPosts(Integer page, Sort.Direction sort) {
        page = getValidatedPageNumber(page);
        sort = getValidatedSortDirection(sort);

        return postRepository.findAllPosts(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, "id"))
        );
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }

    //unikamy problemu N+1, tworzymy tylko dwa zapytania do bazy, jeden o posty, jeden o komentarze
    //potem przypisuję posty do danego komentarza - najbardziej optymalna metoda

    public List<Post> getAllPostsWithComments(Integer page, Sort.Direction sort) {
        page = getValidatedPageNumber(page);
        sort = getValidatedSortDirection(sort);

        List<Post> allPosts = postRepository.findAllPosts(
                PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));

        List<Long> ids = allPosts.stream()
                .map(post -> post.getId())
                .collect(Collectors.toList());

        //SELECT * FROM EMP WHERE MGR IN (7788, 7902, 7566, 7788)
        //ZASTĘPUJE WHERE MGR = 7788 OR MGR = 7566 OR MGR = 7788
        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);

        //do listy komentarzy, do poszczególnego komentarza przypisujemy tylko jego posty
        allPosts.forEach(post -> post.setCommentList(extractComments(comments, post.getId())));
        return allPosts;
    }
    //wyciągamy z listy komentarzy (w której są tylko komentarze z postów z danej strony) komentarze do danego posta

    private List<Comment> extractComments(List<Comment> comments, Long id) {
        return comments.stream()
                .filter(comment -> comment.getPost().getId() == id)
                .collect(Collectors.toList());
    }

    //najpierw sprawdzamy czy page jest równe null lub czy page jest mniejsze od zera
    // jak tak to page ma domyślnie 0;
    private Integer getValidatedPageNumber(Integer page) {
        if (page == null || page < 0) {
            page = 0;
        }
        return page;
    }

    //jeżeli sort direction == null to daje mu defaultowo wartość ASC czyli rosnącą
    private Sort.Direction getValidatedSortDirection(Sort.Direction sortDirection){
        if (sortDirection == null){
            sortDirection = Sort.Direction.ASC;
        }
        return sortDirection;
    }
}
