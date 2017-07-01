
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
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
public class PainelDeDesenho extends javax.swing.JPanel {

    public static boolean exibirMalha = false;
    public static boolean pintaQuadradoOrigem = false;
    public static boolean pintaQuadradoDestino = false;
    public static boolean desenhaRota = false;
    public static int quadradoIDOrigem = 0;
    public static int quadradoIDDestino = 0;
    public static BotaoSelecionado botaoSelecionado = BotaoSelecionado.NENHUM;
    public static List<Integer> valoresMalha = new ArrayList<>();
    public static Grafo novoGrafo;
    public static int []antecessores = null;
    
    private void contabilizaValoresMalha()
    {
        for ( int i = 2; i <= 500; i++ )
        {
            if ( 500 % i == 0 )
                valoresMalha.add(i);
        }
    }
    
    /**
     * Creates new form NewJPanel
     */
    public PainelDeDesenho() 
    {   
        initComponents();
        contabilizaValoresMalha();
        novoGrafo = new Grafo();
        novoGrafo.geraGrafoGrafico( 500, 25 );
        antecessores = new int[ ( 500 / 25 ) * ( 500 / 25 ) ];       
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        if ( pintaQuadradoOrigem )
        {
            g.setColor(Color.GREEN);
            int x = (quadradoIDOrigem % 20) * 25;
            int y = (quadradoIDOrigem / 20) * 25;
            g.fillRect( x, y, 25, 25);
        }
        
        if ( pintaQuadradoDestino )
        {
            g.setColor(Color.BLUE);
            int x = (quadradoIDDestino % 20) * 25;
            int y = (quadradoIDDestino / 20) * 25;
            g.fillRect( x, y, 25, 25);
        }
        
        if ( desenhaRota && antecessores != null )
        {
            g.setColor( Color.YELLOW );
            int verticeEscolhido = antecessores[ quadradoIDDestino ];
            while ( verticeEscolhido != quadradoIDOrigem )
            {
                int x = (verticeEscolhido % 20) * 25;
                int y = (verticeEscolhido / 20) * 25;
                g.fillRect( x, y, 25, 25);
                verticeEscolhido = antecessores[ verticeEscolhido ];
            }
        }
        
        g.setColor(Color.RED);
        if ( exibirMalha )
        {
            for ( int i = 0; i <= 500; i = i + 25 )
                g.drawLine( i,  0, i, 500 );
        
            for ( int i = 0; i <= 500; i = i + 25 )
                g.drawLine( 0, i, 500, i );
        }
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(java.awt.Color.black);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
