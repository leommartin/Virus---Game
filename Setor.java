import java.util.*;

public abstract class Setor
{
    protected Porta porta;
    protected boolean existeInimigo;
    protected boolean visitado;
    protected ArrayList<Inimigo> listaDeInimigos = new ArrayList<Inimigo>(3);

    public Setor(){}
    
    public Porta getPorta()
    {
        return this.porta;
    }
    public void setPorta(Porta porta)
    {
        this.porta = porta;
    }

    public boolean existeInimigo()
    {
        return this.existeInimigo;
    }
    public void setExisteInimigo(boolean existeInimigo)
    {
        this.existeInimigo = existeInimigo;
    }

    public boolean isVisitado()
    {
        return this.visitado;
    }
    public void setVisitado(boolean visitado)
    {
        this.visitado = visitado;
    }

    public ArrayList<Inimigo> getListaDeInimigos()
    {
        return this.listaDeInimigos;
    }
    public Inimigo getInimigo(int indice) 
    {
        return this.getListaDeInimigos().get(indice);
    }
    public void setInimigo(Inimigo inimigo)
    {
        this.listaDeInimigos.add(inimigo);
    }


}