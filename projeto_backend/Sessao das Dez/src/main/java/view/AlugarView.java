package view;

import javax.swing.*;

public class AlugarView extends JFrame {
    private JButton btnPesquisaCliente, btnBuscarFilme, btnAlugar;
    private JLabel jlCliente, jlNomeCliente, jlFilme,
            jlTituloFilme, jllocacao, jlDataLocacao,
            jlMidia, jlTipoMidia;

    public AlugarView(){
        this.iniciar();
    }

    private void iniciar(){
        this.setLayout(null);


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
