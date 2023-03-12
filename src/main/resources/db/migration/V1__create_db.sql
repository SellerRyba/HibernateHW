CREATE TABLE Client (
  id SERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 200)
);

CREATE TABLE Planet (
  id VARCHAR(50) NOT NULL,
  name VARCHAR(500) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT chk_id_format CHECK (id ~ '^[A-Z0-9]+$')
);


CREATE TABLE Ticket (
  id SERIAL PRIMARY KEY,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  client_id INT NOT NULL,
  from_planet_id VARCHAR(50) NOT NULL,
  to_planet_id VARCHAR(50) NOT NULL,
  FOREIGN KEY (client_id) REFERENCES Client(id),
  FOREIGN KEY (from_planet_id) REFERENCES Planet(id),
  FOREIGN KEY (to_planet_id) REFERENCES Planet(id)
);
