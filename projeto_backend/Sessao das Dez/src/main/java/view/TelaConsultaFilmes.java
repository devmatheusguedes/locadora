package view;

import controller.FilmeController;
import dao.ExceptionDAO;
import model.MFilme;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaConsultaFilmes extends JFrame {
    private JLabel icone, jLabel2;
    private JTable jTable;
    private JTextField jTextField;
    private JButton jButtonPesquisar;
    private static final int x = 690;
    private static final int y = 490;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Dimension tamanhoDaTela = toolkit.getScreenSize();
    private JFrame menuCadastro;

    public TelaConsultaFilmes(JFrame menuCadastro){
        super("tela de consulta de filmes");
        this.iniciar();
        this.menuCadastro = menuCadastro;
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

        jLabel2 = new JLabel("Informe o titulo do filme: ");
        jLabel2.setBounds(30, 70, 200, 30);

        jTextField = new JTextField();
        jTextField.setBounds(250, 70, 300, 30);

        jButtonPesquisar.setBounds(560, 70, 110, 30);

        String[] nomeDaColuna = {"código", "titulo", "gênero", "sinopse", "duração"};
        DefaultTableModel modelo = new DefaultTableModel(nomeDaColuna, 0);
        jTable = new JTable(modelo);
        JScrollPane painelTabela = new JScrollPane(jTable);
        painelTabela.setBounds(30, 120, 640, 100);


        jButtonPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = jTextField.getText();
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                FilmeController filmeController = new FilmeController();
                try {
                    ArrayList<MFilme> filmes = filmeController.listarFilme(nome);
                    if (filmes.isEmpty()){
                        JOptionPane.showMessageDialog(null, "banco filme vazio");
                    }

                    filmes.forEach((MFilme filme) -> {
                        model.addRow(new Object[] {
                                filme.getCodFilme(),
                                filme.getTitulo(),
                                filme.getGenero(),
                                filme.getSinopse(),
                                filme.getDuracao()});
                    });
                    jTable.setModel(model);
                } catch (ExceptionDAO exception) {
                    Logger.getLogger(TelaConsultaFilmes.class.getName()).log(Level.SEVERE, null, exception);
                    exception.printStackTrace();
                    System.out.println("problema aqui");
                }
            }
        });



        this.add(icone);
        this.add(jLabel2);
        this.add(jTextField);
        this.add(jButtonPesquisar);
        this.add(painelTabela);

        painelTabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = jTable.getSelectedRow();
                    if(row != -1) {
                        //titulo, genero, sinopse, duracao
                        Integer cod_filme = (Integer) jTable.getModel().getValueAt(row, 0);
                        String titulo = (String) jTable.getModel().getValueAt(row, 1);
                        String genero = (String) jTable.getModel().getValueAt(row, 2);
                        String sinopse = (String) jTable.getModel().getValueAt(row, 3);
                        Integer duracao = (Integer) jTable.getModel().getValueAt(row, 4);
                        preencherMenuCadastroFilme(cod_filme, titulo, genero, sinopse, duracao);
                    }

                }
            }
        }
         );




        this.setVisible(true);

    }

    private void preencherMenuCadastroFilme(Integer cod_filme, String titulo, String genero,
                                            String sinopse, Integer duracao) {

        String tipoTela = this.menuCadastro.getClass().getSimpleName();

        if(tipoTela.equals("MenuCadastroFilme")) {
            MenuCadastroFilme menuCadastroFilme = (MenuCadastroFilme) this.menuCadastro;
            menuCadastroFilme.preencher(cod_filme, titulo, genero, sinopse, duracao);
            menuCadastroFilme.setVisible(true);
        }else {
            if(tipoTela.equals("MenuCadastroItem")) {
                MenuCadastroItem menuCadastroItem = (MenuCadastroItem) this.menuCadastro;
                menuCadastroItem.preencher(cod_filme, titulo);
                menuCadastroItem.setVisible(true);
            } else{
                MenuCadastroAtor menuCadastroAtor = (MenuCadastroAtor) this.menuCadastro;
                menuCadastroAtor.preencherFilme(cod_filme, titulo);
            }
        }
        this.dispose();
    }





}


