
-- Criar banco de dados
CREATE DATABASE AnimeHubDB;

USE AnimeHubDB;

-- Criar tabelas
create table Anime
(
    title         varchar(256)      not null,
    id            int auto_increment
        primary key,
    image         varchar(256)      not null,
    synopsis      longtext          null,
    episodes      int               null,
    launchDate    date              null,
    studio        varchar(256)      null,
    publicRating  double default -1 not null,
    websiteRating double default -1 not null,
    constraint Anime_title_uindex
        unique (title)
);

create table Genres
(
    id    int auto_increment,
    genre varchar(64) null,
    constraint Genres_id_uindex
        unique (id)
);

alter table Genres
    add primary key (id);

create table Anime_Genre
(
    idAnime int not null,
    idGenre int not null,
    id      int auto_increment,
    constraint Anime_Genre_id_uindex
        unique (id),
    constraint Anime_Genre_id_uindex_2
        unique (id),
    constraint Anime_Genre_Anime_id_fk
        foreign key (idAnime) references Anime (id),
    constraint Anime_Genre_Genres_id_fk
        foreign key (idGenre) references Genres (id)
);

alter table Anime_Genre
    add primary key (id);

create table User
(
    login          varchar(32)                                                                                          not null,
    password       varchar(256)                                                                                         not null,
    email          varchar(256)                                                                                         not null,
    profilePicture varchar(256) default 'https://www.drnitinborse.com/wp-content/uploads/2018/02/user-icon-300x300.png' not null,
    role           int          default 1                                                                               not null,
    id             int auto_increment,
    constraint User_id_uindex
        unique (id),
    constraint User_login_uindex
        unique (login)
);

alter table User
    add primary key (id);

create table Comment
(
    id       int auto_increment
        primary key,
    text     longtext      null,
    likes    int default 0 not null,
    deslikes int default 0 not null,
    idUser   int           not null,
    idAnime  int           not null,
    constraint Comment_Anime_id_fk
        foreign key (idAnime) references Anime (id),
    constraint Comment_User_id_fk
        foreign key (idUser) references User (id)
);

create table Rating
(
    id      int auto_increment,
    idUser  int not null,
    idAnime int not null,
    rating  int not null,
    constraint Rating_id_uindex
        unique (id),
    constraint Rating_Anime_id_fk
        foreign key (idAnime) references Anime (id),
    constraint Rating_User_id_fk
        foreign key (idUser) references User (id)
);

alter table Rating
    add primary key (id);

create table Watch_List
(
    id      int auto_increment,
    idAnime int not null,
    idUser  int not null,
    constraint Watch_List_id_uindex
        unique (id),
    constraint WatchList_Anime_id_fk
        foreign key (idAnime) references Anime (id),
    constraint WatchList_User_id_fk
        foreign key (idUser) references User (id)
);

alter table Watch_List
    add primary key (id);


-- Setar o DB para UTF-8


-- Criar animes
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Naruto', 1, 'https://cdn.myanimelist.net/images/anime/13/17405.jpg', 'Naruto ?? uma s??rie de mang?? escrita e ilustrada por Masashi Kishimoto', 220, '2002-10-03', 'Pierrot', 2, 5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Boku no Hero', 2, 'https://cdn.myanimelist.net/images/anime/10/78745.jpg', 'Em um mundo onde 80% da popula????o mundial possuem super poderes, o t??mido estudante Midoriya Izuku teve a infelicidade de nascer sem poderes.Cap. 1 Grande f?? do sorridente All Might, o her??i conhecido como o s??mbolo da paz, Izuku, sofre com a frustra????o de saber que jamais ter?? uma individualidade especial para que possa se tornar, assim como seu grande ??dolo, em um defensor dos fracos e oprimidos.[12]

Mesmo sofrendo bullying por seus amigos de escola, como o arrogante Katsuki, o garoto nunca abandonou o her??i existente dentro de si. Gentil e generoso, ele est?? sempre pronto a ajudar quem precisa.[12]

