5 - Uma das grandes inclusões no Java 8 foi a API Stream. Com ela podemos fazer diversas operações de loop, filtros, maps, etc. Porém, existe uma variação bem interessante do Stream que é ParallelStreams. Descreva com suas palavras quando qual é a diferença entre os dois e quando devemos utilizar cada um deles. 

Resposta:

Ao utilizar stream(), o processamento é sequencial, enquanto com o parallelStream() o processamento é dividido em vários blocos (normalmente a quantidade de processadores) que são executados em paralelo e ao final o resultado de cada execução é agrupado.

O parallelStream deve ser evitado quando os passos do processamento são interdependentes, pois neste caso a ordem de execução pode alterar o resultado final.