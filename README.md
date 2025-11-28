# Gestão de Obras de Museu - Projeto (Entrega)
Projeto Java simples (linha de comando) para gerenciar obras, exposições e restaurações em um museu.
Baseado no documento do usuário enviado.

## Estrutura
- `src/museum/` - código-fonte Java
- `data/` - arquivos de persistência gerados pela execução
- `museum-project.zip` - pacote deste projeto

## Requisitos
- JDK 11+ (compilar com `javac`)
- Rodar com `java` ou importar no IDE (Eclipse/IntelliJ)

## Como compilar e executar
```bash
cd src
javac museum/*.java
java museum.Main
```

Os dados são salvos automaticamente em `data/` (serialização Java).

## Funcionalidades implementadas (mínimo)
- Cadastro e login de Visitante e Administrador.
- Administrador: criar obras, exposições, iniciar restaurações, excluir visitante, excluir obra (com checagens).
- Visitante: visualizar obras/exposições, inscrever-se em exposições (sem conflito de datas), excluir própria conta.
- Persistência via arquivos (serialização).

## Observações
- Projeto entregue como ponto de partida; adapte nomes e lógica conforme modelagem requerida.
