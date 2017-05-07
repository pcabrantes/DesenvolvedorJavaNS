#language: pt

  @CadastrarCampanhaTest
  Funcionalidade: Testar o cadastro de campanhas de marketing
    O sistema deve prover um serviço para cadastro de campanhas de marketing seguindo a seguinte regra:
    No cadastramento de uma nova campanha, deve-se verificar se já existe uma campanha ativa para aquele período (vigência),
    caso exista uma campanha ou N campanhas associadas naquele período, o sistema deverá somar um dia no término da
    vigência de cada campanha já existente. Caso a data final da vigência seja igual a outra campanha, deverá ser
    acrescido um dia a mais de forma que as campanhas não tenham a mesma data de término de vigência.


  Cenario: Cadastra uma conta inicial para ser usada como base nos cenários de teste
    Dado que é informado o nome "Manto Sagrado 2019", o Time 1 e a vigência de "01/10/2019" a "02/10/2019"
    Quando é invocado o serviço de cadastro de campanhas
    Entao é retornado código HTTP 200


  Cenario: Cadastrar uma campanha no mesmo período da campanha vigente
    Dado que é informado o nome "Time do Coração 2019", o Time 2 e a vigência de "01/10/2019" a "02/10/2019"
    Quando é invocado o serviço de cadastro de campanhas
    Entao é retornado código HTTP 200
    E a data final da campanha já cadastrada é alterada para "03/10/2019"
    E é cadastrada a nova campanha no período informado


  Cenario: Cadastrar uma campanha dentro da vigência de duas anteriores já cadastradas
    Dado que é informado o nome "Time do Coração 2019", o Time 3 e a vigência de "01/10/2019" a "03/10/2019"
    Quando é invocado o serviço de cadastro de campanhas
    Entao é retornado código HTTP 200
    E a data final da primeira campanha passa a ser "04/10/2019"
    E a data final da segunda campanha passa a ser "03/10/2019"
    E a data final da nova campanha passa a ser "05/10/2019"


  Cenario: Cadastrar uma campanha nula
    Dado que não são informados dados na requisição
    Quando é invocado o serviço de cadastro de campanhas
    Entao é lançada uma exceção do tipo IllegalArgumentException


  Cenario: Cadastrar uma campanha com data final menor que a data inicial
    Dado que é informado o nome "Time do Coração 2019", o Time 3 e a vigência de "07/10/2019" a "03/10/2019"
    Quando é invocado o serviço de cadastro de campanhas
    Entao é lançada uma exceção do tipo IllegalArgumentException