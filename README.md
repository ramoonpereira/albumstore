# Albumstore
Web Api Albuns Store -  Venda de Albuns

> Albumstore é uma API para gerenciar discos e vendas com cashback

![image](https://http2.mlstatic.com/kit-disco-de-vinil-pequeno-06-unidades-D_NQ_NP_951092-MLB25672011419_062017-F.jpg)

## Requisitos
```sh
Java 11
Docker Compose
Plugin Lombok
Maven
MySql (Podendo utilizar o docker compose)
```

## Instalação:


**Java 11 - SDKMAN:**

```sh
https://sdkman.io/install
```

OU

**Java 11 - Java SE Development Kit 11**

```sh
https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
```

## Banco de Dados

**Mysql (Opcional ou usar docker compose):**
```sh
https://www.mysql.com/downloads/
```
OU

**Executar docker (mysql):**

```sh
docker-compose up (para acompanhar logs)
docker-compose up -d (somente executar)
```


## IDEs:
```sh
Intellij: https://www.jetbrains.com/idea/download
Eclipse : https://www.eclipse.org/downloads/
```

## Lombok plugin:

```sh
Intellij: https://projectlombok.org/setup/intellij
Eclipse : https://projectlombok.org/setup/eclipse
```

## Obtendo Client ID e SECRET Spotify:

```sh
https://developer.spotify.com/dashboard
```

## Ambiente
Configure as variáveis de ambiente de acordo com o ambiente `src/main/application.yml`:

| Nome | Descrição | Valor Padrão | Obrigatório |
| -- | -- | -- | -- |
| ALBUMSTORE_ENV | Ambiente de execução do projeto| development| Caso default não atenda |
| ALBUMSTORE_DATASOURCE_URL | URL JDBC para conectar ao banco de dados | jdbc:mysql://127.0.0.1:3306/albumstore?createDatabaseIfNotExist=true&useSSL=false&useTimezone=true&serverTimezone=UTC  | Caso default não atenda |
| ALBUMSTORE_DATASOURCE_USERNAME | Usuário para conectar ao banco de dados | albumstore | Caso default não atenda |
| ALBUMSTORE_DATASOURCE_PASSWORD | Senha para conectar ao banco de dados | !@12QWqw | Caso default não atenda |
| ALBUMSTORE_SECURITY_BASIC_NAME | Usuário para autenticar em rotas BasicAuth | albumstore | Caso default não atenda |
| ALBUMSTORE_SECURITY_BASIC_PASSWORD | senha para autenticar em rotas BasicAuth | albumstore  | Caso default não atenda |
| ALBUMSTORE_SECURITY_JWT_SECRET_KEY | Secret Key para geração do JWT Token | stubJWT | Caso default não atenda |
| ALBUMSTORE_SPOTIFY_CLIENT_ID | Client ID para consumo de APIs do Spotify | | :white_check_mark: |
| ALBUMSTORE_SPOTIFY_CLIENT_SECRET | Client SECRET para consumo de APIs do Spotify | | :white_check_mark: |


Mantenha o arquivo *src/main/resources/application.yml* , *src/test/resources/application-test.yml* e a tabela de variáveis sempre atualizada.


## Configuração para Execução do projeto

**Acessar a pasta raiz do projeto:**

**Compilar o projeto:**

```sh
mvn clean install
```

**Executar o projeto:**

```sh
java -jar target/albumstore-x.x.x.jar
```

## Autenticação

**Obtendo um JWT Token para autenticar na API:**

```sh
https://jwt.io/
Gerar conforme JWT_SECRET_KEY
```

## WEB API

**Recursos disponiveis:**

| Rota | Versão |Descrição | HTTP Method | Autenticação |
| -- | -- | -- | -- | -- |
| /swagger-ui.html | v1 |Interface para documentação da API| GET | |
| /albumstore/v1/disccatalog/supplyDiskCatalog | v1 | Método para consumir o Spotify e carregar o catalogo de discos | GET |  [:white_check_mark:] [OAuth2] |
| /albumstore/v1/disccatalog/{id} | v1 | Método para buscar disco pelo seu identificador| GET |  [:white_check_mark:] [OAuth2] |
| /albumstore/v1/disccatalog/genre| v1 | Método para buscar discos filtrando por genero| GET |  [:white_check_mark:] [OAuth2] |
| /albumstore/v1/sales/{id}| v1 | Método para buscar venda pelo seu identificador| GET |  [:white_check_mark:] [OAuth2] |
| /albumstore/v1/sales| v1 | Método para buscar vendas filtrando por data da venda| GET |  [:white_check_mark:] [OAuth2] |
| /albumstore/v1/sales| v1 | Método para criar uma nova venda| POST |  [:white_check_mark:] [OAuth2] |



