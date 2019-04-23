package kr.hs.dgsw.dgswblog.Service;

import kr.hs.dgsw.dgswblog.Domain.Post;
import kr.hs.dgsw.dgswblog.Protocol.AttachmentProtocol;

import java.util.List;

public interface PostService {
    List<Post> listAllContents();
    Post add(Post c);
    Post view(Long id);
    Post update(Post p, Long id);
    Post getByUserId(Long userId);

    boolean delete(Long id);
}
