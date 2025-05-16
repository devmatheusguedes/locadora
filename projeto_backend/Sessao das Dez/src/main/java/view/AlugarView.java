package view;

import controller.LocacaoController;
import dao.ExceptionDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class AlugarView extends JFrame {
    private JButton btnPesquisaCliente, btnBuscarFilmeItem, btnAlugar, btnLimpar, btnApagar, btnConsultar;
    private JTextField jtCliente, jtFilme, jtTipoDeMidia, jtPreco;
    private JLabel jlCliente, jlFilme,
            jlMidia, jlPreco;
    private Dimension eixos = Toolkit.getDefaultToolkit().getScreenSize();
    private int largura = TelaSize.SMALL.getWidth(), altura = TelaSize.SMALL.getHeight();
    private Integer id_cliente = 0, id_item = 0, id_locacao = 0;
    private String status;
    private Date data_aluguel, data_devolucao;
    private JFrame menuCadastro;
    public AlugarView(){
        this.iniciar();
    }

    private void iniciar(){
        this.setLayout(null);
        this.setTitle("Alugar");
        this.setBounds((int)((eixos.getWidth() - largura)/2), (int)(eixos.getHeight() - altura)/2, largura, altura);
        jlCliente = new JLabel("nome do cliente: ");
        jlFilme = new JLabel("titulo do filme: ");
        jlMidia = new JLabel("tipo de midia: ");
        jlPreco = new JLabel("preço: ");
        Font font = new Font(Font.SERIF, Font.BOLD, 15);

        jlCliente.setBounds(20, 20, 120, 30);
        jlFilme.setBounds(20, 60, 120, 30);
        jlMidia.setBounds(20, 100, 120, 30);
        jlPreco.setBounds(20, 140, 120, 30);
        jlCliente.setFont(font);
        jlFilme.setFont(font);
        jlMidia.setFont(font);
        jlPreco.setFont(font);

        btnPesquisaCliente = new JButton("Buscar");
        btnAlugar = new JButton("Alugar");
        btnBuscarFilmeItem = new JButton("Buscar");



        jtCliente = new JTextField();
        jtFilme = new JTextField();
        jtPreco = new JTextField();

        jtFilme.setBounds(150, 20, 200, 30);
        jtCliente.setBounds(150, 60, 200, 30);
        jtTipoDeMidia = new JTextField();
        jtTipoDeMidia.setBounds(150, 100, 100, 30);
        jtPreco.setBounds(150, 140, 100, 30);




        btnBuscarFilmeItem.setBounds(360, 20, 120, 30);
        btnPesquisaCliente.setBounds(360, 60, 120, 30);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(150, 300, 120, 30);

        btnAlugar.setBounds(300, 300, 120, 30);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(450, 300, 120, 30);

        btnApagar = new JButton("Apagar");
        btnApagar.setBounds(600, 300, 120, 30);






        this.add(jlCliente);
        this.add(jlFilme);
        this.add(jlPreco);
        this.add(jlMidia);
        this.add(jtCliente);
        this.add(jtFilme);
        this.add(jtTipoDeMidia);
        this.add(jtPreco);
        this.add(btnPesquisaCliente);
        this.add(btnBuscarFilmeItem);
        this.add(btnConsultar);
        this.add(btnAlugar);
        this.add(btnLimpar);
        this.add(btnApagar);
        this.buttonActionaPesquisaCliente();
        this.buttonActionPesquisarItem();
        this.alugarItem();
        this.limparTela();
        this.apagar();
        this.consultar();
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    private void buttonActionaPesquisaCliente(){
        btnPesquisaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConsultaCliente cliente = new TelaConsultaCliente((JFrame)getAlugarView());
            }
        });
    }

    private void consultar(){
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuCadastro = (TelaConsultaLocacao) menuCadastro;
                menuCadastro.setVisible(true);
            }
        });
    }

    private void buttonActionPesquisarItem(){
        btnBuscarFilmeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConsultaItem telaConsultaItem = new TelaConsultaItem((JFrame) getAlugarView());
            }
        });
    }

    public void preencherCampoCliente(String nome, Integer id_cliente){

        this.jtCliente.setText(nome);
        this.id_cliente = id_cliente;
    }

    public void preencherCampoItem(Integer id_item, String tipo_midia, String nome_filme, double preco){
        this.id_item = id_item;
        this.jtTipoDeMidia.setText(tipo_midia);
        this.jtFilme.setText(nome_filme);
        this.jtPreco.setText(String.valueOf(preco));
    }

    public void alugarItem(){
        btnAlugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LocalDate data = LocalDate.now();
                data_aluguel = Date.valueOf(data);

                data_devolucao = Date.valueOf(data.plusDays(10));

                status = "N/P";

                LocacaoController controller = new LocacaoController();
                boolean isTrue = false;
                try {
                    String mensagem = "";
                    if(id_locacao == 0) {
                        isTrue = controller.salvar(id_cliente, id_item, data_aluguel, data_devolucao, status);
                        mensagem += "Cadastro realizado com sucesso!";
                    }else {
                        isTrue = controller.alterar(id_locacao, id_cliente, id_item, data_aluguel, data_devolucao, status);
                        mensagem += "Alteração realizado com sucesso!";
                    }
                    if (!isTrue){
                        mensagem = "Preencha todos os campos.";
                    }

                    JOptionPane.showMessageDialog(null, mensagem);

                } catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void apagar(){
        btnApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocacaoController locacaoController = new LocacaoController();
                String mensagem = "";
                boolean istrue;
                try {
                    istrue = locacaoController.apagar(id_locacao);
                    if (istrue){
                        mensagem += "registro apagado com sucesso";
                        limparTela();
                    }else mensagem += "selecione um resgitro.";

                    JOptionPane.showMessageDialog(null, mensagem);
                }catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    private void limparTela(){
                id_item = 0;
                id_locacao = 0;
                id_cliente = 0;
                jtPreco.setText("");
                jtFilme.setText("");
                jtTipoDeMidia.setText("");
                jtCliente.setText("");

    }

    public void setMenuCadastro(JFrame menuCadastro) {
        this.menuCadastro = menuCadastro;
    }

    public AlugarView getAlugarView(){
        return this;
    }

    public JTextField getJtCliente() {
        return jtCliente;
    }

    public void setJtCliente(String jtCliente) {
        this.jtCliente.setText(jtCliente);
    }

    public JTextField getJtFilme() {
        return jtFilme;
    }

    public void setJtFilme(String jtFilme) {
        this.jtFilme.setText(jtFilme);
    }

    public JTextField getJtTipoDeMidia() {
        return jtTipoDeMidia;
    }

    public void setJtTipoDeMidia(String jtTipoDeMidia) {
        this.jtTipoDeMidia.setText(jtTipoDeMidia);
    }

    public JTextField getJtPreco() {
        return jtPreco;
    }

    public void setJtPreco(String jtPreco) {
        this.jtPreco.setText(jtPreco);
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_item() {
        return id_item;
    }

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData_aluguel() {
        return data_aluguel;
    }

    public void setData_aluguel(Date data_aluguel) {
        this.data_aluguel = data_aluguel;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public Integer getId_locacao() {
        return id_locacao;
    }

    public void setId_locacao(Integer id_locacao) {
        this.id_locacao = id_locacao;
    }
}
