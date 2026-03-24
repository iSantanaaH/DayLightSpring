CREATE TABLE refresh_token
(
    id         BIGSERIAL PRIMARY KEY,
    token      TEXT      NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    revoked    BOOLEAN   NOT NULL,
    user_id    BIGINT    NOT NULL,
    created_at TIMESTAMP NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id)
)