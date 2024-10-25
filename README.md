# Java+RestAssured

# Instalar dependências

```bash
mvn clean install
```

# Executar todos os testes

```bash
mvn test
```

# Executar teste especifico

```bash
mvn test -Dgroups="orderCreation"
```

# Processo para criar o projeto

## Sistema operacional utilizado

Linux

## Instalação do java 21

```bash
sudo apt install openjdk-21-jdk
```

## Ferramenta IDE

VSCode

## Configurar JDK para o VSCode

Ctrl + Shift + P e configurar o arquivo de configuração do usuário pela busca "settings.json"

```bash
{
    "java.jdt.ls.java.home": "/usr/lib/jvm/java-21-openjdk-amd64",
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-21",
            "path": "/usr/lib/jvm/java-21-openjdk-amd64",
            "default": true
        }
    ]
}
```

## Maven , gerenciador de pacotes

```bash
sudo apt install maven
```

## Projeto criado a partir do comando

```bash
mvn archetype:generate -DgroupId=com.example.petstore -DartifactId=petstore-tests -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
