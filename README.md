# Relatório - Ajudaê

## Programação de Sistemas II - 03J

* **Alex Cruz de Santana  RA: 10420357**
* **Graziely de Oliveira Severo RA: 10425431**
* **Jean Pazzini Domingues RA: 10420319**

***

## Visão Geral

O Portal de Doações Ajudaê foi projetado para ser uma plataforma simples e intuitiva, permitindo a visualização e o gerenciamento de campanhas de arrecadação de fundos para diferentes causas. A aplicação utiliza Spring Boot no backend e JavaScript com React no frontend, proporcionando uma estrutura modular, escalável e fácil de manter. Este relatório detalha as principais decisões de design e arquitetura que guiaram o desenvolvimento da aplicação.

## Escolhas de Arquitetura

1. **Arquitetura em Camadas (MVC - Model, View, Controller)**
   
   O design do backend foi estruturado usando o padrão MVC, que separa as responsabilidades da aplicação em Model, View e Controller. Isso permite que cada camada funcione de forma independente e facilita a manutenção e a evolução da aplicação:
   - Model: O modelo representa as entidades principais, é gerenciado por meio do Jakarta Persistence para persistência de dados no banco de dados Supabase com PostgreSQL.
   - Controller: Implementa os endpoints da API REST para operações de CRUD, permitindo a comunicação entre o frontend e o backend.
   - View: A camada de visualização é representada pelo frontend em JavaScript e React, onde a interface do usuário é construída e exibida.


2. **Arquitetura RESTful**
   
   A aplicação segue o estilo RESTful para a comunicação entre o frontend e o backend. Cada recurso (por exemplo, campanhas) possui um endpoint específico, utilizando métodos HTTP apropriados (GET, POST, PUT, DELETE). Essa arquitetura permite que o frontend se comunique com o backend de maneira eficiente e escalável, facilitando futuras integrações e expansões.

3. **Banco de Dados Supabase**
   
   O Supabase, com um banco de dados PostgreSQL, foi escolhido para armazenar as informações de campanhas e usuário (adm). Essa escolha permite flexibilidade e segurança na manipulação dos dados, além de proporcionar um sistema de autenticação básico integrado, utilizado para gerenciar o acesso dos usuários (adm e visitante) à aplicação.

4. **Autenticação e Controle de Acesso**
   
   A aplicação implementa um sistema de autenticação com diferentes níveis de acesso:
   - Administrador (adm): Usuário com permissão para realizar operações completas de CRUD na área administrativa. A página administrativa é invisível para usuários não logados, sendo acessível apenas pelo próprio administrador.
     
   - Visitantes: Usuários com acesso limitado à visualização de campanhas e doações.
   
   A autenticação básica é realizada no backend, protegendo as rotas administrativas e garantindo que apenas usuários autorizados possam acessar e modificar dados sensíveis.

## Escolhas de Design


1. **Paleta de Cores e Identidade Visual**
   
   - A aplicação utiliza uma paleta de cores verde e branca, que transmite tranquilidade, confiança e reforça o propósito da plataforma de ajudar causas sociais. O logotipo "Ajudaê" e outros elementos principais são destacados em verde, criando uma identidade visual coesa e atraente.

2. **Design Baseado em Componentes com React**
   
   - Cada elemento da interface, como cards de campanhas, formulários de login e botões de ação, foi implementado como um componente independente em React. Isso permite a reutilização de componentes e facilita a manutenção e evolução da interface.
     
   - A componentização também melhora o desempenho da aplicação, já que apenas os componentes necessários são renderizados ou atualizados durante as interações do usuário.

3. **Organização e Usabilidade das Telas**
   
  - Tela Principal (Home):
     - Exibe campanhas em formato de cards, contendo informações resumidas como título, descrição, meta de arrecadação, valor arrecadado e uma barra de progresso. Esse layout facilita a navegação e torna a visualização das campanhas intuitiva.

    ![image](https://github.com/user-attachments/assets/cb4b7907-84f2-49c7-aa6f-94da228b40ac)

  - Tela "Como Funciona":
     - Organizada em etapas numeradas com ícones e descrições, ajuda o usuário a entender o processo de criação e divulgação de campanhas. Cada etapa se eleva ao passar o mouse, criando uma experiência interativa e amigável.

    ![image](https://github.com/user-attachments/assets/3288713e-d05d-497e-a405-e5dd58a2320d)

  - Tela de Contatos:
     - Apresenta, de forma clara e visual, as responsabilidades de cada membro da equipe, mostrando quem contribuiu para a criação do projeto.
    
    ![image](https://github.com/user-attachments/assets/226435c2-6a83-40f7-8baa-f52aa329c4ad)

  - Tela de Login:
     - Utiliza um fundo em gradiente verde que alterna a paleta automaticamente, e um formulário centralizado, com efeitos sutis de expansão nos campos ao passar o mouse.

    ![image](https://github.com/user-attachments/assets/f0164d7c-3ec9-4d40-8f41-d3ddc40c7a6a)

  - Caso as credencias estejam incorretas:

    ![image](https://github.com/user-attachments/assets/3fa34e37-8140-4fc8-a9b8-3d262f321c7e)

  - Recuperar senha (visual): 

    ![image](https://github.com/user-attachments/assets/92ea7003-1fd7-4135-8962-cca5f109150f)

  - Tela Administrativa:
    - Disponível apenas para administradores, permite operações de CRUD em campanhas, com botões de edição e criação bem destacados. Essa tela foi desenhada para maximizar a eficiência do administrador na gestão de campanhas.
    
    ![image](https://github.com/user-attachments/assets/c3e5d765-33bc-4576-a415-1de31f322222)

4. **Interatividade e Feedback Visual**
      
  - Efeitos Hover e Animações: Cada card de campanha e etapa do processo "Como Funciona" possui um efeito de elevação ao passar o mouse, proporcionando uma experiência visual agradável e destacando os elementos interativos.
  
  - Barra de Progresso em Campanhas: Exibe o valor arrecadado em relação à meta, oferecendo um feedback visual imediato sobre o andamento das campanhas. Esse elemento incentiva os usuários a contribuir e acompanhar o progresso das causas.

5. **Acessibilidade e Clareza nas Ações**
      
  - O uso de botões destacados e links como "Esqueceu a senha?" garante que os usuários possam navegar pelo site de maneira fácil e intuitiva. Além disso, os títulos e descrições foram escritos de forma clara, melhorando a experiência de usuários de diferentes perfis.
  
  - O site também possui um favicon com o nome ‘Animaê’, proporcionando uma identidade visual adicional que aparece na aba do navegador.

## Conclusão

  As escolhas de design e arquitetura para o Portal de Doações Ajudaê foram orientadas por simplicidade, escalabilidade e uma experiência de usuário positiva. O uso do Spring Boot no backend e React no frontend possibilitou uma estrutura modular e fácil de manter, enquanto as decisões de design focaram em clareza e interatividade.
  
  Essa combinação resulta em uma aplicação que é, ao mesmo tempo, funcional e convidativa, alinhada ao propósito de facilitar doações e promover o engajamento dos usuários em campanhas sociais.
  
  A identidade visual consistente, o controle de acesso e a integração com o banco de dados Supabase asseguram que a aplicação funcione de forma segura e confiável, enquanto oferece uma experiência intuitiva tanto para doadores quanto para administradores.
















