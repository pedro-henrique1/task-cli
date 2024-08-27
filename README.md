# Task Track CLI

O **Task Tracker CLI** é uma ferramenta de linha de comando (CLI) simples e eficaz, projetada para facilitar o
acompanhamento e a gestão de tarefas. Com este aplicativo, você pode adicionar, atualizar, excluir e listar tarefas,
além de marcá-las como "em andamento" ou "concluídas". Todas as tarefas são salvas em um arquivo JSON no diretório
atual, garantindo fácil acesso e organização.

# Feature

- **Adicionar tarefas**: Crie tarefas com descrição
- **Atualizar descrição**: Modifique a descrição da tarefa existente
- **Deletar tarefa**: Remover a tarefa desejada
- **Marcar tarefa com em andamento**: Defina o status de uma tarefa como "em andamento".
- **Marcar tarefa com em concluída**: Defina o status de uma tarefa como "concluida".
- **Listar todas as tarefas**: Lista todas as tarefas
- **Lista todas as tarefas**: Lista todas as tarefas com filtro por status (feito, em ancamento e todo).

# Requisitos

- É necessario ter o  [java](https://www.java.com/download/ie_manual.jsp) instalado em sua maquina para rodar o
  programa e tambem o [maven](https://maven.apache.org/install.html)

# Instalação

- Clone o repositório

```git
  git clone https://github.com/pedro-henrique1/task-cli.git
  cd task-cli
```

- Instalar Dependência:

```
  mvn build
```

- Executar o programa:

```
  mvn exec:java -Dexec.mainClass="com.task.Main"
```

# Uso

Os comandos para o uso do programa.

## Comandos

- **Adicionar tarefa.**
- **Atualizar tarefa.**
- **Excluir tarefa.**
- **Marque tarefa como em andamento.**
- **Marque tarefa como em concluída.**
- **Liste todas as tarefas.**
- **Liste todas as tarefas com status em concluído.**
- **Liste todas as tarefas com status em todo.**
- **Liste todas as tarefas com status em andamento.**

# Exemplos:

```

# Adicionar nova task
task-cli add "Buy groceries"

# Atualizar descrição da task
task-cli update 1 "Buy groceries and cook dinner"

# Excluir task
task-cli delete 1

# Marcar task com 'em progresso' 
task-cli mark-in-progress 1

# Marcar task com 'feito' 
task-cli mark-done 1

# Listar todas as task
task-cli list

# Listar todas as task com status 'feito'
task-cli list done

# Listar todas as task com status 'todo'
task-cli list todo

# Listar todas as task com status 'em progresso'
task-cli list in-progress


```






