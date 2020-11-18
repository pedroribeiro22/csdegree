import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class TrianguloTest {

    private Triangulo t;

    @BeforeEach
    public void init() {
        Ponto p0 = new Ponto(0, 0);
        Ponto p1 = new Ponto(2, 0);
        Ponto p2 = new Ponto(0, 2);
        t = new Triangulo(p0, p1, p2);
    }

    @Test
    void areaTriangulo() {
        double actual_area = 2.0;
        double calculated_area = t.areaTriangulo();
        Assertions.assertEquals(actual_area, calculated_area, 0.001);
    }

    @Test
    void testClone() {
        Ponto p1 = new Ponto(0, 0);
        Ponto p2 = new Ponto(2, 0);
        Ponto p3 = new Ponto(0, 2);
        Triangulo t = new Triangulo(p1, p2, p3);
        Triangulo clone = t.clone();
        Ponto t_0 = t.getPoligono().get(0);
        Ponto clone_t_0 = clone.getPoligono().get(0);
        Ponto t_1 = t.getPoligono().get(1);
        Ponto clone_t_1 = clone.getPoligono().get(1);
        Ponto t_2 = t.getPoligono().get(2);
        Ponto clone_t_2 = clone.getPoligono().get(2);
        Assertions.assertEquals(t_0.getX(), clone_t_0.getX());
        Assertions.assertEquals(t_0.getY(), clone_t_0.getY());
        Assertions.assertEquals(t_1.getX(), clone_t_1.getX());
        Assertions.assertEquals(t_1.getY(), clone_t_1.getY());
        Assertions.assertEquals(t_2.getX(), clone_t_2.getX());
        Assertions.assertEquals(t_2.getY(), clone_t_2.getY());
    }

}