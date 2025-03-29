import dao.ConnectorMVC;

public class main {
    public static void main(String[] args) {
        ConnectorMVC connectorMVC = new ConnectorMVC();
        if (connectorMVC.getConnection() != null){
            System.out.println("conexão realizada com sucesso");
        }
    }
}
