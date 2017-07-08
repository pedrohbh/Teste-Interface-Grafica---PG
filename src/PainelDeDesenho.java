
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public static boolean pintaParede = false;
    public static boolean desenhaRota = false;
    public static int quadradoIDOrigem = 0;
    public static int quadradoIDDestino = 0;
    public static int tamanhoDaMalha = 500;
    public static int valorDivisor = 20;
    public static int intervalo = 0;
    public static BotaoSelecionado botaoSelecionado = BotaoSelecionado.NENHUM;
    public static List<Integer> valoresMalha = new ArrayList<>();
    public static Set<Integer> valoresParede = new HashSet<>();
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
        intervalo = tamanhoDaMalha / valorDivisor;
        initComponents();
        contabilizaValoresMalha();
        novoGrafo = new Grafo();
        novoGrafo.geraGrafoGrafico( tamanhoDaMalha, intervalo );
        antecessores = new int[ valorDivisor * valorDivisor ];       
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        if ( pintaQuadradoOrigem )
        {
            g.setColor(Color.GREEN);
            int x = (quadradoIDOrigem % valorDivisor) * intervalo;
            int y = (quadradoIDOrigem / valorDivisor) * intervalo;
            g.fillRect( x, y, intervalo, intervalo);
        }
        
        if ( pintaQuadradoDestino )
        {
            g.setColor(Color.BLUE);
            int x = (quadradoIDDestino % valorDivisor) * intervalo;
            int y = (quadradoIDDestino / valorDivisor) * intervalo;
            g.fillRect( x, y, intervalo, intervalo);
        }
        
        if ( pintaParede )
        {
            g.setColor(Color.LIGHT_GRAY);
            for ( Integer e: valoresParede )
            {
                int x = (e % valorDivisor) * intervalo;
                int y = (e / valorDivisor) * intervalo;
                g.fillRect( x, y, intervalo, intervalo);
            }
        }
        
        if ( desenhaRota && antecessores != null )
        {
            g.setColor( Color.YELLOW );
            int verticeEscolhido = antecessores[ quadradoIDDestino ];
            while ( verticeEscolhido != quadradoIDOrigem )
            {
                int x = (verticeEscolhido % valorDivisor) * intervalo;
                int y = (verticeEscolhido / valorDivisor) * intervalo;
                g.fillRect( x, y, intervalo, intervalo);
                verticeEscolhido = antecessores[ verticeEscolhido ];
            }
        }
        
        g.setColor(Color.RED);
        if ( exibirMalha )
        {
            for ( int i = 0; i <= tamanhoDaMalha; i = i + intervalo )
                g.drawLine( i,  0, i, tamanhoDaMalha );
        
            for ( int i = 0; i <= tamanhoDaMalha; i = i + intervalo )
                g.drawLine( 0, i, tamanhoDaMalha, i );
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
