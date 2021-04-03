CREATE TABLE IF NOT EXISTS smses (
    id BIGSERIAL PRIMARY KEY,
    phone TEXT NOT NULL,
    sms_sending_time TIMESTAMP NOT NULL DEFAULT current_timestamp,
    message TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS  tags(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS sms_tag(
    id BIGSERIAL PRIMARY KEY,
    sms_id BIGINT REFERENCES smses (id),
    tag_id BIGINT REFERENCES tags (id)
);