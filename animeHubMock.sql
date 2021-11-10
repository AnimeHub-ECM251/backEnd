
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


-- Criar animes
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Naruto', 1, 'https://br.web.img3.acsta.net/c_310_420/pictures/16/04/11/16/56/089875.jpg', 'Naruto é uma série de mangá escrita e ilustrada por Masashi Kishimoto', 220, '2002-10-03', 'Pierrot', -1, 10);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Boku no Hero', 2, 'https://upload.wikimedia.org/wikipedia/en/thumb/5/5a/Boku_no_Hero_Academia_Volume_1.png/220px-Boku_no_Hero_Academia_Volume_1.png', 'Em um mundo onde 80% da população mundial possuem super poderes, o tímido estudante Midoriya Izuku teve a infelicidade de nascer sem poderes.Cap. 1 Grande fã do sorridente All Might, o herói conhecido como o símbolo da paz, Izuku, sofre com a frustração de saber que jamais terá uma individualidade especial para que possa se tornar, assim como seu grande ídolo, em um defensor dos fracos e oprimidos.[12]

Mesmo sofrendo bullying por seus amigos de escola, como o arrogante Katsuki, o garoto nunca abandonou o herói existente dentro de si. Gentil e generoso, ele está sempre pronto a ajudar quem precisa.[12]

Porém, um inesperado encontro irá mudar o destino de Izuku. Destino esse que o levará a ingressar no tão sonhado colégio U.A., instituição para onde os grandes heróis vão estudar e treinar. A partir daí, as cortinas de uma fantástica aventura repleta de personagens cativantes e temerosos vilões se abrem para o jovem Midoriya.[12]', 113, '2016-04-03', 'Bones', -1, 8);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Eighty Six (86)', 3, 'https://cdn.myanimelist.net/images/anime/1987/117507.jpg', 'Com o súbito ataque do exercito do império inimigo, a Republica dos 85 distritos foi forçada a tomar atitudes e desenvolver armas capazes de lutar contras os Legions, veículos militares não tripulados que foram enviados para atacar o país. Por mais que a Republica dos 85 distritos diz fazer uma guerra limpa de sangue, onde também não envia soldados para combater os Legions, em verdade existe o 86º distrito, uma área omitida da população, que é usada constantemente na guerra.', 24, '2021-04-11', 'A-1 Pictures', -1, 10);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Platinum End', 4, 'https://cdn.myanimelist.net/images/anime/1992/116576.jpg', 'Mirai Kakehashi é um jovem estudante farto de uma vida de abusos por parte do tio, tia e primos que o criaram desde a morte dos seus pais, num misterioso acidente. No entanto, quando se tenta suicidar ao atirar-se do alto de um edifício, é salvo por um anjo chamado Nasse (ナ ッ セ?), que lhe conta que é o seu anjo da guarda e que sabe tudo sobre a sua vida. Ao saber por Nasse que os seus pais adotivos são os responsáveis pelo acidente que vitimou os seus pais e irmão, Mirai usa os poderes que ela lhe concedeu para os enfrentar e saber a verdade. Contudo, as provações de Mirai estão apenas a começar, pois Nasse conta-lhe que Deus se vai aposentar em 999 dias e que treze candidatos foram selecionados para o substituir, incluindo o próprio Mirai. Entre os candidatos há alguns capazes de fazer seja o que for para ganhar, incluindo matar inocentes ou os outros candidatos.', 24, '2021-10-08', 'Signal.MD', -1, 6);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Shingeki no Kyojin', 5, 'https://cdn.myanimelist.net/images/anime/1517/100633.jpg', 'Os seres humanos se depararam com a repentina aparição dos Titãs no distrito de Shiganshina após mais de um século de paz. Eren Yeager, Mikasa Ackerman-sua irmã adotiva- e seu amigo de infância, Armin Arlert, testemunham o aparecimento de uma Titã de 60 metros, o Titã Colossal, e outro menor, o Titã Blindado, que abrem uma brecha na muralha Maria. Os Titãs, em seguida, invadem a cidade e fazem uma carnificina, incluindo a morte da mãe de Eren, que é devorada diante de seus olhos. Ele então decide se vingar e matar todos os Titãs, entrando para Divisão de Exploração.[5]

