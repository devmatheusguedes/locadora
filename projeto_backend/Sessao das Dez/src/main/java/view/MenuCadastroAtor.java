package view;

import controller.AtorController;
import dao.ExceptionDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCadastroAtor extends JFrame {
    private final Dimension eixos = Toolkit.getDefaultToolkit().getScreenSize();
    private Integer cod_ator = 0;
    private Integer cod_filme = 0;
    private JTextField txfNome, txNomeFilme;
    private JComboBox cmbxNacionalidade;

    public MenuCadastroAtor(){
        this.iniciar();

    }
    public void iniciar(){
        JLabel rotulo = new JLabel("Cadastro De Ator", JLabel.CENTER);
        rotulo.setFont(new Font("Serif", Font.PLAIN, 30));
        JPanel fundo = new JPanel();
        JPanel fundoTwo = new JPanel();
        JPanel fundoThree = new JPanel();
        JPanel fundoFour = new JPanel();
        JPanel fundoFive = new JPanel();

        JLabel lblNome = new JLabel("Nome");
        JLabel lbFilme = new JLabel("Titulo Do Filme: ");
        txfNome = new JTextField();
        JLabel lblNacionalidade = new JLabel("Nacionalidade");
        JButton btnCnacelar = new JButton("Cancelar");
        JButton btnConsultar = new JButton("Consultar");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnSalvar = new JButton("Salvar");
        JButton btnApagar = new JButton("Apagar");
        JButton buscarFilme = new JButton("buscar Filme");
        cmbxNacionalidade = new JComboBox<>(new String[]{"Nacionalidade", "Brasileiro", "Americano",
                "Dinamarquês", "português"});

        lblNome.setFont(new Font("serif", Font.PLAIN, 14));
        lblNome.setForeground(new Color(255, 255, 255));
        lblNome.setBounds(10, 10, 120, 20);
        txfNome.setBounds(140, 10, 300, 20);

        lblNacionalidade.setFont(new Font("Serif", Font.PLAIN, 14));
        lblNacionalidade.setForeground(new Color(255, 255, 255));
        lblNacionalidade.setBounds(10, 60, 120, 20);
        cmbxNacionalidade.setBounds(140, 60, 120, 20);

        lbFilme.setFont(new Font("serif", Font.PLAIN, 14));
        lbFilme.setForeground(new Color(255, 255, 255));
        lbFilme.setBounds(10, 110, 120, 20);
        txNomeFilme = new JTextField();
        txNomeFilme.setBounds(140, 110, 300, 20);
        buscarFilme.setBackground(new Color(144, 6, 39));
        buscarFilme.setForeground(new Color(253, 253, 253));
        buscarFilme.setBounds(450, 110, 150, 20);

        btnSalvar.setBounds(110, 210, 80, 20);
        btnLimpar.setBounds(210, 210, 80, 20);
        btnCnacelar.setBounds(310, 210, 120, 20);
        btnConsultar.setBounds(450, 210, 120, 20);
        btnApagar.setBounds(590, 210, 120, 20);






        fundo.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());
        TelaSize telaSize = TelaSize.SMALL;
        fundo.setBackground(new Color(53,
                253,
                253));
        fundoTwo.setLayout(null);
        fundoTwo.add(lblNome);
        fundoTwo.add(txfNome);
        fundoTwo.add(lblNacionalidade);
        fundoTwo.add(cmbxNacionalidade);
        fundoTwo.add(lbFilme);
        fundoTwo.add(txNomeFilme);
        fundoTwo.add(buscarFilme);
        fundoTwo.add(btnSalvar);
        fundoTwo.add(btnLimpar);
        fundoTwo.add(btnCnacelar);
        fundoTwo.add(btnConsultar);
        fundoTwo.add(btnApagar);
        fundoTwo.setBackground(new Color(37, 70, 77));
        fundoThree.setBackground(new Color(0x928E8E));
        fundoFour.setBackground(new Color(0x928E8E));
        fundoFive.setBackground(new Color(0x928E8E));



        fundo.add(fundoTwo, BorderLayout.CENTER);
        fundoTwo.setPreferredSize(new Dimension(100, 500));
        fundo.add(fundoThree, BorderLayout.EAST);
        fundo.add(fundoFour, BorderLayout.WEST);
        fundo.add(fundoFive, BorderLayout.SOUTH);
        fundo.add(rotulo, BorderLayout.NORTH);
        this.add(fundo, BorderLayout.CENTER);

        this.setBounds(
                (int) (eixos.getWidth() - telaSize.getWidth()) /2,
                (int) (eixos.getHeight() - telaSize.getHeight()) /2, telaSize.getWidth(),
                telaSize.getHeight());


        btnCnacelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("acionando botão cancelar...");
            }


        });
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConsultaAtor consultaAtor = new TelaConsultaAtor(getMenuCadastroAtor());
                System.out.println("acionando botão consultar...");
                fecharJanela();

            }
        });

        buscarFilme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConsultaFilmes telaConsultaFilmes = new TelaConsultaFilmes(getMenuCadastro());
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txfNome.getText();
                String nacionalidade = cmbxNacionalidade.getSelectedItem().toString();
                boolean sucesso;

                try {
                AtorController atorController = new AtorController();
                String mensagem;
                if (cod_ator == 0) {
                    sucesso = atorController.cadastroAtor(nacionalidade, nome, cod_filme);
                    mensagem = "novo cadastro realizado com sucesso";
                }else {
                    sucesso = atorController.alterar(cod_ator, nome, nacionalidade, cod_filme);
                    mensagem = "alteração concluida.";
                }


                if (sucesso) {
                    JOptionPane.showMessageDialog(null, mensagem);
                }else {
                    mensagem = "falha ao cadastrar/alterar Ator.";
                    mensagem += "\nCampos Obrigatórios: selecionar um filme | Nome do ator | Nacionalidade |";
                    JOptionPane.showMessageDialog(null, mensagem);

                }

                } catch(Exception exception) {
                    JOptionPane.showMessageDialog(null, "ERRO: " + exception);
                }


            }
        });

        btnApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    apagarAtor();
                } catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //btnSalvar, btnLimpar, btnCnacelar, btnConsultar
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);


    }

    public void fecharJanela(){
        this.setVisible(false);
    }
    public MenuCadastroAtor getMenuCadastroAtor(){
        return this;
    }

    public void preencherAtor(Integer cod_ator, String nomeAtor, String nacionalidade, Integer id_filme, String nomeFilme){
        this.cod_ator = cod_ator;
        this.cod_filme = id_filme;
        this.txfNome.setText(nomeAtor);
        this.txNomeFilme.setText(nomeFilme);

        for (int contador = 0; contador < cmbxNacionalidade.getItemCount(); contador++){
            if(cmbxNacionalidade.getItemAt(contador).equals(nacionalidade)){
                cmbxNacionalidade.setSelectedIndex(contador);
                return;
            }else {
                System.out.println("nacionalidade não identificada");
            }
        }
    }

    public void preencherFilme(Integer id_filme, String nomeFilme){
        this.cod_filme = id_filme;
        this.txNomeFilme.setText(nomeFilme);
    }

    public void apagarAtor()throws ExceptionDAO {
        boolean status;
        AtorController atorController = new AtorController();
        status = atorController.deletarAtor(this.cod_ator);
        if (status) JOptionPane.showMessageDialog(null, "Ator deletado com sucesso!", "Status", JOptionPane.PLAIN_MESSAGE);
        else JOptionPane.showMessageDialog(null, "Erro ao deletar Ator", "Status", JOptionPane.ERROR_MESSAGE);
    }

    public JFrame getMenuCadastro(){
        return this;
    }

    private void limparTela(){
        txfNome.setText("");
        txNomeFilme.setText("");
        cmbxNacionalidade.setSelectedIndex(0);
        cod_filme = 0;
        cod_ator = 0;
    }
}
