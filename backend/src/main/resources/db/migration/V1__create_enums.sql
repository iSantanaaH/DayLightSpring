CREATE TYPE gender_type AS ENUM (
    'MALE',
    'FEMALE',
    'NON_BINARY',
    'OTHER'
);

CREATE TYPE user_role AS ENUM (
    'USER',
    'ADMIN'
);