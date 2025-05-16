package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MenuPrincial extends JFrame {
    private static final int largura = TelaSize.SMALL.getWidth();
    private static final int altura = TelaSize.SMALL.getHeight();
    private static final Dimension eixos = Toolkit.getDefaultToolkit().getScreenSize();//



    public MenuPrincial() {
        this.iniciar();
    }

    public static void main(String[] args){
        MenuPrincial menuPrincial = new MenuPrincial();
    }

    private void iniciar() {
        this.setLayout(new BorderLayout());
        this.setBounds((int) (eixos.getWidth() - largura) / 2,
                (int) (eixos.getHeight() - altura) / 2, largura, altura);

        JLayeredPane jLayeredPane = new JLayeredPane();
        jLayeredPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

        BufferedImage image = null;
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream("/iconImagem.jpg");
            if (is != null) {
                image = ImageIO.read(is);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIconImage(image);

        BufferedImage img = null;
        Image dImage = null;
        is = null;



        try {
            is = getClass().getResourceAsStream("/IA_img_background.png");
            if (is != null) {
                img = ImageIO.read(is);
            }
            if (img != null) {
                dImage = img.getScaledInstance(this.getWidth() , this.getHeight(), Image.SCALE_SMOOTH);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        JLabel background = new JLabel(new ImageIcon(dImage));
        background.setSize(this.getWidth(), this.getHeight());
        background.setHorizontalAlignment(JLabel.CENTER);
        this.add(background, BorderLayout.CENTER);


        JMenuBar barraMenu = new JMenuBar();


        JMenu menuCadastro = new JMenu("Cadastro");
        JMenu menuLocacao = new JMenu("Locação");

        menuCadastro.setSize(100, 50);

        JMenuItem miAlugar = new JMenuItem("Alugar");
        JMenuItem miAtor = new JMenuItem("Ator");
        JMenuItem miCliente = new JMenuItem("Cliente");
        JMenuItem miFilme = new JMenuItem("Filme");
        JMenuItem miSair = new JMenuItem("Sair");
        JMenuItem miConsultar = new JMenuItem("Consultar");
        JMenuItem miItem = new JMenuItem("Item");


        menuCadastro.add(miAtor);
        menuCadastro.add(miItem);
        menuCadastro.add(miFilme);
        menuCadastro.add(miCliente);

        menuLocacao.add(miAlugar);
        menuLocacao.add(miConsultar);

        barraMenu.add(menuCadastro);
        barraMenu.add(menuLocacao);
        barraMenu.add(miSair);


        this.setJMenuBar(barraMenu);

        miSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        miItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuCadastroItem item = new MenuCadastroItem();
            }
        });

        miConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlugarView alugarView = new AlugarView();
                TelaConsultaLocacao locacao = new TelaConsultaLocacao((JFrame) alugarView);
            }
        });

        miCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MenuCadastroCliente cliente = new MenuCadastroCliente();
            }
        });

        miAtor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuCadastroAtor ator = new MenuCadastroAtor();
            }
        });

        miFilme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuCadastroFilme filme = new MenuCadastroFilme();

            }
        });

        miAlugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlugarView alugarView = new AlugarView();
            }
        });



        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

    }



    }

