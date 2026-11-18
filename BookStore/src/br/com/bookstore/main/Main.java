package br.com.bookstore.main;

import br.com.bookstore.dao.LivroDAO;
import br.com.bookstore.dao.LivroDAOSqlite;
import br.com.bookstore.model.Livro;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. DIP: depende da interface, instancia a implementação SQLite
        LivroDAO dao = new LivroDAOSqlite();

        // 2. Persistir 3 obras e verificar os IDs gerados
        Livro l1 = new Livro("Clean Code", "Robert C. Martin", 89.90);
        Livro l2 = new Livro("Design Patterns", "Erich Gamma et al.", 129.50);
        Livro l3 = new Livro("Refactoring", "Martin Fowler", 109.00);
        dao.salvar(l1);
        dao.salvar(l2);
        dao.salvar(l3);
        System.out.println("=== IDs gerados ===");
        System.out.println("Clean Code      -> ID " + l1.getId());
        System.out.println("Design Patterns -> ID " + l2.getId());
        System.out.println("Refactoring     -> ID " + l3.getId());

        // 3. Busca do ID 2 com ifPresentOrElse
        System.out.println("\n=== Busca por ID 2 ===");
        dao.buscarPorId(2L).ifPresentOrElse(
                l -> System.out.println("Encontrado: " + l),
                () -> System.out.println("Aviso: livro com ID 2 não encontrado."));

        // 4. ID inexistente: sem NullPointerException
        System.out.println("\n=== Busca por ID 999 ===");
        dao.buscarPorId(999L).ifPresentOrElse(
                l -> System.out.println("Encontrado: " + l),
                () -> System.out.println("Aviso: nenhum livro com ID 999 foi encontrado."));

        // 5. Listagem tabular
        System.out.println("\n=== Catálogo completo ===");
        List<Livro> todos = dao.listarTodos();
        System.out.printf("%-4s | %-20s | %-22s | %10s%n", "ID", "TÍTULO", "AUTOR", "PREÇO");
        System.out.println("-".repeat(66));
        for (Livro l : todos) {
            System.out.printf("%-4d | %-20s | %-22s | R$ %7.2f%n",
                    l.getId(), l.getTitulo(), l.getAutor(), l.getPreco());
        }
    }
}
