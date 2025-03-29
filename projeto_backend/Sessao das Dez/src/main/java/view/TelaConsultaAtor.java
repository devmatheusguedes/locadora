package view;

import controller.AtorController;
import dao.AtorDAO;
import dao.ExceptionDAO;
import model.MAtor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaConsultaAtor extends JFrame{
    private JLabel icone, jLabel2;
    private JTable jTable;
    private JTextField jTextField;
    private JButton jButtonPesquisar;
    private static final int x = 690;
    private static final int y = 490;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Dimension tamanhoDaTela = toolkit.getScreenSize();
    private MenuCadastroAtor menuCadastroAtor;

    public TelaConsultaAtor(MenuCadastroAtor menuCadastroAtor){
        super("tela de consulta do ator");
       this.iniciar();
       this.menuCadastroAtor = menuCadastroAtor;
       this.menuCadastroAtor.dispose();
    }



    private void iniciar(){
        this.setBounds((int) (tamanhoDaTela.getWidth() - x)/2, (int)(tamanhoDaTela.getHeight() - y)/2, x, y);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        Icon imageIcon = new ImageIcon("src/main/resources/xicara.png");
        icone = new JLabel("Consulta Ator", imageIcon, SwingConstants.CENTER);
        icone.setHorizontalTextPosition(SwingConstants.RIGHT);
        icone.setBounds(240, 5, 200, 30);

        jButtonPesquisar = new JButton("pesquisar");

        jLabel2 = new JLabel("Informe o nome do autor: ");
        jLabel2.setBounds(30, 70, 200, 30);

        jTextField = new JTextField();
        jTextField.setBounds(250, 70, 300, 30);

        jButtonPesquisar.setBounds(560, 70, 110, 30);

        String[] nomeDaColuna = {"código", "nome", "nacionalidade"};
        DefaultTableModel modelo = new DefaultTableModel(nomeDaColuna, 0);
        jTable = new JTable(modelo);
        JScrollPane painelTabela = new JScrollPane(jTable);
        painelTabela.setBounds(30, 120, 640, 100);


        jButtonPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = jTextField.getText();
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                AtorController atorController = new AtorController();
                try {
                    ArrayList<MAtor> atores = atorController.listarAtores(nome);

                    atores.forEach((MAtor ator) ->{
                        model.addRow(new Object[]{
                                ator.getCodAtor(),
                                ator.getNome(),
                                ator.getNacionalidade()
                        });
                    });
                    jTable.setModel(model);
                } catch (ExceptionDAO ex) {
                    Logger.getLogger(TelaConsultaAtor.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }

            }
        });

        painelTabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    // cod_ator, nome, nacionalidade
                    int row = jTable.getSelectedRow();
                    if(row != -1){
                    Integer cod_ator = (Integer) jTable.getModel().getValueAt(row, 0);
                    String nome = (String) jTable.getModel().getValueAt(row, 1);
                    String nacionalidade = (String) jTable.getModel().getValueAt(row, 2);
                    preencharCadastroAtor(cod_ator, nome, nacionalidade);

                }}
            }
        });


        this.add(icone);
        this.add(jLabel2);
        this.add(jTextField);
        this.add(jButtonPesquisar);
        this.add(painelTabela);
        this.fecharJanela();






        this.setVisible(true);

    }
    private void fecharJanela(){
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                abrirMenuCadastroAtor();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public void abrirMenuCadastroAtor(){
        this.menuCadastroAtor.setVisible(true);
    }

    public void preencharCadastroAtor(Integer cod_ator, String nome, String nacionalidade){
        this.menuCadastroAtor.preencher(cod_ator, nome, nacionalidade);
        this.menuCadastroAtor.setVisible(true);
        this.dispose();
    }


}
