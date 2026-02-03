CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(120) UNIQUE NOT NULL,
    status VARCHAR(30) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

insert into users (full_name, email,status,created_at ) values('MARCO SAAVEDRA CASTRO', 'MSAAVEDRA.4595@GMAIL.COM','ACTIVE', now());