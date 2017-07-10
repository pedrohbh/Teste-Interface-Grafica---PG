/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedro.pg.grafo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import pedro.pg.estruturadedados.FibonacciHeap;
import pedro.pg.estruturadedados.HeapBinario;

/**
 *
 * @author administrador
 */
public class Grafo 
{
    private int numeroVertices;
    private int numeroArestas;
    private Vertice []verticesGrafo;
    private long []cordenadasX;
    private long []cordenadasY;
    private static Scanner input;
    private static boolean grafoFoiMudado = false;
    
 
    public Grafo()
    {
        numeroArestas = 0;
        numeroVertices = 0;
        verticesGrafo = null;
        cordenadasX = null;
        cordenadasY = null;
    }
    
    public void buscaEmLargutaPorVertice( int idVertice )
    {
        List<Integer> verticesChegaveis = new LinkedList<>();
        Queue<Integer> fila = new LinkedList<>();
        Cores []cor = new Cores[ getNumeroVertices() ];
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            cor[ i ] = Cores.WHITE;
        }
        
        cor[ idVertice ] = Cores.GRAY;
        fila.add(idVertice);
        while ( !fila.isEmpty() )
        {
            int verticeAtual = fila.remove();
            for ( Aresta a: verticesGrafo[ verticeAtual ].arestasAdjacentes )
            {
                if ( cor[ a.idVerticeDestino ] == Cores.WHITE )
                {
                    cor[ a.idVerticeDestino ] = Cores.GRAY;
                    fila.add(  a.idVerticeDestino );
                }
            }
            
            cor[ verticeAtual ] = Cores.BLACK;
            verticesChegaveis.add( verticeAtual );
        }
        System.out.println("Tamanho Total: " + verticesChegaveis.size());
        for ( int i = 0; i < verticesChegaveis.size(); i++ )
        {
            if ( (i+1) % 5 == 0 )
                System.out.printf("%d%n", verticesChegaveis.get( i ));
            else
                System.out.printf("%d ", verticesChegaveis.get( i ) );
        }
        
    }
    
    private void dsfVisit( int id, Cores []cor, List<Integer> verticesChegaveis )
    {
        cor[ id ] = Cores.GRAY;
        //time = time + 1;
        for ( Aresta a: verticesGrafo[ id ].arestasAdjacentes )
        {
            if ( cor[ a.idVerticeDestino ] == Cores.WHITE )
            {
                //pi[ a.idVerticeDestino ] = a.idVeticeOrigem;
                //verticesChegaveis.add(id)
                dsfVisit( a.idVerticeDestino, cor, verticesChegaveis);
            }
        }
        //time = time + 1;
        cor[ id ] = Cores.BLACK;
        verticesChegaveis.add(id);
        /*if ( time % 5 == 0 )
        {
            System.out.printf("%d%n", id);
        }
        else
        {
            System.out.printf("%d ", id );
        }*/
        
        
        
    }
    
    public void buscaEmProfundidadePorVertice( int idVertice )
    {
        Cores []cor = new Cores[ getNumeroVertices() ];
        List<Integer> verticesChegaveis = new LinkedList<>();
        int []pi = new int[ getNumeroVertices() ];
        int time;
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            cor[ i ] = Cores.WHITE;
            pi[ i ] = -1;            
        }
        
        time = 0;
        System.out.println("Vertices chegáveis partindo de " +  idVertice );
        dsfVisit(idVertice, cor, verticesChegaveis);
        for ( int i = 0; i < verticesChegaveis.size(); i++ )
        {
            if ( (i+1) % 5 == 0 )
                System.out.printf("%d%n", verticesChegaveis.get( i ));
            else
                System.out.printf("%d ", verticesChegaveis.get( i ) );
        }
    }
    
    public void atualizaGrafoGerado( Set<Integer> chaves )
    {
        for ( Integer e : chaves )
        {   
            for ( Aresta a: verticesGrafo[ e ].arestasAdjacentes )
            {
                for ( int i = 0; i < verticesGrafo[ a.idVerticeDestino ].arestasAdjacentes.size(); i++ )
                {
                    if ( verticesGrafo[ a.idVerticeDestino ].arestasAdjacentes.get( i ).idVerticeDestino == a.idVeticeOrigem )
                    {
                        verticesGrafo[ a.idVerticeDestino ].arestasAdjacentes.remove( i );
                        break;
                    }
                }
            }
        }
    }
    
    public void geraGrafoGrafico( int tamanhoMalha, int separador )
    {
        int intervalo = tamanhoMalha / separador;
        verticesGrafo = new Vertice[ intervalo * intervalo ];
        numeroVertices = intervalo * intervalo;
        numeroArestas = 0;
        for ( int i = 0; i < intervalo; i++ )
        {            
            for ( int j = 0; j < intervalo; j++ )
            {
                int idVertice = i * intervalo + j; 
                verticesGrafo[ idVertice ] = new Vertice(idVertice);
                verticesGrafo[ idVertice ].idVertice = idVertice;
                verticesGrafo[ idVertice ].arestasAdjacentes = new LinkedList<>();
                if ( i > 0 )
                {
                    verticesGrafo[ idVertice ].arestasAdjacentes.add( new Aresta( idVertice, (i-1) * intervalo + j, 10) );
                    numeroArestas++;
                }
                if ( i < (intervalo-1) )
                {
                    verticesGrafo[ idVertice ].arestasAdjacentes.add( new Aresta( idVertice, (i+1)*intervalo + j, 10 ) );
                    numeroArestas++;
                }
                if ( j > 0 )
                {
                    verticesGrafo[ idVertice ].arestasAdjacentes.add( new Aresta( idVertice, i * intervalo + (j-1), 10) );
                    numeroArestas++;
                }
                if ( j < (intervalo-1) )
                {
                    verticesGrafo[ idVertice ].arestasAdjacentes.add( new Aresta( idVertice, i * intervalo + (j+1), 10) );
                    numeroArestas++;
                }
                // Diagonais
                // Diagonal Superior Esquerda
                if ( i > 0 && j > 0)
                {
                    verticesGrafo[ idVertice ].arestasAdjacentes.add( new Aresta( idVertice, (i-1) * intervalo + (j-1), 14) );
                    numeroArestas++;
                }
                
                // Diagonal Superior Direita
                if ( i > 0 && j < (intervalo-1) )
                {
                    verticesGrafo[ idVertice ].arestasAdjacentes.add( new Aresta( idVertice, (i-1) * intervalo + (j+1), 14) );
                    numeroArestas++;
                }
                
                // Diagonal Inferior Esquerda
                if ( i < (intervalo-1) && j > 0 )
                {
                    verticesGrafo[ idVertice ].arestasAdjacentes.add( new Aresta( idVertice, (i+1) * intervalo + (j-1), 14) );
                    numeroArestas++;
                }
                
                if ( i < (intervalo-1) && j < (intervalo-1) )
                {
                    verticesGrafo[ idVertice ].arestasAdjacentes.add( new Aresta( idVertice, (i+1) * intervalo + (j+1), 14) );
                    numeroArestas++;
                }
                
            }
        }
    }
                
    public void dijkstraHeapFibonacci( int idOrigem )
    {
        int verticesVisitados = getNumeroVertices();
        int []antecessor = new int[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        FibonacciHeap.FibNode []rastreador = new FibonacciHeap.FibNode[ getNumeroVertices() ];
        FibonacciHeap heap = new FibonacciHeap();
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            FibonacciHeap.FibNode novoNodo = heap.criaNodo(i, Integer.MAX_VALUE );
            isDeterminado[ i ] = false;
            antecessor[ i ] = i;
            rastreador[ i ] = novoNodo;
            heap.insert(novoNodo);
        }
        
        
        FibonacciHeap.FibNode nodoAtual = rastreador[ idOrigem ];
        heap.decreaseKey(nodoAtual, 0);
        while ( verticesVisitados > 0 )
        {
            nodoAtual = heap.extractMin();
            int verticeAtual = nodoAtual.getIdVertice();
            isDeterminado[ verticeAtual ] = true;
            for ( Aresta a: verticesGrafo[ verticeAtual ].getArestasAdjacentes() )
            {
                int verticeDestino = a.idVerticeDestino;
                FibonacciHeap.FibNode nodoDestino = rastreador[ verticeDestino ];
                if ( isDeterminado[ verticeDestino ] == true )
                    continue;
                
                if ( nodoDestino.getKey() > ( a.peso + nodoAtual.getKey() ) )
                {
                    if ( ( a.peso + nodoAtual.getKey() ) >= 0 )
                    {
                        heap.decreaseKey(nodoDestino, (a.peso + nodoAtual.getKey() ) );
                        antecessor[ verticeDestino ] = verticeAtual;
                    }
                }               
            }
            verticesVisitados--;
            
            
        }
        
        
        /*System.out.println("Imprimindo antecessores de Heap de Fibonacci");
        for ( int i = 0; i < antecessor.length; i++ )
        {
            System.out.printf("Antecessor( %d ): %d%n", i, antecessor[ i ] );
        }*/
        
        
        
    }
    
    private long computeKeyARA( int idVertice, long []g, long []distanciaHeuristica, double episolon )
    {
        return g[ idVertice ] + Math.round( distanciaHeuristica[ idVertice ] * episolon );
    }
    
    public void anytimeSearchAEstrela( int idOrigem, int idDestino, double episolon, double fatorDeCorte )
    {
        HeapBinario openHeap = new HeapBinario( getNumeroVertices() );
        HeapBinario.HeapNode []rastreadorOpen = new HeapBinario.HeapNode[ getNumeroVertices() ];
        List<Integer> listaInconsistentes = new LinkedList<>();
        Set<Integer> listaFechado = new HashSet<>();
        long []g = new long[ getNumeroVertices() ];
        long []distanciaHeuristica = new long[ getNumeroVertices() ];
        int []antecessores = new int[ getNumeroVertices() ];
        EstadosVertice []estadosVertices = new EstadosVertice[ getNumeroVertices() ];
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessores[ i ] = i;
            estadosVertices[ i ] = EstadosVertice.NEUTRO;
        }
        
        // 4
        g[ idDestino ] = Long.MAX_VALUE;
        distanciaHeuristica[ idDestino ] = 0;
        
        // 5
        g[ idOrigem ] = 0;
        distanciaHeuristica[ idOrigem ] = calculaDistanciaHeuristicaEuclidiana( idOrigem, idDestino );
        
        
        
        // 6
        rastreadorOpen[ idOrigem ] = openHeap.insertHeap(idOrigem,  computeKeyARA(idOrigem, g, distanciaHeuristica, episolon) );
        estadosVertices[ idOrigem ] = EstadosVertice.ABERTO;
        
        // 7
        computePathARA(idDestino, antecessores, openHeap, rastreadorOpen, g, distanciaHeuristica, estadosVertices, listaFechado, listaInconsistentes, episolon );
        System.out.println("Mostando caminho de ARA para o vértice: " + idDestino + ". eps = " + episolon );
        publicaCaminho(antecessores, idOrigem, idDestino);
        System.out.println("Custo total para o vértice " + idDestino + ": " + calculaDistanciaTotal(antecessores, idOrigem, idDestino) );
        System.out.println("");
        
        while ( episolon > 1 )
        {
            episolon -= fatorDeCorte;
            repassaInconsitentesParaOpenARA(listaInconsistentes, openHeap, rastreadorOpen, g, distanciaHeuristica, estadosVertices, episolon );
            atualizaOpenARA(openHeap, g, distanciaHeuristica, episolon );
            limpaFechadoARA(listaFechado, estadosVertices );
            computePathARA(idDestino, antecessores, openHeap, rastreadorOpen, g, distanciaHeuristica, estadosVertices, listaFechado, listaInconsistentes, episolon );
            System.out.println("Mostando caminho de ARA para o vértice: " + idDestino + ". eps = " + episolon );
            publicaCaminho(antecessores, idOrigem, idDestino);
            System.out.println("Custo total para o vértice " + idDestino + ": " + calculaDistanciaTotal(antecessores, idOrigem, idDestino) );
            System.out.println("");
        }
        
        
    }
    
    private void limpaFechadoARA( Set<Integer> listaFechado, EstadosVertice []estadosVertices )
    {
        for ( Integer e: listaFechado )
        {
            estadosVertices[ e ] = EstadosVertice.LIMBO;
        }
        listaFechado.clear();
    }
    
    private void atualizaOpenAD( HeapBinario openHeap, long []g, long []v, long []distanciaHeuristica, double episolon )
    {
        HeapBinario.HeapNode elemento;
        for ( int i = 0; i <= openHeap.getHeapSize(); i++ )
        {
            elemento = openHeap.getElementoPosicao( i );
            openHeap.decreaseKey( elemento.getIndiceAtual(), computeKeyAD( elemento.getIdVertice(), g, v, distanciaHeuristica, episolon) );
        }
    }
    
    private void atualizaOpenARA( HeapBinario openHeap, long []g, long []distanciaHeuristica, double episolon )
    {
        HeapBinario.HeapNode elemento;
        for ( int i = 0; i <= openHeap.getHeapSize(); i++ )
        {
            elemento = openHeap.getElementoPosicao( i );
            openHeap.decreaseKey( elemento.getIndiceAtual(), computeKeyARA( elemento.getIdVertice(), g, distanciaHeuristica, episolon) );
        }
    }
    
    private void repassaInconsitentesParaOpenARA( List< Integer > listaInconsitentes, HeapBinario openHeap, HeapBinario.HeapNode []rastreadorOpen, long []g, long []distanciaHeuristica, EstadosVertice []estadosVertices, double episolon )
    {
        for ( int i = 0; i < listaInconsitentes.size(); i++ )
        {
            Integer e = listaInconsitentes.remove( i );
            rastreadorOpen[ e ] = openHeap.insertHeap( e,  computeKeyARA( e, g, distanciaHeuristica,  episolon ) );
            estadosVertices[ e ] = EstadosVertice.ABERTO;
        }
    }
    
    private void computePathARA( int idDestino, int[] antecessores, HeapBinario openHeap, HeapBinario.HeapNode []rastreadorOpen, long []g, long []distanciaHeuristica, EstadosVertice []estadosVertices, Set<Integer> listaFechado, List<Integer> listaInconsistente, double episolon )
    {
        // 2
        while( computeKeyARA( idDestino, g, distanciaHeuristica, episolon ) > openHeap.getMin().getKey() )
        {
            // 3
            HeapBinario.HeapNode nodoAtual = openHeap.extractMin();
            int idNodoAtual = nodoAtual.getIdVertice();
            rastreadorOpen[ idNodoAtual ] = null;
            
            // 4
            estadosVertices[ idNodoAtual ] = EstadosVertice.FECHADO;
            listaFechado.add( idNodoAtual );
            
            // 5
            for ( Aresta a: verticesGrafo[ idNodoAtual ].arestasAdjacentes )
            {
                int idAdjacente = a.idVerticeDestino;
                
                // 6
                if ( estadosVertices[ idAdjacente ] == EstadosVertice.NEUTRO )
                {
                    distanciaHeuristica[ idAdjacente ] = calculaDistanciaHeuristicaEuclidiana( idAdjacente, idDestino );
                    g[ idAdjacente ] = Long.MAX_VALUE;
                }
                
                // 8
                long distanciaCalculada = g[ idNodoAtual ] + a.peso;
                if ( g[ idAdjacente ] > distanciaCalculada && distanciaCalculada >= 0 )
                {
                    // 9
                    g[ idAdjacente ] = distanciaCalculada;
                    
                    // 10
                    if ( estadosVertices[ idAdjacente ] != EstadosVertice.FECHADO )
                    {
                        if ( rastreadorOpen[ idAdjacente ] == null )
                            rastreadorOpen[ idAdjacente ] = openHeap.insertHeap( idAdjacente, computeKeyARA( idAdjacente, g, distanciaHeuristica, episolon ) );
                        else
                            openHeap.decreaseKey( rastreadorOpen[ idAdjacente ].getIndiceAtual(), computeKeyARA( idAdjacente, g, distanciaHeuristica, episolon ) );
                        
                        estadosVertices[ idAdjacente ] = EstadosVertice.ABERTO;
                    }
                    else
                    {
                        listaFechado.remove( idAdjacente );
                        listaInconsistente.add( idAdjacente );
                        estadosVertices[ idAdjacente ] = EstadosVertice.INCOSISTENTE;                        
                    }
                    
                    antecessores[ idAdjacente ] = idNodoAtual;
                }
            }
        }
        
    }
    
    public static void imprimeDistanciaEAntecessor( int []antecessor, long []distancias, String nomeArquivoSaida )
    {
        Formatter saida = null;
        
        // Usado para verificar se a saida deve ser gravada em um arquivo
        if ( nomeArquivoSaida != null )
        {
            try 
            {
                saida = new Formatter(nomeArquivoSaida);
            } catch (FileNotFoundException ex) 
            {
                System.err.println("Erro ao escrever no arquivod e saida \"" + nomeArquivoSaida + "\".");
                ex.printStackTrace();
                System.exit( 1 );
            }
            
        }
        
        if ( distancias != null && antecessor != null )
        {
            System.out.println("Imprimindo antecessores e distâncias");
            if ( antecessor.length != distancias.length )
            {
                System.err.printf("Error: O tamanho do vetor de distâncias é diferente do tamanho do vetor de antecessores.%nIsso não deveria acontecer. Verifique o que pode estar occorendo%n");
                System.exit( 1 );
            }
            for ( int i = 0; i < antecessor.length; i++  )
            {
                if ( saida == null )
                    System.out.printf( "Distância( %d ): %d\tAntecessor( %d ): %d%n", i, distancias[ i ], i, antecessor[ i ] );
                else
                {
                    saida.format( "Distância( %d ): %d\tAntecessor( %d ): %d%n", i, distancias[ i ], i, antecessor[ i ] );
                }
            }
        }
        else if ( distancias == null )
        {
            for ( int i = 0; i < antecessor.length; i++ )
            {
                if ( saida == null )
                    System.out.printf("Antecessor( %d ): %d%n", i, antecessor[ i ] );
                else
                    saida.format("Antecessor( %d ): %d%n", i, antecessor[ i ] );                
            }
        }
        else
        {
            for ( int i = 0; i < distancias.length; i++ )
            {
                if ( saida == null )
                    System.out.printf("Distância( %d ): %d%n", i, distancias[ i ] );
                else
                    saida.format("Distância( %d ): %d%n", i, distancias[ i ] );
            }
        }
        
        if ( saida != null )
            saida.close();
        
    }
    
    public void publicaCaminho( int []antecessores, int idOrigem, int idDestino )
    {
        Stack<Integer> pilha = new Stack<>();
        int ultimoVisitado = idDestino;
        
        while ( ultimoVisitado != idOrigem )
        {
            pilha.push( ultimoVisitado );
            ultimoVisitado = antecessores[ ultimoVisitado ];
        }
        
        pilha.push( idOrigem );
        
        int ultimoElemento = pilha.firstElement();
        while ( !pilha.isEmpty() )
        {
            int elemento = pilha.pop();
            if ( elemento == ultimoElemento )
                System.out.printf("%d%n", elemento );
            else
                System.out.printf("%d -> ", elemento );
        }
    }
    
    public void algoritmoAEstrelaBeta( int idOrigem, int idDestino )
    {
        HeapBinario heap = new HeapBinario( getNumeroVertices() );
        int []antecessor = new int[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        long []distanciaHeuristica = new long[ getNumeroVertices() ];
        long []distanciaReal = new long[ getNumeroVertices() ];
        HeapBinario.HeapNode []rastreador = new HeapBinario.HeapNode[ getNumeroVertices() ];
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessor[ i ] = i;
            isDeterminado[ i ] = false;
            rastreador[ i ] = heap.insertHeap(i, Long.MAX_VALUE );
            distanciaHeuristica[ i ] = 0;
            distanciaReal[ i ] = Long.MAX_VALUE;
        }
        
        heap.decreaseKey(idOrigem, 0 );
        distanciaReal[ idOrigem ] = 0;
        while ( isDeterminado[ idDestino ] == false )
        {
            HeapBinario.HeapNode nodoAtual = heap.extractMin();
            int idNodoAtual = nodoAtual.getIdVertice();
            isDeterminado[ idNodoAtual ] = true;
            
            for ( Aresta a: verticesGrafo[ idNodoAtual ].getArestasAdjacentes() )
            {
                int idVerticeDestino = a.idVerticeDestino;
                if ( isDeterminado[ idVerticeDestino ] == true )
                    continue;
                
                // Função Heurística - Distância Euclidiana
                if ( distanciaHeuristica[ idVerticeDestino ] == 0 )
                {                    
                    distanciaHeuristica[ idVerticeDestino ] = Math.round( Math.sqrt( Math.pow( cordenadasX[ idVerticeDestino ] - cordenadasX[ idDestino ], 2 ) + Math.pow( cordenadasY[ idVerticeDestino ] - cordenadasY[ idDestino ] , 2 ) ) );
                }
                
                HeapBinario.HeapNode nodoDestino = rastreador[ idVerticeDestino ];
                long chaveDestino = a.peso + distanciaReal[ idNodoAtual ];
                //long distanciaPrevista = ( a.peso + nodoAtual.getKey() + distanciaHeuristica[ idVerticeDestino ] - distanciaHeuristica[ idNodoAtual ] );
                if ( distanciaReal[ idVerticeDestino ] > chaveDestino && chaveDestino >= 0  )
                {
                    distanciaReal[ idVerticeDestino ] = chaveDestino;
                    heap.decreaseKey( nodoDestino.getIndiceAtual(), chaveDestino + distanciaHeuristica[ idVerticeDestino ] );
                    antecessor[ idVerticeDestino ] = idNodoAtual;
                }
            }
        }
        
        
        publicaCaminho(antecessor, idOrigem, idDestino);
        
    }
    
    public int contaNumeroDeVerticesAbertosAEstrelaNaoAdmissivel( int idOrigem, int idDestino )
    {
        HeapBinario heap = new HeapBinario( getNumeroVertices() );
        int []antecessor = new int[ getNumeroVertices() ];
        EstadosVertice []estados = new EstadosVertice[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        long []distanciaHeuristica = new long[ getNumeroVertices() ];
        HeapBinario.HeapNode []rastreador = new HeapBinario.HeapNode[ getNumeroVertices() ];
        int numeroAbertos = 1;
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessor[ i ] = i;
            isDeterminado[ i ] = false;
            rastreador[ i ] = heap.insertHeap(i, Long.MAX_VALUE );
            distanciaHeuristica[ i ] = 0;
            estados[ i ] = EstadosVertice.NEUTRO;
        }
        
        heap.decreaseKey(idOrigem, 0 );
        distanciaHeuristica[ idOrigem ] = 0;
        
        HeapBinario.HeapNode nodoAtual = heap.extractMin();
        
        while ( nodoAtual.getIdVertice() != idDestino )
        {
            
            int idNodoAtual = nodoAtual.getIdVertice();
            isDeterminado[ idNodoAtual ] = true;
            estados[ idNodoAtual ] = EstadosVertice.FECHADO;
            
            for ( Aresta a: verticesGrafo[ idNodoAtual ].getArestasAdjacentes() )
            {
                int idVerticeDestino = a.idVerticeDestino;
                if ( isDeterminado[ idVerticeDestino ] == true )
                    continue;
                
                if ( distanciaHeuristica[ idVerticeDestino ] == 0 )
                {                    
                    distanciaHeuristica[ idVerticeDestino ] = ( Math.abs( cordenadasX[ idVerticeDestino ] - cordenadasX[ idDestino ] ) + Math.abs( cordenadasY[ idVerticeDestino ] - cordenadasY[ idDestino ] ) );
                }
                
                HeapBinario.HeapNode nodoDestino = rastreador[ idVerticeDestino ];
                long distanciaPrevista = ( a.peso + nodoAtual.getKey() + distanciaHeuristica[ idVerticeDestino ] - distanciaHeuristica[ idNodoAtual ] );
                if ( nodoDestino.getKey() > distanciaPrevista && distanciaPrevista >= 0  )
                {
                    heap.decreaseKey( nodoDestino.getIndiceAtual(), distanciaPrevista );
                    antecessor[ idVerticeDestino ] = idNodoAtual;
                    if ( estados[ idVerticeDestino ] == EstadosVertice.NEUTRO )
                    {
                        estados[ idVerticeDestino ] = EstadosVertice.ABERTO;
                        numeroAbertos += 1;
                    }
                }
            }
            nodoAtual = heap.extractMin();
        }
        
        
        //publicaCaminho(antecessor, idOrigem, idDestino);
        
        return numeroAbertos;
        
    }
    
    public int contaNumeroDeVerticesAbertosAEstrela( int idOrigem, int idDestino )
    {
        HeapBinario heap = new HeapBinario( getNumeroVertices() );
        int []antecessor = new int[ getNumeroVertices() ];
        EstadosVertice []estados = new EstadosVertice[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        long []distanciaHeuristica = new long[ getNumeroVertices() ];
        HeapBinario.HeapNode []rastreador = new HeapBinario.HeapNode[ getNumeroVertices() ];
        int numeroAbertos = 1;
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessor[ i ] = i;
            isDeterminado[ i ] = false;
            rastreador[ i ] = heap.insertHeap(i, Long.MAX_VALUE );
            distanciaHeuristica[ i ] = 0;
            estados[ i ] = EstadosVertice.NEUTRO;
        }
        
        heap.decreaseKey(idOrigem, 0 );
        distanciaHeuristica[ idOrigem ] = 0;
        
        HeapBinario.HeapNode nodoAtual = heap.extractMin();
        
        while ( nodoAtual.getIdVertice() != idDestino )
        {
            
            int idNodoAtual = nodoAtual.getIdVertice();
            isDeterminado[ idNodoAtual ] = true;
            estados[ idNodoAtual ] = EstadosVertice.FECHADO;
            
            for ( Aresta a: verticesGrafo[ idNodoAtual ].getArestasAdjacentes() )
            {
                int idVerticeDestino = a.idVerticeDestino;
                if ( isDeterminado[ idVerticeDestino ] == true )
                    continue;
                
                if ( distanciaHeuristica[ idVerticeDestino ] == 0 )
                {                    
                    distanciaHeuristica[ idVerticeDestino ] = Math.round( Math.sqrt( Math.pow( cordenadasX[ idVerticeDestino ] - cordenadasX[ idDestino ], 2 ) + Math.pow( cordenadasY[ idVerticeDestino ] - cordenadasY[ idDestino ] , 2 ) ) );
                }
                
                HeapBinario.HeapNode nodoDestino = rastreador[ idVerticeDestino ];
                long distanciaPrevista = ( a.peso + nodoAtual.getKey() + distanciaHeuristica[ idVerticeDestino ] - distanciaHeuristica[ idNodoAtual ] );
                if ( nodoDestino.getKey() > distanciaPrevista && distanciaPrevista >= 0  )
                {
                    heap.decreaseKey( nodoDestino.getIndiceAtual(), distanciaPrevista );
                    antecessor[ idVerticeDestino ] = idNodoAtual;
                    if ( estados[ idVerticeDestino ] == EstadosVertice.NEUTRO )
                    {
                        estados[ idVerticeDestino ] = EstadosVertice.ABERTO;
                        numeroAbertos += 1;
                    }
                }
            }
            nodoAtual = heap.extractMin();
        }
        
        
        //publicaCaminho(antecessor, idOrigem, idDestino);
        
        return numeroAbertos;
        
    }
    
    public long algoritmoAEstrelaManhattan( int idOrigem, int idDestino, final boolean imprimeRota, final boolean retornaCusto )
    {
        HeapBinario heap = new HeapBinario( getNumeroVertices() );
        int []antecessor = new int[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        long []distanciaHeuristica = new long[ getNumeroVertices() ];
        HeapBinario.HeapNode []rastreador = new HeapBinario.HeapNode[ getNumeroVertices() ];
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessor[ i ] = i;
            isDeterminado[ i ] = false;
            rastreador[ i ] = heap.insertHeap(i, Long.MAX_VALUE );
            distanciaHeuristica[ i ] = 0;
        }
        
        heap.decreaseKey(idOrigem, 0 );
        distanciaHeuristica[ idOrigem ] = 0;
        
        HeapBinario.HeapNode nodoAtual = heap.extractMin();
        
        while ( nodoAtual.getIdVertice() != idDestino )
        {
            
            int idNodoAtual = nodoAtual.getIdVertice();
            isDeterminado[ idNodoAtual ] = true;
            
            for ( Aresta a: verticesGrafo[ idNodoAtual ].getArestasAdjacentes() )
            {
                int idVerticeDestino = a.idVerticeDestino;
                if ( isDeterminado[ idVerticeDestino ] == true )
                    continue;
                
                if ( distanciaHeuristica[ idVerticeDestino ] == 0 )
                {                    
                    distanciaHeuristica[ idVerticeDestino ] = ( Math.abs( cordenadasX[ idVerticeDestino ] - cordenadasX[ idDestino ] ) + Math.abs( cordenadasY[ idVerticeDestino ] - cordenadasY[ idDestino ] ) );
                }
                
                HeapBinario.HeapNode nodoDestino = rastreador[ idVerticeDestino ];
                long distanciaPrevista = ( a.peso + nodoAtual.getKey() + distanciaHeuristica[ idVerticeDestino ] - distanciaHeuristica[ idNodoAtual ] );
                if ( nodoDestino.getKey() > distanciaPrevista && distanciaPrevista >= 0  )
                {
                    heap.decreaseKey( nodoDestino.getIndiceAtual(), distanciaPrevista );
                    antecessor[ idVerticeDestino ] = idNodoAtual;
                }
            }
            nodoAtual = heap.extractMin();
        }
        
        if ( imprimeRota == true )
            publicaCaminho(antecessor, idOrigem, idDestino);
        
        if ( retornaCusto == true )
            return calculaDistanciaTotal(antecessor, idOrigem, idDestino);
        else
            return -1;
        
    }
    
    private long calculaDistanciaTotal( int []antecessores, int idOrigem, int idDestino )
    {
        if ( idDestino == idOrigem )
            return 0;
        
        Stack<Integer> pilha = new Stack<>();
        int ultimoVisitado = idDestino;
        long custoTotalCaminho = 0;
        
        while ( ultimoVisitado != idOrigem )
        {
            pilha.push( ultimoVisitado );
            ultimoVisitado = antecessores[ ultimoVisitado ];
        }
        
        pilha.push( idOrigem );
        
        int ultimoElemento = pilha.firstElement();
        int elementoOrigem = pilha.pop();
        while ( !pilha.isEmpty() )
        {            
            int elementoDestino;
            if ( elementoOrigem != ultimoElemento )
            {
                elementoDestino = pilha.pop();
            
                for ( Aresta a: verticesGrafo[ elementoOrigem ].arestasAdjacentes )
                {
                    if ( a.getIdVerticeDestino() == elementoDestino )
                    {
                        custoTotalCaminho += a.peso;
                        break;
                    }
                
                }
                elementoOrigem = elementoDestino;
            }
            
        }
        
        return custoTotalCaminho;
    }
    
    public long algoritmoAEstrela( int idOrigem, int idDestino, final boolean imprimeRota, final boolean imprimeCusto )
    {
        HeapBinario heap = new HeapBinario( getNumeroVertices() );
        int []antecessor = new int[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        long []distanciaHeuristica = new long[ getNumeroVertices() ];
        HeapBinario.HeapNode []rastreador = new HeapBinario.HeapNode[ getNumeroVertices() ];
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessor[ i ] = i;
            isDeterminado[ i ] = false;
            rastreador[ i ] = heap.insertHeap(i, Long.MAX_VALUE );
            distanciaHeuristica[ i ] = 0;
        }
        
        heap.decreaseKey(idOrigem, 0 );
        distanciaHeuristica[ idOrigem ] = 0;
        
        HeapBinario.HeapNode nodoAtual = heap.extractMin();
        
        while ( nodoAtual.getIdVertice() != idDestino )
        {
            
            int idNodoAtual = nodoAtual.getIdVertice();
            isDeterminado[ idNodoAtual ] = true;
            
            for ( Aresta a: verticesGrafo[ idNodoAtual ].getArestasAdjacentes() )
            {
                int idVerticeDestino = a.idVerticeDestino;
                if ( isDeterminado[ idVerticeDestino ] == true )
                    continue;
                
                if ( distanciaHeuristica[ idVerticeDestino ] == 0 )
                {                    
                    distanciaHeuristica[ idVerticeDestino ] = Math.round( Math.sqrt( Math.pow( cordenadasX[ idVerticeDestino ] - cordenadasX[ idDestino ], 2 ) + Math.pow( cordenadasY[ idVerticeDestino ] - cordenadasY[ idDestino ] , 2 ) ) );
                }
                
                HeapBinario.HeapNode nodoDestino = rastreador[ idVerticeDestino ];
                long distanciaPrevista = ( a.peso + nodoAtual.getKey() + distanciaHeuristica[ idVerticeDestino ] - distanciaHeuristica[ idNodoAtual ] );
                if ( nodoDestino.getKey() > distanciaPrevista && distanciaPrevista >= 0  )
                {
                    heap.decreaseKey( nodoDestino.getIndiceAtual(), distanciaPrevista );
                    antecessor[ idVerticeDestino ] = idNodoAtual;
                }
            }
            nodoAtual = heap.extractMin();
        }
        
        if ( imprimeRota == true )
            publicaCaminho(antecessor, idOrigem, idDestino);
        if ( imprimeCusto == true )
            return calculaDistanciaTotal(antecessor, idOrigem, idDestino);
        
        return -1;
        //calculaDistanciaTotal(antecessor, idOrigem, idDestino);
        
    }
    
    public int contaNumeroDeVerticesAbertosDijkstraAdptado( int idOrigem, int idObjetivo )
    {
        HeapBinario heap = new HeapBinario( getNumeroVertices() );
        int []antecessor = new int[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        long []distancias = new long[ getNumeroVertices() ];
        int verticesASeremVisitados = getNumeroVertices();
        int numeroAbertos = 1;
        EstadosVertice []estados = new EstadosVertice[verticesASeremVisitados];
        HeapBinario.HeapNode []rastreador = new HeapBinario.HeapNode[verticesASeremVisitados];
        
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessor[ i ] = i;
            isDeterminado[ i ] = false;
            rastreador[ i ] = heap.insertHeap(i, Long.MAX_VALUE );
            distancias[ i ]= Integer.MAX_VALUE;
            estados[ i ] = EstadosVertice.NEUTRO;
        }
        
        heap.decreaseKey(idOrigem, 0);
        distancias[ idOrigem ] = 0;
        
        HeapBinario.HeapNode nodoAtual = heap.extractMin();
        while ( nodoAtual.getIdVertice() != idObjetivo )
        {
            
            int idAtual = nodoAtual.getIdVertice();
            isDeterminado[ idAtual ] = true;
            estados[ idAtual ] = EstadosVertice.FECHADO;
            
            for ( Aresta a: verticesGrafo[ idAtual ].arestasAdjacentes )
            {
                int idDestino = a.idVerticeDestino;
                if ( isDeterminado[ idDestino ] )
                    continue;
                
                HeapBinario.HeapNode nodoDestino = rastreador[ idDestino ];
                if ( nodoDestino.getKey() > ( a.peso + nodoAtual.getKey() ) )
                {
                    if ( ( a.peso + nodoAtual.getKey() ) >= 0 )
                    {
                        heap.decreaseKey( nodoDestino.getIndiceAtual(), ( a.peso + nodoAtual.getKey() ) );
                        antecessor[ idDestino ] = idAtual;
                        distancias[ idDestino ] = a.peso + nodoAtual.getKey();
                        if ( estados[ idDestino ] == EstadosVertice.NEUTRO )
                        {
                            estados[ idDestino ] = EstadosVertice.ABERTO;
                            numeroAbertos += 1;
                        }
                    }
                }
            }
            nodoAtual = heap.extractMin();
            
            //verticesASeremVisitados--;
            //if ( isDeterminado[ idAtual ] )
                
            
        }
        
        return numeroAbertos;
        
    }
    
    public void dijkstraHeapBinarioAdptado( int idOrigem, int idObjetivo, boolean imprimeRota )
    {
        HeapBinario heap = new HeapBinario( getNumeroVertices() );
        int []antecessor = new int[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        long []distancias = new long[ getNumeroVertices() ];
        int verticesASeremVisitados = getNumeroVertices();
        HeapBinario.HeapNode []rastreador = new HeapBinario.HeapNode[verticesASeremVisitados];
        
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessor[ i ] = i;
            isDeterminado[ i ] = false;
            rastreador[ i ] = heap.insertHeap(i, Long.MAX_VALUE );
            distancias[ i ]= Integer.MAX_VALUE;
        }
        
        heap.decreaseKey(idOrigem, 0);
        distancias[ idOrigem ] = 0;
        
        HeapBinario.HeapNode nodoAtual = heap.extractMin();
        while ( nodoAtual.getIdVertice() != idObjetivo )
        {
            
            int idAtual = nodoAtual.getIdVertice();
            isDeterminado[ idAtual ] = true;
            
            for ( Aresta a: verticesGrafo[ idAtual ].arestasAdjacentes )
            {
                int idDestino = a.idVerticeDestino;
                if ( isDeterminado[ idDestino ] )
                    continue;
                
                HeapBinario.HeapNode nodoDestino = rastreador[ idDestino ];
                if ( nodoDestino.getKey() > ( a.peso + nodoAtual.getKey() ) )
                {
                    if ( ( a.peso + nodoAtual.getKey() ) >= 0 )
                    {
                        heap.decreaseKey( nodoDestino.getIndiceAtual(), ( a.peso + nodoAtual.getKey() ) );
                        antecessor[ idDestino ] = idAtual;
                        distancias[ idDestino ] = a.peso + nodoAtual.getKey();
                    }
                }
            }
            nodoAtual = heap.extractMin();
            
            //verticesASeremVisitados--;
            //if ( isDeterminado[ idAtual ] )
                
            
        }
        
        //imprimeDistanciaEAntecessor(antecessor, distancias, "/home/administrador/Área de Trabalho/Testes/ResultadoDijkstraHeapBinarioNovo.txt");
        
        if ( imprimeRota == true )
        {
            System.out.println("Mostrando caminho para Dijkstra Adptado");
            publicaCaminho(antecessor, idOrigem, idObjetivo );
            System.out.println("Custo total para o vértice " + idObjetivo + ": " + distancias[ idObjetivo ] );
            System.out.printf("Fim Dijkstra%n%n");            
        }
        
        
        
        /*System.out.println("Imprimindo menores distanciaHeuristica Dijkstra Heap Binário");
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            System.out.printf("Distância( %d ): %d%n", i, distanciaHeuristica[ i ] );
            //System.out.printf("Antecessor( %d ): %d%n", i, antecessor[ i ] );
        }*/
        
    }
    
    private void repassaInconsistenstesParaAbertoAD( Map<Integer, HeapBinario.HeapNode> listaInconsistentes, HeapBinario openHeap, HeapBinario.HeapNode []rastreadorOpen, EstadosVertice []estadosVertices )
    {
        int idVertice;
        HeapBinario.HeapNode nodo;
        
        Set<Integer> chaves = listaInconsistentes.keySet();
        for ( Integer chave: chaves )
        {
            nodo = listaInconsistentes.get( chave );
            idVertice = nodo.getIdVertice();
            estadosVertices[ idVertice ] = EstadosVertice.ABERTO;
            rastreadorOpen[ idVertice ] = openHeap.insertHeap( idVertice, nodo.getKey() );
        }
    }
    
    
    private void limpaListaFechado( List<HeapBinario.HeapNode> listaFechado, EstadosVertice []estadosVertices )
    {
        int idVertice;
        HeapBinario.HeapNode nodo;
        for ( int i = 0; i < listaFechado.size(); i++ )
        {
            nodo = listaFechado.remove(i);
            idVertice = nodo.getIdVertice();
            estadosVertices[ idVertice ] = EstadosVertice.LIMBO;
        }
    }
           
    private long computeKeyAD( int idVertice, long []g, long []v, long []distanciaHeuristica, double episolon )
    {
        if ( v[ idVertice ] >= g[ idVertice ] )
        {
            return Math.max(g[ idVertice ] + Math.round(episolon * distanciaHeuristica[ idVertice ] ), g[ idVertice ] );
        }
        else
        {
            return Math.max( v[ idVertice ] + distanciaHeuristica[ idVertice ], v[ idVertice ] );
        }
    }
    
    private long calculaDistanciaHeuristicaEuclidiana( int idVerticeOrigem, int idVerticeDestino )
    {
        return Math.round( Math.sqrt( Math.pow( cordenadasX[ idVerticeOrigem ] - cordenadasX[ idVerticeDestino ], 2 ) + Math.pow( cordenadasY[ idVerticeOrigem ] - cordenadasY[ idVerticeDestino ], 2 ) ) );
    }
    
    private VerticeEspecialAD argmin( int idVertice, Map<Integer, VerticeEspecialAD> []listaPredecessores, long []v )
    {
        long menorValor = Long.MAX_VALUE;
        int minId = Integer.MAX_VALUE;
        
        Set<Integer> chaves = listaPredecessores[ idVertice ].keySet();
        for ( Integer chave: chaves )
        {
            long valorCalculado = v[ chave ] + listaPredecessores[ idVertice ].get( chave ).getPeso();
            if ( valorCalculado < menorValor )
            {
                minId = chave;
                menorValor = valorCalculado;
            }
        }
        
        return listaPredecessores[ idVertice ].get( minId );
    }
    
    private void updateSetMembership( int idVertice, HeapBinario openHeap, HeapBinario.HeapNode []rastreadorOpen, HeapBinario.HeapNode []rastreadorClosed, long []distanciaReal, long []v, long []distanciaHeuristica, EstadosVertice []estadosVertices, Map<Integer, HeapBinario.HeapNode> listaInconsistentes, double epsilon )
    {
        if ( v[ idVertice ] != distanciaReal[ idVertice ] )
        {
            if ( estadosVertices[ idVertice ] != EstadosVertice.FECHADO )
            {                
                if ( estadosVertices[ idVertice ] == EstadosVertice.NEUTRO || estadosVertices[ idVertice ] == EstadosVertice.LIMBO )
                    rastreadorOpen[ idVertice ] = openHeap.insertHeap(idVertice, computeKeyAD(idVertice, distanciaReal, v, distanciaHeuristica, epsilon) );
                else
                    openHeap.decreaseKey( rastreadorOpen[ idVertice ].getIndiceAtual(), computeKeyAD(idVertice, distanciaReal, v, distanciaHeuristica, epsilon));
                
                estadosVertices[ idVertice ] = EstadosVertice.ABERTO;
            }
            else if ( estadosVertices[ idVertice ] != EstadosVertice.INCOSISTENTE )
            {
                estadosVertices[ idVertice ] = EstadosVertice.INCOSISTENTE;
                rastreadorClosed[ idVertice ].setKey( computeKeyAD(idVertice, distanciaReal, v, distanciaHeuristica, epsilon) );
                listaInconsistentes.put(idVertice, rastreadorClosed[ idVertice ] );
                rastreadorClosed[ idVertice ] = null;
            }
        }
        else
            {
                if ( estadosVertices[ idVertice ] == EstadosVertice.ABERTO )
                {
                    rastreadorOpen[ idVertice ] = openHeap.removeElemento( rastreadorOpen[ idVertice ].getIndiceAtual() );
                    estadosVertices[ idVertice ] = EstadosVertice.LIMBO;
                }
                else if ( estadosVertices[ idVertice ] == EstadosVertice.INCOSISTENTE )
                {
                    listaInconsistentes.remove( idVertice );
                    estadosVertices[ idVertice ] = EstadosVertice.LIMBO;
                }
                
            }
    }
    
    private void computePathAD( int idDestino, HeapBinario openHeap, HeapBinario.HeapNode []rastreadorOpen, HeapBinario.HeapNode []rastreadorClosed, int []antecessores, long []distanciaReal, long []v, long []distanciaHeuristica, EstadosVertice []estadosVertices, List<HeapBinario.HeapNode> listaFechado, Map<Integer, HeapBinario.HeapNode> listaInconsistentes, Map<Integer, VerticeEspecialAD> []listaPredecessores, VerticeEspecialAD []bp, double epsilon )
    {
        while ( computeKeyAD(idDestino, distanciaReal, v, distanciaHeuristica, epsilon) > openHeap.getMin().getKey() || v[ idDestino ] < distanciaReal[ idDestino ] )
        {
            HeapBinario.HeapNode nodoAtual = openHeap.extractMin();
            int idNodoAtual = nodoAtual.getIdVertice();            
            if ( v[ idNodoAtual ] > distanciaReal[ idNodoAtual ] )
            {
                v[ idNodoAtual ] = distanciaReal[ idNodoAtual ];
                estadosVertices[ idNodoAtual ] = EstadosVertice.FECHADO;
                listaFechado.add( nodoAtual );
                rastreadorClosed[ idNodoAtual ] = nodoAtual;
                rastreadorOpen[ idNodoAtual ] = null;
                
                for ( Aresta a: verticesGrafo[ idNodoAtual ].arestasAdjacentes )
                {
                    int idAdjacente = a.idVerticeDestino;
                    // 14
                    if ( estadosVertices[ idAdjacente ] == EstadosVertice.NEUTRO )
                    {
                        distanciaReal[ idAdjacente ] = v[ idAdjacente ] = Long.MAX_VALUE;
                        distanciaHeuristica[ idAdjacente ] = calculaDistanciaHeuristicaEuclidiana(idAdjacente, idDestino );
                        bp[ idAdjacente ] = null;
                        listaPredecessores[ idAdjacente ] = new HashMap<>();
                    }
                    
                    // Adiciona predecessor do vértice adjacente
                    if ( !listaPredecessores[ idAdjacente ].containsKey( idNodoAtual ) )
                        listaPredecessores[ idAdjacente ].put( idNodoAtual, new VerticeEspecialAD( idNodoAtual, a.peso ) );
                    
                    long distanciaCalculada = distanciaReal[ idNodoAtual ] + a.peso;
                    if ( distanciaReal[ idAdjacente ] > distanciaCalculada && distanciaCalculada >= 0 )
                    {
                        bp[ idAdjacente ] = listaPredecessores[ idAdjacente ].get( idNodoAtual ); //new VerticeEspecialAD( idNodoAtual, a.peso );
                        antecessores[ idAdjacente ] = idNodoAtual;
                        distanciaReal[ idAdjacente ] = distanciaReal[ bp[ idAdjacente ].getIdVertice() ] + bp[ idAdjacente ].peso;
                        updateSetMembership( idAdjacente, openHeap, rastreadorOpen, rastreadorClosed, distanciaReal, v, distanciaHeuristica, estadosVertices, listaInconsistentes, epsilon );
                    }
                }
                
            }
            else
            {
                v[ idNodoAtual ] = Long.MAX_VALUE;
                updateSetMembership(idNodoAtual, openHeap, rastreadorOpen, rastreadorClosed, distanciaReal, v, distanciaHeuristica, estadosVertices, listaInconsistentes, epsilon );
                for ( Aresta a: verticesGrafo[ idNodoAtual ].arestasAdjacentes )
                {
                    int idAdjacente = a.idVerticeDestino;
                    // 22
                    if ( estadosVertices[ idAdjacente ] == EstadosVertice.NEUTRO )
                    {
                        distanciaReal[ idAdjacente ] = v[ idAdjacente ] = Long.MAX_VALUE;
                        distanciaHeuristica[ idAdjacente ] = calculaDistanciaHeuristicaEuclidiana(idAdjacente, idDestino );
                        bp[ idAdjacente ] = null;
                    }
                    
                    if ( bp[ idAdjacente ].idVertice == idNodoAtual )
                    {
                       bp[ idAdjacente ] = argmin( idAdjacente, listaPredecessores, v );
                       distanciaReal[ idAdjacente ] = v[ bp[ idAdjacente].idVertice ] + bp[ idAdjacente ].getPeso();
                       updateSetMembership( idAdjacente, openHeap, rastreadorOpen, rastreadorClosed, distanciaReal, v, distanciaHeuristica, estadosVertices, listaInconsistentes, epsilon);
                    }
                }
            }
            
        }
    }
    
    private void alteraPesosArestasGrafo(Map<Integer, VerticeEspecialAD> []listaPredecessores, List<Aresta> arestasModificadas )
    {
        SecureRandom random = new SecureRandom();
        
        int verticeInicialSorteado = 1357;
        int nodoSorteado = 1357;
        int novoPeso = 80000;
        
        //verticesGrafo[ verticeInicialSorteado ].arestasAdjacentes.get( nodoSorteado );
        
        for ( Aresta a: verticesGrafo[ verticeInicialSorteado ].arestasAdjacentes )
        {
            if ( a.idVerticeDestino == 1356 )
            {
                System.out.printf("Realizando alteração de peso.%nAresta: %d -> %d. Peso antigo: %d; Novo peso: %d%n", verticeInicialSorteado, a.idVerticeDestino, a.peso, novoPeso );
                a.peso = novoPeso;
                
                if ( listaPredecessores[ a.idVerticeDestino ].containsKey( verticeInicialSorteado ) )
                {
                    listaPredecessores[ a.idVerticeDestino ].get( verticeInicialSorteado ).peso = novoPeso;
                }
                arestasModificadas.add( a );
                
                break;
            }
            
        }
        
        System.out.printf("Peso mudado com sucesso%n%n");
    }
    
    public void dynamicSearchAEstrelaBeta( int idOrigem, int idDestino, double episolon, double fatorDeCorte )
    {
        // Definições de variáveis
        int []antecessores = new int[ getNumeroVertices() ];
        long []g = new long[ getNumeroVertices() ];
        long []v = new long[ getNumeroVertices() ];
        VerticeEspecialAD []bp = new VerticeEspecialAD[ getNumeroVertices() ];
        long []distanciaHeuristica = new long[ getNumeroVertices() ];
        HeapBinario openHeap = new HeapBinario( getNumeroVertices() );
        HeapBinario.HeapNode []rastreadorOpen = new HeapBinario.HeapNode[ getNumeroVertices() ];
        List<Aresta> arestasModificadas = new LinkedList<>();
        Set<Integer> listaFechado = new HashSet<>();
        Set<Integer> listaInconsistentes = new HashSet<>();
        Map<Integer, VerticeEspecialAD> []listaPredecessores = new Map[ getNumeroVertices() ];
        EstadosVertice []estadosVertices = new EstadosVertice[ getNumeroVertices() ];
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessores[ i ] = i;
            estadosVertices[ i ] = EstadosVertice.NEUTRO;
        }
        
        // 7
        g[ idDestino ] = v[ idDestino ] = Long.MAX_VALUE;
        distanciaHeuristica[ idDestino ] = 0;
        v[ idOrigem ] = Long.MAX_VALUE;
        bp[ idDestino ] = bp[ idOrigem ] = null;
        
        // 8
        g[ idOrigem ] = 0;
        listaPredecessores[ idOrigem ] = new HashMap<>();
        distanciaHeuristica[ idOrigem ] = calculaDistanciaHeuristicaEuclidiana( idOrigem, idDestino );
        
        // 9
        rastreadorOpen[ idOrigem ] = openHeap.insertHeap(idOrigem, computeKeyAD(idOrigem, g, v, distanciaHeuristica, episolon) );
        computePathADBeta(idDestino, antecessores, openHeap, rastreadorOpen, g, v, distanciaHeuristica, estadosVertices, bp, listaFechado, listaInconsistentes, listaPredecessores, episolon );
        System.out.println("Mostando caminho de AD* para o vértice: " + idDestino + ". eps = " + episolon );
        publicaCaminho( antecessores, idOrigem, idDestino );
        System.out.println("Custo total para o vértice " + idDestino + ": " + calculaDistanciaTotal(antecessores, idOrigem, idDestino) );
        System.out.println("");
        
        while ( episolon > 1 )
        {
            episolon -= fatorDeCorte;
            if ( episolon == 1 )
            {
                alteraPesosArestasGrafo(listaPredecessores, arestasModificadas );                
            }
            for ( Aresta a: arestasModificadas )
            {
                if ( a.idVerticeDestino != idOrigem && estadosVertices[ a.idVerticeDestino ] != EstadosVertice.NEUTRO )
                {
                    bp[ a.idVerticeDestino ] = argmin( a.idVerticeDestino, listaPredecessores, v );
                    g[ a.idVerticeDestino ] = v[ bp[ a.idVerticeDestino ].idVertice ] + bp[ a.idVerticeDestino ].peso;
                    updateSetMembershipBeta( a.idVerticeDestino, openHeap, rastreadorOpen, g, v, distanciaHeuristica, estadosVertices, listaFechado, listaInconsistentes, episolon );
                }
            }
            arestasModificadas.clear();
            repassaInconsistentesParaAbertoADBeta(openHeap, rastreadorOpen, listaInconsistentes, v, g, distanciaHeuristica, estadosVertices, episolon);
            atualizaOpenAD(openHeap, g, v, distanciaHeuristica, episolon);
            limpaFechadoARA(listaFechado, estadosVertices);
            computePathADBeta(idDestino, antecessores, openHeap, rastreadorOpen, g, v, distanciaHeuristica, estadosVertices, bp, listaFechado, listaInconsistentes, listaPredecessores, episolon );
            System.out.println("Mostando caminho de AD* para o vértice: " + idDestino + ". eps = " + episolon );
            publicaCaminho( antecessores, idOrigem, idDestino );
            System.out.println("Custo total para o vértice " + idDestino + ": " + calculaDistanciaTotal(antecessores, idOrigem, idDestino) );
            System.out.println("");
        }
        
    }
    
    private void repassaInconsistentesParaAbertoADBeta( HeapBinario openHeap, HeapBinario.HeapNode []rastreadorOpen, Set< Integer> listaInconsistenstes, long []v, long []g, long []distanciaHeuristica, EstadosVertice []estadosVertices, double episolon )
    {
        for ( Integer e: listaInconsistenstes )
        {
            rastreadorOpen[ e ] = openHeap.insertHeap( e, computeKeyAD( e, g, v, distanciaHeuristica, episolon) );
            estadosVertices[ e ] = EstadosVertice.ABERTO;            
        }
        listaInconsistenstes.clear();
    }
    
    private void computePathADBeta( int idDestino, int []antecessores, HeapBinario openHeap, HeapBinario.HeapNode []rastreadorOpen, long []g, long []v, long []distanciaHeuristica, EstadosVertice []estadosVertices, VerticeEspecialAD []bp, Set<Integer> listaFechado, Set<Integer> listaInconsistentes, Map<Integer, VerticeEspecialAD> []listaPredecessores, double episolon )
    {
        while( computeKeyAD(idDestino, g, v, distanciaHeuristica, episolon ) > openHeap.getMin().getKey() || v[ idDestino ] < g[ idDestino ] )
        {
            // 10
            HeapBinario.HeapNode nodoAtual = openHeap.extractMin();
            int idNodoAtual = nodoAtual.getIdVertice();
            rastreadorOpen[ idNodoAtual ] = null;
            estadosVertices[ idNodoAtual ] = EstadosVertice.LIMBO;
            
            // 11
            if ( v[ idNodoAtual ] > g[ idNodoAtual ] )
            {
                // 12
                v[ idNodoAtual ] = g[ idNodoAtual ];
                listaFechado.add( idNodoAtual );
                estadosVertices[ idNodoAtual ] = EstadosVertice.FECHADO;
                
                // 13
                for ( Aresta a: verticesGrafo[ idNodoAtual ].getArestasAdjacentes() )
                {
                    int idVerticeAdjacente = a.idVerticeDestino;
                    // 14
                    if ( estadosVertices[ idVerticeAdjacente ] == EstadosVertice.NEUTRO )
                    {
                        // 15
                        v[ idVerticeAdjacente ] = g[ idVerticeAdjacente ] = Long.MAX_VALUE;
                        bp[ idVerticeAdjacente ] = null;
                        distanciaHeuristica[ idVerticeAdjacente ] = calculaDistanciaHeuristicaEuclidiana( idVerticeAdjacente, idDestino );
                        listaPredecessores[ idVerticeAdjacente ] = new HashMap<>();
                    }
                    
                    // Adiciona a lista de predecessores que serão usados em argmin
                    if ( !listaPredecessores[ idVerticeAdjacente ].containsKey( idNodoAtual ) )
                    {
                        listaPredecessores[ idVerticeAdjacente ].put( idNodoAtual, new VerticeEspecialAD(idNodoAtual, a.peso) );
                    }
                    
                    // 16
                    long distanciaCalculada = g[ idNodoAtual ] + listaPredecessores[ idVerticeAdjacente ].get( idNodoAtual ).getPeso();
                    if ( g[ idVerticeAdjacente ] > distanciaCalculada && distanciaCalculada >= 0 )
                    {
                        // 17
                        bp[ idVerticeAdjacente ] = listaPredecessores[ idVerticeAdjacente ].get( idNodoAtual );
                        // 18
                        g[ idVerticeAdjacente ] = g[ bp[ idVerticeAdjacente ].idVertice ] + bp[ idVerticeAdjacente ].peso;
                        updateSetMembershipBeta( idVerticeAdjacente, openHeap, rastreadorOpen, g, v, distanciaHeuristica, estadosVertices, listaFechado, listaInconsistentes, episolon );
                        antecessores[ idVerticeAdjacente ] = bp[ idVerticeAdjacente ].idVertice;
                    }                    
                }               
            }
            // 19
            else
            {
                // 30
                v[ idNodoAtual ] = Long.MAX_VALUE;
                updateSetMembershipBeta( idNodoAtual, openHeap, rastreadorOpen, g, v, distanciaHeuristica, estadosVertices, listaFechado, listaInconsistentes, episolon );
                
                // 21
                for ( Aresta a: verticesGrafo[ idNodoAtual ].getArestasAdjacentes() )
                {
                    int idVerticeAdjacente = a.getIdVerticeDestino();
                    
                    if ( estadosVertices[ idVerticeAdjacente ] == EstadosVertice.NEUTRO )
                    {
                        // 15
                        v[ idVerticeAdjacente ] = g[ idVerticeAdjacente ] = Long.MAX_VALUE;
                        bp[ idVerticeAdjacente ] = null;
                        distanciaHeuristica[ idVerticeAdjacente ] = calculaDistanciaHeuristicaEuclidiana( idVerticeAdjacente, idDestino );
                        listaPredecessores[ idVerticeAdjacente ] = new HashMap<>();
                    }
                    
                    // Adiciona a lista de predecessores que serão usados em argmin
                    if ( !listaPredecessores[ idVerticeAdjacente ].containsKey( idNodoAtual ) )
                    {
                        listaPredecessores[ idVerticeAdjacente ].put( idNodoAtual, new VerticeEspecialAD(idNodoAtual, a.peso) );
                    }
                    
                    // 24
                    if ( bp[ idVerticeAdjacente ] != null && bp[ idVerticeAdjacente ].idVertice == idNodoAtual )
                    {
                        bp[ idVerticeAdjacente ] = argmin( idVerticeAdjacente, listaPredecessores, v );
                        g[ idVerticeAdjacente ] = v[ bp[ idVerticeAdjacente ].idVertice ] + bp[ idVerticeAdjacente ].peso;
                        updateSetMembershipBeta(idDestino, openHeap, rastreadorOpen, g, v, distanciaHeuristica, estadosVertices, listaFechado, listaInconsistentes, episolon );
                        antecessores[ idVerticeAdjacente ] = bp[ idVerticeAdjacente ].idVertice;
                    }
                }               
            }
        }       
    }
    
    private void updateSetMembershipBeta( int idVertice, HeapBinario openHeap, HeapBinario.HeapNode []rastreadorOpen, long []g, long []v, long []distanciaHeuristica, EstadosVertice []estadosVertices, Set< Integer > listaFechado, Set< Integer > listaInconsistens, double episolon )
    {
        // 2
        if ( v[ idVertice ] != g[ idVertice ] )
        {
            // 3
            if ( estadosVertices[ idVertice ] != EstadosVertice.FECHADO )
            {
                if ( rastreadorOpen[ idVertice ] == null )
                {
                    rastreadorOpen[ idVertice ] = openHeap.insertHeap(idVertice, computeKeyAD( idVertice, g, v, distanciaHeuristica, episolon ) );
                }
                else
                {
                    long novaChave = computeKeyAD(idVertice, g, v, distanciaHeuristica, episolon );
                    // GAMBIARRA AQUI - CUIDADO
                    if ( novaChave > rastreadorOpen[ idVertice ].getKey() )
                    {
                        rastreadorOpen[ idVertice ] = openHeap.removeElemento( rastreadorOpen[ idVertice ].getIndiceAtual() );
                        rastreadorOpen[ idVertice ] = openHeap.insertHeap(idVertice, novaChave);
                    }
                    else
                        openHeap.decreaseKey( rastreadorOpen[ idVertice ].getIndiceAtual(), novaChave );
                    
                }
                
                estadosVertices[ idVertice ] = EstadosVertice.ABERTO;
            }
            else if ( estadosVertices[ idVertice ] != EstadosVertice.INCOSISTENTE )
            {
                if ( listaFechado.contains( idVertice ) )
                    listaFechado.remove( idVertice );
                
                listaInconsistens.add(idVertice);
                estadosVertices[ idVertice ] = EstadosVertice.INCOSISTENTE;
            }            
        }
        else
        {
            if ( estadosVertices[ idVertice ] == EstadosVertice.ABERTO )
            {
                openHeap.removeElemento(idVertice);
                rastreadorOpen[ idVertice ] = null;
                estadosVertices[ idVertice ] = EstadosVertice.LIMBO;
            }
            else if ( estadosVertices[ idVertice ] == EstadosVertice.INCOSISTENTE )
            {
                listaInconsistens.remove( idVertice );
                estadosVertices[ idVertice ] = EstadosVertice.LIMBO;
            }
        }
    }
    
    public void dynamicSearchAEstrela( int idOrigem, int idDestino, double episolon, double fatorDeCorte )
    {
        // Definições de varíaveis
        int []antecessores = new int[ getNumeroVertices() ];
        long []distanciaReal = new long[ getNumeroVertices() ]; // valor correspondente ao g(s)
        long []v = new long[ getNumeroVertices() ]; // valor corresponde ao v(s)
        VerticeEspecialAD []bp = new VerticeEspecialAD[ getNumeroVertices() ];
        long []distanciaHeuristica = new long[ getNumeroVertices() ];
        HeapBinario openHeap = new HeapBinario( getNumeroVertices() );
        HeapBinario.HeapNode []rastreadorOpen = new HeapBinario.HeapNode[ getNumeroVertices() ];
        HeapBinario.HeapNode []rastreadorClosed = new HeapBinario.HeapNode[ getNumeroVertices() ];
        List<HeapBinario.HeapNode> listaFechado = new LinkedList<>();
        Map<Integer, HeapBinario.HeapNode> listaInconsistentes = new HashMap<>();
        Map<Integer, VerticeEspecialAD> []listaPredecessores = new Map[ getNumeroVertices() ];
        EstadosVertice []estadosVertices = new EstadosVertice[ getNumeroVertices() ];
        
        // 7
        distanciaReal[ idDestino ] = v[ idDestino ] = Long.MAX_VALUE;
        v[ idOrigem ] = Long.MAX_VALUE;
        bp[ idDestino ] = bp[ idOrigem ] = null;
        
        // 8
        distanciaReal[ idOrigem ] = 0;
        distanciaHeuristica[ idOrigem ] = calculaDistanciaHeuristicaEuclidiana( idOrigem, idDestino );
        
        // 9
        rastreadorOpen[ idOrigem ] = openHeap.insertHeap(idOrigem, computeKeyAD(idOrigem, distanciaReal, v, distanciaHeuristica, episolon) );
        estadosVertices[ idOrigem ] = EstadosVertice.ABERTO;
        listaPredecessores[ idOrigem ] = new HashMap<>();
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessores[ i ] = i;
            estadosVertices[ i ] = EstadosVertice.NEUTRO;
        }
        
        while ( episolon >= 1 )
        {
            computePathAD(idDestino, openHeap, rastreadorOpen, rastreadorClosed, antecessores, distanciaReal, v, distanciaHeuristica, estadosVertices, listaFechado, listaInconsistentes, listaPredecessores, bp, episolon);
            System.out.println("Publicando caminho para eps = " + episolon );
            publicaCaminho(antecessores, idOrigem, idDestino);
            System.out.println("Custo total para o vértice " + idDestino + ": " + calculaDistanciaTotal(antecessores, idOrigem, idDestino) );
            episolon -= fatorDeCorte;
            limpaListaFechado(listaFechado, estadosVertices);
            repassaInconsistenstesParaAbertoAD(listaInconsistentes, openHeap, rastreadorOpen, estadosVertices );
            atualizaOpenAD(openHeap, distanciaReal, v, distanciaHeuristica, episolon);
            // PUBLICA CAMINHO
        }
    }
    
    public int []dijkstraHeapBinario( int idOrigem )
    {
        HeapBinario heap = new HeapBinario( getNumeroVertices() );
        int []antecessor = new int[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        long []distancias = new long[ getNumeroVertices() ];
        int verticesASeremVisitados = getNumeroVertices();
        HeapBinario.HeapNode []rastreador = new HeapBinario.HeapNode[verticesASeremVisitados];
        
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            antecessor[ i ] = i;
            isDeterminado[ i ] = false;
            rastreador[ i ] = heap.insertHeap(i, Long.MAX_VALUE );
            distancias[ i ]= Integer.MAX_VALUE;
        }
        
        heap.decreaseKey(idOrigem, 0);
        distancias[ idOrigem ] = 0;
        while ( verticesASeremVisitados > 0 )
        {
            HeapBinario.HeapNode nodoAtual = heap.extractMin();
            int idAtual = nodoAtual.getIdVertice();
            isDeterminado[ idAtual ] = true;
            
            for ( Aresta a: verticesGrafo[ idAtual ].arestasAdjacentes )
            {
                int idDestino = a.idVerticeDestino;
                if ( isDeterminado[ idDestino ] )
                    continue;
                
                HeapBinario.HeapNode nodoDestino = rastreador[ idDestino ];
                if ( nodoDestino.getKey() > ( a.peso + nodoAtual.getKey() ) )
                {
                    if ( ( a.peso + nodoAtual.getKey() ) >= 0 )
                    {
                        heap.decreaseKey( nodoDestino.getIndiceAtual(), ( a.peso + nodoAtual.getKey() ) );
                        antecessor[ idDestino ] = idAtual;
                        distancias[ idDestino ] = a.peso + nodoAtual.getKey();
                    }
                }
            }
            
            verticesASeremVisitados--;
            //if ( isDeterminado[ idAtual ] )
                
            
        }
        
        //imprimeDistanciaEAntecessor(antecessor, distancias, "/home/administrador/Área de Trabalho/Testes/ResultadoDijkstraHeapBinarioNovo.txt");
        
        /*if ( idObjetivo >= 0 )
        {
            System.out.println("Mostrando para Dijkstra");
            publicaCaminho(antecessor, idOrigem, idObjetivo );
            System.out.println("Custo total para o vértice " + idObjetivo + ": " + distancias[ idObjetivo ] );
            System.out.printf("Fim Dijkstra%n%n");            
        }*/
        
        
        
        /*System.out.println("Imprimindo menores distanciaHeuristica Dijkstra Heap Binário");
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            System.out.printf("Distância( %d ): %d%n", i, distanciaHeuristica[ i ] );
            //System.out.printf("Antecessor( %d ): %d%n", i, antecessor[ i ] );
        }*/
        
        return antecessor;
        
    }
     
    
    public void dijkstraCanonico( int idOrigem )
    {
        long []distancias = new long[ getNumeroVertices() ];
        int []antecessor = new int[ getNumeroVertices() ];
        boolean []isDeterminado = new boolean[ getNumeroVertices() ];
        int verticesASeremVisitados = getNumeroVertices();
        
        for ( int i = 0; i < getNumeroVertices(); i++ )
        {
            distancias[ i ] = Integer.MAX_VALUE;
            antecessor[ i ] = i;
            isDeterminado[ i ] = false;
        }
        
        distancias[ idOrigem ] = 0;
        int atualVertice = idOrigem;
        
        while ( verticesASeremVisitados > 0 )
        {
            isDeterminado[ atualVertice ] = true;
            verticesASeremVisitados--;
            
            for ( Aresta a: getVerticesGrafo()[ atualVertice ].getArestasAdjacentes() )
            {
                int verticeDestino = a.getIdVerticeDestino();
                if ( isDeterminado[ verticeDestino ] )
                    continue;
                
                if ( distancias[ verticeDestino ] > distancias[ atualVertice ] + a.getPeso()  )
                {
                    if (  (distancias[ atualVertice ] + a.getPeso()) >= 0 )
                    {
                        distancias[ verticeDestino ] = distancias[ atualVertice ] + a.getPeso();
                        antecessor[ verticeDestino ] = atualVertice;
                    }
                }               
                
            }
            long minimaDistancia = Integer.MAX_VALUE;
            boolean jaSelecionado = false;
            for ( int i = 0; i < getNumeroVertices(); i++ )
            {
                if ( isDeterminado[ i ] )
                    continue;
                if ( jaSelecionado == false )
                {
                    minimaDistancia = distancias[ i ];
                    atualVertice = i;
                    jaSelecionado = true;
                }                
                else if ( distancias[ i ] <  minimaDistancia )
                {
                    atualVertice = i;
                    minimaDistancia = distancias[ i ];
                }
            }
            
        }
        
        //imprimeDistanciaEAntecessor(antecessor, distancias, "/home/administrador/Área de Trabalho/Testes/ResultadoDijkstraCanonicoNovo.txt");
        
    }
    
    
    
    private static void abreArquivo( String nomeArquivo )
    {
        try
        {
            input = new Scanner(Paths.get(nomeArquivo));            
        }
        catch ( IOException e )
        {
            System.err.println("Erro ao abrir arquico de entrada \"" + nomeArquivo + "\". Certifique-se de que ele existe ou o seu endereço foi digitado corretamente.%nEncerrando programa" );
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void leDados(String nomeArquivo)
    {
        System.out.print("Iniciando leitura de dados...");
        try
        {
            boolean isPadrao = false;
            String avaliador = input.next();
            if ( Character.isLetter( avaliador.charAt(0) ) )
                isPadrao = true;
            else if ( Character.isDigit( avaliador.charAt(0) ) )
                isPadrao = false;           
            else
            {
                System.err.println("O arquivo de entrada não se encontra no padrão esperado de leitura, verifique se o arquivo de entrada está corretamente codificado.%nEncerrando o programa");
                System.exit( 1 );
            }
            
            fechaArquivo();
            abreArquivo(nomeArquivo);
            
            
            if ( isPadrao )
            {
                while ( input.hasNext() )
                {
                    String entradaDecodificadora = input.next();
                
                    if ( entradaDecodificadora.equals( "a" ) )
                    {
                        int id1 = input.nextInt() - 1;
                        int id2 = input.nextInt() - 1;
                        int peso = input.nextInt();
                        Aresta novaAresta = new Aresta( id1, id2, peso );
                        if ( getVerticesGrafo()[ id1 ] == null )
                            verticesGrafo[ id1 ] = new Vertice( id1 );
                        
                        getVerticesGrafo()[ id1 ].getArestasAdjacentes().add(novaAresta);
                        
                    }
                    else if ( entradaDecodificadora.equals( "c" ) )
                    {
                        input.nextLine();
                    }
                    else if ( entradaDecodificadora.equals("p") )
                    {
                        input.next();
                        numeroVertices = input.nextInt();
                        numeroArestas = input.nextInt();
                        
                        verticesGrafo = new Vertice[getNumeroVertices()];
                    }
                    else
                    {
                        System.err.println("Não devia cair aqui");
                        System.exit( 0 );
                    }
                }
                
            }
            else
            {
                numeroVertices = input.nextInt();
                numeroArestas = input.nextInt();
                
                verticesGrafo = new Vertice[getNumeroVertices()];
                
                while ( input.hasNext() )
                {
                    int id1 = input.nextInt();
                    int id2 = input.nextInt();
                    int peso = input.nextInt();
                    Aresta novaAresta = new Aresta(id1, id2, peso);
                    
                    if ( getVerticesGrafo()[ id1 ] == null )
                        verticesGrafo[ id1 ] = new Vertice( id1 );
                    
                    getVerticesGrafo()[ id1 ].getArestasAdjacentes().add(novaAresta);
                }
            }
            
        } catch ( NoSuchElementException e )
        {
            System.err.println("Exeção durante a leitura:%nO arquivo de entrada não se encontra no padrão esperado de leitura, verifique se o arquivo de entrada está corretamente codificado.%nEncerrando o programa");
            e.printStackTrace();
            System.exit( 1 );
        }
        catch ( IllegalStateException e )
        {
            System.err.println("Erro ao ler o arquivo de entrada");
            e.printStackTrace();
            System.exit( 1 );
        }
        
        
        System.out.println(" Feito.");
    }
    
    private static void fechaArquivo()
    {
        if ( input != null )
            input.close();
    }
    
    public void leCordenadas( String nomeArquivoCordenads )
    {
        System.out.print("Iniciando leitura do arquivo de cordenadas... ");
        cordenadasX = new long[numeroVertices];
        cordenadasY = new long[numeroVertices];
        while ( input.hasNext() )
        {
            String caracter = input.next();
            if ( caracter.equals( "v" ) )
            {
                int vertice = input.nextInt() - 1;
                cordenadasX[ vertice ] = input.nextLong();
                cordenadasY[ vertice ] = input.nextLong();
            }
            else if ( caracter.equals( "p" ) )
            {
                input.next();
                input.next();
                input.next();
                int vertificadordeQuantidade = input.nextInt();
                if ( numeroVertices != vertificadordeQuantidade )
                {
                    System.err.println("[ERROR]: O número de vértices do grafo é diferente do número de vertices do arquivo de cordenadas \"" + nomeArquivoCordenads + "\". Encerrando o programa.");
                    System.exit( 1 );
                }
            }
            else
                input.nextLine();
        }
        
        System.out.println("Feito");
    }
    
    public void leArquivoDeCordenadas( String nomeArquivoCordenadas )
    {
        abreArquivo(nomeArquivoCordenadas);
        this.leCordenadas(nomeArquivoCordenadas);
        fechaArquivo();
        
    }
    
    
    public void leArquivoEntrada( String nomeArquivo )
    {
        abreArquivo(nomeArquivo);
        this.leDados(nomeArquivo);
        fechaArquivo();        
    }
   
    public void imprimeGrafo()
    {
        System.out.println("Imprimido Grafo:");
        for (Vertice verticesGrafo1 : getVerticesGrafo()) 
        {
            for (Aresta a : verticesGrafo1.getArestasAdjacentes()) {
                System.out.printf("%d ---> %d w: %d%n", a.getIdVeticeOrigem(), a.getIdVerticeDestino(), a.getPeso());
            }
        }
    }
    
    
    
    
    
    
    // DEFINIÇÕES DAS CLASSES INTERNAS
    
    public class Vertice
    {
        private int idVertice;
        private List<Aresta> arestasAdjacentes = new LinkedList<>();
        
        public Vertice( int idVertice )
        {
            this.idVertice = idVertice;
        }

        /**
         * @return the arestasAdjacentes
         */
        public List<Aresta> getArestasAdjacentes() {
            return arestasAdjacentes;
        }
    }
    
    public class VerticeEspecialAD
    {
        private final int idVertice;
        private int peso;
        
        public VerticeEspecialAD( int id, int peso )
        {
            this.idVertice = id;
            this.peso = peso;
        }

        /**
         * @return the idVertice
         */
        public int getIdVertice() {
            return idVertice;
        }

        /**
         * @return the peso
         */
        public int getPeso() {
            return peso;
        }

        /**
         * @param peso the peso to set
         */
        public void setPeso(int peso) {
            this.peso = peso;
        }
        
        
        
        
    }
    
    
    public class Aresta
    {
        private final int idVeticeOrigem;
        private final int idVerticeDestino;
        private int peso;
        
        
        public Aresta( int origem, int destino, int peso )
        {
            this.idVerticeDestino = destino;
            this.idVeticeOrigem = origem;
            this.peso = peso;
        }

        /**
         * @return the idVeticeOrigem
         */
        public int getIdVeticeOrigem() {
            return idVeticeOrigem;
        }

        /**
         * @return the idVerticeDestino
         */
        public int getIdVerticeDestino() {
            return idVerticeDestino;
        }

        /**
         * @return the peso
         */
        public int getPeso() {
            return peso;
        }
    }

    /**
     * @return the verticesGrafo
     */
    public Vertice[] getVerticesGrafo() {
        return verticesGrafo;
    }

    /**
     * @return the numeroVertices
     */
    public int getNumeroVertices() {
        return numeroVertices;
    }

    /**
     * @return the numeroArestas
     */
    public int getNumeroArestas() {
        return numeroArestas;
    }
}
