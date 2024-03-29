### Sobre o projeto: :smiley:

Um cliente muito importante nos solicitou a criação de um sistema de comércio eletrônico para sua empresa e que fosse apresentado inicialmente um MVP para que eles pudessem acompanhar o projeto em outros passos. Para isso, ele nos solicitou uma estrutura específica e que pudéssemos agir sobre ela e trazer bons resultados.

Desenvolva um sistema de carrinho de compras em Java utilizando MongoDB ou MySQL como banco de dados. 

### O sistema deve possuir as seguintes funcionalidades: :paperclip:

**Gerenciamento de Produtos:** :star:

Cada produto deve ter um nome, preço e quantidade disponível em estoque. Os produtos devem ser armazenados no banco de dados. Deve ser possível adicionar, remover e atualizar produtos.

**Carrinho de compras:** :star:

O carrinho de compras deve armazenar os produtos selecionados pelo cliente, juntamente com a quantidade de cada produto. O valor total do carrinho de compras deve ser calculado com base nos produtos selecionados e suas quantidades.

**Confirmação de Venda:** :star:

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

### Como inicializar: :mag_right:

É necessário o uso de uma IDE (integrated development environment) para inicializar o código, recomendamos o uso da IDE - IntelliJ IDEA Community Edition 2023.1.2.

Com o projeto aberto dentro da IDE, procure pelo arquivo principal Application localizado em:

**src/View/Application**

Abra o arquivo Application, dois cliques sobre ele, o código fonte deverá ser exibido na IDE.

Para inicializar este arquivo, você pode optar por dar run no projeto procurando o botão que fica (geralmente) no parte superior direita da IDE, ou utilizando o comando rápido (Shift+F10), esse comando pode variar de acordo com a IDE.

![4](https://github.com/WolneiACordeiro/desafio-1-e-commerce/assets/78006412/6f2c9be6-489d-4a35-8b67-0f716f838514)

Se todo processo ocorrer com sucesso o seguinte MENU irá aparecer no terminal da sua IDE, o banco de dados MySQL será criado automaticamente, permitindo rodar o projeto funcionalmente.

![5](https://github.com/WolneiACordeiro/desafio-1-e-commerce/assets/78006412/2af8464b-113a-40cd-962d-ec6b5862733f)

### Possíveis configurações necessárias: :bug:

1. Para garantir que o XAMPP esteja funcionando corretamente, certifique-se de que tanto o serviço Apache quanto o serviço MySQL estejam ligados

![1](https://github.com/WolneiACordeiro/desafio-1-e-commerce/assets/78006412/1947ae0f-f04a-4b05-b68a-dc4f5fc21a9c)

2. Pode ser necessário configurar o conexão do código de acordo com suas credenciais configuradas em seu servidor MySQL, o arquivo de conexão está disponível dentro da pasta do projeto localizado em: **src/Model/Connect**

![2](https://github.com/WolneiACordeiro/desafio-1-e-commerce/assets/78006412/ed95d8a1-b432-45c9-bb47-e1b4e73c3500)

3. Em caso de conflito verifique se o SDK do projeto está utilizando o openjdk-20

![3](https://github.com/WolneiACordeiro/desafio-1-e-commerce/assets/78006412/ac36edea-0548-445a-a965-5cee966fa10c)

4. Certifique-se que não exista nenhum banco de dados (database) de nome **ecommerceCartWAC** cadastrado em seu servidor MySQL

5. Caso e somente caso a classe de conexão não esteja criando o banco de dados automaticamente, o script SQL que pode ser executado dentro do seu gerenciador de banco de dados MySQL se encontra em **src/Model/ecommercecartwac**

### Manifest: :newspaper:

Manifest-Version: 1.0

Main-Class: e_commerce.View.Application

Created-By: 20.0.1 (Oracle Corporation)
