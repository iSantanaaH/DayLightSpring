CREATE TABLE posts
(
    id             BIGSERIAL PRIMARY KEY,
    title          VARCHAR(150) NOT NULL UNIQUE,
    content        TEXT         NOT NULL,
    image_id       BIGINT       NOT NULL,
    user_id        BIGINT       NOT NULL,
    created_at     TIMESTAMP    NOT NULL,
    updated_at     TIMESTAMP    NOT NULL,
    status         VARCHAR(30)  NOT NULL,
    likes_count    INTEGER      NOT NULL DEFAULT 0,
    comments_count INTEGER      NOT NULL DEFAULT 0,

    FOREIGN KEY (user_id) REFERENCES users (id)
)