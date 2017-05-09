# DesenvolvedorJavaNS

As questões 1 e 2 foram implementadas utilizando a metodologia BDD (Behaviour Driven Development), utilizando o framework Cucumber para a execução e documentação dos testes.

- Para build e gestão de dependências foi usado Maven
- Foi usado Spring Boot para simplificação das configurações do projeto, usando um tomcat embutido, sendo definidas as seguintes portas de conexão:
  - Cadastro de Campanhas: 8888
  - Cadastro de Sócio Torcedor: 8889
- Banco de dados H2
- As aplicações foram divididas em 3 camadas:
  - Controller: exposição dos serviços REST
  - Service: comunicação com o controller e aplicação de regras de negócio
  - Repository: comunicação com banco de dados
