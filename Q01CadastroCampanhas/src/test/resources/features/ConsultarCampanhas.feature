#language: pt

  @ConsultarCampanhasTest
  Funcionalidade: Testar a consulta de campanhas
    O sistema deve prover um serviço para consultar as campanhas cadastradas, retornando apenas as que estão em vigência

  Esquema do Cenario: Inserir campanhas a serem usadas como base nos cenários de teste
    Dado que é informado o nome <nome>, o Time <time> e a vigência de <dataInicial> a <dataFinal>
    Quando é invocado o serviço de cadastro de campanhas
    Entao é retornado código HTTP 200

    Exemplos:
      | nome                    | time | dataInicial   | dataFinal    |
      | "Manto Sagrado"         | 1    | "01/01/2017"  | "02/10/2017" |
      | "Time do Coração"       | 2    | "01/01/2017"  | "01/05/2017" |
      | "O melhor time é o meu" | 3    | "05/05/2017"  | "30/05/2017" |
      | "Meu time campeão"      | 4    | "01/06/2017"  | "30/06/2017" |


  Cenario: Consultar campanhas vigentes
    Dado que o serviço de consulta é invocado
    Entao a consulta é executada com sucesso
    E a lista de retorno apresenta apenas campanhas vigentes