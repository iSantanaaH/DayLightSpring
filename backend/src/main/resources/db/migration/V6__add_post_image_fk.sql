ALTER TABLE posts
    ADD CONSTRAINT fk_posts_image
        FOREIGN KEY (image_id)
            REFERENCES post_image (id);