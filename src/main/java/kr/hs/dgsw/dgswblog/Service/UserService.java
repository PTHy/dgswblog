package kr.hs.dgsw.dgswblog.Service;

import kr.hs.dgsw.dgswblog.Domain.User;
import kr.hs.dgsw.dgswblog.Protocol.AttachmentProtocol;

import java.util.List;

public interface UserService {
    User add(User u);
    User view(Long id);
    User update(User u, Long id);
    boolean delete(Long id);
    User login(User u);

    List<User> list();
    AttachmentProtocol getProfileImage(Long id);
}
