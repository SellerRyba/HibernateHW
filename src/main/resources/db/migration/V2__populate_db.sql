INSERT INTO Client (name) VALUES
('Caitlin'),
('Michael'),
('Alexander'),
('Sofia'),
('Jane'),
('Josh'),
('Ilya'),
('Eugene'),
('Alice'),
('Tom');
INSERT INTO Planet (id, name) VALUES
('MARS', 'MARS'),
('VEN', 'VENUS'),
('JUP', 'JUPITER'),
('SAT', 'SATURN'),
('NEP', 'NEPTUN');
INSERT INTO Ticket (client_id, from_planet_id, to_planet_id) VALUES
(1, 'MARS', 'JUP'),
(2, 'NEP', 'SAT'),
(3, 'VEN', 'MARS'),
(4, 'JUP', 'NEP'),
(5, 'SAT', 'MARS'),
(6, 'MARS', 'VEN'),
(7, 'JUP', 'SAT'),
(8, 'NEP', 'MARS'),
(9, 'VEN', 'JUP'),
(10, 'SAT', 'NEP');

