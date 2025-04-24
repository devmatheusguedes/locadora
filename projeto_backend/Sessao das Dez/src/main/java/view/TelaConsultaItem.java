package view;

import controller.FilmeController;
import controller.ItemController;
import dao.ExceptionDAO;
import model.MFilme;
import model.MItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaConsultaItem extends JFrame{

    private JLabel icone, jLabel2;
    private JTable jTable;
    private JTextField jTextField;
    private JButton jButtonPesquisar;
    private static final int x = 690;
    private static final int y = 490;
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Dimension tamanhoDaTela = toolkit.getScreenSize();
    private JFrame menuCadastro;



    public TelaConsultaItem(){
        super("tela de consulta de Item");
        this.iniciar();
    }
    public TelaConsultaItem(JFrame menuCadastro){
        super("tela de consulta de Item");
        this.iniciar();
        this.menuCadastro = menuCadastro;
    }

    private void iniciar(){
        this.setBounds((int) (tamanhoDaTela.getWidth() - x)/2, (int)(tamanhoDaTela.getHeight() - y)/2, x, y);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        Icon imageIcon = new ImageIcon("src/main/resources/xicara.png");
        icone = new JLabel("Consulta Itens", imageIcon, SwingConstants.CENTER);
        icone.setHorizontalTextPosition(SwingConstants.RIGHT);
        icone.setBounds(240, 5, 200, 30);

        jButtonPesquisar = new JButton("pesquisar");

        jLabel2 = new JLabel("Informe o titulo do Item: ");
        jLabel2.setBounds(30, 70, 200, 30);

        jTextField = new JTextField();
        jTextField.setBounds(250, 70, 300, 30);

        jButtonPesquisar.setBounds(560, 70, 110, 30);

        String[] nomeDaColuna = {"código Item","Código Filme", "Tipo", "Preço", "Titulo"};
        DefaultTableModel modelo = new DefaultTableModel(nomeDaColuna, 0);
        jTable = new JTable(modelo);
        JScrollPane painelTabela = new JScrollPane(jTable);
        painelTabela.setBounds(30, 120, 640, 100);


        jButtonPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = jTextField.getText();
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                ItemController itemController = new ItemController();
                try {
                    ArrayList<MItem> items = itemController.listarItens(titulo);

                    items.forEach((MItem item) -> {
                        model.addRow(new Object[] {
                                item.getCod_item(),
                                item.getFilme().getCodFilme(),
                                item.getTipo(),
                                item.getPreco(),
                                item.getFilme().getTitulo()
                        });
                    });
                    jTable.setModel(model);
                } catch (ExceptionDAO exception) {
                    Logger.getLogger(TelaConsultaFilmes.class.getName()).log(Level.SEVERE,
                            "problema ao listar filme na tela de consulta", exception);
                    exception.printStackTrace();

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
            //titulo, genero, sinopse, duracaoitem
            // getCod_item(),
//                                item.getFilme().getCodFilme(),
//                                item.getTipo(),
//                                item.getPreco(),
//                                item.getTitulo(){
          if (e.getClickCount() == 2) {
              int row = jTable.getSelectedRow();
             if(row != -1) {
                 Integer cod_item = (Integer) jTable.getModel().getValueAt(row, 0);
                 Integer cod_filme = (Integer) jTable.getModel().getValueAt(row, 1);
                 String tipo = (String) jTable.getModel().getValueAt(row, 2);
                 double preco = (double) jTable.getModel().getValueAt(row, 3);
                 String titulo = (String) jTable.getModel().getValueAt(row, 4);
                 preencherMenuCadastroFilme(cod_item, cod_filme, tipo, preco, titulo);
             }else {
                 String msgm = "nehuma linha selecionada";
                 JOptionPane.showMessageDialog(null, msgm);
             }


          }
                                          }
                                      }
        );




        this.setVisible(true);

    }

    private void preencherMenuCadastroFilme(Integer cod_item, Integer cod_filme, String tipo, double preco, String titulo) {
            MenuCadastroItem menuCadastroItem = (MenuCadastroItem) this.menuCadastro;
            menuCadastroItem.preencher(cod_item, cod_filme, titulo, tipo, preco);
            menuCadastroItem.setVisible(true);

        this.dispose();
    }

    public static void main(String[] args) {
        TelaConsultaItem item = new TelaConsultaItem();
    }

}
