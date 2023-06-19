### Sobre o projeto: :money_with_wings:

Um cliente muito importante nos solicitou a criação de um sistema de comércio eletrônico para sua empresa e que fosse apresentado inicialmente um MVP para que eles pudessem acompanhar o projeto em outros passos. Para isso, ele nos solicitou uma estrutura específica e que pudéssemos agir sobre ela e trazer bons resultados.

Desenvolva um sistema de carrinho de compras em Java utilizando MongoDB ou MySQL como banco de dados. 

### O sistema deve possuir as seguintes funcionalidades: :paperclip:

**Gerenciamento de Produtos:**

Cada produto deve ter um nome, preço e quantidade disponível em estoque. Os produtos devem ser armazenados no banco de dados. Deve ser possível adicionar, remover e atualizar produtos.

**Carrinho de compras:** 

O carrinho de compras deve armazenar os produtos selecionados pelo cliente, juntamente com a quantidade de cada produto. O valor total do carrinho de compras deve ser calculado com base nos produtos selecionados e suas quantidades.

**Confirmação de Venda:**

Antes de confirmar a venda, o sistema deve solicitar uma confirmação do cliente. O cliente deve confirmar a compra antes que a venda seja concluída. Após a confirmação, o estoque dos produtos vendidos deve ser atualizado.

**Requisitos técnicos:**

- [x] O projeto deve ser desenvolvido em Java. 
- [x] Use a API oficial do MongoDB para interagir com o banco de dados ou utilize um banco de dados SQL, como o MySQL, para armazenar os dados. 
- [x] Utilize a biblioteca JDBC para conectar e interagir com o banco de dados. 
- [x] Organize o código em classes e utilize boas práticas de programação.
- [x] Implemente métodos para adicionar, remover e atualizar produtos. 
- [x] Implemente métodos para adicionar produtos ao carrinho de compras e calcular o valor total do carrinho. 
- [x] Implemente um fluxo de confirmação de venda antes de concluir a venda.

### Especificações de desenvolvimento: :open_file_folder:

- ###### Versão - 20.0.1 do Java.

- ###### IDE - IntelliJ IDEA Community Edition 2023.1.2

- ###### Sistema Operacional - Windows 10 Pro x64

### Requerimentos: :chart_with_upwards_trend:

1. ###### Java SE Development Kit 20.0.1 - https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html

2. ###### MySQL - https://dev.mysql.com/downloads/windows/installer/8.0.html

3. ###### XAMPP - https://www.apachefriends.org/download.html

### Possíveis configurações necessárias: :bug:

1. Para garantir que o XAMPP esteja funcionando corretamente, certifique-se de que tanto o serviço Apache quanto o serviço MySQL estejam ligados

![image-20230619005913747](https://prnt.sc/KxLjfonOYLM0)

2. Pode ser necessário configurar o conexão do código de acordo com suas credenciais configuradas em seu servidor MySQL, o arquivo de conexão está disponível dentro da pasta do projeto localizado em: **src/Model/Connect**

![image-20230619010804120](https://prnt.sc/KRUAiWCECAi5)

3. Em caso de conflito verifique se o SDK do projeto está utilizando o openjdk-20

![image-20230619012018780](https://prnt.sc/iFv2P_2wRdmw)

4. Certifique-se que não exista nenhum banco de dados (database) de nome **ecommerceCartWAC** cadastrado em seu servidor MySQL

### Manifest: :newspaper:

Manifest-Version: 1.0
Main-Class: e_commerce.View.Application
Created-By: 20.0.1 (Oracle Corporation)



