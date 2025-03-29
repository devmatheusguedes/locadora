package view;

import controller.ItemController;
import dao.ExceptionDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuCadastroItem extends JFrame {
    private final TelaSize telaCadastro = TelaSize.SMALL;
    private JButton btnPesquisar;
    private final Dimension telaComp = Toolkit.getDefaultToolkit().getScreenSize();
    private final int[] dimensoes = new int[]{(int)(telaComp.getWidth() - telaCadastro.getWidth())/2,
            (int)(telaComp.getHeight() - telaCadastro.getHeight())/2, telaCadastro.getWidth(), telaCadastro.getHeight()};

    JLabel lbTitulo, lbTipo, lbPreco, lbcifrao, lbCadastroItem;
    JComboBox<String> cbTipo;
    JTextField tfTitulo;
    JTextField tfpreco;
    JButton btSalvar, btLimpar, btCancelar, btConsultar, btApagar;
    JPanel pnlTopo, pnlCentro;
    private final Font fonteLabel = new Font(Font.SERIF, Font.BOLD, 21);
    private Integer cod_filme = 0;
    private Integer cod_item = 0;

    public MenuCadastroItem(){
        this.iniciar();
    }

    public void preencher(Integer cod_item, Integer cod_filme, String titulo, String tipo, double preco){
       this.cod_item = cod_item;
        this.cod_filme = cod_filme;
        this.tfTitulo.setText(titulo);
        this.tfpreco.setText(String.valueOf(preco));
        for(int i = 0; i < cbTipo.getItemCount(); i++){
            if (cbTipo.getItemAt(i).equals(tipo)){
                cbTipo.setSelectedIndex(i);
                return;
            }
        }
    }

    public void preencher(Integer cod_filme, String titulo){
        this.cod_filme = cod_filme;
        this.tfTitulo.setText(titulo);
    }

    public void iniciar(){
        this.getContentPane().setBackground(new Color(149, 149, 149));
        this.setBounds(dimensoes[0], dimensoes[1], dimensoes[2], dimensoes[3]);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        lbCadastroItem = new JLabel("Cadastro de Item", JLabel.CENTER);
        lbCadastroItem.setFont(new Font(Font.SERIF, Font.BOLD, 21));
        pnlTopo = new JPanel(new BorderLayout());
        pnlTopo.add(lbCadastroItem, BorderLayout.CENTER);


        pnlCentro = new JPanel(new GridBagLayout()); // painel central
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Define margens


        lbTitulo = new JLabel("Titulo: ");
        lbTitulo.setFont(fonteLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(lbTitulo, gbc);

        btnPesquisar = new JButton("pesquisar");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        pnlCentro.add(btnPesquisar, gbc);

        lbTipo = new JLabel("Tipo: ");
        lbTipo.setFont(fonteLabel);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(lbTipo, gbc);



        lbPreco = new JLabel("Preco: ");
        lbPreco.setFont(fonteLabel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        pnlCentro.add(lbPreco, gbc);

        tfTitulo = new JTextField("nome do filme", 40);
        gbc.gridx = 1;
        gbc.gridy = 0;
      //  gbc.anchor = GridBagConstraints.WEST;
        pnlCentro.add(tfTitulo, gbc);

        String[] tipos = {"selecione um tipo", "DVD", "Blu-Ray", "fitaK7", "midia digital", "cd"};
        cbTipo = new JComboBox<>(tipos);
        gbc.gridx = 1;
        gbc.gridy = 1;
       // gbc.anchor = GridBagConstraints.WEST;
        pnlCentro.add(cbTipo, gbc);

        tfpreco = new JTextField("12.50", 40);
        gbc.gridx = 1;
        gbc.gridy = 2;
       // gbc.anchor = GridBagConstraints.WEST;
        pnlCentro.add(tfpreco, gbc);

        btSalvar = new JButton("Salvar");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.SOUTH;
        pnlCentro.add(btSalvar, gbc);

        btLimpar = new JButton("Limpar");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.SOUTH;
        pnlCentro.add(btLimpar, gbc);

        btCancelar = new JButton("Cancelar");
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.SOUTH;
        pnlCentro.add(btCancelar, gbc);

        btConsultar = new JButton("Consultar");
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.SOUTH;
        pnlCentro.add(btConsultar, gbc);

        btApagar = new JButton("Apagar");
        gbc.gridx = 4;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.SOUTH;
        pnlCentro.add(btApagar, gbc);



        this.add(pnlTopo, BorderLayout.NORTH);
        this.add(pnlCentro, BorderLayout.CENTER);

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = "N/A";
               if(cbTipo.getSelectedItem()!=null) {
                   tipo = (String) cbTipo.getSelectedItem();
               }

                double preco = Double.parseDouble(tfpreco.getText());
                String titulo = tfTitulo.getText();
                boolean sucesso;
                try {
                    String msgm;
                    ItemController itemController = new ItemController();
                    if(cod_item == 0){
                        sucesso = itemController.salvar(cod_filme, tipo, preco, titulo);
                        msgm = "item salvo com sucesso";
                    }else {
                        sucesso = itemController.alterar(cod_item, tipo, titulo, preco, cod_filme);
                        cod_item = 0;
                        msgm = "item alterado com sucesso";
                    }

                    if (sucesso){
                        JOptionPane.showMessageDialog(null, msgm);
                        limpartela();
                    }else {
                        JOptionPane.showMessageDialog(null, msgm);
                    }
                } catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpartela();
            }
        });

        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancelando...");
            }
        });

        btConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConsultaItem telaConsultaItem = new TelaConsultaItem((JFrame) getMenuCadastroItem());
            }
        });
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaConsultaFilmes consultaFilmes = new TelaConsultaFilmes((JFrame) getMenuCadastroItem());
            }
        });

        btApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemController itemController = new ItemController();
                boolean sucesso;
                try {
                    sucesso = itemController.apagar(cod_item);
                    cod_item = 0;
                    if (sucesso){
                        JOptionPane.showMessageDialog(null, "item deletado com sucesso");
                    }else {
                        JOptionPane.showMessageDialog(null, "Não foi possivel deletar o item");
                    }

                }catch (ExceptionDAO ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.setVisible(true);
    }
    private MenuCadastroItem getMenuCadastroItem(){

        return this;
    }
    public void limpartela(){
        tfTitulo.setText("");
        tfpreco.setText("");
        cbTipo.setSelectedIndex(0);
    }
}
