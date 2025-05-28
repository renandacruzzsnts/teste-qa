import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.util.Vector;

public class Teste {
    // Erro: variável pública estática mutável
    public static ArrayList<String> listaPublica = new ArrayList<>();
    
    // Erro: campo não final em uma classe Serializable
    public String campoMutavel;

    public static void main(String[] args) {
        String idade = "20";
        if (idade > 18) { // Erro: comparação entre String e int
            print("Maior de idade"); // Erro: uso de print (não existe em Java, deveria ser System.out.println)
        }

        int b = 0;
        if (b == 0) {
            int c = 10 / b; // Erro: divisão por zero
        }

        // Erro: variável não inicializada
        int x;
        System.out.println(x);

        // Erro: array index out of bounds
        int[] arr = new int[2];
        arr[2] = 10;

        // Erro: uso de variável não declarada
        y = 5;

        // Erro: uso de método deprecated
        Date data = new Date();
        int ano = data.getYear();

        // Erro de segurança: SQL Injection
        String usuario = "admin";
        String senha = "1234";
        String query = "SELECT * FROM usuarios WHERE usuario = '" + usuario + "' AND senha = '" + senha + "'";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("Usuário encontrado!");
            }
            // Erro: não fecha recursos (vazamento de recurso)
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Erro: Vector é obsoleto
        Vector v = new Vector();
        
        // Erro: uso inseguro de reflexão
        try {
            Class<?> classe = Class.forName("java.lang.String");
            Field campo = classe.getDeclaredField("value");
            campo.setAccessible(true);
        } catch(Exception e) {
            // Erro: captura genérica de exceção
            System.out.println(e);
        }

        // Erro: memory leak em coleção
        ArrayList list = new ArrayList();
        while(true) {
            list.add(new Object());
        }

        // Erro: race condition
        final int[] contador = {0};
        Thread t1 = new Thread(() -> contador[0]++);
        Thread t2 = new Thread(() -> contador[0]++);
        t1.start();
        t2.start();

        // Erro: null pointer exception potencial
        String texto = null;
        if(texto.equals("teste")) {
            System.out.println("igual");
        }

        // Erro: hardcoding de senha
        final String SENHA = "minhasenha123";

        // Erro: comparação de floating point insegura
        double a = 0.1;
        double b = 0.2;
        if(a + b == 0.3) {
            System.out.println("igual");
        }
    }

    // Erro: método que engole exceção
    private void metodoProblematico() {
        try {
            // algo perigoso
        } catch(Exception e) {
            // não faz nada com a exceção
        }
    }

    // Erro: método synchronized desnecessário
    synchronized public void metodoLento() {
        // operação que não precisa de sincronização
        System.out.println("processo lento");
    }

    // Função não utilizada
    public void funcaoNaoUsada() {
        System.out.println("Nunca chamada");
    }
}
