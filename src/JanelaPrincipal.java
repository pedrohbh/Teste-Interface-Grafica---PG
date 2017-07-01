
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import pedro.pg.grafo.Grafo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author administrador
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal() 
    {
        super("Grafo Gráfico");
        initComponents();
        
        MouseHandler mouseHandler = new MouseHandler();
        NewJPanel.addMouseListener(mouseHandler);
        NewJPanel.addMouseMotionListener(mouseHandler);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoesVertice = new javax.swing.ButtonGroup();
        NewJPanel = new PainelDeDesenho();
        botaoMalha = new javax.swing.JToggleButton();
        tamanhoDaMalhaDescritor = new javax.swing.JLabel();
        tamanhoDaMalhaAjustor = new javax.swing.JSpinner();
        debugLabel = new javax.swing.JLabel();
        botaoOrigem = new javax.swing.JRadioButton();
        botaoDestino = new javax.swing.JRadioButton();
        botaoNenhum = new javax.swing.JRadioButton();
        botaoCalculaCaminho = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout NewJPanelLayout = new javax.swing.GroupLayout(NewJPanel);
        NewJPanel.setLayout(NewJPanelLayout);
        NewJPanelLayout.setHorizontalGroup(
            NewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        NewJPanelLayout.setVerticalGroup(
            NewJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        botaoMalha.setText("Exibir Malha");
        botaoMalha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoMalhaActionPerformed(evt);
            }
        });

        tamanhoDaMalhaDescritor.setText("Tamanho da Malha:");

        tamanhoDaMalhaAjustor.setModel(new javax.swing.SpinnerNumberModel());
        tamanhoDaMalhaAjustor.setEditor(new javax.swing.JSpinner.NumberEditor(tamanhoDaMalhaAjustor, ""));
        tamanhoDaMalhaAjustor.setValue(2);

        grupoBotoesVertice.add(botaoOrigem);
        botaoOrigem.setText("Origem");
        botaoOrigem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoOrigemActionPerformed(evt);
            }
        });

        grupoBotoesVertice.add(botaoDestino);
        botaoDestino.setText("Destino");
        botaoDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDestinoActionPerformed(evt);
            }
        });

        grupoBotoesVertice.add(botaoNenhum);
        botaoNenhum.setText("Nenhum");
        botaoNenhum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNenhumActionPerformed(evt);
            }
        });

        botaoCalculaCaminho.setText("Calcula Caminho");
        botaoCalculaCaminho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCalculaCaminhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NewJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(debugLabel))
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoMalha)
                    .addComponent(botaoCalculaCaminho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoNenhum)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoOrigem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoDestino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tamanhoDaMalhaDescritor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tamanhoDaMalhaAjustor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCalculaCaminho, botaoMalha});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(NewJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoMalha)
                        .addComponent(botaoOrigem)
                        .addComponent(botaoDestino))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tamanhoDaMalhaDescritor)
                        .addComponent(tamanhoDaMalhaAjustor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoNenhum)
                    .addComponent(botaoCalculaCaminho))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(debugLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoMalhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoMalhaActionPerformed
        if ( PainelDeDesenho.exibirMalha == false )
        {
            PainelDeDesenho.exibirMalha = true;
            repaint();
        }
        
        else
        {
            PainelDeDesenho.exibirMalha = false;
            repaint();
        }
    }//GEN-LAST:event_botaoMalhaActionPerformed

    private void botaoOrigemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOrigemActionPerformed
        PainelDeDesenho.botaoSelecionado = BotaoSelecionado.ORIGEM;
    }//GEN-LAST:event_botaoOrigemActionPerformed

    private void botaoDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDestinoActionPerformed
        PainelDeDesenho.botaoSelecionado = BotaoSelecionado.DESTINO;
    }//GEN-LAST:event_botaoDestinoActionPerformed

    private void botaoNenhumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNenhumActionPerformed
        PainelDeDesenho.botaoSelecionado = BotaoSelecionado.NENHUM;
    }//GEN-LAST:event_botaoNenhumActionPerformed

    private void botaoCalculaCaminhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCalculaCaminhoActionPerformed
        PainelDeDesenho.antecessores = PainelDeDesenho.novoGrafo.dijkstraHeapBinario( PainelDeDesenho.quadradoIDOrigem );
        PainelDeDesenho.desenhaRota = true;
        repaint();
    }//GEN-LAST:event_botaoCalculaCaminhoActionPerformed

    private class MouseHandler implements MouseMotionListener, MouseListener
    {

        @Override
        public void mouseDragged(MouseEvent e) 
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseMoved(MouseEvent e) 
        {
            debugLabel.setText(String.format("Mouse movido nas posições (%d,%d).", e.getX(), e.getY() ) );
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseClicked(MouseEvent e) 
        {
            
            debugLabel.setText(String.format("Mouse clicado nas posições (%d,%d).", e.getX(), e.getY() ) );
            if ( e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3 )
            {
                PainelDeDesenho.pintaQuadradoOrigem = false;
                PainelDeDesenho.pintaQuadradoDestino = false;
            }
            else
            {
                if ( PainelDeDesenho.botaoSelecionado == BotaoSelecionado.ORIGEM )
                {
                    PainelDeDesenho.quadradoIDOrigem = (e.getX() / 25) + ( e.getY() / 25 ) * 20;
                    PainelDeDesenho.pintaQuadradoOrigem = true;
                    if ( PainelDeDesenho.desenhaRota )
                        PainelDeDesenho.desenhaRota = false;
                }
                else if ( PainelDeDesenho.botaoSelecionado == BotaoSelecionado.DESTINO )
                {
                    PainelDeDesenho.quadradoIDDestino = (e.getX() / 25) + ( e.getY() / 25 ) * 20;
                    PainelDeDesenho.pintaQuadradoDestino = true;
                    if ( PainelDeDesenho.desenhaRota )
                        PainelDeDesenho.desenhaRota = false;
                }
            }
            repaint();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) 
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) 
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) 
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) 
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                /*if ( "GTK+".equals( info.getName() ) )
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }*/
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NewJPanel;
    private javax.swing.JButton botaoCalculaCaminho;
    private javax.swing.JRadioButton botaoDestino;
    private javax.swing.JToggleButton botaoMalha;
    private javax.swing.JRadioButton botaoNenhum;
    private javax.swing.JRadioButton botaoOrigem;
    private javax.swing.JLabel debugLabel;
    private javax.swing.ButtonGroup grupoBotoesVertice;
    private javax.swing.JSpinner tamanhoDaMalhaAjustor;
    private javax.swing.JLabel tamanhoDaMalhaDescritor;
    // End of variables declaration//GEN-END:variables
}
