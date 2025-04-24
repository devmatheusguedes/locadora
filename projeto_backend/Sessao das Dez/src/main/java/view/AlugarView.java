package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlugarView extends JFrame {
    private JButton btnPesquisaCliente, btnBuscarFilmeItem, btnAlugar;
    private JTextField jtCliente, jtFilme, jtTipoDeMidia, jtPreco;
    private JLabel jlCliente, jlFilme,
            jlMidia, jlPreco;
    private Dimension eixos = Toolkit.getDefaultToolkit().getScreenSize();
    private int largura = TelaSize.SMALL.getWidth(), altura = TelaSize.SMALL.getHeight();

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
        jtCliente.setBounds(150, 20, 200, 30);
        jtFilme.setBounds(150, 60, 200, 30);
        jtTipoDeMidia = new JTextField();
        jtTipoDeMidia.setBounds(150, 100, 100, 30);
        jtPreco.setBounds(150, 140, 100, 30);




        btnPesquisaCliente.setBounds(360, 20, 120, 30);
        btnBuscarFilmeItem.setBounds(360, 60, 120, 30);
        btnAlugar.setBounds(70, 300, 120, 30);





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
        this.add(btnAlugar);

        this.buttonActionaPesquisaCliente();
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

    public void preencherCampoCliente(String nome){
        this.jtCliente.setText(nome);
    }
    public AlugarView getAlugarView(){
        return this;
    }
}
