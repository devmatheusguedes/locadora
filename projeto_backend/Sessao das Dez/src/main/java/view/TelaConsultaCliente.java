package view;

import controller.ClienteController;
import dao.ExceptionDAO;
import model.MCliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

public class TelaConsultaCliente extends JFrame{
    private JLabel icone, jLabel2;
    private JTable jTable;
    private JTextField jTextField;
    private JButton jButtonPesquisar;
    private static final int x = 690;
    private static final int y = 490;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Dimension tamanhoDaTela = toolkit.getScreenSize();
    private MenuCadastroCliente menuCadastroCliente;

    public TelaConsultaCliente(MenuCadastroCliente menuCadastroCliente){
        super("Tela de consulta do cliente");
        this.menuCadastroCliente = menuCadastroCliente;
        this.iniciar();
    }

    private void iniciar(){
        this.setBounds((int) (tamanhoDaTela.getWidth() - x)/2, (int)(tamanhoDaTela.getHeight() - y)/2, x, y);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        Icon imageIcon = new ImageIcon("src/main/resources/xicara.png");
        icone = new JLabel("Consulta filmes", imageIcon, SwingConstants.CENTER);
        icone.setHorizontalTextPosition(SwingConstants.RIGHT);
        icone.setBounds(240, 5, 200, 30);

        jButtonPesquisar = new JButton("pesquisar");

        jLabel2 = new JLabel("Informe o nome do cliente: ");
        jLabel2.setBounds(30, 70, 200, 30);

        jTextField = new JTextField();
        jTextField.setBounds(250, 70, 300, 30);

        jButtonPesquisar.setBounds(560, 70, 110, 30);

        String[] nomeDaColuna = {"código", "nome", "CPF", "e-mail", "endereço", "Data de nascimento"};
        DefaultTableModel modelo = new DefaultTableModel(nomeDaColuna, 0);
        jTable = new JTable(modelo);
        JScrollPane painelTabela = new JScrollPane(jTable);
        painelTabela.setBounds(30, 120, 640, 100);


        jButtonPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteController clienteController = new ClienteController();
                String nome = jTextField.getText();
                DefaultTableModel moelo = (DefaultTableModel) jTable.getModel();

                try {
                    ArrayList<MCliente> clientes = clienteController.listarClientes(nome);

                    clientes.forEach((MCliente cliente) ->{modelo.addRow(new Object[]{
                            cliente.getCodCliente(),
                            cliente.getNome(),
                            cliente.getCpf(),
                            cliente.getEmail(),
                            cliente.getEndereco(),
                            cliente.getDataNascimento()
                    });});
                    jTable.setModel(moelo);
                } catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        painelTabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //código", "nome", "CPF", "e-mail", "endereço", "Data de nascimento
                if(e.getClickCount() == 2) {
                    int row = jTable.getSelectedRow();
                    if (row != -1) {
                        Integer cod_cliente = (Integer) jTable.getModel().getValueAt(row, 0);
                        String nome = (String) jTable.getModel().getValueAt(row, 1);
                        String cpf = (String) jTable.getModel().getValueAt(row, 2);
                        String email = (String) jTable.getModel().getValueAt(row, 3);
                        String endereco = (String) jTable.getModel().getValueAt(row, 4);
                        Date data_nascimento = (Date) jTable.getModel().getValueAt(row, 5);
                        menuCadastroCliente.preencher(cod_cliente, nome, cpf, email, endereco, data_nascimento);
                        menuCadastroCliente.setVisible(true);
                        dispose();
                    }
                }
            }
        });


        this.add(icone);
        this.add(jLabel2);
        this.add(jTextField);
        this.add(jButtonPesquisar);
        this.add(painelTabela);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fecharTela();
            }
        });






        this.setVisible(true);

    }

    private void fecharTela(){
        this.dispose();
        this.menuCadastroCliente.setVisible(true);
    }
}
