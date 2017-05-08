4 - O que é Deadlock? Detalhe um pouco sobre o caso e como você poderia resolver isso.

Resposta

Deadlock é uma situação de impasse onde recursos compartilhados são bloqueados por threads que necessitam do acesso aos mesmos para concluir sua execução, então as threads envolvidas não proseguem sua execução, pois estão aguardando que as cocncorrentes liberem os recursos, algo que nunca irá acontecer.

Supondo a existencia de duas threads (T1 e T2) que compartilham os recursos R1 e R2. Para que T1 possa liberar o recurso R1, ele precisa do recurso R2, que está sendo utilizado por T2. T2, por sua vez, precisa do recurso R1 para que possa liberar R2. Esta situação pode ser eexemplificada pelo seguinte trecho de código: 

class Exemplo {
	
	...

		Object R1 = new Object();
		Object R2 = new Object()

		//T1
		synchronized(R1) {

			Thread.sleep(10);

			synchronized(R2) {
				Thread.sleep(20);
			}
		}

		//T2
		synchronized(R2) {
			Thread.sleep(20);

			synchronized(R2) {
				Thread.sleep(10);
			}	
		}

	...

}

Uma forma de evitar a ocorrência de deadlocks é não utilizar blocos sychronized aninhados, ou seja, tentar acessar apenas um recurso por vez.	
