package kr.hs.dgsw.dgswblog.Service;

import kr.hs.dgsw.dgswblog.Domain.Post;
import kr.hs.dgsw.dgswblog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.dgswblog.Repository.PostRepository;
import kr.hs.dgsw.dgswblog.Domain.User;
import kr.hs.dgsw.dgswblog.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl
    implements PostService {

    Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Post> listAllContents() {
        return this.postRepository.findAll();
    }

    @Override
    public Post add(Post p) {
        Optional<User> user = this.userRepository.findById(p.getUserId());
        if (!user.isPresent())
            return null;
        return this.postRepository.save(p);
    }



    @Override
    public Post view(Long id) {
        return this.postRepository.findById(id)
                .map(fp -> {
                    return fp;
                })
                .orElse(null);
    }

    @Override
    public Post update(Post p, Long id) {
        return this.postRepository.findById(id)
                .map(fp -> {
                    if (p.getUserId() != null)
                        fp.setUserId(p.getUserId());
                    if (p.getContent() != null)
                        fp.setContent(p.getContent());
                    return this.postRepository.save(fp);
                })
                .orElse(null);
    }

    @Override
    public Post getByUserId(Long userId) {
        return this.postRepository
                .findTopByUserIdOrderByIdDesc(userId)
                .orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        try {
            return this.postRepository.findById(id)
                    .map(fp -> {
                        this.postRepository.delete(fp);
                        return true;
                    })
                    .orElse(null);
        } catch (Exception e) {
            return false;
        }
    }

//    @Override
//    public AttachmentProtocol getPostImage(Long id) {
//        Optional<Post> found = this.postRepository.findById(id);
//        if(!found.isPresent())
//            return null;
//        return new AttachmentProtocol(found.get().getStoredPath(), found.get().getOriginName());
//    }
}
