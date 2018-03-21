INSERT INTO Role (idRole, name)
SELECT * FROM (SELECT 1, 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT idRole FROM Role WHERE idRole = 1
) LIMIT 1;

INSERT INTO Role (idRole, name)
SELECT * FROM (SELECT 2, 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT idRole FROM Role WHERE idRole = 2
) LIMIT 1;

INSERT INTO User (idUser, enabled, historiqueIdentifier, login, password, surname, userEmail, userName)
SELECT * FROM (SELECT 1 as idUser,1 as enabled,'adminHistory', 'admin', '$2a$10$XbQUFZcAA.RmuYHqUCZ4zOmriZ.KmmJpPWkiTN64A3.Fo88nOIxou', 'surname', 'admin.admin@mailObligatoire.com', 'userName admin') AS tmp
WHERE NOT EXISTS (
    SELECT idUser FROM User WHERE idUser = 1
) LIMIT 1;

INSERT INTO user_role (idUser, idRole)
SELECT 1, 1 FROM DUAL
WHERE NOT EXISTS (
    SELECT idUser FROM user_role WHERE idUser = 1 and idRole=1
) LIMIT 1;