package view;

import controller.FilmeController;
import dao.ExceptionDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.WHITE;
import static java.awt.Color.white;

public class MenuCadastroFilme extends JFrame {
    public JLabel lTitulo, lSinopse, lGenero, lDuracao, lCadastroFilme;
    public JTextField tfTitulo;
    public JComboBox<String> cbGenero;
    public JTextArea tfSinopse;
    JSpinner spDuracao;
    public JButton bSalvar, bLimpar, bCancelar, bConsultar, btnApagar;
    public Dimension eixos = Toolkit.getDefaultToolkit().getScreenSize(); // pegando o tamanho da tela do notebook
    public TelaSize tela = TelaSize.SMALL;
    private Integer cod_filme = 0;

    public MenuCadastroFilme(){
        this.iniciar();
    }

    private void iniciar(){

        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(36, 34, 34));
        Font fonteLabel = new Font(Font.SERIF, Font.BOLD, 17);
        JPanel painelNorte = new JPanel();
        painelNorte.setLayout(new BorderLayout());
        painelNorte.setBackground(Color.CYAN);
        lCadastroFilme = new JLabel("Cadastro de Filme", SwingConstants.CENTER);
        lCadastroFilme.setFont(new Font(Font.SERIF, Font.BOLD, 21));
        lCadastroFilme.setForeground(Color.BLACK);
        painelNorte.add(lCadastroFilme, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridBagLayout()); // Painel central com GridBagLayout
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Define margens

        // Adiciona componentes ao painel central
        lTitulo = new JLabel("Título:");
        lTitulo.setForeground(WHITE);
        lTitulo.setFont(fonteLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        centerPanel.add(lTitulo, gbc);

        tfTitulo = new JTextField(100);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(tfTitulo, gbc);


        lGenero = new JLabel("Gênero:");
        lGenero.setForeground(WHITE);
        lGenero.setFont(fonteLabel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        centerPanel.add(lGenero, gbc);


        cbGenero = new JComboBox<>(new String[]{"Comédia", "Ação", "Aventura", "Drama", "Fantasia", "Terror",
                "Ficção", "Eduacção", "Documentario"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        centerPanel.add(cbGenero, gbc);

        lSinopse = new JLabel("Sinopse:");
        lSinopse.setForeground(WHITE);
        lSinopse.setFont(fonteLabel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        centerPanel.add(lSinopse, gbc);

        tfSinopse = new JTextArea(10, 200);
        tfSinopse.setLineWrap(true);
        tfSinopse.setWrapStyleWord(true);
        JScrollPane spSinopse = new JScrollPane(tfSinopse);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(spSinopse, gbc);

        lDuracao =  new JLabel("Duração (min)");
        lDuracao.setForeground(WHITE);
        lDuracao.setFont(fonteLabel);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        centerPanel.add(lDuracao, gbc);

        spDuracao = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(spDuracao, gbc);

        // a classe gridbagconstraints sendo configurada para por elementos de forma horizontal
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridy = 4;

        bLimpar = new JButton("Limpar");
        gbc.gridx = 0;
        centerPanel.add(bLimpar, gbc);

        bSalvar = new JButton("Salvar");
        gbc.gridx = 1;
        centerPanel.add(bSalvar, gbc);


        bCancelar = new JButton("Cancelar");
        gbc.gridx = 2;
        centerPanel.add(bCancelar, gbc);

        bConsultar = new JButton("Consultar");
        gbc.gridx = 3;
        centerPanel.add(bConsultar, gbc);

        btnApagar = new JButton("Apagar");
        gbc.gridx = 4;
        centerPanel.add(btnApagar, gbc);



        this.salvarFilme();
        this.consultarFilme();
        this.deletarFilme();
        this.cancelar();

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(painelNorte, BorderLayout.NORTH);
        this.setSize(tela.getWidth(), tela.getHeight());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void deletarFilme(){
        btnApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean sucesso;
                FilmeController controller = new FilmeController();
                try {
                    sucesso = controller.apagar(cod_filme);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Filme deletado com sucesso!", "Status", JOptionPane.PLAIN_MESSAGE);
                        limparTelaCadastro();
                    }else {
                        JOptionPane.showMessageDialog(null, "Erro ao apagar Filme, isira um filme já cadastrado", "Status", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ExceptionDAO ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void cancelar(){
        bCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharJanela();
            }
        });
    }

    private void salvarFilme(){
        bSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int duracao = Integer.parseInt(spDuracao.getValue().toString());
                String genero = cbGenero.getSelectedItem().toString();
                boolean sucesso;

                try {
                    FilmeController filmeController = new FilmeController();

                    if(cod_filme == 0) {
                        sucesso = filmeController.cadastrarFilme(tfTitulo.getText(), genero, tfSinopse.getText(), duracao);
                    }else {
                        sucesso = filmeController.alterar(cod_filme, tfTitulo.getText(), genero, tfSinopse.getText(), duracao);
                        System.out.println("alteração realizada.");
                    }


                    if (sucesso){
                        JOptionPane.showMessageDialog(null,"O cadastro foi realizado com sucesso"
                        );
                        limparTelaCadastro();
                    }else {
                        JOptionPane.showMessageDialog(null, "Os campos não foram preenchidos corretamente.");

                    }
                } catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "ERRO" + exception);
                }
            }
        });

    }

    public void limparTelaCadastro(){
        bLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfSinopse.setText("");
                spDuracao.setValue(-1);
                tfTitulo.setText("");
                cbGenero.setSelectedIndex(0);

            }
        });
    }

    public void consultarFilme(){
        bConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConsultaFilmes consultaFilmes = new TelaConsultaFilmes((JFrame) getMenuCadastroFilme());
            }
        });


    }

    private MenuCadastroFilme getMenuCadastroFilme(){
        return this;
    }


    //titulo, genero, sinopse, duracao
    public void preencher(Integer cod_filme, String titulo, String genero, String sinopse, Integer duracao){
        this.cod_filme = cod_filme;
        this.tfTitulo.setText(titulo);
        this.tfSinopse.setText(sinopse);
        this.spDuracao.setValue(duracao);
        for (int contador = 0; contador < cbGenero.getItemCount(); contador++){

            if(cbGenero.getItemAt(contador).equals(genero)){
                cbGenero.setSelectedIndex(contador);
                return;
            }

        }
    }

    private void fecharJanela(){
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


}
