package kr.hs.dgsw.dgswblog.Protocol;

import lombok.Data;

@Data
public class AttachmentProtocol {
    private String storedPath;
    private String originName;

    public AttachmentProtocol(String storedPath, String originName) {
        this.storedPath = storedPath;
        this.originName = originName;
    }
}
