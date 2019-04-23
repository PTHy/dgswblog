package kr.hs.dgsw.dgswblog.Controller;

import kr.hs.dgsw.dgswblog.Domain.Post;
import kr.hs.dgsw.dgswblog.Protocol.ResponseFormat;
import kr.hs.dgsw.dgswblog.Protocol.ResponseType;
import kr.hs.dgsw.dgswblog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public ResponseFormat listPosts() {
        List<Post> list = this.postService.listAllContents();

        return new ResponseFormat(ResponseType.POST_GET, list);
    }

    @GetMapping("/post/{id}")
    public ResponseFormat view(@PathVariable Long id) {
        Post fp = this.postService.view(id);

        if (fp == null)
            return new ResponseFormat(ResponseType.FAIL, null);
        return new ResponseFormat(ResponseType.POST_VIEW, fp, id);
    }

    @PostMapping("/post")
    public ResponseFormat add(@RequestBody Post p) {
        Post fp = this.postService.add(p);

        if (fp == null)
            return new ResponseFormat(ResponseType.FAIL, null);
        return new ResponseFormat(ResponseType.POST_ADD, fp);
    }

    @PutMapping("/post/{id}")
    public ResponseFormat update(@RequestBody Post p, @PathVariable Long id) {
        Post post = this.postService.update(p, id);
        if (post == null)
            return new ResponseFormat(ResponseType.FAIL, null);
        return new ResponseFormat(ResponseType.POST_UPDATE, post, id);
    }
    @DeleteMapping("/post/{id}")
    public ResponseFormat delete(@PathVariable Long id) {
        boolean chk = this.postService.delete(id);

        if (!chk)
            return new ResponseFormat(ResponseType.FAIL, null);
        return new ResponseFormat(ResponseType.POST_DELETE, null);
    }
}
