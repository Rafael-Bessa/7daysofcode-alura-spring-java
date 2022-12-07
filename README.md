
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

![json](https://user-images.githubusercontent.com/104053775/206095034-88846dce-5581-41b1-bc72-4532851024ce.png)

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

### 3° Terceiro passo:
- Depois de conseguir uma lista de Objetos do tipo Movie, parseados do JSON consumido, eu decidi salvar no banco de dados apenas os filmes que tem seu **ano** maior ou igual à 2020, filmes mais atuais.
- Tive que mudar a classe Movie para se tornar uma **Entidade da JPA** e realizar as configurações do banco no application.properties, além de adicionar novas dependências ao projeto
- Banco de dados escolhido foi o H2, em memória para uma maior praticidade.
- Também tive que criar um Repository para Movie, com a ajuda do Spring-Data-JPA os métodos de CRUD e mais alguns já estão prontos.

![movie](https://user-images.githubusercontent.com/104053775/206081264-b3f2059c-3bf0-4b5c-a68a-4a197039bf96.png)
- Toda vez que a aplicação é reiniciada, o banco H2 também é reiniciado.

![h2](https://user-images.githubusercontent.com/104053775/206081504-b8faaea8-d2bd-47fd-92fd-2358b1a9c0ae.png)

![repo](https://user-images.githubusercontent.com/104053775/206082532-030921f6-6dc5-41ab-b9c1-a08f48f371c3.png)

- Criei uma novo Controller para fazer essa personalização das páginas, e salvar apenas alguns Movies no banco.
- Após feito isso a lista completa do banco de dados é enviada até a página HTML alvo, e com ajuda do engine do **Thymeleaf** é possível fazer a interação com objetos Java.

![controller](https://user-images.githubusercontent.com/104053775/206082542-53fe2c30-f6c9-4a02-ac0b-e1fef3d67581.png)

### 4° Quarto passo:
- Depois enviar a lista para a View, basta ajustar a página com o HTML E CSS que desejar.
- Utilizei os cards do bootstrap para fazer um melhor design para a exibição dos filmes.
- A template engine Thymeleaf é essencial nesta parte, ela é a responsável em colocar objetos Java no HTML.
- Não esquecer de ter a dependência do Thymeleaf no pom.xml

![html](https://user-images.githubusercontent.com/104053775/206094088-ff84272b-1467-4151-a54b-57e516ce084a.png)

### 5° Quinto passo:
- Após tudo isso, basta inicializar o servidor, ele vai abrir na porta 8080 (Tomcat)
- Eu configurei um endpoint com o método get para **/personalizado** , onde irá renderizar alguns filmes selecionados e estilizados.

![final1](https://user-images.githubusercontent.com/104053775/206094778-04d9877c-0e6f-4f25-9ead-26a6924cfa32.png)
<br>
![final2](https://user-images.githubusercontent.com/104053775/206094819-a3006157-3628-480f-bb17-28eaa26b8045.png)



