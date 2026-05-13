CREATE TABLE post_image
(
    id         BIGSERIAL PRIMARY KEY,
    path       TEXT      NOT NULL,
    size       TEXT      NOT NULL,
    width      TEXT      NOT NULL,
    height     TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL
)