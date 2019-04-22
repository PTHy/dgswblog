package kr.hs.dgsw.dgswblog.Controller;

import kr.hs.dgsw.dgswblog.Domain.Post;
import kr.hs.dgsw.dgswblog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public List<Post> listPosts() {
        return this.postService.listAllContents();
    }

    @GetMapping("/post/{id}")
    public Post view(@PathVariable Long id) {
        return this.postService.view(id);
    }

    @PostMapping("/post")
    public Post add(@RequestBody Post p) {
        return this.postService.add(p);
    }

    @PutMapping("/post/{id}")
    public Post update(@RequestBody Post p, @PathVariable Long id) {
        return this.postService.update(p, id);
    }

    @DeleteMapping("/post/{id}")
    public boolean delete(@PathVariable Long id) {
        return this.postService.delete(id);
    }
}
