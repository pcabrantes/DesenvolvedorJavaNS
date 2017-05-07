#language: pt

@RemoverCampanhaTest
Funcionalidade: Remover uma campanha cadastrada
  O sistema deve prover um serviço para remover uma campanha cadastrada com base em seu ID. Será feita uma
  exclusão lógica no banco de dados.


  Cenario: Incluir uma campanha a ser usada como base nos cenários de teste
    Dado que é incluída uma campanha de teste
    Quando invocar o serviço de cadastro de campanhas
    Entao a campanha é cadastrada


  Cenario: Excluir a campanha cadastrada anteriormente
    Dado que é informado o ID da campanha incluída anteriormente
    Quando o serviço de exclusão é invocado
    Entao o registro é excluído


  Cenario: Remover uma campanha que não existe
    Dado que é informado o ID 9999999
    Quando o serviço de exclusão é invocado
    Entao é lançada uma exceção indicando que o recurso não foi encontrado



