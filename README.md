<h1 align="center"> Beyond Stars </h1> 
<img src="https://gist.githubusercontent.com/Uxtraordinario/151d7da6ac918754925a11e5654662f3/raw/a50fa4a72d8a1ebe55284afcdd0d5f5d45948e1f/Starwars.svg">

 <p align="center"> 
    <img 
      alt="Made by uxtraordinario" 
      src="https://img.shields.io/badge/made%20by-uxtraordinario-%20?color=f6bd20"
    >
    <img 
      alt="Project top programing language" 
      src="https://img.shields.io/github/languages/top/uxtraordinario/BeyondStars?color=f6bd20"
    >
    <img 
      alt="Repository size" 
      src="https://img.shields.io/github/repo-size/uxtraordinario/BeyondStars?color=f6bd20"
    >
    <img 
      alt="GitHub license" 
      src="https://img.shields.io/github/license/uxtraordinario/BeyondStars?color=f6bd205"
    >
  </p>

## Descrição:
> ℹ️ **NOTE:** Um dos principais projetos em meu portfólio

Este projeto foi desenvolvido como paixão ao universo Geek Star Wars, possibilitando a busca pelos personagens, planetas, espécies e naves deste universo.

## Estrutura do Projeto

- **MainApplication:** A classe principal que contém a lógica da aplicação.
- **StarWarsCharacter:** Modelo para personagens de Star Wars.
- **StarWarsPlanets:** Modelo para planetas de Star Wars.
- **StarWarsSpecies:** Modelo para espécies de Star Wars.
- **StarWarsStarship:** Modelo para naves de Star Wars.
- **StarWarsCharacterRepository:** Repositório para manipulação de dados dos personagens.

## Tecnologias utilizadas:

|  Tecnologias |    Utilização     |
| :----: | -------------------------------------------------------------------------------------- |
|  Java  | Principal linguagem utilizada no projeto, responsável pela estruturação |       
|  Maven  | Ferramenta de gerenciamento de dependências e automação de build |
|  Gson  | Dependência utilizada para a serialização e desserialização de objetos JSON |
|  SWAPI  | API utilizada para obter dados sobre personagens, planetas, espécies e naves de Star Wars |
|  Java HttpClient | Utilizado para fazer requisições HTTP à API SWAPI |
|  Spring Boot  | Framework utilizado para criar a aplicação Java e injeção de dependências (Autowired) |
|  Spring Data JPA  | Utilizado para manipulação de dados e persistência dos personagens |


## Como testar?
para testar este projeto em sua máquinas siga os seguintes passos:

### 1 - Em seu console no Git
 ``` sh
    git clone https://github.com/SEU_USUARIO/BEYOND_STARS.git
 ```

### 2 - Navegue até o diretório do projeto
 ``` sh
    cd BEYOND_STARS
 ```

### 3 - Instale as dependências
 ``` sh
   mvn install
 ```

## Como Executar
### 1 -Compile e execute o projeto usando o Maven:
 ``` sh
  mvn compile exec:java -Dexec.mainClass="com.starwars.BeyondStars.MainApplication"
 ```
### 2- O programa será iniciado e você verá o seguinte prompt:
 ``` sh
  Enter search type (character/planet/species/starship/exit):

 ```


