#language: pt

  @CadastrarTorcedorTest
  Funcionalidade: Cadastro de Sócio Torcedor
    O sistema deve prover um serviço para cadastro de sócio torcedor informando como entrada:
      - Nome Completo;
      - E-mail;
      - Data de Nascimento;
      - Meu Time do Coração;
    O Cliente não pode ter mais de um cadastro ativo, validado pelo e-mail. Dado um E-mail que já existe:
      - informar que o cadastro já foi efetuado
      - enviar as novas campanhas como resposta (regra não coberta por estes testes)

  Cenario: Cadastro de Sócio Torcedor
    Dado que foi informado um cliente com nome "Socio Torcedor", email "email@cliente1", nascimento "20/06/1986" e time 1
    Quando invocar o serviço de cadastro de sócio torcedor
    Entao efetuar o cadastro com os dados informados


  Cenario: Cadastro de torcedor já existente
    Dado que foi informado um cliente com nome "Socio Torcedor", email "email@cliente1", nascimento "20/06/1986" e time 1
    Quando invocar o serviço de cadastro de sócio torcedor
    Entao informar que o cadastro já foi efetuado


  Cenario: Cadastro de torcedor sem informar todos os dados
    Dado que foram informados dados incompletos do torcedor
    Quando invocar o serviço de cadastro de sócio torcedor
    Entao lançar uma excecao do tipo IllegalArgumentException


  Cenario: Cadastro de torcedor com data de nascimento inválida
    Dado que o torcedor informou uma data de nascimento inválida
    Quando invocar o serviço de cadastro de sócio torcedor
    Entao lancar uma excecao do tipo ParseException