# 🏛️ Sistema de Gestão de Museu - POO

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge)

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos (PEX0130)** da **UFERSA (Campus Pau dos Ferros)**. O sistema gerencia o acervo de um museu, exposições, visitantes e processos de restauração, aplicando conceitos fundamentais de POO e persistência de dados.

## 📋 Sobre o Projeto

O sistema simula o funcionamento de um museu real, permitindo dois tipos de acesso: **Administrador** e **Visitante**. O foco principal é a integridade dos dados e o cumprimento de regras de negócio estritas, como conflito de horários e exclusividade de obras.

O "Banco de Dados" utiliza **Serialização de Objetos em Java**, salvando todo o estado do sistema em um arquivo binário (`museu_dados.bin`), garantindo que os dados persistam entre as execuções.

## 🚀 Funcionalidades

### 👤 Administrador
- **Gestão de Acervo:** Criar, editar, listar e excluir obras de arte.
- **Gestão de Exposições:** Criar exposições com data de início/fim e capacidade máxima.
- **Curadoria:** Adicionar obras em exposições (com validação de disponibilidade).
- **Restauração:** Iniciar e concluir restaurações (remove automaticamente a obra de exposições ativas).
- **Gestão de Usuários:** Listar visitantes e excluir contas indesejadas.

### 👥 Visitante
- **Cadastro e Login:** Sistema de autenticação com validação de login único.
- **Exploração:** Visualizar exposições disponíveis e obras expostas.
- **Inscrição:** Inscrever-se em exposições (com verificação automática de choque de datas).
- **Perfil:** Editar dados pessoais ou excluir a própria conta.

## 🧠 Regras de Negócio Implementadas

O diferencial deste projeto são as validações lógicas robustas:

1.  **Conflito de Datas:** Um visitante não pode se inscrever em duas exposições que ocorrem no mesmo intervalo de datas.
2.  **Exclusividade de Status:** Uma obra não pode ser excluída se estiver vinculada a uma exposição ou restauração ativa.
3.  **Integridade de Restauração:** Ao iniciar uma restauração, a obra é **automaticamente removida** de qualquer exposição ativa e seu status muda para "Em Restauração".
4.  **Capacidade:** Exposições respeitam o limite máximo de visitantes.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (JDK 17+)
- **Paradigma:** Orientação a Objetos (Herança, Polimorfismo, Encapsulamento).
- **Persistência:** `java.io.Serializable` (Arquivos Binários).
- **Interface:** Terminal / Console (CLI).

## 📦 Como Rodar o Projeto

### Pré-requisitos
- Ter o [Java JDK](https://www.oracle.com/java/technologies/downloads/) instalado.

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/SEU-USUARIO/NOME-DO-REPO.git](https://github.com/SEU-USUARIO/NOME-DO-REPO.git)
   cd NOME-DO-REPO