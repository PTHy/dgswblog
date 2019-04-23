package kr.hs.dgsw.dgswblog.Controller;

import kr.hs.dgsw.dgswblog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.dgswblog.Service.PostService;
import kr.hs.dgsw.dgswblog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile uploadFile) {
        String destFilename = "D:/Dev/Spring/web_326/src/main/java/kr/hs/dgsw/web_326/upload/"
                                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                                + UUID.randomUUID().toString() + "_"
                                + uploadFile.getOriginalFilename();

        // ..../upload/2019/04/02/asdf-asdf-asf-adsf

        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            uploadFile.transferTo(destFile);
            return new AttachmentProtocol(destFilename, uploadFile.getOriginalFilename());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

//    @GetMapping("/attachment/{type}/{id}")
//    public void download(@PathVariable String type, @PathVariable Long id, HttpServletRequest req, HttpServletResponse res)
//    {
//        try {
//            AttachmentProtocol ap = null;
//            String filePath, fileName;
//            if (type.equals("user")) {
//                ap = userService.getProfileImage(id);
//            }
//            else if (type.equals("post")) {
//                ap = postService.getPostImage(id);
//            }
//
//            if (ap == null) {
//                System.out.println("File Not Found");
//                return;
//            }
//
//            filePath = ap.getStoredPath();
//            fileName = ap.getOriginName();
//
//            File file = new File(filePath);
//
//            if (!file.exists()) return;
//
//            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//            if (mimeType == null) mimeType = "application/octet-stream";
//
//            res.setContentType(mimeType);
//            res.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
//            res.setContentLength((int) file.length());
//
//            InputStream is = new BufferedInputStream(new FileInputStream(file));
//            FileCopyUtils.copy(is, res.getOutputStream());
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
}
