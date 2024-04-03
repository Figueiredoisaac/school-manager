# school-manager

---

**Descrição:**
O projeto School Manager é uma aplicação para gerenciamento de escolas, que inclui funcionalidades para gerenciar usuários, cursos, avaliações e outras operações relacionadas ao ambiente escolar.

**Tecnologias Utilizadas:**

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- ModelMapper
- Swagger

**Estrutura do Projeto:**

O projeto está estruturado em módulos que incluem:
- schoolmanager-controller: Contém os controladores REST para gerenciar usuários, cursos, avaliações, etc.
- schoolmanager-service: Contém a lógica de negócio e acesso ao banco de dados.
- schoolmanager-domain: Contém as entidades JPA que representam os objetos do sistema.
- schoolmanager-repository: Contém as interfaces dos repositórios JPA para acesso aos dados.

**Configuração do Banco de Dados:**
O projeto utiliza o MySQL como banco de dados. Certifique-se de configurar corretamente as credenciais de acesso ao banco de dados no arquivo application.properties ou application.yml.

**Execução do Projeto:**
  1. Clone o repositório do projeto em sua máquina local.
  2. Importe o projeto em sua IDE preferida.
  3. Configure o banco de dados conforme necessário.
  4. Execute a aplicação Spring Boot.

**NEW**
**Execução com Docker:**
  1. Certifique-se de ter o Docker instalado e em execução em sua máquina.
  2. No terminal, navegue até o diretório raiz do projeto.
  3. Construa a imagem Docker usando o comando: docker compose up -d --build .

**Documentação da API:**
A documentação da API pode ser acessada através do Swagger UI:
 Endpoint: /swagger-ui/index.html#/

**Observações:**
Certifique-se de atualizar o url_do_swagger com o baseURL correto para o Swagger após implantar a aplicação em um ambiente acessível.

**Contribuição:**
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests para melhorias, correções de bugs ou novos recursos.
