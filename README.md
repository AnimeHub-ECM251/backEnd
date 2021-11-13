"# backEnd" 

# Rodar o banco de dados

## Buildar a imagem
 `docker-compose build `

## Rodar o container
` docker-compose up`

# Documentação da API

# AnimeHubAPI

Documentação da API do Back End do Anime Hub para uso da aplicação de Front End do Anime Hub.

Essa documentação está dividida em 5 partes:
- Anime
- Comentários
- Usuário
- Watch List
- Avaliação dos Usuários

## Indices

* [Anime](#anime)

    * [Atualizar Anime](#1-atualizar-anime)
    * [Criar anime](#2-criar-anime)
    * [Deletar Anime](#3-deletar-anime)
    * [Get Anime](#4-get-anime)
    * [Get Todos os Animes](#5-get-todos-os-animes)
    * [Get Todos os IDs de Animes por Página](#6-get-todos-os-ids-de-animes-por-página)

* [Avaliação dos Usuários](#avaliação-dos-usuários)

    * [Criar nova Avaliação](#1-criar-nova-avaliação)
    * [Get Avaliação de um Usuário](#2-get-avaliação-de-um-usuário)

* [Comentários](#comentários)

    * [Criar Comentário](#1-criar-comentário)
    * [Get Comentários por Anime](#2-get-comentários-por-anime)

* [Usuário](#usuário)

    * [Cadastrar Usuário](#1-cadastrar-usuário)
    * [Login de Usuário](#2-login-de-usuário)

* [Watch List](#watch-list)

    * [Adicionar/Remover Anime da Watch List](#1-adicionarremover-anime-da-watch-list)
    * [Verificar se Anime está adicionado na Watch List](#2-verificar-se-anime-está-adicionado-na-watch-list)


--------


## Anime
## Rotas para manipulação de animes
### GETs
- Get Anime
- Get Todos os Animes
- Get Todos os IDs de Animes por Página
### POSTs
- Criar Anime
- Atualizar Anime
- Deletar Anime



### 1. Atualizar Anime


Recebe um JSON com, obrigatoriamente, o ID do Anime e outros campos opcionais.

Sobrescreve os valores passados no JSON do Anime de ID especificado no Banco de Dados.


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{baseUrl}}/atualizar-anime
```



***Body:***

```js        
{
    "studio": "A-1 Pictures",
    "image": "https://cdn.myanimelist.net/images/anime/1987/117507.jpg",
    "websiteRating": "9.0",
    "synopsis": "Com o súbito ataque do exercito do império inimigo, a Republica dos 85 distritos foi forçada a tomar atitudes e desenvolver armas capazes de lutar contras os Legions, veículos militares não tripulados que foram enviados para atacar o país. Por mais que a Republica dos 85 distritos diz fazer uma guerra limpa de sangue, onde também não envia soldados para combater os Legions, em verdade existe o 86º distrito, uma área omitida da população, que é usada constantemente na guerra.",
    "launchDate": "2021-04-11",
    "title": "Eighty Six (86)",
    "episodes": "24"
}
```



***More example Requests/Responses:***


##### I. Example Request: Criar anime



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| title |  | Título do Anime |
| synopsis |  | Sinopse do Anime |
| launchDate |  | Dia de Lançamento do Anime |
| image |  | URL com imagem |
| episodes |  | Número de episódios lançados até o momento |
| studio |  | Estúdio produtor do Anime |



***Body:***

```js        
{
    "studio": "A-1 Pictures",
    "image": "https://cdn.myanimelist.net/images/anime/1987/117507.jpg",
    "websiteRating": "10.0",
    "synopsis": "Com o súbito ataque do exercito do império inimigo, a Republica dos 85 distritos foi forçada a tomar atitudes e desenvolver armas capazes de lutar contras os Legions, veículos militares não tripulados que foram enviados para atacar o país. Por mais que a Republica dos 85 distritos diz fazer uma guerra limpa de sangue, onde também não envia soldados para combater os Legions, em verdade existe o 86º distrito, uma área omitida da população, que é usada constantemente na guerra.",
    "launchDate": "2021-04-11",
    "title": "Eighty Six (86)",
    "episodes": "24"
}
```



##### I. Example Response: Criar anime
```js
Anime Eighty Six (86) was created
```


***Status Code:*** 200

<br>



### 2. Criar anime


Recebe um JSON com informações de um Anime e cria esse Anime no banco de dados


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{baseUrl}}/criar-anime
```



***Body:***

```js        
{
    "studio": "A-1 Pictures",
    "image": "https://cdn.myanimelist.net/images/anime/1987/117507.jpg",
    "websiteRating": "10.0",
    "synopsis": "Com o súbito ataque do exercito do império inimigo, a Republica dos 85 distritos foi forçada a tomar atitudes e desenvolver armas capazes de lutar contras os Legions, veículos militares não tripulados que foram enviados para atacar o país. Por mais que a Republica dos 85 distritos diz fazer uma guerra limpa de sangue, onde também não envia soldados para combater os Legions, em verdade existe o 86º distrito, uma área omitida da população, que é usada constantemente na guerra.",
    "launchDate": "2021-04-11",
    "title": "Eighty Six (86)",
    "episodes": "24"
}
```



***More example Requests/Responses:***


##### I. Example Request: Criar anime



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| title |  | Título do Anime |
| synopsis |  | Sinopse do Anime |
| launchDate |  | Dia de Lançamento do Anime |
| image |  | URL com imagem |
| episodes |  | Número de episódios lançados até o momento |
| studio |  | Estúdio produtor do Anime |



***Body:***

```js        
{
    "studio": "A-1 Pictures",
    "image": "https://cdn.myanimelist.net/images/anime/1987/117507.jpg",
    "websiteRating": "10.0",
    "synopsis": "Com o súbito ataque do exercito do império inimigo, a Republica dos 85 distritos foi forçada a tomar atitudes e desenvolver armas capazes de lutar contras os Legions, veículos militares não tripulados que foram enviados para atacar o país. Por mais que a Republica dos 85 distritos diz fazer uma guerra limpa de sangue, onde também não envia soldados para combater os Legions, em verdade existe o 86º distrito, uma área omitida da população, que é usada constantemente na guerra.",
    "launchDate": "2021-04-11",
    "title": "Eighty Six (86)",
    "episodes": "24"
}
```



##### I. Example Response: Criar anime
```js
Anime Eighty Six (86) was created
```


***Status Code:*** 200

<br>



### 3. Deletar Anime


Recebe um JSON com o ID de um Anime e o deleta do Banco de Dados.


***Endpoint:***

```bash
Method: POST
Type: 
URL: {{baseUrl}}/deletar-anime
```



### 4. Get Anime


Retorna informações do Anime


***Endpoint:***

```bash
Method: GET
Type: RAW
URL: {{baseUrl}}/anime/:idAnime
```



***URL variables:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime | 1 | ID do Anime (> 1) |



***More example Requests/Responses:***


##### I. Example Request: Get Anime



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime |  |  |



##### I. Example Response: Get Anime
```js
{
    "studio": "Pierrot",
    "image": "https://br.web.img3.acsta.net/c_310_420/pictures/16/04/11/16/56/089875.jpg",
    "websiteRating": "10.0",
    "id": "1",
    "synopsis": "Naruto Ã© uma sÃ©rie de mangÃ¡ escrita e ilustrada por Masashi Kishimoto",
    "launchDate": "2002-10-03",
    "title": "Naruto",
    "episodes": "220",
    "publicRating": "-1.0"
}
```


***Status Code:*** 200

<br>



### 5. Get Todos os Animes


Retorna informações sobre todos os Animes


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{baseUrl}}/todos-animes
```



***More example Requests/Responses:***


##### I. Example Request: Get Todos os Animes



##### I. Example Response: Get Todos os Animes
```js
[
    {
        "studio": "Pierrot",
        "image": "https://br.web.img3.acsta.net/c_310_420/pictures/16/04/11/16/56/089875.jpg",
        "websiteRating": "10.0",
        "id": "1",
        "synopsis": "Naruto Ã© uma sÃ©rie de mangÃ¡ escrita e ilustrada por Masashi Kishimoto",
        "launchDate": "2002-10-03",
        "title": "Naruto",
        "episodes": "220",
        "publicRating": "-1.0"
    },
    {
        "studio": "Bones",
        "image": "https://upload.wikimedia.org/wikipedia/en/thumb/5/5a/Boku_no_Hero_Academia_Volume_1.png/220px-Boku_no_Hero_Academia_Volume_1.png",
        "websiteRating": "8.0",
        "id": "2",
        "synopsis": "Em um mundo onde 80% da populaÃ§Ã£o mundial possuem super poderes, o tÃ\u00admido estudante Midoriya Izuku teve a infelicidade de nascer sem poderes.Cap. 1 Grande fÃ£ do sorridente All Might, o herÃ³i conhecido como o sÃ\u00admbolo da paz, Izuku, sofre com a frustraÃ§Ã£o de saber que jamais terÃ¡ uma individualidade especial para que possa se tornar, assim como seu grande Ã\u00addolo, em um defensor dos fracos e oprimidos.[12]\n\nMesmo sofrendo bullying por seus amigos de escola, como o arrogante Katsuki, o garoto nunca abandonou o herÃ³i existente dentro de si. Gentil e generoso, ele estÃ¡ sempre pronto a ajudar quem precisa.[12]\n\nPorÃ©m, um inesperado encontro irÃ¡ mudar o destino de Izuku. Destino esse que o levarÃ¡ a ingressar no tÃ£o sonhado colÃ©gio U.A., instituiÃ§Ã£o para onde os grandes herÃ³is vÃ£o estudar e treinar. A partir daÃ\u00ad, as cortinas de uma fantÃ¡stica aventura repleta de personagens cativantes e temerosos vilÃµes se abrem para o jovem Midoriya.[12]",
        "launchDate": "2016-04-03",
        "title": "Boku no Hero",
        "episodes": "113",
        "publicRating": "-1.0"
    },
    {
        "studio": "A-1 Pictures",
        "image": "https://cdn.myanimelist.net/images/anime/1987/117507.jpg",
        "websiteRating": "10.0",
        "id": "3",
        "synopsis": "Com o sÃºbito ataque do exercito do impÃ©rio inimigo, a Republica dos 85 distritos foi forÃ§ada a tomar atitudes e desenvolver armas capazes de lutar contras os Legions, veÃ\u00adculos militares nÃ£o tripulados que foram enviados para atacar o paÃ\u00ads. Por mais que a Republica dos 85 distritos diz fazer uma guerra limpa de sangue, onde tambÃ©m nÃ£o envia soldados para combater os Legions, em verdade existe o 86Âº distrito, uma Ã¡rea omitida da populaÃ§Ã£o, que Ã© usada constantemente na guerra.",
        "launchDate": "2021-04-11",
        "title": "Eighty Six (86)",
        "episodes": "24",
        "publicRating": "-1.0"
    },
    {
        "studio": "Signal.MD",
        "image": "https://cdn.myanimelist.net/images/anime/1992/116576.jpg",
        "websiteRating": "6.0",
        "id": "4",
        "synopsis": "Mirai Kakehashi Ã© um jovem estudante farto de uma vida de abusos por parte do tio, tia e primos que o criaram desde a morte dos seus pais, num misterioso acidente. No entanto, quando se tenta suicidar ao atirar-se do alto de um edifÃ\u00adcio, Ã© salvo por um anjo chamado Nasse (ãƒŠ ãƒƒ ã‚»?), que lhe conta que Ã© o seu anjo da guarda e que sabe tudo sobre a sua vida. Ao saber por Nasse que os seus pais adotivos sÃ£o os responsÃ¡veis pelo acidente que vitimou os seus pais e irmÃ£o, Mirai usa os poderes que ela lhe concedeu para os enfrentar e saber a verdade. Contudo, as provaÃ§Ãµes de Mirai estÃ£o apenas a comeÃ§ar, pois Nasse conta-lhe que Deus se vai aposentar em 999 dias e que treze candidatos foram selecionados para o substituir, incluindo o prÃ³prio Mirai. Entre os candidatos hÃ¡ alguns capazes de fazer seja o que for para ganhar, incluindo matar inocentes ou os outros candidatos.",
        "launchDate": "2021-10-08",
        "title": "Platinum End",
        "episodes": "24",
        "publicRating": "-1.0"
    },
    {
        "studio": "Wit Studio (#1â€“59) e MAPPA (#60â€“)",
        "image": "https://cdn.myanimelist.net/images/anime/1517/100633.jpg",
        "websiteRating": "9.0",
        "id": "5",
        "synopsis": "Os seres humanos se depararam com a repentina apariÃ§Ã£o dos TitÃ£s no distrito de Shiganshina apÃ³s mais de um sÃ©culo de paz. Eren Yeager, Mikasa Ackerman-sua irmÃ£ adotiva- e seu amigo de infÃ¢ncia, Armin Arlert, testemunham o aparecimento de uma TitÃ£ de 60 metros, o TitÃ£ Colossal, e outro menor, o TitÃ£ Blindado, que abrem uma brecha na muralha Maria. Os TitÃ£s, em seguida, invadem a cidade e fazem uma carnificina, incluindo a morte da mÃ£e de Eren, que Ã© devorada diante de seus olhos. Ele entÃ£o decide se vingar e matar todos os TitÃ£s, entrando para DivisÃ£o de ExploraÃ§Ã£o.[5]\n\nCinco anos mais tarde, os trÃªs graduados cadetes foram enviados para o distrito de Trost, uma das cidades da fronteira que se localiza na Muralha Rose, quando o TitÃ£ Colossal reaparece e faz novamente uma brecha na muralha; na batalha que se seguiu, Eren foi devorado por um dos TitÃ£s na frente de Armin. Pouco tempo depois, um TitÃ£ aparece e ataca os outros TitÃ£s, em vez de seres humanos; esse acaba se revelando o prÃ³prio Eren, que de alguma forma adquiriu a capacidade de se transformar em TitÃ£. Embora seja considerado uma ameaÃ§a por alguns, ele ajuda os soldados a recuperar o distrito de Trost fechando a brecha da muralha. Depois de ser levado Ã  justiÃ§a, ele Ã© recrutado pela DivisÃ£o de ExploraÃ§Ã£o com a supervisÃ£o da DivisÃ£o de ExploraÃ§Ã£o de OperaÃ§Ãµes Especiais, liderada pelo capitÃ£o Levi.[6]\n\nEm uma expediÃ§Ã£o para Shinganshina em busca de respostas para o mistÃ©rio em torno de Eren, os soldados sÃ£o atacados por um TitÃ£ FÃªmea que tenta capturar Eren. Embora os soldados sÃ£o capazes de capturar rapidamente a TitÃ£ FÃªmea, ela se liberta e mata todos da equipe de Levi Ackerman, forÃ§ando o fim da expediÃ§Ã£o. Armin descobre que a TitÃ£ FÃªmea Ã© Annie Leonhardt, um dos cadetes que ensinaram Eren a lutar, e elabora um plano para capturÃ¡-la no distrito Stohess. A missÃ£o foi um sucesso, embora o alvo passa a proteger-se dentro de um cristal. Durante esta operaÃ§Ã£o, os danos colaterais revelam que os TitÃ£s residem dentro das paredes das muralhas",
        "launchDate": "2013-04-19",
        "title": "Shingeki no Kyojin",
        "episodes": "75",
        "publicRating": "-1.0"
    },
    {
        "studio": "Kyoto Animation",
        "image": "https://cdn.myanimelist.net/images/anime/1299/110774.jpg",
        "websiteRating": "10.0",
        "id": "6",
        "synopsis": "A histÃ³ria de Clannad gira em torno de Tomoya Okazaki, um estudante do terceiro ano do ensino mÃ©dio que nÃ£o gosta de sua vida. A mÃ£e de Tomoya (Atsuko) morreu quando Tomoya era jovem, deixando seu pai (Naoyuki) para criÃ¡-lo. ApÃ³s o acidente, o pai de Tomoya voltou-se para o Ã¡lcool e jogos de azar, e teve brigas frequentes com seu filho. Um dia, Naoyuki, enquanto discutia com seu filho, bateu Tomoya contra a janela, deslocando o ombro de Tomoya. Essa lesÃ£o impede Tomoya de jogar no time de basquete e faz com que ele se distancie dos outros. Desde entÃ£o, seu pai tratou Tomoya de maneira amÃ¡vel, mas distante, como se Tomoya e ele fossem estranhos, e nÃ£o uma famÃ\u00adlia. Isso machuca Tomoya mais do que seu relacionamento anterior com seu pai, e o constrangimento de voltar para casa leva Tomoya a ficar constantemente fora a noite toda. Assim comeÃ§a sua vida delinquente. O bom amigo de Tomoya, Youhei Sunohara, que foi expulso do clube de futebol por causa de uma disputa com seus veteranos, tambÃ©m Ã© um delinquente e costuma ficar em seu dormitÃ³rio com Tomoya sem fazer nada.\n\nA histÃ³ria comeÃ§a na segunda-feira, 14 de abril de 2003, no inÃ\u00adcio do ano letivo, [4] quando Tomoya conhece Nagisa Furukawa, uma garota de fala mansa que Ã© um ano mais velha do que ele, mas estÃ¡ repetindo o Ãºltimo ano no ensino mÃ©dio devido a estar doente grande parte do ano anterior. Seu objetivo Ã© entrar para o clube de teatro, o que ela nÃ£o pÃ´de fazer devido Ã  sua doenÃ§a, mas eles descobrem que o clube de teatro foi dissolvido depois que os poucos membros restantes se formaram. Como Tomoya tem muito tempo para matar, ele ajuda Nagisa a reformar o clube de teatro. Durante este perÃ\u00adodo, Tomoya conhece e sai com vÃ¡rias outras meninas que ele conhece bem e ajuda em seus problemas individuais.",
        "launchDate": "2013-03-07",
        "title": "Clannad",
        "episodes": "49",
        "publicRating": "-1.0"
    },
    {
        "studio": "ufotable",
        "image": "https://myanimelist.net/anime/49926/Kimetsu_no_Yaiba__Mugen_Ressha-hen/pics",
        "websiteRating": "8.0",
        "id": "7",
        "synopsis": "Ambientada no JapÃ£o durante o PerÃ\u00adodo TaishÅ\u008d (1912-1926), a histÃ³ria gira ao entorno de TanjirÅ\u008d Kamado, um garoto bondoso e inteligente que vive junto com sua mÃ£e e seus irmÃ£os, ganhando dinheiro vendendo carvÃ£o, assim como seu falecido pai. Certo dia, ao voltar para casa apÃ³s ter ido a uma cidade vender carvÃ£o, Tanjiro descobre que toda sua famÃ\u00adlia foi atacada por onis, sendo que uma de suas irmÃ£s, Nezuko, Ã© a Ãºnica que sobreviveu ao ataque. Nezuko entÃ£o passa a ser um oni, mas ela surpreendentemente ainda demonstra sinais de emoÃ§Ãµes e pensamentos humanos. TanjirÅ\u008d decide entÃ£o se tornar um caÃ§ador de onis, e com a ajuda de Nezuko, passa a sair em jornadas pelo JapÃ£o a fim de impedir que a mesma tragÃ©dia que afetou sua famÃ\u00adlia aconteÃ§a com outras pessoas, enquanto que ele busca uma maneira de tornar Nezuko humana novamente.",
        "launchDate": "2019-04-07",
        "title": "Demon Slayer: Kimetsu no Yaiba",
        "episodes": "26",
        "publicRating": "-1.0"
    },
    {
        "studio": "Toei Animation",
        "image": "https://upload.wikimedia.org/wikipedia/pt/4/44/DBZ_Logo.png",
        "websiteRating": "10.0",
        "id": "8",
        "synopsis": "O irmÃƒÂ£o de Son Goku (protagonista da sÃƒÂ©rie Dragon Ball) aparece na Terra e lhe informa que sua famÃƒÂ\u00adlia pertence a outro planeta.",
        "launchDate": "1989-04-26",
        "title": "Dragon Ball Z",
        "episodes": "291",
        "publicRating": "-1.0"
    },
    {
        "studio": "Xebec Zwei",
        "image": "https://upload.wikimedia.org/wikipedia/pt/6/69/Black_Clover%2C_volume_1.jpg",
        "websiteRating": "10.0",
        "id": "9",
        "synopsis": "Black Clover ÃƒÂ© uma sÃƒÂ©rie de mangÃƒÂ¡ adolescente do tipo shonen e fantasia japonÃƒÂªs, escrita e ilustrada por YÃ…Â«ki Tabata",
        "launchDate": "2017-05-02",
        "title": "Black Clover",
        "episodes": "170",
        "publicRating": "-1.0"
    },
    {
        "studio": "Studio Deen",
        "image": "https://upload.wikimedia.org/wikipedia/en/thumb/3/3e/Kono_Subarashii_Sekai_ni_Shukufuku_o%21_light_novel_volume_1_cover.jpg/220px-Kono_Subarashii_Sekai_ni_Shukufuku_o%21_light_novel_volume_1_cover.jpg",
        "websiteRating": "6.0",
        "id": "10",
        "synopsis": "ApÃ³s uma morte prematura e embaraÃ§osa, Kazuma SatÅ\u008d, um adolescente japonÃªs enclausurado NEET , conhece uma deusa chamada Aqua, que se oferece para reencarnar em um mundo paralelo com elementos MMORPG , onde ele pode partir em aventuras e lutar contra monstros. Apesar de ter sido oferecido um item com superpoderes ou habilidade para usar neste novo mundo, Kazuma, apÃ³s alguma provocaÃ§Ã£o, escolhe a prÃ³pria Aqua para acompanhÃ¡-lo atÃ© a cidade de Axel, rapidamente descobrindo que sua distraÃ§Ã£o nÃ£o Ã© benÃ©fica. LN 1.P Com Aqua incapaz de retornar Ã  vida apÃ³s a morte atÃ© o Rei DiaboÃ© derrotado, os dois formam um partido e recrutam outros dois membros; um mÃ¡gico obcecado por explosÃµes chamado Megumin e um cruzado masoquista chamado Darkness. LN 1.2.4 Devido Ã s habilidades disfuncionais do grupo, Kazuma rapidamente desiste da ideia de derrotar o Rei DemÃ´nio e tenta viver uma vida confortÃ¡vel, apenas para descobrir que as circunstÃ¢ncias de sua vida diÃ¡ria estÃ£o forÃ§ando ele e seu grupo a se encontrarem e batalharem os generais do Rei Diabo. LN 2.3",
        "launchDate": "2016-01-14",
        "title": "KonoSuba",
        "episodes": "20",
        "publicRating": "-1.0"
    },
    {
        "studio": "White Fox",
        "image": "https://upload.wikimedia.org/wikipedia/en/thumb/3/3c/Re-Zero_kara_Hajimeru_Isekai_Seikatsu_light_novel_volume_1_cover.jpg/220px-Re-Zero_kara_Hajimeru_Isekai_Seikatsu_light_novel_volume_1_cover.jpg",
        "websiteRating": "7.0",
        "id": "11",
        "synopsis": "Subaru Natsuki Ã© um NEET que de repente Ã© convocado para um mundo fantasioso. Logo depois de chegar, ele Ã© morto enquanto tentava ajudar uma jovem meio-elfa de sua amizade, Emilia, que Ã© um candidato a se tornar o prÃ³ximo governante do Reino de Lugunica, apenas para reviver algumas horas no passado. Depois de morrer algumas vezes, Subaru percebe que tem o poder de voltar atrÃ¡s no tempo apÃ³s sua morte. Depois de ajudar com sucesso Emilia, Subaru comeÃ§a a viver em uma das mansÃµes dos Roswaal Mathers como mordomo. Em gratidÃ£o e carinho por Emilia, Subaru faz uso de sua recÃ©m-descoberta capacidade de protegÃª-la e ajudar em sua ambiÃ§Ã£o de ser nomeada com sucesso como a prÃ³xima rainha, tambÃ©m prestando assistÃªncia a outros amigos que faz ao longo do caminho, enquanto sofre com a dor infligido a ele toda vez que ele morre, e carregando consigo as memÃ³rias de tudo o que aconteceu antes de seu poder ser ativado, o que Ã© esquecido por todos, exceto por ele.",
        "launchDate": "2016-04-04",
        "title": "Re:Zero âˆ’ Starting Life in Another World",
        "episodes": "50",
        "publicRating": "-1.0"
    },
    {
        "studio": "CloverWorks e Studio Trigger",
        "image": "https://upload.wikimedia.org/wikipedia/pt/e/eb/Darling_in_the_Franxx_Poster.jpg",
        "websiteRating": "7.0",
        "id": "12",
        "synopsis": "Darling in the Franxx, ÃƒÂ© um anime de ficÃƒÂ§ÃƒÂ£o cientÃƒÂ\u00adfica e romance japonÃƒÂªs",
        "launchDate": "2018-01-13",
        "title": "Darling in the Franxx",
        "episodes": "27",
        "publicRating": "-1.0"
    },
    {
        "studio": "Silver Link e Studio Palette",
        "image": "https://cdn.myanimelist.net/images/anime/1928/117620.jpg",
        "websiteRating": "8.5",
        "id": "13",
        "synopsis": "O protagonista Ã© um velho que viveu sua vida como o maior assassino do mundo. Devido Ã  sua idade avanÃ§ada, finalmente foi decidido que ele poderia se aposentar. No entanto, o aviÃ£o em que ele estava foi sabotado e mesmo suas habilidades como o maior assassino nÃ£o puderam salvÃ¡-lo. Quando ele morreu, ele foi acordado por uma deusa que queria reencarnÃ¡-lo em um mundo de espadas e magia porque ela precisa de seu conjunto de habilidades para evitar a destruiÃ§Ã£o daquele mundo nas mÃ£os do HerÃ³i. Aceitando o pedido, o protagonista desperta como Lugh Tuatha DÃ© e jura finalmente viver sua vida em sua plenitude como ser humano e nÃ£o como uma ferramenta.",
        "launchDate": "2021-10-06",
        "title": "Sekai Saikou no Ansatsusha, Isekai Kizoku ni Tensei suru",
        "episodes": "12",
        "publicRating": "-1.0"
    },
    {
        "studio": "Asread",
        "image": "https://upload.wikimedia.org/wikipedia/pt/1/1a/Mirainikkicover1.jpg",
        "websiteRating": "9.0",
        "id": "14",
        "synopsis": "Mirai Nikki ÃƒÂ© um mangÃƒÂ¡ shÃ…Â\u008dnen escrito e ilustrado por Sakae Esuno",
        "launchDate": "2011-10-10",
        "title": "Mirai Nikki",
        "episodes": "26",
        "publicRating": "-1.0"
    },
    {
        "studio": "Nippon Animation",
        "image": "https://upload.wikimedia.org/wikipedia/pt/6/63/Hunter_x_Hunter_Volume_1.JPG",
        "websiteRating": "10.0",
        "id": "15",
        "synopsis": "Hunter ÃƒÂ— Hunter ÃƒÂ© uma sÃƒÂ©rie de mangÃƒÂ¡ escrita e ilustrada por Yoshihiro Togashi.",
        "launchDate": "1999-10-16",
        "title": "Hunter x Hunter",
        "episodes": "148",
        "publicRating": "-1.0"
    },
    {
        "studio": "Sugar Boy e Blue Cat",
        "image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAX_euUOtlqRpjdbqbBMcDAdawVWlIwLe6rQ&usqp=CAU",
        "websiteRating": "12.0",
        "id": "16",
        "synopsis": "O otimista e afeminado Pico estÃ¡ trabalhando na cafeteria de seu avÃ´, CafÃ© Bebe, durante o verÃ£o. Tamotsu Ã© um trabalhador de colarinho branco em busca de uma fuga da mundanidade de sua vida cotidiana. Quando eles se encontram no cafÃ©, faÃ\u00adscas de amor e luxÃºria rapidamente aproximam os dois. As noÃ§Ãµes convencionais de idade, sexo e sexualidade sÃ£o desfeitas Ã  medida que o par busca a gratificaÃ§Ã£o carnal na companhia um do outro. Mas os prazeres da carne equivalem a uma conexÃ£o entre os coraÃ§Ãµes?",
        "launchDate": "2006-09-07",
        "title": "Boku no Pico",
        "episodes": "1",
        "publicRating": "-1.0"
    }
]
```


***Status Code:*** 200

<br>



### 6. Get Todos os IDs de Animes por Página


Retorna uma lista de IDs do Anime de uma página específica.
As páginas começam em 1 e cada página possui 5 animes


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{baseUrl}}/todos-animes/id/:page
```



***URL variables:***

| Key | Value | Description |
| --- | ------|-------------|
| page | 1 | página (começando em 1) |



***More example Requests/Responses:***


##### I. Example Request: Get Animes por Página



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| page | 1 |  |



##### I. Example Response: Get Animes por Página
```js
[1, 2, 3, 4, 5]
```


***Status Code:*** 200

<br>



## Avaliação dos Usuários
## Rotas para manipulação das Avaliações de Usuários
### GETs
- Get Avaliação de um Usuário
### POSTs
- Criar nova Avaliação



### 1. Criar nova Avaliação



***Endpoint:***

```bash
Method: POST
Type: 
URL: {{baseUrl}}/user-rating
```



***More example Requests/Responses:***


##### I. Example Request: Nova avaliação



***Body:***

```js        
{
    "idUser": "1",
    "idAnime": "10",
    "rating": "3"
}
```



##### I. Example Response: Nova avaliação
```js
Anime was inserted in Rating
```


***Status Code:*** 200

<br>



##### II. Example Request: Avaliação de um Anime já avaliado



***Body:***

```js        
{
    "idUser": "1",
    "idAnime": "1",
    "rating": "5"
}
```



##### II. Example Response: Avaliação de um Anime já avaliado
```js
Anime was updated in Rating (3 → 5)
```


***Status Code:*** 200

<br>



### 2. Get Avaliação de um Usuário


Verifica a Nota que um Usuário deu para um Anime.

Retorna um JSON contendo "rated" e "rating".

Caso o Usuário já tenha avaliado esse Anime:
"rated" = "true"
"rating" = {Rating do Usuário}

Caso o Usuário não tenha avaliado esse Anime:
"rated" = "false"
"rating" = "-1"


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{baseUrl}}/user-rating/:idAnime/:idUser
```



***URL variables:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime | 1 | ID do Anime |
| idUser | 1 | ID do Usuário |



***More example Requests/Responses:***


##### I. Example Request: Avaliação existente



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime | 1 | ID do Anime |
| idUser | 1 | ID do Usuário |



##### I. Example Response: Avaliação existente
```js
{"rated":"true","rating":"3"}
```


***Status Code:*** 200

<br>



##### II. Example Request: Avaliação não existente



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime | 1 | ID do Anime |
| idUser | 16 | ID do Usuário |



##### II. Example Response: Avaliação não existente
```js
{"rated":"false","rating":"-1"}
```


***Status Code:*** 200

<br>



## Comentários
## Rotas para manipulação de Comentários
### GETs
- Get Comentários por Anime
### POSTs
- Criar Comentário



### 1. Criar Comentário


Cria um novo Comentário


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{baseUrl}}/criar-comentario
```



***Body:***

```js        
    {
        "idUser": "1",
        "idAnime": "2",
        "text": "boku legal"
    }
```



***More example Requests/Responses:***


##### I. Example Request: Criar Comentário



***Body:***

```js        
    {
        "idUser": "1",
        "idAnime": "2",
        "text": "boku legal"
    }
```



##### I. Example Response: Criar Comentário
```js
Comment boku legal was created
```


***Status Code:*** 200

<br>



### 2. Get Comentários por Anime


Retorna todos os Comentários feitos no Anime especificado.


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{baseUrl}}/comentarios/:idAnime
```



***URL variables:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime |  | ID do Anime |



***More example Requests/Responses:***


##### I. Example Request: Get Comentários por Anime



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime |  |  |



##### I. Example Response: Get Comentários por Anime
```js
[
    {
        "idUser": "1",
        "deslikes": "0",
        "idAnime": "2",
        "id": "2",
        "text": "boku",
        "likes": "0",
        "username": "user1"
    },
    {
        "idUser": "1",
        "deslikes": "0",
        "idAnime": "2",
        "id": "3",
        "text": "hero",
        "likes": "0",
        "username": "user1"
    },
    {
        "idUser": "1",
        "deslikes": "0",
        "idAnime": "2",
        "id": "4",
        "text": "bem legal",
        "likes": "0",
        "username": "user1"
    }
]
```


***Status Code:*** 200

<br>



## Usuário
## Rotas para manipulação de Usuários
### POSTs
- Cadastrar Usuário
- Login de Usuário



### 1. Cadastrar Usuário


Cria um novo Usuário


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{baseUrl}}/cadastrar-usuario
```



***Body:***

```js        
{
    "login": "bruno-teste",
    "email": "bruno@bruno.com",
    "password": "55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251"
}
```



***More example Requests/Responses:***


##### I. Example Request: Cadastrar Usuário com sucesso



***Body:***

```js        
{
    "login": "bruno-teste",
    "email": "bruno@bruno.com",
    "password": "55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251"
}
```



##### I. Example Response: Cadastrar Usuário com sucesso
```js
User bruno-teste was created
```


***Status Code:*** 200

<br>



##### II. Example Request: Cadastrar Usuário com mesmo Username



***Body:***

```js        
{
    "login": "bruno-teste",
    "email": "bruno@bruno.com",
    "password": "55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251"
}
```



##### II. Example Response: Cadastrar Usuário com mesmo Username
```js
User bruno-teste already exists
```


***Status Code:*** 200

<br>



##### III. Example Request: Cadastrar Usuário com senha não hasheada



***Body:***

```js        
{
    "login": "bruno-teste",
    "email": "bruno@bruno.com",
    "password": "senha123"
}
```



##### III. Example Response: Cadastrar Usuário com senha não hasheada
```js
Password must be 64 characters long
```


***Status Code:*** 200

<br>



### 2. Login de Usuário


Realiza a tentativa de Login com uma combinação de username e senha.

Retorna "true" em sucesso e "false" em falha.


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{baseUrl}}/logar-usuario
```



***Body:***

```js        
{
    "login": "bruno-teste",
    "password": "55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251"
}
```



***More example Requests/Responses:***


##### I. Example Request: Login de Usuário com sucesso



***Body:***

```js        
{
    "login": "bruno-teste",
    "password": "55a5e9e78207b4df8699d60886fa070079463547b095d1a05bc719bb4e6cd251"
}
```



##### I. Example Response: Login de Usuário com sucesso
```js
true
```


***Status Code:*** 200

<br>



##### II. Example Request: Login de Usuário com senha errada



***Body:***

```js        
{
    "login": "bruno-teste",
    "password": "senha123"
}
```



##### II. Example Response: Login de Usuário com senha errada
```js
false
```


***Status Code:*** 200

<br>



## Watch List
## Rotas para manipulação de Watch List
### GETs
- Verificar se Anime está adicionado na Watch List
### POSTs
- Adicionar/Remover Anime de Watch List



### 1. Adicionar/Remover Anime da Watch List


Adiciona um Anime na Watch List de um Usuário


***Endpoint:***

```bash
Method: POST
Type: RAW
URL: {{baseUrl}}/anime-watchlist
```



***Body:***

```js        
{
    "idUser": "1",
    "idAnime": "16"
}
```



***More example Requests/Responses:***


##### I. Example Request: Adicionar Anime



***Body:***

```js        
{
    "idUser": "1",
    "idAnime": "16"
}
```



##### I. Example Response: Adicionar Anime
```js
Anime was inserted in Watchlist
```


***Status Code:*** 200

<br>



##### II. Example Request: Remover Anime



***Body:***

```js        
{
    "idUser": "1",
    "idAnime": "16"
}
```



##### II. Example Response: Remover Anime
```js
Anime was removed in Watchlist
```


***Status Code:*** 200

<br>



### 2. Verificar se Anime está adicionado na Watch List


Verifica se o Usuário adicionou esse Anime em sua Watch List.

Retorna "true" caso esteja adicionada e "false" caso não esteja adicionada.


***Endpoint:***

```bash
Method: GET
Type: 
URL: {{baseUrl}}/watchlist/:idAnime/:idUser
```



***URL variables:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime |  |  |
| idUser |  |  |



***More example Requests/Responses:***


##### I. Example Request: Anime está na Watch List do Usuário



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime |  |  |
| idUser |  |  |



##### I. Example Response: Anime está na Watch List do Usuário
```js
true
```


***Status Code:*** 200

<br>



##### II. Example Request: Anime não está na Watch List do usuário



***Query:***

| Key | Value | Description |
| --- | ------|-------------|
| idAnime |  |  |
| idUser |  |  |



##### II. Example Response: Anime não está na Watch List do usuário
```js
false
```


***Status Code:*** 200

<br>



---
[Back to top](#animehubapi)
> Made with &#9829; by [thedevsaddam](https://github.com/thedevsaddam) | Generated at: 2021-11-13 19:14:59 by [docgen](https://github.com/thedevsaddam/docgen)

