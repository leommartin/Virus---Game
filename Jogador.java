import java.util.Random;
public abstract class Jogador
{
    protected Posicao pos;
    protected int atk, def;
    
    //Construtores
    public Jogador(Posicao pos,int atk, int def)
    {
        this.setAtk(atk);
        this.setDef(def);
        this.setPos(pos);
    }
    // Getters e Setters
    public Posicao getPos() 
    {
        return this.pos;
    }
    public void setPos(Posicao pos) 
    {
        this.pos = pos;
    }

    public int getAtk() 
    {
        return this.atk;
    }
    public void setAtk(int atk) 
    {
        this.atk = atk;
    }

    public int getDef() 
    {
        return this.def;
    }
    public void setDef(int def) 
    {
        this.def = def;
    }

    // Outro métodos
    public void atacar(){}
    
    public boolean movimentar(Jogador p, char direcao, Setor[][] tabuleiro)
    {
        Posicao pos;
        Porta porta;
        int linha,coluna;
        
        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();

        porta=tabuleiro[linha][coluna].getPorta();

        // m[y][x] ; para alterar m[l][c], altere l
        if (!tabuleiro[linha][coluna].existeInimigo())
        {
            if(direcao == 'u')
            {     
                if(porta.isAcima())
                {
                    pos.setY(linha - 1);
                }
            }
            else if(direcao == 'd')
            {
                if(porta.isAbaixo());
                    pos.setY(linha + 1);
            }
            else if(direcao == 'l')
            {
                if(porta.isEsquerda())
                    pos.setX(coluna - 1);
            }
            else
            {
                if(porta.isDireita())
                    pos.setX(coluna + 1);
           
            }
            return true;
        }
        return false;
    }

    public void procurar(Jogador p, Setor[][] tabuleiro)
    {
        Posicao pos;
        int linha, coluna;
        int defPlayer;
            
        Random random = new Random(); 
        int numAleatorio = random.nextInt(6)+1;

        defPlayer = p.getDef();
        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();

        if(numAleatorio == 4)
        {
            defPlayer = defPlayer + 1;
            p.setDef(defPlayer);
        }
        else if(numAleatorio == 5)
        {
            defPlayer = defPlayer + 2;
            p.setDef(defPlayer);
        }
        else if(numAleatorio == 6)
        {
            int qtdInimigos;
            int defInimigo;

            qtdInimigos = tabuleiro[linha][coluna].getListaDeInimigos().size();
            
            for (int i = 0; i < qtdInimigos; i++) 
            {    
                defInimigo = tabuleiro[linha][coluna].getInimigo(i).getDef();
                
                defInimigo = defInimigo - 1;

                tabuleiro[linha][coluna].getInimigo(i).setDef(defInimigo);
            }
            
        }
        
    }

    public void atacar(int indiceInimigo, Jogador p, Setor[][] tabuleiro, Posicao pos)
    {
        int linha, coluna;
        int atkPlayer;
        int defInimigo;
        
        linha = pos.getY();
        coluna = pos.getX();

        atkPlayer = p.getAtk();

        defInimigo = tabuleiro[linha][coluna].getInimigo(indiceInimigo).getDef();

        // Ataque do jogador em setor Oculto
        if(tabuleiro[linha][coluna] instanceof SetorOculto)
        {
            Random random = new Random(); 
            int numAleatorio = random.nextInt(1);
            
            if(numAleatorio != 0)
            {
                defInimigo = defInimigo - atkPlayer;

                if(defInimigo > 0)
                {
                    tabuleiro[linha][coluna].getInimigo(indiceInimigo).setDef(defInimigo);
                }
                else
                {
                    tabuleiro[linha][coluna].getListaDeInimigos().remove(indiceInimigo);
                }
            }  
        }
        else
        {
            defInimigo = defInimigo - atkPlayer;
            if(defInimigo > 0)
            {
                tabuleiro[linha][coluna].getInimigo(indiceInimigo).setDef(defInimigo);
            }
            else
            {
                tabuleiro[linha][coluna].getListaDeInimigos().remove(indiceInimigo);
            } 
        }

        int qtdInimigos = tabuleiro[linha][coluna].getListaDeInimigos().size();

        if(qtdInimigos < 1)
        {
            tabuleiro[linha][coluna].existeInimigo = false;
        }

        for(int i = 0; i < qtdInimigos; i++)
        {
            int atk = tabuleiro[linha][coluna].getInimigo(i).getAtk();
            int def = tabuleiro[linha][coluna].getInimigo(i).getDef();

            System.out.printf("%d - %d/%d\n", i+1, atk, def);
        }
    }
    
}