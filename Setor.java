public class Setor
{
    protected Porta porta;
    protected boolean existeInimigo;
    protected boolean visitado;

    public Porta getPorta()
    {
        return this.porta;
    }

    public void setPorta(Porta porta)
    {
        this.porta=porta;
    }

    public boolean existeInimigo()
    {
        return this.existeInimigo;
    }

    public void setExisteInimigo(boolean existeInimigo)
    {
        this.existeInimigo=existeInimigo;
    }

    public boolean isVisitado()
    {
        return this.visitado;
    }

    public void setVisitado(boolean visitado)
    {
        this.visitado=visitado;
    }
}