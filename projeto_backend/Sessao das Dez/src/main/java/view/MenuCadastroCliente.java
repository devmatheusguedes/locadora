package view;

import controller.ClienteController;
import dao.ExceptionDAO;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.SimpleFormatter;

import static java.awt.BorderLayout.*;

public class MenuCadastroCliente extends JFrame {
    private final Dimension eixos = Toolkit.getDefaultToolkit().getScreenSize();
     JButton btnSalvar, btnLimpar, btnCancelar, btnConsultar, btnApagar;
     JLabel nome, LdataNascimento, cpf, email, endereco, cadastroCliente;
    private JTextField jtxnome, jtxCpf, jtxEmail, jtxEndereco;
    private JDatePickerImpl dataNascimento;
    private SqlDateModel modelo;
    private Integer cod_cliente = 0;

    public MenuCadastroCliente(){
        this.iniciar();
    }


    //código", "nome", "CPF", "e-mail", "endereço", "Data de nascimento
    public void preencher(Integer cod_cliente, String nome, String cpf, String email, String endereco, Date data_nascimento){
        this.cod_cliente = cod_cliente;
        this.jtxnome.setText(nome);
        this.jtxCpf.setText(cpf);
        this.jtxEmail.setText(email);
        this.jtxEndereco.setText(endereco);
        this.modelo.setValue((java.sql.Date) data_nascimento);
        // transforma os atributos sqlDateModel em um atributo global;
    }

    public void iniciar(){

        modelo = new SqlDateModel();
        Properties p = new Properties();
        p.put("text.today", "Hoje");
        p.put("text.month", "Mês");
        p.put("text.year", "Ano");
       JDatePanelImpl datePanel = new JDatePanelImpl(modelo, p);
        dataNascimento = new JDatePickerImpl(datePanel, new DateComponentFormatter());



        JPanel painelNorte, painelCentro, painelSul;
        Font font = new Font(Font.SERIF, Font.BOLD, 17);
        Color btnBordaCor = new Color(253, 253, 253, 255);
        Color pnlBacground = new Color(53, 253, 253);

      cadastroCliente = new JLabel("Cadastro Cliente", JLabel.CENTER);

      painelNorte = new JPanel();
      painelNorte.setLayout(new BorderLayout());
      cadastroCliente.setFont(new Font("calibre", Font.BOLD, 25));
      cadastroCliente.setForeground(Color.BLACK);
      painelNorte.add(cadastroCliente, CENTER);
      painelNorte.setBackground(new Color(0x90AED4));


        painelCentro = new JPanel();
        painelCentro.setLayout(new GridLayout(0, 2, 0, 10));
        nome = new JLabel("nome:", JLabel.CENTER);
        LdataNascimento = new JLabel("Data De Nascimento:", JLabel.CENTER);
        cpf = new JLabel("CPF:", JLabel.CENTER);
        email = new JLabel("E-mail:", JLabel.CENTER);
        endereco = new JLabel("Endereço:", JLabel.CENTER);
        painelCentro.setBackground(new Color(112, 187, 198));

      nome.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, btnBordaCor));
      cadastroCliente.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, btnBordaCor));
cpf.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1  , btnBordaCor));
      dataNascimento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,btnBordaCor));
      email.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, btnBordaCor));
      endereco.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, btnBordaCor));
        jtxnome = new JTextField();
        jtxCpf = new JTextField();
        jtxEmail = new JTextField();
        jtxEndereco = new JTextField();
        painelCentro.add(nome);
        painelCentro.add(jtxnome);
        painelCentro.add(LdataNascimento);
        painelCentro.add(dataNascimento);
        painelCentro.add(cpf);
        painelCentro.add(jtxCpf);
        painelCentro.add(email);
        painelCentro.add(jtxEmail);
        painelCentro.add(endereco);
        painelCentro.add(jtxEndereco);

        painelSul = new JPanel();
        painelSul.setLayout(new GridLayout(2, 5, 1, 1));
        btnCancelar =  new JButton("Cancelar");
        btnLimpar =  new JButton("Limpar");
        btnConsultar =  new JButton("Consultar");
        btnApagar = new JButton("Apagar");
        btnSalvar =  new JButton("Salvar");

        btnCancelar.setFont(font);
        btnSalvar.setFont(font);
        btnLimpar.setFont(font);
        btnConsultar.setFont(font);
        btnApagar.setFont(font);

        painelSul.add(btnLimpar);
        painelCentro.add(btnApagar);
        painelSul.add(btnConsultar);
        painelSul.add(btnCancelar);
        painelSul.add(btnSalvar);



        this.setLayout(new BorderLayout());
        this.add(painelCentro, CENTER);
        this.add(painelNorte, NORTH);
        this.add(painelSul, SOUTH);
        this.setBounds((int)(eixos.getWidth() - TelaSize.SMALL.getWidth()) /2,
                (int) (eixos.getHeight() -TelaSize.SMALL.getHeight()) /2,
                TelaSize.SMALL.getWidth(), TelaSize.SMALL.getHeight());

        btnCancelar.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.out.println("cancelando...");
          }
        });

        btnSalvar.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            String nome = jtxnome.getText();
            String cpf = jtxCpf.getText();
            String endereco = jtxEndereco.getText();
            String email = jtxEmail.getText();
            java.sql.Date nascimento = (java.sql.Date) dataNascimento.getModel().getValue();
            System.out.println(nascimento);

            try{
                boolean sucesso;
                String mensagem;
                ClienteController clienteController = new ClienteController();

                if(cod_cliente ==0){
                sucesso = clienteController.castroCliente(nome, cpf, endereco, nascimento, email);
                mensagem = "Cadastro realizado com sucesso!";
                }else {
                    sucesso = clienteController.alterar(cod_cliente, nome, cpf, endereco, nascimento, email);
                    mensagem = "Aleração relizada com sucesso!";
                }
                if(sucesso){
                    JOptionPane.showMessageDialog(null, mensagem);
                    limparTela();
                }else {
                    JOptionPane.showMessageDialog(null, "preencha os campos corretamente.");
                }
            }catch (Exception exception){
                JOptionPane.showMessageDialog(null, "ERRO: "+exception, null, JOptionPane.ERROR_MESSAGE);
            }
          }
        });


        btnConsultar.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              TelaConsultaCliente telaConsultaCliente = new TelaConsultaCliente(menuCadastroCliente());
              dispose();
          }
        });

        btnLimpar.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            limparTela();
          }
        });

        btnApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    apagarCliente();
                } catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

private void limparTela(){
        jtxnome.setText("");
        jtxCpf.setText("");
        jtxEmail.setText("");
        jtxEndereco.setText("");
        jtxnome.setText("");
        dataNascimento.getModel().setValue(null);
        dataNascimento.getJFormattedTextField().setText("");
        this.cod_cliente = 0;
}
public MenuCadastroCliente menuCadastroCliente(){
        return this;
}

public void apagarCliente() throws ExceptionDAO {
        boolean status;
        Integer cod_cliente = this.cod_cliente;
        ClienteController controller = new ClienteController();
        status = controller.apagarCliente(cod_cliente);
        if (status) JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso","Status", JOptionPane.PLAIN_MESSAGE);
        else JOptionPane.showMessageDialog(null, "Erro ao deletar cliente", "Status", JOptionPane.ERROR_MESSAGE);

}

}
