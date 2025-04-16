package shop.mtcoding.blog.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    public static class UpdateDTO {
        // title=제목1&content=내용1 -> isPublic은 null이다.
        // title=제목1&content=내용1&isPublic -> isPublic은 ""이다.
        // title=제목1&content=내용1&isPublic=  -> isPublic은 " "이다.
        @NotEmpty(message = "제목을 입력하세요") // null, space" ", 빈것""
        private String title;
        @NotEmpty(message = "내용을 입력하세요")
        private String content;
        private String isPublic;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .isPublic(isPublic == null ? false : true)
                    .user(user) // user객체 필요
                    .build();
        }
    }

    @Data
    public static class SaveDTO {
        @NotEmpty(message = "제목을 입력하세요")
        private String title;
        @NotEmpty(message = "내용을 입력하세요")
        private String content;
        private String isPublic;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .isPublic(isPublic == null ? false : true)
                    .user(user) // user객체 필요
                    .build();
        }
    }
}