Cinco anos mais tarde, os três graduados cadetes foram enviados para o distrito de Trost, uma das cidades da fronteira que se localiza na Muralha Rose, quando o Titã Colossal reaparece e faz novamente uma brecha na muralha; na batalha que se seguiu, Eren foi devorado por um dos Titãs na frente de Armin. Pouco tempo depois, um Titã aparece e ataca os outros Titãs, em vez de seres humanos; esse acaba se revelando o próprio Eren, que de alguma forma adquiriu a capacidade de se transformar em Titã. Embora seja considerado uma ameaça por alguns, ele ajuda os soldados a recuperar o distrito de Trost fechando a brecha da muralha. Depois de ser levado à justiça, ele é recrutado pela Divisão de Exploração com a supervisão da Divisão de Exploração de Operações Especiais, liderada pelo capitão Levi.[6]

Em uma expedição para Shinganshina em busca de respostas para o mistério em torno de Eren, os soldados são atacados por um Titã Fêmea que tenta capturar Eren. Embora os soldados são capazes de capturar rapidamente a Titã Fêmea, ela se liberta e mata todos da equipe de Levi Ackerman, forçando o fim da expedição. Armin descobre que a Titã Fêmea é Annie Leonhardt, um dos cadetes que ensinaram Eren a lutar, e elabora um plano para capturá-la no distrito Stohess. A missão foi um sucesso, embora o alvo passa a proteger-se dentro de um cristal. Durante esta operação, os danos colaterais revelam que os Titãs residem dentro das paredes das muralhas', 75, '2013-04-19', 'Wit Studio (#1–59) e MAPPA (#60–)', -1, 9);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Clannad', 6, 'https://cdn.myanimelist.net/images/anime/1299/110774.jpg', 'A história de Clannad gira em torno de Tomoya Okazaki, um estudante do terceiro ano do ensino médio que não gosta de sua vida. A mãe de Tomoya (Atsuko) morreu quando Tomoya era jovem, deixando seu pai (Naoyuki) para criá-lo. Após o acidente, o pai de Tomoya voltou-se para o álcool e jogos de azar, e teve brigas frequentes com seu filho. Um dia, Naoyuki, enquanto discutia com seu filho, bateu Tomoya contra a janela, deslocando o ombro de Tomoya. Essa lesão impede Tomoya de jogar no time de basquete e faz com que ele se distancie dos outros. Desde então, seu pai tratou Tomoya de maneira amável, mas distante, como se Tomoya e ele fossem estranhos, e não uma família. Isso machuca Tomoya mais do que seu relacionamento anterior com seu pai, e o constrangimento de voltar para casa leva Tomoya a ficar constantemente fora a noite toda. Assim começa sua vida delinquente. O bom amigo de Tomoya, Youhei Sunohara, que foi expulso do clube de futebol por causa de uma disputa com seus veteranos, também é um delinquente e costuma ficar em seu dormitório com Tomoya sem fazer nada.

A história começa na segunda-feira, 14 de abril de 2003, no início do ano letivo, [4] quando Tomoya conhece Nagisa Furukawa, uma garota de fala mansa que é um ano mais velha do que ele, mas está repetindo o último ano no ensino médio devido a estar doente grande parte do ano anterior. Seu objetivo é entrar para o clube de teatro, o que ela não pôde fazer devido à sua doença, mas eles descobrem que o clube de teatro foi dissolvido depois que os poucos membros restantes se formaram. Como Tomoya tem muito tempo para matar, ele ajuda Nagisa a reformar o clube de teatro. Durante este período, Tomoya conhece e sai com várias outras meninas que ele conhece bem e ajuda em seus problemas individuais.', 49, '2013-03-07', 'Kyoto Animation', -1, 10);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Demon Slayer: Kimetsu no Yaiba', 7, 'https://myanimelist.net/anime/49926/Kimetsu_no_Yaiba__Mugen_Ressha-hen/pics', 'Ambientada no Japão durante o Período Taishō (1912-1926), a história gira ao entorno de Tanjirō Kamado, um garoto bondoso e inteligente que vive junto com sua mãe e seus irmãos, ganhando dinheiro vendendo carvão, assim como seu falecido pai. Certo dia, ao voltar para casa após ter ido a uma cidade vender carvão, Tanjiro descobre que toda sua família foi atacada por onis, sendo que uma de suas irmãs, Nezuko, é a única que sobreviveu ao ataque. Nezuko então passa a ser um oni, mas ela surpreendentemente ainda demonstra sinais de emoções e pensamentos humanos. Tanjirō decide então se tornar um caçador de onis, e com a ajuda de Nezuko, passa a sair em jornadas pelo Japão a fim de impedir que a mesma tragédia que afetou sua família aconteça com outras pessoas, enquanto que ele busca uma maneira de tornar Nezuko humana novamente.', 26, '2019-04-07', 'ufotable', -1, 8);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Dragon Ball Z', 8, 'https://upload.wikimedia.org/wikipedia/pt/4/44/DBZ_Logo.png', 'O irmÃ£o de Son Goku (protagonista da sÃ©rie Dragon Ball) aparece na Terra e lhe informa que sua famÃ­lia pertence a outro planeta.', 291, '1989-04-26', 'Toei Animation', -1, 10);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Black Clover', 9, 'https://upload.wikimedia.org/wikipedia/pt/6/69/Black_Clover%2C_volume_1.jpg', 'Black Clover Ã© uma sÃ©rie de mangÃ¡ adolescente do tipo shonen e fantasia japonÃªs, escrita e ilustrada por YÅ«ki Tabata', 170, '2017-05-02', 'Xebec Zwei', -1, 10);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('KonoSuba', 10, 'https://upload.wikimedia.org/wikipedia/en/thumb/3/3e/Kono_Subarashii_Sekai_ni_Shukufuku_o%21_light_novel_volume_1_cover.jpg/220px-Kono_Subarashii_Sekai_ni_Shukufuku_o%21_light_novel_volume_1_cover.jpg', 'Após uma morte prematura e embaraçosa, Kazuma Satō, um adolescente japonês enclausurado NEET , conhece uma deusa chamada Aqua, que se oferece para reencarnar em um mundo paralelo com elementos MMORPG , onde ele pode partir em aventuras e lutar contra monstros. Apesar de ter sido oferecido um item com superpoderes ou habilidade para usar neste novo mundo, Kazuma, após alguma provocação, escolhe a própria Aqua para acompanhá-lo até a cidade de Axel, rapidamente descobrindo que sua distração não é benéfica. LN 1.P Com Aqua incapaz de retornar à vida após a morte até o Rei Diaboé derrotado, os dois formam um partido e recrutam outros dois membros; um mágico obcecado por explosões chamado Megumin e um cruzado masoquista chamado Darkness. LN 1.2.4 Devido às habilidades disfuncionais do grupo, Kazuma rapidamente desiste da ideia de derrotar o Rei Demônio e tenta viver uma vida confortável, apenas para descobrir que as circunstâncias de sua vida diária estão forçando ele e seu grupo a se encontrarem e batalharem os generais do Rei Diabo. LN 2.3', 20, '2016-01-14', 'Studio Deen', -1, 6);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Re:Zero − Starting Life in Another World', 11, 'https://upload.wikimedia.org/wikipedia/en/thumb/3/3c/Re-Zero_kara_Hajimeru_Isekai_Seikatsu_light_novel_volume_1_cover.jpg/220px-Re-Zero_kara_Hajimeru_Isekai_Seikatsu_light_novel_volume_1_cover.jpg', 'Subaru Natsuki é um NEET que de repente é convocado para um mundo fantasioso. Logo depois de chegar, ele é morto enquanto tentava ajudar uma jovem meio-elfa de sua amizade, Emilia, que é um candidato a se tornar o próximo governante do Reino de Lugunica, apenas para reviver algumas horas no passado. Depois de morrer algumas vezes, Subaru percebe que tem o poder de voltar atrás no tempo após sua morte. Depois de ajudar com sucesso Emilia, Subaru começa a viver em uma das mansões dos Roswaal Mathers como mordomo. Em gratidão e carinho por Emilia, Subaru faz uso de sua recém-descoberta capacidade de protegê-la e ajudar em sua ambição de ser nomeada com sucesso como a próxima rainha, também prestando assistência a outros amigos que faz ao longo do caminho, enquanto sofre com a dor infligido a ele toda vez que ele morre, e carregando consigo as memórias de tudo o que aconteceu antes de seu poder ser ativado, o que é esquecido por todos, exceto por ele.', 50, '2016-04-04', 'White Fox', -1, 7);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Darling in the Franxx', 12, 'https://upload.wikimedia.org/wikipedia/pt/e/eb/Darling_in_the_Franxx_Poster.jpg', 'Darling in the Franxx, Ã© um anime de ficÃ§Ã£o cientÃ­fica e romance japonÃªs', 27, '2018-01-13', 'CloverWorks e Studio Trigger', -1, 7);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Sekai Saikou no Ansatsusha, Isekai Kizoku ni Tensei suru', 13, 'https://cdn.myanimelist.net/images/anime/1928/117620.jpg', 'O protagonista é um velho que viveu sua vida como o maior assassino do mundo. Devido à sua idade avançada, finalmente foi decidido que ele poderia se aposentar. No entanto, o avião em que ele estava foi sabotado e mesmo suas habilidades como o maior assassino não puderam salvá-lo. Quando ele morreu, ele foi acordado por uma deusa que queria reencarná-lo em um mundo de espadas e magia porque ela precisa de seu conjunto de habilidades para evitar a destruição daquele mundo nas mãos do Herói. Aceitando o pedido, o protagonista desperta como Lugh Tuatha Dé e jura finalmente viver sua vida em sua plenitude como ser humano e não como uma ferramenta.', 12, '2021-10-06', 'Silver Link e Studio Palette', -1, 8.5);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Mirai Nikki', 14, 'https://upload.wikimedia.org/wikipedia/pt/1/1a/Mirainikkicover1.jpg', 'Mirai Nikki Ã© um mangÃ¡ shÅnen escrito e ilustrado por Sakae Esuno', 26, '2011-10-10', 'Asread', -1, 9);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Hunter x Hunter', 15, 'https://upload.wikimedia.org/wikipedia/pt/6/63/Hunter_x_Hunter_Volume_1.JPG', 'Hunter Ã Hunter Ã© uma sÃ©rie de mangÃ¡ escrita e ilustrada por Yoshihiro Togashi.', 148, '1999-10-16', 'Nippon Animation', -1, 10);
INSERT INTO AnimeHubDB.Anime (title, id, image, synopsis, episodes, launchDate, studio, publicRating, websiteRating) VALUES ('Boku no Pico', 16, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAX_euUOtlqRpjdbqbBMcDAdawVWlIwLe6rQ&usqp=CAU', 'O otimista e afeminado Pico está trabalhando na cafeteria de seu avô, Café Bebe, durante o verão. Tamotsu é um trabalhador de colarinho branco em busca de uma fuga da mundanidade de sua vida cotidiana. Quando eles se encontram no café, faíscas de amor e luxúria rapidamente aproximam os dois. As noções convencionais de idade, sexo e sexualidade são desfeitas à medida que o par busca a gratificação carnal na companhia um do outro. Mas os prazeres da carne equivalem a uma conexão entre os corações?', 1, '2006-09-07', 'Sugar Boy e Blue Cat', -1, 12);



-- Criar Usuários
INSERT INTO AnimeHubDB.User (login, password, email, profilePicture, role, id) VALUES ('user1', 'senha123', 'user1@email.com', 'https://www.drnitinborse.com/wp-content/uploads/2018/02/user-icon-300x300.png', 1, 1);



-- Criar Comentários
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (1, 'Naruto-kun xD', 0, 0, 1, 1);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (2, 'boku', 0, 0, 1, 2);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (3, 'hero', 0, 0, 1, 2);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (4, 'bem legal', 0, 0, 1, 2);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (5, 'narutinho e hinata s2', 0, 0, 1, 1);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (6, 'sasuke', 0, 0, 1, 1);
INSERT INTO AnimeHubDB.Comment (id, text, likes, deslikes, idUser, idAnime) VALUES (7, 'Naruto teste', 0, 0, 1, 1);

-- Criar Gêneros

INSERT INTO AnimeHubDB.Genres (id, genre) VALUES (1, 'ACTION');
INSERT INTO AnimeHubDB.Genres (id, genre) VALUES (2, 'ROMANCE');



