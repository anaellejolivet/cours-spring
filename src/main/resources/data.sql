-- Initialisation des tables
INSERT INTO Country(id, code, name) VALUES
    (1, 'FR', 'France'), -- Les clés sont auto-générées
    (2, 'UK', 'United Kingdom'),
    (3, 'USA', 'United States of America');

INSERT INTO CITY(name, population, country_id) VALUES
    ('Paris', 12, 1),
    ('London', 18, 2),
    ('New York', 27, 3);

/* ou -- Initialisation des tables
INSERT INTO Country(code, name) VALUES
    ('FR', 'France'), -- Les clés sont auto-générées
    ('UK', 'United Kingdom'),
    ('US', 'United States of America');

INSERT INTO CITY(name, population, country_id) VALUES
    ('Paris', 12, SELECT id FROM Country WHERE code = 'FR'),
    ('London', 18, SELECT id FROM Country WHERE code = 'UK'),
    ('New York', 27, SELECT id FROM Country WHERE code = 'US');

Rajouter ALTER SEQUENCE country_id_seq RESTART WITH 4;
pour Postgresql
    */