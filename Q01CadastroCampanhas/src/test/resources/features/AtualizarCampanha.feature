#language: pt

@AtualizarCampanhasTest
Funcionalidade: Atualizar uma campanha cadastrada
  O sistema deve prover um serviço para atualizar uma campanha cadastrada com base em seu ID, utilizando as mesmas
  regras do cadastro de campanhas

  Esquema do Cenario: Inserir campanhas a serem usadas como base nos cenários de teste
    Dado que é informado o nome <nome>, o Time <time> e a vigência de <dataInicial> a <dataFinal>
    Quando é invocado o serviço de cadastro de campanhas
    Entao é retornado código HTTP 200

    Exemplos:
      | nome                        | time | dataInicial   | dataFinal    |
      | "Manto Sagrado - A"         | 5    | "01/01/2017"  | "02/10/2017" |
      | "Time do Coração - A"       | 6    | "01/01/2017"  | "01/05/2017" |
      | "O melhor time é o meu - A" | 7    | "05/05/2017"  | "30/05/2017" |
      | "Meu time campeão - A"      | 8    | "01/06/2017"  | "30/06/2017" |


  Cenario: Consultar todas as campanhas vigentes, atualizar o nome par adicionar o texto '(Atualizada)' ao final
    e alterar o id do time para 10
    Dado que são consultadas todas as campanhas
    Quando adicionar ao final de todos os nomes o texto "(Atualizada)", alterar o time para 10 e invocar o serviço de atualizar
    Entao os dados informados são atualizados
    E a quantidade de resultados permanece a mesma


  Cenario: Atualizar uma campanha que não existe
    Dado que é montado um objeto com id inexistente
    Quando é invocado o serviço de atualizar campanhas
    Entao é lançada uma exceção do tipo RecursoNaoEncontradoException


  Cenario: Atualizar uma campanha sem informar o id
    Dado que é montado um objeto sem id
    Quando é invocado o serviço de atualizar campanhas
    Entao é lançada uma exceção IllegalArgumentException