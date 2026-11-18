# Aula 29 • DAO — Respostas da Lista Interativa

## Questões de múltipla escolha
| Questão | Resposta | Por quê |
|---|---|---|
| 1 (Impedance Mismatch) | **B** | Assimetria entre grafos de objetos na RAM e tabelas relacionais de dados atômicos. |
| 2 (SRP) | **C** | A classe de domínio fica acoplada a JDBC, tem duas razões para mudar e não dá para testar sem banco. |
| 3 (DIP) | **A** | O Controller depende do contrato; dá para trocar SQLite, PostgreSQL ou Mock sem alterar a regra de negócio. |
| 4 (Optional) | **D** | Explicita que o resultado pode estar ausente e obriga o chamador a tratar, evitando NPE acidental. |

## Desafio 1: mapearLinha
```java
private Livro mapearLinha(ResultSet rs) throws SQLException {
    Long id = rs.getLong("id");
    String titulo = rs.getString("titulo");
    String autor = rs.getString("autor");
    double preco = rs.getDouble("preco");
    return new Livro(id, titulo, autor, preco);
}
```

## Desafio 2: salvar
```java
stmt.setString(1, livro.getTitulo());
stmt.setString(2, livro.getAutor());
stmt.setDouble(3, livro.getPreco());
stmt.executeUpdate();
try (ResultSet keys = stmt.getGeneratedKeys()) {
    if (keys.next()) {
        livro.setId(keys.getLong(1));
    }
}
```

## Desafio 3: buscarPorId (trecho dentro do try do ResultSet)
```java
if (rs.next()) {
    return Optional.of(mapearLinha(rs));
}
return Optional.empty();
```

## Missão oficial
Projeto completo em `src/br/com/bookstore/` (model, dao, main).

Compilar e executar (a partir da raiz do projeto):
```
javac -encoding UTF-8 -d bin $(find src -name '*.java')
java -cp "bin:lib/sqlite-jdbc-3.46.1.3.jar" br.com.bookstore.main.Main
```
No Windows, use `;` no lugar de `:` no classpath.
