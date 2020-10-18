# Lanchonete (Back-end)

Back-end de uma aplicação para lanchonetes feito para estudos. Foi utizada a arquitetura REST API.

## Para rodar o projeto na máquina, é necessário ter instalado:
- Java 8 para rodar o projeto back-end;
- Banco de dados MySQL 8.0.21;
- Maven 3.6.3 para gerenciamento da aplicação;
- GIT 2.28.0 para clonar os repositórios;
- Node.js 12.19.0 para rodar o javascript server-side;
- Yarn 1.22.10 para gerenciar os pacotes do Node.

## Para executar o projeto:
- Clone esse repositório e o repositório do front-end:
```
git clone https://github.com/juanbrisola/lanchonete-back-end.git
git clone https://github.com/juanbrisola/lanchonete-front-end.git
```
- Em um database do MySQL chamado hamburgueria com Timezone UTC, usuário root e senha root, importe o Dump.sql localizado no repositório do back-end:
```
spring-boot-rest-api/src/main/resources/Dump.sql
```
- Em um terminal, acesse a pasta raiz do projeto back-end, onde fica localizado o pom.xml;
- Rode o comando:
```
mvn clean package
```
- Após concluir o build, acesse a pasta target do projeto back-end e rode o comando:
```
java -jar spring-boot-rest-api-0.0.1-SNAPSHOT.jar
```
- Em seguida, vá para o projeto front-end e acesse a pasta raiz do projeto. Rode o comando:
```
yarn start
```
- Acesse: http://localhost:3000/login
- Você irá se deparar com a tela de login;
- A aplicação ainda não possui cadastro de novos usuários, por conta disso, deixei pré-configurado um usuário "juan" com senha "admin123";
- Para visualizar o swagger, acesse: http://localhost:8080/swagger-ui.html

## Tecnologias que foram utilizadas no back-end:
- Java 8;
- Maven;
- Spring Boot 2.1.3.RELEASE;
- Spring Data JPA;
- Spring Security e Token JWT;
- Swagger;
- Banco de Dados MySQL;

## Tecnologias que foram utilizadas no front-end:
- Node.js;
- Yarn;
- React.js;
- Axios;
- Material UI (biblioteca do react);

## Como funciona a aplicação:
A aplicação representa uma situação comum encontrada em todas as lanchonetes: montagem do pedido e cálculo do valor. Após realizar o login, o usuário é redirecionado para a tela Home da aplicação. Nela, é possível verificar as promoções que a lanchonete oferece. Ao clicar no botão "Criar Pedido", o usuário é redirecionado para a tela de pedidos. Nessa tela, é possível selecionar o lanche e seus adicionais. É possível adicionar mais de um lanche. Ao clicar em "Finalizar Pedido", o usuário visualiza o valor do pedido e as promoções aplicadas. Também é possível consultar e alterar os ingredientes através da barra superior. O Botão "Sair" finaliza a sessão do usuário e redireciona para a tela de login.

## O que eu gostaria de ter implementado na aplicação:
- HATEOAS: acrônimo para Hypermedia as the Engine of Application State, o HATEOAS serve como um guia para os usuários que estão consumindo o serviço REST. Um HATEOAS bem implementado ajuda o usuário a "se localizar" enquanto consome o serviço. Essa tecnologia demandaria um pouco mais de estudo e tempo, e por isso acabou não sendo implementada no momento.
- Testes Unitários: os testes auxiliam o desenvolvedor a garantir o bom funcionamento da aplicação, bem como a integridade dos dados que estão sendo gerados a partir dela.
- API Session: No front-end, eu estou armazenando o Token JWT no LocalStorage do navegador. Nas minhas pesquisas, encontrei que isso não é uma boa prática, pois compromete a segurança da aplicação, uma vez que é possível acessar o localStorage à partir do navegador. Implementar API Session demandaria mais tempo, mas resolveria esse problema.

As três implementações citadas acima serão trabalhadas nas próximas semanas, e esse projeto será atualizado.

Observação: Durante o desenvolvimento do projeto, me deparei com alguns problemas relacionado ao CORS da aplicação, e a solução que eu implementei acabou atrapalhando no funcionamento do Token. Isso também será resolvido nas próximas semanas.

Dúvidas, críticas ou sugestões: juan.brisola@outlook.com
