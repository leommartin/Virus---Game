public class Principal{
    public static void main(String[] args) 
    {
        
        Posicao posP1 = new Posicao();
        Posicao posP2 = new Posicao();

        posP1.setX(3);
        posP1.setY(3);
        JogadorSimples p1 = new JogadorSimples(posP1, 0, 0);

        posP2.setX(3);
        posP2.setY(3);
        JogadorSuporte p2 = new JogadorSuporte(posP2, 0, 0);
        
    }
}