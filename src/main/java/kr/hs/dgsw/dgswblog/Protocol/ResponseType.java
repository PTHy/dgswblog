package kr.hs.dgsw.dgswblog.Protocol;

public enum ResponseType {
    FAIL                  (0, "명령을 실행하지 못했습니다"),

    USER_GET            (101, "사용자가 조회되었습니다"),
    USER_VIEW           (102, "[%s]님의 정보가 조회되었습니다"),
    USER_ADD            (103, "사용자를 추가하였습니다"),
    USER_UPDATE         (104, "ID [%d]의 정보를 수정하였습니다"),
    USER_DELETE         (105, "사용자를 삭제하였습니다"),
    USER_LOGIN          (106, "로그인이 되었습니다"),

    POST_GET            (201, "게시글이 조회되었습니다"),
    POST_VIEW          (202, "[%s]번 게시물의 정보가 조회되었습니다"),
    POST_ADD            (203, "게시글이 추가되었습니다"),
    POST_UPDATE         (204, "[%d]번 게시글이 수정되었습니다"),
    POST_DELETE         (205, "[%d]번 게시글이 삭제되었습니다"),

    ATTACHMENT_STORED       (301, "파일이 조회되었습니다"),
    ;

    final private int code;
    final private String desc;
    ResponseType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }
}
