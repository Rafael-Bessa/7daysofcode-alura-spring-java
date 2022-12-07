
<h1 align="center">
  <p align="center">Desafio do 7 days of code da Alura - Usando o Spring Framework</p>
</h1>

<p align="center">
  <img src="https://user-images.githubusercontent.com/104053775/206071014-f6c1c36c-0cf6-47dd-8d99-0b7edc251e03.png">
</p>

## O desafio com o Spring se baseia em: 
- ``Consumir uma API Pública, no caso a API de filmes e séries da IMDB``
- ``Consumir o endpoint para Top250 melhores filmes rankeados do site``
- ``Receber o JSON do consumo e fazer a conversão para Objeto Java POJO``
- ``Após transformar o JSON em Objetos JAVA, manipular as consultas e devolver páginas HTML personalizadas da maneira que desejar``

### 1° Primeiro passo:
- Crie sua própria conta no site da iMdB, é gratuito! Você irá receber sua própria <API.KEY>.
- Em sequência podemos começar o Projeto, vamos consumir o endpoint para os TOP250 Filmes, você vai precisar da sua <API.KEY>.
- O endereço para consumo é https://imdb-api.com/en/API/Top250Movies/ + <API.KEY>
- Utilizei a Classe **RestTemplate** para fazer a chamada API, também é necessário anotar um @Bean para o RestTemplate, assim o Spring vai saber injetá-lo no projeto.
- Eu escondi o valor da minha <API.KEY> por motivos de segurança, mas você pode colocar a sua chave sem problemas no endereço.

![bean](https://user-images.githubusercontent.com/104053775/206077520-85dac90e-c95b-4032-9418-2d4eeb1432a3.png)

![resttemplate](https://user-images.githubusercontent.com/104053775/206077524-47657fc8-e04f-4b60-ae71-2cc158648683.png)

### 2° Segundo passo:
- Vá até o navegador e digite, localhost:8080/top250
- O retorno dessa requisição GET é um JSON gigante, contendo os 250 Filmes mais rankeados pelo site.
- Com tudo funcionando, agora vamos transformar o JSON em Objetos Java.
- Primeiro temos que criar uma Classe que represente esses Objetos, no caso, a Classe Movie (Filme).
- No JSON existem vários atributos no objeto, mas no caso eu decidi que não quero todos, apenas alguns deles (Titulo, ano, imagem, rating)
- A anotação @JsonIgnoreProperties(ignoreUnknown = true) é muito importante, para dizer ao Spring que não vamos querer todos os atributos do Json em nosso Objeto Java.

![pojo](https://user-images.githubusercontent.com/104053775/206078800-b72438e8-1477-497e-974b-90358af7f2b0.png)

- O Spring por padrão já possui a biblioteca do **Jackson**, que consegue converter os objetos JSON em JAVA e vice-versa.
- Criei um método separamente para fazer essa transformação

![jackson](https://user-images.githubusercontent.com/104053775/206079533-40d07285-6fc7-4cb8-8808-15cbb6534302.png)

- Em azul, eu tive que cortar um pedaço da String do JSON, para que ela fosse compatível com o Jackson, a String tem que começar com "[" e terminar com "]", simbolizando uma lista.

- Em verde a real utilização do Jackson, fazendo o parse para Objetos Java.
