# Repositório da disciplina DM110
## Desenvolvimento Java EE

* [dm110_poller](https://github.com/furvao/dm110_poller/tree/master/dm110_poller) - Implementação de projeto de scanner de equipamentos, 
realizando a varredura na rede a partir do IP e máscara de subrede, salvando-os em banco de dados relacional Postgres, configurado como datasource EquipamentDS no JBoss WildFly 10.1, a partir de fila MQueue dm110_poller_queue.

** [Script do banco de dados] (https://github.com/furvao/dm110_poller/tree/master/dm110_poller/scripts)
** Nomes JNDI dos DataSources: EquipamentDS (java:/EquipamentDS)
** Nomes JNDI das filas ou tópicos JMS: java:/jms/queue/dm110_poller_queue

## Professor
***Prof. Márcio Emílio Cruz Vono de Azevedo***

### Instituto Nacional de Telecomunicações [www.inatel.br](http://www.inatel.br)
