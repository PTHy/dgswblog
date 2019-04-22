package kr.hs.dgsw.dgswblog.Service;

import kr.hs.dgsw.dgswblog.Domain.User;
import kr.hs.dgsw.dgswblog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.dgswblog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public AttachmentProtocol getProfileImage(Long id) {
        Optional<User> found = this.userRepository.findById(id);
        if (!found.isPresent())
            return null;
        return new AttachmentProtocol(found.get().getStoredPath(), found.get().getOriginName());
    }

    @Override
    public User add(User u) {
        Optional<User> user = this.userRepository.findByAccount(u.getAccount());
        if(user.isPresent())
            return null;
        u.setPassword(u.getPassword());
        return this.userRepository.save(u);
    }



    @Override
    public User view(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        if (!user.isPresent())
            return null;

        return user.get();
    }

    @Override
    public User update(User u, Long id) {
        return this.userRepository.findById(id)
                .map(fu -> {
                    if(u.getUsername() != null)
                        fu.setUsername(u.getUsername());
                    if(u.getEmail() != null)
                        fu.setEmail(u.getEmail());
                    if(u.getPassword() != null) {
                        fu.setPassword(u.getPassword());
                    }
                    if(u.getStoredPath() != null)
                        fu.setStoredPath(u.getStoredPath());
                    if(u.getOriginName() != null)
                        fu.setOriginName(u.getOriginName());
                    return this.userRepository.save(fu);
                })
                .orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        try {
            return this.userRepository.findById(id)
                    .map(fu -> {
                        this.userRepository.delete(fu);
                        System.out.println(fu);
                        return true;
                    })
                    .orElse(false);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public User login(User u) {
        u.setPassword(u.getPassword());
        return this.userRepository.findByAccountAndPassword(u.getAccount(), u.getPassword())
                .map(fu -> {
                    return fu;
                })
                .orElse(null);
    }

    @Override
    public List<User> list() {
        try {
            return this.userRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }
}
