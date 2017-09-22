INSERT INTO spsys.uf
(id, sign)
VALUES(1, 'AC');
INSERT INTO spsys.uf
(id, sign)
VALUES(2, 'AL');
INSERT INTO spsys.uf
(id, sign)
VALUES(3, 'AM');
INSERT INTO spsys.uf
(id, sign)
VALUES(4, 'AP');
INSERT INTO spsys.uf
(id, sign)
VALUES(5, 'BA');
INSERT INTO spsys.uf
(id, sign)
VALUES(6, 'CE');
INSERT INTO spsys.uf
(id, sign)
VALUES(7, 'DF');
INSERT INTO spsys.uf
(id, sign)
VALUES(8, 'ES');
INSERT INTO spsys.uf
(id, sign)
VALUES(9, 'GO');
INSERT INTO spsys.uf
(id, sign)
VALUES(10, 'MA');
INSERT INTO spsys.uf
(id, sign)
VALUES(11, 'MG');
INSERT INTO spsys.uf
(id, sign)
VALUES(12, 'MS');
INSERT INTO spsys.uf
(id, sign)
VALUES(13, 'MT');
INSERT INTO spsys.uf
(id, sign)
VALUES(14, 'PA');
INSERT INTO spsys.uf
(id, sign)
VALUES(15, 'PB');
INSERT INTO spsys.uf
(id, sign)
VALUES(16, 'PE');
INSERT INTO spsys.uf
(id, sign)
VALUES(17, 'PI');
INSERT INTO spsys.uf
(id, sign)
VALUES(18, 'PR');
INSERT INTO spsys.uf
(id, sign)
VALUES(19, 'RJ');
INSERT INTO spsys.uf
(id, sign)
VALUES(20, 'RN');
INSERT INTO spsys.uf
(id, sign)
VALUES(21, 'RO');
INSERT INTO spsys.uf
(id, sign)
VALUES(22, 'RR');
INSERT INTO spsys.uf
(id, sign)
VALUES(23, 'RS');
INSERT INTO spsys.uf
(id, sign)
VALUES(24, 'SC');
INSERT INTO spsys.uf
(id, sign)
VALUES(25, 'SE');
INSERT INTO spsys.uf
(id, sign)
VALUES(26, 'SP');
INSERT INTO spsys.uf
(id, sign)
VALUES(27, 'TO');

-- Status
INSERT INTO spsys.status
(id, name)
VALUES(1, 'Aberto');
INSERT INTO spsys.status
(id, name)
VALUES(2, 'Executando');
INSERT INTO spsys.status
(id, name)
VALUES(3, 'Concluído');
INSERT INTO spsys.status
(id, name)
VALUES(4, 'Cancelado');

-- Support levels
INSERT INTO spsys.supportLevels
(id, levelName, levelWeight, trust)
VALUES(1, 'root', 10, 10);
INSERT INTO spsys.supportLevels
(id, levelName, levelWeight, trust)
VALUES(2, 'SuperUser', 9, 9);

-- Images
INSERT INTO spsys.images
(id, imgName, ext)
VALUES(1, '20170921-6a5s4da5s4d', 'png');

-- Users Support
INSERT INTO spsys.supportUsers
(id, name, snome, email, idConfEmail, pass, `level`, corpMobile, setorPhone, avatar, `desc`)
VALUES(8, 'Miguel', '.C', 'vicentcdb@gmail.com', '010101', '123123', 1, '4545', '4545', 1, '· Usuario Avançado');

-- Normal users
INSERT INTO spsys.users
(id, name, sname, email, idConfEmail, pass, `level`, mobile, phone, avatar, description, client, dataRegister)
VALUES(8, 'Miguel', '.C', 'vicentcdb@gmail.com', '010101', '123123', 1, '4545', '4545', 1, ' Usuario Avançado',1,'2017-09-21 18:42:59');


INSERT INTO spsys.clients set 
	name = 'ASSPM',
	phone = '117878-1212',
	email = 'imprensa@asspm.org.br',
	phoneB = '117878-1313',
	address = 'Av Cruzeiro do Sul 248',
	bairro = 'Centro',
	city = 'São Paulo',
	uf = 26,
	level = 10,
	desc/ = 'Cliente com militares reformados faturamento 1mi',
	logoImg = 1;
	
INSERT INTO spsys.department SET	
	name = 'Portaria',
	`desc` = 'Setor de controle de acessos',
	client = 1,
	phone = '11 78784545';
