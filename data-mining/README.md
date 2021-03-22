# Data Mining

> Status do Projeto: Concluido :heavy_check_mark:

![Badge](https://img.shields.io/static/v1?label=java&message=language&color=blue&style=for-the-badge&logo=JAVA)
![Badge](https://img.shields.io/static/v1?label=thread&message=paralell%20programming&color=lightblue&style=for-the-badge&logo=THREAD)

![Badge](https://img.shields.io/static/v1?label=spark&message=framework&color=green&style=for-the-badge&logo=SPARK)
![Badge](https://img.shields.io/static/v1?label=JUnit&message=framework&color=green&style=for-the-badge&logo=JUNIT)

![Badge](https://img.shields.io/static/v1?label=GSon&message=library&style=for-the-badge&logo=JSON)
![Badge](https://img.shields.io/static/v1?label=JSoup&message=library&style=for-the-badge&logo=JSOUP)

#### Data Mining, nada mais é do que o próprio nome diz: um software para mineração de dados. Ele é um webcrawler utilizando a biblioteca JSOUP para navegar nas páginas Web e encontrar termos que desejar procurar.

## Como funciona

Existem dois parâmetros que limitam o funcionamento do webcrawler, esses parâmetros tem que estar armazenados nas variáveis de ambiente:

- BASE_URL: a url base que o webcrawler irá executar, toda e qualquer url que não inicie com a URL_BASE.
- MAX_RESULTS: a quantidade máxima de urls que irá conter os termos. (<= 0 = infinito)

<span style="text-decoration: underline">Esse parâmetros não tem como serem alterados em tempo de execução.</span>

Ao iniciar o projeto, ele irá disponibilizar uma API na porta <span style="color: blue; font-weight: 700;">4567</span> com as seguintes chamadas:

![Badge](https://img.shields.io/static/v1?label=POST&message=/crawl&style=for-the-badge&logo=HTTP)

##### Request Body

```json
{
	keyword: "term"
}
```

A cada chamada é iniciada uma nova <span style="font-weight: 700;">Thread</span> que é um webcrawler, ele vai funcionar até que seja atingido o limite do <span style="font-weight: 700;">MAX_RESULT</span>, a menos que seja infinito. 

Iniciado o processo, é retornado um ID que identifica a busca, para consulta futura.

##### Response Body

```json
{
	id: "{codigo alfanumerico de 8 caracteres}"
}
```
![Badge](https://img.shields.io/static/v1?label=GET&message=/crawl/{id}&style=for-the-badge&logo=HTTP)

##### body

```json
vazio
```

Ao fazer a requisição passando o {id} da busca retornado na primeira chamada, ele retornará como está a situação do webcrawler, com os seguintes campos no corpo da resposta:

```json
{
	id: "{codigo alfanumerico de 8 caracteres}",
    status: "{inactive | active | done}",
    urls: ["URLs que encontrar"]
}
```