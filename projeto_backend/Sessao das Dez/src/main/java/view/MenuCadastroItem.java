package view;

import controller.FilmeController;
import controller.ItemController;
import dao.ExceptionDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.WHITE;


public class MenuCadastroItem extends JFrame {
    public JLabel lTituloFilme, lPreco, lTipo, lCasdastrarFilme;
    public JTextField tfTituloFilme;
    public JSpinner sPreco;
    public JComboBox<String> cbTipo;
    public JButton bSalvar, bLimpar, bCancelar, bConsultar, btnApagar, btnBuscarFilme;
    public Dimension eixos = Toolkit.getDefaultToolkit().getScreenSize(); // pegando o tamanho da tela do notebook
    public TelaSize tela = TelaSize.SMALL;
    private Integer id_item = 0;
    private Integer id_filme = 0;

    public MenuCadastroItem(){
        this.iniciar();
    }

    private void iniciar(){

        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(36, 34, 34));
        Font fonteLabel = new Font(Font.SERIF, Font.BOLD, 17);
        JPanel painelNorte = new JPanel();
        painelNorte.setLayout(new BorderLayout());
        painelNorte.setBackground(Color.CYAN);
        lCasdastrarFilme = new JLabel("Cadastro de Item", SwingConstants.CENTER);
        lCasdastrarFilme.setFont(new Font(Font.SERIF, Font.BOLD, 21));
        lCasdastrarFilme.setForeground(Color.BLACK);
        painelNorte.add(lCasdastrarFilme, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new GridBagLayout()); // Painel central com GridBagLayout
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Define margens

        // Adiciona componentes ao painel central
        lTituloFilme = new JLabel("Título Do filme:");
        lTituloFilme.setForeground(WHITE);
        lTituloFilme.setFont(fonteLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        centerPanel.add(lTituloFilme, gbc);

        tfTituloFilme = new JTextField(100);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        centerPanel.add(tfTituloFilme, gbc);

        btnBuscarFilme = new JButton("Buscar Filme");
        btnBuscarFilme.setBackground(Color.green);
        gbc.gridx = 2;
        gbc.gridy = 0;
        centerPanel.add(btnBuscarFilme, gbc);



                    // Tipos de mídia física
                    // DVD
                    // Blu-Ray
                    // CD-ROM
                    // HDD (Hard Disk Drive)
                    // SSD (Solid State Drive)
                    // Pendrive
                    // Cartões de Memória
                    // Fitas VHS

                    // Tipos de mídia digital
                    // Arquivos Digitais (MP4, AVI, MKV, etc.)
                    // Serviços de Streaming (Netflix, Disney+, Amazon Prime, etc.)
                    // Servidores NAS (Network Attached Storage)
                    // Nuvem (Google Drive, OneDrive, Dropbox)
        lTipo = new JLabel("Tipo de Midia: ");
        lTipo.setForeground(WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(lTipo, gbc);

        cbTipo = new JComboBox<>(new String[]{"DVD", "Blu-ray", "CD-ROM", "HDD", "SSD", "PENDRIVE", "FITA VHS",
                "MP4", "AVI", "MKV"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        centerPanel.add(cbTipo, gbc);

        lPreco =  new JLabel("Preço: ");
        lPreco.setForeground(WHITE);
        lPreco.setForeground(WHITE);
        lPreco.setFont(fonteLabel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(lPreco, gbc);

        sPreco = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));
        gbc.gridx = 1;
        gbc.gridy = 2;
        centerPanel.add(sPreco, gbc);


        // a classe gridbagconstraints sendo configurada para por elementos de forma horizontal
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridy = 3;

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
        this.limparTelaCadastro();
        this.consultarItem();
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
                ItemController controller = new ItemController();
                try {
                    sucesso = controller.apagar(id_item);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "item deletado com sucesso!", "Status", JOptionPane.PLAIN_MESSAGE);
                        limparTelaCadastro();
                    }else {
                        JOptionPane.showMessageDialog(null, "Erro ao apagar item, isira um item já cadastrado", "Status", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ExceptionDAO ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void consultarItem(){
        bConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConsultaItem telaConsultaItem = new TelaConsultaItem(getMenuCadastroItem());
            }
        });
    }

    private void salvarFilme(){
        bSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = cbTipo.getSelectedItem().toString();
                String tituloFilme = lTituloFilme.getText();
                double preco = Integer.parseInt(sPreco.getValue().toString());
                boolean sucesso;

                try {
                    ItemController itemController = new ItemController();
                    String mensagem = "";

                    if(id_item == 0) {
                        sucesso = itemController.salvar(id_filme, tipo, preco, tituloFilme);
                        mensagem += "Cadastro realizado com sucesso!";
                    }else {
                        sucesso = itemController.alterar(id_item, tipo, tituloFilme, preco, id_filme);
                        mensagem += "Alteração realizada com sucesso!!";
                    }


                    if (sucesso){
                        JOptionPane.showMessageDialog(null,mensagem);
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
                tfTituloFilme.setText("");
                sPreco.setValue(-1);
                cbTipo.setSelectedIndex(0);
            }
        });
    }


    public void consultarFilme(){
        btnBuscarFilme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConsultaFilmes telaConsultaFilmes = new TelaConsultaFilmes(getMenuCadastroItem());
            }
        });
    }

    private void cancelar(){
        bCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTelaCadastro();
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
    }

    private MenuCadastroItem getMenuCadastroItem(){
        return this;
    }

    public void preencher(Integer cod_item, Integer cod_filme, String tituloFilme, String tipo, double preco){
        //cod_item, cod_filme, titulo, tipo, preco
        this.id_filme = cod_filme;
        this.id_item = cod_item;
        this.tfTituloFilme.setText(tituloFilme);
        this.sPreco.setValue(preco);
        for (int contador = 0; contador < cbTipo.getItemCount(); contador++){

            if(cbTipo.getItemAt(contador).equals(tipo)){
                cbTipo.setSelectedIndex(contador);
                return;
            }

        }
    }

    public void preencher(Integer codFilme, String titulo){
        this.id_filme = codFilme;
        this.tfTituloFilme.setText(titulo);
    }
}
