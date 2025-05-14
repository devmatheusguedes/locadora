package view;

import controller.ItemController;
import controller.LocacaoController;
import dao.ExceptionDAO;
import model.MItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaConsultaLocacao extends JFrame{

    private JLabel icone, jLabel2;
    private JTable jTable;
    private JTextField jTextField;
    private JButton jButtonPesquisar;
    private static final int x = 900;
    private static final int y = TelaSize.SMALL.getHeight();
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Dimension tamanhoDaTela = toolkit.getScreenSize();
    private JFrame menuCadastro;
    private ArrayList<Map<String, Object>> registros;
    public TelaConsultaLocacao(JFrame menuCadastro){
    this.iniciar();
    this.menuCadastro = menuCadastro;
}

public TelaConsultaLocacao(){
        this.iniciar();
}

    private void iniciar(){
        this.setTitle("tela de consulta de Locação");
        this.setBounds((int) (tamanhoDaTela.getWidth() - x)/2, (int)(tamanhoDaTela.getHeight() - y)/2, x, y);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        Icon imageIcon = new ImageIcon("src/main/resources/xicara.png");
        icone = new JLabel("Consulta Locação", imageIcon, SwingConstants.CENTER);
        icone.setHorizontalTextPosition(SwingConstants.RIGHT);
        icone.setBounds(240, 5, 200, 30);

        jButtonPesquisar = new JButton("pesquisar");

        jLabel2 = new JLabel("Informe o nome do cliente: ");
        jLabel2.setBounds(30, 70, 200, 30);

        jTextField = new JTextField();
        jTextField.setBounds(250, 70, 300, 30);

        jButtonPesquisar.setBounds(560, 70, 110, 30);

        String[] nomeDaColuna = {"id_locação", "Cliente", "Filme", "Tipo", "Preço", "Data Aluguel",
        "Data Devolução", "Status de Compra"};
        DefaultTableModel modelo = new DefaultTableModel(nomeDaColuna, 0);
        jTable = new JTable(modelo);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane painelTabela = new JScrollPane(jTable);
        painelTabela.setBounds((x-800)/2, 120, 800, 300);


        jButtonPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome_cliente = jTextField.getText();
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                model.setRowCount(0);
                LocacaoController locacaoController = new LocacaoController();
                try {
                    registros = locacaoController.listar(nome_cliente);

                    for (Map<String, Object> registro : registros) {
                        model.addRow(new Object[]{
                                registro.get("locacao_id"),
                                registro.get("cliente_nome"),
                                registro.get("item_titulo"),
                                registro.get("item_tipo"),
                                registro.get("item_preco"),
                                registro.get("locacao_dtlocacao"),
                                registro.get("locacao_devolucao"),
                                registro.get("locaao_status")
                        });
                    }
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
              if (e.getClickCount() == 2) {
                  int row = jTable.getSelectedRow();
                  if(row != -1) {
                      Integer id_locacao = (Integer) jTable.getModel().getValueAt(row, 0);
                      Integer id_cliente = (Integer) registros.get(row).get("cliente_id");
                      Integer id_item = (Integer) registros.get(row).get("item_id");
                      String nome = (String) jTable.getModel().getValueAt(row, 1);
                      String titulo = (String) jTable.getModel().getValueAt(row, 2);
                      String tipo = (String) jTable.getModel().getValueAt(row, 3);
                      String preco = (String) jTable.getModel().getValueAt(row, 4);
                      Date data_aluguel = (Date) jTable.getModel().getValueAt(row, 5);
                      Date data_devolucao = (Date) jTable.getModel().getValueAt(row, 6);
                      String status = (String) jTable.getModel().getValueAt(row, 7);
                      AlugarView alugarView = (AlugarView) menuCadastro;
                      alugarView.setId_cliente(id_cliente);
                      alugarView.setId_item(id_item);
                      alugarView.setJtCliente(nome);
                      alugarView.setJtFilme(titulo);
                      alugarView.setJtTipoDeMidia(tipo);
                      alugarView.setJtPreco(preco);
                      alugarView.setData_aluguel(data_aluguel);
                      alugarView.setData_devolucao(data_devolucao);
                      alugarView.setStatus(status);
                      fecharJanela(alugarView);
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

    private void fecharJanela(AlugarView menuCadastro){
        this.setVisible(false);
        menuCadastro.setVisible(true);
    }


}