Por??m, um inesperado encontro ir?? mudar o destino de Izuku. Destino esse que o levar?? a ingressar no t??o sonhado col??gio U.A., institui????o para onde os grandes her??is v??o estudar e treinar. A partir da??, as cortinas de uma fant??stica aventura repleta de personagens cativantes e temerosos vil??es se abrem para o jovem Midoriya.[12]', 113, '2016-04-03', 'Bones', -1, 4);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Eighty Six (86)', 3, 'https://cdn.myanimelist.net/images/anime/1987/117507.jpg', 'Com o s??bito ataque do exercito do imp??rio inimigo, a Republica dos 85 distritos foi for??ada a tomar atitudes e desenvolver armas capazes de lutar contras os Legions, ve??culos militares n??o tripulados que foram enviados para atacar o pa??s. Por mais que a Republica dos 85 distritos diz fazer uma guerra limpa de sangue, onde tamb??m n??o envia soldados para combater os Legions, em verdade existe o 86?? distrito, uma ??rea omitida da popula????o, que ?? usada constantemente na guerra.', 24, '2021-04-11', 'A-1 Pictures', -1, 5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Platinum End', 4, 'https://cdn.myanimelist.net/images/anime/1992/116576.jpg', 'Mirai Kakehashi ?? um jovem estudante farto de uma vida de abusos por parte do tio, tia e primos que o criaram desde a morte dos seus pais, num misterioso acidente. No entanto, quando se tenta suicidar ao atirar-se do alto de um edif??cio, ?? salvo por um anjo chamado Nasse (??? ??? ????), que lhe conta que ?? o seu anjo da guarda e que sabe tudo sobre a sua vida. Ao saber por Nasse que os seus pais adotivos s??o os respons??veis pelo acidente que vitimou os seus pais e irm??o, Mirai usa os poderes que ela lhe concedeu para os enfrentar e saber a verdade. Contudo, as prova????es de Mirai est??o apenas a come??ar, pois Nasse conta-lhe que Deus se vai aposentar em 999 dias e que treze candidatos foram selecionados para o substituir, incluindo o pr??prio Mirai. Entre os candidatos h?? alguns capazes de fazer seja o que for para ganhar, incluindo matar inocentes ou os outros candidatos.', 24, '2021-10-08', 'Signal.MD', -1, 3);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Shingeki no Kyojin', 5, 'https://cdn.myanimelist.net/images/anime/1517/100633.jpg', 'Os seres humanos se depararam com a repentina apari????o dos Tit??s no distrito de Shiganshina ap??s mais de um s??culo de paz. Eren Yeager, Mikasa Ackerman-sua irm?? adotiva- e seu amigo de inf??ncia, Armin Arlert, testemunham o aparecimento de uma Tit?? de 60 metros, o Tit?? Colossal, e outro menor, o Tit?? Blindado, que abrem uma brecha na muralha Maria. Os Tit??s, em seguida, invadem a cidade e fazem uma carnificina, incluindo a morte da m??e de Eren, que ?? devorada diante de seus olhos. Ele ent??o decide se vingar e matar todos os Tit??s, entrando para Divis??o de Explora????o.[5]

Cinco anos mais tarde, os tr??s graduados cadetes foram enviados para o distrito de Trost, uma das cidades da fronteira que se localiza na Muralha Rose, quando o Tit?? Colossal reaparece e faz novamente uma brecha na muralha; na batalha que se seguiu, Eren foi devorado por um dos Tit??s na frente de Armin. Pouco tempo depois, um Tit?? aparece e ataca os outros Tit??s, em vez de seres humanos; esse acaba se revelando o pr??prio Eren, que de alguma forma adquiriu a capacidade de se transformar em Tit??. Embora seja considerado uma amea??a por alguns, ele ajuda os soldados a recuperar o distrito de Trost fechando a brecha da muralha. Depois de ser levado ?? justi??a, ele ?? recrutado pela Divis??o de Explora????o com a supervis??o da Divis??o de Explora????o de Opera????es Especiais, liderada pelo capit??o Levi.[6]

