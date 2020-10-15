CREATE TABLE IF NOT EXISTS services (
    id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    url VARCHAR(250) NOT NULL,
    latest_status INT,
    CONSTRAINT service_pk PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS history (
    id INT AUTO_INCREMENT,
    service_id INT,
    status INT NOT NULL,
    response_time INT,
    log_date TIMESTAMP,
    CONSTRAINT history_pk PRIMARY KEY(id),
    FOREIGN KEY(service_id) REFERENCES services(id)
);

INSERT INTO services (name, url, latest_status) VALUES
    ('vix.digital', 'https://vix.digital/', 404);
INSERT INTO services (name, url, latest_status) VALUES
    ('Google', 'https://google.com/', 404);
INSERT INTO services (name, url, latest_status) VALUES
    ('Dummy', 'http://does_not_exist', 200);