Em uma expedi????o para Shinganshina em busca de respostas para o mist??rio em torno de Eren, os soldados s??o atacados por um Tit?? F??mea que tenta capturar Eren. Embora os soldados s??o capazes de capturar rapidamente a Tit?? F??mea, ela se liberta e mata todos da equipe de Levi Ackerman, for??ando o fim da expedi????o. Armin descobre que a Tit?? F??mea ?? Annie Leonhardt, um dos cadetes que ensinaram Eren a lutar, e elabora um plano para captur??-la no distrito Stohess. A miss??o foi um sucesso, embora o alvo passa a proteger-se dentro de um cristal. Durante esta opera????o, os danos colaterais revelam que os Tit??s residem dentro das paredes das muralhas', 75, '2013-04-19', 'Wit Studio (#1???59) e MAPPA (#60???)', -1, 4.5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Clannad', 6, 'https://cdn.myanimelist.net/images/anime/1299/110774.jpg', 'A hist??ria de Clannad gira em torno de Tomoya Okazaki, um estudante do terceiro ano do ensino m??dio que n??o gosta de sua vida. A m??e de Tomoya (Atsuko) morreu quando Tomoya era jovem, deixando seu pai (Naoyuki) para cri??-lo. Ap??s o acidente, o pai de Tomoya voltou-se para o ??lcool e jogos de azar, e teve brigas frequentes com seu filho. Um dia, Naoyuki, enquanto discutia com seu filho, bateu Tomoya contra a janela, deslocando o ombro de Tomoya. Essa les??o impede Tomoya de jogar no time de basquete e faz com que ele se distancie dos outros. Desde ent??o, seu pai tratou Tomoya de maneira am??vel, mas distante, como se Tomoya e ele fossem estranhos, e n??o uma fam??lia. Isso machuca Tomoya mais do que seu relacionamento anterior com seu pai, e o constrangimento de voltar para casa leva Tomoya a ficar constantemente fora a noite toda. Assim come??a sua vida delinquente. O bom amigo de Tomoya, Youhei Sunohara, que foi expulso do clube de futebol por causa de uma disputa com seus veteranos, tamb??m ?? um delinquente e costuma ficar em seu dormit??rio com Tomoya sem fazer nada.

A hist??ria come??a na segunda-feira, 14 de abril de 2003, no in??cio do ano letivo, [4] quando Tomoya conhece Nagisa Furukawa, uma garota de fala mansa que ?? um ano mais velha do que ele, mas est?? repetindo o ??ltimo ano no ensino m??dio devido a estar doente grande parte do ano anterior. Seu objetivo ?? entrar para o clube de teatro, o que ela n??o p??de fazer devido ?? sua doen??a, mas eles descobrem que o clube de teatro foi dissolvido depois que os poucos membros restantes se formaram. Como Tomoya tem muito tempo para matar, ele ajuda Nagisa a reformar o clube de teatro. Durante este per??odo, Tomoya conhece e sai com v??rias outras meninas que ele conhece bem e ajuda em seus problemas individuais.', 49, '2013-03-07', 'Kyoto Animation', -1, 5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Demon Slayer: Kimetsu no Yaiba', 7, 'https://cdn.myanimelist.net/images/anime/1286/99889.jpg', 'Ambientada no Jap??o durante o Per??odo Taish?? (1912-1926), a hist??ria gira ao entorno de Tanjir?? Kamado, um garoto bondoso e inteligente que vive junto com sua m??e e seus irm??os, ganhando dinheiro vendendo carv??o, assim como seu falecido pai. Certo dia, ao voltar para casa ap??s ter ido a uma cidade vender carv??o, Tanjiro descobre que toda sua fam??lia foi atacada por onis, sendo que uma de suas irm??s, Nezuko, ?? a ??nica que sobreviveu ao ataque. Nezuko ent??o passa a ser um oni, mas ela surpreendentemente ainda demonstra sinais de emo????es e pensamentos humanos. Tanjir?? decide ent??o se tornar um ca??ador de onis, e com a ajuda de Nezuko, passa a sair em jornadas pelo Jap??o a fim de impedir que a mesma trag??dia que afetou sua fam??lia aconte??a com outras pessoas, enquanto que ele busca uma maneira de tornar Nezuko humana novamente.', 26, '2019-04-07', 'ufotable', -1, 4);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Dragon Ball Z', 8, 'https://cdn.myanimelist.net/images/anime/1607/117271.jpg', 'O irm??o de Son Goku (protagonista da s??rie Dragon Ball) aparece na Terra e lhe informa que sua fam??lia pertence a outro planeta.', 291, '1989-04-26', 'Toei Animation', -1, 5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Black Clover', 9, 'https://cdn.myanimelist.net/images/anime/2/88336.jpg', 'Black Clover ?? uma s??rie de mang?? adolescente do tipo shonen e fantasia japon??s, escrita e ilustrada por Y??ki Tabata', 170, '2017-05-02', 'Xebec Zwei', -1, 5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('KonoSuba', 10, 'https://cdn.myanimelist.net/images/anime/8/77831.jpg', 'Ap??s uma morte prematura e embara??osa, Kazuma Sat??, um adolescente japon??s enclausurado NEET , conhece uma deusa chamada Aqua, que se oferece para reencarnar em um mundo paralelo com elementos MMORPG , onde ele pode partir em aventuras e lutar contra monstros. Apesar de ter sido oferecido um item com superpoderes ou habilidade para usar neste novo mundo, Kazuma, ap??s alguma provoca????o, escolhe a pr??pria Aqua para acompanh??-lo at?? a cidade de Axel, rapidamente descobrindo que sua distra????o n??o ?? ben??fica. LN 1.P Com Aqua incapaz de retornar ?? vida ap??s a morte at?? o Rei Diabo?? derrotado, os dois formam um partido e recrutam outros dois membros; um m??gico obcecado por explos??es chamado Megumin e um cruzado masoquista chamado Darkness. LN 1.2.4 Devido ??s habilidades disfuncionais do grupo, Kazuma rapidamente desiste da ideia de derrotar o Rei Dem??nio e tenta viver uma vida confort??vel, apenas para descobrir que as circunst??ncias de sua vida di??ria est??o for??ando ele e seu grupo a se encontrarem e batalharem os generais do Rei Diabo. LN 2.3', 20, '2016-01-14', 'Studio Deen', -1, 3);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Re:Zero ??? Starting Life in Another World', 11, 'https://cdn.myanimelist.net/images/anime/11/79410.jpg', 'Subaru Natsuki ?? um NEET que de repente ?? convocado para um mundo fantasioso. Logo depois de chegar, ele ?? morto enquanto tentava ajudar uma jovem meio-elfa de sua amizade, Emilia, que ?? um candidato a se tornar o pr??ximo governante do Reino de Lugunica, apenas para reviver algumas horas no passado. Depois de morrer algumas vezes, Subaru percebe que tem o poder de voltar atr??s no tempo ap??s sua morte. Depois de ajudar com sucesso Emilia, Subaru come??a a viver em uma das mans??es dos Roswaal Mathers como mordomo. Em gratid??o e carinho por Emilia, Subaru faz uso de sua rec??m-descoberta capacidade de proteg??-la e ajudar em sua ambi????o de ser nomeada com sucesso como a pr??xima rainha, tamb??m prestando assist??ncia a outros amigos que faz ao longo do caminho, enquanto sofre com a dor infligido a ele toda vez que ele morre, e carregando consigo as mem??rias de tudo o que aconteceu antes de seu poder ser ativado, o que ?? esquecido por todos, exceto por ele.', 50, '2016-04-04', 'White Fox', -1, 3.5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Darling in the Franxx', 12, 'https://cdn.myanimelist.net/images/anime/1614/90408.jpg', 'Darling in the Franxx, ?? um anime de fic????o cient??fica e romance japon??s', 27, '2018-01-13', 'CloverWorks e Studio Trigger', -1, 3.5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Sekai Saikou no Ansatsusha, Isekai Kizoku ni Tensei suru', 13, 'https://cdn.myanimelist.net/images/anime/1928/117620.jpg', 'O protagonista ?? um velho que viveu sua vida como o maior assassino do mundo. Devido ?? sua idade avan??ada, finalmente foi decidido que ele poderia se aposentar. No entanto, o avi??o em que ele estava foi sabotado e mesmo suas habilidades como o maior assassino n??o puderam salv??-lo. Quando ele morreu, ele foi acordado por uma deusa que queria reencarn??-lo em um mundo de espadas e magia porque ela precisa de seu conjunto de habilidades para evitar a destrui????o daquele mundo nas m??os do Her??i. Aceitando o pedido, o protagonista desperta como Lugh Tuatha D?? e jura finalmente viver sua vida em sua plenitude como ser humano e n??o como uma ferramenta.', 12, '2021-10-06', 'Silver Link e Studio Palette', -1, 4.25);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Mirai Nikki', 14, 'https://cdn.myanimelist.net/images/anime/13/33465.jpg', 'Mirai Nikki ?? um mang?? sh??nen escrito e ilustrado por Sakae Esuno', 26, '2011-10-10', 'Asread', -1, 4.5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Hunter x Hunter', 15, 'https://cdn.myanimelist.net/images/anime/11/33657.jpg', 'Hunter ?? Hunter ?? uma s??rie de mang?? escrita e ilustrada por Yoshihiro Togashi.', 148, '1999-10-16', 'Nippon Animation', -1, 5);
insert into AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) values ('Boku no Pico', 16, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAX_euUOtlqRpjdbqbBMcDAdawVWlIwLe6rQ&usqp=CAU', 'O otimista e afeminado Pico est?? trabalhando na cafeteria de seu av??, Caf?? Bebe, durante o ver??o. Tamotsu ?? um trabalhador de colarinho branco em busca de uma fuga da mundanidade de sua vida cotidiana. Quando eles se encontram no caf??, fa??scas de amor e lux??ria rapidamente aproximam os dois. As no????es convencionais de idade, sexo e sexualidade s??o desfeitas ?? medida que o par busca a gratifica????o carnal na companhia um do outro. Mas os prazeres da carne equivalem a uma conex??o entre os cora????es?', 1, '2006-09-07', 'Sugar Boy e Blue Cat', -1, 12);



-- Criar Usu??rios
INSERT INTO AnimeHubDB.User (login, password, email, profilePicture, role, id) VALUES ('user1', '55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251', 'user1@email.com', 'https://www.drnitinborse.com/wp-content/uploads/2018/02/user-icon-300x300.png', 1, 1);



-- Criar Coment??rios
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (1, 'Naruto-kun xD', 0, 0, 1, 1);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (2, 'boku', 0, 0, 1, 2);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (3, 'hero', 0, 0, 1, 2);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (4, 'bem legal', 0, 0, 1, 2);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (5, 'narutinho e hinata s2', 0, 0, 1, 1);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (6, 'sasuke', 0, 0, 1, 1);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (7, 'Naruto teste', 0, 0, 1, 1);

-- Criar G??neros

INSERT INTO AnimeHubDB.Genres (id, genre) VALUES (1, 'ACTION');
INSERT INTO AnimeHubDB.Genres (id, genre) VALUES (2, 'ROMANCE');

-- Criar Ratings
INSERT INTO AnimeHubDB.Rating (id, idUser, idAnime, rating) VALUES (1, 1, 3, 2);
INSERT INTO AnimeHubDB.Rating (id, idUser, idAnime, rating) VALUES (2, 1, 2, 3);
INSERT INTO AnimeHubDB.Rating (id, idUser, idAnime, rating) VALUES (3, 1, 1, 3);

-- Criar watchlists
INSERT INTO AnimeHubDB.Watch_List (id, idAnime, idUser) VALUES (2, 2, 1);
INSERT INTO AnimeHubDB.Watch_List (id, idAnime, idUser) VALUES (5, 15, 1);
INSERT INTO AnimeHubDB.Watch_List (id, idAnime, idUser) VALUES (8, 3, 1);
INSERT INTO AnimeHubDB.Watch_List (id, idAnime, idUser) VALUES (16, 1, 1);
